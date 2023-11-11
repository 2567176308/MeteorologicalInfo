package com.shen.meteManagerbackend.service.impl;

import com.shen.meteManagerbackend.dao.UserDao;
import com.shen.meteManagerbackend.dto.*;
import com.shen.meteManagerbackend.entity.Address;
import com.shen.meteManagerbackend.entity.User;
import com.shen.meteManagerbackend.enumerate.IsLocked;
import com.shen.meteManagerbackend.enumerate.Role;
import com.shen.meteManagerbackend.exception.*;
import com.shen.meteManagerbackend.service.IAuthService;
import com.shen.meteManagerbackend.service.IUserService;
import com.shen.meteManagerbackend.util.JwtUtil;
import com.shen.meteManagerbackend.util.UserInfoUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements IUserService {

    private final UserDao userDao;
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

    @Override
    public void changePwd(ChangePwdDTO changePwdDTO) {
//        判断密码正误
        if (!changePwdDTO.getNewPassWord().equals(changePwdDTO.getRePassWord())) {
            throw new PassWordInconsistentException("Two password are inconsistent");
        }
//        判断原密码是否正确
        String ordPassWordFromDB = userDao.getUserByEmail(changePwdDTO.getUserMail())
                .map(User::getPassword)
                .orElseThrow(() -> new EmailNotfoundException("This email has not been registered yet"));
        if (!passwordEncoder.matches(changePwdDTO.getOldPassWord(), ordPassWordFromDB)) {
            throw new PasswordOrEmailErrorException("The email or password incorrect");
        }
//        修改密码到数据库
        User user = User.builder()
                .userMail(changePwdDTO.getUserMail())
                .passWord(passwordEncoder.encode(changePwdDTO.getNewPassWord())) // 加密后存入DB
                .build();
        userDao.updateUserInfo(user);
    }

    @Override
    public void addOrChangeAddress(Address address) {
        String userMail = UserInfoUtil.getUserMail();
        User user = User.builder()
                .address(address)
                .userMail(userMail)
                .updateTime(new Date())
                .build();
        userDao.updateUserInfo(user);
    }


    /*-------------------------admin-----------------------------*/
    @Override
    public void deleteUserByAdmin(Integer userId) {
        User user = User.builder()
                .deleteTime(new Date()) //逻辑删除时间
                .userId(userId)
                .build();
        deleteUser(user);
    }

    @Override
    public void deleteUserByAdmin(String userMail) {
        User user = User.builder()
                .deleteTime(new Date()) //逻辑删除时间
                .userMail(userMail)
                .build();
        deleteUser(user);
    }

    @Override
    public void lockUser(Integer userId) {
        User user = User.builder()
                .userId(userId)
                .isLock(true)
                .build();
        lockOrUnlockUser(user);
    }

    @Override
    public void lockUser(String userMail) {
        User user = User.builder()
                .userMail(userMail)
                .isLock(true)
                .build();
        lockOrUnlockUser(user);
    }

    @Override
    public void unlockUser(String userMail) {
        User user = User.builder()
                .userMail(userMail)
                .isLock(false)
                .build();
        lockOrUnlockUser(user);
    }

    @Override
    public void unlockUser(Integer userId) {
        User user = User.builder()
                .userId(userId)
                .isLock(false)
                .build();
        lockOrUnlockUser(user);
    }

    /**
     * 删除用户真实方法
     * @param user user实例
     */
    private void deleteUser(User user) {
        userDao.deleteUser(user);
    }

    /**
     * 冻结&解冻用户真实方法
     * @param user user实例
     */
    private void lockOrUnlockUser(User user) {
        userDao.lockOrUnlockUser(user);
    }

}
