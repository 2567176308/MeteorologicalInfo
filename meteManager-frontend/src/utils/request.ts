import axios from 'axios'


export function request(config:any) {
    // 创建axios实例
    const instance = axios.create({
        baseURL: 'http://localhost:8080',
        timeout:100000
    })
    // 全局拦截
    instance.interceptors.request.use(config => {
        // 拦截请求
        return config
    },err => {})
    // 拦截响应
    instance.interceptors.response.use(res => {
        return res.data
    }, err => {
        console.log(err)
    })
    // 发送真正网络请求
    return instance(config)
}

