package com.lixinxinlove.controller;


import com.lixinxinlove.constant.RedisConstant;
import com.lixinxinlove.entity.NoteUser;
import com.lixinxinlove.enums.UserEnum;
import com.lixinxinlove.service.NoteUserService;
import com.lixinxinlove.utils.ResultVOUtil;
import com.lixinxinlove.utils.UserIdUtil;
import com.lixinxinlove.vo.ResultVO;
import com.lixinxinlove.vo.NoteUserOV;
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
public class NoteUserController {

    @Autowired
    private NoteUserService userInfoService;

    @Autowired
    private StringRedisTemplate redisTemplate;


    @GetMapping("/list")
    public ResultVO<List<NoteUserOV>> list() {

        List<NoteUser> userInfoList = userInfoService.findAll();

        List<NoteUserOV> userInfoOVList = new ArrayList<>();

        for (NoteUser userInfo : userInfoList) {
            NoteUserOV userInfoOV = new NoteUserOV();
            BeanUtils.copyProperties(userInfo, userInfoOV);
            userInfoOVList.add(userInfoOV);
        }
        return ResultVOUtil.success(userInfoOVList);
    }


    @PostMapping("/login")
    public ResultVO<List<NoteUserOV>> login(@RequestParam("phone") String phone,
                                            @RequestParam("password") String password) {


        NoteUserOV userInfoOV = new NoteUserOV();
        NoteUser userInfo = userInfoService.findOneByPhoneAndPassword(phone, password);


        if (userInfo == null) {
            return ResultVOUtil.error(UserEnum.LOGIN_FAIL.getCode(), UserEnum.LOGIN_FAIL.getMessage());
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
    public ResultVO<List<NoteUserOV>> register(@RequestParam("phone") String phone, @RequestParam("password") String password) {
        //1.查询手机号是否注册过
        NoteUser userInfo = userInfoService.findOneByPhone(phone);
        if (userInfo != null) {
            return ResultVOUtil.error(UserEnum.USER_EXIST.getCode(), UserEnum.USER_EXIST.getMessage());
        } else {
            NoteUserOV userInfoOV = new NoteUserOV();

            NoteUser user = new NoteUser();
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
