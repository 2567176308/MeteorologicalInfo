package com.shen.meteManagerbackend.service;

import com.shen.meteManagerbackend.entity.originData.ForeCasts;
import com.shen.meteManagerbackend.entity.originData.Live;
import com.shen.meteManagerbackend.entity.originData.OriginResp;
import com.shen.meteManagerbackend.entity.originData.OriginRespFromLsb;

import java.util.List;

public interface ICloudWeatherInfo {

    /**
     * 获取api调用原始数据
     * @param cityCode 城市编码
     * @return OriginResponse
     */
    OriginResp getOriginData(String cityCode);

    OriginResp getSingleOriginData(String cityCode);
    /**
     * 获取详细信息
     * @param cityCode 城市编码
     * @return forCasts
     */
    List<ForeCasts> getForCasts(String cityCode);


    List<Live> getSingleLive(String cityCode);


}
