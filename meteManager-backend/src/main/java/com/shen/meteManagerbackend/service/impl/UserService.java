package com.shen.meteManagerbackend.service.impl;

import com.shen.meteManagerbackend.dao.UserDao;
import com.shen.meteManagerbackend.dto.ReqLoginDTO;
import com.shen.meteManagerbackend.dto.ReqRegisterDTO;
import com.shen.meteManagerbackend.dto.ResLoginDTO;
import com.shen.meteManagerbackend.dto.ResRegisterDTO;
import com.shen.meteManagerbackend.entity.User;
import com.shen.meteManagerbackend.enumerate.IsLocked;
import com.shen.meteManagerbackend.enumerate.Role;
import com.shen.meteManagerbackend.exception.AccountHasLockedException;
import com.shen.meteManagerbackend.exception.DuplicateRegistrationException;
import com.shen.meteManagerbackend.exception.PasswordOrEmailErrorException;
import com.shen.meteManagerbackend.service.IAuthService;
import com.shen.meteManagerbackend.service.IUserService;
import com.shen.meteManagerbackend.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements IUserService {

    private final UserDao userDao;
    private final AuthenticationManager manager;
    private final PasswordEncoder passwordEncoder;
    private final IAuthService authService;
    @Override
    public ResRegisterDTO userRegister(ReqRegisterDTO reqRegisterDTO) throws DuplicateRegistrationException {
//        解决重复注册
        Integer i = userDao.countByMail(reqRegisterDTO.getUserMail());
        if (i == null || i != 0 ) {
            throw new DuplicateRegistrationException("This email address" +
                    " has already been registered or is incorrect");
        }
//        TODO 检验字段正确(略)
        final Date REGISTER_TIME = new Date();
//        转换数据 registerDTO -> User
        User user = User.builder()
                .userName(reqRegisterDTO.getUserName())
                .userMail(reqRegisterDTO.getUserMail())
                .passWord(passwordEncoder.encode(reqRegisterDTO.getPassWord())) // password加密
                .role(Role.USER) // 普通用户
                .isLock(IsLocked.UNLOCK.status()) // 非冻结用户
                .createTime(REGISTER_TIME)
                .updateTime(REGISTER_TIME)
                .build();


//        插入数据
        userDao.insertUser(user);
//        生成token
        String token = JwtUtil.generateToken(user);
//        完成注册
        return ResRegisterDTO.builder()
                .token(token)
                .userName(user.getUsername())
                .role(user.getRole())
                .build();
    }

    /**
     * 用户登录
     * @param reqLoginDTO user实例
     */
    @Override
    public ResLoginDTO userLogin(ReqLoginDTO reqLoginDTO) throws
            PasswordOrEmailErrorException,
            AccountHasLockedException {


//        根据邮箱比对用户密码是否正确
        UserDetails user = authService.loadUserByUsername(reqLoginDTO.getUserMail());
        String passWordFromDB = user.getPassword();
        String passWordFromWeb = reqLoginDTO.getPassWord();
//        密码不相等
        if (!passwordEncoder.matches(passWordFromWeb,passWordFromDB)) {
            throw new PasswordOrEmailErrorException("The email or password incorrect");
        }
        if (user.isAccountNonLocked()) {
            throw new AccountHasLockedException("Your account has been locked");
        }
//        密码相等
//        登录成功
//        生成token
        String token = JwtUtil.generateToken(user);
        User userP = (User) user;
        return ResLoginDTO.builder()
                .token(token)
                .userName(userP.getRealUserName())
                .role(userP.getRole())
                .build();
    }



}
