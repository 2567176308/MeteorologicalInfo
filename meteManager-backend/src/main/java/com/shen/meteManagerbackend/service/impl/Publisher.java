package com.shen.meteManagerbackend.service.impl;

import com.shen.meteManagerbackend.dao.UserDao;
import com.shen.meteManagerbackend.entity.Subscription;
import com.shen.meteManagerbackend.entity.originData.Live;
import com.shen.meteManagerbackend.service.ICloudWeatherInfo;
import com.shen.meteManagerbackend.service.IEmailService;
import com.shen.meteManagerbackend.service.IPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class Publisher implements IPublisher {
    private final IEmailService emailService;
    private final UserDao userDao;
    private final ICloudWeatherInfo cloudWeatherInfo;
    // 每天早上七点执行计划
    @Scheduled(cron = "0 0 7 * * *")
    @Override
    public void publish() {
        List<Subscription> subscriptions = userDao.getSubscriptions();
        for (Subscription subscription : subscriptions) {
            String adCode = subscription.getAdCode();
            Live live = cloudWeatherInfo.getSingleLive(adCode).get(0);
            String weather = live.getWeather();
            if (weather.contains("雨") || weather.contains("雪")) {
//                雨雪天气预报
                emailService.sendWarningEmail(live,subscription.getEmails());
            }
        }



    }
    @Override
    @Scheduled(cron = "0 52 11 * * *")
    public void publisherMySelf() {
        List<Live> singleLive = cloudWeatherInfo.getSingleLive("441900");
        List<Live> singleLive1 = cloudWeatherInfo.getSingleLive("430182");
        List<String> myMail = new ArrayList<>();
        myMail.add("3118948439@qq.com");
        emailService.sendWarningEmail(singleLive.get(0),myMail);
        List<String> myMail1 = new ArrayList<>();
        myMail1.add("2567176308@qq.com");
        emailService.sendWarningEmail(singleLive1.get(0),myMail1);
    }
}
