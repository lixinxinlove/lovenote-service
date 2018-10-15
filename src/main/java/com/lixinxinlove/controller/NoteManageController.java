package com.lixinxinlove.controller;


import com.lixinxinlove.entity.Note;
import com.lixinxinlove.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;


/**
 * 后台管理
 */
@Controller
@RequestMapping("/note/manage")
public class NoteManageController {


    @Autowired
    private NoteRepository noteRepository;

    /**
     * @param map
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(Map<String, Object> map) {
        List<Note> noteList = noteRepository.findAll();
        map.put("noteList", noteList);
        return new ModelAndView("note/list", map);
    }


}
