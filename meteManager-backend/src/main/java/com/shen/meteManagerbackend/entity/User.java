package com.shen.meteManagerbackend.entity;

import com.shen.meteManagerbackend.enumerate.Role;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;

/**
 * 用户实体类 , 用户鉴权登录实现UserDetails接口
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements UserDetails {

    private Integer userId;
    private String userName;
    private String passWord;
    private String userMail;
    private Address address;
    private Role role;
    private Boolean isLock;
    private Date createTime;
    private Date updateTime;
    private Date deleteTime;


    /**
     * 获取权限信息
     * @return role
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(role.name()));
    }

    /**
     * 获取密码
     * @return password
     */
    @Override
    public String getPassword() {
        return passWord;
    }


    /**
     * 获取用户名 (采取邮箱登录方式、故应该是邮箱)
     * @return email
     */
    @Override
    public String getUsername() {
        return getUserMail();
    }

    public String getRealUserName() {
        return userName;
    }

    /**
     * 判断用户使用情况
     * @return boolean
     */
    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    /**
     * 判断用户是否被冻结情况
     * @return boolean
     */
    @Override
    public boolean isAccountNonLocked() {
        return getIsLock();
    }

    /**
     * 凭据是否过期（暂不启用）
     * @return boolean
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 是否可用(暂不启用)
     * @return boolean
     */
    @Override
    public boolean isEnabled() {
        return !(getDeleteTime() == null);
    }
}
