package com.shen.meteManagerbackend;

import com.shen.meteManagerbackend.dao.UserDao;
import com.shen.meteManagerbackend.entity.User;
import com.shen.meteManagerbackend.entity.originData.Cast;
import com.shen.meteManagerbackend.entity.originData.ForeCasts;
import com.shen.meteManagerbackend.service.ICloudWeatherInfo;
import com.shen.meteManagerbackend.service.IEmailService;
import com.shen.meteManagerbackend.service.IPublisher;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
class MeteManagerBackendApplicationTests {
	@Autowired
	private ICloudWeatherInfo cloudWeatherInfo;
//	@Autowired
//	private IEmailService emailService;
	@Autowired
	private IPublisher publisher;
	@Autowired
	private UserDao userDao;
	@Test
	void test01() {
		publisher.publish();
//		System.out.println(cloudWeatherInfo.getSingleLive("654221"));
	}
}
