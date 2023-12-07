<template>
  <el-menu
    :default-active="activeIndex"
    class="el-menu-demo"
    mode="horizontal"
    :ellipsis="false"
    @select="handleSelect"
  >
    <el-menu-item index="0">
      <img
        style="width: 150px"
        src="src/assets/cover.png"
        alt="Element logo"
      />
    </el-menu-item>
    <div class="flex-grow" />
    <el-menu-item index="1" @click="toPage('index')">首页</el-menu-item>
    <el-menu-item index="2" @click="toPage('login')" v-if="!isLogin">
        <el-button type="primary" round>登录</el-button>
    </el-menu-item>
    <el-menu-item index="3" @click="toPage('register')" v-if="!isLogin">
        <el-button type="success" round >注册</el-button>
    </el-menu-item>
    <el-sub-menu index="4" v-show="isLogin">
      <template #title>{{ userName }}</template>
      <el-menu-item index="4-1">item one</el-menu-item>
      <el-menu-item index="4-2">item two</el-menu-item>
      <el-menu-item index="4-3" @click="logout()">退出登录</el-menu-item>
      <el-sub-menu index="4-4">
        <template #title>item four</template>
        <el-menu-item index="4-4-1">item one</el-menu-item>
        <el-menu-item index="4-4-2">item two</el-menu-item>
        <el-menu-item index="4-4-3">item three</el-menu-item>
      </el-sub-menu>
    </el-sub-menu>
  </el-menu>
</template>

<script lang="ts" setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router' 
import { LStorage } from '../utils/storage';
import { inject,provide } from 'vue'
let isLogin = inject('isLogin')
const toVue = inject<any>('toVue')
const activeIndex = ref('1')
const handleSelect = (key: string, keyPath: string[]) => {
  console.log(key, keyPath)
}
const router = useRouter()
const toPage = (url:string) => {
  router.push(url);
}

const userName = LStorage.getOrigin("userName")
if( userName !== null && userName !== undefined) {
  isLogin = true
}

function toHeader(){
  console.log("why")
  location.reload()
}
// provide
// 退出登录
const  logout = ()=> {
  LStorage.delete("token")
  LStorage.delete("userName")
  isLogin = false
  toHeader()
  toVue()
}


</script>

<style>
.flex-grow {
  flex-grow: 1;
}
</style>

