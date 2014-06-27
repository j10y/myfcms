/**
 * <p>项目名称：</p>
 * <p>版权所有 (c) </p>
 * <p>版本：1.0</p>
 * <p>日期：Jun 27, 2014</p>
 * <p>更新：</p>
 */
package org;

import java.util.regex.Pattern;

/**
 * @author xiacc
 * 
 * 描述：
 */
public class Html {

	private String content;

	private String title;

	private String htmlSource;

	private String htmlText;

	/** The Constant _titlePattern. */
	private final static String titlePattern = "<title>(.*?)</title>";

	/** The Constant _titleRegexPattern. */
	public final static Pattern titleRegexPattern = Pattern.compile(titlePattern, Pattern.CANON_EQ
			| Pattern.DOTALL | Pattern.CASE_INSENSITIVE);

	/**
	 * 返回 content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 设置 content
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 返回 title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 设置 title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 返回 htmlSource
	 */
	public String getHtmlSource() {
		return htmlSource;
	}

	/**
	 * 设置 htmlSource
	 */
	public void setHtmlSource(String htmlSource) {
		this.htmlSource = htmlSource;
	}

	/**
	 * 返回 htmlText
	 */
	public String getHtmlText() {
		return htmlText;
	}

	/**
	 * 设置 htmlText
	 */
	public void setHtmlText(String htmlText) {
		this.htmlText = htmlText;
	}

}
