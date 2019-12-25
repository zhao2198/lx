package com.lx.common.util;

import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Create Date: 2018年4月28日 上午9:03:20
 *
 * @version: V3.0.1
 * @author: zhao wei
 */
@Slf4j
public final class StringUtils {

    /**
     * 默认构造函数
     */
    private StringUtils() {

    }

    /**
     * 判断字符串是否为空
     *
     * @param str 待判断的字符串
     * @return 空为true
     */
    public static boolean isBlank(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static boolean isBlank(Object str) {
        if (str == null)
            return true;
        return isBlank(str.toString());
    }

    /**
     * 作用:判断文本是否为空文本 注意:若文本为null也会返回true
     *
     * @param str 作者:廉伟茂 2018年2月5日 下午3:45:07
     */
    public static boolean isEmpty(String str) {
        return isBlank(str);
    }

    /**
     * 作用:判断不定长参数中是否包含空值 注意:
     *
     * @param strs
     * @return 作者:廉伟茂 2018年4月16日 下午8:10:30
     */
    public static final boolean hasEmpty(String... strs) {
        if (strs == null || strs.length == 0)
            return false;
        for (String str : strs) {
            if (isEmpty(str))
                return true;
        }
        return false;
    }

    /**
     * 作用:判断不定长参数中是否都是空的 注意:
     *
     * @param strs
     * @return 作者:廉伟茂 2018年4月16日 下午8:10:30
     */
    public static final boolean allIsEmpty(String... strs) {
        if (strs == null || strs.length == 0)
            return true;
        for (String str : strs) {
            if (!isEmpty(str))
                return false;
        }
        return true;
    }

    /**
     * 作用:判断不定长参数中的第一个参数是否和后面的任一个参数相同
     *
     * @param strs
     * @return 存在相同返回true，参数只有一个返回true，否则返回false 作者:廉伟茂 2018年4月18日 上午8:42:16
     */
    public static final boolean equalsAnyOne(String... strs) {
        if (strs == null || strs.length <= 1)
            return true;
        String arg = strs[0];
        for (int i = 1; i < strs.length; i++) {
            try {
                if (strs[i].equals(arg))
                    return true;
            } catch (Exception e) {
                continue;
            }
        }
        return false;
    }

    /**
     * 判断字符串是否不为空
     *
     * @param str 待判断的字符串
     * @return 空为fale
     */
    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    /**
     * 字符串转化为二进制数组
     *
     * @param str 待转化字符串
     * @return 二进制数组
     */
    public static byte[] getBytes(String str) {
        byte[] ret = null;
        if (isNotBlank(str)) {

            try {
                ret = str.getBytes("UTF-8");
            } catch (UnsupportedEncodingException e) {
                log.error("failed to getBytes", e);
            }
        }
        return ret;
    }

    /**
     * 将二进制数组转化成字符串
     *
     * @param data 二进制数组
     * @return 字符串
     */
    public static String getString(byte[] data) {
        try {
            return new String(data, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    /**
     * url encode
     *
     * @param str orginal str
     * @return encoded value
     */
    public static String urlEncode(String str) {
        if (isBlank(str)) {
            return str;
        }
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return str;
        }
    }

    /**
     * url decode
     *
     * @param str orginal str
     * @return decoded value
     */
    public static String urlDecode(String str) {
        if (isBlank(str)) {
            return str;
        }
        try {
            return URLDecoder.decode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return str;
        }
    }

    /**
     * valueOf
     *
     * @param obj parameter
     * @return toString
     */
    public static String valueOf(Object obj) {
        if (obj == null) {
            return null;
        } else {
            return String.valueOf(obj);
        }
    }

    /**
     * 将字符串解析成Integer型
     *
     * @param str 待解析字符串
     * @return long型值
     */
    public static Integer parseInt(String str) {
        if (isBlank(str)) {
            return null;
        }
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /**
     * 将字符串解析成long型
     *
     * @param str 待解析字符串
     * @return long型值
     */
    public static Long parseLong(String str) {
        if (isBlank(str)) {
            return null;
        }
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static final char UNDERLINE = '_';

    /**
     * 驼峰转小写下划线 Lian weimao CreateTime:2018年5月29日 下午5:01:02
     *
     * @param param
     * @return
     */
    public static String camelToUnderline(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append(UNDERLINE);
                sb.append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 下划线小写转驼峰 Lian weimao CreateTime:2018年5月29日 下午5:01:32
     *
     * @param param
     * @return
     */
    public static String underlineToCamel(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (c == UNDERLINE) {
                if (++i < len) {
                    sb.append(Character.toUpperCase(param.charAt(i)));
                }
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 使用*替换文本中的部分内容，用来保护信息 Lian weimao CreateTime:2018年6月19日 上午10:50:23
     *
     * @param str             需要处理的字符串
     * @param startKeepLength 前面保留的长度
     * @param endKeepLength   后面保留的长度
     * @return
     */
    public static String coverUpWithAsterisk(String str, int startKeepLength, int endKeepLength) {
        if (str == null)
            return null;
        int length = str.length();
        if (length == 0)
            return str;
        if (startKeepLength > length || endKeepLength > length)
            throw new IllegalArgumentException("前保留位数或后保留位数不得超过文本长度");
        StringBuilder sBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            if ((i + 1) <= startKeepLength || (i) >= length - endKeepLength) {
                sBuilder.append(str.charAt(i));
            } else {
                sBuilder.append("*");
            }
        }
        return sBuilder.toString();
    }

    public static void main(String[] args) {
        String lsString = coverUpWithAsterisk("13835952078", 3, 12);
        System.out.println(lsString);
    }

    /**
     * 传入字符串和默认返回值，判断传入字符串是否为null,如果为null则返回默认值
     *
     * @param value
     * @param defaultValue
     * @return
     */
    public static String getStrValue(String value, String defaultValue) {
        if (null == value)
            return defaultValue;
        return value;
    }

    /**
     * 传入字符串值，如果为null，则返回0
     *
     * @param value
     * @return
     */
    public static String getStrValue(String value) {
        return getStrValue(value, "0");
    }

    /**
     * 比较两个string字符串，空白字符和null比较同样会返回true
     * </br> null : "" true
     * </br> 1:1 true
     * </br> null : null true
     * </br> 1 : null false
     * </br>
     * Lian weimao CreateTime:2018年8月20日 下午5:10:06
     *
     * @param str1
     * @param str2
     * @return
     */
    public static boolean equals(String str1, String str2) {
        if (StringUtils.isBlank(str1)) str1 = "";
        if (StringUtils.isBlank(str2)) str2 = "";
        return str1.equals(str2);
    }


}
