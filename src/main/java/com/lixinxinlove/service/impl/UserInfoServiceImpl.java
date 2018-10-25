package com.lixinxinlove.service.impl;

import com.lixinxinlove.entity.UserInfo;
import com.lixinxinlove.repository.UserInfoRepository;
import com.lixinxinlove.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserInfoServiceImpl implements UserInfoService {


    @Autowired
    private UserInfoRepository repository;

    @Override
    public UserInfo findOne(String userId) {
        return repository.getOne(userId);
    }

    @Override
    public List<UserInfo> findAll() {
        return repository.findAll();
    }

    @Override
    public UserInfo findOneByPhoneAndPassword(String phone, String password) {
        return repository.findOneByPhoneAndPassword(phone, password);
    }

    @Override
    public UserInfo findOneByPhone(String phone) {
        return repository.findOneByPhone(phone);
    }

    @Override
    public Page<UserInfo> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public UserInfo save(UserInfo userInfo) {
        return repository.save(userInfo);
    }

    @Override
    public void delete(UserInfo userInfo) {
        repository.delete(userInfo);
    }
}
