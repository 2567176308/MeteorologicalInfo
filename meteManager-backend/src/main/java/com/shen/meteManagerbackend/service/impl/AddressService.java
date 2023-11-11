package com.shen.meteManagerbackend.service.impl;

import com.shen.meteManagerbackend.dao.AddressDao;
import com.shen.meteManagerbackend.dao.UserDao;
import com.shen.meteManagerbackend.service.IAddressService;
import com.shen.meteManagerbackend.util.UserInfoUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressService implements IAddressService {
    private final UserDao userDao;
    @Override
    public void addAddressCode(String adCode) {
        String correctUserMail = UserInfoUtil.getUserMail();
    }
}
