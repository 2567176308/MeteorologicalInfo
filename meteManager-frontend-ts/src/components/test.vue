<template>
    <div id="container">
    </div>
</template>
<script setup>
import AMapLoader from '@amap/amap-jsapi-loader';
export default {
  name: "map-view",
  mounted() {
    this.initAMap();
  },
  unmounted() {
    this.map?.destroy();
  },
  methods: {
    initAMap() {
      AMapLoader.load({
        key: "58139b03432e5df5015e6163ae5535f3", // 申请好的Web端开发者Key，首次调用 load 时必填
        version: "2.0", // 指定要加载的 JSAPI 的版本，缺省时默认为 1.4.15
        plugins: ["AMap.Weather"], // 需要使用的的插件列表，如比例尺'AMap.Scale'等
      })
        .then((AMap) => {
          this.map = new AMap.Map("container", {
            // 设置地图容器id
            viewMode: "3D", // 是否为3D地图模式
            zoom: 11, // 初始化地图级别
            center: [116.397428, 39.90923], // 初始化地图中心点位置
          });
        })
        .catch((e) => {
          console.log(e);
        });
    },
  },
};
</script>
<style scoped>
    #container{
        padding:0px;
        margin: 0px;
        width: 100%;
        height: 800px;
    }
</style>

