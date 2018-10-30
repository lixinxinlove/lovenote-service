package com.lixinxinlove.service;

import com.lixinxinlove.entity.Note;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 笔记
 */
public interface NoteService {


    Note findOne(Integer noteId);

    Note findByNoteId(Integer noteId);

    List<Note> findAll();

    List<Note> findAllByUserId(String userId);

    Page<Note> findList(Pageable pageable);

    /**
     * 添加
     *
     * @param note
     * @return
     */
    Note save(Note note);

    /**
     * 删除
     *
     * @param note
     * @return
     */
    void delete(Note note);


}
