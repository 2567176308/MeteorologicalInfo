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
	@Autowired
	private IEmailService emailService;
	@Autowired
	private IPublisher publisher;
	@Test
	void contextLoads() {
		String to = "2567176308@qq.com";
		String text = "Hello World";
		String subject = "You can do it";
		ForeCasts foreCasts = new ForeCasts();
		foreCasts.setCity("宁乡");
		foreCasts.setProvince("浙江");
		foreCasts.setReporttime("2023/11/12");
		List<Cast> castList = new ArrayList<>();
		Cast cast = new Cast();
		cast.setDate(new Date());
		castList.add(cast);
		castList.add(cast);
		foreCasts.setCasts(castList);
		emailService.sendWarningEmail(foreCasts,to);
	}
	@Test
	void test01() {
		publisher.publish();
	}
}
