package com.lixinxinlove.service;


import com.lixinxinlove.entity.UserInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 用户信息
 */
public interface UserInfoService {

    UserInfo findOne(String userId);

    List<UserInfo> findAll();

    Page<UserInfo> findAll(Pageable pageable);

    /**
     * 添加
     *
     * @param userInfo
     * @return
     */
    UserInfo save(UserInfo userInfo);

    /**
     * 删除
     *
     * @param userInfo
     * @return
     */
    void delete(UserInfo userInfo);

}
