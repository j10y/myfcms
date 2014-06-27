/**
 * <p>项目名称：</p>
 * <p>版权所有 (c) </p>
 * <p>版本：1.0</p>
 * <p>日期：Jun 26, 2014</p>
 * <p>更新：</p>
 */
package org;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.regex.Matcher;

import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.IndexAnalysis;
import org.apache.commons.lang.StringUtils;

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

		// Html html = new
		// HtmlExtractor().processFile("./html/中共许昌市委组织部子站.htm");

		Html html = new HtmlExtractor().processURL("http://news.12371.cn/2014/02/22/ARTI1393043944223970.shtml");
		System.out.println("realtitle:" + html.getTitle());
		System.out.println("content:" + html.getContent());
	}

	public Html processURL(String url) {
		html = new Html();
		url = url.trim();
		String htmlText = DownloadURL.downURL(url, "IE8.0");
		html.setHtmlSource(htmlText);
		process(html);
		return html;
	}

	public Html processFile(String path) {
		html = new Html();

		try {

			InputStreamReader isr = new InputStreamReader(new FileInputStream(path), "utf-8");
			BufferedReader br = new BufferedReader(isr);

			StringBuffer sb = new StringBuffer();
			String temp = null;
			while ((temp = br.readLine()) != null) {
				sb.append(temp);
			}
			br.close();

			html.setHtmlSource(sb.toString());
			process(html);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return html;
	}

	public boolean process(Html html) {
		String htmlText = preProcess(html.getHtmlSource());

		html.setHtmlText(htmlText);

		String[] txt = htmlText.split("\n\n");

		String result = new String();

		int indexof = 0;
		for (int i = 0; i < txt.length; i++) {

			if (StringUtils.isNotBlank(txt[i])) {
				if (txt[i].length() > result.length()) {
					result = txt[i];
					indexof = i;
				}
			}
		}

		if (result.length() < 100) {
			html.setContent("该网页非主题类型，不支持解析！");
			return false;
		} else if (StringUtils.countMatches(result, "，") + StringUtils.countMatches(result, "。") < 15) {
			html.setContent("该网页非主题类型，不支持解析！");
			return false;
		} else {
			html.setContent(result);
			extractTitle(html, txt, indexof);
			return true;
		}

	}

	public String extractTitle(Html html, String[] txt, int indexof) {

		String realTitle = new String();
		IndexAnalysis analysis = new IndexAnalysis();

		float maxScore = 0;
		String title = null;

		int k = 0;
		for (int i = 0; i < indexof; i++) {

			StringBuffer sb = new StringBuffer();

			String[] strs = txt[i].replace(" ", "").replace("\\s", "").split("\n");
			for (String str : strs) {
				if (StringUtils.isNotBlank(str)) {
					sb.append(str);
					break;
				}
			}
			if (sb.length() > 100) {
				break;
			}

			List<Term> terms = analysis.parse(sb.toString());
			float score = 0;
			for (int j = 0; j < terms.size(); j++) {
				float count = StringUtils.countMatches(html.getContent(), terms.get(j).getRealName());
				score = score + count;
			}
			score = score / ((indexof - i) * 10 + sb.length());

			if (score > maxScore) {
				realTitle = sb.toString();
				maxScore = score;
				k = i;
			}
			System.out.println("title:" + sb.toString() + score);

		}

		Matcher m1 = Html.titleRegexPattern.matcher(html.getHtmlSource());

		if (m1.find()) {
			String headTitle = m1.group(1).replaceAll("\\s| |　|	", "");

			List<Term> terms = analysis.parse(headTitle);
			float score = 0;
			for (int j = 0; j < terms.size(); j++) {
				float count = StringUtils.countMatches(html.getContent(), terms.get(j).getRealName());
				score = score + count;
			}
			score = score / ((indexof - k) * 10 + headTitle.length());

			System.out.println("<title>:" + headTitle + score);
			if (score > maxScore) {
				maxScore = score;
				realTitle = headTitle;
			}
		}

		html.setTitle(realTitle);
		return title;
	}

	private String preProcess(String htmlText) {

		htmlText = htmlText.replaceAll("&quot;", "\"");
		htmlText = htmlText.replaceAll("&ldquo;", "“");
		htmlText = htmlText.replaceAll("&rdquo;", "”");
		htmlText = htmlText.replaceAll("&middot;", "·");
		htmlText = htmlText.replaceAll("&#8231;", "·");
		htmlText = htmlText.replaceAll("&#8212;", "——");
		htmlText = htmlText.replaceAll("&#28635;", "濛");
		htmlText = htmlText.replaceAll("&hellip;", "…");
		htmlText = htmlText.replaceAll("&#23301;", "嬅");
		htmlText = htmlText.replaceAll("&#27043;", "榣");
		htmlText = htmlText.replaceAll("&#8226;", "·");
		htmlText = htmlText.replaceAll("&#40;", "(");
		htmlText = htmlText.replaceAll("&#41;", ")");
		htmlText = htmlText.replaceAll("&#183;", "·");
		htmlText = htmlText.replaceAll("&amp;", "&");
		htmlText = htmlText.replaceAll("&bull;", "·");
		// text = text.replaceAll("&lt;", "<");
		// text = text.replaceAll("&#60;", "<");
		// text = text.replaceAll("&gt;", ">");
		// text = text.replaceAll("&#62;", ">");
		htmlText = htmlText.replaceAll("&nbsp;", " ");
		htmlText = htmlText.replaceAll("&#160;", " ");
		htmlText = htmlText.replaceAll("&tilde;", "~");
		htmlText = htmlText.replaceAll("&mdash;", "—");
		htmlText = htmlText.replaceAll("&copy;", "@");
		htmlText = htmlText.replaceAll("&#169;", "@");
		htmlText = htmlText.replaceAll("♂", "");
		htmlText = htmlText.replaceAll("\r\n|\r", "\n");

		htmlText = htmlText.replaceAll("\\s| |　|	", "");
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
		htmlText = htmlText.replaceAll("(?is)</div.*?>", "\n");
		// table
		htmlText = htmlText.replaceAll("(?is)<table.*?>", "\n");
		htmlText = htmlText.replaceAll("(?is)</table.*?>", "\n");
		// td
		htmlText = htmlText.replaceAll("(?is)<td.*?>", "\n\n");
		// a
		htmlText = htmlText.replaceAll("(?is)<li.*?>", "\n\n");
		// a
		// htmlText = htmlText.replaceAll("(?is)<p.*?>", "");

		htmlText = htmlText.replaceAll("(?is)<option.*?>", "\n\n");
		// a
		htmlText = htmlText.replaceAll("(?is)<a.*?>", "\n");
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
