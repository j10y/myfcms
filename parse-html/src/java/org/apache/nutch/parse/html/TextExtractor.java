package org.apache.nutch.parse.html;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.IndexAnalysis;
import org.apache.commons.lang.StringUtils;

/**
 * 主题型网页正文抽取，比较适合于新闻和Blog的正文抽取.<br />
 * 采用《基于行块分布函数的通用网页正文抽取》的算法，该算法时间复杂度为线性， 不需要建立DOM树，且不依赖HTML标签。<br />
 * 首先将网页正文抽取问题转化为求页面的行块分布函数，这种方法不用建立Dom树， 不被病态HTML所累（事实上与HTML标签完全无关）。
 * 通过在线性时间内建立的行块分布函数图，直接准确定位网页正文。
 * 
 * @author Fandy Wang(lfwang@ir.hit.edu.cn)
 * @date 2010.08.12
 */
public class TextExtractor {

	/** 行块大小. */
	private static Integer _block = 3;

	/** The Constant _titlePattern. */
	private final static String _titlePattern = "<title>(.*?)</title>";

	/** The Constant _titleRegexPattern. */
	private final static Pattern _titleRegexPattern = Pattern.compile(_titlePattern, Pattern.CANON_EQ | Pattern.DOTALL
			| Pattern.CASE_INSENSITIVE);

	/** The _title. */
	private String _title = "";

	/** The _text. */
	private String _text = "";

	/**
	 * Sets the block.
	 * 
	 * @param block
	 *            the new block
	 */
	@SuppressWarnings("unused")
	private static void setBlock(Integer block) {
		_block = block;
	}

	/**
	 * Extract url.
	 * 
	 * @param url
	 *            the url
	 */
	public void extractURL(String url) {
		url = url.trim();
		if (isLegalURL(url)) {
			if (isContentURL(url)) {
				String htmlText = DownloadURL.downURL(url, "IE8.0");
				extractHTML(htmlText);
			} else {
				_text = "*推测您提供的网页为非主题型网页，目前暂不处理！:-)";
			}
		} else {
			_title = "*非法URL:-)";
		}
	}

	/**
	 * Extract url.
	 * 
	 * @param url
	 *            the url
	 * @param encoding
	 *            the encoding
	 */
	public void extractURL(String url, String encoding) {
		url = url.trim();
		if (isLegalURL(url)) {
			if (isContentURL(url)) {
				String htmlText = DownloadURL.downURL(url, encoding, "IE8.0");
				extractHTML(htmlText);
			} else {
				_text = "*推测您提供的网页为非主题型网页，目前暂不处理！:-)";
			}
		} else {
			_title = "*非法URL:-)";
		}
	}

	/**
	 * Checks if is content url.
	 * 
	 * @param url
	 *            the url
	 * 
	 * @return true, if is content url
	 */
	private boolean isContentURL(String url) {
		int count = 0;
		for (int i = 0; i < url.length() - 1 && count < 3; i++) {
			if (url.charAt(i) == '/')
				count++;
		}

		// return count > 2;
		return true;
	}

	/**
	 * Checks if is legal url.
	 * 
	 * @param url
	 *            the url
	 * 
	 * @return true, if is legal url
	 */
	private boolean isLegalURL(String url) {
		if (url == null || url.length() == 0) {
			return false;
		}

		String regEx = "^(http|https|ftp)\\://([a-zA-Z0-9\\.\\-]+(\\:[a-zA-"
				+ "Z0-9\\.&%\\$\\-]+)*@)?((25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{"
				+ "2}|[1-9]{1}[0-9]{1}|[1-9])\\.(25[0-5]|2[0-4][0-9]|[0-1]{1}"
				+ "[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|"
				+ "[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\\.(25[0-5]|2[0-"
				+ "4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])|([a-zA-Z0"
				+ "-9\\-]+\\.)*[a-zA-Z0-9\\-]+\\.[a-zA-Z]{2,4})(\\:[0-9]+)?(/"
				+ "[a-zA-Z0-9\\.\\,\\?\\'\\\\/\\+&%\\$\\=~_\\-@]*)*$";
		Pattern p = Pattern.compile(regEx);
		Matcher matcher = p.matcher(url);
		// System.out.println(matcher.matches());
		return matcher.matches();
	}

	/**
	 * Extract html.
	 * 
	 * @param htmlText
	 *            the html text
	 */
	public boolean extractHTML(String htmlText) {

		getTitle(htmlText);
		htmlText = preProcess(htmlText);
		if (!isContentPage(htmlText)) {
			_text = "*推测您提供的网页为非主题型网页，目前暂不处理！:-)";
			return false;
		}

		List<String> lines = Arrays.asList(htmlText.split("\n"));
		List<Integer> indexDistribution = lineBlockDistribute(lines);

		List<String> textList = new ArrayList<String>();
		List<Integer> textBeginList = new ArrayList<Integer>();
		List<Integer> textEndList = new ArrayList<Integer>();

		for (int i = 0; i < indexDistribution.size(); i++) {
			if (indexDistribution.get(i) > 0) {
				StringBuilder tmp = new StringBuilder();
				textBeginList.add(i);
				while (i < indexDistribution.size() && indexDistribution.get(i) > 0) {
					tmp.append(lines.get(i)).append("\n");
					i++;
				}
				textEndList.add(i);
				textList.add(tmp.toString());
			}
		}

		// 如果两块只差两个空行，并且两块包含文字均较多，则进行块合并，以弥补单纯抽取最大块的缺点
		for (int i = 1; i < textList.size(); i++) {
			if (textBeginList.get(i) == textEndList.get(i - 1) + 1 && textEndList.get(i) > textBeginList.get(i) + _block
					&& textList.get(i).replaceAll("\\s+", "").length() > 40) {
				if (textEndList.get(i - 1) == textBeginList.get(i - 1) + _block
						&& textList.get(i - 1).replaceAll("\\s+", "").length() < 40) {
					continue;
				}
				textList.set(i - 1, textList.get(i - 1) + textList.get(i));
				textEndList.set(i - 1, textEndList.get(i));

				textList.remove(i);
				textBeginList.remove(i);
				textEndList.remove(i);
				--i;
			}
		}

		String result = "";
		int i = 0;
		int j = 0;
		for (String text : textList) {
			// System.out.println("text:" + text + "\n" +
			// text.replaceAll("\\s+", "").length());
			if (text.replaceAll("\\s+", "").length() > result.replaceAll("\\s+", "").length()) {
				result = text;
				j = i;
			}
			i++;
		}

		// 最长块长度小于100，归为非主题型网页
		if (result.replaceAll("\\s+", "").length() < 100) {
			_text = "*推测您提供的网页为非主题型网页，目前暂不处理！:-)";
			return false;
		} else {
			_text = result;
			String title = _title;
			extractTitle(_text, textList, j);
			if (StringUtils.isEmpty(_title)) {
				_title = title;
			}
			return true;
		}

	}

	/**
	 * Checks if is content page.
	 * 
	 * @param htmlText
	 *            the html text
	 * 
	 * @return true, if is content page
	 */
	private boolean isContentPage(String htmlText) {
		int count = 0;
		for (int i = 0; i < htmlText.length() && count < 15; i++) {
			if (htmlText.charAt(i) == '，' || htmlText.charAt(i) == '。')
				count++;
		}

		return count >= 15;
	}

	/**
	 * Extract title.
	 * 
	 * @param htmlText
	 *            the html text
	 */
	private void getTitle(String htmlText) {
		Matcher m1 = _titleRegexPattern.matcher(htmlText);

		if (m1.find()) {
			_title = replaceSpecialChar(m1.group(1));
		}
		_title = _title.replaceAll("\n+", "");
	}

	private void extractTitle(String content, List<String> textList, int indexof) {
		IndexAnalysis analysis = new IndexAnalysis();

		float maxScore = 0;
		String title = null;

		int k = 0;
		for (int i = 0; i < indexof; i++) {

			StringBuffer sb = new StringBuffer();

			String[] strs = textList.get(i).replace(" ", "").replace("\\s", "").split("\n");
			for (String str : strs) {
				if (StringUtils.isNotBlank(str)) {
					sb.append(str);
					break;
				}
			}

			List<Term> terms = analysis.parse(sb.toString());
			float score = 0;
			for (int j = 0; j < terms.size(); j++) {
				float count = StringUtils.countMatches(content, terms.get(j).getRealName());
				score = score + count;
			}
			score = score / ((indexof - i) * 10);

			if (score > maxScore) {
				title = sb.toString();
				maxScore = score;
				k = i;
			}
			//System.out.println("title:" + sb + score);
		}

		List<Term> terms = analysis.parse(this._title);
		float score = 0;
		for (int j = 0; j < terms.size(); j++) {
			float count = StringUtils.countMatches(content, terms.get(j).getRealName());
			score = score + count;
		}
		score = score / ((indexof - k + 1) * 10);

		//System.out.println("<title>:" + _title + score);
		if (score > maxScore) {
			maxScore = score;
		} else {
			this._title = title;
		}
	}

	/**
	 * Gets the title.
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return _title;
	}

	/**
	 * Gets the text.
	 * 
	 * @return the text
	 */
	public String getText() {
		return _text;
	}

	/**
	 * Line block distribute.
	 * 
	 * @param lines
	 *            the lines
	 * 
	 * @return the list< integer>
	 */
	private List<Integer> lineBlockDistribute(List<String> lines) {
		List<Integer> indexDistribution = new ArrayList<Integer>();

		for (int i = 0; i < lines.size(); i++) {
			indexDistribution.add(lines.get(i).replaceAll("\\s+", "").length());
		}
		// 删除上下存在两个空行的文字行
		for (int i = 0; i + 4 < lines.size(); i++) {
			if (indexDistribution.get(i) == 0 && indexDistribution.get(i + 1) == 0 && indexDistribution.get(i + 2) > 0
					&& indexDistribution.get(i + 2) < 10 && indexDistribution.get(i + 3) == 0
					&& indexDistribution.get(i + 4) == 0) {
				// System.out.println("line:" + lines.get(i+2));
				lines.set(i + 2, "");
				indexDistribution.set(i + 2, 0);
				i += 3;
			}
		}

		for (int i = 0; i < lines.size() - _block; i++) {
			int wordsNum = indexDistribution.get(i);
			for (int j = i + 1; j < i + _block && j < lines.size(); j++) {
				wordsNum += indexDistribution.get(j);
			}
			indexDistribution.set(i, wordsNum);
		}

		return indexDistribution;
	}

	/**
	 * Pre processing.
	 * 
	 * @param htmlText
	 *            the html text
	 * 
	 * @return the string
	 */
	private String preProcess(String htmlText) {
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

		return replaceSpecialChar(htmlText);
	}

	/**
	 * Replace special char.
	 * 
	 * @param content
	 *            the content
	 * 
	 * @return the string
	 */
	private String replaceSpecialChar(String content) {
		String text = content.replaceAll("&quot;", "\"");
		text = text.replaceAll("&ldquo;", "“");
		text = text.replaceAll("&rdquo;", "”");
		text = text.replaceAll("&middot;", "·");
		text = text.replaceAll("&#8231;", "·");
		text = text.replaceAll("&#8212;", "——");
		text = text.replaceAll("&#28635;", "濛");
		text = text.replaceAll("&hellip;", "…");
		text = text.replaceAll("&#23301;", "嬅");
		text = text.replaceAll("&#27043;", "榣");
		text = text.replaceAll("&#8226;", "·");
		text = text.replaceAll("&#40;", "(");
		text = text.replaceAll("&#41;", ")");
		text = text.replaceAll("&#183;", "·");
		text = text.replaceAll("&amp;", "&");
		text = text.replaceAll("&bull;", "·");
		//text = text.replaceAll("&lt;", "<");
		//text = text.replaceAll("&#60;", "<");
		//text = text.replaceAll("&gt;", ">");
		//text = text.replaceAll("&#62;", ">");
		text = text.replaceAll("&nbsp;", " ");
		text = text.replaceAll("&#160;", " ");
		text = text.replaceAll("&tilde;", "~");
		text = text.replaceAll("&mdash;", "—");
		text = text.replaceAll("&copy;", "@");
		text = text.replaceAll("&#169;", "@");
		text = text.replaceAll("♂", "");
		text = text.replaceAll("\r\n|\r", "\n");

		return text;
	}

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the args
	 */
	public static void main(String[] args) {
		// http://baike.baidu.com/view/25215.htm
		// http://hi.baidu.com/handylee/blog/item/6523c4fc35a235fffc037fc5.html
		// http://www.techweb.com.cn/news/2010-08-11/659082.shtml
		// http://hi.baidu.com/fandywang_jlu/blog/item/dfe2f0134907142edd5401d7.html
		// http://www.ifanr.com/15876

		TextExtractor te = new TextExtractor();
//		te.extractURL("http://www.jshrss.gov.cn/sy/zcfg/201311/t20131127_129440.html");
		try {
			
			FileReader fr=new FileReader("./html/中共云南省委关于中央巡视组反馈意见整改情况的通报.htm");
	        //可以换成工程目录下的其他文本文件
	        BufferedReader br=new BufferedReader(fr);
	        StringBuffer sb = new StringBuffer();
	        
	        while(br.readLine()!=null){
	            sb.append(br.readLine()+"\n");
	        }
	        br.close();
	        te.extractHTML(sb.toString());
			
			System.out.println("realtitle:" + te.getTitle());
			System.out.println("content:" + te.getText());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}

}
