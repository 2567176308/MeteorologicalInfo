package com.shen.meteManagerbackend.dto;

import lombok.Data;

/**
 * 修改密码传入数据
 */
@Data
public class ChangePwdDTO {

    private String userMail;
    private String oldPassWord;
//    前端判断、后端还需再判断
    private String newPassWord;
    private String rePassWord;

}
