// 登录
import { request } from '../utils/request'
export function userLogin(userMail: string,passWord: string) {
    return request({
        method: 'post',
        url: '/user/api/login',
        data: {
            userMail: userMail,
            passWord: passWord
        },
        headers: {
            'Authorization': window.localStorage.token
        }
    })
}