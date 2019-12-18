package com.lx.common.util;

import java.math.BigInteger;
import java.util.Random;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class StringUtilForUdp {
	public static String ERROR_MSG = "This command is invalid";
	public static String NORETUNRN_MSG = "NORETUNRN";

	/**
	 * 判断一个字符串是否为空或空字符串
	 * 
	 * @param str
	 * @return 如果str==null或str==""返回true否则返回false
	 */
	public static boolean isEmpty(String str) {
		return str == null || "".equals(str);
	}

	/**
	 * 截取公共部分字符串
	 */
	public static String substringCommon(String str, int index) {
		if (!isEmpty(str)) {
			return str.substring(0, index);
		} else {
			return null;
		}
	}

	/**
	 * 截取标识符字符串
	 */
	public static String substringFlag(String str, int index) {
		if (!isEmpty(str)) {
			return str.substring(index, index + 2);
		} else {
			return null;
		}
	}

	/**
	 * 10进制数字转16进制
	 */
	public static String DecToHex(int num) {
		return Integer.toHexString(num);
	}

	/**
	 * 十六进制转成二进制字符串
	 * 
	 * @param hexString
	 * @return
	 */
	public static String HexStr2BinStr(String hexString) {
		if (hexString == null || hexString.length() % 2 != 0)
			return null;
		String bString = "", tmp;
		for (int i = 0; i < hexString.length(); i++) {
			tmp = "0000" + Integer.toBinaryString(Integer.parseInt(hexString.substring(i, i + 1), 16));
			bString += tmp.substring(tmp.length() - 4);
		}
		return bString;
	}

	/**
	 * 十六进制转成十进制数字
	 * 
	 * @param hexString
	 * @return
	 */
	public static int HexStr2Int(String hexString) {
		try {
			Integer x = Integer.parseInt(hexString, 16);
			return x.intValue();
		} catch (Exception ex) {
			return 0;
		}
	}

	/**
	 * 二进制转换成十进制数字
	 * 
	 * @param binString
	 * @return
	 */
	public static int BinStr2Int(String binString) {
		try {
			Integer x = Integer.parseInt(binString, 2);
			return x.intValue();
		} catch (Exception ex) {
			return 0;
		}
	}

	/**
	 * @Description: 十进制转换成二进制 ()
	 * @param decimalSource
	 * @return String
	 */
	public static String decimalToBinary(String decimalSource) {

		BigInteger bi = new BigInteger(decimalSource); // 转换成BigInteger类型
		return bi.toString(2); // 参数2指定的是转化成X进制，默认10进制
	}

	/**
	 * 获取16进制随机数
	 * 
	 * @param len
	 * @return
	 * @throws CoderException
	 */
	public static String randomHexString(int len) {
		try {
			StringBuffer result = new StringBuffer();
			for (int i = 0; i < len; i++) {
				result.append(Integer.toHexString(new Random().nextInt(16)));
			}
			String saltHex = result.toString().toUpperCase();
			if ("0000".equals(saltHex)) {
				randomHexString(len);
			}
			return result.toString().toUpperCase();

		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;

	}

	/**
	 * 传入的字符串高低位转换
	 * 
	 * @param s
	 * @return
	 */
	public static String rev(String s) {

		byte b[] = s.getBytes();
		byte result[] = new byte[b.length];
		for (int i = b.length - 1, j = 0; i >= 0; i = i - 2, j = j + 2) {
			result[j] = b[i - 1];
			result[j + 1] = b[i];
		}
		return new String(result);
	}

	/**
	 * 执行运算字符串型的表达式
	 * 
	 * @param exp
	 * @return
	 */
	public static String ExeExp(String exp) {
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("js");
		try {
			Object result = engine.eval(exp);
			return String.valueOf(result);
		} catch (ScriptException e) {
			return "";
		}
	}

	/**
	 * 替换所有的字符串
	 * 
	 * @param str
	 * @param oldstr
	 * @param newStr
	 * @return
	 */
	public static String Replace(String str, String oldstr, String newStr) {
		return str.replaceAll(oldstr, newStr);
	}

	public static void main(String[] aa) {
		String aaa = ExeExp("((230+1000)*0.01).toFixed(9)");

	}

	/**
	 * 数组转换成十六进制字符串
	 * 
	 * @param byte[]
	 * @return HexString
	 */
	public static final String bytesToHexString(byte[] bArray) {
		StringBuffer sb = new StringBuffer(bArray.length);
		String sTemp;
		for (int i = 0; i < bArray.length; i++) {
			sTemp = Integer.toHexString(0xFF & bArray[i]);
			if (sTemp.length() < 2)
				sb.append(0);
			sb.append(sTemp.toUpperCase());
		}
		return sb.toString();
	}

	/**
	 * 字符转十六进制
	 * 
	 * @param s
	 * @return
	 */
	public static String strTo16(String s) {
		String str = "";
		for (int i = 0; i < s.length(); i++) {
			int ch = s.charAt(i);
			String s4 = Integer.toHexString(ch);
			str = str + s4;
		}
		return str;
	}

	public static String HexString2binaryString(String hexString) {

		if (hexString == null || hexString.length() % 2 != 0)
			return null;
		String bString = "", tmp;
		for (int i = 0; i < hexString.length(); i++) {
			tmp = "0000" + Integer.toBinaryString(Integer.parseInt(hexString.substring(i, i + 1), 16));
			bString += tmp.substring(tmp.length() - 4);
		}
		return bString;
	}

	/**
	 * 这里只处理规格化数，非规格化数，NaN，finite等没有考虑 2进制字符串转Float数，包括（正数和负数）
	 * 
	 * @param binaryString
	 * @return
	 */
	public static float BinaryStringToFloat(final String binaryString) {
		// float是32位，将这个binaryString左边补0补足32位，如果是Double补足64位。
		final String stringValue = LeftPad(binaryString, '0', 32);
		// 首位是符号部分，占1位。
		// 如果符号位是0则代表正数，1代表负数
		final int sign = stringValue.charAt(0) == '0' ? 1 : -1;
		// 第2到9位是指数部分，float占8位，double占11位。
		final String exponentStr = stringValue.substring(1, 9);
		// 将这个二进制字符串转成整数，由于指数部分加了偏移量（float偏移量是127，double是1023）
		// 所以实际值要减去127
		final int exponent = Integer.parseInt(exponentStr, 2) - 127;
		// 最后的23位是尾数部分，由于规格化数，小数点左边隐含一个1，现在加上
		final String mantissaStr = "1".concat(stringValue.substring(9, 32));
		// 这里用double，尽量保持精度，最好用BigDecimal，这里只是方便计算所以用double
		double mantissa = 0.00000;
		for (int i = 0; i < mantissaStr.length(); i++) {
			final int intValue = Character.getNumericValue(mantissaStr.charAt(i));
			// 计算小数部分，具体请查阅二进制小数转10进制数相关资料
			mantissa += (intValue * Math.pow(2, -i));
		}
		// 根据IEEE 754 标准计算：符号位 * 2的指数次方 * 尾数部分
		return (float) (sign * Math.pow(2, exponent) * mantissa);
	}

	// 一个简单的补齐方法，很简单，没考虑很周到。具体请参考common-long.jar/StringUtils.leftPad()
	/**
	 * @param str
	 * @param padChar
	 * @param length
	 * @return
	 */
	public static String LeftPad(final String str, final char padChar, int length) {
		final int repeat = length - str.length();

		if (repeat <= 0) {
			return str;
		}

		final char[] buf = new char[repeat];
		for (int i = 0; i < buf.length; i++) {
			buf[i] = padChar;
		}
		return new String(buf).concat(str);
	}
}
