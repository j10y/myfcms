package com.zving.workflow;

import java.util.Map;

public abstract interface Condition
{
  public abstract boolean passesCondition(Map paramMap1, Map paramMap2);
}

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.workflow.Condition
 * JD-Core Version:    0.5.3
 */