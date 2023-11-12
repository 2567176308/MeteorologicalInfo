package com.shen.meteManagerbackend.service.impl;

import com.shen.meteManagerbackend.entity.originData.*;
import com.shen.meteManagerbackend.service.ICloudWeatherInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CloudWeatherInfo implements ICloudWeatherInfo {
    @Value("${lbs.key}")
    private String apiKey;
    private final RestTemplate restTemplate;

    @Override
    public OriginRespFromLsb getOriginData(String cityCode) {
        String url = "https://restapi.amap.com/v3/weather/weatherInfo?key="+ apiKey + "&city="+ cityCode + "&extensions=all";

        return restTemplate.getForObject(url, OriginRespFromLsb.class);
    }

    @Override
    public SingleRespFromLsb getSingleOriginData(String cityCode) {
        String url = "https://restapi.amap.com/v3/weather/weatherInfo?key="+ apiKey + "&city=" + cityCode;
        return restTemplate.getForObject(url, SingleRespFromLsb.class);
    }

    @Override
    public List<ForeCasts> getForCasts(String cityCode) {

        OriginRespFromLsb originData = getOriginData(cityCode);
        return originData.getForecasts();
    }

    @Override
    public List<Live> getSingleLive(String cityCode) {
        SingleRespFromLsb singleOriginData = getSingleOriginData(cityCode);
        return singleOriginData.getLives();
    }
}
