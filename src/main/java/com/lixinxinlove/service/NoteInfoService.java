package com.lixinxinlove.service;

import com.lixinxinlove.entity.NoteInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 笔记
 */
public interface NoteInfoService {


    NoteInfo findOne(Integer noteId);

    NoteInfo findByNoteId(Integer noteId);

    List<NoteInfo> findAll();

    List<NoteInfo> findAllByUserId(String userId);

    Page<NoteInfo> findList(Pageable pageable);

    /**
     * 添加
     *
     * @param note
     * @return
     */
    NoteInfo save(NoteInfo note);

    /**
     * 删除
     *
     * @param note
     * @return
     */
    void delete(NoteInfo note);


}
