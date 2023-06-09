package com.prc.transfer.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum RespBeanEnum {
    SUCCESS(200,"SUCCESS"),
    ERROR(500,"服务端异常"),
    //登录模块
    LOGIN_ERROR(500210,"用户名密码错误"),
    BIND_ERROR(500212,"参数校验异常"),
    SESSION_ERROR(500215,"用戶不存在");
    //模块5005xx
    //公共返回对象枚举
    private final Integer code;
    private final String message;


}
