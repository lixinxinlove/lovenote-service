package com.lixinxinlove.entity.mapper;

import org.apache.ibatis.annotations.Insert;

import java.util.Map;

public interface NoteMapper {

    @Insert("insert into note(user_id,note_text) values(#{user_id,jdbcType=VARCHAR},#{note_text,jdbcType=VARCHAR})")
    int insertByMap(Map<String, Object> map);

}
