/*jadclipse*/// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
package com.zving.framework.ssi;

import java.io.IOException;
import java.util.*;

// Referenced classes of package com.zving.framework.ssi:
//            URLEncoder, SSIConditionalState, Strftime, DateTool, 
//            SSIExternalResolver

public class SSIMediator {

	public SSIMediator(SSIExternalResolver ssiExternalResolver, long lastModifiedDate, int debug) {
		configErrMsg = "[an error occurred while processing this directive]";
		configTimeFmt = "%A, %d-%b-%Y %T %Z";
		configSizeFmt = "abbrev";
		className = getClass().getName();
		conditionalState = new SSIConditionalState();
		this.ssiExternalResolver = ssiExternalResolver;
		this.lastModifiedDate = lastModifiedDate;
		this.debug = debug;
		setConfigTimeFmt("%A, %d-%b-%Y %T %Z", true);
	}

	public void setConfigErrMsg(String configErrMsg) {
		this.configErrMsg = configErrMsg;
	}

	public void setConfigTimeFmt(String configTimeFmt) {
		setConfigTimeFmt(configTimeFmt, false);
	}

	public void setConfigTimeFmt(String configTimeFmt, boolean fromConstructor) {
		this.configTimeFmt = configTimeFmt;
		strftime = new Strftime(configTimeFmt, DateTool.LOCALE_US);
		setDateVariables(fromConstructor);
	}

	public void setConfigSizeFmt(String configSizeFmt) {
		this.configSizeFmt = configSizeFmt;
	}

	public String getConfigErrMsg() {
		return configErrMsg;
	}

	public String getConfigTimeFmt() {
		return configTimeFmt;
	}

	public String getConfigSizeFmt() {
		return configSizeFmt;
	}

	public SSIConditionalState getConditionalState() {
		return conditionalState;
	}

	public Collection getVariableNames() {
		Set variableNames = new HashSet();
		variableNames.add("DATE_GMT");
		variableNames.add("DATE_LOCAL");
		variableNames.add("LAST_MODIFIED");
		ssiExternalResolver.addVariableNames(variableNames);
		for (Iterator iter = variableNames.iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			if (isNameReserved(name))
				iter.remove();
		}

		return variableNames;
	}

	public long getFileSize(String path, boolean virtual) throws IOException {
		return ssiExternalResolver.getFileSize(path, virtual);
	}

	public long getFileLastModified(String path, boolean virtual) throws IOException {
		return ssiExternalResolver.getFileLastModified(path, virtual);
	}

	public String getFileText(String path, boolean virtual) throws IOException {
		return ssiExternalResolver.getFileText(path, virtual);
	}

	protected boolean isNameReserved(String name) {
		return name.startsWith(className + ".");
	}

	public String getVariableValue(String variableName) {
		return getVariableValue(variableName, "none");
	}

	public void setVariableValue(String variableName, String variableValue) {
		if (!isNameReserved(variableName))
			ssiExternalResolver.setVariableValue(variableName, variableValue);
	}

	public String getVariableValue(String variableName, String encoding) {
		String lowerCaseVariableName = variableName.toLowerCase();
		String variableValue = null;
		if (!isNameReserved(lowerCaseVariableName)) {
			variableValue = ssiExternalResolver.getVariableValue(variableName);
			if (variableValue == null) {
				variableName = variableName.toUpperCase();
				variableValue = ssiExternalResolver
						.getVariableValue(className + "." + variableName);
			}
			if (variableValue != null)
				variableValue = encode(variableValue, encoding);
		}
		return variableValue;
	}

	public String substituteVariables(String val) {
		if (val.indexOf('$') < 0)
			return val;
		StringBuffer sb = new StringBuffer(val);
		for (int i = 0; i < sb.length();) {
			while (i < sb.length()) {
				if (sb.charAt(i) == '$') {
					i++;
					break;
				}
				i++;
			}
			if (i == sb.length())
				break;
			if (i > 1 && sb.charAt(i - 2) == '\\') {
				sb.deleteCharAt(i - 2);
				i--;
			} else {
				int nameStart = i;
				int start = i - 1;
				int end = -1;
				int nameEnd = -1;
				char endChar = ' ';
				if (sb.charAt(i) == '{') {
					nameStart++;
					endChar = '}';
				}
				for (; i < sb.length(); i++)
					if (sb.charAt(i) == endChar)
						break;

				end = i;
				nameEnd = end;
				if (endChar == '}')
					end++;
				String varName = sb.substring(nameStart, nameEnd);
				String value = getVariableValue(varName);
				if (value == null)
					value = "";
				sb.replace(start, end, value);
				i = start + value.length();
			}
		}

		return sb.toString();
	}

	protected String formatDate(Date date, TimeZone timeZone) {
		String retVal;
		if (timeZone != null) {
			TimeZone oldTimeZone = strftime.getTimeZone();
			strftime.setTimeZone(timeZone);
			retVal = strftime.format(date);
			strftime.setTimeZone(oldTimeZone);
		} else {
			retVal = strftime.format(date);
		}
		return retVal;
	}

	protected String encode(String value, String encoding) {
		String retVal = null;
		if (encoding.equalsIgnoreCase("url"))
			retVal = urlEncoder.encode(value);
		else if (encoding.equalsIgnoreCase("none"))
			retVal = value;
		else if (encoding.equalsIgnoreCase("entity"))
			retVal = value;
		else
			throw new IllegalArgumentException("Unknown encoding: " + encoding);
		return retVal;
	}

	public void log(String message) {
		ssiExternalResolver.log(message, null);
	}

	public void log(String message, Throwable throwable) {
		ssiExternalResolver.log(message, throwable);
	}

	protected void setDateVariables(boolean fromConstructor) {
		boolean alreadySet = ssiExternalResolver.getVariableValue(className + ".alreadyset") != null;
		if (!fromConstructor || !alreadySet) {
			ssiExternalResolver.setVariableValue(className + ".alreadyset", "true");
			Date date = new Date();
			TimeZone timeZone = TimeZone.getTimeZone("GMT");
			String retVal = formatDate(date, timeZone);
			setVariableValue("DATE_GMT", null);
			ssiExternalResolver.setVariableValue(className + ".DATE_GMT", retVal);
			retVal = formatDate(date, null);
			setVariableValue("DATE_LOCAL", null);
			ssiExternalResolver.setVariableValue(className + ".DATE_LOCAL", retVal);
			retVal = formatDate(new Date(lastModifiedDate), null);
			setVariableValue("LAST_MODIFIED", null);
			ssiExternalResolver.setVariableValue(className + ".LAST_MODIFIED", retVal);
		}
	}

	protected static final String DEFAULT_CONFIG_ERR_MSG = "[an error occurred while processing this directive]";
	protected static final String DEFAULT_CONFIG_TIME_FMT = "%A, %d-%b-%Y %T %Z";
	protected static final String DEFAULT_CONFIG_SIZE_FMT = "abbrev";
	protected static URLEncoder urlEncoder;
	protected String configErrMsg;
	protected String configTimeFmt;
	protected String configSizeFmt;
	protected String className;
	protected SSIExternalResolver ssiExternalResolver;
	protected long lastModifiedDate;
	protected int debug;
	protected Strftime strftime;
	protected SSIConditionalState conditionalState;

	static {
		urlEncoder = new URLEncoder();
		urlEncoder.addSafeCharacter(',');
		urlEncoder.addSafeCharacter(':');
		urlEncoder.addSafeCharacter('-');
		urlEncoder.addSafeCharacter('_');
		urlEncoder.addSafeCharacter('.');
		urlEncoder.addSafeCharacter('*');
		urlEncoder.addSafeCharacter('/');
		urlEncoder.addSafeCharacter('!');
		urlEncoder.addSafeCharacter('~');
		urlEncoder.addSafeCharacter('\'');
		urlEncoder.addSafeCharacter('(');
		urlEncoder.addSafeCharacter(')');
	}
}

/*
 * DECOMPILATION REPORT
 * 
 * Decompiled from: E:\xiacc\workspace\zcms\WebRoot\WEB-INF\lib\com.jar Total
 * time: 31 ms Jad reported messages/errors: Exit status: 0 Caught exceptions:
 */