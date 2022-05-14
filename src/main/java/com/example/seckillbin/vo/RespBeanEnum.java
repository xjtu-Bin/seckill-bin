package com.example.seckillbin.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
public enum RespBeanEnum {
    /**
     * 通用返回类型
     */
    SUCCESS(200,"SUCCESS"),
    ERROR(500,"服务端异常"),
    /**
     * 登陆模块5002xx
     */
    LOGIN_ERROR(500210,"用户名或密码不正确"),
    MOBILE_ERROR(500211,"手机号码不正确"),
    BINDING_ERROR(500212,"参数校验不正确"),
    LOGIN_TIMEOUT_ERROR(500213,"登陆过期，请重新登陆"),
    PASSWORD_UPDATE_ERROR(500214,"密码更新失败，请重试"),
    SESSION_ERROR(500215,"用户不存在"),
    /**
     * 秒杀模块5005xx
     */
    EMPTY_STOCK(500500,"库存不足"),
    REPEATE_ERROR(500501,"该商品每人限购一件"),
    /**
     * 订单模块5003xx
     */
    ORDER_NOT_EXIST(500300,"订单信息不存在")

    ;

    private final Integer code;
    private final String message;
}
