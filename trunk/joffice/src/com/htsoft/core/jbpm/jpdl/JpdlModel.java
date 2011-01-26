package com.htsoft.core.jbpm.jpdl;

import java.awt.Point;
import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.dom4j.Element;

import com.htsoft.core.util.XmlUtil;

public class JpdlModel {
	private Set<String> activityNames = new HashSet();

	private Map<String, Node> nodes = new LinkedHashMap();
	public static final int RECT_OFFSET_X = -4;
	public static final int RECT_OFFSET_Y = -8;
	public static final int DEFAULT_PIC_SIZE = 48;
	private static final Map<String, Object> nodeInfos = new HashMap();

	static {
		nodeInfos.put("start", "start_event_empty.png");
		nodeInfos.put("end", "end_event_terminate.png");
		nodeInfos.put("end-cancel", "end_event_cancel.png");
		nodeInfos.put("end-error", "end_event_error.png");
		nodeInfos.put("decision", "gateway_exclusive.png");
		nodeInfos.put("fork", "gateway_parallel.png");
		nodeInfos.put("join", "gateway_parallel.png");
		nodeInfos.put("state", null);
		nodeInfos.put("hql", null);
		nodeInfos.put("sql", null);
		nodeInfos.put("java", null);
		nodeInfos.put("script", null);
		nodeInfos.put("task", null);
		nodeInfos.put("sub-process", null);
		nodeInfos.put("custom", null);
	}

	public JpdlModel(String defXml) throws Exception {
		this(XmlUtil.stringToDocument(defXml).getRootElement());
	}

	public JpdlModel(InputStream is) throws Exception {
		this(XmlUtil.load(is).getRootElement());
	}

	private JpdlModel(Element rootEl) throws Exception {
		List<Element> elements = rootEl.elements();
		for (Element el : elements) {
			String type = el.getQName().getName();
			if (!nodeInfos.containsKey(type)) {
				continue;
			}
			String name = null;
			if (el.attribute("name") != null) {
				name = el.attributeValue("name");
			}
			String[] location = el.attributeValue("g").split(",");
			int x = Integer.parseInt(location[0]);
			int y = Integer.parseInt(location[1]);
			int w = Integer.parseInt(location[2]);
			int h = Integer.parseInt(location[3]);

			if (nodeInfos.get(type) != null) {
				w = 48;
				h = 48;
			} else {
				x += 4;
				y += 8;
				w -= 8;
				h -= 16;
			}
			Node node = new Node(name, type, x, y, w, h);
			parserTransition(node, el);
			this.nodes.put(name, node);
		}
	}

	public Set<String> getActivityNames() {
		return this.activityNames;
	}

	public void setActivityNames(Set<String> activityNames) {
		this.activityNames = activityNames;
	}

	private void parserTransition(Node node, Element nodeEl) {
		List<Element> elements = nodeEl.elements("transition");
		for (Element el : elements) {
			String label = el.attributeValue("name");
			String to = el.attributeValue("to");
			Transition transition = new Transition(label, to);
			String g = el.attributeValue("g");
			if ((g != null) && (g.length() > 0)) {
				if (g.indexOf(":") < 0) {
					transition.setLabelPosition(getPoint(g));
				} else {
					String[] p = g.split(":");
					transition.setLabelPosition(getPoint(p[1]));
					String[] lines = p[0].split(";");
					for (String line : lines) {
						transition.addLineTrace(getPoint(line));
					}
				}
			}
			node.addTransition(transition);
		}
	}

	private Point getPoint(String exp) {
		if ((exp == null) || (exp.length() == 0)) {
			return null;
		}
		String[] p = exp.split(",");
		return new Point(Integer.valueOf(p[0]).intValue(), Integer.valueOf(p[1]).intValue());
	}

	public Map<String, Node> getNodes() {
		return this.nodes;
	}

	public static Map<String, Object> getNodeInfos() {
		return nodeInfos;
	}
}
