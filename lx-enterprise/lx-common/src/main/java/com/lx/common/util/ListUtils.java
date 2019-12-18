package com.lx.common.util;
/**
 * 集合的工具类
 * Create Date:	2018年7月18日 上午11:03:25
 * @version:	V3.0.1
 * @author:		Lian weimao
 */

import java.util.List;
import java.util.stream.Collectors;

public class ListUtils {
	/**
	 * 截取List，并返回截取后的List
	 * <br>例：list:[1,2,3,4,5,6,7,8]，start:2,offset:3
	 * <br>返回：[3,4,5]<br>
	 * Lian weimao CreateTime:2018年7月18日 上午11:07:27
	 * @param list 数据源
	 * @param start 截取的起始位置 从0开始
	 * @param offset 截取量
	 * @return
	 */
	public static <T> List<T> limit(List<T> list,int start,int offset){
		if(list==null) {
			throw new IllegalArgumentException("list:列表不能为null");
		}
		if(start<1) {
			throw new IllegalArgumentException("start：当前页码不能小于0");
		}
		return list.stream().skip(start).limit(offset).collect(Collectors.toList());
	}
	/**
	 * 按照页面查询规范截取List，并返回截取后的List
	 * <br>例：list:[1,2,3,4,5,6,7,8]，currentPage:2,pageSize:3
	 * <br>返回：[4,5,6]<br>
	 * Lian weimao CreateTime:2018年7月18日 上午11:10:08
	 * @param list 数据源
	 * @param currentPage 当前页码 从1开始
	 * @param pageSize 页面数据数量
	 * @return
	 */
	public static <T> List<T> limitPage(List<T> list,int currentPage,int pageSize){
		if(list==null) {
			throw new IllegalArgumentException("list:列表不能为null");
		}
		if(currentPage<1) {
			throw new IllegalArgumentException("currentPage：当前页码不能小于1");
		}
		return list.stream().skip((currentPage-1)*pageSize).limit(pageSize).collect(Collectors.toList());
	}
}
