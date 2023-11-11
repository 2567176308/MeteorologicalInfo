package com.shen.meteManagerbackend.entity.originData;

import lombok.Data;

import java.util.Date;

@Data
public class Cast {
    Date date;
    String week;
    String dayweather;
    String nightweather;
    String daytemp;
    String nighttemp;
    String daywind;
    String nightwind;
    String daypower;
    String nightpower;
    String daytemp_float;
    String nighttemp_float;
}
