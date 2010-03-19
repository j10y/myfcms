function trim(s) {
	return s.replace(/^\s*/, "").replace(/\s*$/, "");
}

function isEmpty(s) {

}

function showDataDeleteConfirm() {
	  return confirm("您确实要删除数据吗?");
}

function CheckChange(readId, writeId) {
	var _value = document.getElementById(readId).value;
	var _indexB = _value.lastIndexOf('.');
	var _indexE = _value.length;
	var _tCvalue = _value.substring(_indexB + 1, _indexE);
	document.getElementById(writeId).value = _tCvalue;
}

function SelectionData(sCode, sName) {
	this.code = sCode;
	this.name = sName;
	this.selection1 = null;
}

function SelectionArgument() {
	this.multi = "false";
	this.initialData = null;
}

function showOrgPersonSelectDialog(sMulti, tInitialData, sUrl) {
	var arg = new SelectionArgument();
	arg.multi = sMulti;
	arg.initialData = tInitialData;
	var selection = window.showModalDialog(sUrl, arg,
			"dialogHeight=512px; dialogWidth=558px");
	if ("false" == sMulti) {
		if (selection == null || selection.length == 0)
			return null;
		else
			return selection[0];
	} else {
		return selection;
	}
}

function showOrgSelectDialog(sMulti, tInitialData, sUrl) {
	var arg = new SelectionArgument();
	arg.multi = sMulti;
	arg.initialData = tInitialData;
	var selection = window.showModalDialog(sUrl, arg,
			"dialogHeight=512px; dialogWidth=558px");
	if ("false" == sMulti) {
		if (selection == null || selection.length == 0)
			return null;
		else
			return selection[0];
	} else {
		return selection;
	}
}

function showDeviceSelectDialog(sUrl) {
	return window.showModalDialog(sUrl, null,
			"dialogHeight=480px;dialogWidth=660px");
}

function showRunPlanSelectDialog(sUrl) {
	return window.showModalDialog(sUrl, null,
			"dialogHeight=300px;dialogWidth=280px");
}

function showMaintOvertureDialog(sUrl) {
	return window.showModalDialog(sUrl, null,
			"dialogHeight=300px;dialogWidth=280px");
}

function showWorkOrderSymptCodeSelectDialog(sUrl) {
	return window.showModalDialog(sUrl, null,
			"dialogHeight=480px;dialogWidth=660px");
}

function dataAlert(promptMessage) {
	ymPrompt.alert(promptMessage, null,null,'提示信息');
}

function dataDeleteConfirm() {
	ymPrompt.confirmInfo({
				message : '您确实要删除数据吗?',
				handler : getConfirmValue
	});
}

function getConfirmValue(tp) {
	if (tp == 'ok') {
		var v = document.getElementById("deleteUrl").value;
		//alert(v);
		window.location = v;
	}
	if (tp == 'cancel') {
		ymPrompt.close();
	}
	if (tp == 'close') {
		ymPrompt.close();
	}
}

function dataAlertAndGoUrl(promptMessage, type) {
	if (type == 1)
		ymPrompt.succeedInfo({message:promptMessage,width:300,height:180,handler:goToUrl})
	else
		ymPrompt.errorInfo({message:promptMessage,handler:goToUrl})
}



function goToUrl(tp) {
	if (tp == 'ok') {
		window.location = document.getElementById("goToUrl").value;
	}
	if (tp == 'close') {
		window.location = document.getElementById("goToUrl").value;
	}
}

function hidesubmenu(sid) {
	eval("submenu" + sid + ".style.display=\"none\";");
}
function showsubmenu(sid) {
	// submenu2.style.display="none";
	eval("submenu" + sid + ".style.display=\"block\";");
}
function mOvr(src, clrOver) {
	if (!src.contains(event.fromElement)) {
		src.style.cursor = 'hand';
		src.bgColor = clrOver;
		src.style.color = '#ffffff';
	}
}

function mOut(src, clrIn) {
	if (!src.contains(event.toElement)) {
		src.style.cursor = 'default';
		src.bgColor = clrIn;
		src.style.color = '#000000';
	}
}

function MM_jumpMenu(targ, selObj, restore) { // v3.0
	eval(targ + ".location='" + selObj.options[selObj.selectedIndex].value
			+ "'");
	if (restore)
		selObj.selectedIndex = 0;
}

function init() {
	if (GDNetMenuDown.isSupported()) {
		GDNetMenuDown.initialize();
	}
}

function hidesubmenu(sid) {
	eval("submenu" + sid + ".style.display=\"none\";");
}

function mOvr(src, clrOver) {
	if (!src.contains(event.fromElement)) {
		src.style.cursor = 'hand';
		src.bgColor = clrOver;
		src.style.color = '#ffffff';
	}
}

function mOut(src, clrIn) {
	if (!src.contains(event.toElement)) {
		src.style.cursor = 'default';
		src.bgColor = clrIn;
		src.style.color = '#000000';
	}
}

function AddFavorite(sURL, sTitle) 
{ 
    try 
    { 
        window.external.addFavorite(sURL, sTitle); 
    } 
    catch (e) 
    { 
        try 
        { 
            window.sidebar.addPanel(sTitle, sURL, ""); 
        } 
        catch (e) 
        { 
            alert("加入收藏失败，请使用Ctrl+D进行添加"); 
        } 
    } 
} 
function SetHome(obj,vrl){ 
        try{ 
                obj.style.behavior='url(#default#homepage)';obj.setHomePage(vrl); 
        } 
        catch(e){ 
                if(window.netscape) { 
                        try { 
                                netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect"); 
                        } 
                        catch (e) { 
                                alert("此操作被浏览器拒绝！\n请在浏览器地址栏输入“about:config”并回车\n然后将 [signed.applets.codebase_principal_support]的值设置为'true',双击即可。"); 
                        } 
                        var prefs = Components.classes['@mozilla.org/preferences-service;1'].getService(Components.interfaces.nsIPrefBranch); 
                        prefs.setCharPref('browser.startup.homepage',vrl); 
                 } 
        } 
}
