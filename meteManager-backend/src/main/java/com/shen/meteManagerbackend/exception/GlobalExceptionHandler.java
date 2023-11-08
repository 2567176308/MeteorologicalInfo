package com.shen.meteManagerbackend.exception;

import com.shen.meteManagerbackend.common.CodeMsg;
import com.shen.meteManagerbackend.common.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(DuplicateRegistrationException.class)
    public Result<?> handleDuplicateRegistrationException(DuplicateRegistrationException e) {
        return Result.error(CodeMsg.EMPTY_PARAM_ERROR,"your email may has been registered");
    }

    @ExceptionHandler({PasswordOrEmailErrorException.class, EmailNotfoundException.class, AccountHasLockedException.class})
    public Result<?> handleLoginException(Exception e) {
        return Result.error(CodeMsg.EMPTY_PARAM_ERROR,e.getMessage());
    }

    @ExceptionHandler(PassWordInconsistentException.class)
    public Result<?> handlePassWordInconsistentException(PassWordInconsistentException e) {
        return Result.error(CodeMsg.EMPTY_PARAM_ERROR,e.getMessage());
    }
}
