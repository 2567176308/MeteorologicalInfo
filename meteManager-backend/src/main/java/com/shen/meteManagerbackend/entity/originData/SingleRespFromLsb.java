package com.shen.meteManagerbackend.entity.originData;

import lombok.Data;

import java.util.List;

@Data
public class SingleRespFromLsb implements OriginResp{
    String status;
    String count;
    String info;
    String infocode;
    List<Live> lives;
}
