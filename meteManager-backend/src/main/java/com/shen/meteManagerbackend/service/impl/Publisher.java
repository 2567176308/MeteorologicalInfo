package com.shen.meteManagerbackend.service.impl;

import com.shen.meteManagerbackend.dao.UserDao;
import com.shen.meteManagerbackend.entity.Subscription;
import com.shen.meteManagerbackend.entity.originData.ForeCasts;
import com.shen.meteManagerbackend.entity.originData.Live;
import com.shen.meteManagerbackend.service.ICloudWeatherInfo;
import com.shen.meteManagerbackend.service.IEmailService;
import com.shen.meteManagerbackend.service.IPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class Publisher implements IPublisher {
    private final IEmailService emailService;
    private final UserDao userDao;
    private final ICloudWeatherInfo cloudWeatherInfo;
    @Scheduled(cron = "0 0 0 * * *")
    @Override
    public void publish() {
        List<Subscription> subscriptions = userDao.getSubscriptions();
        for (Subscription subscription : subscriptions) {

            String cityCode = subscription.getAdCode();
            List<Live> singleLive = cloudWeatherInfo.getSingleLive(cityCode);
            Live live = singleLive.get(0);
            for (String email : subscription.getEmails()) {
                emailService.sendWarningEmail(live,email);
            }
        }
    }
}
