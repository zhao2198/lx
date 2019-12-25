package com.lx.core.common.utils;

import lombok.extern.slf4j.Slf4j;

import java.text.NumberFormat;
import java.util.Random;

@Slf4j
public class RandomUtil {
    public static String orderCode() {
        int r1 = (int) (Math.random() * (10));// 产生2个0-9的随机数
        int r2 = (int) (Math.random() * (10));
        long now = System.currentTimeMillis();// 一个13位的时间戳
        String paymentID = String.valueOf(r1) + String.valueOf(r2) + String.valueOf(now);// 订单ID
        return paymentID;
    }

    /**
     * 生成一个指定长度的随机数,
     * Lian weimao CreateTime:2018年10月10日 上午11:31:04
     *
     * @param length 范围 [1-15]
     * @return
     */
    public static String randomNum(int length) {
        if (length < 1 || length > 15) {
            throw new IllegalArgumentException();
        }
        Random random = new Random();
        double num = (random.nextDouble() + 1) * Math.pow(10, length);
        NumberFormat nFormat = NumberFormat.getInstance();
        nFormat.setGroupingUsed(false);
        String numStr = nFormat.format(num).substring(1, length + 1);
        return numStr;
    }
}
