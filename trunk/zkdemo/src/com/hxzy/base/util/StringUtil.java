
package com.hxzy.base.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.StringUtils;


public class StringUtil {

	/**
	 * 描述: 顺序号，用来产生唯一字符串
	 */
	private static Long internalSerialNo = new Long(0);

	/**
	 * 描述: 将字符串转化为Long类型，如无法转换或发生错误返回null
	 * 
	 * @param str
	 *            整形字符串
	 * @return 长整形类，如发生错误返回null
	 */
	public static Long stringToLong(String str) {
		if (str == null || "".equals(str.trim()))
			return null;
		try {
			return new Long(str.trim());
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * 描述: 将字符串转化为Dobule类，如无法转换或发生错误返回null
	 * 
	 * @param str
	 * @return
	 */
	public static Double stringToDouble(String str) {
		if (str == null || "".equals(str.trim()))
			return null;
		try {
			return new Double(str.trim());
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * 描述: 产生一个唯一的临时字符串 产生方法为当前的时间的毫秒数加上"_"加上顺序号
	 * 
	 * @return 字符串
	 */
	public static String getTmpStr() {
		String str = String.valueOf((new Date()).getTime());
		synchronized (internalSerialNo) {
			internalSerialNo = new Long(internalSerialNo.longValue() + 1);
			str = str + "_" + internalSerialNo.toString();
		}
		return str;
	}

	/**
	 * 描述: 格式化用户输入的字符串
	 * 
	 * @param str
	 *            用户输入的值
	 * @return
	 */
	public static String formatQueryStr(String str) {
		str = str.trim();
		if (str.indexOf("%") >= 0)
			str = replace(str, "%", "[%]");
		if (str.indexOf("'") >= 0)
			str = replace(str, "'", "''");
		return str;
	}

	/**
	 * 描述: 替换字符串
	 * 
	 * @param con
	 *            原始字符串
	 * @param tag
	 *            原替换的字符串
	 * @param rep
	 *            替换后的字符串
	 * @return
	 */
	public static String replace(String con, String tag, String rep) {
		int j = 0;
		int i = 0;
		String RETU = "";
		String temp = con;
		int tagc = tag.length();
		while (i < con.length())
			if (con.substring(i).startsWith(tag)) {
				temp = con.substring(j, i) + rep;
				RETU = RETU + temp;
				i += tagc;
				j = i;
			} else {
				i++;
			}
		RETU = RETU + con.substring(j);
		return RETU;
	}

	/**
	 * 描述: 将传入的正则表达式与传入的字符串进行匹配，如符合返回True，否之返回False
	 * 
	 * @param pattern
	 *            正则表达式
	 * @param str
	 *            比对的字符串
	 * @return boolean
	 */
	public static boolean isMatcherPattern(String pattern, String str) {
		boolean flag = false;
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(str);
		flag = m.matches();
		return flag;
	}

	/**
	 * 描述: 将传入的字符串按逗号分割成单个的字符串,并存入至List中
	 * 
	 * @param str
	 *            字符串
	 * @return
	 */
	public static ArrayList splicStr(String str) {
		str = str.trim();
		str = str.replace('，', ',');
		str = str.replace(' ', ',');
		ArrayList list = new ArrayList();
		if (str != null && !str.equals("")) {
			String temp1;
			String temp2;
			temp1 = str;
			if (str.indexOf(",") == -1)
				list.add(temp1);
			else {
				temp1 = temp1 + ",";
				if (str.indexOf(",") == 0)
					temp1 = temp1.substring(1);
				while (!temp1.equals("")) {
					int pos = temp1.indexOf(",");
					temp2 = temp1.substring(0, pos);
					if (!temp2.equals(""))
						list.add(temp2);
					temp1 = temp1.substring(pos + 1);
				}
			}
		}
		return list;
	}

	/**
	 * 描述: 截取字符串
	 * 
	 * @param string
	 *            被截取的字符串
	 * @param length
	 *            字符串长度
	 * @param endChar
	 *            结尾的符号
	 * @return 被修改后的字符串
	 */
	public static String cutString(String string, int length, String endChar) {
		StringBuffer newString = new StringBuffer();
		int newLength = 0;
		int endCharLength = 0;

		endCharLength = StringLength(endChar);

		for (int i = 0; i < string.length(); i++) {
			if ((int) string.charAt(i) > 127) {
				newLength += 2;
			} else {
				newLength++;
			}
			newString.append(string.charAt(i));
			if (newLength >= length - endCharLength - 2) {
				i = string.length();
				newString.append(endChar);
			}
		}

		return newString.toString();
	}


	/**
	 * 描述:测试字符串长度
	 * 
	 * @param string
	 *            被测试的字符串
	 * @return 字符串长度
	 */
	public static int StringLength(String string) {
		int length = 0;
		for (int i = 0; i < string.length(); i++) {
			if ((int) string.charAt(i) > 127) {
				length += 2;
			} else {
				length++;
			}
		}
		return length;
	}

	// 汉字编码转换
	public static String ConvToCn(String inString) {
		if (inString == null)
			return "";
		String outString = null;
		try {
			outString = new String(inString.getBytes("ISO8859_1"), "GBK");
		} catch (UnsupportedEncodingException e) {
			System.out.println(e.getMessage());
		}
		return outString;
	}

	// 插入汉字内编码转换
	public static String ConvToEn(String inString) {
		if (inString == null)
			return "";
		String outString = null;
		try {
			outString = new String(inString.getBytes("GBK"), "ISO8859_1");
		} catch (UnsupportedEncodingException e) {
			outString = null;
			System.out.println(e.getMessage());
		}
		return outString;
	}

	public static String HtmlToEn(String s) {
		String re = "";
		re = replace(s, "<", "&lt;");
		re = replace(re, ">", "&gt;");
		re = replace(re, "\r\n", "<br>");
		re = replace(re, " ", "&nbsp;");
		return re;
	}

	public static String HtmlToCn(String str) {
		String re = "";
		re = replace(str, "&lt;", "<");
		re = replace(re, "&gt;", ">");
		re = replace(re, "<br>", "\r\n");
		re = replace(re, "&nbsp;", " ");
		return re;
	}

	/**
	 * 描述: 格式化搜索关键词
	 * 
	 * @param str
	 * @return
	 */
	public static String formatSearchWord(String str) {
		String frist = str.toLowerCase().trim();
		// 去除多余的中文空格
		Pattern p = Pattern.compile("　{1,}");
		Matcher m = p.matcher(frist);
		String second = m.replaceAll(" ");
		// 去除多余空格
		p = Pattern.compile(" {2,}");
		m = p.matcher(second);
		String third = m.replaceAll(" ");
		// 去除加号前后的空格
		p = Pattern.compile("\\s*\\+\\s*");
		m = p.matcher(third);
		String fourth = m.replaceAll("+");
		p = Pattern.compile("\\s*\\-\\s*");
		m = p.matcher(fourth);
		str = m.replaceAll("-");
		str = str.replaceAll(" ", " AND ");
		str = str.replaceAll("-", " NOT ");
		str = str.replaceAll("\\+", " OR ");
		System.out.println("search:"+str);
		return str;
	}

    /**
     * 描述: 还原格式化后的字符串(字符串为用户输入)
     * 
     * @param str 格式化后的字符串
     * @return
     */
    public static String reductionQueryStr(String str) {
        if (str.indexOf("/%") >= 0)
            str = replace(str, "/%", "%");
        return str;
    }

	/**
	 * 描述：
	 * @param pageNo
	 * @return
	 */
	public static int String2Int(String str) {
		if(StringUtils.hasText(str)){
			return Integer.valueOf(str);
		}else{
			return 0;
		}
	}
	
	public static void main(String[] args) {
		
		String str = "1";
		
		int i = StringUtil.String2Int(str);
		
		System.out.println(i);
		
	}
}
