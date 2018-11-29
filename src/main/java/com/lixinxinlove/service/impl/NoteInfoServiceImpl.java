package com.lixinxinlove.service.impl;

import com.lixinxinlove.entity.NoteInfo;
import com.lixinxinlove.repository.NoteInfoRepository;
import com.lixinxinlove.service.NoteInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class NoteInfoServiceImpl implements NoteInfoService {

    @Autowired
    private NoteInfoRepository repository;


    @Override
    public NoteInfo findOne(Integer noteId) {
        return repository.getOne(noteId);
    }

    @Override
    public NoteInfo findByNoteId(Integer noteId) {
        return repository.findByNoteId(noteId);
    }

    @Override
    public List<NoteInfo> findAll() {
        return repository.findAll();
    }

    @Override
    public List<NoteInfo> findAllByUserId(String userId) {

        return repository.findByUserId(userId);
    }

    @Override
    public Page<NoteInfo> findList(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public NoteInfo save(NoteInfo note) {
        return repository.save(note);
    }

    @Override
    public void delete(NoteInfo note) {
         repository.delete(note);
    }
}
