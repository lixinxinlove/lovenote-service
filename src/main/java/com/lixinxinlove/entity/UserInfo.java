package com.lixinxinlove.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class UserInfo {

    @Id
    private String userId;

    private String userName;

    private String phone;

    private String email;

    private String password;

    /**
     * 性别0女1男2中性'
     */
    private Integer sex;

    private String address;

    private String userIcon;

    /**
     * 创建时间.
     */
    private Date createTime;
    /**
     * 更新时间.
     */
    private Date updateTime;


}
