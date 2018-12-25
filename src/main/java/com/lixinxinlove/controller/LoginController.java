package com.lixinxinlove.controller;


import com.lixinxinlove.constant.RedisConstant;
import com.lixinxinlove.entity.NoteUser;
import com.lixinxinlove.enums.UserEnum;
import com.lixinxinlove.service.NoteUserService;
import com.lixinxinlove.utils.ResultVOUtil;
import com.lixinxinlove.vo.NoteUserOV;
import com.lixinxinlove.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/open")
public class LoginController {


    @Autowired
    private NoteUserService userInfoService;

    @Autowired
    private StringRedisTemplate redisTemplate;


    //发送短信
    @GetMapping("/sms")
    public ResultVO<String> sendSms(){
        return  ResultVOUtil.success("");
    }

    @PostMapping("/login1")
    public ResultVO<NoteUserOV> login(@RequestParam("phone") String phone, @RequestParam("password") String password) {

        NoteUserOV userInfoOV = new NoteUserOV();
        NoteUser userInfo = userInfoService.findOneByPhoneAndPassword(phone, password);

        if (userInfo == null) {
            return ResultVOUtil.error(UserEnum.LOGIN_FAIL.getCode(), UserEnum.LOGIN_FAIL.getMessage());
        } else   {
            String mPhone = phone;
            String token = UUID.randomUUID().toString();
            Integer expire = RedisConstant.EXPIRE;
            BeanUtils.copyProperties(userInfo, userInfoOV);
            userInfoOV.setToken(token);
            redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX, mPhone), token, expire, TimeUnit.SECONDS);
            return ResultVOUtil.success(userInfoOV);
        }
    }


    @PostMapping("/login")
    public ResultVO<NoteUserOV> loginByCode(@RequestParam("phone") String phone, @RequestParam("code") String code) {

        //验证码为空
        if (code.isEmpty()){
            return ResultVOUtil.error(UserEnum.LOGIN_FAIL.getCode(), UserEnum.LOGIN_FAIL.getMessage());
        }

        //验证码错误
        if (code.equals("123456")){
            return ResultVOUtil.error(UserEnum.LOGIN_FAIL.getCode(), UserEnum.LOGIN_FAIL.getMessage());
        }

        NoteUserOV userInfoOV = new NoteUserOV();
        NoteUser userInfo = userInfoService.findOneByPhone(phone);

        if (userInfo == null) {
            return ResultVOUtil.error(UserEnum.LOGIN_FAIL.getCode(), UserEnum.LOGIN_FAIL.getMessage());
        } else   {
            String mPhone = phone;
            String token = UUID.randomUUID().toString();
            Integer expire = RedisConstant.EXPIRE;
            BeanUtils.copyProperties(userInfo, userInfoOV);
            userInfoOV.setToken(token);
            redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX, mPhone), token, expire, TimeUnit.SECONDS);
            return ResultVOUtil.success(userInfoOV);
        }
    }

}
