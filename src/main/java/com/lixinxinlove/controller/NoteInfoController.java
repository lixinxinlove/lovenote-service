package com.lixinxinlove.controller;


import com.lixinxinlove.entity.NoteInfo;
import com.lixinxinlove.service.NoteInfoService;
import com.lixinxinlove.utils.ResultVOUtil;
import com.lixinxinlove.vo.NoteInfoOV;
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
public class NoteInfoController {

    @Autowired
    private NoteInfoService userInfoService;

    @GetMapping("/list")
    public ResultVO<List<NoteInfoOV>> list() {

        List<NoteInfo> noteList = userInfoService.findAll();

        List<NoteInfoOV> noteOVList = new ArrayList<>();

        for (NoteInfo note : noteList) {
            NoteInfoOV noteOV = new NoteInfoOV();
            BeanUtils.copyProperties(note, noteOV);
            noteOVList.add(noteOV);
        }
        return ResultVOUtil.success(noteOVList);
    }


    //获取用户下所有的记录
    @GetMapping("/notes")
    public ResultVO<List<NoteInfoOV>> noteList(String userId) {

        List<NoteInfo> noteList = userInfoService.findAllByUserId(userId);

        List<NoteInfoOV> noteOVList = new ArrayList<>();

        for (NoteInfo note : noteList) {
            NoteInfoOV noteOV = new NoteInfoOV();
            BeanUtils.copyProperties(note, noteOV);
            noteOVList.add(noteOV);
        }
        return ResultVOUtil.success(noteOVList);
    }


}
