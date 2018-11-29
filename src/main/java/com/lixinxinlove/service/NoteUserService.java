package com.lixinxinlove.service;


import com.lixinxinlove.entity.NoteUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 用户信息
 */
public interface NoteUserService {

    NoteUser findOne(String userId);

    List<NoteUser> findAll();

    NoteUser findOneByPhoneAndPassword(String phone, String password);

    NoteUser findOneByPhone(String phone);

    Page<NoteUser> findAll(Pageable pageable);

    /**
     * 添加
     *
     * @param userInfo
     * @return
     */
    NoteUser save(NoteUser userInfo);

    /**
     * 删除
     *
     * @param userInfo
     * @return
     */
    void delete(NoteUser userInfo);

}
