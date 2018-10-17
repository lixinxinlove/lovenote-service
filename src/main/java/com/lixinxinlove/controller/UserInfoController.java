package com.lixinxinlove.controller;


import com.lixinxinlove.constant.RedisConstant;
import com.lixinxinlove.entity.UserInfo;
import com.lixinxinlove.service.UserInfoService;
import com.lixinxinlove.utils.ResultVOUtil;
import com.lixinxinlove.vo.ResultVO;
import com.lixinxinlove.vo.UserInfoOV;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private StringRedisTemplate redisTemplate;


    @GetMapping("/list")
    public ResultVO<List<UserInfoOV>> list() {

        List<UserInfo> userInfoList = userInfoService.findAll();

        List<UserInfoOV> userInfoOVList = new ArrayList<>();

        for (UserInfo userInfo : userInfoList) {
            UserInfoOV userInfoOV = new UserInfoOV();
            BeanUtils.copyProperties(userInfo, userInfoOV);
            userInfoOVList.add(userInfoOV);
        }
        return ResultVOUtil.success(userInfoOVList);
    }


    @PostMapping("/login")
    public ResultVO<List<UserInfoOV>> login(@RequestParam("phone") String phone,
                                            @RequestParam("password") String password) {

        log.error("phone=={}", phone);
        log.error("password=={}", password);


        UserInfoOV userInfoOV = new UserInfoOV();
        UserInfo userInfo = userInfoService.findOneByPhoneAndPassword(phone, password);

        String mPhone = phone;
        String token = UUID.randomUUID().toString();
        Integer expire = RedisConstant.EXPIRE;
        redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX, "lixinxin"), mPhone, expire, TimeUnit.SECONDS);

        if (userInfo == null) {
            return ResultVOUtil.error(11, "密码或手机号错误");
        } else {
            BeanUtils.copyProperties(userInfo, userInfoOV);
            return ResultVOUtil.success(userInfoOV);
        }
    }
}
