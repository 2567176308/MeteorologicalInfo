package com.shen.meteManagerbackend.controller;

import com.shen.meteManagerbackend.common.CodeMsg;
import com.shen.meteManagerbackend.common.Result;
import com.shen.meteManagerbackend.dto.LoginDTO;
import com.shen.meteManagerbackend.dto.RegisterDTO;
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
     * @param registerDTO userDetails from frontend
     * @return Result<String>
     */
    @PostMapping("/api/register")
    public Result<String> register(
            @RequestBody RegisterDTO registerDTO) {
        try {
            userService.userRegister(registerDTO);
            return Result.success("registration success");
        } catch (DuplicateRegistrationException e) {
            return Result.error(CodeMsg.EMPTY_PARAM_ERROR,"your email may has been registered");
        }
    }


    /**
     * 用户登录
     * @param loginDTO 前端传入数据信息（email + password）
     * @return ? TODO 具体前端需要数据
     */
    @PostMapping("/api/login")
    public Result<?> login(@RequestBody LoginDTO loginDTO) {
        try {
            userService.userLogin(loginDTO);
            return Result.success();
        } catch (PasswordOrEmailErrorException | EmailNotfoundException | AccountHasLockedException e) {
            return Result.error(CodeMsg.EMPTY_PARAM_ERROR,e.getMessage());
        }
    }
}
