package com.lixinxinlove.repository;


import com.lixinxinlove.entity.NoteUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 用户信息
 */
public interface NoteUserRepository extends JpaRepository<NoteUser, String> {

    List<NoteUser> findByUserId(String userId);

    NoteUser findOneByPhoneAndPassword(String phone, String password);

    /**
     * 通过手机号查询
     * @param phone
     * @return
     */
    NoteUser findOneByPhone(String phone);

}
