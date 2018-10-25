package com.lixinxinlove.utils;

import java.util.Random;

public class UserIdUtil {

    /**
     * 生成唯一的主键
     * 格式: 时间+随机数
     *
     * @return
     */
    public static synchronized String genUniqueUserId() {
        Random random = new Random();
        Integer number = random.nextInt(900) + 100;

        return System.currentTimeMillis() / 1000 + String.valueOf(number);
    }
}
