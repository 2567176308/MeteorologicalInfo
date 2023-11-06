package com.shen.meteManagerbackend.service.impl;

import com.shen.meteManagerbackend.dao.UserDao;
import com.shen.meteManagerbackend.dto.RegisterDTO;
import com.shen.meteManagerbackend.entity.User;
import com.shen.meteManagerbackend.enumerate.IsLocked;
import com.shen.meteManagerbackend.enumerate.Role;
import com.shen.meteManagerbackend.exception.DuplicateRegistrationException;
import com.shen.meteManagerbackend.service.IUserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
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

    @Override
    public void userLogin(RegisterDTO user) {

    }
}
