package com.lixinxinlove.controller;


import com.lixinxinlove.entity.UserInfo;
import com.lixinxinlove.repository.UserInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * 用户后台管理
 */
@Controller
@RequestMapping("/user/manage")
@Slf4j
public class UserManageController {


    @Autowired
    private UserInfoRepository userInfoRepository;

    /**
     * @param map
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(Map<String, Object> map) {
        List<UserInfo> userInfoList = userInfoRepository.findAll();
        map.put("userInfoList", userInfoList);
        return new ModelAndView("user/list", map);
    }


    @GetMapping("/index")
    public ModelAndView index(Map<String, Object> map) {
        return new ModelAndView("user/index", map);
    }


    /**
     * 表单参数的传递
     */
    // @RequestParam("phone") String phone
    // @RequestParam("password") String password
    @PostMapping("/login")
    public ModelAndView login(UserInfo user, Map<String, Object> map) {

        log.error("phone=={}", user.getPhone());
        log.error("password=={}", user.getPassword());

       // return new ModelAndView("user/list", map);
        return list(map);
    }

}
