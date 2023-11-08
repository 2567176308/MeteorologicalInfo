package com.shen.meteManagerbackend.util;

import com.shen.meteManagerbackend.entity.User;
import com.shen.meteManagerbackend.exception.UserHasNotLoginException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserInfoUtil {

    /**
     * 获取当前线程操作用户信息
     * @return userMail
     */
    private static User getUser() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        判断有无认证登录
        if ("anonymousUser".equals(authentication.getPrincipal())) {
            throw new UserHasNotLoginException("user has not login yet");
        }
        return (User) authentication.getPrincipal();
    }

    /**
     * 获取用户具体mail
     * @return userMail
     */
    public static String getUserMail() {

        return getUser().getUserMail();
    }

    /**
     * 判断是否是用户自身操作？有没有越权操作
     * @param inputMail 输入邮箱
     * @return true ？ 是自身 ： 不是自身或邮箱有误
     */
    public static boolean isCorrectMail(String inputMail) {
        return inputMail.equals(getUserMail());
    }

}
