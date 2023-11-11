package com.shen.meteManagerbackend.entity.originData;

import lombok.Data;

import java.util.List;

@Data

public class ForeCasts {
    String city;
    String adcode;
    String province;
    String reporttime;
    List<Cast> casts;
}
