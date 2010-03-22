package com.zving.search.index;

import com.zving.framework.utility.FileUtil;
import com.zving.framework.utility.Mapx;
import com.zving.framework.utility.NumberUtil;
import com.zving.framework.utility.StringUtil;
import com.zving.search.HtmlTextExtracter;
import java.io.IOException;
import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleFragmenter;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.wltea.analyzer.IKSegmentation;
import org.wltea.analyzer.Lexeme;
import org.wltea.analyzer.lucene.IKAnalyzer;
import org.wltea.analyzer.lucene.IKQueryParser;

public class IndexUtil {
	private static String filterWords;
	private static String filterChars;

	public static String getTextFromHtml(String html) {
		String text = HtmlTextExtracter.getPureText(html);
		if (StringUtil.isEmpty(text)) {
			text = StringUtil.clearHtmlTag(html);
		}
		return text.replaceAll("[\\s\\u0020　]{2,}", " ");
	}

	public static String[] getKeyword(String content) {
		content = getTextFromHtml(content);
		IKSegmentation seg = new IKSegmentation(new StringReader(content));
		Mapx map = new Mapx();
		ArrayList list = new ArrayList();
		try {
			Lexeme word = seg.next();
			while (word != null) {
				String k = word.getLexemeText();
				if ((k != null) && (k.length() != 1)) {
					if (map.containsKey(k))
						map.put(k, new Integer(
								((Integer) map.get(word.getLexemeText())).intValue() + 1));
					else {
						map.put(k, new Integer(1));
					}
				}
				word = seg.next();
			}

			Object[] ks = map.keyArray();
			Object[] vs = map.valueArray();
			ArrayList arr = new ArrayList();
			for (int i = 0; i < ks.length; ++i) {
				String k = ks[i].toString();
				if (!(filter(k))) {
					continue;
				}
				int count = ((Integer) vs[i]).intValue();

				for (int j = 0; j < ks.length; ++j) {
					if ((j == i) || (ks[j].toString().indexOf(k) < 0))
						continue;
					int otherCount = ((Integer) vs[j]).intValue();
					count -= otherCount;
				}

				arr.add(new Object[] { k, new Integer(count) });
			}
			Collections.sort(arr, new Comparator() {
				public int compare(Object o1, Object o2) {
					Object[] arr1 = (Object[]) o1;
					Object[] arr2 = (Object[]) o2;
					Integer i1 = (Integer) arr1[1];
					Integer i2 = (Integer) arr2[1];
					return (i2.intValue() - i1.intValue());
				}
			});
			int count;
			for (int i = 0; i < arr.size(); ++i) {
				Object[] wordArr = (Object[]) arr.get(i);
				String k = wordArr[0].toString();
				count = ((Integer) wordArr[1]).intValue();
				if (count == 1)
					continue;
				if (list.contains(k)) {
					continue;
				}
				if (list.size() < 3) {
					list.add(k);
				} else if (list.size() == 3) {
					if (count > 15)
						list.add(k);
				} else {
					if (list.size() != 4)
						break;
					if (count > 20) {
						list.add(k);
					}

				}

			}

			if (list.size() > 0 && list.size() <= 3 && arr.size() > list.size()) {
				int lastCount = ((Integer) ((Object[]) arr.get(list.size() - 1))[1]).intValue();
				for (int i = list.size(); i < 5 && i < arr.size(); i++) {
					Object wordArr[] = (Object[]) arr.get(i);
					count = ((Integer) wordArr[1]).intValue();
					if (count >= lastCount - 1 && !list.contains(wordArr[0]))
						list.add(wordArr[0]);
				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		String[] arr = new String[list.size()];
		for (int i = 0; i < list.size(); ++i) {
			arr[i] = list.get(i).toString();
		}
		return arr;
	}

	public static boolean filter(String word) {
		if ((filterWords == null) || (filterChars == null)) {
			try {
				filterWords = FileUtil.readText(IndexUtil.class.getResource("wordfilter.dic")
						.openStream(), "UTF-8");
				filterChars = FileUtil.readText(IndexUtil.class.getResource("charfilter.dic")
						.openStream(), "UTF-8");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		if (NumberUtil.isNumber(word)) {
			return false;
		}
		if ((word == null) || (word.length() < 2)) {
			return false;
		}
		if (filterWords.indexOf(word) >= 0) {
			return false;
		}
		String s = word.substring(0, 1);
		String e = word.substring(word.length() - 1);

		return ((filterChars.indexOf(s) < 0) && (filterChars.indexOf(e) < 0));
	}

	public static String getTextAbstract(String title, String content) {
		try {
			content = getTextFromHtml(content);
			Query q = IKQueryParser.parse("CONTENT", title);
			SimpleHTMLFormatter formatter = new SimpleHTMLFormatter("", "");
			Highlighter highlighter = new Highlighter(formatter, new QueryScorer(q));
			highlighter.setTextFragmenter(new SimpleFragmenter(200));
			TokenStream tokenStream = new IKAnalyzer().tokenStream("CONTENT", new StringReader(
					content));
			String tmp = highlighter.getBestFragment(tokenStream, content);
			if (StringUtil.isNotEmpty(tmp))
				content = tmp.trim();
		} catch (Exception e) {
			e.printStackTrace();
		}

		int start = 0;
		int end = 0;
		boolean startFlag = true;
		char c;
		for (int i = 0; i < content.length(); ++i) {
			c = content.charAt(i);
			if (startFlag) {
				if (Character.isWhitespace(c))
					continue;
				if (Character.isISOControl(c)) {
					continue;
				}
				if ((c == ',') || (c == 65292) || (c == 8221) || (c == 8217) || (c == '.')
						|| (c == 12290) || (c == '>') || (c == '?') || (c == 65311) || (c == ' ')
						|| (c == 12288))
					continue;
				if (c == ' ') {
					continue;
				}
				if ((c == '!') || (c == 65281) || (c == ';') || (c == 65307) || (c == ':')
						|| (c == 65306) || (c == ']'))
					continue;
				if (c == 65341) {
					continue;
				}
				start = i;
				startFlag = false;
			}
			if (!(startFlag)) {
				if ((c == '.') || (c == 12290) || (c == '?') || (c == 65311) || (c == '!')
						|| (c == 65281) || (c == ' ') || (c == 12288) || (c == ' ')) {
					if (i < 8) {
						start = i + 1;
					}
					end = i;
					if ((i == content.length() - 1)
							|| ((content.charAt(i + 1) != 8221) && (content.charAt(i + 1) != 8217)))
						continue;
					end = i + 1;
				} else {
					if ((((c == ',') || (c == 65292) || (c == '>') || (c == 12299) || (c == 12289)))
							&& (i < 2)) {
						start = i + 1;
					}

					if ((c == 8217) || (c == 8221)) {
						if (i != content.length() - 1) {
							char next = content.charAt(i + 1);
							if ((next != ',') && (next == 65292) && (next == 12289)
									&& (next == ';') && (next == 65307))
								end = i + 1;
						} else {
							end = i;
						}
					}
				}
			}
		}
		if ((end != 0) && (end > start)) {
			content = content.substring(start, end + 1).trim();

			start = 0;
			for (int i = 0; i < content.length(); ++i) {
				c = content.charAt(i);
				if (((c != '.') && (c != 12290) && (c != '?') && (c != 65311) && (c != '!')
						&& (c != 65281) && (c != ' ') && (c != 12288) && (c != ' '))
						|| (i >= 8))
					continue;
				start = i + 1;
			}

			if (start != 0) {
				content = content.substring(start);
			}
			end = 0;
			c = content.charAt(content.length() - 1);
			if ((c != '.') && (c != 12290) && (c != '?') && (c != 65311) && (c != '!')
					&& (c != 65281)) {
				for (int i = content.length() - 2; i > 0; --i) {
					c = content.charAt(i);
					if ((c == ';') || (c == 65307) || (c == ',') || (c == 65292) || (c == '>')
							|| (c == 12299)) {
						end = i;
						break;
					}
				}
			}
			if (end != 0) {
				content = content.substring(0, end);
			}
		}
		return content;
	}
}

/*
 * Location: F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\ Qualified Name:
 * com.zving.search.index.IndexUtil JD-Core Version: 0.5.3
 */