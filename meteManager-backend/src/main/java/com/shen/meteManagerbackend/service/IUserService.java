package com.shen.meteManagerbackend.service;

import com.shen.meteManagerbackend.dto.LoginDTO;
import com.shen.meteManagerbackend.dto.RegisterDTO;
import com.shen.meteManagerbackend.entity.User;
import com.shen.meteManagerbackend.exception.AccountHasLockedException;
import com.shen.meteManagerbackend.exception.DuplicateRegistrationException;
import com.shen.meteManagerbackend.exception.EmailNotfoundException;
import com.shen.meteManagerbackend.exception.PasswordOrEmailErrorException;

public interface IUserService {

    /**
     * 用户注册
     * @param registerDTO user实例
     */
    void userRegister(RegisterDTO registerDTO) throws DuplicateRegistrationException;

    /**
     * 用户登录
     * @param loginDTO user实例
     */
    void userLogin(LoginDTO loginDTO) throws PasswordOrEmailErrorException, EmailNotfoundException, AccountHasLockedException;

}
