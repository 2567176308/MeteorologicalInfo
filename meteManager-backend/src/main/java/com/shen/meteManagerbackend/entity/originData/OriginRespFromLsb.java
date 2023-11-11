package com.shen.meteManagerbackend.entity.originData;

import lombok.Data;

import java.util.List;

@Data
public class OriginRespFromLsb implements OriginResp{
    String status;
    String count;
    String info;
    String infocode;
    List<ForeCasts> forecasts;
}
