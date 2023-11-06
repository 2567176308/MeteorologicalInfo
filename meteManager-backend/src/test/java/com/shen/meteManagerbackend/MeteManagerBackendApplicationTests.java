package com.shen.meteManagerbackend;

import com.shen.meteManagerbackend.dao.UserDao;
import com.shen.meteManagerbackend.entity.User;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@SpringBootTest
@RequiredArgsConstructor
class MeteManagerBackendApplicationTests {
	private final UserDao userDao;
	@Test
	void contextLoads() {
	}




}
