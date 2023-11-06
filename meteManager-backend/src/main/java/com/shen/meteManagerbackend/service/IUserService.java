package com.shen.meteManagerbackend.service;

import com.shen.meteManagerbackend.dto.ReqLoginDTO;
import com.shen.meteManagerbackend.dto.ReqRegisterDTO;
import com.shen.meteManagerbackend.dto.ResLoginDTO;
import com.shen.meteManagerbackend.dto.ResRegisterDTO;
import com.shen.meteManagerbackend.exception.AccountHasLockedException;
import com.shen.meteManagerbackend.exception.DuplicateRegistrationException;
import com.shen.meteManagerbackend.exception.EmailNotfoundException;
import com.shen.meteManagerbackend.exception.PasswordOrEmailErrorException;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService {

    /**
     * 用户注册
     * @param reqRegisterDTO user实例
     */
    ResRegisterDTO userRegister(ReqRegisterDTO reqRegisterDTO) throws DuplicateRegistrationException;

    /**
     * 用户登录
     * @param reqLoginDTO user实例
     */
    ResLoginDTO userLogin(ReqLoginDTO reqLoginDTO) throws PasswordOrEmailErrorException, EmailNotfoundException, AccountHasLockedException;

}
