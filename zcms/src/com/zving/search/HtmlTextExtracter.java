package com.zving.search;

import com.zving.framework.utility.StringUtil;
import com.zving.framework.utility.Treex;
import com.zving.framework.utility.Treex.TreeIterator;
import com.zving.framework.utility.Treex.TreeNode;
import com.zving.framework.utility.Treex.TreeNodeList;
import com.zving.framework.utility.XMLUtil;
import java.io.PrintStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.html.dom.HTMLDocumentImpl;
import org.cyberneko.html.parsers.DOMFragmentParser;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.html.HTMLDocument;
import org.xml.sax.InputSource;

public class HtmlTextExtracter {
	private String html;
	private String url;
	private Node body;
	private Treex tree;
	private Pattern pKeywords = Pattern.compile(
			"<meta name=[\\\"\\']keywords[\\\"\\'] content=[\\\"\\'](.*?)[\\\"\\']>", 34);

	private Pattern pDescription = Pattern.compile(
			"<meta name=[\\\"\\']description[\\\"\\'] content=[\\\"\\'](.*?)[\\\"\\']>", 34);

	private static Pattern clearPattern = Pattern.compile("[\\p{Punct}\\s*　]", 32);

	public String getHtml() {
		return this.html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public String getContentText() {
		try {
			return extract(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getContentHtml() {
		try {
			return extract(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private String extract(boolean textflag) throws Exception {
		Document doc = XMLUtil.htmlToXercesDocument(this.html);
		this.body = doc.getElementsByTagName("body").item(0);
		this.tree = new Treex();
		if (this.body == null) {
			System.out.println("没有找到Body节点:" + this.url);
			return null;
		}
		TextWeight tw = getPureTextWeight(this.body, this.tree.getRoot());
		this.tree.getRoot().setData(tw);

		Treex.TreeIterator ti = this.tree.iterator();
		ArrayList list = new ArrayList();
		while (ti.hasNext()) {
			Treex.TreeNode tn = (Treex.TreeNode) ti.next();
			tw = (TextWeight) tn.getData();
			if ((tw.Weight > 1600) && (tw.Text != null) && (tw.Text.length() > 10)) {
				list.add(tn);
			}

		}

		if (list.size() == 0) {
			return "";
		}

		Treex.TreeNode maxNode = (Treex.TreeNode) list.get(0);
		tw = (TextWeight) maxNode.getData();
		for (int i = 1; i < list.size(); ++i) {
			Treex.TreeNode node = (Treex.TreeNode) list.get(i);
			TextWeight tw2 = (TextWeight) node.getData();
			if (tw2.Weight > tw.Weight) {
				maxNode = node;
				tw = tw2;
			}
		}

		Treex.TreeNode[] tnArr = this.tree.toArray();

		while ((isCopyrightNode(maxNode, tnArr)) || (!(isReadableNode(maxNode, tnArr)))) {
			list.remove(maxNode);
			if (list.size() == 0) {
				return "";
			}
			maxNode = (Treex.TreeNode) list.get(0);
			tw = (TextWeight) maxNode.getData();
			for (int i = 1; i < list.size(); ++i) {
				Treex.TreeNode node = (Treex.TreeNode) list.get(i);
				TextWeight tw2 = (TextWeight) node.getData();
				if (tw2.Weight > tw.Weight) {
					maxNode = node;
					tw = tw2;
				}
			}
		}

		Treex.TreeNode[] contentArr = (Treex.TreeNode[]) null;

		Treex.TreeNode pn = maxNode.getParent();
		while (pn != null) {
			Treex.TreeNodeList tns = pn.getChildren();
			if (tns.size() > 10) {
				break;
			}
			int count = 0;
			for (int i = 0; i < tns.size(); ++i) {
				TextWeight w = (TextWeight) tns.get(i).getData();
				if (w.Weight > 40) {
					++count;
				}
			}
			if (count > 3) {
				break;
			}
			pn = pn.getParent();
		}
		if ((pn == null) || (pn.getParent() == null)) {
			contentArr = new Treex.TreeNode[] { maxNode };
		} else {
			TextWeight pw = (TextWeight) pn.getData();
			if (pw.Weight - tw.Weight > 1000) {
				int level = pn.getLevel() + 1;
				pn = maxNode;
				while (pn.getLevel() > level) {
					pn = pn.getParent();
				}
				int maxIndex = 0;
				for (int i = 0; i < tnArr.length; ++i) {
					if (tnArr[i] == pn) {
						maxIndex = i;
						break;
					}
				}
				int startIndex = maxIndex;
				int endIndex = maxIndex;

				int count = 0;
				TextWeight w;
				for (int i = maxIndex - 1; i > 0; --i) {
					if (tnArr[i].getLevel() < pn.getLevel()) {
						break;
					}
					w = (TextWeight) tnArr[i].getData();
					if (w.Node.getNodeName().equalsIgnoreCase("img")) {
						count = 0;
					}
					if ((w.Weight == 0) || (w.Weight < 400) || (w.LinkCount > 0)) {
						if (w.LinkCount > 0) {
							count += w.LinkCount;
						}
						if (w.Weight == 0) {
							++count;
						}
						if (StringUtil.isNotEmpty(w.Text)) {
							--count;
						}
						if (count <= 10)
							break;
					}

					count = 0;

					if ((tnArr[i].getLevel() != pn.getLevel())
							|| ((w.Weight <= 0) && (!(w.Node.getNodeName().equalsIgnoreCase("img")))))
						continue;
					startIndex = i;
				}

				count = 0;
				for (int i = maxIndex + 1; i < tnArr.length; ++i) {
					if (tnArr[i].getLevel() < pn.getLevel()) {
						break;
					}
					w = (TextWeight) tnArr[i].getData();
					if (w.Node.getNodeName().equalsIgnoreCase("img")) {
						count = 0;
					}
					if ((w.Weight == 0) || (w.Weight < 400) || (w.LinkCount > 0)) {
						if (w.LinkCount > 0) {
							count += w.LinkCount;
						}
						if (w.Weight == 0) {
							++count;
						}
						if (StringUtil.isNotEmpty(w.Text)) {
							--count;
						}
						if (count <= 10)

							break;
					}

					count = 0;

					if (tnArr[i].getLevel() == pn.getLevel()) {
						if (isRelaArticle(tnArr[i], tnArr)) {
							break;
						}
						if ((w.Weight > 0) || (w.Node.getNodeName().equalsIgnoreCase("img"))) {
							endIndex = i;
						}
					}
				}
				int size = 0;
				int linkCount = 0;
				int textCount = 0;
				for (int i = startIndex; i <= endIndex; ++i) {
					if (tnArr[i].getLevel() == pn.getLevel()) {
						w = (TextWeight) tnArr[i].getData();
						if ((w.LinkCount >= 1) && (w.Weight > 40) && (w.Weight < 20000)) {
							++linkCount;
						}
						if (w.Weight > 40) {
							++textCount;
						}
						if (w.Node.getNodeName().equalsIgnoreCase("img")) {
							++textCount;
						}
						++size;
					}
				}
				if (linkCount * 1.0D / textCount > 0.8D) {
					if (isListPage()) {
						return getMetaText();
					}
					return "";
				}
				Treex.TreeNode[] rArr = new Treex.TreeNode[size];
				int i = startIndex;
				for (int j = 0; i <= endIndex; ++i) {
					if (tnArr[i].getLevel() == pn.getLevel()) {
						rArr[(j++)] = tnArr[i];
					}
				}
				contentArr = rArr;
			} else {
				contentArr = new Treex.TreeNode[] { maxNode };
			}
		}

		if (contentArr != null) {
			String text = getNodesText(contentArr);
			if ((text.length() < 100) && (isListPage())) {
				return getMetaText();
			}

			if (textflag) {
				return text;
			}
			return getNodesHtml(contentArr);
		}

		return "";
	}

	private boolean isCopyrightNode(Treex.TreeNode tn, Treex.TreeNode[] arr) {
		int index = 0;
		for (int i = 0; i < arr.length; ++i) {
			if (tn == arr[i]) {
				index = i;
				break;
			}
		}
		if (index * 1.0D / arr.length > 0.9D) {
			return true;
		}
		Treex.TreeNode pn = tn.getParent().getParent();
		String text = getPureText(getDOMNode(pn)).toLowerCase();

		return ((text.indexOf("版权") >= 0) || (text.indexOf("copyright") >= 0));
	}

	private boolean isReadableNode(Treex.TreeNode tn, Treex.TreeNode[] arr) {
		Treex.TreeNode pn = tn;
		while (pn != null) {
			Node node = getDOMNode(pn);
			if (node.hasAttributes()) {
				node = node.getAttributes().getNamedItem("style");
				if (node != null) {
					String style = node.getNodeValue();
					Pattern p = Pattern.compile("\\sdisplay\\s*\\:\\s*none\\s", 2);
					if (p.matcher(style).find()) {
						return false;
					}
				}
			}
			pn = pn.getParent();
		}
		int index = 0;
		for (int i = 0; i < arr.length; ++i) {
			if (tn == arr[i]) {
				index = i;
				break;
			}
		}

		if (index * 1.0D / arr.length > 0.9D) {
			String text = getNodesText(new Treex.TreeNode[] { tn });
			int count = 0;
			for (int i = 0; i < text.length(); ++i) {
				if (!(Character.isJavaIdentifierPart(text.charAt(i)))) {
					++count;
					if (count > 20) {
						return false;
					}
				}
			}
		}
		return true;
	}

	private boolean isRelaArticle(Treex.TreeNode tn, Treex.TreeNode[] arr) {
		String text = getPureText(getDOMNode(tn)).replaceAll("\\s", "");
		if ((text.length() < 8)
				&& (((text.indexOf("相关阅读") >= 0) || (text.indexOf("相关文章") >= 0)
						|| (text.indexOf("更多文章") >= 0) || (text.indexOf("更多阅读") >= 0)))) {
			int index = 0;
			for (int i = 0; i < arr.length; ++i) {
				if (tn == arr[i]) {
					index = i;
					break;
				}
			}
			for (int i = index + 1; i < arr.length; ++i) {
				index = i;
				if (arr[i].getLevel() == tn.getLevel()) {
					break;
				}
			}
			for (int i = index; i < arr.length; ++i) {
				TextWeight tw = (TextWeight) arr[i].getData();
				if (tw.Weight == 0) {
					continue;
				}
				if ((tw.Weight != 0) && (tw.LinkCount == 0)) {
					return false;
				}
				if (tw.LinkCount > 0) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean isListPage() {
		if (StringUtil.isNotEmpty(this.url)) {
			if (this.url.endsWith("/")) {
				return true;
			}
			if (this.url.indexOf("/", 8) < 0) {
				return true;
			}
			if ((this.url.toLowerCase().indexOf("index") > 0)
					|| (this.url.toLowerCase().indexOf("list") > 0)) {
				return true;
			}
		}
		return false;
	}

	private String getMetaText() {
		if ((this.url.indexOf("/", 8) < 0) || (this.url.indexOf("/") == this.url.length() - 1)) {
			String keyword = null;
			String description = null;
			Matcher m = this.pKeywords.matcher(this.html);
			if (m.find()) {
				keyword = m.group(1);
			}
			m = this.pDescription.matcher(this.html);
			if (m.find()) {
				description = m.group(1);
			}
			return description + " \n关键字：" + keyword;
		}
		return "";
	}

	private TextWeight getPureTextWeight(Node node, Treex.TreeNode tnode) {
		TextWeight tw = new TextWeight();
		tw.Node = node;
		Treex.TreeNode tn = tnode.addChild(tw);
		if (!(isFiltered(node))) {
			if (!(node.hasChildNodes())) {
				if (XMLUtil.isTextNode(node)) {
					String str = StringUtil.htmlDecode(node.getNodeValue());
					node.setNodeValue(str);
					str = clearPattern.matcher(str).replaceAll("");
					int l = StringUtil.lengthEx(str);
					int s = l * l;
					if (node.getNodeName().equalsIgnoreCase("a")) {
						tw.Weight = s;
						tw.LinkCount = 1;
						tw.Text = str;
					} else {
						tw.Weight = s;
						tw.LinkCount = 0;
						tw.Text = str;
					}
				}
			} else {
				NodeList list = node.getChildNodes();
				for (int i = 0; i < list.getLength(); ++i) {
					TextWeight cw = getPureTextWeight(list.item(i), tn);
					tw.LinkCount += cw.LinkCount;
					tw.Weight += cw.Weight;
				}
				if (node.getNodeName().equalsIgnoreCase("a")) {
					tw.LinkCount += 1;
				}
			}
		}

		return tw;
	}

	private String getNodesText(Treex.TreeNode[] tns) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < tns.length; ++i) {
			if (i != 0) {
				sb.append(" ");
			}
			sb.append(getPureText(getDOMNode(tns[i])));
		}
		return sb.toString().replaceAll("[\\s　]{2,}", " ");
	}

	private String getNodesHtml(Treex.TreeNode[] tns) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < tns.length; ++i) {
			if (i != 0) {
				sb.append("\n");
			}
			sb.append(XMLUtil.toString(getDOMNode(tns[i])));
		}
		return sb.toString().replaceAll("\\r\\n", "\n").replaceAll("\\n{2,}", "\n");
	}

	private Node getDOMNode(Treex.TreeNode tn) {
		return ((TextWeight) tn.getData()).Node;
	}

	public static String getPureText(String html) {
		DOMFragmentParser parser = new DOMFragmentParser();
		HTMLDocument document = new HTMLDocumentImpl();
		DocumentFragment fragment = document.createDocumentFragment();
		try {
			parser.parse(new InputSource(new StringReader(html)), fragment);
			String txt = getPureText(fragment);
			return StringUtil.htmlDecode(txt);
		} catch (Exception e) {
			System.out.println("XML中存在非法字符!");
		}

		return null;
	}

	public static String getPureText(Node node) {
		if ((!(node.hasChildNodes())) && (XMLUtil.isTextNode(node))) {
			return node.getNodeValue();
		}

		if (isFiltered(node)) {
			return "";
		}

		if (node.hasAttributes()) {
			Node a = node.getAttributes().getNamedItem("style");
			if (a != null) {
				String style = a.getNodeValue();
				Pattern p = Pattern.compile("display\\s*\\:\\s*none", 2);
				if (p.matcher(style).find()) {
					return "";
				}
			}
		}
		StringBuffer sb = new StringBuffer();
		NodeList list = node.getChildNodes();
		for (int i = 0; i < list.getLength(); ++i) {
			Node child = list.item(i);
			String name = child.getNodeName();
			sb.append(getPureText(child));
			sb.append(" ");
			if ((name.equals("TR")) || (name.equals("P")) || (name.equals("DIV"))) {
				sb.append("\n");
			}
		}
		return sb.toString();
	}

	private static boolean isFiltered(Node node) {
		short type = node.getNodeType();
		String name = node.getNodeName();
		if (type == 8) {
			return true;
		}
		if (name.equals("SCRIPT")) {
			return true;
		}
		if (name.equals("LINK")) {
			return true;
		}
		if (name.equals("STYLE")) {
			return true;
		}

		return (name.equals("OBJECT"));
	}

	public void trimTree(Treex.TreeNode node) {
		for (int i = node.getChildren().size() - 1; i >= 0; --i) {
			TextWeight tw = (TextWeight) node.getChildren().get(i).getData();
			if (tw.Weight < 625) {
				node.removeChild(tw);
			}
		}
		for (int i = 0; i < node.getChildren().size(); ++i)
			trimTree(node.getChildren().get(i));
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Treex getTree() {
		return this.tree;
	}

	public static class TextWeight {
		public int Weight;
		public int LinkCount;
		public String Text;
		public Node Node;

		public String toString() {
			return "{" + this.Weight + "," + this.LinkCount
					+ ((StringUtil.isNotEmpty(this.Text)) ? "," + this.Text : "") + "}";
		}
	}
}

/*
 * Location: F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\ Qualified Name:
 * com.zving.search.HtmlTextExtracter JD-Core Version: 0.5.3
 */