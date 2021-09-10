package com.deng.commons.utils;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

/**
 * @ClassName: DecimalUtil
 * @Description: 金额处理工具类
 * @author ZongCai
 * @date 2021/7/19
 */
public class DecimalUtil {

	private static int ROUND_CEILING; // 向正无穷方向舍入
	private static int ROUND_DOWN; // 向零方向舍入
	private static int ROUND_FLOOR; // 向负无穷方向舍入
	private static int ROUND_HALF_DOWN; // 向（距离）最近的�?边舍入，除非两边（的距离）是相等,如果是这样，向下舍入, 例如1.55 保留�?位小数结果为1.5
	private static int ROUND_HALF_EVEN; // 向（距离）最近的�?边舍入，除非两边（的距离）是相等,如果是这样，如果保留位数是奇数，使用ROUND_HALF_UP ，如果是偶数，使用ROUND_HALF_DOWN
	private static int ROUND_HALF_UP; // 向（距离）最近的�?边舍入，除非两边（的距离）是相等,如果是这样，向上舍入, 1.55保留�?位小数结果为1.6
	private static int ROUND_UNNECESSARY; // 计算结果是精确的，不�?要舍入模�?
	private static int ROUND_UP; // 向远�?0的方向舍�?

	private static final int DEF_DIV_SCALE = 10;


	public static BigDecimal format(BigDecimal value) {
		return format(value, 2);
	}

	/**
	 * 货币format
	 * @param value  货币
	 * @param digits 保留小数位数
	 * @return
	 */
	private static BigDecimal format(BigDecimal value, int digits) {
		// return value.setScale(digits, BigDecimal.ROUND_HALF_UP); //四舍五入
		// return value.setScale(digits, BigDecimal.ROUND_CEILING);  //向上取整
		return value.setScale(digits, BigDecimal.ROUND_HALF_UP);  //向下取整
	}

	/**
	 * 货币format
	 * @param value  货币
	 * @param digits 保留小数位数
	 * @param roundingMode  舍入方式
	 * @return
	 */
	public static BigDecimal format(BigDecimal value, int digits,int roundingMode) {
		// return value.setScale(digits, BigDecimal.ROUND_HALF_UP); //四舍五入
		// return value.setScale(digits, BigDecimal.ROUND_CEILING);  //向上取整
		return value.setScale(digits, roundingMode);  //向下取整
	}

	/**
	 * 向上取整
	 */
	public static BigDecimal ceiling(BigDecimal value) {
		return  BigDecimal.valueOf(Math.ceil(value.doubleValue()));
	}

	public static double deciMal(String value, int scale, int roundingMode) {
		if (StringUtils.isBlank(value)){
			value = "0";
		}
		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(scale, roundingMode);
		return bd.doubleValue();
	}

	/**
	 * @param value
	 * @param scale
	 * @param roundingmode
	 * @return
	 * @author liuyang
	 * 2018年4月26日 下午1:29:01
	 */
	public static BigDecimal BigToPay(BigDecimal value, Integer scale, Integer roundingmode) {
		// scale默认两位数
		if (scale == null){
			scale = 2;
		}
		// 默认 四舍五入
		if (roundingmode == null) {
			roundingmode = BigDecimal.ROUND_HALF_UP;
		}
		return (value == null ? BigDecimal.ZERO : value).setScale(scale, roundingmode);
	}

	public static double deciMal(double value, int scale, int roundingMode) {
		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(scale, roundingMode);
		double d = bd.doubleValue();
		bd = null;
		return d;
	}

	public static BigDecimal deciBigMal(String value, int scale, int roundingMode) {
		if (StringUtils.isBlank(value)){
			value = "0";
		}
		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(scale, roundingMode);
		return bd;
	}


	// 比较大小  // 大于 1 小于 -1 等于 0
	public static Integer judgeStrDecil (String start, String end) {
		if (StringUtils.isBlank(start)){
			start = "0";
		}
		if (StringUtils.isBlank(end)){
			end = "0";
		}
		Integer flag = new BigDecimal(start).compareTo(new BigDecimal(end));
		return flag;
	}

	// BigDecimal 比较大小  // 大于 1 小于 -1 等于 0
	public static Integer judgeBigDecil (BigDecimal start, BigDecimal end) {
		Integer flag = (start == null ? BigDecimal.ZERO : start).compareTo(end == null ? BigDecimal.ZERO : end);
		return flag;
	}

	// 除法
	public static BigDecimal diviBigMal(String value1, String value2, int scale, int roundingMode) {
		if (StringUtils.isBlank(value1)){
			value1 = "0";
		}
		if (StringUtils.isBlank(value2)){
			value2 = "1";
		}
		BigDecimal bd1 = new BigDecimal(value1);
		BigDecimal bd2 = new BigDecimal(value2);
		BigDecimal bd = bd1.divide(bd2, scale, roundingMode);
		return bd;
	}

	public static double deciMals(BigDecimal value, int scale, int roundingMode) {
		value = (value == null ? BigDecimal.ZERO : value).setScale(scale, roundingMode);
		double d = value.doubleValue();
		value = null;
		return d;
	}

	// 加法
	public static BigDecimal addBigMal(String value1, String value2, int scale, int roundingMode) {
		if (StringUtils.isBlank(value1)){
			value1 = "0";
		}
		if (StringUtils.isBlank(value2)){
			value2 = "0";
		}
		BigDecimal bd1 = new BigDecimal(value1);
		BigDecimal bd2 = new BigDecimal(value2);
		return bd1.add(bd2);
	}

	public static BigDecimal addBigMall(BigDecimal value1, BigDecimal value2, Integer scale, Integer roundingmode) {
		// scale默认两位数
		if (scale == null){
			scale = 2;
		}
		// 默认 四舍五入
		if (roundingmode == null) {
			roundingmode = BigDecimal.ROUND_HALF_UP;
		}
		BigDecimal bd2 = (value1 == null ? BigDecimal.ZERO : value1).add(value2 == null ? BigDecimal.ZERO : value2);
		bd2 = bd2.setScale(scale, roundingmode);
		return bd2;
	}

	// 减法
	public static BigDecimal subtractionBigMal(String value1, String value2, int scale, int roundingMode) {
		if (StringUtils.isBlank(value1)){
			value1 = "0";
		}
		if (StringUtils.isBlank(value2)){
			value2 = "0";
		}
		BigDecimal bd1 = new BigDecimal(value1);
		BigDecimal bd2 = new BigDecimal(value2);
		return bd1.subtract(bd2);
	}

	public static BigDecimal subTionBigMal(BigDecimal value1, BigDecimal value2, Integer scale, Integer roundingmode) {
		// scale默认两位数
		if (scale == null){
			scale = 2;
		}
		// 默认 四舍五入
		if (roundingmode == null) {
			roundingmode = BigDecimal.ROUND_HALF_UP;
		}
		return (value1 == null ? BigDecimal.ZERO : value1).subtract(value2 == null ? BigDecimal.ZERO : value2).setScale(scale, roundingmode);
	}

	// 乘法
	public static BigDecimal multiplicationBigMal(String value1, String value2, int scale, Integer roundingMode) {
		if (StringUtils.isBlank(value1)){
			value1 = "0";
		}
		if (StringUtils.isBlank(value2)){
			value2 = "0";
		}
		BigDecimal bd1 = new BigDecimal(value1);
		BigDecimal bd2 = new BigDecimal(value2);
		// 默认 四舍五入
		if (roundingMode == null){
			roundingMode = BigDecimal.ROUND_HALF_UP;
		}
		return bd1.multiply(bd2).setScale(scale,roundingMode);
	}

	// big乘法转换
	public static BigDecimal BigMalMultBigMal(BigDecimal value1, BigDecimal value2, Integer scale, Integer roundingmode) {
		// scale默认两位数
		if (scale == null){
			scale = 2;
		}
		// 默认 四舍五入
		if (roundingmode == null){
			roundingmode = BigDecimal.ROUND_HALF_UP;
		}
		return (value1 == null ? BigDecimal.ZERO : value1).multiply(value2 == null ? BigDecimal.ZERO : value2).setScale(scale,roundingmode);
	}

	// 除法
	public static BigDecimal divisionBigMal(String value1, String value2, int scale, Integer roundingMode) {
		if (StringUtils.isBlank(value1)){
			value1 = "0";
		}
		if (StringUtils.isBlank(value2)){
			value2 = "0";
		}
		// 默认 四舍五入
		if (roundingMode == null){
			roundingMode = BigDecimal.ROUND_HALF_UP;
		}
		BigDecimal bd1 = new BigDecimal(value1);
		BigDecimal bd2 = new BigDecimal(value2);
		return bd1.divide(bd2,scale,roundingMode);
	}

	// 除法 BigDecimal 传参
	public static BigDecimal divisionBigMal(BigDecimal value1, BigDecimal value2, int scale, Integer roundingMode) {
		// 默认 四舍五入
		if (roundingMode == null){
			roundingMode = BigDecimal.ROUND_HALF_UP;
		}
		return (value1 == null ? BigDecimal.ZERO : value1).divide(value2 == null ? new BigDecimal(1) : value2).setScale(scale,roundingMode);
	}

}






