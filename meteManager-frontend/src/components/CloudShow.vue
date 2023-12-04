<template>
  <div class="container">
    <div class="nav">
      <div class="time">{{ localTime }}</div>
      <div class="city" @click="changeCity">切换城市</div>
    </div>

    <div class="city-info">
      <el-row>
    <el-col
      v-for="(o, index) in 2"
      :key="o"
      :span="8"
      :offset="index > 0 ? 2 : 0"
    >
      <el-card :body-style="{ padding: '0px' }">
        <img
          src="https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png"
          class="image"
        />
        <div style="padding: 14px">
          <span>Yummy hamburger</span>
          <div class="bottom">
            <time class="time">{{ currentDate }}</time>
            <el-button text class="button">Operating</el-button>
          </div>
        </div>
      </el-card>
    </el-col>
  </el-row>
    </div>
  </div>
</template>

<script>
import * as echarts from "echarts";
import { areaList } from '@vant/area-data';
import { ref } from 'vue';
const currentDate = ref(new Date())
export default {
  data() {
    return {
      localTime: "",
      mapData: {},
      futureMapData: [],
      seriesData: [],
      seriesNightData: [],
      areaList: areaList,
      show: false,
      currentDate: currentDate
    };
  },
  created() {
    setInterval(() => {
      this.localTime = this.getLocalTime();
    }, 1000);

    this.initMap();
  },
  methods: {
    getLocalTime() {
      return new Date().toLocaleTimeString();
    },
    initMap() {
      let that = this;
      AMap.plugin("AMap.CitySearch", function () {
        var citySearch = new AMap.CitySearch();
        citySearch.getLocalCity(function (status, result) {
          if (status === "complete" && result.info === "OK") {
            // 查询成功，result即为当前所在城市信息
            // console.log(result.city);
            that.getWeatherData(result.city);
          }
        });
      });
    },
    getWeatherData(cityName) {
      let that = this;
      AMap.plugin("AMap.Weather", function () {
        //创建天气查询实例
        var weather = new AMap.Weather();

        //执行实时天气信息查询
        weather.getLive(cityName, function (err, data) {
          console.log(err, data);
          that.mapData = data;
        });

        //执行实时天气信息查询
        weather.getForecast(cityName, function (err, data) {
          that.futureMapData = data.forecasts;
          console.log(that.futureMapData);

          // 每天的温度
          that.seriesData = []
          that.seriesNightData = []
          data.forecasts.forEach(item => {
            that.seriesData.push(item.dayTemp)
            that.seriesNightData.push(item.nightTemp)
          })

          that.$nextTick(() => {
            that.initEchart()
          })
        });
      });
    },
    initEchart() {
      // 基于准备好的dom，初始化echarts实例
      let myChart = echarts.init(this.$refs.echartContainer);

      // 绘制图表
      let option = {
        title: {
          text: "未来天气走向",
        },
        tooltip: {},
        xAxis: {
          data: ["今天", "明天", "后天", "三天后"],
          axisTick: {
            show: false
          },
          axisLine: {
            lineStyle: {
              color: "#fff"
            }
          }
        },
        yAxis: {
          min: '-10',
          max: '50',
          interval: 10,
          axisLine: {
            show: true,
            lineStyle: {
              color: '#fff'
            }
          },
          splitLine: {
            show: true,
            lineStyle: {
              type: "dashed",
              color: ['red', 'green', 'yellow']
            }
          }
        },
        series: [
          {
            name: "白天温度",
            type: "line",
            data: this.seriesData,
          },
          {
            name: "夜间温度",
            type: "line",
            data: this.seriesNightData,
            lineStyle: {
              color: 'red'
            }
          },
        ],
      }
      myChart.setOption(option)
    },
    changeCity() {
      this.show = true
    },
    selectCity(e) {
      console.log(e[1].name);
      this.getWeatherData(e[1].name)
      this.show = false
    }
  },
};
</script>

<style lang="less" scoped>
.container {
  // min-height: 100vh;
  // background: skyblue;
  // opacity: 0.7;
  // color: #fff;
  // font-size: 16px;
  .nav {
    display: flex;
    justify-content: space-between;
    padding: 10px;
  }
  .city-info {
    text-align: center;
    .temp {
      font-size: 26px;
      em {
        font-size: 34px;
        font-style: normal;
      }
    }
  }
  .future {
    margin-top: 30px;
    padding: 0 10px;
    .group {
      height: 44px;
      line-height: 44px;
      border-radius: 4px;
      background: rgba(255, 255, 255, 0.6);
      color: rgba(16, 16, 16, 1);
      font-size: 13px;
      margin-bottom: 10px;
      padding: 0 10px;
      .tm {
        color: #fff;
      }
      .tm:last-child {
        margin-left: 8px;
      }
    }
  }
  .echart-container {
    width: 100%;
    height: 50vh;
  }
}
.time {
  font-size: 12px;
  color: #999;
}

.bottom {
  margin-top: 13px;
  line-height: 12px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.button {
  padding: 0;
  min-height: auto;
}

.image {
  width: 100%;
  display: block;
}
</style>