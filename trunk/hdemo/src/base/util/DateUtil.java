/**
 * <p>项目名称：公共模块</p>
 * <p>版权所有 (c) 2006 湖北全达信息科技有限公司</p>
 * <p>作者：</p>
 * <p>版本：1.0</p>
 * <p>日期：2006-7-14</p>
 * <p>更新：</p>
 */
package com.hxzy.base.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {

	public static final String DATE_PATTERN_STRING_YEAR = "yyyy";

	public static final String DATE_PATTERN_STRING_YEAR_MONTH = "yyyy-MM";

	public static final String DATE_PATTERN_STRING_YEAR_MONTH_DAY = "yyyy-MM-dd";

	public static final String DATE_PATTERN_STRING_YEAR_MONTH_DAY_HOUR = "yyyy-MM-dd HH";

	public static final String DATE_PATTERN_STRING_YEAR_MONTH_DAY_HOUR_MIN = "yyyy-MM-dd HH:mm";

	public static final String DATE_PATTERN_STRING_YEAR_MONTH_DAY1 = "yyyyMMdd";

	/**
	 * 描述: 将日期字符串按年模式字符串(Constant.DATE_PATTERN_STRING_YEAR) 转换为Date类型
	 * 
	 * @param dateStr
	 *            日期字符串
	 * @return 转换成功则返回日期字符串对应Date值，转换不成功则返回null
	 */
	public static Date toDateFromYearPattern(String dateStr) {
		return toDateFromPattern(DATE_PATTERN_STRING_YEAR, dateStr);
	}

	/**
	 * 描述: 将日期字符串按年，月模式字符串(Constant.DATE_PATTERN_STRING_YEAR_MONTH) 转换为Date类型
	 * 
	 * @param dateStr
	 *            日期字符串
	 * @return 转换成功则返回日期字符串对应Date值，转换不成功则返回null
	 */
	public static Date toDateFromYearMonthPattern(String dateStr) {
		return toDateFromPattern(DATE_PATTERN_STRING_YEAR_MONTH, dateStr);
	}

	/**
	 * 描述: 将日期字符串按年，月，日模式字符串(Constant.DATE_PATTERN_STRING_YEAR_MONTH_DAY)
	 * 转换为Date类型
	 * 
	 * @param dateStr
	 *            日期字符串
	 * @return 转换成功则返回日期字符串对应Date值，转换不成功则返回null
	 */
	public static Date toDateFromYearMonthDayPattern(String dateStr) {
		return toDateFromPattern(DATE_PATTERN_STRING_YEAR_MONTH_DAY, dateStr);
	}

	/**
	 * 描述:
	 * 将日期字符串按年，月，日，小时，分钟模式字符串(Constant.DATE_PATTERN_STRING_YEAR_MONTH_DAY_HOUR_MIN)
	 * 转换为Date类型
	 * 
	 * @param dateStr
	 *            日期字符串
	 * @return 转换成功则返回日期字符串对应Date值，转换不成功则返回null
	 */
	public static Date toDateFromYearMonthDayHourMinPattern(String dateStr) {
		return toDateFromPattern(DATE_PATTERN_STRING_YEAR_MONTH_DAY_HOUR_MIN,
				dateStr);
	}

	/**
	 * 描述: 将日期字符串按指定的日期字符串模式转换为Date类型
	 * 
	 * @param pattern
	 *            日期字符串模式
	 * @param dateStr
	 *            日期字符串
	 * @return 转换成功则返回日期字符串对应Date值，转换不成功则返回null
	 */
	public static Date toDateFromPattern(String pattern, String dateStr) {
		try {
			return (new SimpleDateFormat(pattern)).parse(dateStr);
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * 描述: 将日期按指定模式串转换为字符串
	 * 
	 * @param pattern
	 *            模式串
	 * @param date
	 *            日期
	 * @return 转换成功返回相应字符串，转换失败或发生异常返回null
	 */
	public static String toStringInPattern(String pattern, Date date) {
		try {
			return new SimpleDateFormat(pattern).format(date);
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * 描述: 将日期转换为年模式(DATE_PATTERN_STRING_YEAR)的字符串
	 * 
	 * @param date
	 *            日期
	 * @return 年模式的字符串，如转换失败或发生异常则返回null
	 */
	public static String toStringInYearPattern(Date date) {
		return toStringInPattern(DATE_PATTERN_STRING_YEAR, date);
	}

	/**
	 * 描述: 将日期转换为年，月模式(DATE_PATTERN_STRING_YEAR_MONTH)的字符串
	 * 
	 * @param date
	 *            日期
	 * @return 年，月模式的字符串，如转换失败或发生异常则返回null
	 */
	public static String toStringInYearMonthPattern(Date date) {
		return toStringInPattern(DATE_PATTERN_STRING_YEAR_MONTH, date);
	}

	/**
	 * 描述: 将日期转换为年，月，日模式(DATE_PATTERN_STRING_YEAR_MONTH_DAY)的字符串
	 * 
	 * @param date
	 *            日期
	 * @return 年，月，日模式的字符串，如转换失败或发生异常则返回null
	 */
	public static String toStringInYearMonthDayPattern(Date date) {
		return toStringInPattern(DATE_PATTERN_STRING_YEAR_MONTH_DAY, date);
	}

	/**
	 * 描述: 将日期转换为年，月，日，小时，分钟模式(DATE_PATTERN_STRING_YEAR_MONTH_DAY_HOUR_MIN)的字符串
	 * 
	 * @param date
	 *            日期
	 * @return 年，月，日模式的字符串，如转换失败或发生异常则返回null
	 */
	public static String toStringInYearMonthDayHourMinPattern(Date date) {
		return toStringInPattern(DATE_PATTERN_STRING_YEAR_MONTH_DAY_HOUR_MIN,
				date);
	}

	/**
	 * 描述: 获取当前日期，日期精确到日，即时，分，秒，毫秒设置为0
	 * 
	 * @return
	 */
	public static Date getNowPreciseToDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 描述: 获取当前日期，日期精确到时，即分，秒，毫秒设置为0
	 * 
	 * @return
	 */
	public static Date getNowPreciseToHour() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 描述: 获取当前日期，日期精确到分，即秒，毫秒设置为0
	 * 
	 * @return
	 */
	public static Date getNowPreciseToMin() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 描述: 获取当前日期，日期精确到秒，即毫秒设置为0
	 * 
	 * @return
	 */
	public static Date getNowPreciseToSecond() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 描述: Date+int = 新的Date。
	 * 
	 * @param inDate
	 *            输入原日期
	 * @param AddDateInt
	 *            输入要加减的天数
	 * @return Date :转换之后的Date
	 */
	public static Date DateAddInt(Date inDate, int AddDateInt) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(inDate);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		cal.set(year, month, day + AddDateInt);
		return cal.getTime();
	}

	/**
	 * 描述: 得到传入时间当年的最后一天
	 * 
	 * @param oriDate
	 * @return
	 */
	public static Date getEndDayOfYearByDate(Date oriDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(oriDate);
		cal.set(cal.get(Calendar.YEAR), Calendar.DECEMBER, 31);
		return cal.getTime();
	}

	/**
	 * 描述: 得到传入时间当月的第一天
	 * 
	 * @param oriDate
	 * @return
	 */
	public static Date getFirstDayOfMonthByDate(Date oriDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(oriDate);
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), 1);
		return cal.getTime();
	}

	/**
	 * 描述: 得到传入时间当月的最后一天
	 * 
	 * @param oriDate
	 * @return
	 */
	public static Date getEndDayOfMonthByDate(Date oriDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(oriDate);
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal
				.getActualMaximum(Calendar.DATE));
		return cal.getTime();
	}

	/**
	 * 描述: 根据传入的日期得到该周的第一天日期
	 * 
	 * @param oriDate
	 * @return
	 */
	public static Date getFirstDayOfWeekByDate(Date oriDate) {
		Calendar cal = Calendar.getInstance();
		// 设置一周的起始时间
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		cal.setTime(oriDate);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return cal.getTime();
	}

	/**
	 * 描述: 根据传入的日期得到该周的最后一天日期
	 * 
	 * @param oriDate
	 * @return
	 */
	public static Date getEndDayOfWeekByDate(Date oriDate) {
		Calendar cal = Calendar.getInstance();
		// 设置一周的起始时间
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		cal.setTime(oriDate);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		return cal.getTime();
	}

	/**
	 * 描述: 根据传入的yyyy-ww(周数)，返回该周的第一天（星期一）
	 * 
	 * @param oriDate
	 * @return
	 */
	public static Date getDateByYearWeek(String oriDate) {
		int loca = oriDate.indexOf("-");
		// 分别取出年、周
		String year = oriDate.substring(0, loca);
		String week = oriDate.substring(loca + 1, oriDate.length());
		Calendar cal = Calendar.getInstance();
		// 设置一周的起始时间
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		// 设置一年中开始周需要包括的最少天数
		cal.setMinimalDaysInFirstWeek(4);
		// 设置时间为当前日期（格式化小时、分钟用）
		cal.setTime(getNowPreciseToDay());
		// 设置年、周到擦Calendar中
		cal.set(Calendar.YEAR, Integer.parseInt(year));
		cal.set(Calendar.WEEK_OF_YEAR, Integer.parseInt(week));
		// 因为取到的值为一周的最后一天，需要转成第一天
		return DateUtil.getFirstDayOfWeekByDate(cal.getTime());
	}

	/**
	 * 描述: 根据传入日期，与相加减的月份，取新日期值
	 * 
	 * @param oriMonth
	 * @param monthInt
	 * @return
	 */
	public static Date getNewMonthByDate(Date oriMonth, int monthInt) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(oriMonth);
		int month = calendar.get(Calendar.MONTH);
		calendar.set(Calendar.MONTH, month + monthInt);
		return calendar.getTime();
	}

	public static void main(String[] args) {
//		String s1 = "2009-03-19";
//		String s2 = "2009-04-07";
//		Date d1 = toDateFromYearMonthDayPattern(s1);
//		Date d2 = toDateFromYearMonthDayPattern(s2);
//		System.out.println(dateDiff(d2, d1));
	}

	/**
	 * 描述: 根据传入日期得到当前的周数
	 * 
	 * @param oriDate
	 * @return
	 */
	public static String getYearWeekByDate(Date oriDate) {
		Calendar cal = Calendar.getInstance();
		// 设置一周的起始时间
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		// 设置一年中开始周需要包括的最少天数
		cal.setMinimalDaysInFirstWeek(4);
		cal.setTime(oriDate);
		int week = cal.get(Calendar.WEEK_OF_YEAR);
		int year = cal.get(Calendar.YEAR);
		String yearWeek = "";
		if (week < 10)
			yearWeek = year + "-0" + week;
		else
			yearWeek = year + "-" + week;
		return yearWeek;
	}

	/**
	 * 描述: 返回上一个月的今天
	 * 
	 * @return Date yyyy-MM-dd
	 */
	public static Date getDateByLastMonth() {
		Calendar calendar = new GregorianCalendar();
		int mouthInt = calendar.get(Calendar.MONTH);
		if (mouthInt == 0) {
			calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - 1);
			calendar.set(Calendar.MONTH, 11);
		} else
			calendar.set(Calendar.MONTH, mouthInt - 1);
		// calendar.roll(Calendar.DATE, calendar.get(Calendar.DATE));
		// Date thisTime = calendar.getTime();
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return calendar.getTime();
	}

	public static String getDateByMonth(int monthInt) {
		Calendar calendar = Calendar.getInstance();
		int month = calendar.get(Calendar.MONTH);
		calendar.set(Calendar.MONTH, month + monthInt);
		Date thisTime = calendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(thisTime);
	}

	public static String getDateByYear(int yearInt) {
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		calendar.set(Calendar.YEAR, year + yearInt);
		Date thisTime = calendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(thisTime);
	}

	public static Date getNewDateByYear(int yearInt) {
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		calendar.set(Calendar.YEAR, year + yearInt);
		return calendar.getTime();
	}

	/**
	 * 描述: Date+int = 新的Date。
	 * 
	 * @param inDate
	 *            输入原时间
	 * @param AddDateInt
	 *            输入要加减的分钟
	 * @return Date :转换之后的Date
	 */
	public static Date MinthAddInt(Date inDate, int addMinthInt) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(inDate);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int minth = cal.get(Calendar.MINUTE);
		cal.set(year, month, day, hour, minth - (addMinthInt), 0);
		return cal.getTime();
	}

	/**
	 * 描述: Date-Date = 之前的分钟数。
	 * 
	 * @param inDate
	 *            当前时间
	 * @return Date :计算每15分钟和当前时间所差的分钟数
	 */
	public static int MinthDecr(Date inDate) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(inDate);
		int minth = cal.get(Calendar.MINUTE);
		return (int) (15 - Math.round((minth / 15.0 - minth / 15) * 15)) - 1;
	}

	/**
	 * 描述: 比较两个日期之间相差的天数
	 * 
	 * @param d1
	 *            第一个日期
	 * @param d2
	 *            第二个日期限
	 * @return
	 */
	public static long dateDiff(Date d1, Date d2) {
		long daterange = d1.getTime() - d2.getTime();
		long time = 1000 * 3600 * 24; // A day in milliseconds
		return daterange / time;
	}

	public static long dateDiffMin(Date d1, Date d2) {
		long daterange = d1.getTime() - d2.getTime();
		long time = 1000 * 60; // A day in milliseconds
		return daterange / time;
	}

	/**
	 * 描述: 格式化时间为中文字符串:如2000-1-1为二〇〇〇年一月一日
	 * 
	 * @param str
	 * @return
	 */
	public static String formatChStr(String str) {
		StringBuffer sb = new StringBuffer();
		int pos1 = str.indexOf("-");
		int pos2 = str.lastIndexOf("-");

		for (int i = 0; i < 4; i++) {
			sb.append(formatDigit(str.charAt(i)));
		}
		sb.append("年");
		if (getMidLen(str, pos1, pos2) == 1) {
			sb.append(formatDigit(str.charAt(5)) + "月");

			if (str.charAt(7) != '0') {
				if (getLastLen(str, pos2) == 1) {
					sb.append(formatDigit(str.charAt(7)) + "日");
				}
				if (getLastLen(str, pos2) == 2) {
					if (str.charAt(7) != '1' && str.charAt(8) != '0') {
						sb.append(formatDigit(str.charAt(7)) + "十"
								+ formatDigit(str.charAt(8)) + "日");
					} else if (str.charAt(7) != '1' && str.charAt(8) == '0') {
						sb.append(formatDigit(str.charAt(7)) + "十日");
					} else if (str.charAt(7) == '1' && str.charAt(8) != '0') {
						sb.append("十" + formatDigit(str.charAt(8)) + "日");
					} else {
						sb.append("十日");
					}
				}
			} else {
				sb.append(formatDigit(str.charAt(8)) + "日");
			}
		}
		if (getMidLen(str, pos1, pos2) == 2) {
			if (str.charAt(5) != '0' && str.charAt(6) != '0') {
				sb.append("十" + formatDigit(str.charAt(6)) + "月");

				if (getLastLen(str, pos2) == 1) {
					sb.append(formatDigit(str.charAt(8)) + "日");
				}
				if (getLastLen(str, pos2) == 2) {
					if (str.charAt(8) != '0') {
						if (str.charAt(8) != '1' && str.charAt(9) != '0') {
							sb.append(formatDigit(str.charAt(8)) + "十"
									+ formatDigit(str.charAt(9)) + "日");
						} else if (str.charAt(8) != '1' && str.charAt(9) == '0') {
							sb.append(formatDigit(str.charAt(8)) + "十日");
						} else if (str.charAt(8) == '1' && str.charAt(9) != '0') {
							sb.append("十" + formatDigit(str.charAt(9)) + "日");
						} else {
							sb.append("十日");
						}
					} else {
						sb.append(formatDigit(str.charAt(9)) + "日");
					}
				}
			} else if (str.charAt(5) != '0' && str.charAt(6) == '0') {
				sb.append("十月");
				if (getLastLen(str, pos2) == 1) {
					sb.append(formatDigit(str.charAt(8)) + "日");
				}
				if (getLastLen(str, pos2) == 2) {
					if (str.charAt(8) != '0') {
						if (str.charAt(8) != '1' && str.charAt(9) != '0') {
							sb.append(formatDigit(str.charAt(8)) + "十"
									+ formatDigit(str.charAt(9)) + "日");
						} else if (str.charAt(8) != '1' && str.charAt(9) == '0') {
							sb.append(formatDigit(str.charAt(8)) + "十日");
						} else if (str.charAt(8) == '1' && str.charAt(9) != '0') {
							sb.append("十" + formatDigit(str.charAt(9)) + "日");
						} else {
							sb.append("十日");
						}
					} else {
						sb.append(formatDigit(str.charAt(9)) + "日");
					}
				}
			} else {
				sb.append(formatDigit(str.charAt(6)) + "月");

				if (getLastLen(str, pos2) == 1) {
					sb.append(formatDigit(str.charAt(8)) + "日");
				}
				if (getLastLen(str, pos2) == 2) {
					if (str.charAt(8) != '0') {
						if (str.charAt(8) != '1' && str.charAt(9) != '0') {
							sb.append(formatDigit(str.charAt(8)) + "十"
									+ formatDigit(str.charAt(9)) + "日");
						} else if (str.charAt(8) != '1' && str.charAt(9) == '0') {
							sb.append(formatDigit(str.charAt(8)) + "十日");
						} else if (str.charAt(8) == '1' && str.charAt(9) != '0') {
							sb.append("十" + formatDigit(str.charAt(9)) + "日");
						} else {
							sb.append("十日");
						}
					} else {
						sb.append(formatDigit(str.charAt(9)) + "日");
					}
				}
			}
		}

		return sb.toString();
	}

	/**
	 * 将源字符串中的阿拉伯数字格式化为汉字
	 * 
	 * @param sign
	 *            源字符串中的字符
	 * @return
	 */
	public static char formatDigit(char sign) {
		if (sign == '0')
			sign = '〇';
		if (sign == '1')
			sign = '一';
		if (sign == '2')
			sign = '二';
		if (sign == '3')
			sign = '三';
		if (sign == '4')
			sign = '四';
		if (sign == '5')
			sign = '五';
		if (sign == '6')
			sign = '六';
		if (sign == '7')
			sign = '七';
		if (sign == '8')
			sign = '八';
		if (sign == '9')
			sign = '九';

		return sign;
	}

	/**
	 * 获得月份字符串的长度
	 * 
	 * @param str
	 *            待转换的源字符串
	 * @param pos1
	 *            第一个''-''的位置
	 * @param pos2
	 *            第二个''-''的位置
	 * @return
	 */
	public static int getMidLen(String str, int pos1, int pos2) {
		return str.substring(pos1 + 1, pos2).length();
	}

	/**
	 * 获得日期字符串的长度
	 * 
	 * @param str
	 *            待转换的源字符串
	 * @param pos2
	 *            第二个''-''的位置
	 * @return
	 */
	public static int getLastLen(String str, int pos2) {
		return str.substring(pos2 + 1).length();
	}
}