/**
 * <p>项目名称：</p>
 * <p>版权所有 (c) </p>
 * <p>版本：1.0</p>
 * <p>日期：Jun 26, 2014</p>
 * <p>更新：</p>
 */
package org.apache.nutch.parse.html;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.regex.Matcher;

/**
 * @author xiacc
 * 
 * 描述：
 */
public class HtmlExtractor {

	private Html html;

	/**
	 * 描述：
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		HtmlExtractor extractor = new HtmlExtractor();
		Html html = new Html();

		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(
					"./html/荆门市组织工作网.htm"), Charset.forName("utf-8")));
			StringBuffer sb = new StringBuffer();

			while (br.readLine() != null) {
				sb.append(br.readLine());
			}
			br.close();

			// System.out.println(sb.toString());
			html.setHtmlSource(sb.toString());
			extractor.process(html);

			// System.out.println("realtitle:" + html.getTitle());
			// System.out.println("content:" + html.getContent());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void process(Html html) {
		// extractTitle(html);
		html.setHtmlText(preProcess(html.getHtmlSource()));
		System.out.println(html.getHtmlText());

	}

	public String extractContent(Html html) {

		return null;
	}

	public String extractTitle(Html html) {
		Matcher m1 = Html.titleRegexPattern.matcher(html.getHtmlSource());

		if (m1.find()) {
			html.setTitle(m1.group(1));
			return m1.group(1);
		}
		return new String();
	}

	public String preProcess(String htmlText) {

		// htmlText = htmlText.replaceAll("\n|\r\n|\n\r", "");

		// DTD
		htmlText = htmlText.replaceAll("(?is)<!DOCTYPE.*?>", "");
		// html comment
		htmlText = htmlText.replaceAll("(?is)<!--.*?-->", "");
		// js
		htmlText = htmlText.replaceAll("(?is)<script.*?>.*?</script>", "");
		// css
		htmlText = htmlText.replaceAll("(?is)<style.*?>.*?</style>", "");
		// div
		htmlText = htmlText.replaceAll("(?is)<div.*?>", "\n");
		// table
		htmlText = htmlText.replaceAll("(?is)<table.*?>", "\n");
		// td
		htmlText = htmlText.replaceAll("(?is)<td.*?>", "\n");
		// a
		htmlText = htmlText.replaceAll("(?is)<a.*?>", "\n\n");
		// html
		htmlText = htmlText.replaceAll("(?is)<.*?>", "");

		return htmlText;
	}

	/**
	 * 返回 html
	 */
	public Html getHtml() {
		return html;
	}

	/**
	 * 设置 html
	 */
	public void setHtml(Html html) {
		this.html = html;
	}

}
