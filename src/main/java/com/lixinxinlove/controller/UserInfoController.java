package com.lixinxinlove.controller;


import com.lixinxinlove.constant.RedisConstant;
import com.lixinxinlove.entity.UserInfo;
import com.lixinxinlove.enums.UserEnum;
import com.lixinxinlove.service.UserInfoService;
import com.lixinxinlove.utils.ResultVOUtil;
import com.lixinxinlove.utils.UserIdUtil;
import com.lixinxinlove.vo.ResultVO;
import com.lixinxinlove.vo.UserInfoOV;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
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


        UserInfoOV userInfoOV = new UserInfoOV();
        UserInfo userInfo = userInfoService.findOneByPhoneAndPassword(phone, password);


        if (userInfo == null) {
            return ResultVOUtil.error(11, "密码或手机号错误");
        } else {
            String mPhone = phone;
            String token = UUID.randomUUID().toString();
            Integer expire = RedisConstant.EXPIRE;

            BeanUtils.copyProperties(userInfo, userInfoOV);
            userInfoOV.setToken(token);
            redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX, mPhone), token, expire, TimeUnit.SECONDS);

            return ResultVOUtil.success(userInfoOV);
        }
    }


    /**
     * api接口注册
     *
     * @param phone
     * @param password
     * @return
     */
    @PostMapping("/register")
    public ResultVO<List<UserInfoOV>> register(@RequestParam("phone") String phone, @RequestParam("password") String password) {
        //1.查询手机号是否注册过
        UserInfo userInfo = userInfoService.findOneByPhone(phone);
        if (userInfo != null) {
            return ResultVOUtil.error(UserEnum.USER_EXIST.getCode(), UserEnum.USER_EXIST.getMessage());
        } else {
            UserInfoOV userInfoOV = new UserInfoOV();

            UserInfo user = new UserInfo();
            user.setUserId(UserIdUtil.genUniqueUserId());
            user.setUserName(phone);
            user.setPhone(phone);
            user.setPassword(password);
            user.setAddress("");
            user.setEmail("");
            user.setUserIcon("");
            user.setSex(1);
            user.setCreateTime(new Date());
            user.setUpdateTime(new Date());


            userInfo = userInfoService.save(user);

            if (userInfo != null) {
                BeanUtils.copyProperties(userInfo, userInfoOV);
                return ResultVOUtil.success(userInfoOV);
            } else {
                return ResultVOUtil.error(UserEnum.REGISTER_FAIL.getCode(), UserEnum.REGISTER_FAIL.getMessage());
            }
        }
    }
}
