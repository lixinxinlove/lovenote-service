package com.lixinxinlove.vo;


import lombok.Data;

@Data
public class UserInfoOV {

    private String userId;

    private String userName;

    private String phone;

    /**性别0女1男2中性'*/
    private Integer sex;

    private String address;

    private String userIcon;

}
