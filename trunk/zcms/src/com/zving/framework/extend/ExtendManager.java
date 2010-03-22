package com.zving.framework.extend;

import com.zving.framework.Config;
import com.zving.framework.utility.LogUtil;
import com.zving.framework.utility.Mapx;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ExtendManager {
	private static Mapx map;

	public static void loadConfig() {
		if (map == null) {
			map = new Mapx();
			String path = Config.getContextRealPath() + "WEB-INF/classes/framework.xml";
			if (!(new File(path).exists())) {
				return;
			}
			SAXReader reader = new SAXReader(false);
			try {
				Document doc = reader.read(new File(path));
				Element root = doc.getRootElement();
				Element extend = root.element("extend");
				if (extend != null) {
					List types = extend.elements("action");
					for (int i = 0; i < types.size(); ++i) {
						Element type = (Element) types.get(i);
						String className = type.attributeValue("class");
						try {
							Object obj = Class.forName(className).newInstance();
							if (!(obj instanceof IExtendAction)) {
								LogUtil.getLogger().warn("类" + className + "必须继承IExtendAction!");

							}
							IExtendAction action = (IExtendAction) obj;
							ArrayList list = (ArrayList) map.get(action.getTarget());
							if (list == null) {
								list = new ArrayList();
							}
							list.add(action);
							map.put(action.getTarget(), list);
						} catch (InstantiationException e) {
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						} catch (ClassNotFoundException e) {
							e.printStackTrace();
						}
					}
				}
			} catch (DocumentException e) {
				e.printStackTrace();
			}
		}
	}

	public static void addExtendAction(IExtendAction action, String target) {
		synchronized (ExtendManager.class) {
			ArrayList list = (ArrayList) map.get(target);
			if (list == null) {
				list = new ArrayList();
			}
			for (int i = 0; i < list.size(); ++i) {
				if (list.get(i).getClass().getName().equals(action.getClass().getName())) {
					return;
				}
			}
			list.add(action);
		}
	}

	public static boolean hasAction(String target) {
		return (map.get(target) != null);
	}

	public static IExtendAction[] find(String target) {
		ArrayList list = (ArrayList) map.get(target);
		if (list == null) {
			return new IExtendAction[0];
		}
		IExtendAction[] arr = new IExtendAction[list.size()];
		for (int i = 0; i < arr.length; ++i) {
			arr[i] = ((IExtendAction) list.get(i));
		}
		return arr;
	}

	public static void executeAll(String target, Object[] args) {
		IExtendAction[] actions = find(target);
		for (int i = 0; i < actions.length; ++i)
			actions[i].execute(args);
	}
}

/*
 * Location: F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\ Qualified Name:
 * com.zving.framework.extend.ExtendManager JD-Core Version: 0.5.3
 */