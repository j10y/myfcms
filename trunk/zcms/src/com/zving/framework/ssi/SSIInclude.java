package com.zving.framework.ssi;

import java.io.IOException;
import java.io.PrintWriter;

public final class SSIInclude implements SSICommand {
	public long process(SSIMediator ssiMediator, String commandName, String[] paramNames,
			String[] paramValues, PrintWriter writer) {
		long lastModified = 0L;
		String configErrMsg = ssiMediator.getConfigErrMsg();
		for (int i = 0; i < paramNames.length; ++i) {
			String paramName = paramNames[i];
			String paramValue = paramValues[i];
			String substitutedValue = ssiMediator.substituteVariables(paramValue);
			try {
				if ((paramName.equalsIgnoreCase("file")) || (paramName.equalsIgnoreCase("virtual"))) {
					boolean virtual = paramName.equalsIgnoreCase("virtual");
					lastModified = ssiMediator.getFileLastModified(substitutedValue, virtual);
					String text = ssiMediator.getFileText(substitutedValue, virtual);
					writer.write(text);
				} else {
					ssiMediator.log("#include--Invalid attribute: " + paramName);
				}
				writer.write(configErrMsg);
			} catch (IOException e) {
				ssiMediator.log("#include--Couldn't include file: " + substitutedValue, e);
				writer.write(configErrMsg);
			}
		}
		return lastModified;
	}
}

/*
 * Location: F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\ Qualified Name:
 * com.zving.framework.ssi.SSIInclude JD-Core Version: 0.5.3
 */