function isIEBrowser() {
    if (navigator.userAgent.indexOf("MSIE") != -1) {
      return true;
    } else {
      return false;
    }
  }

  function deleteAnswerRowById(tabId, rowId) {
    var workTable = document.getElementById(tabId);
    var rowNum = document.getElementById(rowId).rowIndex
    workTable.deleteRow(rowNum);
 }

  function addAnswerRow(tabId, maxId, delStr) {
  
    var isIE = isIEBrowser();  
    var workTab = document.getElementById(tabId);
    var maxField = document.getElementById(maxId);
    var maxNum = parseInt(maxField.value) + 1;
    maxField.value = maxNum;
    //alert(maxNum);
   
    wjfjTe = document.createElement("input");
    wjfjTe.type = "text";
    wjfjTe.size="70";
    wjfjTe.id = "answer" + maxNum;
    wjfjTe.name = "answer" + maxNum;
    wjfjTe.className = "inputText";

	if (isIE) {
	 delBtStr = "<input class='button_delete' type='button' value='' onClick=deleteAnswerRowById('" + tabId + "','" + tabId + "Row" + maxNum + "');>";
	 delBt = document.createElement(delBtStr);
	} else {
      delBt = document.createElement("input");
      delBt.type = "button";
      delBt.value = "";
      delBt.setAttribute("onClick", "deleteAnswerRowById('" + tabId + "', '"  + tabId + "Row" + maxNum + "')");
      delBt.className = "button_delete";
    }
    var uRow = workTab.insertRow(workTab.rows.length);
    uRow.id = tabId + "Row" + maxNum;
    uRow.className = "bellContentDataCell";	
    
    uCell = uRow.insertCell(-1);
	uCell.align = "center";
	uCell.width = "20%";
    uCell.appendChild(delBt);   
    uCell.className = "bellContentLabelCell";    
	

    uCell = uRow.insertCell(-1);
	uCell.align = "center";
    uCell.appendChild(wjfjTe);
    uCell.className = "bellContentDataCell";
}
