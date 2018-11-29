package com.lixinxinlove.controller;


import com.lixinxinlove.entity.NoteInfo;
import com.lixinxinlove.form.NoteInfoForm;
import com.lixinxinlove.service.impl.NoteInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 后台管理
 */
@Controller
@RequestMapping("/note/manage")
public class NoteInfoManageController {


    @Autowired
    private NoteInfoServiceImpl noteService;

    /**
     * @param map
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(Map<String, Object> map) {
        List<NoteInfo> noteList = noteService.findAll();
        map.put("noteList", noteList);
        return new ModelAndView("note/list", map);
    }

    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "noteId", required = false) Integer noteId, Map<String, Object> map) {
        NoteInfo note = noteService.findByNoteId(noteId);
        map.put("note", note);
        return new ModelAndView("note/index", map);
    }


    @PostMapping("/save")
    public ModelAndView postEdit(@Valid NoteInfoForm noteForm, Map<String, Object> map) {

        NoteInfo note=new NoteInfo();
        note.setUpdateTime(new Date());
        note.setCreateTime(new Date());

        note.setNoteId(Integer.valueOf(noteForm.getNoteId()));
        note.setUserId(noteForm.getUserId());
        note.setNoteText(noteForm.getNoteText());

        noteService.save(note);

        return new ModelAndView("redirect:" + "/note/manage/list");

    }


}
