/*
 * Dictionary Tree Item class
 */

function appDictTreeItem(sText, sAction, sItemId, sParentId, sCategory, sItemText, sElderBrotherId) {
  //call super
  this.WebFXTreeItem = WebFXTreeItem;
  this.WebFXTreeItem(sText, sAction);

  //setup property values
  this.value = sItemId;
  this.parentValue = sParentId;
  this.uItemId = sItemId;
  this.uParentId = sParentId;
  this.uCategory = sCategory;
  this.uItemText = sItemText;
  this.uElderBrotherId = sElderBrotherId;
  this.target = "workFrame";
}

appDictTreeItem.prototype = new WebFXTreeItem;