package com.zving.workflow.util;

import java.io.PrintWriter;
import java.io.Serializable;

public abstract interface XMLizable extends Serializable
{
  public static final String INDENT = "  ";

  public abstract void writeXML(PrintWriter paramPrintWriter, int paramInt);
}

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.workflow.util.XMLizable
 * JD-Core Version:    0.5.3
 */