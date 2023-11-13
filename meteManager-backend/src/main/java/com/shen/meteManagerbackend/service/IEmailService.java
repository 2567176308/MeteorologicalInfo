package com.shen.meteManagerbackend.service;

import com.shen.meteManagerbackend.entity.originData.Cast;
import com.shen.meteManagerbackend.entity.originData.ForeCasts;
import com.shen.meteManagerbackend.entity.originData.Live;

import java.util.List;

public interface IEmailService {

    /**
     * 发送邮件
     */
    void sendSimpleEmail(String to,String subject,String text);

    void sendComplexEmail(String to,String subject,String text,String filePath);

    void sendWarningEmail(ForeCasts foreCasts, String to);

    void sendWarningEmail(Live live,String to);

    void sendWarningEmail(Live live, List<String> tos);

}
