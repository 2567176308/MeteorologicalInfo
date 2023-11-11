package com.shen.meteManagerbackend.entity;

import lombok.Data;

@Data
public class Address {
    private String chineseName;
    private String adCode; //primary key
    private String cityCode;
}
