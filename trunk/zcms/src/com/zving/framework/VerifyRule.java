package com.zving.framework;

import com.zving.framework.utility.DateUtil;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VerifyRule {
	public static final String F_String = "String";
	public static final String F_Any = "Any";
	public static final String F_Number = "Number";
	public static final String F_Date = "Date";
	public static final String F_Time = "Time";
	public static final String F_DateTime = "DateTime";
	public static final String F_Year = "Year";
	public static final String F_Month = "Month";
	public static final String F_Day = "Day";
	public static final String F_Int = "Int";
	public static final String F_DigitChar = "DigitChar";
	public static final String F_AsciiChar = "AsciiChar";
	public static final String F_LetterChar = "LetterChar";
	public static final String F_UpperChar = "UpperChar";
	public static final String F_LowerChar = "LowerChar";
	public static final String F_NotNull = "NotNull";
	public static final String F_Email = "Email";
	public static final String F_Code = "Code";
	public static final String F_HalfChar = "HalfChar";
	public static final String F_FullChar = "FullChar";
	public static final String O_Add = "&&";
	public static final String O_Or = "||";
	public static final String O_Not = "!";
	public static final String A_Format = "Format";
	public static final String A_RegFormat = "RegFormat";
	public static final String A_Max = "Max";
	public static final String A_Min = "Min";
	public static final String A_Len = "Length";
	private static final String regEmail = "^[_\\-a-z0-9A-Z]*?[\\._\\-a-z0-9]*?[a-z0-9]+@[a-z0-9]+[a-z0-9\\-]*?[a-z0-9]+\\.[\\.a-z0-9]*$";
	private static Pattern patternEmail = null;
	private String Rule;
	private String[] Features;
	private ArrayList Messages;

	public VerifyRule() {
	}

	public VerifyRule(String rule) {
		this.Rule = rule;
	}

	public boolean verify(String value) {
		this.Messages = new ArrayList();
		this.Features = this.Rule.split("\\&\\&");
		boolean sqlFlag = true;
		boolean verifyFlag = true;
		try {
			for (int i = 0; i < this.Features.length; ++i) {
				String op = "=";
				if (this.Features[i].indexOf(62) > 0)
					op = ">";
				else if (this.Features[i].indexOf(60) > 0) {
					op = "<";
				}
				String[] f = this.Features[i].split("\\" + op);
				String fName = f[0];
				String fValue = null;
				if (f.length > 1) {
					fValue = f[1];
				}
				label880: if (fName.equals("Any")) {
					sqlFlag = false;
				} else if (fName.equals("NotNull")) {
					if ((value == null) || (value.equals(""))) {
						this.Messages.add("不能为空");
						return false;
					}
				} else if (fName.equals("Code")) {
					if ((value == null) || (!(value.equals(""))))
						continue;
				} else if (fName.equals("Date")) {
					if (value == null)
						continue;
					if (value.equals("")) {
						continue;
					}
					if (!(DateUtil.isDate(value))) {
						this.Messages.add("不是正确的日期值");
						verifyFlag = false;
					}
				} else if (fName.equals("Time")) {
					if (value == null)
						continue;
					if (value.equals("")) {
						continue;
					}
					if (!(DateUtil.isTime(value))) {
						this.Messages.add("不是正确的日期值");
						verifyFlag = false;
					}
				} else if (fName.equals("DateTime")) {
					if (value == null)
						continue;
					if (value.equals("")) {
						continue;
					}
					String[] arr = value.split(" ");
					if ((arr.length == 1) && (!(DateUtil.isDate(arr[0])))) {
						this.Messages.add("不是正确的日期值");
						verifyFlag = false;
					} else if (arr.length == 2) {
						if ((!(DateUtil.isDate(arr[0]))) || (!(DateUtil.isTime(arr[1])))) {
							this.Messages.add("不是正确的日期值");
							verifyFlag = false;
						}
					} else {
						this.Messages.add("不是正确的日期值");
						verifyFlag = false;
					}
				} else if (fName.equals("Number")) {
					if (value == null)
						continue;
					if (value.equals(""))
						continue;
					try {
						Double.parseDouble(value);
					} catch (Exception e) {
						this.Messages.add("不是正确的数值");
						verifyFlag = false;
					}
				} else if (fName.equals("Int")) {
					if (value == null)
						continue;
					if (value.equals(""))
						continue;
					try {
						Integer.parseInt(value);
					} catch (Exception e) {
						this.Messages.add("不是正确的整数值");
						verifyFlag = false;
					}
				} else if (fName.equals("String")) {
					if (value == null)
						continue;
					if (value.equals("")) {
						continue;
					}
					if ((value.indexOf(39) >= 0) || (value.indexOf(34) >= 0)) {
						this.Messages.add("可能是非法字符串");
						verifyFlag = false;
					}
				} else if (fName.equals("Email")) {
					if (value == null)
						continue;
					if (value.equals("")) {
						continue;
					}
					if (patternEmail == null) {
						patternEmail = Pattern
								.compile("^[_\\-a-z0-9A-Z]*?[\\._\\-a-z0-9]*?[a-z0-9]+@[a-z0-9]+[a-z0-9\\-]*?[a-z0-9]+\\.[\\.a-z0-9]*$");
					}
					Matcher m = patternEmail.matcher(value);
					if (!(m.find())) {
						this.Messages.add("不是正确的电子邮箱地址");
						verifyFlag = false;
					}
				} else if ((fName.equals("Length")) && (value != null)) {
					if (value.equals("")) {
						continue;
					}
					if ((fValue == null) || (fValue.equals("")))
						throw new RuntimeException("校验规则错误，Length后面必须是数字");
					try {
						int len = Integer.parseInt(fValue);
						if ((op.equals("=")) && (value.length() != len)) {
							this.Messages.add("长度必须是" + len);
							verifyFlag = false;
						}
						if ((op.equals(">")) && (value.length() <= len)) {
							this.Messages.add("长度必须大于" + len);
							verifyFlag = false;
						}
						if ((op.equals("<")) && (value.length() >= len)) {
							this.Messages.add("长度必须小于" + len);
							verifyFlag = false;
						}
					} catch (Exception e) {
						e.printStackTrace();
						throw new RuntimeException("校验规则错误，Length后面必须是整数");
					}
				}
			}

			if ((sqlFlag)
					&& (value != null)
					&& (((value.indexOf(" and ") > 0) || (value.indexOf(" or ") > 0)))
					&& (((value.indexOf(33) > 0) || (value.indexOf(" like ") > 0)
							|| (value.indexOf(61) > 0) || (value.indexOf(62) > 0) || (value
							.indexOf(60) > 0)))) {
				this.Messages.add("请不要尝试输入SQL语句!");
				verifyFlag = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("不正确的校验规则:" + this.Rule);
		}
		if ((sqlFlag) && (!(checkSQL(value)))) {
			verifyFlag = false;
		}

		return verifyFlag;
	}

	public String getMessages(String fieldName) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < this.Messages.size(); ++i) {
			sb.append(fieldName);
			sb.append(":");
			sb.append(this.Messages.get(i));
			sb.append("\n");
		}
		return sb.toString();
	}

	private boolean checkSQL(String value) {
		return true;
	}

	protected String getRule() {
		return this.Rule;
	}

	protected void setRule(String rule) {
		this.Rule = rule;
	}

	public static void main(String[] args) {
		VerifyRule rule = new VerifyRule();
		rule.setRule("Email");
		System.out.println(rule.verify("wyuch_.-2@m165-a.com"));
		System.out.println(rule.getMessages("电子邮相"));
	}
}

/*
 * Location: F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\ Qualified Name:
 * com.zving.framework.VerifyRule JD-Core Version: 0.5.3
 */