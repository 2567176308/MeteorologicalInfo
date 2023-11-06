package com.shen.meteManagerbackend.dto;

import lombok.Data;

@Data
public class ReqRegisterDTO {
    private String userMail;
    private String passWord;
    private String userName;
}
