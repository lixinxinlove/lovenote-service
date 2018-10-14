package com.lixinxinlove.repository;

import com.lixinxinlove.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note,Integer> {

    List<Note> findByUserId (String userId);
}
