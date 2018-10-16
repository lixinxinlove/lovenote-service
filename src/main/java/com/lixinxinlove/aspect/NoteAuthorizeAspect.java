package com.lixinxinlove.aspect;


import com.lixinxinlove.constant.CookieConstant;
import com.lixinxinlove.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@Slf4j
public class NoteAuthorizeAspect {


    @Autowired
    private StringRedisTemplate redisTemplate;

    //切点
    @Pointcut("execution(public * com.lixinxinlove.controller.User*.*(..))" +
            "&& !execution(public * com.lixinxinlove.controller.UserInfoController.*(..))")
    public void verify() {
    }

    @Before("verify()")
    public void doVerify() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        //url
        log.error("url={}", request.getRequestURI());

        //method
        log.error("method={}",request.getMethod());

        //ip
        log.error("ip={}",request.getRemoteAddr());

        //查询cookie
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookie == null) {
            log.warn("【登录校验】Cookie中查不到token");
            // throw new SellerAuthorizeException();
        }

        //去redis里查询
//        String tokenValue = redisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
//        if (StringUtils.isEmpty(tokenValue)) {
//            log.warn("【登录校验】Redis中查不到token");
//            // throw new SellerAuthorizeException();
//        }
    }


    @After("verify()")
    public void log() {
        log.error("log()");
    }

    @AfterReturning("verify()")
    public void doAfterReturning(){

    }

}
