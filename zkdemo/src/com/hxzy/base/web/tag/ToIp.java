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

import com.hxzy.base.util.StringUtil;


/**
 * <p>
 * 类名: ToIp
 * </p>
 * <p>
 * 描述: 格式化显示IP标签类
 * </p>
 */
public class ToIp extends TagSupport {

  private String value;

  public int doStartTag() throws JspException {
    try {
      //获取设置的属性
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