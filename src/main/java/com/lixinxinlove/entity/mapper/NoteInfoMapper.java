package com.lixinxinlove.entity.mapper;

import com.lixinxinlove.entity.NoteInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface NoteInfoMapper {

    @Insert("insert into note_info(user_id,note_text) values(#{user_id,jdbcType=VARCHAR},#{note_text,jdbcType=VARCHAR})")
    int insertByMap(Map<String, Object> map);


    @Insert("insert into note_info(user_id,note_text) values(#{userId,jdbcType=VARCHAR},#{noteText,jdbcType=VARCHAR})")
    int insertByObject(NoteInfo note);

    @Select("select * from note_info where note_id=#{noteId}")
    @Results({@Result(column = "note_id", property = "noteId"),
            @Result(column = "user_id", property = "userId"),
            @Result(column = "note_text", property = "noteText"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime")})
    NoteInfo findByNoteId(Integer noteId);


    @Select("select * from note_info where user_id=#{userId}")
    @Results({@Result(column = "note_id", property = "noteId"),
            @Result(column = "user_id", property = "userId"),
            @Result(column = "note_text", property = "noteText"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime")})
    List<NoteInfo> findByUserId(String userId);

    @Update("update note_info set note_text= #{noteText} where note_id=#{noteId}")
    int updateByNoteId(@Param("noteText") String noteText, @Param("noteId") Integer noteId);


    @Update("update note_info set note_text= #{noteText} where note_id=#{noteId}")
    int updateByObject(NoteInfo note);

    @Delete("delete from note_info where note_id=#{noteId}")
    int deleteByNoteId(Integer noteId);


    NoteInfo selectByNoteId(Integer noteId);

}
