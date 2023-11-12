package com.shen.meteManagerbackend.dao;

import com.shen.meteManagerbackend.entity.Subscription;
import com.shen.meteManagerbackend.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserDao {

//    添加用户
    void insertUser(User user);

//    根据邮箱查询账户数量(结果一般为0，用于检测是否重复注册)
    Integer countByMail(String userMail);
//根据邮箱查询指定用户
    Optional<User> getUserByEmail(String userMail);
//    更新用户信息
    void updateUserInfo(User user);

    Integer countAddressByMail(String userMail);

    void updateAddressByMail(User user);

    List<String> getOrderAddress();


    List<Subscription> getSubscriptions();
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
