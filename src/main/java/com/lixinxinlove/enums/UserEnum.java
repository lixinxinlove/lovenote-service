package com.lixinxinlove.enums;

import lombok.Getter;

@Getter
public enum UserEnum {


    SUCCESS(0, "成功"),

    USER_EXIST(1, "该用户已经注册"),

    REGISTER_FAIL(2,"注册失败");


    private Integer code;

    private String message;

    UserEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }


}
