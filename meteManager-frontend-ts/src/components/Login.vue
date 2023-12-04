<template>
    <div class="login">
        <div class="loginName">
            <h2>登录</h2>
        </div>
        <div class="card">
            <el-form :model="loginForm" label-width="60px">
                <el-form-item label="邮箱">
                <el-input 
                v-model="loginForm.userMail" 
                class="w-50 m-2"
                size="large"
                clearable
                placeholder="Please Input"/>
                </el-form-item>
                <el-popover
                    placement="right"
                    :width="200"
                    trigger="hover"
                    :content= validation
                    >
                    <template #reference>
                    <el-form-item label="密码">
                        <el-input
                        v-model="loginForm.passWord"
                        type="password"
                        class="w-50 m-2"
                        size="large"
                        placeholder="Please input password"
                        maxlength="16"
                        show-password/>
                </el-form-item>
                    </template>
                </el-popover>
                
                <el-form-item>
                    <a href="#">忘记密码?</a>
                </el-form-item>
                <el-form-item>
                    <el-button class="form-btns" type="primary" @click="login" size="large" round>登录</el-button>
                    <el-button class="form-btns" type="success" size="large" round>注册</el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>

<script lang="ts" setup>
import { reactive } from 'vue'
import { ref } from 'vue'

import { userLogin } from '../api/user'
import useStore from 'element-plus/es/components/table/src/store/index.mjs';
import router from '@/router';
import { useRouter } from 'vue-router';
import {LStorage} from '../api/storage'
let loginForm = reactive({
    userMail: '',
    passWord: '',
})
let err = ref('')
let validation = ref("请满足密码要求")
const store = useStore()
const usert = useRouter()
function login() {
    userLogin(  loginForm.userMail,
                loginForm.passWord
    ).then((res)=> {
        if(!(res && res.code === 200)) {
            // 否定前置
            err = res.msg
            validation.value = '账号或密码不正确'
            console.log(validation.value)
            return;
        }
        LStorage.set("token",res.data.token)
        console.log("success")
        usert.push("/index")
        console.log(res)
    })
}


</script>

<style>

.login {
    width: 600px;
    height: 400px;
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    border: 1px solid #ccc;
    text-align: center;
    padding: 20px;
    border-radius: 10px; /* 圆润边框 */
    border: 2px solid #87CEEB; /* 天蓝色边框 */
    background-color: #f0f8ff; /* 淡蓝色背景 */
}
.card {
    text-align: center;
    margin: 40px;
}
.loginName {
    text-align: center;
    font-size: 27px;
    margin-bottom: 20px;
    font-weight: 700;
}
.form-btns {
    text-align: center;
    margin-top: 60px;
    /* margin-right: 160px; */
    width: 40%; /* 让按钮充满父容器宽度 */
    font-size: 18px; /* 自定义字体大小，调整按钮尺寸 */
    padding: 15px; /* 自定义内边距，调整按钮尺寸 */
}
.el-alert {
  margin: 20px 0 0;
}
.el-alert:first-child {
  margin: 0;
}
</style>
