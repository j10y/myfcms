/*
 * appTree class
 */

function appTree(sText, sValue) {
	// call super
	this.WebFXTree = WebFXTree;
	this.WebFXTree(sText);

	// setup default property values
	this.value = sValue;
}

appTree.prototype = new WebFXTree;

//generate tree function
function genTree(root, nodeArray) {
  var nodeStack = new Array;
  var p = 0;
  var len = nodeArray.length;
  nodeStack[0] = root;
  for (var i = 0; i < len; i++) {
    if (nodeArray[i].parentValue == nodeStack[p].value) {
	  nodeStack[p].lastLevel = "0";
	  nodeStack[p].add(nodeArray[i]);
      p++;
	  nodeStack[p] = nodeArray[i];
	} else {
      while (p >=0 && nodeArray[i].parentValue != nodeStack[p].value) {
	    p--;
	  }
	  if (p >= 0) nodeStack[p].add(nodeArray[i]);
	  p++;
      nodeStack[p] = nodeArray[i];
	}
  }
  return root;
}