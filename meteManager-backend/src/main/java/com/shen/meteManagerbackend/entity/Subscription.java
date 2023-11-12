package com.shen.meteManagerbackend.entity;

import lombok.Data;

import java.util.List;

@Data
public class Subscription {

    private String adCode;
    private List<String> emails;
}
