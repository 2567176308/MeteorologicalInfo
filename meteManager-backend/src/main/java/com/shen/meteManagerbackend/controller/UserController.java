package com.shen.meteManagerbackend.controller;

import com.shen.meteManagerbackend.common.CodeMsg;
import com.shen.meteManagerbackend.common.Result;
import com.shen.meteManagerbackend.dto.*;
import com.shen.meteManagerbackend.entity.Address;
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
    /**
     * 用户添加当前地址或修改当前地址
     * @param address 地址代码
     * @return Result
     */
    @PostMapping("/user/addAddress")
    public Result<?> addAddress(@RequestBody Address address) {
        userService.addOrChangeAddress(address);
        return Result.success();
    }

    @PostMapping("/user/subscribeService/{adCode}")
    public Result<?> subscribeService(@PathVariable String adCode) {
        userService.subscribe(adCode);
        return Result.success();
    }
    /*----------------------------------power by admin---------------------------------------------*/

    /**
     * 逻辑删除用户(管理员)
     * @param userId 用户id
     * @return result
     */
    @DeleteMapping("/admin/deleteUserById/{userId}")
    public Result<?> deleteUser(@PathVariable Integer userId) {
        userService.deleteUserByAdmin(userId);
        return Result.success();
    }
    /**
     * 逻辑删除用户(管理员)
     * @param userMail 用户mail
     * @return result
     */
    @DeleteMapping("/admin/deleteUserByMail/{userMail}")
    public Result<?> deleteUser(@PathVariable String userMail) {
        userService.deleteUserByAdmin(userMail);
        return Result.success();
    }
    /**
     * 冻结用户(管理员)
     * @param userId 用户id
     * @return result
     */
    @PutMapping("/admin/lockUserById/{userId}")
    public Result<?> lockUser(@PathVariable Integer userId) {
        userService.lockUser(userId);
        return Result.success();
    }
    /**
     * 冻结用户(管理员)
     * @param userMail 用户Mail
     * @return result
     */
    @PutMapping("/admin/lockUserByMail/{userMail}")
    public Result<?> lockUser(@PathVariable String userMail) {
        userService.lockUser(userMail);
        return Result.success();
    }
    /**
     * 解冻用户(管理员)
     * @param userMail 用户Mail
     * @return result
     */
    @PutMapping("/admin/unlockUserByMail/{userMail}")
    public Result<?> unlockUser(@PathVariable String userMail) {
        userService.unlockUser(userMail);
        return Result.success();
    }
    /**
     * 解冻用户(管理员)
     * @param userId 用户Mail
     * @return result
     */
    @PutMapping("/admin/unlockUserById/{userId}")
    public Result<?> unlockUser(@PathVariable Integer userId) {
        userService.unlockUser(userId);
        return Result.success();
    }
}
