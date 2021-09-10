package com.deng.commons.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: DateTimeUtils
 * @Description: 时间处理工具类
 * @author ZongCai
 * @date 2021/7/19
 */
@Slf4j
public class DateTimeUtils {
	private static StringBuffer buffer = new StringBuffer();
	private static String ZERO = "0";
	public static SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
	public static SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 *时间（秒）间隔转化为文字显示间隔
	 */
	public static String translateSecondsOfDisplay(String secsondsString) {
		if (StringUtils.isEmpty(secsondsString) || secsondsString.equals("-")) {
			return "-";
		}
		long secsonds = Long.valueOf(secsondsString).longValue();
		long day = secsonds / (24 * 60 * 60);
		long hour = (secsonds / (60 * 60) - day * 24);
		long min = ((secsonds / (60)) - day * 24 * 60 - hour * 60);
		long secs = (secsonds - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
		String s = "";
		if (day > 0) {
			s = day + "天" + hour + "小时" + min + "分钟" + secs + "秒";
		} else if (hour > 0) {
			s = hour + "小时" + min + "分钟" + secs + "秒";
		} else if (min > 0) {
			s = min + "分钟" + secs + "秒";
		} else {
			s = secs + "秒";
		}
		return s;
	}

	/**
	 * 日期格式化
	 * @param dateTime 被格式化的时间（LocalDateTime类型时间）
	 * @param pattern 格式化形式
	 */
	public static String localDateTimeFormat(LocalDateTime dateTime,String pattern){

		if(dateTime == null){
			return  "";
		}
		DateTimeFormatter df = DateTimeFormatter.ofPattern(pattern);

		return df.format(dateTime);
	}

	/**
	 *java.util.Date 转换为 LocalDateTime 类型
	 */
	public static LocalDateTime dateToLocalDateTime(java.util.Date date) {
		Instant instant = date.toInstant();
		ZoneId zone = ZoneId.systemDefault();
		LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
		return localDateTime;
	}

	/**
	 * 计算两个时间点的天数差
	 * @param dt1 第一个时间点
	 * @param dt2 第二个时间点
	 * @return int，即要计算的天数差
	 */
	public static int dateDiff(LocalDateTime dt1,LocalDateTime dt2){
		//获取第一个时间点的时间戳对应的秒数
		long t1 = dt1.toEpochSecond(ZoneOffset.ofHours(0));
		//获取第一个时间点在是1970年1月1日后的第几天
		long day1 = t1 /(60*60*24);
		//获取第二个时间点的时间戳对应的秒数
		long t2 = dt2.toEpochSecond(ZoneOffset.ofHours(0));
		//获取第二个时间点在是1970年1月1日后的第几天
		long day2 = t2/(60*60*24);
		//返回两个时间点的天数差
		return (int)(day2 - day1);
	}

	/**
	 * 获取日期当中的号
	 */
	public static int getDayStrByDate(Date datas) {
		String sdatas = format1.format(datas);
		String[] dss = sdatas.split("-");
		return Integer.parseInt(dss[2]);
	}
	
	/**
	 * 指定日期N个月后的日期
	 * 
	 * 参数：inputDate 初始日期
	 * 	   number    增加的月份
	 *    
	 */
	public static String  getAfterMonthDay(String inputDate,int number) {
        Calendar c = Calendar.getInstance();//获得一个日历的实例   
        Date date = null;   
        try{   
            date = format1.parse(inputDate);//初始日期   
        }catch(Exception e){  
        	e.printStackTrace();
        }   
        c.setTime(date);//设置日历时间   
        c.add(Calendar.MONTH,number);//在日历的月份上增加n个月
        String strDate = format1.format(c.getTime());//的到你想要得n个月后的日期   
        return strDate;
	}
	
	/**
	 * 描        述：yyyy-MM-dd 转 yyyyMMdd
	 */
	public static String formatDel(String str){
		  SimpleDateFormat sf1 = new SimpleDateFormat("yyyyMMdd");
		  String sfstr = "";
		  try {
		      sfstr = sf1.format(format1.parse(str));
		  } catch (ParseException e) {
			  e.printStackTrace();
		  }
		  return sfstr;
		 }

	/**
	 * 根据当前时间得到项目编号
	 */
	public static String create18Num(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmssSSS");
		return sdf.format(date);
	}
	
	/**
	 * 得到指定日期的一天的的最后时刻23:59:59
	 */
	public static Date getFinallyDate(Date date) {
		String temp = format1.format(date);
		temp += " 23:59:59";
		try {
			return format2.parse(temp);
		} catch (ParseException e) {
			return null;
		}
	}
	public static String getFinallyDate(String date) {

		date = format1.format(parseDate(date));
		date += " 23:59:59";
		return date;
	}
	public static LocalDateTime getFinallyDate(LocalDateTime date) {
		return LocalDateTime.of(date.toLocalDate(), LocalTime.MAX);
	}

	public static Date getCurrentDateTime() {
		String temp = format2.format(new Date());
		try {
			return format2.parse(temp);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 得到指定日期的一天的开始时刻00:00:00
	 */
	public static Date getStartDate(Date date) {
		String temp = format1.format(date);
		temp += " 00:00:00";
		try {
			return format2.parse(temp);
		} catch (Exception e) {
			return null;
		}
	}
	public static String getStartDate(String date) {
		date = format1.format(parseDate(date));
		date += " 00:00:00";
		try {
			return date;
		} catch (Exception e) {
			return null;
		}
	}
	public static LocalDateTime getStartDate(LocalDateTime date) {
		return LocalDateTime.of(date.toLocalDate(), LocalTime.MIN);
	}

	/**
	 * 获取date周后的第amount周的最后时间（这里星期日为一周的最后一天）
	 * @param amount
	 */
	public static Date getSpecficWeekEnd(Date date, int amount) {
		Calendar cal = Calendar.getInstance();
		cal.setFirstDayOfWeek(Calendar.MONDAY); /* 设置一周的第一天为星期一 */
		cal.add(Calendar.WEEK_OF_MONTH, amount);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		return getFinallyDate(cal.getTime());
	}

	public static Date getSpecficDateStart(Date date, int amount) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_YEAR, amount);
		return getStartDate(cal.getTime());
	}

	public static String getSpecficDateStart2(Date date, int amount) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, amount);
		buffer.delete(0, buffer.capacity());
		buffer.append(getYear(calendar) + "-");

		if (getMonth(calendar) < 10) {
			buffer.append(ZERO);
		}
		buffer.append(getMonth(calendar) + "-");

		if (getDate(calendar) < 10) {
			buffer.append(ZERO);
		}
		buffer.append(getDate(calendar));
		return buffer.toString();
	}

	/**
	 * 获取date年后的amount年的第一天的开始时间
	 * @param amount 可正
	 */
	public static Date getSpecficYearStart(Date date, int amount) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.YEAR, amount);
		cal.set(Calendar.DAY_OF_YEAR, 1);
		return getStartDate(cal.getTime());
	}

	/**
	 * 获取date年后的amount年的最后一天的终止时间
	 * @param amount 可正
	 */
	public static Date getSpecficYearEnd(Date date, int amount) {
		Date temp = getStartDate(getSpecficYearStart(date, amount + 1));
		Calendar cal = Calendar.getInstance();
		cal.setTime(temp);
		cal.add(Calendar.DAY_OF_YEAR, -1);
		return getFinallyDate(cal.getTime());
	}

	/**
	 * 获取date月后的amount月的第一天的开始时间
	 * @param amount 可正
	 */
	public static Date getSpecficMonthStart(Date date, int amount) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, amount);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return getStartDate(cal.getTime());
	}

	/**
	 * 获取当前自然月后的amount月的最后一天的终止时间
	 * @param amount 可正
	 */
	public static Date getSpecficMonthEnd(Date date, int amount) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getSpecficMonthStart(date, amount + 1));
		cal.add(Calendar.DAY_OF_YEAR, -1);
		return getFinallyDate(cal.getTime());
	}

	/**
	 * 获取date周后的第amount周的开始时间（这里星期一为一周的开始）
	 * @param amount 可正
	 */
	public static Date getSpecficWeekStart(Date date, int amount) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.setFirstDayOfWeek(Calendar.MONDAY); /* 设置一周的第一天为星期一 */
		cal.add(Calendar.WEEK_OF_MONTH, amount);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return getStartDate(cal.getTime());
	}

	public static int getYearsBetweenDate(Date begin, Date end) {
		int bYear = getDateField(begin, Calendar.YEAR);
		int eYear = getDateField(end, Calendar.YEAR);
		return eYear - bYear;
	}

	public static int getMonthsBetweenDate(Date begin, Date end) {
		int bMonth = getDateField(begin, Calendar.MONTH);
		int eMonth = getDateField(end, Calendar.MONTH);
		return eMonth - bMonth;
	}

	public static int getWeeksBetweenDate(Date begin, Date end) {
		int bWeek = getDateField(begin, Calendar.WEEK_OF_YEAR);
		int eWeek = getDateField(end, Calendar.WEEK_OF_YEAR);
		return eWeek - bWeek;
	}

	public static int getDaysBetweenDate(Date begin, Date end) {
		int bDay = getDateField(begin, Calendar.DAY_OF_YEAR);
		int eDay = getDateField(end, Calendar.DAY_OF_YEAR);
		return eDay - bDay;
	}

	public static String getNowTimeString() {
		Calendar calendar = getCalendar();
		buffer.delete(0, buffer.capacity());
		buffer.append(getYear(calendar) + "-");

		if (getMonth(calendar) < 10) {
			buffer.append(ZERO);
		}
		buffer.append(getMonth(calendar) + "-");

		if (getDate(calendar) < 10) {
			buffer.append(ZERO);
		}
		buffer.append(getDate(calendar) + " ");
		if (getHour(calendar) < 10) {
			buffer.append(ZERO);
		}
		buffer.append(getHour(calendar) + ":");
		if (getMinute(calendar) < 10) {
			buffer.append(ZERO);
		}
		buffer.append(getMinute(calendar) + ":");
		if (getSecond(calendar) < 10) {
			buffer.append(ZERO);
		}
		buffer.append(getSecond(calendar));
		return buffer.toString();
	}

	public static String getNowDateString() {
		Calendar calendar = getCalendar();
		buffer.delete(0, buffer.capacity());
		buffer.append(getYear(calendar) + "-");

		if (getMonth(calendar) < 10) {
			buffer.append(ZERO);
		}
		buffer.append(getMonth(calendar) + "-");

		if (getDate(calendar) < 10) {
			buffer.append(ZERO);
		}
		buffer.append(getDate(calendar));
		return buffer.toString();
	}

	public static String timeToString(Date date) {
		if (date != null) {
			return format1.format(date).toString();
		} else {
			return "";
		}
	}

	public static String timeToString2(Date date) {
		if (date != null) {
			return format2.format(date).toString();
		} else {
			return "";
		}
	}

	/**
	 * 方法说明：返回最近的检查时间
	 */
	public static String getLastDate(String lastCheckTime, String curCheckTime) {
		if (StringUtils.isBlank(lastCheckTime)) {
			lastCheckTime = curCheckTime;
		} else {
			lastCheckTime = lastCheckTime.compareTo(curCheckTime) > 0 ? lastCheckTime
					: curCheckTime;
		}
		return lastCheckTime;
	}

	public static Date parseDate(String date) {
		try {
			return format1.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return new Date();
		}
	}

	public static Date parseDate2(String date) {
		try {
			return format2.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return new Date();
		}
	}

	public static String formatDate(String date, String format) {
		try {
			if (StringUtils.isNotBlank(date)) {
				SimpleDateFormat f = new SimpleDateFormat(format);
				return f.format(f.parse(date));
			} else {
				return date;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return date;
		}
	}

	/**
	 * 两个时间段的相差天数
	 */
	public static long getDifferDays(String begin, String end) {
		long t = 0;
		try {
			Date dt1 = format1.parse(begin);
			Date dt0 = format1.parse(end);
			if (dt0.getTime() >= dt1.getTime()) {
				long l = dt0.getTime() - dt1.getTime();
				t = l / (3600 * 24 * 1000);
			}
			//t = t + 1;//最后一天不算
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}

	public static long getDifferDays(Date begin, Date end) {
		long t = 0;
		try {
			long d0 = end.getTime();
			long d1 = begin.getTime();
			if (d0 >= d1) {
				long l = d0 - d1;
				t = l / (3600 * 24 * 1000);
			}
			//t = t + 1;//最后一天不算
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return t;
	}

	public static long getDays(String begin) {
		long t = 0;
		Date d = new Date();
		try {
			if (StringUtils.isNotBlank(begin)) {
				Date dt1 = format1.parse(begin);
				long l = d.getTime() - dt1.getTime();
				t = l / (3600 * 1000 * 24);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return t;
	}

	public static long getDays(Date begin) {
		long t = 0;
		Date d = new Date();
		try {
			long l = d.getTime() - begin.getTime();
			t = l / (3600 * 1000 * 24);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return t;
	}

	/**
	 * 获取月龄
	 */
	public static int getMonths(Date birth) {
		int y = 0;
		Date d = new Date();
		try {
			y = getDiffMonth(birth, d);
			Calendar cal = Calendar.getInstance();
			cal.setTime(d);
			int dayOfMonthDate = cal.get(Calendar.DAY_OF_MONTH);
			cal.setTime(birth);
			int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
			if (dayOfMonthBirth > dayOfMonthDate) {
				y--;
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return y;
	}

	public static int getMonths(String birth) {
		int y = 0;
		Date d = new Date();
		try {
			Date dt1 = format1.parse(birth);
			Date dt0 = format1.parse(format1.format(d));
			// long l = d.getTime() - dt1.getTime();
			// long t = l / (3600 * 24 * 1000);
			y = getDiffMonth(dt1, dt0);
			Calendar cal = Calendar.getInstance();
			cal.setTime(d);
			int dayOfMonthDate = cal.get(Calendar.DAY_OF_MONTH);
			cal.setTime(dt1);
			int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
			if (dayOfMonthBirth > dayOfMonthDate) {
				y--;
			}

		} catch (Exception e) {
			log.error(e.getMessage());

		}
		return y;
	}

	public static int getMonths(String birth, String date) {
		int y = 0;
		try {
			Date dt1 = format1.parse(birth);
			Date dt0 = format1.parse(date);
			if (dt0.getTime() >= dt1.getTime()) {
				/*
				 * long l = dt0.getTime() - dt1.getTime(); long t = l / (3600 *
				 * 24 * 1000);
				 */
				y = getDiffMonth(dt1, dt0);

			}
			Calendar cal = Calendar.getInstance();
			cal.setTime(dt0);
			int dayOfMonthDate = cal.get(Calendar.DAY_OF_MONTH);
			cal.setTime(dt1);
			int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
			if (dayOfMonthBirth > dayOfMonthDate) {
				y--;
			}
		} catch (Exception e) {
			log.error(e.getMessage());

		}
		return y;

	}

	/*
	 * public static int getMonths(Date begin, Date end) { int y = 0; try { if
	 * (end.getTime() >= begin.getTime()) { long l = end.getTime() -
	 * begin.getTime(); long t = l / (3600 * 24 * 1000); y = (int) (t / 30); } }
	 * catch (Exception e) { e.printStackTrace(); } return y; }
	 */
	public static int getDiffMonth(Date beginDate, Date endDate) {
		Calendar calbegin = Calendar.getInstance();
		Calendar calend = Calendar.getInstance();
		calbegin.setTime(beginDate);
		calend.setTime(endDate);
		int m_begin = calbegin.get(Calendar.MONTH) + 1;
		int m_end = calend.get(Calendar.MONTH) + 1;
		int checkmonth = m_end - m_begin
				+ (calend.get(Calendar.YEAR) - calbegin.get(Calendar.YEAR))
				* 12;
		return checkmonth;
	}

	// 计算年龄
	@SuppressWarnings({ "rawtypes" })
	public static Map getNowAge(String birth, String date) {
		try {
			Date birthday = null;
			Date nowDate = null;
			if (StringUtils.isNotBlank(birth)) {
				birthday = DateUtils.parseDate(birth,
						new String[] { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss"  });
			}
			if (StringUtils.isNotBlank(date)) {
				nowDate = DateUtils.parseDate(date, new String[] {
						"yyyy-MM-dd", "yyyy-MM-dd HH:mm" });
			}
			return getNowAge(birthday, nowDate);
		} catch (ParseException e) {
			return null;
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Map<String, Object> getNowAge(Date birth, Date date) {
		int day = 0, month = 0, year = 0;long days = 0;
		String str = "";
		if (null != birth) {
			Calendar birthday = Calendar.getInstance();
			birthday.setTime(birth);
			Calendar now = Calendar.getInstance();
			if (null != date) {
				now.setTime(date);
			}
			// 忽略时分秒
			birthday.set(Calendar.HOUR_OF_DAY, 0);
			birthday.set(Calendar.MINUTE, 0);
			birthday.set(Calendar.SECOND, 0);
			now.set(Calendar.HOUR_OF_DAY, 0);
			now.set(Calendar.MINUTE, 0);
			now.set(Calendar.SECOND, 0);
			if (now.compareTo(birthday) < 0) {
				// 避免数据库中错误数据影响查询的情况，有些儿童体检时间比出生时间还小
				now.setTime(new Date());
				// throw new IllegalArgumentException("报错了，出生日期大于当前日期!");
			}
			day = now.get(Calendar.DAY_OF_MONTH)
					- birthday.get(Calendar.DAY_OF_MONTH);
			month = now.get(Calendar.MONTH) - birthday.get(Calendar.MONTH);
			year = now.get(Calendar.YEAR) - birthday.get(Calendar.YEAR);
			// 按照减法原理，先day相减，不够向month借；然后month相减，不够向year借；最后year相减。
			if (day < 0) {
				month -= 1;
				// birth 距离多少天月底 + date 天数
				day = birthday.getActualMaximum(Calendar.DAY_OF_MONTH) + day;
			}
			if (month < 0) {
				month = (month + 12) % 12;
				year--;
			}
			if (year > 0) {
				str += year + "岁";
			}
			if (month > 0) {
				str += month + "月";
			}
			str += day + "天";

			long sec1 = now.getTimeInMillis();
			long sec2 = birthday.getTimeInMillis();
			days = Long.parseLong(Long.toString((sec1 - sec2) / 1000 / 60
					/ 60 / 24));

		}

		Map m = new HashMap();
		m.put("currentAge", str);
		m.put("monthAge", month + 12 * year);
		m.put("yearAge", year);
		m.put("dayAge", days);
		int totalMonth = 0;
		totalMonth=year*12+month;
		m.put("totalMonthAge", totalMonth);//用于新增体格检查使用
		int totalDay = 0;
		totalDay =totalMonth*30+day;
		m.put("totalDayAge", totalDay);//用于新增体格检查使用
		return m;

	}

	public static int getDayOfWeek() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.DAY_OF_WEEK);
	}

	public static int getDayOfMonth() {
		Calendar cal = Calendar.getInstance();
		int end = cal.get(Calendar.DAY_OF_MONTH);
		return end - 1;
	}

	public static int getDayOfMonth(int amount) {
		Calendar cal = Calendar.getInstance();
		Date begin = cal.getTime();
		cal.add(Calendar.MONTH, amount);
		Date end = cal.getTime();
		long l = begin.getTime() - end.getTime();
		int t = (int) (l / (3600 * 24 * 1000));
		return t;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map getWeek(String d) {
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(format1.parse(d));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int day = cal.get(Calendar.DAY_OF_WEEK);
		String[] weekday = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		String isZm = "";
		if (day == 1 || day == 7) {
			isZm = "1";
		} else {
			isZm = "0";
		}
		Map result = new HashMap();
		result.put("isZm", isZm);
		result.put("week", weekday[day - 1]);
		return result;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map getWeekDay(String begin, String end) {
		long days = getDifferDays(begin, end);
		int week = (int) (days / 7);
		int day = (int) (days % 7);
		Map result = new HashMap();
		result.put("week", week);
		result.put("day", day);
		return result;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map getWeekDay(Date begin, Date end) {
		long days = getDifferDays(begin, end);
		int week = (int) (days / 7);
		int day = (int) (days % 7);
		Map result = new HashMap();
		result.put("week", week);
		result.put("day", day);
		return result;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map getWeekDay(Date begin) {
		long days = getDays(begin);
		int week = (int) (days / 7);
		int day = (int) (days % 7);
		Map result = new HashMap();
		result.put("week", week);
		result.put("day", day);
		return result;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map getWeekDay(String begin) {
		long days = getDays(begin);
		int week = (int) (days / 7);
		int day = (int) (days % 7);
		Map result = new HashMap();
		result.put("week", week);
		result.put("day", day);
		return result;
	}

	/*
	 * 获取n天后的日期
	 */
	public static String getDateAfterDays(String date, int n) {
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(format1.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.add(Calendar.DATE, n);
		return format1.format(c.getTime());
	}

	private static int getYear(Calendar calendar) {
		return calendar.get(Calendar.YEAR);
	}

	private static int getMonth(Calendar calendar) {
		return calendar.get(Calendar.MONTH) + 1;
	}

	private static int getDate(Calendar calendar) {
		return calendar.get(Calendar.DATE);
	}

	private static int getHour(Calendar calendar) {
		return calendar.get(Calendar.HOUR_OF_DAY);
	}

	private static int getMinute(Calendar calendar) {
		return calendar.get(Calendar.MINUTE);
	}

	private static int getSecond(Calendar calendar) {
		return calendar.get(Calendar.SECOND);
	}

	private static Calendar getCalendar() {
		return Calendar.getInstance();
	}

	private static int getDateField(Date date, int field) {
		Calendar c = getCalendar();
		c.setTime(date);
		return c.get(field);
	}

	/**
	 * 获得系统日期(年/月/日) 参数 ： 无 返回值： 格式日期：年/月/日
	 **/
	public static String getToday() {
		String strReturn = "";
		int intYear = Calendar.getInstance().get(Calendar.YEAR);
		int intMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
		int intDate = Calendar.getInstance().get(Calendar.DATE);
		strReturn = "" + intYear;

		if (intMonth < 10)
			strReturn += "-" + "0" + intMonth;
		else
			strReturn += "-" + intMonth;

		if (intDate < 10)
			strReturn += "-" + "0" + intDate;
		else
			strReturn += "-" + intDate;
		return strReturn;
	}

	// 获取当前日期
	public static Date getNowDate() {
		Calendar calendar = getCalendar();
		return calendar.getTime();
	}

	/**
	 * 获得系统时间(yyyy-MM-dd HH:mm:ss)
	 **/
	public static String getNowTime() {
		return format2.format(new Date());
	}

	/**
	 * 获得系统日期(yyyy-MM-dd)
	 */
	public static String getTodayStr() {
		return format1.format(new Date());
	}
	
	/**
	 * 获得系统日期(yyyyMMdd)
	 */
	public static String getTodayStr2() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		return dateFormat.format(new Date());
	}

	public static String getTodayStr(String format) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(new Date());
	}

	/**
	 * 判断一个日期是否在两个日期之间 包括当前日期。
	 */
	public static boolean innerData(String beginDate, String endDate,
			String middleDate) {
		boolean flag = false;
		if (middleDate.equals(beginDate) || middleDate.equals(endDate)) {
			flag = true;
		}
		try {
			Date bdate = format1.parse(beginDate);
			Date edate = format1.parse(endDate);
			Date mdate = format1.parse(middleDate);
			if (bdate.before(mdate) && edate.after(mdate)) {
				flag = true;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return flag;
	}

	public static String getDateBeforYear(Date datas, int n) {
		String sdatas = format1.format(datas);
		String[] dss = sdatas.split("-");
		int year = Integer.parseInt(dss[0]);
		return (year - n) + "-" + dss[1] + "-" + dss[2];
	}

	/**
	 * 当日期格式为“2011-09-10 20:09:19”时用此方法（用第一个日期减去第二个） 返回毫秒
	 * @param end
	 * @param begin
	 * @return2011/09/10
	 */
	public static long diffDays(Date end, Date begin) {
		long between = 0;
		try {
			between = (end.getTime() - begin.getTime());// 得到两者的毫秒数
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return between;
	}

	public static String appendEndTimeOfDate(String date) {
		if (StringUtils.isNotBlank(date)) {
			if (date.length() >= 10) {
				return date = date.substring(0, 10) + " 23:59:59";
			}
		}
		return "";
	}
	
	/**
	 * 获得第N个月的最后一天日期（往当前往后的日期计算）
	 * @param term 期数（指定期数，如：第3期）
	 * @return
	 */
    public static int getNextMonthEnd(Date date,int term) {
    	int str = 0;
    	try {
    		Calendar lastDate = Calendar.getInstance();
    		lastDate.setTime(date);
    		lastDate.add(Calendar.MONTH, term);// 加的月数
    		lastDate.set(Calendar.DATE, 1);// 把日期设置为当月第一天
    		lastDate.roll(Calendar.DATE, -1);// 日期回滚一天，也就是本月最后一天
    		SimpleDateFormat sdf = new SimpleDateFormat("dd");
    		str = Integer.parseInt(sdf.format(lastDate.getTime()));
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return str;
    }
    
    
    /**
     * 获取第N期的日期（往当前往后的日期计算）
     * @param stDate 用来计算后面日期的根据
     * @param term 期数（指定期数，如：第3期）
     * @return
     * 规则：如果当前取的日期 N的月份里没有，则取 N的月份里的最后一天（如：当前为 2018-11-30 
     * 三个月后为   2019-2-28（2月份只有28天））
     */
    public static String getNDates(String stDate,int term){
    	String NDates = null;
    	try {
    		Date date = parseDate(stDate);
    		//第N期的最后一天日期
    		int nextMonthEnd = getNextMonthEnd(date,term);
    		//获取依据的时间日期月份
    		SimpleDateFormat formatMonth = new SimpleDateFormat("MM");
    		//获取依据的时间月份
    		String nowMonth = formatMonth.format(date).toString();
    		//System.out.println("依据的时间月份:"+nowMonth);
    		//获取依据的时间日期
    		SimpleDateFormat formatDate = new SimpleDateFormat("dd");
    		String nowDate = formatDate.format(date).toString();
    		//System.out.println("依据的时间日期:"+nowDate);
    		//获取N个月后的年份
    		Calendar calendar = Calendar.getInstance();
    		calendar.setTime(date);
    		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + term);
    		date = calendar.getTime();
    		SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
    		String nYear = yearFormat.format(date);
    		//System.out.println("N个月后的年份:"+nYear);
    		//第N期的月份
    		int month = Integer.parseInt(nowMonth)+term;
    		//负数
    		if(month==0){
    			month = 12;
    		}else if(month==-1){
    			month = 11;
    		}else if(month==-2){
    			month = 10;
    		}else if(month==-3){
    			month = 9;
    		}else if(month==-4){
    			month = 8;
    		}else if(month==-5){
    			month = 7;
    		}else if(month==-6){
    			month = 6;
    		}else if(month==-7){
    			month = 5;
    		}else if(month==-8){
    			month = 4;
    		}else if(month==-9){
    			month = 3;
    		}else if(month==-10){
    			month = 2;
    		}else if(month==-11){
    			month = 1;
    		}else if(month==-12){
    			month = 12;
    		}else if(month==-13){
    			month = 11;
    		}else if(month==-14){
    			month = 10;
    		}else if(month==-15){
    			month = 9;
    		}else if(month==-16){
    			month = 8;
    		}else if(month==-17){
    			month = 7;
    		}else if(month==-18){
    			month = 6;
    		}else if(month==-19){
    			month = 5;
    		}else if(month==-20){
    			month = 4;
    		}else if(month==-21){
    			month = 3;
    		}else if(month==-22){
    			month = 2;
    		}else if(month==-23){
    			month = 1;
    		}
    		//正数
    		else if(month==13){
    			month = 1;
    		}else if(month==14){
    			month = 2;
    		}else if(month==15){
    			month = 3;
    		}else if(month==16){
    			month = 4;
    		}else if(month==17){
    			month = 5;
    		}else if(month==18){
    			month = 6;
    		}else if(month==19){
    			month = 7;
    		}else if(month==20){
    			month = 8;
    		}else if(month==21){
    			month = 9;
    		}else if(month==22){
    			month = 10;
    		}else if(month==23){
    			month = 11;
    		}else if(month==24){
    			month = 12;
    		}else if(month==25){
    			month = 1;
    		}else if(month==26){
    			month = 2;
    		}else if(month==27){
    			month = 3;
    		}else if(month==28){
    			month = 4;
    		}else if(month==29){
    			month = 5;
    		}else if(month==30){
    			month = 6;
    		}else if(month==31){
    			month = 7;
    		}else if(month==32){
    			month = 8;
    		}else if(month==33){
    			month = 9;
    		}else if(month==34){
    			month = 10;
    		}else if(month==35){
    			month = 11;
    		}else if(month==36){
    			month = 12;
    		}
    		if(Integer.parseInt(nowDate) > nextMonthEnd){
    			nowDate = nextMonthEnd+"";
    		}
    		NDates = nYear+"-"+month+"-"+nowDate;
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return NDates;
    }
    
    /**
     * 获取N个月后当前的日期，如果是
     * @param inputDate
     * @param number
     * @return
     * 	规则：如果当前取的日期 N的月份里没有，则取 N的月份里的最后一天（如：当前为 2018-11-30 
     * 三个月后为   2019-2-28（2月份只有28天））
     */
    public static String  getAfterMonth(String inputDate,int number) {
        Calendar c = Calendar.getInstance();//获得一个日历的实例   
        Date date = null;
        try{   
            date = format1.parse(inputDate);//初始日期
        }catch(Exception e){  
        	e.printStackTrace();
        }   
        c.setTime(date);//设置日历时间   
        c.add(Calendar.MONTH,number);//在日历的月份上增加N个月
        String strDate = format1.format(c.getTime());//得到你想要得N个月后的日期
        return strDate;
    }
    
    /**
     * 计算两个日期相差的月数
     * @param beginDate 开始日期
     * @param endDate	结束日期
     * @return
     * 描述：
     * 根据结束日期日往前推，每个月的结束日期日为一期，第一期的开始日期到结束日期不够一个月，则算一期
     * 
     * 注：
     * 1、如果开始日期的天(如：2018-12-15的 15) 小于 结束日期的天(如：2019-2-25 的25)，则第一期为 2018-12-15 ~ 2018-12-25，第二期为 2018-12-25 ~ 2019-1-25，第三期为  2019-1-25 ~ 2019-2-25 
     * 2、如果开始日期的天(如：2018-12-25的 25) 大于 结束日期的天(如：2019-2-15 的15)，则第一期为 2018-11-25 ~ 2018-12-15，第二期为 2018-12-15 ~ 2019-1-15，第三期为  2019-1-15 ~ 2019-2-15
     */
    public static double getDifferMonths(String beginDate,String endDate){
    	try {
    		SimpleDateFormat formatDate = new SimpleDateFormat("dd");
    		//判断第一期的结束日期是在开始日期前面还是后面
    		//获取结束日期的天
    		String endDateDay = formatDate.format(parseDate(endDate)).toString();
    		//获取开始日期的天
    		String beginDateDay = formatDate.format(parseDate(beginDate)).toString();
    		if(StringUtils.isNotBlank(beginDateDay) && StringUtils.isNotBlank(endDateDay)){
    			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
    			Calendar bef = Calendar.getInstance();
    			Calendar aft = Calendar.getInstance();
    			bef.setTime(sdf.parse(beginDate));
    			aft.setTime(sdf.parse(endDate));
    			int result = aft.get(Calendar.MONTH) - bef.get(Calendar.MONTH);
    			int month = (aft.get(Calendar.YEAR) - bef.get(Calendar.YEAR)) * 12;
    			int months = Math.abs(month + result);
    			if(Integer.parseInt(beginDateDay) < Integer.parseInt(endDateDay)){
    				months = months+1;
    			}
    			return months;
    		}
		} catch (Exception e) {
			e.printStackTrace();
		}
        return 0;
    }
    
    /**
     * 根据开始和结束日期，计算出第一期的相差天数（资产放款日到起息日的天数）
     * @param beginDate	(起息日)
     * @param endDate	(还款日)
     * @return
     */
    public static double getFirstMonthDifferDays(String beginDate,String endDate){
    	try {
    		if(StringUtils.isNotBlank(beginDate) && StringUtils.isNotBlank(endDate)){
    			//计算出两个日期相差期（月）数
    			double differMonths = getDifferMonths(beginDate,endDate);
    			//把月份往前推（即负数）
    			differMonths = new BigDecimal(differMonths).divide(new BigDecimal(-1), 2, BigDecimal.ROUND_DOWN).doubleValue();
    			//算出几个月前(或后)日期
    			String beforeDay = getNDates(endDate, (int)differMonths);
    			int days = 0;
    			//判断两日期是否为同一天
    			boolean flag = DateUtils.isSameDay(parseDate(beginDate), parseDate(beforeDay));
    			if(!flag){//不是同一天
        			Date bt = parseDate(beginDate); 
        			Date et = parseDate(beforeDay); 
    				if (bt.after(et)){
	    				days = Integer.parseInt(getDifferDays(beforeDay,beginDate)+"");
	    			}else{
	    				days = Integer.parseInt(getDifferDays(beginDate,beforeDay)+"");
	    			}
    			}
    			return days;
    		}
		} catch (Exception e) {
			e.printStackTrace();
		}
        return 0;
    }
    
    /**
     * 第一期实际天数（起息日到第一期还款日的天数）
     * @param beginDate
     * @param endDate
     * @return
     */
    public static double getFirstMonthClearDays(String beginDate,String endDate){
    	try {
    		if(StringUtils.isNotBlank(beginDate) && StringUtils.isNotBlank(endDate)){
    			//计算出两个日期相差期（月）数
    			double differMonths = getDifferMonths(beginDate,endDate)+1;
    			//把月份往前推（即负数）
    			differMonths = new BigDecimal(differMonths).divide(new BigDecimal(-1), 2, BigDecimal.ROUND_DOWN).doubleValue();
    			//算出几个月前(或后)日期
    			String beforeDay = getNDates(endDate, (int)differMonths);
    			int days = 0;
    			//判断两日期是否为同一天
    			boolean flag = DateUtils.isSameDay(parseDate(beginDate), parseDate(beforeDay));
    			if(!flag){//不是同一天
        			Date bt = parseDate(beginDate); 
        			Date et = parseDate(beforeDay); 
    				if (bt.before(et)){
	    				days = Integer.parseInt(getDifferDays(beforeDay,beginDate)+"");
	    			}else{
	    				beforeDay = getNDates(endDate, (int)differMonths+2);
	    				days = Integer.parseInt(getDifferDays(beginDate,beforeDay)+"");
	    			}
    			}
    			return days;
    		}
		} catch (Exception e) {
			e.printStackTrace();
		}
        return 0;
    }

    /**
     * 判断该日期是否是该月的第一天
     * @param date 需要判断的日期
     * @return
     */
    public static boolean isFirstDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        System.out.println(calendar.get(Calendar.MONTH));
        return calendar.get(Calendar.DAY_OF_MONTH) == 1;
    }

    /**
	 * 获取该月的最后一天
	 * @param date 
	 * @return
	 */
    public static String getDateLastDay(String date) {
    	Date dates = parseDate(date);
    	//获取依据的时间日期月份
		SimpleDateFormat formatMonth = new SimpleDateFormat("MM");
		//获取时间年份
		SimpleDateFormat formatYear = new SimpleDateFormat("yyyy");
		int year=Integer.parseInt(formatYear.format(dates).toString());
		//获取时间月份
		int month=Integer.parseInt(formatMonth.format(dates).toString());
		Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR,year);
        //设置月份
        cal.set(Calendar.MONTH, month-1);
        //获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        //格式化日期
        String lastDayOfMonth = format1.format(cal.getTime());
        return lastDayOfMonth;
    }
	
    /**
    * 判断给定日期是否为月末的一天
    *
    * @param date
    * @return true:是|false:不是
    */
	public static boolean isLastDayOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DATE, (calendar.get(Calendar.DATE) + 1));
		if (calendar.get(Calendar.DAY_OF_MONTH) == 1) {
			return true;
		}
		return false;
	}

	/**
	 * 判断选择的日期是否是本周
	 *
	 * @param time
	 * @return true:是|false:不是
	 */
	public static boolean isThisWeek(long time)
	{
		Calendar calendar = Calendar.getInstance();
		int currentWeek = calendar.get(Calendar.WEEK_OF_YEAR);
		calendar.setTime(new Date(time));
		int paramWeek = calendar.get(Calendar.WEEK_OF_YEAR);
		if(paramWeek==currentWeek){
			return true;
		}
		return false;
	}

	/**
	 * 判断选择的日期是否是今天
	 *
	 * @param time
	 * @return true:是|false:不是
	 */
	public static boolean isToday(long time)
	{
		return isThisTime(time,"yyyy-MM-dd");
	}
	//判断选择的日期是否是本月
	public static boolean isThisMonth(long time)
	{
		return isThisTime(time,"yyyy-MM");
	}
	private static boolean isThisTime(long time,String pattern) {
		Date date = new Date(time);
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		String param = sdf.format(date);//参数时间
		String now = sdf.format(new Date());//当前时间
		if(param.equals(now)){
			return true;
		}
		return false;
	}

	/**
	 * 当前时间加一天
	 *
	 * @param endTime
	 * @return true:是|false:不是
	 */
	public static Date addOneDay(Date endTime) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(endTime);
		cal.add(Calendar.DATE, 1);
		endTime = cal.getTime();
		return endTime;
	}

	/**
	 * 当前时间加一天
	 *
	 * @param endTime
	 * @return true:是|false:不是
	 */
	public static LocalDateTime addOneDay(LocalDateTime endTime) {
		return endTime.plusDays(1);
	}


	public static void main(String[] args) {

		/*String interestStartDate = "2018-12-20";
		String repayDate = DateTimeUtils.getAfterMonth(interestStartDate, 4);
		System.out.println("N期后的时间： " + repayDate);*/

		// 两时间差天数
		//String interestEndDate = "2019-3-10";
		String interestStartDate = "2018-2-25";
		String interestEndDate = "2019-6-29";
		int intTerm = 3;
		for(int i=1;i<=intTerm;i++){
			System.out.println("第"+i+"期还款日期为："+ DateTimeUtils.getNDates(interestEndDate,i-intTerm));
		 }
		/*Date bt=parseDate(interestStartDate); 
		Date et=parseDate(interestEndDate); 
		if (bt.before(et)){
			//表示bt小于et 
			System.out.println("正常");
			long a = getDifferDays(parseDate(interestStartDate), parseDate(interestEndDate));
			System.out.println("两日期相差： " + a + "天");
			double b = getDifferMonths(interestStartDate,interestEndDate);
			System.out.println("两日期相差： " + b + "月");
			double days = getFirstMonthDifferDays(interestStartDate,interestEndDate);
			System.out.println("第一期差： " + days + "天");
			double clearDays = getFirstMonthClearDays(interestStartDate,interestEndDate);
			System.out.println("第一期实际： " + clearDays + "天");
		}else{ 
			System.out.println("反常");
			System.out.println("两日期相差0天");	
			System.out.println("两日期相差0月");	
		}*/
		
	}
    
}
