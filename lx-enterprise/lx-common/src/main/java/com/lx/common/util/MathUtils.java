
package com.lx.common.util;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Objects;


public class MathUtils {

	private static BigDecimal zero = BigDecimal.ZERO;
	
	public static String generateCode(int num) {
		String code = StringUtils.EMPTY;
		int max = 9;
		for (int i = 0; i < num; i++) {
			if (i == 0) {
				code += String.valueOf(Math.round(Math.random() * (max - 1) + 1));
			} else {
				code += String.valueOf(Math.round(Math.random() * max));
			}
		}
		return code;
	}

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			System.out.println(generateCode(6));
		}
	}
	
	/**
	 * 计算比例
	 * @param value 值
	 * @param total 总和
	 * @return
	 */
	public static BigDecimal calculatePercent(BigDecimal value,BigDecimal total) {
		if(value.compareTo(zero)<=0)return zero;
		return value.divide(total, 2, RoundingMode.HALF_UP).multiply(new BigDecimal("100"));
	}
	/**
	 * 获取数字中，最小的一个数字
	 * 如果传入的参数没有有效值，则会抛出 @throws @see NoSuchElementException
	 * @param nums
	 * @return
	 */
	public static BigDecimal getSmallest(BigDecimal... nums) {
		return Arrays.asList(nums)
				.stream()
				.filter(Objects::nonNull)
				.min((a,b)->a.compareTo(b))
				.get();
	}
	/**
	 * 获取数字中，最大的一个数字
	 * 如果传入的参数没有有效值，则会抛出 @throws @see NoSuchElementException
	 * @param nums
	 * @return
	 */
	public static BigDecimal getBiggest(BigDecimal... nums) {
		return Arrays.asList(nums)
				.stream()
				.filter(Objects::nonNull)
				.max((a,b)->a.compareTo(b))
				.get();
	}
	
}
