package com.shen.meteManagerbackend.dao;

import com.shen.meteManagerbackend.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface UserDao {

//    添加用户
    void insertUser(User user);

//    根据邮箱查询账户数量(结果一般为0，用于检测是否重复注册)
    Integer countByMail(String userMail);

    User getUserByEmail(String userMail);



}
