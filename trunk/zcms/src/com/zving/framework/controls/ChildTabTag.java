package com.zving.framework.controls;

import com.zving.framework.utility.StringFormat;
import com.zving.framework.utility.StringUtil;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class ChildTabTag extends BodyTagSupport {
	private static final long serialVersionUID = 1L;
	private String id;
	private String onClick;
	private String afterClick;
	private String src;
	private boolean selected;
	private boolean disabled;
	private boolean visible;
	private boolean lazy;
	private static int No = 0;

	public void setPageContext(PageContext pc) {
		super.setPageContext(pc);
		this.id = null;
		this.onClick = null;
		this.afterClick = null;
		this.src = null;
		this.selected = false;
		this.disabled = false;
		this.visible = true;
		this.lazy = false;
	}

	public int doAfterBody() throws JspException {
		String content = getBodyContent().getString();
		try {
			if (StringUtil.isEmpty(this.id)) {
				this.id = String.valueOf((No++));
			}
			if (this.onClick == null) {
				this.onClick = "";
			}
			if ((StringUtil.isNotEmpty(this.onClick)) && (!(this.onClick.trim().endsWith(";")))) {
				this.onClick = this.onClick.trim() + ";";
			}
			if (this.afterClick == null) {
				this.afterClick = "";
			}
			String type = "";
			if (this.selected)
				type = "Current";
			else if (this.disabled) {
				type = "Disabled";
			}
			StringBuffer sb = new StringBuffer();
			String vStr = "";
			if (!(this.visible)) {
				vStr = "style='display:none'";
			}
			sb.append("<div style='-moz-user-select:none;' onselectstart='return false' ");
			if ("Disabled".equalsIgnoreCase(type)) {
				sb.append("id='" + this.id + "' " + vStr + " targetURL='" + this.src
						+ "' class='divchildtab" + type + "' >");
			} else {
				if (this.lazy)
					this.src = "src='javascript:void(0);' targetURL='" + this.src + "'";
				else {
					this.src = "src='" + this.src + "'";
				}
				StringFormat sf = new StringFormat(
						"id='?' ? class='divchildtab?' ? onclick=\"?Tab.onChildTabClick(this);?\" onMouseOver='Tab.onChildTabMouseOver(this)' onMouseOut='Tab.onChildTabMouseOut(this)'>");

				sf.add(this.id);
				sf.add(vStr);
				sf.add(type);
				sf.add(this.src);
				sf.add(this.onClick);
				sf.add(this.afterClick);
				sb.append(sf.toString());
			}
			sb.append(content);
			sb.append("</div>");
			getPreviousOut().print(sb);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 6;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOnClick() {
		return this.onClick;
	}

	public void setOnClick(String onClick) {
		this.onClick = onClick;
	}

	public String getAfterClick() {
		return this.afterClick;
	}

	public void setAfterClick(String afterClick) {
		this.afterClick = afterClick;
	}

	public boolean isDisabled() {
		return this.disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public boolean isSelected() {
		return this.selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public String getSrc() {
		return this.src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public boolean isVisible() {
		return this.visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public boolean isLazy() {
		return this.lazy;
	}

	public void setLazy(boolean lazy) {
		this.lazy = lazy;
	}
}

/*
 * Location: F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\ Qualified Name:
 * com.zving.framework.controls.ChildTabTag JD-Core Version: 0.5.3
 */