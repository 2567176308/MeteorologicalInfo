package com.shen.meteManagerbackend.controller;

import com.shen.meteManagerbackend.common.CodeMsg;
import com.shen.meteManagerbackend.common.Result;
import com.shen.meteManagerbackend.dto.RegisterDTO;
import com.shen.meteManagerbackend.exception.DuplicateRegistrationException;
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
}
