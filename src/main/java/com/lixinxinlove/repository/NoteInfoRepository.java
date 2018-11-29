package com.lixinxinlove.repository;

import com.lixinxinlove.entity.NoteInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteInfoRepository extends JpaRepository<NoteInfo,Integer> {

    List<NoteInfo> findByUserId (String userId);

    NoteInfo findByNoteId (Integer noteId);
}
