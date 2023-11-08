package com.shen.meteManagerbackend.service;

import com.shen.meteManagerbackend.dto.*;
import com.shen.meteManagerbackend.exception.AccountHasLockedException;
import com.shen.meteManagerbackend.exception.DuplicateRegistrationException;
import com.shen.meteManagerbackend.exception.EmailNotfoundException;
import com.shen.meteManagerbackend.exception.PasswordOrEmailErrorException;

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


    /**
     * 修改密码
     * @param changePwdDTO changePwdDTO
     */
    void changePwd(ChangePwdDTO changePwdDTO);

    /*----------------------------power by admin----------------------------*/
    void deleteUserByAdmin(Integer userId);
    void deleteUserByAdmin(String userMail);

    void lockUser(Integer userId);
    void lockUser(String userMail);

    void unlockUser(String userMail);
    void unlockUser(Integer userId);
}
