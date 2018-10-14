package com.lixinxinlove.service.impl;

import com.lixinxinlove.entity.Note;
import com.lixinxinlove.repository.NoteRepository;
import com.lixinxinlove.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteRepository repository;


    @Override
    public Note findOne(Integer noteId) {
        return repository.getOne(noteId);
    }

    @Override
    public List<Note> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Note> findAllByUserId(String userId) {

        return repository.findByUserId(userId);
    }

    @Override
    public Page<Note> findList(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Note save(Note note) {
        return repository.save(note);
    }

    @Override
    public void delete(Note note) {
         repository.delete(note);
    }
}
