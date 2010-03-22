package com.zving.framework.controls;

import com.zving.framework.data.DataColumn;
import com.zving.framework.data.DataRow;
import com.zving.framework.data.DataTable;
import com.zving.framework.utility.HtmlUtil;
import com.zving.framework.utility.Mapx;
import com.zving.framework.utility.ServletUtil;
import java.io.IOException;
import java.lang.reflect.Method;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.Tag;

public class SimpleListTag extends BodyTagSupport {
	private static final long serialVersionUID = 1L;
	public String method;
	public DataTable dt;
	public int index;
	public DataRow dr;

	public DataRow getDataRow() {
		return this.dr;
	}

	public void transferDataRow(DataRow dr) {
		this.dr = dr;
	}

	public int doStartTag() throws JspException {
		this.index = 0;
		this.dr = null;

		HttpServletRequest request = (HttpServletRequest) this.pageContext.getRequest();
		Mapx params = ServletUtil.getParameterMap(request);

		Tag ptag = getParent();
		int i = this.method.lastIndexOf(46);
		String className = this.method.substring(0, i);
		this.method = this.method.substring(i + 1);
		try {
			Class c = Class.forName(className);
			Method m = c.getMethod(this.method, new Class[] { Mapx.class, DataRow.class });
			Object o = null;
			if ((ptag != null) && (ptag instanceof SimpleListTag))
				o = m.invoke(null, new Object[] { params, ((SimpleListTag) ptag).getDataRow() });
			else {
				Object[] aobj = new Object[2];
				aobj[0] = params;
				o = m.invoke(null, aobj);
			}
			if (!(o instanceof DataTable)) {
				throw new RuntimeException("调用z:simplelist指定的method时发现返回类型不是DataTable");
			}
			this.dt = ((DataTable) o);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (this.dt.getRowCount() > 0) {
			this.dt.insertColumn(new DataColumn("_RowNo", 8));
			transferDataRow(this.dt.getDataRow(this.index++));
			return 2;
		}
		return 0;
	}

	public int doAfterBody() throws JspException {
		BodyContent body = getBodyContent();
		String content = body.getString().trim();
		try {
			this.dr.set("_RowNo", new Integer(this.index));
			getPreviousOut().write(HtmlUtil.replaceWithDataRow(this.dr, content));
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (this.dt.getRowCount() > this.index) {
			transferDataRow(this.dt.getDataRow(this.index++));
			body.clearBody();
			return 2;
		}
		return 0;
	}

	public String getMethod() {
		return this.method;
	}

	public void setMethod(String method) {
		this.method = method;
	}
}

/*
 * Location: F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\ Qualified Name:
 * com.zving.framework.controls.SimpleListTag JD-Core Version: 0.5.3
 */