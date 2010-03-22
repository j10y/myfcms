/*jadclipse*/// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.

package com.zving.framework.ssi;

import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

// Referenced classes of package com.zving.framework.ssi:
//            SSIConfig, SSIEcho, SSIExec, SSIInclude, 
//            SSIFlastmod, SSIFsize, SSIPrintenv, SSISet, 
//            SSIConditional, SSIMediator, IOTools, SSIConditionalState, 
//            SSIExternalResolver, SSICommand

public class SSIProcessor {

	public SSIProcessor(SSIExternalResolver ssiExternalResolver, int debug) {
		commands = new HashMap();
		this.ssiExternalResolver = ssiExternalResolver;
		this.debug = debug;
		addBuiltinCommands();
	}

	protected void addBuiltinCommands() {
		addCommand("config", new SSIConfig());
		addCommand("echo", new SSIEcho());
		addCommand("exec", new SSIExec());
		addCommand("include", new SSIInclude());
		addCommand("flastmod", new SSIFlastmod());
		addCommand("fsize", new SSIFsize());
		addCommand("printenv", new SSIPrintenv());
		addCommand("set", new SSISet());
		SSIConditional ssiConditional = new SSIConditional();
		addCommand("if", ssiConditional);
		addCommand("elif", ssiConditional);
		addCommand("endif", ssiConditional);
		addCommand("else", ssiConditional);
	}

	public void addCommand(String name, SSICommand command) {
		commands.put(name, command);
	}

	public long process(Reader reader, long lastModifiedDate, PrintWriter writer)
			throws IOException {
		SSIMediator ssiMediator = new SSIMediator(ssiExternalResolver, lastModifiedDate, debug);
		StringWriter stringWriter = new StringWriter();
		IOTools.flow(reader, stringWriter);
		String fileContents = stringWriter.toString();
		stringWriter = null;
		int index = 0;
		boolean inside = false;
		StringBuffer command = new StringBuffer();
		try {
			while (index < fileContents.length()) {
				char c = fileContents.charAt(index);
				if (!inside) {
					if (c == "<!--#".charAt(0) && charCmp(fileContents, index, "<!--#")) {
						inside = true;
						index += "<!--#".length();
						command.setLength(0);
					} else {
						if (!ssiMediator.getConditionalState().processConditionalCommandsOnly)
							writer.write(c);
						index++;
					}
				} else if (c == "-->".charAt(0) && charCmp(fileContents, index, "-->")) {
					inside = false;
					index += "-->".length();
					String strCmd = parseCmd(command);
					if (debug > 0)
						ssiExternalResolver.log("SSIProcessor.process -- processing command: "
								+ strCmd, null);
					String paramNames[] = parseParamNames(command, strCmd.length());
					String paramValues[] = parseParamValues(command, strCmd.length(),
							paramNames.length);
					String configErrMsg = ssiMediator.getConfigErrMsg();
					SSICommand ssiCommand = (SSICommand) commands.get(strCmd.toLowerCase());
					String errorMessage = null;
					if (ssiCommand == null)
						errorMessage = "Unknown command: " + strCmd;
					else if (paramValues == null)
						errorMessage = "Error parsing directive parameters.";
					else if (paramNames.length != paramValues.length)
						errorMessage = "Parameter names count does not match parameter values count on command: "
								+ strCmd;
					else if (!ssiMediator.getConditionalState().processConditionalCommandsOnly
							|| (ssiCommand instanceof SSIConditional)) {
						long lmd = ssiCommand.process(ssiMediator, strCmd, paramNames, paramValues,
								writer);
						if (lmd > lastModifiedDate)
							lastModifiedDate = lmd;
					}
					if (errorMessage != null) {
						ssiExternalResolver.log(errorMessage, null);
						writer.write(configErrMsg);
					}
				} else {
					command.append(c);
					index++;
				}
			}
		} catch (Exception exception) {
		}
		return lastModifiedDate;
	}

	protected String[] parseParamNames(StringBuffer cmd, int start) {
		int bIdx = start;
		int i = 0;
		int quotes = 0;
		boolean inside = false;
		StringBuffer retBuf = new StringBuffer();
		while (bIdx < cmd.length())
			if (!inside) {
				for (; bIdx < cmd.length() && isSpace(cmd.charAt(bIdx)); bIdx++)
					;
				if (bIdx >= cmd.length())
					break;
				inside = !inside;
			} else {
				for (; bIdx < cmd.length() && cmd.charAt(bIdx) != '='; bIdx++)
					retBuf.append(cmd.charAt(bIdx));

				retBuf.append('=');
				inside = !inside;
				quotes = 0;
				boolean escaped = false;
				for (; bIdx < cmd.length() && quotes != 2; bIdx++) {
					char c = cmd.charAt(bIdx);
					if (c == '\\' && !escaped) {
						escaped = true;
						bIdx++;
					} else {
						escaped = false;
						if (c == '"')
							quotes++;
					}
				}

			}
		StringTokenizer str = new StringTokenizer(retBuf.toString(), "=");
		String retString[] = new String[str.countTokens()];
		while (str.hasMoreTokens())
			retString[i++] = str.nextToken().trim();
		return retString;
	}

	protected String[] parseParamValues(StringBuffer cmd, int start, int count) {
		int valIndex = 0;
		boolean inside = false;
		String vals[] = new String[count];
		StringBuffer sb = new StringBuffer();
		char endQuote = '\0';
		for (int bIdx = start; bIdx < cmd.length(); bIdx++)
			if (!inside) {
				for (; bIdx < cmd.length() && !isQuote(cmd.charAt(bIdx)); bIdx++)
					;
				if (bIdx >= cmd.length())
					break;
				inside = !inside;
				endQuote = cmd.charAt(bIdx);
			} else {
				boolean escaped = false;
				for (; bIdx < cmd.length(); bIdx++) {
					char c = cmd.charAt(bIdx);
					if (c == '\\' && !escaped) {
						escaped = true;
						continue;
					}
					if (c == endQuote && !escaped)
						break;
					if (c == '$' && escaped)
						sb.append('\\');
					escaped = false;
					sb.append(c);
				}

				if (bIdx == cmd.length())
					return null;
				vals[valIndex++] = sb.toString();
				sb.delete(0, sb.length());
				inside = !inside;
			}

		return vals;
	}

	private String parseCmd(StringBuffer cmd) {
		int firstLetter = -1;
		int lastLetter = -1;
		for (int i = 0; i < cmd.length(); i++) {
			char c = cmd.charAt(i);
			if (Character.isLetter(c)) {
				if (firstLetter == -1)
					firstLetter = i;
				lastLetter = i;
				continue;
			}
			if (!isSpace(c) || lastLetter > -1)
				break;
		}

		String command = null;
		if (firstLetter != -1)
			command = cmd.substring(firstLetter, lastLetter + 1);
		return command;
	}

	protected boolean charCmp(String buf, int index, String command) {
		return buf.regionMatches(index, command, 0, command.length());
	}

	protected boolean isSpace(char c) {
		return c == ' ' || c == '\n' || c == '\t' || c == '\r';
	}

	protected boolean isQuote(char c) {
		return c == '\'' || c == '"' || c == '`';
	}

	protected static final String COMMAND_START = "<!--#";
	protected static final String COMMAND_END = "-->";
	protected static final int BUFFER_SIZE = 4096;
	protected SSIExternalResolver ssiExternalResolver;
	protected HashMap commands;
	protected int debug;
}


/*
	DECOMPILATION REPORT

	Decompiled from: E:\xiacc\workspace\zcms\WebRoot\WEB-INF\lib\com.jar
	Total time: 32 ms
	Jad reported messages/errors:
	Exit status: 0
	Caught exceptions:
*/