
package com.hxzy.base.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.StringUtils;


public class StringUtil {

	/**
	 * ����: ˳��ţ���������Ψһ�ַ���
	 */
	private static Long internalSerialNo = new Long(0);

	/**
	 * ����: ���ַ���ת��ΪLong���ͣ����޷�ת���������󷵻�null
	 * 
	 * @param str
	 *            �����ַ���
	 * @return �������࣬�緢�����󷵻�null
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
	 * ����: ���ַ���ת��ΪDobule�࣬���޷�ת���������󷵻�null
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
	 * ����: ����һ��Ψһ����ʱ�ַ��� ��������Ϊ��ǰ��ʱ��ĺ���������"_"����˳���
	 * 
	 * @return �ַ���
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
	 * ����: ��ʽ���û�������ַ���
	 * 
	 * @param str
	 *            �û������ֵ
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
	 * ����: �滻�ַ���
	 * 
	 * @param con
	 *            ԭʼ�ַ���
	 * @param tag
	 *            ԭ�滻���ַ���
	 * @param rep
	 *            �滻����ַ���
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
	 * ����: �������������ʽ�봫����ַ�������ƥ�䣬����Ϸ���True����֮����False
	 * 
	 * @param pattern
	 *            ������ʽ
	 * @param str
	 *            �ȶԵ��ַ���
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
	 * ����: ��������ַ��������ŷָ�ɵ������ַ���,��������List��
	 * 
	 * @param str
	 *            �ַ���
	 * @return
	 */
	public static ArrayList splicStr(String str) {
		str = str.trim();
		str = str.replace('��', ',');
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
	 * ����: ��ȡ�ַ���
	 * 
	 * @param string
	 *            ����ȡ���ַ���
	 * @param length
	 *            �ַ�������
	 * @param endChar
	 *            ��β�ķ���
	 * @return ���޸ĺ���ַ���
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
	 * ����:�����ַ�������
	 * 
	 * @param string
	 *            �����Ե��ַ���
	 * @return �ַ�������
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

	// ���ֱ���ת��
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

	// ���뺺���ڱ���ת��
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
	 * ����: ��ʽ�������ؼ���
	 * 
	 * @param str
	 * @return
	 */
	public static String formatSearchWord(String str) {
		String frist = str.toLowerCase().trim();
		// ȥ����������Ŀո�
		Pattern p = Pattern.compile("��{1,}");
		Matcher m = p.matcher(frist);
		String second = m.replaceAll(" ");
		// ȥ������ո�
		p = Pattern.compile(" {2,}");
		m = p.matcher(second);
		String third = m.replaceAll(" ");
		// ȥ���Ӻ�ǰ��Ŀո�
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
     * ����: ��ԭ��ʽ������ַ���(�ַ���Ϊ�û�����)
     * 
     * @param str ��ʽ������ַ���
     * @return
     */
    public static String reductionQueryStr(String str) {
        if (str.indexOf("/%") >= 0)
            str = replace(str, "/%", "%");
        return str;
    }

	/**
	 * ������
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
