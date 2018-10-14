package com.lixinxinlove.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class Note {

    @Id
    @GeneratedValue
    private Integer noteId;

    private String userId;

    private String noteText;


    /** 创建时间. */
    private Date createTime;
    /** 更新时间. */
    private Date updateTime;

}
