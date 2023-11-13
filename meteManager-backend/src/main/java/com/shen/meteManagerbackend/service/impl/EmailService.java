package com.shen.meteManagerbackend.service.impl;

import com.shen.meteManagerbackend.entity.originData.ForeCasts;
import com.shen.meteManagerbackend.entity.originData.Live;
import com.shen.meteManagerbackend.service.IEmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService implements IEmailService {
    private final MailSender mailSender;
    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;
    @Value("${spring.mail.username}")
    private String from;
    @Override
    public void sendSimpleEmail(String to, String subject, String text) {
        SimpleMailMessage simpleMailMessage =
                new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setText(text);
        simpleMailMessage.setSubject(subject);
        try {
            mailSender.send(simpleMailMessage);
        }catch (MailException e) {
//            TODO do what at here
            log.info("mail sent exception--->{e}",e);
        }
    }

    @Override
    public void sendComplexEmail(String to, String subject, String text, String filePath) {

    }

    @Override
    public void sendWarningEmail(ForeCasts foreCasts, String to) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject("天气预警");
            Context context = new Context();
            context.setVariable("city",foreCasts.getCity());
            context.setVariable("province",foreCasts.getProvince());
            context.setVariable("date",foreCasts.getReporttime());
            context.setVariable("weather","下雨");
            String content = templateEngine.process("/earlyWarning.html",context);
            mimeMessageHelper.setText(content);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void sendWarningEmail(Live live, String to) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setSubject("天气预警");
            Context context = getContext(live);
            String content = templateEngine.process("/earlyWarning.html",context);
            mimeMessageHelper.setText(content);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendWarningEmail(Live live, List<String> tos) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
            tos.forEach((to) -> {
                try {
                    mimeMessageHelper.addTo(to);
                } catch (MessagingException e) {
                    throw new RuntimeException(e);
                }
            });
            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setSubject("天气预警");
            Context context = getContext(live);
            String content = templateEngine.process("/earlyWarning.html",context);
            mimeMessageHelper.setText(content);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private static Context getContext(Live live) {
        Context context = new Context();
        context.setVariable("city", live.getCity());
        context.setVariable("province", live.getProvince());
        context.setVariable("date", live.getReporttime());
        context.setVariable("weather", live.getWeather());
        context.setVariable("temperature", live.getTemperature_float());
        context.setVariable("humidity", live.getHumidity_float());
        context.setVariable("windDirection", live.getWinddirection());
        context.setVariable("windPower", live.getWindpower());
        return context;
    }

}
