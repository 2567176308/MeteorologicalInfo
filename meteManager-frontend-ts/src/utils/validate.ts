// 密码验证 、以字母开头，长度在8~18之间 、只能包含字母、数字、下划线
export function isvalidPass(passwd : string) {
    const reg = /^[a-zA-Z]\w{8,18}$/;
    return reg.test(passwd);
}

// 验证用户名  用户名要求 数字、字母、下划线的组合，其中数字和字母必须同时存在*
export function isvalidUsername(str: string) {
    const reg = /^(?![^A-Za-z]+$)(?![^0-9]+$)[0-9A-Za-z_]{4,15}$/;
    return reg.test(str);
}