package com.lixinxinlove.controller;


import com.lixinxinlove.entity.UserInfo;
import com.lixinxinlove.service.UserInfoService;
import com.lixinxinlove.utils.ResultVOUtil;
import com.lixinxinlove.vo.ResultVO;
import com.lixinxinlove.vo.UserInfoOV;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

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

        if (userInfo==null){
            return ResultVOUtil.error(11,"密码或手机号错误");
        }else {
            BeanUtils.copyProperties(userInfo, userInfoOV);
            return ResultVOUtil.success(userInfoOV);
        }
    }
}
