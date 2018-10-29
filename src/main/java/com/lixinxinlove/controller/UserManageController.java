package com.lixinxinlove.controller;


import com.lixinxinlove.constant.RedisConstant;
import com.lixinxinlove.entity.UserInfo;
import com.lixinxinlove.repository.UserInfoRepository;
import com.lixinxinlove.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
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
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 用户后台管理
 */
@Controller
@RequestMapping("/user/manage")
@Slf4j
public class UserManageController {


    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private StringRedisTemplate redisTemplate;


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

        //1.登陆 查用户
        UserInfo userInfo = userInfoService.findOneByPhoneAndPassword(user.getPhone(), user.getPassword());
        //2.写入缓存
        if (userInfo != null) {
            String mPhone = user.getPhone();
            String token = UUID.randomUUID().toString();
            Integer expire = RedisConstant.EXPIRE;
            redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX, mPhone), token, expire, TimeUnit.SECONDS);
        }
        //3.写入cookies

        //4.重定向
        return new ModelAndView("redirect:" + "/user/manage/list");
    }


    @GetMapping("/edit")
    public ModelAndView edit(@RequestParam(value = "userId", required = false) String userId, Map<String, Object> map) {
        UserInfo userInfo = userInfoService.findOne(userId);
        map.put("userInfo", userInfo);
        return new ModelAndView("user/edit", map);
    }


    @PostMapping("/save")
    public ModelAndView postEdit(@Valid UserInfo user, Map<String, Object> map) {


        user.setUpdateTime(new Date());
        user.setCreateTime(new Date());
        userInfoService.save(user);

        return new ModelAndView("redirect:" + "/user/manage/list");

    }

}
