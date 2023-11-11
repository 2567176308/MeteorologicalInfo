package com.shen.meteManagerbackend.service;

import org.springframework.stereotype.Service;

public interface IAddressService {

    /**
     * 添加地址code
     * @param adCode 地址code
     */
    void addAddressCode(String adCode);
}
