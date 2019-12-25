
package com.lx.common.util;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ValidateUtil {

    /**
     * @param args
     */
    public static void main(String[] args) {

        System.out.println(isEmail("tomaer@gmail.com"));
        System.out.println(isChinese("你好中国"));
        System.out.println(isArchives("c:\\pic\\pic.tar.gz"));
        System.out.println(isIDCard("610426198703190054"));
    }

    /**
     * 是否为腾讯QQ号码
     *
     * @param str
     * @return
     */
    public static boolean isQQ(String str) {

        checkString(str);

        return str.matches(RegexExpression.REGEX_QQ);
    }

    /**
     * 数字验证
     *
     * @param val
     * @return 提取的数字。
     */
    @SuppressWarnings("unused")
    public static boolean isNum(String val) {

        return val == null || "".equals(val) ? false : val.matches("^[0-9]*$");
    }

    /**
     * 是否为整数
     *
     * @param num
     * @return
     */
    public static boolean isInteger(int num) {

        return (num + "").matches(RegexExpression.REGEX_INTEGER);
    }

    /**
     * 是否为正整数
     *
     * @param num
     * @return
     */
    public static boolean isPositiveInteger(String num) {

        return num.matches(RegexExpression.REGEX_POSITIVE_INTEGER);
    }

    /**
     * 是否为负整数
     *
     * @param num
     * @return
     */
    public static boolean isNegativeInteger(int num) {

        return (num + "").matches(RegexExpression.REGEX_NEGATIVE_INTEGER);
    }

    /**
     * 是否为电子邮箱
     *
     * @param str
     * @return
     */
    public static boolean isEmail(String str) {

        checkString(str);
        return str.matches(RegexExpression.REGEX_EMAIL);
    }

    /**
     * 是否为中文汉字
     *
     * @param str
     * @return
     */
    public static boolean isChinese(String str) {

        checkString(str);
        return str.matches(RegexExpression.REGEX_CHINESE);
    }

    /**
     * 是否为中国的邮政编码
     *
     * @param str
     * @return
     */
    public static boolean isPostcode(String str) {

        checkString(str);
        return str.matches(RegexExpression.REGEX_POSTCODE);
    }

    /**
     * 是否为图片格式
     *
     * @param str
     * @return
     */
    public static boolean isPicture(String str) {

        checkString(str);
        return str.matches(RegexExpression.REGEX_PICTURE);
    }

    /**
     * 是否为归档文件格式
     *
     * @param str
     * @return
     */
    public static boolean isArchives(String str) {

        checkString(str);
        return str.matches(RegexExpression.REGEX_ARCHIVES);
    }

    /**
     * memo :是否为电话
     *
     * @param str
     * @return
     */
    public static boolean isTel(String str) {

        checkString(str);
        return str.matches(RegexExpression.REGEX_TEL);
    }

    /**
     * 是否是有效的联系方式
     *
     * @param str
     * @return
     */
    public static boolean isLinkTel(String str) {

        boolean flag = isTel(str);
        boolean fg = isPhone(str);
        if (flag || fg) {
            return true;
        }
        return false;
    }

    /**
     * memo :是否为手机
     *
     * @param str
     * @return
     */
    public static boolean isPhone(String str) {

        checkString(str);
        return str.matches(RegexExpression.REGEX_PHONE);
    }

    /**
     * 是否为用户名
     *
     * @param str
     * @return
     */
    public static boolean isUsername(String str) {

        checkString(str);
        return str.matches(RegexExpression.REGEX_USERNAME);
    }

    /**
     * 是否为英文字母
     *
     * @param str
     * @return
     */
    public static boolean isLetter(String str) {

        checkString(str);
        return str.matches(RegexExpression.REGEX_LETTER);
    }

    /**
     * 是否为英文大写字母
     *
     * @param str
     * @return
     */
    public static boolean isUpperLetter(String str) {

        checkString(str);
        return str.matches(RegexExpression.REGEX_UPPER_LETTER);
    }

    /**
     * 是否为英文小写字母
     *
     * @param str
     * @return
     */
    public static boolean isLowerLetter(String str) {

        checkString(str);
        return str.matches(RegexExpression.REGEX_LOWER_LETTER);
    }

    /**
     * 是否为身份证号码
     *
     * @param str
     * @return
     */
    public static boolean isIDCard(String str) {

        checkString(str);
        return IDCardValidate.validateCard(str);
    }

    /**
     * 检查字符串是否为空
     *
     * @param str
     */
    static void checkString(String str) {

        if (StringUtils.isEmpty(str)) {
            throw new IllegalArgumentException("Illegal argument exception");
        }
    }

    /**
     * 匹配Luhn算法：可用于检测银行卡卡号
     *
     * @param cardNo
     * @return
     */
    public static boolean matchLuhn(String cardNo) {
        int[] cardNoArr = new int[cardNo.length()];
        for (int i = 0; i < cardNo.length(); i++) {
            cardNoArr[i] = Integer.valueOf(String.valueOf(cardNo.charAt(i)));
        }
        for (int i = cardNoArr.length - 2; i >= 0; i -= 2) {
            cardNoArr[i] <<= 1;
            cardNoArr[i] = cardNoArr[i] / 10 + cardNoArr[i] % 10;
        }
        int sum = 0;
        for (int i = 0; i < cardNoArr.length; i++) {
            sum += cardNoArr[i];
        }
        return sum % 10 == 0;
    }

    protected static final class RegexExpression {
        public final static String REGEX_QQ = "^[1-9]*[1-9][0-9]*$";

        public final static String REGEX_INTEGER = "^-?[1-9]\\d*$";

        public final static String REGEX_POSITIVE_INTEGER = "^[1-9]\\d*$";

        public final static String REGEX_NEGATIVE_INTEGER = "^-[1-9]\\d*$";

        public final static String REGEX_EMAIL = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";

        public final static String REGEX_CHINESE = "^[\\u4E00-\\u9FA5\\uF900-\\uFA2D]+$";

        public final static String REGEX_POSTCODE = "^\\d{6}$";

        public final static String REGEX_PICTURE = "(.*)\\.(jpg|bmp|gif|ico|pcx|jpeg|tif|png|raw|tga)$";

        public final static String REGEX_ARCHIVES = "(.*)\\.(rar|zip|7zip|tgz|tar.gz|7z)$";

        public final static String REGEX_TEL = "^(([0\\+]\\d{2,3}-)?(0\\d{2,3})-)?(\\d{7,8})(-(\\d{3,}))?$";

        public final static String REGEX_PHONE = "^((13|15|18)[0-9]{9})|(177[0-9]{8})$";

        public final static String REGEX_USERNAME = "^\\w+$";

        public final static String REGEX_LETTER = "^[A-Za-z]+$";

        public final static String REGEX_UPPER_LETTER = "^[A-Z]+$";

        public final static String REGEX_LOWER_LETTER = "^[a-z]+$";
    }

    protected static class IDCardValidate {
        /**
         * 中国省、直辖市名称代码对应HashMap
         */
        public static Map<String, String> cityMap = new HashMap<String, String>();

        /**
         * 中国公民身份证号码最小长度。
         */
        public static final int CHINA_ID_MIN_LENGTH = 15;

        /**
         * 中国公民身份证号码最大长度。
         */
        public static final int CHINA_ID_MAX_LENGTH = 18;

        /**
         * 省、直辖市代码表
         */
        public static final String cityCode[] = {"11", "12", "13", "14", "15", "21", "22", "23", "31", "32", "33",
                "34", "35", "36", "37", "41", "42", "43", "44", "45", "46", "50", "51", "52", "53", "54", "61", "62",
                "63", "64", "65", "71", "81", "82", "91"};

        /**
         * 每位加权因子
         */
        public static final int power[] = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};

        /**
         * 第18位校检码
         */
        public static final String verifyCode[] = {"1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2"};

        /**
         * 台湾身份首字母对应数字
         */
        public static Map<String, Integer> twFirstCode = new HashMap<String, Integer>();

        /**
         * 香港身份首字母对应数字
         */
        public static Map<String, Integer> hkFirstCode = new HashMap<String, Integer>();

        public static final int MIN = 1930;

        static {
            cityMap.put("11", "北京");
            cityMap.put("12", "天津");
            cityMap.put("13", "河北");
            cityMap.put("14", "山西");
            cityMap.put("15", "内蒙古");
            cityMap.put("21", "辽宁");
            cityMap.put("22", "吉林");
            cityMap.put("23", "黑龙江");
            cityMap.put("31", "上海");
            cityMap.put("32", "江苏");
            cityMap.put("33", "浙江");
            cityMap.put("34", "安徽");
            cityMap.put("35", "福建");
            cityMap.put("36", "江西");
            cityMap.put("37", "山东");
            cityMap.put("41", "河南");
            cityMap.put("42", "湖北");
            cityMap.put("43", "湖南");
            cityMap.put("44", "广东");
            cityMap.put("45", "广西");
            cityMap.put("46", "海南");
            cityMap.put("50", "重庆");
            cityMap.put("51", "四川");
            cityMap.put("52", "贵州");
            cityMap.put("53", "云南");
            cityMap.put("54", "西藏");
            cityMap.put("61", "陕西");
            cityMap.put("62", "甘肃");
            cityMap.put("63", "青海");
            cityMap.put("64", "宁夏");
            cityMap.put("65", "新疆");
            cityMap.put("71", "台湾");
            cityMap.put("81", "香港");
            cityMap.put("82", "澳门");
            cityMap.put("91", "国外");
            twFirstCode.put("A", 10);
            twFirstCode.put("B", 11);
            twFirstCode.put("C", 12);
            twFirstCode.put("D", 13);
            twFirstCode.put("E", 14);
            twFirstCode.put("F", 15);
            twFirstCode.put("G", 16);
            twFirstCode.put("H", 17);
            twFirstCode.put("J", 18);
            twFirstCode.put("K", 19);
            twFirstCode.put("L", 20);
            twFirstCode.put("M", 21);
            twFirstCode.put("N", 22);
            twFirstCode.put("P", 23);
            twFirstCode.put("Q", 24);
            twFirstCode.put("R", 25);
            twFirstCode.put("S", 26);
            twFirstCode.put("T", 27);
            twFirstCode.put("U", 28);
            twFirstCode.put("V", 29);
            twFirstCode.put("X", 30);
            twFirstCode.put("Y", 31);
            twFirstCode.put("W", 32);
            twFirstCode.put("Z", 33);
            twFirstCode.put("I", 34);
            twFirstCode.put("O", 35);
            hkFirstCode.put("A", 1);
            hkFirstCode.put("B", 2);
            hkFirstCode.put("C", 3);
            hkFirstCode.put("R", 18);
            hkFirstCode.put("U", 21);
            hkFirstCode.put("Z", 26);
            hkFirstCode.put("X", 24);
            hkFirstCode.put("W", 23);
            hkFirstCode.put("O", 15);
            hkFirstCode.put("N", 14);
        }

        /**
         * 转换15位的身份证到18位
         *
         * @param idCard
         * @return
         */
        private static String conver15CardTo18(String idCard) {

            String idCard18 = "";
            if (idCard.length() != CHINA_ID_MIN_LENGTH) {
                return null;
            }
            if (isNum(idCard)) {
                // 获取出生年月日
                String birthday = idCard.substring(6, 12);
                Date birthDate = null;
                try {
                    birthDate = new SimpleDateFormat("yyMMdd").parse(birthday);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Calendar cal = Calendar.getInstance();
                if (birthDate != null) {
                    cal.setTime(birthDate);
                }
                // 获取出生年(完全表现形式,如：2010)
                String sYear = String.valueOf(cal.get(Calendar.YEAR));
                idCard18 = idCard.substring(0, 6) + sYear + idCard.substring(8);
                // 转换字符数组
                char[] cArr = idCard18.toCharArray();
                if (cArr != null) {
                    int[] iCard = converCharToInt(cArr);
                    int iSum17 = getPowerSum(iCard);
                    // 获取校验位
                    String sVal = getCheckCode18(iSum17);
                    if (sVal.length() > 0) {
                        idCard18 += sVal;
                    } else {
                        return null;
                    }
                }
            } else {
                return null;
            }
            return idCard18;
        }

        /**
         * 验证身份证是否合法
         */
        public static boolean validateCard(String idCard) {

            String card = idCard.trim();
            if (validateIdCard18(card)) {
                return true;
            }
            if (validateIdCard15(card)) {
                return true;
            }
            String[] cardval = validateIdCard10(card);
            if (cardval != null) {
                if (cardval[2].equals("true")) {
                    return true;
                }
            }
            return false;
        }

        /**
         * 206 验证18位身份编码是否合法 207 208
         *
         * @param idCard 身份编码 209
         * @return 是否合法 210
         */
        private static boolean validateIdCard18(String idCard) {

            boolean bTrue = false;
            if (idCard.length() == CHINA_ID_MAX_LENGTH) {
                // 前17位
                String code17 = idCard.substring(0, 17);
                // 第18位
                String code18 = idCard.substring(17, CHINA_ID_MAX_LENGTH);
                if (isNum(code17)) {
                    char[] cArr = code17.toCharArray();
                    if (cArr != null) {
                        int[] iCard = converCharToInt(cArr);
                        int iSum17 = getPowerSum(iCard);
                        // 获取校验位
                        String val = getCheckCode18(iSum17);
                        if (val.length() > 0) {
                            if (val.equalsIgnoreCase(code18.toLowerCase())) {
                                bTrue = true;
                            }
                        }
                    }
                }
            }
            return bTrue;
        }

        /**
         * 验证15位身份编码是否合法
         *
         * @param idCard 身份编码
         * @return 是否合法
         */
        private static boolean validateIdCard15(String idCard) {

            if (idCard.length() != CHINA_ID_MIN_LENGTH) {
                return false;
            }
            if (isNum(idCard)) {
                String proCode = idCard.substring(0, 2);
                if (cityMap.get(proCode) == null) {
                    return false;
                }
                String birthCode = idCard.substring(6, 12);
                Date birthDate = null;
                try {
                    birthDate = new SimpleDateFormat("yy").parse(birthCode.substring(0, 2));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Calendar cal = Calendar.getInstance();
                if (birthDate != null) {
                    cal.setTime(birthDate);
                }
                if (!valiDate(cal.get(Calendar.YEAR), Integer.valueOf(birthCode.substring(2, 4)),
                        Integer.valueOf(birthCode.substring(4, 6)))) {
                    return false;
                }
            } else {
                return false;
            }
            return true;
        }

        /**
         * 验证10位身份编码是否合法
         *
         * @param idCard 身份编码
         * @return 身份证信息数组
         * <p>
         * [0] - 台湾、澳门、香港 [1] - 性别(男M,女F,未知N) [2] -
         * 是否合法(合法true,不合法false) 若不是身份证件号码则返回null
         * </p>
         */
        private static String[] validateIdCard10(String idCard) {

            String[] info = new String[3];
            String card = idCard.replaceAll("[\\(|\\)]", "");
            if (card.length() != 8 && card.length() != 9 && idCard.length() != 10) {
                return null;
            }
            if (idCard.matches("^[a-zA-Z][0-9]{9}$")) { // 台湾
                info[0] = "台湾";
                String char2 = idCard.substring(1, 2);
                if (char2.equals("1")) {
                    info[1] = "M";
                } else if (char2.equals("2")) {
                    info[1] = "F";
                } else {
                    info[1] = "N";
                    info[2] = "false";
                    return info;
                }
                info[2] = validateTWCard(idCard) ? "true" : "false";
            } else if (idCard.matches("^[1|5|7][0-9]{6}\\(?[0-9A-Z]\\)?$")) { // 澳门
                info[0] = "澳门";
                info[1] = "N";
            } else if (idCard.matches("^[A-Z]{1,2}[0-9]{6}\\(?[0-9A]\\)?$")) { // 香港
                info[0] = "香港";
                info[1] = "N";
                info[2] = validateHKCard(idCard) ? "true" : "false";
            } else {
                return null;
            }
            return info;
        }

        /**
         * 验证台湾身份证号码
         *
         * @param idCard 身份证号码
         * @return 验证码是否符合
         */
        private static boolean validateTWCard(String idCard) {

            String start = idCard.substring(0, 1);
            String mid = idCard.substring(1, 9);
            String end = idCard.substring(9, 10);
            Integer iStart = twFirstCode.get(start);
            Integer sum = iStart / 10 + iStart % 10 * 9;
            char[] chars = mid.toCharArray();
            Integer iflag = 8;
            for (char c : chars) {
                sum = sum + Integer.valueOf(c + "") * iflag;
                iflag--;
            }
            return (sum % 10 == 0 ? 0 : 10 - sum % 10) == Integer.valueOf(end) ? true : false;
        }

        /**
         * 验证香港身份证号码(存在Bug，部份特殊身份证无法检查)
         * <p>
         * 身份证前2位为英文字符，如果只出现一个英文字符则表示第一位是空格，对应数字58 前2位英文字符A-Z分别对应数字10-35
         * 最后一位校验码为0-9的数字加上字符"A"，"A"代表10
         * </p>
         * <p>
         * 将身份证号码全部转换为数字，分别对应乘9-1相加的总和，整除11则证件号码有效
         * </p>
         *
         * @param idCard 身份证号码
         * @return 验证码是否符合
         */
        private static boolean validateHKCard(String idCard) {

            String card = idCard.replaceAll("[\\(|\\)]", "");
            Integer sum = 0;
            if (card.length() == 9) {
                sum = (Integer.valueOf(card.substring(0, 1).toUpperCase().toCharArray()[0]) - 55) * 9
                        + (Integer.valueOf(card.substring(1, 2).toUpperCase().toCharArray()[0]) - 55) * 8;
                card = card.substring(1, 9);
            } else {
                sum = 522 + (Integer.valueOf(card.substring(0, 1).toUpperCase().toCharArray()[0]) - 55) * 8;
            }
            String mid = card.substring(1, 7);
            String end = card.substring(7, 8);
            char[] chars = mid.toCharArray();
            Integer iflag = 7;
            for (char c : chars) {
                sum = sum + Integer.valueOf(c + "") * iflag;
                iflag--;
            }
            if (end.toUpperCase().equals("A")) {
                sum = sum + 10;
            } else {
                sum = sum + Integer.valueOf(end);
            }
            return sum % 11 == 0 ? true : false;
        }

        /**
         * 将字符数组转换成数字数组
         *
         * @param ca 字符数组
         * @return 数字数组
         */
        private static int[] converCharToInt(char[] ca) {

            int len = ca.length;
            int[] iArr = new int[len];
            try {
                for (int i = 0; i < len; i++) {
                    iArr[i] = Integer.parseInt(String.valueOf(ca[i]));
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            return iArr;
        }

        /**
         * 将身份证的每位和对应位的加权因子相乘之后，再得到和值
         *
         * @param iArr
         * @return 身份证编码。
         */
        private static int getPowerSum(int[] iArr) {

            int iSum = 0;
            if (power.length == iArr.length) {
                for (int i = 0; i < iArr.length; i++) {
                    for (int j = 0; j < power.length; j++) {
                        if (i == j) {
                            iSum = iSum + iArr[i] * power[j];
                        }
                    }
                }
            }
            return iSum;
        }

        /**
         * 将power和值与11取模获得余数进行校验码判断
         *
         * @param iSum
         * @return 校验位
         */
        private static String getCheckCode18(int iSum) {

            String sCode = "";
            switch (iSum % 11) {
                case 10:
                    sCode = "2";
                    break;
                case 9:
                    sCode = "3";
                    break;
                case 8:
                    sCode = "4";
                    break;
                case 7:
                    sCode = "5";
                    break;
                case 6:
                    sCode = "6";
                    break;
                case 5:
                    sCode = "7";
                    break;
                case 4:
                    sCode = "8";
                    break;
                case 3:
                    sCode = "9";
                    break;
                case 2:
                    sCode = "x";
                    break;
                case 1:
                    sCode = "0";
                    break;
                case 0:
                    sCode = "1";
                    break;
            }
            return sCode;
        }

        /**
         * 根据身份编号获取年龄
         *
         * @param idCard 身份编号
         * @return 年龄
         */
        @SuppressWarnings("unused")
        private static int getAgeByIdCard(String idCard) {

            int iAge = 0;
            if (idCard.length() == CHINA_ID_MIN_LENGTH) {
                idCard = conver15CardTo18(idCard);
            }
            String year = idCard.substring(6, 10);
            Calendar cal = Calendar.getInstance();
            int iCurrYear = cal.get(Calendar.YEAR);
            iAge = iCurrYear - Integer.valueOf(year);
            return iAge;
        }

        /**
         * 486 根据身份编号获取生日 487 488
         *
         * @param idCard 身份编号 489
         * @return 生日(yyyyMMdd) 490
         */
        @SuppressWarnings("unused")
        private static String getBirthByIdCard(String idCard) {

            Integer len = idCard.length();
            if (len < CHINA_ID_MIN_LENGTH) {
                return null;
            } else if (len == CHINA_ID_MIN_LENGTH) {
                idCard = conver15CardTo18(idCard);
            }
            return idCard.substring(6, 14);
        }

        /**
         * 根据身份编号获取生日年
         *
         * @param idCard 身份编号
         * @return 生日(yyyy)
         */
        @SuppressWarnings("unused")
        private static Short getYearByIdCard(String idCard) {

            Integer len = idCard.length();
            if (len < CHINA_ID_MIN_LENGTH) {
                return null;
            } else if (len == CHINA_ID_MIN_LENGTH) {
                idCard = conver15CardTo18(idCard);
            }
            return Short.valueOf(idCard.substring(6, 10));
        }

        /**
         * 根据身份编号获取生日月
         *
         * @param idCard 身份编号
         * @return 生日(MM)
         */
        @SuppressWarnings("unused")
        private static Short getMonthByIdCard(String idCard) {

            Integer len = idCard.length();
            if (len < CHINA_ID_MIN_LENGTH) {
                return null;
            } else if (len == CHINA_ID_MIN_LENGTH) {
                idCard = conver15CardTo18(idCard);
            }
            return Short.valueOf(idCard.substring(10, 12));
        }

        /**
         * 根据身份编号获取生日天
         *
         * @param idCard 身份编号
         * @return 生日(dd)
         */
        @SuppressWarnings("unused")
        private static Short getDateByIdCard(String idCard) {

            Integer len = idCard.length();
            if (len < CHINA_ID_MIN_LENGTH) {
                return null;
            } else if (len == CHINA_ID_MIN_LENGTH) {
                idCard = conver15CardTo18(idCard);
            }
            return Short.valueOf(idCard.substring(12, 14));
        }

        /**
         * 根据身份编号获取性别
         *
         * @param idCard 身份编号
         * @return 性别(M - 男 ， F - 女 ， N - 未知)
         */
        @SuppressWarnings("unused")
        private static String getGenderByIdCard(String idCard) {

            String sGender = "N";
            if (idCard.length() == CHINA_ID_MIN_LENGTH) {
                idCard = conver15CardTo18(idCard);
            }
            String sCardNum = idCard.substring(16, 17);
            if (Integer.parseInt(sCardNum) % 2 != 0) {
                sGender = "M";
            } else {
                sGender = "F";
            }
            return sGender;
        }

        /**
         * 根据身份编号获取户籍省份
         *
         * @param idCard 身份编码
         * @return 省级编码。
         */
        @SuppressWarnings("unused")
        private static String getProvinceByIdCard(String idCard) {

            int len = idCard.length();
            String sProvince = null;
            String sProvinNum = "";
            if (len == CHINA_ID_MIN_LENGTH || len == CHINA_ID_MAX_LENGTH) {
                sProvinNum = idCard.substring(0, 2);
            }
            sProvince = cityMap.get(sProvinNum);
            return sProvince;
        }

        /**
         * 数字验证
         *
         * @param val
         * @return 提取的数字。
         */
        private static boolean isNum(String val) {

            return val == null || "".equals(val) ? false : val.matches("^[0-9]*$");
        }

        /**
         * 验证小于当前日期 是否有效
         *
         * @param iYear  待验证日期(年)
         * @param iMonth 待验证日期(月 1-12)
         * @param iDate  待验证日期(日)
         * @return 是否有效
         */
        private static boolean valiDate(int iYear, int iMonth, int iDate) {

            Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            int datePerMonth;
            if (iYear < MIN || iYear >= year) {
                return false;
            }
            if (iMonth < 1 || iMonth > 12) {
                return false;
            }
            switch (iMonth) {
                case 4:
                case 6:
                case 9:
                case 11:
                    datePerMonth = 30;
                    break;
                case 2:
                    boolean dm = (iYear % 4 == 0 && iYear % 100 != 0 || iYear % 400 == 0) && iYear > MIN && iYear < year;
                    datePerMonth = dm ? 29 : 28;
                    break;
                default:
                    datePerMonth = 31;
            }
            return iDate >= 1 && iDate <= datePerMonth;
        }
    }

}
