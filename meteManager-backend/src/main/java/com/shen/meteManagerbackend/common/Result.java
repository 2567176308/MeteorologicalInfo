package com.shen.meteManagerbackend.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result <T> implements Serializable {
    private String msg;
    private int code;
    private T data;

    /**
     * 成功success
     * @param data 返回数据
     */
    private Result(T data) {
        this.code = 200;
        this.msg ="success";
        this.data = data;
    }

    /**
     * 原型定义
     * @param cmg codeMsg实例
     */
    private Result(CodeMsg cmg) {
        if (cmg == null) {
            throw new NullPointerException("codeMsg should not be a null");
        }
        this.code = cmg.getCode();
        this.msg = cmg.getMsg();
    }

    /**
     * 执行成功并返回包装数据
     * @param data 具体数据
     * @return result 实例
     * @param <T> data泛型
     */
    public static <T> Result<T> success(T data) {
        return new Result<>(data);
    }


//    成功不许要传入参数类型
    @SuppressWarnings("unchecked")
    public static <T> Result<T> success() {
        return (Result<T>) success("");
    }

    /**
     * 返回特定失败状态
     * @param cmg codeMsg 实例
     * @return Result实例
     * @param <T> result 的泛型
     */
    public static <T> Result<T> error(CodeMsg cmg) {
        return new Result<T>(cmg);
    }

    public static <T> Result<T> error(CodeMsg cmg,String message) {
        CodeMsg newCmg = null;
        try {
             newCmg = cmg.clone();
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
        newCmg.setMsg(cmg.getMsg() + "--" + message);
        return new Result<>(newCmg);
    }

}
