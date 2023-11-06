package com.shen.meteManagerbackend.controller;

import com.shen.meteManagerbackend.dao.UserDao;
import com.shen.meteManagerbackend.entity.User;
import com.shen.meteManagerbackend.enumerate.IsLocked;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("test")
@AllArgsConstructor
public class TestController {
    private final UserDao userDao;
    @PostMapping("/testUser")
    public void insertUser(@RequestBody User user) {
        final Boolean IS_NOT_LOCK = false;
        Date date = new Date();
        user.setCreateTime(date);
        user.setUpdateTime(date);
        user.setIsLock(IsLocked.UNLOCK.status());
        userDao.insertUser(user);
    }
}
