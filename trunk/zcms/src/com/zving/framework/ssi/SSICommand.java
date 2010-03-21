package com.zving.framework.ssi;

import java.io.PrintWriter;

public abstract interface SSICommand
{
  public abstract long process(SSIMediator paramSSIMediator, String paramString, String[] paramArrayOfString1, String[] paramArrayOfString2, PrintWriter paramPrintWriter)
    throws SSIStopProcessingException;
}

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.framework.ssi.SSICommand
 * JD-Core Version:    0.5.3
 */