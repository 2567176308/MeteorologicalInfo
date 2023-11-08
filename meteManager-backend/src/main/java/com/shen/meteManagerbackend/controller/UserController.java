package com.shen.meteManagerbackend.controller;

import com.shen.meteManagerbackend.common.CodeMsg;
import com.shen.meteManagerbackend.common.Result;
import com.shen.meteManagerbackend.dto.*;
import com.shen.meteManagerbackend.exception.*;
import com.shen.meteManagerbackend.service.IUserService;
import com.shen.meteManagerbackend.util.UserInfoUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
            ResRegisterDTO resRegisterDTO = userService.userRegister(reqRegisterDTO);
            return Result.success(resRegisterDTO);
    }


    /**
     * 用户登录
     * @param reqLoginDTO 前端传入数据信息（email + password）
     * @return Result<ResLoginDTO>
     */
    @PostMapping("/api/login")
    public Result<?> login(@RequestBody ReqLoginDTO reqLoginDTO) {
        ResLoginDTO resLoginDTO = userService.userLogin(reqLoginDTO);
        return Result.success(resLoginDTO);
    }

    /**
     * 用户修改密码、作用于仅仅在于该用户线程不允许跨用户操作
     * 仅仅允许修改自身密码
     * @param changePwdDTO from front end
     * @return success or fail
     */
    @PutMapping("/user/changePwd")
    public Result<?> changePassWord(@RequestBody ChangePwdDTO changePwdDTO){

        if (!UserInfoUtil.isCorrectMail(changePwdDTO.getUserMail())) {
            return Result.error(CodeMsg.EMPTY_PARAM_ERROR);
        }
        userService.changePwd(changePwdDTO);
        return Result.success();
    }
}
