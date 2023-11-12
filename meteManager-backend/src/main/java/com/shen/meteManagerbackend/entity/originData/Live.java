package com.shen.meteManagerbackend.entity.originData;

import lombok.Data;

@Data
public class Live {
    private String province;
    private String city;
    private String adcode;
    private String weather;
    private String temperature;
    private String winddirection;
    private String windpower;
    private String humidity;
    private String reporttime;
    private String temperature_float;
    private String humidity_float;
}
