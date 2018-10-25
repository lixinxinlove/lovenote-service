package com.lixinxinlove.repository;


import com.lixinxinlove.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 用信息
 */
public interface UserInfoRepository extends JpaRepository<UserInfo, String> {

    List<UserInfo> findByUserId(String userId);

    UserInfo findOneByPhoneAndPassword(String phone, String password);

    /**
     * 通过手机号查询
     * @param phone
     * @return
     */
    UserInfo findOneByPhone(String phone);

}
