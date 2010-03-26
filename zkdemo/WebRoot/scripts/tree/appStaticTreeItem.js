/*
 * appStaticTreeItem class
 */

function appStaticTreeItem(sText, sAction, sValue, sParentValue) {
  //call super
  this.WebFXTreeItem = WebFXTreeItem;
  this.WebFXTreeItem(sText, sAction);

  //setup property values
  this.value = sValue;
  this.parentValue = sParentValue;
  this.target = "workFrame";
}

appStaticTreeItem.prototype = new WebFXTreeItem;