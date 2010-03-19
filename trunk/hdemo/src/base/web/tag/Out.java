/**
 * <p>项目名称：公共模块</p>
 * <p>版权所有 (c) 2005 湖北全达信息科技有限公司</p>
 * <p>作者：</p>
 * <p>版本：1.0</p>
 * <p>日期：2005-10-22</p>
 * <p>更新：</p>
 */
package com.hxzy.base.web.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;

/**
 * <p>
 * 类名: Out
 * </p>
 * <p>
 * 描述: 输出标签类
 * </p>
 */
public class Out extends TagSupport {

  /**
   * 描述: 值
   */
  private String value;

  public int doStartTag() throws JspException {
    try {
      String content = (String) ExpressionEvaluatorManager.evaluate("value",
          value, String.class, this, pageContext);
      if (content == null)
        content = "";
      JspWriter out = pageContext.getOut();
      int len = content.length();
      for (int i = 0; i < len; i++) {
        char c = content.charAt(i);
        if (c == '&')
          out.print("&amp;");
        else if (c == '<')
          out.print("&lt;");
        else if (c == '>')
          out.print("&gt;");
        else if (c == '"')
          out.print("&#034;");
        else if (c == '\'')
          out.print("&#039;");
        else if (c == '\r')
          out.print("");
        else if (c == '\n')
          out.print("<br>");
        else if (c == ' ')
          out.print("&nbsp;");
        else
          out.print(c);
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return TagSupport.SKIP_BODY;
  }

  /**
   * 描述: 返回 value
   */
  public String getValue() {
    return value;
  }

  /**
   * 描述: 设置 value
   */
  public void setValue(String value) {
    this.value = value;
  }
}

