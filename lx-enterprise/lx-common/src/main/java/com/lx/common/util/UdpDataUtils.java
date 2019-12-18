package com.lx.common.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 定义udp中需要转换的常量
 * 
 * @author feng yi
 *
 */
public class UdpDataUtils {

	/**
	 * 数据标识中的数据分类表计
	 */
	public static final Map<String, String> StationTypeMap = new HashMap<String, String>() {
		private static final long serialVersionUID = 1L;

		{

			put("00001", "00001");
			put("00010", "00010");
			put("00011", "00011");
			put("00100", "00100");
			put("00101", "00101");
			put("00110", "00110");
			put("00111", "00111");
			put("01000", "01000");
			put("01001", "01001");
			put("10000", "10000");
			put("10001", "10001");
			put("10010", "10010");
			put("11000", "11000");
			put("11001", "11001");
			put("11010", "11010");

			// put("00001","电压");
			// put("00010","电流");
			// put("00011","有功功率");
			// put("00100","无功功率");
			// put("00101","视在功率");
			// put("00110","功率因数");
			// put("00111","电压相角");
			// put("01000","电流相角");
			// put("01001","累计电量");
			// put("10000","电网频率");
			// put("10001","电流不平衡度");
			// put("10010","电流THD");
			// put("11000","温度");
			// put("11001","湿度");
			// put("11010","开关状态");
		}
	};

	/**
	 * 数据分类对应的数据计算方式
	 */
	public static final Map<String, String> StationCalRuleMap = new HashMap<String, String>() {
		private static final long serialVersionUID = 1L;

		{

			put("00001", "{DATA}.toFixed(2)");
			put("00010", "{DATA}.toFixed(2)");
			put("00011", "{DATA}.toFixed(4)");
			put("00100", "{DATA}.toFixed(3)");
			put("00101", "{DATA}.toFixed(4)");
			put("00110", "{DATA}.toFixed(3)");
			put("00111", "{DATA}.toFixed(1)");
			put("01000", "{DATA}.toFixed(1)");
			put("01001", "{DATA}.toFixed(1)");
			put("10000", "{DATA}.toFixed(2)");
			put("10001", "{DATA}.toFixed(2)");
			put("10010", "{DATA}.toFixed(2)");
			put("10011", "{DATA}.toFixed(2)");
			put("10100", "{DATA}.toFixed(2)");
			put("10101", "{DATA}.toFixed(2)");
			put("11000", "{DATA}.toFixed(1)");
			put("11001", "{DATA}.toFixed(2)");
			put("11000", "{DATA}.toFixed(1)");
			put("11001", "{DATA}.toFixed(2)");
			put("01010", "{DATA}.toFixed(1)");// 总有功电度
			put("01011", "{DATA}.toFixed(1)");// 总无功电度
			put("11010", "{DATA}.toFixed(0)");// 整数
		}
		/*
		 * {
		 * 
		 * put("00001", "({DATA}*0.01).toFixed(2)"); put("00010", "({DATA}*0.01).toFixed(2)"); put("00011", "({DATA}*0.0001).toFixed(4)");
		 * put("00100", "({DATA}*0.001).toFixed(3)"); put("00101", "({DATA}*0.0001).toFixed(4)"); put("00110", "({DATA}*0.001).toFixed(3)");
		 * put("00111", "({DATA}*0.1).toFixed(1)"); put("01000", "({DATA}*0.1).toFixed(1)"); put("01001", "({DATA}*0.1).toFixed(1)"); put("10000",
		 * "({DATA}*0.01).toFixed(2)"); put("10001", "({DATA}*0.01).toFixed(2)"); put("10010", "({DATA}*0.01).toFixed(2)"); put("11000",
		 * "({DATA}*0.1).toFixed(1)"); put("11001", "({DATA}*0.01).toFixed(2)"); put("11010", "{DATA}");// 整数 }
		 */ };
	/**
	 * 相位常量
	 */

	public static final Map<String, String> PhaseMap = new HashMap<String, String>() {
		private static final long serialVersionUID = 1L;

		{
			// put("00","A相");
			// put("01","B相");
			// put("10","C相");
			// put("11","N相/三相和");
			put("00", "00");
			put("01", "01");
			put("10", "10");
			put("11", "11");
		}
	};
	/**
	 * 测点对应的实体属性
	 */

	public static final Map<String, String> StationMap = new HashMap<String, String>() {
		private static final long serialVersionUID = 1L;

		{
			put("0000001", "aphaseVoltage");// A相电压
			put("0100001", "bphaseVoltage");// B相电压
			put("1000001", "cphaseVoltage");// C相电压

			put("0000010", "aphaseCurrent");// A相电流
			put("0100010", "bphaseCurrent");// B相电流
			put("1000010", "cphaseCurrent");// C相电流
			put("1100010", "nphaseCurrent");// N相电流

			put("1100011", "sumActivePower");// 总有功功率
			put("0000011", "aphaseActivePower");// A相有功功率
			put("0100011", "bphaseActivePower");// B相有功功率
			put("1000011", "cphaseActivePower");// C相有功功率

			put("1100100", "sumReactivePower");// 总无功功率
			put("0000100", "aphaseReactivePower");// A相无功功率
			put("0100100", "bphaseReactivePower");// B相无功功率
			put("1000100", "cphaseReactivePower");// C相无功功率

			put("1100101", "sumApparentPower");// 总视在功率
			put("0000101", "aphaseApparentPower");// A相视在功率
			put("0100101", "bphaseApparentPower");// B相视在功率
			put("1000101", "cphaseApparentPower");// C相视在功率

			put("1100110", "sumPowerFactor");// 总功率因数
			put("0000110", "aphasePowerFactor");// A相功率因数
			put("0100110", "bphasePowerFactor");// B相功率因数
			put("1000110", "cphasePowerFactor");// C相功率因数

			put("0000111", "aphaseVoltagePhaseAngle");// 电压相角-A
			put("0100111", "bphaseVoltagePhaseAngle");// 电压相角-B
			put("1000111", "cphaseVoltagePhaseAngle");// 电压相角-C

			put("0001000", "aphaseCurrentPhaseAngle");// 电流相角-A
			put("0101000", "bphaseCurrentPhaseAngle");// 电流相角-B
			put("1001000", "cphaseCurrentPhaseAngle");// 电流相角-C

			put("0010001", "threePhaseCurrentImbalance");// 电流三相不平衡度

			put("0010010", "aphaseCurrentThd");// 电流THD-A
			put("0110010", "bphaseCurrentThd");// 电流THD-B
			put("1010010", "cphaseCurrentThd");// 电流THD-C

			put("0010011", "lineVoltageOneTwo");// 线电压12
			put("0010100", "lineVoltageTwoThree");// 线电压23
			put("0010101", "lineVoltageThreeOne");// 线电压31

			put("0010000", "gridFrequency");// 电网频率

			put("0001001", "acmultElectr");// 累计电量

			put("0011000", "temperature");// 温度
			put("0011001", "humidity");// 湿度
			put("0011010", "switchStatus");// 开关量状态

			put("0001010", "totalActivePower");// 总有功电度
			put("0001011", "totalReactivePower");// 总无功电度
		}
	};
	/**
	 * 测点对应的实体属性
	 */

	public static final Map<String, String> StationNameMap = new HashMap<String, String>() {
		private static final long serialVersionUID = 1L;

		{
			put("aphaseVoltage", "A相电压");// A相电压
			put("bphaseVoltage", "B相电压");// B相电压
			put("cphaseVoltage", "C相电压");// C相电压

			put("aphaseCurrent", "A相电流");// A相电流
			put("bphaseCurrent", "B相电流");// B相电流
			put("cphaseCurrent", "C相电流");// C相电流
			put("nphaseCurrent", "N相电流");// N相电流

			put("sumActivePower", "总有功功率");// 总有功功率
			put("aphaseActivePower", "A相有功功率");// A相有功功率
			put("bphaseActivePower", "B相有功功率");// B相有功功率
			put("cphaseActivePower", "C相有功功率");// C相有功功率

			put("sumReactivePower", "总无功功率");// 总无功功率
			put("aphaseReactivePower", "A相无功功率");// A相无功功率
			put("bphaseReactivePower", "B相无功功率");// B相无功功率
			put("cphaseReactivePower", "C相无功功率");// C相无功功率

			put("sumApparentPower", "总视在功率");// 总视在功率
			put("aphaseApparentPower", "A相视在功率");// A相视在功率
			put("bphaseApparentPower", "B相视在功率");// B相视在功率
			put("cphaseApparentPower", "C相视在功率");// C相视在功率

			put("sumPowerFactor", "总功率因数");// 总功率因数
			put("aphasePowerFactor", "A相功率因数");// A相功率因数
			put("bphasePowerFactor", "B相功率因数");// B相功率因数
			put("cphasePowerFactor", "C相功率因数");// C相功率因数

			put("aphaseVoltagePhaseAngle", "电压相角-A");// 电压相角-A
			put("bphaseVoltagePhaseAngle", "电压相角-B");// 电压相角-B
			put("cphaseVoltagePhaseAngle", "电压相角-C");// 电压相角-C

			put("aphaseCurrentPhaseAngle", "电流相角-A");// 电流相角-A
			put("bphaseCurrentPhaseAngle", "电流相角-B");// 电流相角-B
			put("cphaseCurrentPhaseAngle", "电流相角-C");// 电流相角-C

			put("threePhaseCurrentImbalance", "电流三相不平衡度");// 电流三相不平衡度

			put("aphaseCurrentThd", "电流THD-A");// 电流THD-A
			put("bphaseCurrentThd", "电流THD-B");// 电流THD-B
			put("cphaseCurrentThd", "电流THD-C");// 电流THD-C

			put("lineVoltageOneTwo", "线电压12");//
			put("lineVoltageTwoThree", "线电压23");//
			put("lineVoltageThreeOne", "线电压31");//

			put("gridFrequency", "电网频率");// 电网频率

			put("acmultElectr", "累计电量");// 累计电量

			put("temperature", "温度");// 温度
			put("humidity", "湿度");// 湿度
			put("switchStatus", "开关量状态");// 开关量状态

			put("totalActivePower", "总有功电度");// 总有功电度
			put("totalReactivePower", "总无功电度");// 总无功电度
		}
	};
	/**
	 * 征程异常标记
	 */

	public static final Map<String, String> PassStateMap = new HashMap<String, String>() {
		private static final long serialVersionUID = 1L;

		{
			put("1", "正常");
			put("0", "异常");
		}
	};
	/**
	 * 用途对应表
	 */
	public static final Map<String, String> DevicePuMap = new HashMap<String, String>() {
		private static final long serialVersionUID = 1L;

		{
			put("0A", "工业用");
			put("0B", "家庭用");
		}
	};

	/**
	 * 测量点类型分类 类型子类
	 */
	public static final Map<String, String> StationTypeSonMap = new HashMap<String, String>() {
		private static final long serialVersionUID = 1L;

		{
			put("high", "高压");
			put("low", "低压");
			put("branch", "分支");
		}
	};
}
