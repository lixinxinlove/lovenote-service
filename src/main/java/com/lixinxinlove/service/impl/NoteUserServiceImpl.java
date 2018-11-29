package com.lixinxinlove.service.impl;

import com.lixinxinlove.entity.NoteUser;
import com.lixinxinlove.repository.NoteUserRepository;
import com.lixinxinlove.service.NoteUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class NoteUserServiceImpl implements NoteUserService {


    @Autowired
    private NoteUserRepository repository;

    @Override
    public NoteUser findOne(String userId) {
        return repository.getOne(userId);
    }

    @Override
    public List<NoteUser> findAll() {
        return repository.findAll();
    }

    @Override
    public NoteUser findOneByPhoneAndPassword(String phone, String password) {
        return repository.findOneByPhoneAndPassword(phone, password);
    }

    @Override
    public NoteUser findOneByPhone(String phone) {
        return repository.findOneByPhone(phone);
    }

    @Override
    public Page<NoteUser> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public NoteUser save(NoteUser userInfo) {
        return repository.save(userInfo);
    }

    @Override
    public void delete(NoteUser userInfo) {
        repository.delete(userInfo);
    }
}
