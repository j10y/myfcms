/**
 * <p>��Ŀ���ƣ�����ģ��</p>
 * <p>��Ȩ���� (c) 2005 ����ȫ����Ϣ�Ƽ����޹�˾</p>
 * <p>���ߣ�</p>
 * <p>�汾��1.0</p>
 * <p>���ڣ�2005-10-22</p>
 * <p>���£�</p>
 */
package com.hxzy.base.web.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;

/**
 * <p>
 * ����: Out
 * </p>
 * <p>
 * ����: �����ǩ��
 * </p>
 */
public class Out extends TagSupport {

  /**
   * ����: ֵ
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
   * ����: ���� value
   */
  public String getValue() {
    return value;
  }

  /**
   * ����: ���� value
   */
  public void setValue(String value) {
    this.value = value;
  }
}

