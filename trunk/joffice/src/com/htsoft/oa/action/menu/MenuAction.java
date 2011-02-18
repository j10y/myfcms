package com.htsoft.oa.action.menu;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;

import com.google.gson.Gson;
import com.htsoft.core.util.AppUtil;
import com.htsoft.core.util.ContextUtil;
import com.htsoft.core.util.XmlUtil;
import com.htsoft.core.web.action.BaseAction;

public class MenuAction extends BaseAction {
	private String id = null;
	private static final String USER_MENU_DOC = "_USER_MENU_DOC";

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	private Document getCurDocument() {
		HttpSession session = getSession();
		Document userDoc = (Document) session.getAttribute("_USER_MENU_DOC");
		if (userDoc != null) {
			return userDoc;
		}

		Document doc = AppUtil.getLeftMenuDocument();
		Set rights = ContextUtil.getCurrentUser().getRights();

		if (rights.contains("__ALL")) {
			return doc;
		}

		rights.addAll(AppUtil.getPublicMenuIds());

		Document newDoc = DocumentHelper.createDocument();
		Element root = newDoc.addElement("Menus");

		createSubMenus(rights, doc.getRootElement(), root);

		session.setAttribute("_USER_MENU_DOC", newDoc);
		return newDoc;
	}

	private Document getModuleDocument() {
		String topMenuId = getRequest().getParameter("topMenuId");
		if (StringUtils.isEmpty(topMenuId)) {
			topMenuId = "oa";
		}

		String menuXmlPath = AppUtil.getAppAbsolutePath() + "/js/menu-" + topMenuId + ".xml";

		Document doc = XmlUtil.load(menuXmlPath);

		return doc;
	}

	private void createSubMenus(Set<String> rights, Element curNodes, Element newCurNodes) {
		List els = curNodes.elements();
		if (els.size() == 0)
			return;

		for (int i = 0; i < els.size(); ++i) {
			Element el = (Element) els.get(i);
			Attribute id = el.attribute("id");
			if (id != null) {
				String idVal = id.getValue();
				if ((rights.contains(idVal)) || (idVal == null)) {
					Element newNodes = newCurNodes.addElement(el.getName());
					Iterator it = el.attributeIterator();

					while (it.hasNext()) {
						Attribute at = (Attribute) it.next();
						newNodes.addAttribute(at.getName(), at.getValue());
					}
					createSubMenus(rights, el, newNodes);
				}
			}
		}
	}

	public String items() {
		Document doc = getCurDocument();

		if (doc != null) {
			StringBuffer sb = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r");

			Element el = doc.getRootElement();

			List nodes = el.selectNodes("/Menus/Items[@id='" + this.id + "']");

			sb.append("<Menus>\r");
			for (int i = 0; i < nodes.size(); ++i) {
				Node node = (Node) nodes.get(i);
				sb.append(node.asXML());
			}

			sb.append("\r</Menus>\r");
			setJsonString(sb.toString());
		}

		return "success";
	}

	public String models() {
		Document doc = getCurDocument();
		StringBuffer sb = new StringBuffer("[");

		if (doc != null) {
			Element root = doc.getRootElement();
			List els = root.elements();

			for (int i = 0; i < els.size(); ++i) {
				Element el = (Element) els.get(i);

				Attribute id = el.attribute("id");
				Attribute text = el.attribute("text");
				Attribute iconCls = el.attribute("iconCls");

				sb.append("{id:'").append((id == null) ? "" : id.getValue()).append("',");
				sb.append("text:'").append((text == null) ? "" : text.getValue()).append("',");
				sb.append("iconCls:'").append((iconCls == null) ? "" : iconCls.getValue()).append(
						"'},");
			}

			if (els.size() > 0) {
				sb.deleteCharAt(sb.length() - 1);
			}
		}

		sb.append("]");
		setJsonString(sb.toString());
		return "success";
	}

	public String panelTree() {
		Gson gson = new Gson();
		Document doc = getCurDocument();

		StringBuffer sb = new StringBuffer("[");

		if (doc != null) {
			Element root = doc.getRootElement();
			List els = root.elements();

			for (int i = 0; i < els.size(); ++i) {
				Element el = (Element) els.get(i);

				Attribute id = el.attribute("id");
				Attribute text = el.attribute("text");
				Attribute iconCls = el.attribute("iconCls");

				sb.append("{id:'").append((id == null) ? "" : id.getValue()).append("',");
				sb.append("text:'").append((text == null) ? "" : text.getValue()).append("',");
				sb.append("iconCls:'").append((iconCls == null) ? "" : iconCls.getValue()).append(
						"',");
				sb.append("subXml:").append(gson.toJson(getModelXml(doc, id.getValue()))).append(
						"},");
			}

			if (els.size() > 0) {
				sb.deleteCharAt(sb.length() - 1);
			}
		}

		sb.append("]");
		setJsonString(sb.toString());

		return "success";
	}

	protected String getModelXml(Document doc, String modelId) {
		StringBuffer sb = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r");

		Element el = doc.getRootElement();

		List nodes = el.selectNodes("/Menus/Items[@id='" + modelId + "']");

		sb.append("<Menus>\r");
		for (int i = 0; i < nodes.size(); ++i) {
			Node node = (Node) nodes.get(i);
			sb.append(node.asXML());
		}
		sb.append("\r</Menus>\r");

		return sb.toString();
	}
}


 
 
 
 