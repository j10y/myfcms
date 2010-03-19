function AppTreeItemForSelectPrototypeToString(nItem, nItemCount) {
	var foo = this.parentNode;
	var indent = '';
	if (nItem + 1 == nItemCount) { this.parentNode._last = true; }
	var i = 0;
	while (foo.parentNode) {
		foo = foo.parentNode;
		indent = "<img id=\"" + this.id + "-indent-" + i + "\" src=\"" + ((foo._last)?webFXTreeConfig.blankIcon:webFXTreeConfig.iIcon) + "\">" + indent;
		i++;
	}
	this._level = i;
	if (this.childNodes.length) { this.folder = 1; }
	else { this.open = false; }
	if ((this.folder) || (webFXTreeHandler.behavior != 'classic')) {
		if (!this.icon) { this.icon = webFXTreeConfig.folderIcon; }
		if (!this.openIcon) { this.openIcon = webFXTreeConfig.openFolderIcon; }
	}
	else if (!this.icon) { this.icon = webFXTreeConfig.fileIcon; }
	var label = this.text.replace(/</g, '&lt;').replace(/>/g, '&gt;');
	var str = "<div id=\"" + this.id + "\" ondblclick=\"webFXTreeHandler.toggle(this);\" class=\"webfx-tree-item\" onkeydown=\"return webFXTreeHandler.keydown(this, event)\">" +
		indent +
		"<img id=\"" + this.id + "-plus\" src=\"" + ((this.folder)?((this.open)?((this.parentNode._last)?webFXTreeConfig.lMinusIcon:webFXTreeConfig.tMinusIcon):((this.parentNode._last)?webFXTreeConfig.lPlusIcon:webFXTreeConfig.tPlusIcon)):((this.parentNode._last)?webFXTreeConfig.lIcon:webFXTreeConfig.tIcon)) + "\" onclick=\"webFXTreeHandler.toggle(this);\">" +
		"<img id=\"" + this.id + "-icon\" class=\"webfx-tree-icon\" src=\"" + ((webFXTreeHandler.behavior == 'classic' && this.open)?this.openIcon:this.icon) + "\" onclick=\"webFXTreeHandler.select(this);\">" +
		"<a ondblclick=\"javascript:selectItem('" + this.id + "');\"  href=\"" + this.action + "\" id=\"" + this.id + "-anchor\" onfocus=\"webFXTreeHandler.focus(this);\" onblur=\"webFXTreeHandler.blur(this);\"" +
		(this.target ? " target=\"" + this.target + "\"" : "") +
		">" + label + "</a></div>" +
		"<div id=\"" + this.id + "-cont\" class=\"webfx-tree-container\" style=\"display: " + ((this.open)?'block':'none') + ";\">";
	var sb = [];
	for (var i = 0; i < this.childNodes.length; i++) {
		sb[i] = this.childNodes[i].toString(i,this.childNodes.length);
	}
	this.plusIcon = ((this.parentNode._last)?webFXTreeConfig.lPlusIcon:webFXTreeConfig.tPlusIcon);
	this.minusIcon = ((this.parentNode._last)?webFXTreeConfig.lMinusIcon:webFXTreeConfig.tMinusIcon);
	return str + sb.join("") + "</div>";
}

function AppDynTreeItemForSelect(sText, sXmlSrc, sAction, sCode, sName, sType, sIcon, sOpenIcon, eParent) {
	// call super
	this.WebFXLoadTreeItem = WebFXLoadTreeItem;
	this.WebFXLoadTreeItem(sText, sXmlSrc, sAction, eParent, sIcon, sOpenIcon);

	// setup default property values
	this.uCode = sCode;
	this.uName = sName;
	this.uType = sType;
}

AppDynTreeItemForSelect.prototype = new WebFXLoadTreeItem;
AppDynTreeItemForSelect.prototype.toString = AppTreeItemForSelectPrototypeToString;

function AppStaticTreeItemForSelect(sText, sAction, sCode, sName, sType, sIcon, sOpenIcon, eParent) {
    // call super
	this.WebFXTreeItem = WebFXTreeItem;
	this.WebFXTreeItem(sText, sAction, eParent, sIcon, sOpenIcon);
	
	// setup default property values
	this.uCode = sCode;
	this.uName = sName;
	this.uType = sType;
}

AppStaticTreeItemForSelect.prototype = new WebFXTreeItem;
AppStaticTreeItemForSelect.prototype.toString = AppTreeItemForSelectPrototypeToString;

// Converts an xml tree to a js tree. See article about xml tree format
function _xmlTreeToJsTree(oNode) {
	// retreive attributes
	var text = oNode.getAttribute("text");
	var action = oNode.getAttribute("action");
	var parent = null;
	var icon = oNode.getAttribute("icon");
	var openIcon = oNode.getAttribute("openIcon");
	var src = oNode.getAttribute("src");
	var target = oNode.getAttribute("target");
	var uCode = oNode.getAttribute("uCode");
	var uName = oNode.getAttribute("uName");
	var uType = oNode.getAttribute("uType");
	
	if (uType == "person")
	  icon = "scripts/tree/images/xp/file.png";
	
	// create jsNode
	var jsNode;
	if (src != null && src != "")
		jsNode = new AppDynTreeItemForSelect(text, src, action, uCode, uName, uType, icon, openIcon, parent);
	else
		jsNode = new AppStaticTreeItemForSelect(text, action, uCode, uName, uType, icon, openIcon, parent);

	if (target != "")
		jsNode.target = target;
    
	// go through childNOdes
	var cs = oNode.childNodes;
	var l = cs.length;
	for (var i = 0; i < l; i++) {
		if (cs[i].tagName == "tree")
			jsNode.add( _xmlTreeToJsTree(cs[i]), true );
	}
	return jsNode;
}

function selectItem(nodeId) {
  var treeItem = webFXTreeHandler.all[nodeId];
  var selectResultVar = parent.selectResultVar;
  var documentVar = parent.documentVar;
  if (treeItem.uType == "person" && parent.selectTypeVar == "person") {
    for (var i = 0; i < selectResultVar.options.length; i++) {
      if (selectResultVar.options[i].value == treeItem.uCode)
        return;
    }
    if ("false" == parent.multi) {
      parent.clearAll();
    }
    var option = documentVar.createElement("option");
    option.text = treeItem.uName;
    option.value = treeItem.uCode;
    selectResultVar.options.add(option);
  }
  if (treeItem.uType == "org" && parent.selectTypeVar == "org") {
    for (var i = 0; i < selectResultVar.options.length; i++) {
      if (selectResultVar.options[i].value == treeItem.uCode)
        return;
    }
    if ("false" == parent.multi) {
      parent.clearAll();
    }
    var option = documentVar.createElement("option");
    option.text = treeItem.uName;
    option.value = treeItem.uCode;
    selectResultVar.options.add(option);
  } 
}