package com.shen.meteManagerbackend.controller;

import com.shen.meteManagerbackend.common.Result;
import com.shen.meteManagerbackend.entity.originData.ForeCasts;
import com.shen.meteManagerbackend.entity.originData.OriginResp;
import com.shen.meteManagerbackend.service.ICloudWeatherInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("service")
public class CloudController {
    private final ICloudWeatherInfo cloudWeatherInfo;
    @GetMapping("/api/getWeatherInfoByCity/{city}")
    public Result<List<ForeCasts>> getWeatherInfo(@PathVariable String city) {
        return null;
    }
    @GetMapping("api/getWeatherInfoByCode/{adCode}")
    public Result<List<ForeCasts>> getWeatherInfo(@PathVariable Integer adCode) {
        List<ForeCasts> forCasts = cloudWeatherInfo.getForCasts(adCode.toString());
        return Result.success(forCasts);
    }
}
