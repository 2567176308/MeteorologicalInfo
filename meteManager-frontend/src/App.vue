<script setup>
import CloudShowVue from './components/CloudShow.vue';
import Header from './components/Header.vue';
import Login from './components/Login.vue'
import Register from './components/Register.vue';
import Footer from './components/Footer.vue';
import { ref ,provide,nextTick } from 'vue'
// 是否登录？ 传递给子组件


const reload = () => {
  isShow.value = false
  nextTick(()=> {
    isShow.value = true
  })
}

const toVue = () => {
    location.reload
}
provide('isLogin',false)
provide('reload',reload)
provide('toVue',toVue)

</script>

<template>
  <Header />
  <main>
    <RouterView></RouterView>
  </main>
</template>

<style scoped>
</style>
