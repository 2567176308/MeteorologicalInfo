package com.shen.meteManagerbackend.dto;

import lombok.Data;

/**
 * 用户登录前端输入数据
 */
@Data
public class LoginDTO {
    private String userMail;
    private String passWord;
}
