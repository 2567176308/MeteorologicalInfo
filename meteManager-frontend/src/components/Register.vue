<template>
    <div class="register">
        <div class="registerName">
            <h2>注册</h2>
        </div>
        <div class="card">
            <el-form :model="form" label-width="60px">
                <el-form-item label="邮箱">
                <el-input 
                v-model="form.name" 
                class="w-50 m-2"
                size="large"
                clearable
                placeholder="Please Input"/>
                </el-form-item>
                 <el-form-item label="用户名">
                <el-input 
                v-model="form.name" 
                class="w-50 m-2"
                size="large"
                clearable
                placeholder="Please Input"/>
                </el-form-item>
                <el-form-item label="密码">
                <el-input
                v-model="input"
                type="password"
                class="w-50 m-2"
                size="large"
                placeholder="Please input password"
                maxlength="16"
                show-password/>
                </el-form-item>
                <el-form-item label="确认">
                <el-input
                v-model="input"
                type="password"
                class="w-50 m-2"
                size="large"
                placeholder="Please input password"
                maxlength="16"
                show-password/>
                </el-form-item>
                <el-form-item>
                    <a href="#">已有账号?</a>
                </el-form-item>
                <el-form-item>
                <el-button class="form-btns" type="success" @click="onSubmit" size="large" round>注册</el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
    <component is="Login"></component>
</template>

<script lang="ts" setup>
import { reactive } from 'vue'
import { ref } from 'vue'
const input = ref('')
// do not use same name with ref
const form = reactive({
  name: '',
  region: '',
  date1: '',
  date2: '',
  delivery: false,
  type: [],
  resource: '',
  desc: '',
})

const onSubmit = () => {
  console.log('submit!')
}
</script>

<style scoped>

.register {
    width: 600px;
    height: 600px;
    position: absolute;
    top:60%;
    left: 50%;
    transform: translate(-50%, -50%);
    border: 1px solid #ccc;
    text-align: center;
    padding: 20px;
    border-radius: 10px; /* 圆润边框 */
    border: 2px solid #87CEEB; /* 天蓝色边框 */
    background-color: #f0f8ff; /* 淡蓝色背景 */
}
/* .card {
    text-align: center;
    margin: px;
} */
.registerName {
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
</style>
