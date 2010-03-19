/**
 * <p>��Ŀ���ƣ�����ģ��</p>
 * <p>��Ȩ���� (c) 2006 ����ȫ����Ϣ�Ƽ����޹�˾</p>
 * <p>���ߣ�</p>
 * <p>�汾��1.0</p>
 * <p>���ڣ�2006-7-14</p>
 * <p>���£�</p>
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
	 * ����: �������ַ�������ģʽ�ַ���(Constant.DATE_PATTERN_STRING_YEAR) ת��ΪDate����
	 * 
	 * @param dateStr
	 *            �����ַ���
	 * @return ת���ɹ��򷵻������ַ�����ӦDateֵ��ת�����ɹ��򷵻�null
	 */
	public static Date toDateFromYearPattern(String dateStr) {
		return toDateFromPattern(DATE_PATTERN_STRING_YEAR, dateStr);
	}

	/**
	 * ����: �������ַ������꣬��ģʽ�ַ���(Constant.DATE_PATTERN_STRING_YEAR_MONTH) ת��ΪDate����
	 * 
	 * @param dateStr
	 *            �����ַ���
	 * @return ת���ɹ��򷵻������ַ�����ӦDateֵ��ת�����ɹ��򷵻�null
	 */
	public static Date toDateFromYearMonthPattern(String dateStr) {
		return toDateFromPattern(DATE_PATTERN_STRING_YEAR_MONTH, dateStr);
	}

	/**
	 * ����: �������ַ������꣬�£���ģʽ�ַ���(Constant.DATE_PATTERN_STRING_YEAR_MONTH_DAY)
	 * ת��ΪDate����
	 * 
	 * @param dateStr
	 *            �����ַ���
	 * @return ת���ɹ��򷵻������ַ�����ӦDateֵ��ת�����ɹ��򷵻�null
	 */
	public static Date toDateFromYearMonthDayPattern(String dateStr) {
		return toDateFromPattern(DATE_PATTERN_STRING_YEAR_MONTH_DAY, dateStr);
	}

	/**
	 * ����:
	 * �������ַ������꣬�£��գ�Сʱ������ģʽ�ַ���(Constant.DATE_PATTERN_STRING_YEAR_MONTH_DAY_HOUR_MIN)
	 * ת��ΪDate����
	 * 
	 * @param dateStr
	 *            �����ַ���
	 * @return ת���ɹ��򷵻������ַ�����ӦDateֵ��ת�����ɹ��򷵻�null
	 */
	public static Date toDateFromYearMonthDayHourMinPattern(String dateStr) {
		return toDateFromPattern(DATE_PATTERN_STRING_YEAR_MONTH_DAY_HOUR_MIN,
				dateStr);
	}

	/**
	 * ����: �������ַ�����ָ���������ַ���ģʽת��ΪDate����
	 * 
	 * @param pattern
	 *            �����ַ���ģʽ
	 * @param dateStr
	 *            �����ַ���
	 * @return ת���ɹ��򷵻������ַ�����ӦDateֵ��ת�����ɹ��򷵻�null
	 */
	public static Date toDateFromPattern(String pattern, String dateStr) {
		try {
			return (new SimpleDateFormat(pattern)).parse(dateStr);
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * ����: �����ڰ�ָ��ģʽ��ת��Ϊ�ַ���
	 * 
	 * @param pattern
	 *            ģʽ��
	 * @param date
	 *            ����
	 * @return ת���ɹ�������Ӧ�ַ�����ת��ʧ�ܻ����쳣����null
	 */
	public static String toStringInPattern(String pattern, Date date) {
		try {
			return new SimpleDateFormat(pattern).format(date);
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * ����: ������ת��Ϊ��ģʽ(DATE_PATTERN_STRING_YEAR)���ַ���
	 * 
	 * @param date
	 *            ����
	 * @return ��ģʽ���ַ�������ת��ʧ�ܻ����쳣�򷵻�null
	 */
	public static String toStringInYearPattern(Date date) {
		return toStringInPattern(DATE_PATTERN_STRING_YEAR, date);
	}

	/**
	 * ����: ������ת��Ϊ�꣬��ģʽ(DATE_PATTERN_STRING_YEAR_MONTH)���ַ���
	 * 
	 * @param date
	 *            ����
	 * @return �꣬��ģʽ���ַ�������ת��ʧ�ܻ����쳣�򷵻�null
	 */
	public static String toStringInYearMonthPattern(Date date) {
		return toStringInPattern(DATE_PATTERN_STRING_YEAR_MONTH, date);
	}

	/**
	 * ����: ������ת��Ϊ�꣬�£���ģʽ(DATE_PATTERN_STRING_YEAR_MONTH_DAY)���ַ���
	 * 
	 * @param date
	 *            ����
	 * @return �꣬�£���ģʽ���ַ�������ת��ʧ�ܻ����쳣�򷵻�null
	 */
	public static String toStringInYearMonthDayPattern(Date date) {
		return toStringInPattern(DATE_PATTERN_STRING_YEAR_MONTH_DAY, date);
	}

	/**
	 * ����: ������ת��Ϊ�꣬�£��գ�Сʱ������ģʽ(DATE_PATTERN_STRING_YEAR_MONTH_DAY_HOUR_MIN)���ַ���
	 * 
	 * @param date
	 *            ����
	 * @return �꣬�£���ģʽ���ַ�������ת��ʧ�ܻ����쳣�򷵻�null
	 */
	public static String toStringInYearMonthDayHourMinPattern(Date date) {
		return toStringInPattern(DATE_PATTERN_STRING_YEAR_MONTH_DAY_HOUR_MIN,
				date);
	}

	/**
	 * ����: ��ȡ��ǰ���ڣ����ھ�ȷ���գ���ʱ���֣��룬��������Ϊ0
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
	 * ����: ��ȡ��ǰ���ڣ����ھ�ȷ��ʱ�����֣��룬��������Ϊ0
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
	 * ����: ��ȡ��ǰ���ڣ����ھ�ȷ���֣����룬��������Ϊ0
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
	 * ����: ��ȡ��ǰ���ڣ����ھ�ȷ���룬����������Ϊ0
	 * 
	 * @return
	 */
	public static Date getNowPreciseToSecond() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * ����: Date+int = �µ�Date��
	 * 
	 * @param inDate
	 *            ����ԭ����
	 * @param AddDateInt
	 *            ����Ҫ�Ӽ�������
	 * @return Date :ת��֮���Date
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
	 * ����: �õ�����ʱ�䵱������һ��
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
	 * ����: �õ�����ʱ�䵱�µĵ�һ��
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
	 * ����: �õ�����ʱ�䵱�µ����һ��
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
	 * ����: ���ݴ�������ڵõ����ܵĵ�һ������
	 * 
	 * @param oriDate
	 * @return
	 */
	public static Date getFirstDayOfWeekByDate(Date oriDate) {
		Calendar cal = Calendar.getInstance();
		// ����һ�ܵ���ʼʱ��
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		cal.setTime(oriDate);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return cal.getTime();
	}

	/**
	 * ����: ���ݴ�������ڵõ����ܵ����һ������
	 * 
	 * @param oriDate
	 * @return
	 */
	public static Date getEndDayOfWeekByDate(Date oriDate) {
		Calendar cal = Calendar.getInstance();
		// ����һ�ܵ���ʼʱ��
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		cal.setTime(oriDate);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		return cal.getTime();
	}

	/**
	 * ����: ���ݴ����yyyy-ww(����)�����ظ��ܵĵ�һ�죨����һ��
	 * 
	 * @param oriDate
	 * @return
	 */
	public static Date getDateByYearWeek(String oriDate) {
		int loca = oriDate.indexOf("-");
		// �ֱ�ȡ���ꡢ��
		String year = oriDate.substring(0, loca);
		String week = oriDate.substring(loca + 1, oriDate.length());
		Calendar cal = Calendar.getInstance();
		// ����һ�ܵ���ʼʱ��
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		// ����һ���п�ʼ����Ҫ��������������
		cal.setMinimalDaysInFirstWeek(4);
		// ����ʱ��Ϊ��ǰ���ڣ���ʽ��Сʱ�������ã�
		cal.setTime(getNowPreciseToDay());
		// �����ꡢ�ܵ���Calendar��
		cal.set(Calendar.YEAR, Integer.parseInt(year));
		cal.set(Calendar.WEEK_OF_YEAR, Integer.parseInt(week));
		// ��Ϊȡ����ֵΪһ�ܵ����һ�죬��Ҫת�ɵ�һ��
		return DateUtil.getFirstDayOfWeekByDate(cal.getTime());
	}

	/**
	 * ����: ���ݴ������ڣ�����Ӽ����·ݣ�ȡ������ֵ
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
	 * ����: ���ݴ������ڵõ���ǰ������
	 * 
	 * @param oriDate
	 * @return
	 */
	public static String getYearWeekByDate(Date oriDate) {
		Calendar cal = Calendar.getInstance();
		// ����һ�ܵ���ʼʱ��
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		// ����һ���п�ʼ����Ҫ��������������
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
	 * ����: ������һ���µĽ���
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
	 * ����: Date+int = �µ�Date��
	 * 
	 * @param inDate
	 *            ����ԭʱ��
	 * @param AddDateInt
	 *            ����Ҫ�Ӽ��ķ���
	 * @return Date :ת��֮���Date
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
	 * ����: Date-Date = ֮ǰ�ķ�������
	 * 
	 * @param inDate
	 *            ��ǰʱ��
	 * @return Date :����ÿ15���Ӻ͵�ǰʱ������ķ�����
	 */
	public static int MinthDecr(Date inDate) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(inDate);
		int minth = cal.get(Calendar.MINUTE);
		return (int) (15 - Math.round((minth / 15.0 - minth / 15) * 15)) - 1;
	}

	/**
	 * ����: �Ƚ���������֮����������
	 * 
	 * @param d1
	 *            ��һ������
	 * @param d2
	 *            �ڶ���������
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
	 * ����: ��ʽ��ʱ��Ϊ�����ַ���:��2000-1-1Ϊ����������һ��һ��
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
		sb.append("��");
		if (getMidLen(str, pos1, pos2) == 1) {
			sb.append(formatDigit(str.charAt(5)) + "��");

			if (str.charAt(7) != '0') {
				if (getLastLen(str, pos2) == 1) {
					sb.append(formatDigit(str.charAt(7)) + "��");
				}
				if (getLastLen(str, pos2) == 2) {
					if (str.charAt(7) != '1' && str.charAt(8) != '0') {
						sb.append(formatDigit(str.charAt(7)) + "ʮ"
								+ formatDigit(str.charAt(8)) + "��");
					} else if (str.charAt(7) != '1' && str.charAt(8) == '0') {
						sb.append(formatDigit(str.charAt(7)) + "ʮ��");
					} else if (str.charAt(7) == '1' && str.charAt(8) != '0') {
						sb.append("ʮ" + formatDigit(str.charAt(8)) + "��");
					} else {
						sb.append("ʮ��");
					}
				}
			} else {
				sb.append(formatDigit(str.charAt(8)) + "��");
			}
		}
		if (getMidLen(str, pos1, pos2) == 2) {
			if (str.charAt(5) != '0' && str.charAt(6) != '0') {
				sb.append("ʮ" + formatDigit(str.charAt(6)) + "��");

				if (getLastLen(str, pos2) == 1) {
					sb.append(formatDigit(str.charAt(8)) + "��");
				}
				if (getLastLen(str, pos2) == 2) {
					if (str.charAt(8) != '0') {
						if (str.charAt(8) != '1' && str.charAt(9) != '0') {
							sb.append(formatDigit(str.charAt(8)) + "ʮ"
									+ formatDigit(str.charAt(9)) + "��");
						} else if (str.charAt(8) != '1' && str.charAt(9) == '0') {
							sb.append(formatDigit(str.charAt(8)) + "ʮ��");
						} else if (str.charAt(8) == '1' && str.charAt(9) != '0') {
							sb.append("ʮ" + formatDigit(str.charAt(9)) + "��");
						} else {
							sb.append("ʮ��");
						}
					} else {
						sb.append(formatDigit(str.charAt(9)) + "��");
					}
				}
			} else if (str.charAt(5) != '0' && str.charAt(6) == '0') {
				sb.append("ʮ��");
				if (getLastLen(str, pos2) == 1) {
					sb.append(formatDigit(str.charAt(8)) + "��");
				}
				if (getLastLen(str, pos2) == 2) {
					if (str.charAt(8) != '0') {
						if (str.charAt(8) != '1' && str.charAt(9) != '0') {
							sb.append(formatDigit(str.charAt(8)) + "ʮ"
									+ formatDigit(str.charAt(9)) + "��");
						} else if (str.charAt(8) != '1' && str.charAt(9) == '0') {
							sb.append(formatDigit(str.charAt(8)) + "ʮ��");
						} else if (str.charAt(8) == '1' && str.charAt(9) != '0') {
							sb.append("ʮ" + formatDigit(str.charAt(9)) + "��");
						} else {
							sb.append("ʮ��");
						}
					} else {
						sb.append(formatDigit(str.charAt(9)) + "��");
					}
				}
			} else {
				sb.append(formatDigit(str.charAt(6)) + "��");

				if (getLastLen(str, pos2) == 1) {
					sb.append(formatDigit(str.charAt(8)) + "��");
				}
				if (getLastLen(str, pos2) == 2) {
					if (str.charAt(8) != '0') {
						if (str.charAt(8) != '1' && str.charAt(9) != '0') {
							sb.append(formatDigit(str.charAt(8)) + "ʮ"
									+ formatDigit(str.charAt(9)) + "��");
						} else if (str.charAt(8) != '1' && str.charAt(9) == '0') {
							sb.append(formatDigit(str.charAt(8)) + "ʮ��");
						} else if (str.charAt(8) == '1' && str.charAt(9) != '0') {
							sb.append("ʮ" + formatDigit(str.charAt(9)) + "��");
						} else {
							sb.append("ʮ��");
						}
					} else {
						sb.append(formatDigit(str.charAt(9)) + "��");
					}
				}
			}
		}

		return sb.toString();
	}

	/**
	 * ��Դ�ַ����еİ��������ָ�ʽ��Ϊ����
	 * 
	 * @param sign
	 *            Դ�ַ����е��ַ�
	 * @return
	 */
	public static char formatDigit(char sign) {
		if (sign == '0')
			sign = '��';
		if (sign == '1')
			sign = 'һ';
		if (sign == '2')
			sign = '��';
		if (sign == '3')
			sign = '��';
		if (sign == '4')
			sign = '��';
		if (sign == '5')
			sign = '��';
		if (sign == '6')
			sign = '��';
		if (sign == '7')
			sign = '��';
		if (sign == '8')
			sign = '��';
		if (sign == '9')
			sign = '��';

		return sign;
	}

	/**
	 * ����·��ַ����ĳ���
	 * 
	 * @param str
	 *            ��ת����Դ�ַ���
	 * @param pos1
	 *            ��һ��''-''��λ��
	 * @param pos2
	 *            �ڶ���''-''��λ��
	 * @return
	 */
	public static int getMidLen(String str, int pos1, int pos2) {
		return str.substring(pos1 + 1, pos2).length();
	}

	/**
	 * ��������ַ����ĳ���
	 * 
	 * @param str
	 *            ��ת����Դ�ַ���
	 * @param pos2
	 *            �ڶ���''-''��λ��
	 * @return
	 */
	public static int getLastLen(String str, int pos2) {
		return str.substring(pos2 + 1).length();
	}
}