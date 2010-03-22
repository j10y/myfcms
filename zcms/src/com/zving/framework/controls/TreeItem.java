package com.zving.framework.controls;

import com.zving.framework.Config;
import com.zving.framework.data.DataRow;
import com.zving.framework.utility.Mapx;
import com.zving.framework.utility.StringUtil;

public class TreeItem extends HtmlP implements Cloneable {
	public static final String Icon_Branch_NotLast_NotExpand = "Icons/treeicon01.gif";
	public static final String Icon_Branch_Last_NotExpand = "Icons/treeicon04.gif";
	public static final String Icon_Branch_NotLast_Expand = "Icons/treeicon02.gif";
	public static final String Icon_Branch_Last_Expand = "Icons/treeicon05.gif";
	public static final String Icon_Line_Vertical = "Icons/treeicon03.gif";
	public static final String Icon_Line_Null = "Icons/treeicon08.gif";
	public static final String Icon_Leaf_Last = "Icons/treeicon07.gif";
	public static final String Icon_Leaf_NotLast = "Icons/treeicon06.gif";
	public static final String Branch_NotLast_NotExpand = "1";
	public static final String Branch_NotLast_Expand = "2";
	public static final String Branch_Last_NotExpand = "3";
	public static final String Branch_Last_Expand = "4";
	public static final String Icon_Special = "Framework/Images/icon_folder_special.gif";
	public static final String Icon_Image = "Framework/Images/icon_folder_image.gif";
	public static final String Icon_Video = "Framework/Images/icon_folder_video.gif";
	public static final String Icon_Audio = "Framework/Images/icon_folder_audio.gif";
	private String icon;
	private int level;
	private boolean isLast;
	private boolean isRoot;
	private boolean isBranch;
	private boolean isLeaf;
	private boolean lazy;
	private TreeItem parent;
	private TreeAction action;
	private String ID;
	private String ParentID;
	private String levelStr;
	private boolean isExpanded = true;
	private DataRow data;

	public String getOuterHtml() {
		HtmlP item = new HtmlP();
		item.Attributes.putAll(this.Attributes);
		item.setAttribute("level", String.valueOf(this.level));
		item.setAttribute("id", this.action.getID() + "_" + this.ID);
		item.setAttribute("parentID", this.ParentID);
		item.setAttribute("lazy", (this.lazy) ? "1" : "0");

		String onclick = getAttributeExt("onclick");
		if (StringUtil.isEmpty(onclick)) {
			onclick = "";
		}
		item.setAttribute("onclick", "Tree.onItemClick(event,this);" + onclick);
		item.setAttribute("ondblclick", "Tree.onItemDblClick(event,this);");
		item.setAttribute("oncontextmenu", getAttributeExt("oncontextmenu"));
		String afterDrag = getAttribute("afterDrag");
		if (StringUtil.isNotEmpty(afterDrag)) {
			item.setAttribute("dragEnd", "Tree.dragEnd");
			item.setAttribute("onMouseUp", "DragManager.onMouseUp(event,this)");

			if (this.action.Params.getString("Header.UserAgent").indexOf("msie") >= 0)
				item.setAttribute("onMouseEnter", "DragManager.onMouseOver(event,this)");
			else {
				item.setAttribute("onMouseOver", "DragManager.onMouseOver(event,this)");
			}
			item.setAttribute("dragOver", "Tree.dragOver");

			if (this.action.Params.getString("Header.UserAgent").indexOf("msie") >= 0)
				item.setAttribute("onMouseLeave", "DragManager.onMouseOut(event,this)");
			else {
				item.setAttribute("onMouseOut", "DragManager.onMouseOut(event,this)");
			}
			item.setAttribute("dragOut", "Tree.dragOut");
		}

		String text = getText();
		String prefix = Config.getContextPath();
		text = "<img src='" + prefix + getIcon() + "'>" + text;

		StringBuffer levelSb = new StringBuffer();

		if ((this.isBranch) && (this.isLast) && (this.isExpanded)) {
			text = "<img onclick='Tree.onBranchIconClick(event);' src='" + prefix
					+ "Icons/treeicon05.gif" + "'>" + text;
			if (this.lazy) {
				levelSb.insert(0, "0");
			}

			item.setAttribute("expand", "4");
		}
		if ((this.isBranch) && (this.isLast) && (!(this.isExpanded))) {
			text = "<img onclick='Tree.onBranchIconClick(event);' src='" + prefix
					+ "Icons/treeicon04.gif" + "'>" + text;
			if (this.lazy) {
				levelSb.insert(0, "0");
			}
			item.setAttribute("expand", "3");
		}
		if ((this.isBranch) && (!(this.isLast)) && (!(this.isExpanded))) {
			text = "<img onclick='Tree.onBranchIconClick(event);' src='" + prefix
					+ "Icons/treeicon01.gif" + "'>" + text;
			if (this.lazy) {
				levelSb.insert(0, "1");
			}
			item.setAttribute("expand", "1");
		}
		if ((this.isBranch) && (!(this.isLast)) && (this.isExpanded)) {
			text = "<img onclick='Tree.onBranchIconClick(event);' src='" + prefix
					+ "Icons/treeicon02.gif" + "'>" + text;
			if (this.lazy) {
				levelSb.insert(0, "1");
			}
			item.setAttribute("expand", "2");
		}
		if ((this.isLeaf) && (this.isLast)) {
			text = "<img src='" + prefix + "Icons/treeicon07.gif" + "'>" + text;
		}

		if ((this.isLeaf) && (!(this.isLast))) {
			text = "<img src='" + prefix + "Icons/treeicon06.gif" + "'>" + text;
		}

		TreeItem pTI = this.parent;
		while ((pTI != null) && (!(pTI.isRoot))) {
			if (pTI.isLast) {
				text = "<img src='" + prefix + "Icons/treeicon08.gif" + "'>" + text;
				if (this.lazy)
					levelSb.insert(0, "0");
			} else {
				text = "<img src='" + prefix + "Icons/treeicon03.gif" + "'>" + text;
				if (this.lazy) {
					levelSb.insert(0, "1");
				}
			}
			pTI = pTI.parent;
		}

		if (this.levelStr != null) {
			for (int j = this.levelStr.length() - 1; j >= 0; --j) {
				if (this.levelStr.charAt(j) == '0')
					text = "<img src='" + prefix + "Icons/treeicon08.gif" + "'>" + text;
				else {
					text = "<img src='" + prefix + "Icons/treeicon03.gif" + "'>" + text;
				}
			}
			if (this.lazy) {
				levelSb.insert(0, this.levelStr);
			}
		}
		if (this.lazy) {
			item.setAttribute("levelstr", levelSb.toString());
		}

		item.setInnerHTML(text);

		return item.getOuterHtml();
	}

	private String getAttributeExt(String attr) {
		String v = getAttribute(attr);
		if (StringUtil.isEmpty(v)) {
			if (this.parent != null) {
				return this.parent.getAttributeExt(attr);
			}
			return null;
		}

		return v;
	}

	public String getIcon() {
		if (this.icon == null) {
			if (this.isRoot) {
				return this.action.getRootIcon();
			}
			if (this.isLeaf) {
				return this.action.getLeafIcon();
			}
			if (this.isBranch) {
				return this.action.getBranchIcon();
			}
		}
		return this.icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public boolean isBranch() {
		return this.isBranch;
	}

	public void setBranch(boolean isBranch) {
		this.isBranch = isBranch;
		this.isLeaf = (!(isBranch));
	}

	public boolean isLeaf() {
		return this.isLeaf;
	}

	public void setLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
		this.isBranch = (!(isLeaf));
	}

	public boolean isRoot() {
		return this.isRoot;
	}

	public void setRoot(boolean isRoot) {
		this.isRoot = isRoot;
	}

	public int getLevel() {
		return this.level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getOnClick() {
		return getAttributeExt("onclick");
	}

	public void setOnClick(String onClick) {
		setAttribute("onclick", onClick);
	}

	public String getOnContextMenu() {
		return getAttributeExt("oncontextmenu");
	}

	public void setOnContextMenu(String onContextMenu) {
		setAttribute("oncontextmenu", onContextMenu);
	}

	public String getOnMouseOut() {
		return getAttributeExt("onmouseout");
	}

	public void setOnMouseOut(String onMouseOut) {
		setAttribute("onmouseout", onMouseOut);
	}

	public String getOnMouseOver() {
		return getAttributeExt("onmouseover");
	}

	public void setOnMouseOver(String onMouseOver) {
		setAttribute("onmouseover", onMouseOver);
	}

	public TreeItem getParent() {
		return this.parent;
	}

	public void setParent(TreeItem parent) {
		this.parent = parent;
	}

	public String getText() {
		return getInnerHTML();
	}

	public void setText(String text) {
		setInnerHTML(text);
	}

	public TreeAction getAction() {
		return this.action;
	}

	public void setAction(TreeAction action) {
		this.action = action;
	}

	public String getID() {
		return this.ID;
	}

	public void setID(String id) {
		this.ID = id;
	}

	public String getParentID() {
		return this.ParentID;
	}

	public void setParentID(String parentID) {
		this.ParentID = parentID;
	}

	public boolean isExpanded() {
		return this.isExpanded;
	}

	public void setExpanded(boolean isExpanded) {
		this.isExpanded = isExpanded;
	}

	public boolean isLast() {
		return this.isLast;
	}

	public void setLast(boolean isLast) {
		this.isLast = isLast;
	}

	public boolean isLazy() {
		return this.lazy;
	}

	public void setLazy(boolean lazy) {
		this.lazy = lazy;
	}

	public String getLevelStr() {
		return this.levelStr;
	}

	public void setLevelStr(String levelStr) {
		this.levelStr = levelStr;
	}

	public DataRow getData() {
		if ((this.isRoot) && (this.data == null)) {
			throw new RuntimeException("Root节点没有Data.");
		}
		return this.data;
	}

	public void setData(DataRow data) {
		this.data = data;
	}
}

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.framework.controls.TreeItem
 * JD-Core Version:    0.5.3
 */