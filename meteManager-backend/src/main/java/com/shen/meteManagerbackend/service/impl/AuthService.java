package com.shen.meteManagerbackend.service.impl;

import com.shen.meteManagerbackend.dao.UserDao;
import com.shen.meteManagerbackend.exception.EmailNotfoundException;
import com.shen.meteManagerbackend.service.IAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {
    private final UserDao userDao;
    /**
     * 实际上以mail为准
     * @param userMail 用户邮箱
     * @return UserDetails 实例
     * @throws UsernameNotFoundException 邮箱未找到 == 邮箱未被注册
     */
    @Override
    public UserDetails loadUserByUsername(String userMail) throws UsernameNotFoundException {
        return userDao.getUserByEmail(userMail)
                .orElseThrow(() -> new EmailNotfoundException("This email has not been registered yet"));
    }
}
