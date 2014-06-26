/**
 * <p>项目名称：</p>
 * <p>版权所有 (c) </p>
 * <p>版本：1.0</p>
 * <p>日期：Jun 26, 2014</p>
 * <p>更新：</p>
 */
package org.apache.nutch.parse.html;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author xiacc
 * 
 * 描述：
 */
public class Html {

	/** The Constant titlePattern. */
	public final static String titlePattern = "<title>(.*?)</title>";

	public final static Pattern titleRegexPattern = Pattern.compile(titlePattern, Pattern.CANON_EQ
			| Pattern.DOTALL | Pattern.CASE_INSENSITIVE);

	private String title;

	private String content;

	private String htmlSource;

	private String htmlText;

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
