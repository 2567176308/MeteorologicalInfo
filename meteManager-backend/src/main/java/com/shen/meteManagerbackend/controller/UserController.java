package com.shen.meteManagerbackend.controller;

import com.shen.meteManagerbackend.common.CodeMsg;
import com.shen.meteManagerbackend.common.Result;
import com.shen.meteManagerbackend.dto.ReqLoginDTO;
import com.shen.meteManagerbackend.dto.ReqRegisterDTO;
import com.shen.meteManagerbackend.dto.ResLoginDTO;
import com.shen.meteManagerbackend.dto.ResRegisterDTO;
import com.shen.meteManagerbackend.exception.AccountHasLockedException;
import com.shen.meteManagerbackend.exception.DuplicateRegistrationException;
import com.shen.meteManagerbackend.exception.EmailNotfoundException;
import com.shen.meteManagerbackend.exception.PasswordOrEmailErrorException;
import com.shen.meteManagerbackend.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {
    private final IUserService userService;

    /**
     * 注册用户
     * @param reqRegisterDTO userDetails from frontend
     * @return Result<String>
     */
    @PostMapping("/api/register")
    public Result<?> register(
            @RequestBody ReqRegisterDTO reqRegisterDTO) {
        try {
            ResRegisterDTO resRegisterDTO = userService.userRegister(reqRegisterDTO);
            return Result.success(resRegisterDTO);
        } catch (DuplicateRegistrationException e) {
            return Result.error(CodeMsg.EMPTY_PARAM_ERROR,"your email may has been registered");
        }
    }


    /**
     * 用户登录
     * @param reqLoginDTO 前端传入数据信息（email + password）
     * @return ? TODO 具体前端需要数据
     */
    @PostMapping("/api/login")
    public Result<?> login(@RequestBody ReqLoginDTO reqLoginDTO) {
        try {
            ResLoginDTO resLoginDTO = userService.userLogin(reqLoginDTO);
            return Result.success(resLoginDTO);
        } catch (PasswordOrEmailErrorException | EmailNotfoundException | AccountHasLockedException e) {
            return Result.error(CodeMsg.EMPTY_PARAM_ERROR,e.getMessage());
        }
    }
}
