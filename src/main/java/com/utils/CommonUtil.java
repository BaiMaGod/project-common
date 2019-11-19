package com.utils;

import java.util.UUID;

/**
 * 常用工具类
 */
public class CommonUtil {

    /**
     * 生成UUID,32位
     * @return id
     */
    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        String id = uuid.toString();
        //去掉随机ID的短横线
        id = id.replace("-", "");

        return id;
    }

    /**
     * 生成短数字id,8位左右
     * @return id
     */
    public static String getShortId() {
        //取UUID的哈希码，换成纯数字
        int num = getUUID().hashCode();
        //去绝对值
        num = num < 0 ? -num : num;
        return String.valueOf(num);
    }

    /**
     * 生成长数字ID,纯数字，递增，23位左右
     * @return id
     */
    public static String getLongId() {
//		在生成的 短id 前加入时间戳，形成递增
        return String.valueOf(System.currentTimeMillis()) + getShortId();
    }
}
