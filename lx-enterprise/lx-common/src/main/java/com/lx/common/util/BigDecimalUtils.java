package com.lx.common.util;

import java.math.BigDecimal;

/**
 * BigDecimal工具类
 * Create Date:	2018年7月6日 下午3:03:47
 *
 * @version: V3.0.1
 * @author: wang xiao chao
 */
public class BigDecimalUtils {
    /**
     * 传入String值如果为null返回默认值的BigDecimal类型
     *
     * @param value
     * @param defaultValue
     * @return
     */
    public static BigDecimal getBigValue(String value, String defaultValue) {
        if (null == value) return new BigDecimal(defaultValue);
        return new BigDecimal(value);
    }

    /**
     * 传入bigdecimal和String的默认值
     *
     * @param value
     * @param defaultValue
     * @return
     */
    public static BigDecimal getBigValue(BigDecimal value, String defaultValue) {
        if (null == value) return new BigDecimal(defaultValue);
        return value;
    }

    /**
     * 传入String值，如果为null，返回BigDecimal类型的0
     *
     * @param value
     * @return
     */
    public static BigDecimal getBigValue(String value) {
        return getBigValue(value, "0");
    }

    public static BigDecimal getBigValue() {
        return getBigValue("0");
    }

    /**
     * 传入bigdecimal，如果为null返回0
     *
     * @param value
     * @return
     */
    public static BigDecimal getBigValue(BigDecimal value) {
        return getBigValue(value, "0");
    }

    /**
     * 处理bigdecimal小数点 ，建议不要参与运算，单纯处理某个BigDecimal;
     *
     * @param num   保留的位数
     * @param value 传入的bigdecimal
     * @return
     */
    public static BigDecimal reserveDecimalPoint(int num, BigDecimal value) {
        value = value.setScale(num, BigDecimal.ROUND_HALF_UP);
        return value;
    }

    /**
     * BigDecimal的除法运算，如果除数为0，则直接返回0
     * 商=被除数.devide(除数，保留小数位数，精确方法)
     * 此方法可排除除法运算无尽循环的时候抛Non-terminating decimal expansion；no exact representable decimal result.异常
     *
     * @param dividend
     * @param num
     * @param divisor
     * @return
     */
    public static BigDecimal divide(BigDecimal dividend, int num, BigDecimal divisor) {
        BigDecimal result = null;
        if (divisor.compareTo(BigDecimal.ZERO) > 0) {
            result = dividend.divide(divisor, num, BigDecimal.ROUND_HALF_UP);
            return result;
        } else {
            return BigDecimal.ZERO;
        }
    }

    /**
     * 将int转为big 不保留小数
     *
     * @param num
     * @return
     */
    public static BigDecimal converIntToBig(int num) {
        return reserveDecimalPoint(0, new BigDecimal(num));
    }

}
