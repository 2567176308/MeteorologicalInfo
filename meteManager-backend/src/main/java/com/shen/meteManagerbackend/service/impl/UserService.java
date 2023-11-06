package com.shen.meteManagerbackend.service.impl;

import com.shen.meteManagerbackend.dao.UserDao;
import com.shen.meteManagerbackend.dto.LoginDTO;
import com.shen.meteManagerbackend.dto.RegisterDTO;
import com.shen.meteManagerbackend.entity.User;
import com.shen.meteManagerbackend.enumerate.IsLocked;
import com.shen.meteManagerbackend.enumerate.Role;
import com.shen.meteManagerbackend.exception.AccountHasLockedException;
import com.shen.meteManagerbackend.exception.DuplicateRegistrationException;
import com.shen.meteManagerbackend.exception.EmailNotfoundException;
import com.shen.meteManagerbackend.exception.PasswordOrEmailErrorException;
import com.shen.meteManagerbackend.service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements IUserService {
    private final UserDao userDao;
    @Override
    public void userRegister(RegisterDTO registerDTO) throws DuplicateRegistrationException {
//        解决重复注册
        Integer i = userDao.countByMail(registerDTO.getUserMail());
        if (i == null || i != 0 ) {
            throw new DuplicateRegistrationException("This email address" +
                    " has already been registered or is incorrect");
        }
//        检验字段正确(略)
//        TODO 密码加密|认证与鉴权
        final Date REGISTER_TIME = new Date();
//        转换数据 registerDTO -> User
        User user = User.builder()
                .userName(registerDTO.getUserName())
                .userMail(registerDTO.getUserMail())
                .passWord(registerDTO.getPassWord())
                .role(Role.USER) // 普通用户
                .isLock(IsLocked.UNLOCK.status()) // 非冻结用户
                .createTime(REGISTER_TIME)
                .updateTime(REGISTER_TIME)
                .build();


//        插入数据
        userDao.insertUser(user);
//        完成注册
    }

    /**
     * 用户登录
     * @param loginDTO user实例
     */
    @Override
    public void userLogin(LoginDTO loginDTO) throws
            PasswordOrEmailErrorException,
            EmailNotfoundException,
            AccountHasLockedException {
//        根据邮箱比对用户密码是否正确
        User user = userDao.getUserByEmail(loginDTO.getUserMail());
        String passWordFromDB = user.getPassWord();
//        密码不相等
//        TODO 密码加密后比对
        if (!passWordFromDB.equals(loginDTO.getPassWord())) {
            throw new PasswordOrEmailErrorException("The email or password incorrect");
        }
//        TODO 加入 security修改一下
        if (user.getIsLock()) {
            throw new AccountHasLockedException("Your account has been locked");
        }
//        密码相等
//        TODO... 对应处理逻辑 |认证与鉴权 | 制作Jwt等等
//        登录成功
    }
}
