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
//    @Scheduled(cron = "0 0 7 * * *")
    @Override
    public void publish() {
        List<Subscription> subscriptions = userDao.getSubscriptions();
        for (Subscription subscription : subscriptions) {

            String cityCode = subscription.getAdCode();
            List<Live> singleLive = cloudWeatherInfo.getSingleLive(cityCode);
            Live live = singleLive.get(0);
            emailService.sendWarningEmail(live,subscription.getEmails());
        }
    }
    @Override
    @Scheduled(cron = "0 0 7 * * *")
    public void publisherMySelf() {
        List<Live> singleLive = cloudWeatherInfo.getSingleLive("441900");
        List<Live> singleLive1 = cloudWeatherInfo.getSingleLive("430182");
        Live live = singleLive.get(0);
        Live live1 = singleLive1.get(0);
        if (live1.getWeather().contains("雨") || live1.getWeather().contains("雪")) {
            List<String> myMail = new ArrayList<>();
            myMail.add("3118948439@qq.com");
            emailService.sendWarningEmail(singleLive.get(0),myMail);
        }
        if (live.getWeather().contains("雨") || live.getWeather().contains("雪")) {
            List<String> myMail = new ArrayList<>();
            myMail.add("2567176308@qq.com");
            emailService.sendWarningEmail(singleLive.get(0),myMail);
        }
    }
}
