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

    Optional<User> getUserByEmail(String userMail);

    void updateUserInfo(User user);



//    ===========================================================
    /**
     * 管理员操作、删除用户(逻辑删除,支持根据id和根据邮箱)
     * @param user 用户ID 以及deleteTime
     */
    void deleteUser(User user);


    /**
     * 管理员操作、冻结&&解冻用户
     * @param user 用户(支持根据id和根据邮箱)
     */
    void lockOrUnlockUser(User user);

}
