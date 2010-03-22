/*jadclipse*/// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
package com.zving.framework.ssi;

import java.io.IOException;
import java.io.PrintWriter;

// Referenced classes of package com.zving.framework.ssi:
//            SSICommand, SSIMediator

public final class SSIInclude implements SSICommand {

	public SSIInclude() {
	}

	public long process(SSIMediator ssiMediator, String commandName, String paramNames[],
			String paramValues[], PrintWriter writer) {
		long lastModified = 0L;
		String configErrMsg = ssiMediator.getConfigErrMsg();
		for (int i = 0; i < paramNames.length; i++) {
			String paramName = paramNames[i];
			String paramValue = paramValues[i];
			String substitutedValue = ssiMediator.substituteVariables(paramValue);
			try {
				if (paramName.equalsIgnoreCase("file") || paramName.equalsIgnoreCase("virtual")) {
					boolean virtual = paramName.equalsIgnoreCase("virtual");
					lastModified = ssiMediator.getFileLastModified(substitutedValue, virtual);
					String text = ssiMediator.getFileText(substitutedValue, virtual);
					writer.write(text);
				} else {
					ssiMediator.log("#include--Invalid attribute: " + paramName);
					writer.write(configErrMsg);
				}
			} catch (IOException e) {
				ssiMediator.log("#include--Couldn't include file: " + substitutedValue, e);
				writer.write(configErrMsg);
			}
		}

		return lastModified;
	}
}

/*
 * DECOMPILATION REPORT
 * 
 * Decompiled from: E:\xiacc\workspace\zcms\WebRoot\WEB-INF\lib\com.jar Total
 * time: 31 ms Jad reported messages/errors: Exit status: 0 Caught exceptions:
 */