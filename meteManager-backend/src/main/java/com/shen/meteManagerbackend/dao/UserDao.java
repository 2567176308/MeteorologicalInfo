package com.shen.meteManagerbackend.dao;

import com.shen.meteManagerbackend.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface UserDao {

//    添加用户
    void insertUser(User user);

     Integer countByMail(String userMail);
}
