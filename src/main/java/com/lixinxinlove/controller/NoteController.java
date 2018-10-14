package com.lixinxinlove.controller;


import com.lixinxinlove.entity.Note;
import com.lixinxinlove.service.NoteService;
import com.lixinxinlove.utils.ResultVOUtil;
import com.lixinxinlove.vo.NoteOV;
import com.lixinxinlove.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/note")
public class NoteController {

    @Autowired
    private NoteService userInfoService;

    @GetMapping("/list")
    public ResultVO<List<NoteOV>> list() {

        List<Note> noteList = userInfoService.findAll();

        List<NoteOV> noteOVList = new ArrayList<>();

        for (Note note : noteList) {
            NoteOV noteOV = new NoteOV();
            BeanUtils.copyProperties(note, noteOV);
            noteOVList.add(noteOV);
        }
        return ResultVOUtil.success(noteOVList);
    }

}
