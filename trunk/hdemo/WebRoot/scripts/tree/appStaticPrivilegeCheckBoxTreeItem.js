/*
 * appStaticPrivilegeCheckBoxTreeItem class
 */

function appStaticPrivilegeCheckBoxTreeItem(sText, sInputName, sValue, sParentValue, sChecked) {
  //call super
  this.WebFXTreeItem = WebFXTreeItem;
  this.WebFXTreeItem(sText);

  // setup property values
  this.inputName = sInputName;
  this.value = sValue;
  this.parentValue = sParentValue;
  this.checked = sChecked;
  this.lastLevel = "1";
}

appStaticPrivilegeCheckBoxTreeItem.prototype = new WebFXTreeItem;

// override the toString method
appStaticPrivilegeCheckBoxTreeItem.prototype.toString = function (nItem, nItemCount)  {
  //add checkbox
  var ifChecked = "";
  var selectHref = "";
  if (this.checked == "1") {
	ifChecked = " checked ";
  }
  var checkBox = "";
  if (this.lastLevel == "1") {
	checkBox = checkBox + " <input type=\"checkbox\" name=\"" + this.inputName + "\" value=\""
			     + this.value + "\" " + ifChecked + " id=\"" + this.value + "\" " + "  style=\"vertical-align: bottom\" class=\"webfx-tree-icon\" >";	        
  } else {
    selectHref = selectHref + " <a href=\"javascript:setChildCheckBox('" 
                 + this.id + "', '1');\"><img src='images/selectAll.jpg' border=0 alt='全部选中'></a><a href=\"javascript:setChildCheckBox('" 
                 + this.id + "', '0');\"><img src='images/deSelectAll.jpg' border=0 alt='全部取消'></a> ";
 
     }

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
  if (this.childNodes.length) { 
    this.folder = 1; 
  } else { 
    this.open = false; 
  }
  if ((this.folder) || (webFXTreeHandler.behavior != 'classic')) {
	if (!this.icon) { this.icon = webFXTreeConfig.folderIcon; }
	if (!this.openIcon) { this.openIcon = webFXTreeConfig.openFolderIcon; }
  } else if (!this.icon) { 
    this.icon = webFXTreeConfig.fileIcon; 
  }
  var label = this.text.replace(/</g, '&lt;').replace(/>/g, '&gt;');
  var str = "<div id=\"" + this.id + "\" ondblclick=\"webFXTreeHandler.toggle(this);\" class=\"webfx-tree-item\" onkeydown=\"return webFXTreeHandler.keydown(this, event)\">" +
		indent +
		"<img id=\"" + this.id + "-plus\" src=\"" + ((this.folder)?((this.open)?((this.parentNode._last)?webFXTreeConfig.lMinusIcon:webFXTreeConfig.tMinusIcon):((this.parentNode._last)?webFXTreeConfig.lPlusIcon:webFXTreeConfig.tPlusIcon)):((this.parentNode._last)?webFXTreeConfig.lIcon:webFXTreeConfig.tIcon)) + "\" onclick=\"webFXTreeHandler.toggle(this);\">" +
		"<img id=\"" + this.id + "-icon\" class=\"webfx-tree-icon\" src=\"" + ((webFXTreeHandler.behavior == 'classic' && this.open)?this.openIcon:this.icon) + "\" onclick=\"webFXTreeHandler.select(this);\">" + checkBox +
		"<a href=\"" + this.action + "\" id=\"" + this.id + "-anchor\" onfocus=\"webFXTreeHandler.focus(this);\" onblur=\"webFXTreeHandler.blur(this);\"" +
		(this.target ? " target=\"" + this.target + "\"" : "") +
		">" + label + "</a>" + selectHref + "</div>" +
		"<div id=\"" + this.id + "-cont\" class=\"webfx-tree-container\" style=\"display: " + ((this.open)?'block':'none') + ";\">";


  var sb = [];
  for (var i = 0; i < this.childNodes.length; i++) {
	sb[i] = this.childNodes[i].toString(i,this.childNodes.length);
  }
  this.plusIcon = ((this.parentNode._last)?webFXTreeConfig.lPlusIcon:webFXTreeConfig.tPlusIcon);
  this.minusIcon = ((this.parentNode._last)?webFXTreeConfig.lMinusIcon:webFXTreeConfig.tMinusIcon);
  return str + sb.join("") + "</div>";
};

function setChildCheckBox(nodeId, checkFlag) {
  var node = webFXTreeHandler.all[nodeId];
  var children = node.childNodes;
  var len = children.length;
  var uCheckBox;
  var i = 0;
  for (i = 0; i < len; i++) {
    if (children[i].childNodes.length == 0) {
      uCheckBox = document.getElementById(children[i].value);
      if (checkFlag == "1") 
        uCheckBox.checked = true;
      else
        uCheckBox.checked = false;
    } else {
      setChildCheckBox(children[i].id, checkFlag);
    }
  }
}