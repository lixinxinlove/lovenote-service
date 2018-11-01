package com.lixinxinlove.mapper;


import com.lixinxinlove.entity.mapper.NoteMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class NoteMapperTest {

    @Autowired
    private NoteMapper mapper;

    @Test
    public void insertByMap() throws Exception {

        Map<String, Object> map = new HashMap<>();
        map.put("user_id", "0");
        map.put("note_text", "mybatis测试");
        int i = mapper.insertByMap(map);
        Assert.assertEquals(1, i);

    }

}
