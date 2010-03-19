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

import com.hxzy.base.util.StringUtil;


/**
 * <p>
 * ����: ToIp
 * </p>
 * <p>
 * ����: ��ʽ����ʾIP��ǩ��
 * </p>
 */
public class ToIp extends TagSupport {

  private String value;

  public int doStartTag() throws JspException {
    try {
      //��ȡ���õ�����
      String ipStr = (String) ExpressionEvaluatorManager.evaluate("value",
          value, String.class, this, pageContext);
      if (ipStr != null && ipStr.length() == 12) {
        JspWriter out = pageContext.getOut();
        out.println(StringUtil.stringToLong(ipStr.substring(0, 3)));
        out.println(".");
        out.println(StringUtil.stringToLong(ipStr.substring(3, 6)));
        out.println(".");
        out.println(StringUtil.stringToLong(ipStr.substring(6, 9)));
        out.println(".");
        out.println(StringUtil.stringToLong(ipStr.substring(9, 12)));
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