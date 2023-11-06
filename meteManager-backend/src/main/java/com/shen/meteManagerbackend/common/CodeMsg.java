package com.shen.meteManagerbackend.common;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CodeMsg implements Cloneable{

    private int code;
    private String msg;

    // 通用异常
    public static CodeMsg SUCCESS = new CodeMsg(200, "success");
    public static CodeMsg EMPTY_PARAM_ERROR = new CodeMsg(400,
            "There is may be someTing wrong with the parameter you provided.");
    public static CodeMsg SERVER_ERROR = new CodeMsg(505, "server exception");
    public static CodeMsg AUTH_ERROR = new CodeMsg(403,"auth fail");
    /**
     * 浅拷贝
     * @return codeMsg 实例
     */
    @Override
    public CodeMsg clone() {
        try {
            return (CodeMsg) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
