function init() {
	if (GDNetMenuDown.isSupported()) {
		GDNetMenuDown.initialize();
	}
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

function dataDeleteConfirm(sUrl) {
	var decision = showDataDeleteConfirm();
	if (decision != "0") {
		window.location = sUrl;
	}
}

function showDataDeleteConfirm() {
	return confirm("您确实要删除数据吗?");
}
function hidesubmenu(sid) {
	eval("submenu" + sid + ".style.display=\"none\";");
}
function showsubmenu(sid) {
	submenu2.style.display = "none";
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

function trimPrefix(str, prefix) {
	var tmpstr = str;
	var len = prefix.length;

	// alert("char code:"+tmpstr.charCodeAt(0));
	// alert(tmpstr.substring(0,len));
	if (tmpstr.substring(0, len) == prefix)
	// if(prefix.indexOf(tmpstr.substring(0,len)) != -1)
	{
		tmpstr = tmpstr.substr(len);
	}
	return tmpstr;
}

function trimPrefixIndent(str) {
	// (2004-01-18,by Fred)space in Option.text is unicode 160
	var prefixIndent = String.fromCharCode(160, 160) + "--";
	return trimPrefix(str, prefixIndent);
}

function AddItem(ObjName, DesName, CatName) {
	// GET OBJECT ID AND DESTINATION OBJECT
	ObjID = GetObjID(ObjName);
	DesObjID = GetObjID(DesName);
	// window.alert(document.queryForm.elements[DesObjID].length);
	k = 0;
	i = document.queryForm.elements[ObjID].options.length;
	if (i == 0)
		return;
	maxselected = 0
	for (h = 0; h < i; h++)
		if (document.queryForm.elements[ObjID].options[h].selected) {
			k = k + 1;
			maxselected = h + 1;
		}
	if (maxselected >= i)
		maxselected = 0;
	/*
	 * if ( document.queryForm.elements[DesObjID].length + k >5 ) {
	 * window.alert("最多可选择5条"); return; }
	 */

	if (CatName != "")
		CatObjID = GetObjID(CatName);
	else
		CatObjID = 0;
	if (ObjID != -1 && DesObjID != -1 && CatObjID != -1) {
		jj = document.queryForm.elements[CatObjID].selectedIndex;
		if (CatName != "") {
			CatValue = document.queryForm.elements[CatObjID].options[jj].text;
			CatCode = document.queryForm.elements[CatObjID].options[jj].value;
		} else
			CatValue = "";
		i = document.queryForm.elements[ObjID].options.length;
		j = document.queryForm.elements[DesObjID].options.length;
		for (h = 0; h < i; h++) {
			if (document.queryForm.elements[ObjID].options[h].selected) {
				Code = document.queryForm.elements[ObjID].options[h].value;
				Text = document.queryForm.elements[ObjID].options[h].text;
				cid = document.queryForm.elements[ObjID].options[h].id;
				j = document.queryForm.elements[DesObjID].options.length;
				if (Text.indexOf('--不限--') != -1) {
					for (k = j - 1; k >= 0; k--) {
						document.queryForm.elements[DesObjID].options[k] = null;
					}
					j = 0;
				}
				if (Text.substring(0, 1) == '-' && Text.substring(1, 2) != '-') {
					for (k = j - 1; k >= 0; k--) {
						if (((document.queryForm.elements[DesObjID].options[k].value)
								.substring(0, 2)) == (Code.substring(0, 2)))
							document.queryForm.elements[DesObjID].options[k] = null;
					}
					j = document.queryForm.elements[DesObjID].options.length;
				}
				HasSelected = false;
				for (k = 0; k < j; k++) {
					if ((document.queryForm.elements[DesObjID].options[k].text)
							.indexOf('--不限--') != -1) {
						HasSelected = true;
						window.alert('已经包括本选项：' + Text);
						break;
					}
					// else if
					// ((document.queryForm.elements[DesObjID].options[k].text).indexOf('-')!=-1
					else if ((document.queryForm.elements[DesObjID].options[k].text)
							.substring(0, 1) == '-'
							&& ((document.queryForm.elements[DesObjID].options[k].value) == Code)) {
						HasSelected = true;
						window.alert('已经包括本选项：' + Text);
						break;
					}
					if (document.queryForm.elements[DesObjID].options[k].value == Code) {
						HasSelected = true;
						break;
					}
				}
				if (HasSelected == false) {
					Text = trimPrefixIndent(Text);
					if (CatValue != "") {
						Location = GetLocation(DesObjID, CatValue);
						if (Location == -1) {
							document.queryForm.elements[DesObjID].options[j] = new Option(
									"---" + CatValue + "---", CatCode);
							document.queryForm.elements[DesObjID].options[j + 1] = new Option(
									Text, Code);
						}// if
						else {
							InsertItem(DesObjID, Location + 1);
							document.queryForm.elements[DesObjID].options[Location
									+ 1] = new Option(Text, Code);
						}// else
					} else {
						document.queryForm.elements[DesObjID].options[j] = new Option(
								Text, Code);
						// ggtree change other color
						if (cid == 1) {
							document.queryForm.elements[DesObjID].options[j].style.color = "red";
						}
					}
				}// if
				document.queryForm.elements[ObjID].options[h].selected = false;
			}// if
		}// for
		document.queryForm.elements[ObjID].options[maxselected].selected = true;
	}// if
}// end of function

function AddItemOther(ObjName, DesName) {
	// GET OBJECT ID AND DESTINATION OBJECT
	ObjID = GetObjID(ObjName);
	DesObjID = GetObjID(DesName);
	i = document.queryForm.elements[ObjID].options.length;
	if (i == 0)
		return;
	maxselected = 0
	for (h = 0; h < i; h++) {
		if (document.queryForm.elements[ObjID].options[h].selected) {
			maxselected = h + 1;
			if (maxselected >= i)
				maxselected = 0;
			var Text = document.queryForm.elements[ObjID].options[h].text;
			var Code = document.queryForm.elements[ObjID].options[h].value;
			dlength = document.queryForm.elements[DesObjID].options.length;
			document.queryForm.elements[DesObjID].options[dlength] = new Option(
					Text, Code);
			document.queryForm.elements[ObjID].options[h] = null;
			h--;
		}
	}

}// end of function

function DeleteItem(ObjName, DesName) {
	ObjID = GetObjID(ObjName);
	minselected = 0;
	if (ObjID != -1) {
		for (i = window.queryForm.elements[ObjID].length - 1; i >= 0; i--) {
			if (window.queryForm.elements[ObjID].options[i].selected) { // window.alert(i);
				if (minselected == 0 || i < minselected)
					minselected = i;
				window.queryForm.elements[ObjID].options[i] = null;
			}
		}
		i = window.queryForm.elements[ObjID].length;

		if (i > 0) {
			if (minselected >= i)
				minselected = i - 1;
			window.queryForm.elements[ObjID].options[minselected].selected = true;
		}
	}
}
function AddAllItem(ObjName, DesName) {
	ObjID = GetObjID(ObjName);
	DesObjID = GetObjID(DesName);
	document.queryForm.elements[DesObjID].length = document.queryForm.elements[ObjID].length;
	for (var i = 0; i < document.queryForm.elements[ObjID].length; i++) {
		// alert(document.queryForm.elements[ObjID].options[i].text);
		document.queryForm.elements[DesObjID].options[i].text = document.queryForm.elements[ObjID].options[i].text;
		document.queryForm.elements[DesObjID].options[i].value = document.queryForm.elements[ObjID].options[i].value;
	}
	return
}
function AddAllItemOther(ObjName, DesName) {
	ObjID = GetObjID(ObjName);
	DesObjID = GetObjID(DesName);
	i = document.queryForm.elements[ObjID].options.length;
	if (i == 0)
		return;
	maxselected = 0
	for (h = 0; h < i; h++) {
		maxselected = h + 1;
		if (maxselected >= i)
			maxselected = 0;
		var Text = document.queryForm.elements[ObjID].options[h].text;
		var Code = document.queryForm.elements[ObjID].options[h].value;
		dlength = document.queryForm.elements[DesObjID].options.length;
		document.queryForm.elements[DesObjID].options[dlength] = new Option(
				Text, Code);
		document.queryForm.elements[ObjID].options[h] = null;
		h--;
	}
}

function DeleteAllItem(DesName) {
	DesObjID = GetObjID(DesName);
	document.queryForm.elements[DesObjID].length = 0;
}

function GetObjID(ObjName) {
	for (var ObjID = 0; ObjID < document.queryForm.elements.length; ObjID++)
		if (document.queryForm.elements[ObjID].name == ObjName) {
			return (ObjID);
			break;
		}
	return (-1);
}

function tbs(o, a, b, c, d) {
	var t = document.getElementById(o).getElementsByTagName("tr");
	for (var i = 0; i < t.length; i++) {
		t[i].style.backgroundColor = (t[i].sectionRowIndex % 2 == 0) ? a : b;
		t[i].onclick = function() {
			if (this.x != "1") {
				this.x = "1";
				this.style.backgroundColor = d;
			} else {
				this.x = "0";
				this.style.backgroundColor = (this.sectionRowIndex % 2 == 0)
						? a
						: b;
			}
		}
		t[i].onmouseover = function() {
			if (this.x != "1")
				this.style.backgroundColor = c;
		}
		t[i].onmouseout = function() {
			if (this.x != "1")
				this.style.backgroundColor = (this.sectionRowIndex % 2 == 0)
						? a
						: b;
		}
	}
}

function doPrint(category) {
	bdhtml = window.document.body.innerHTML;
	sprnstr = "<!--startprint-->";
	eprnstr = "<!--endprint-->";
	prnhtml = bdhtml.substr(bdhtml.indexOf(sprnstr) + 17);
	prnhtml = prnhtml.substring(0, prnhtml.indexOf(eprnstr));
	// prnhtml = prnhtml.toLowerCase();
	if (category == 2)
		prnhtml = prnhtml
				.replace("<!--img-->",
						"<img src=\"../../../../images/gat/bgs2_1.png\" width=\"498\" height=\"55\" />");
	if (category == 3)
		prnhtml = prnhtml
				.replace("<!--img-->",
						"<img src=\"../../../../images/gat/pbgs3.png\" width=\"480\" height=\"77\" />");
	if (category == 5)
		prnhtml = prnhtml
				.replace("<!--img-->",
						"<img src=\"../../../../images/gat/pbgs5.png\" width=\"480\" height=\"77\" />");
	if (category == 7)
		prnhtml = prnhtml.replace("<!--img-->",
				"<img src=\"../../../../images/gat/pbgs7.png\" />");
	if (category == 9)
		prnhtml = prnhtml
				.replace("<!--img-->",
						"<img src=\"../../../../images/gat/pbgs9.png\" width=\"500\" height=\"63\" />");
	if (category == 11)
		prnhtml = prnhtml
				.replace("<!--img-->",
						"<img src=\"../../../../images/gat/pbgs16.png\" width=\"450\" height=\"68\" />");
	if (category == 12) {
		prnhtml = prnhtml
				.replace(
						"<!--img-->",
						"<img src=\"../../../../images/gat/pbgs12_1.png\" width=\"457\" height=\"109\" />");
		prnhtml = prnhtml
				.replace(
						"<!--img1-->",
						"<li><img src=\"../../../../images/gat/pbgs12_2.png\" width=\"558\" height=\"30\" /></li>");
	}
	if (category == 13)
		prnhtml = prnhtml.replace("<!--img-->",
				"<img src=\"../../../../images/gat/pbgs13.png\" />");
	if (category == 14)
		prnhtml = prnhtml
				.replace("<!--img-->",
						"<img src=\"../../../../images/gat/pbgs14.png\" width=\"420\" height=\"123\"/>");
	if (category == 15)
		prnhtml = prnhtml.replace("<!--img-->",
				"<img src=\"../../../../images/gat/pbgs15.png\" />");
	if (category == 18)
		prnhtml = prnhtml.replace("<!--img-->",
				"<img src=\"../../../../images/gat/pbgs18.png\" />");
	prnhtml = prnhtml.replaceAll("/UserFiles", "http://10.72.1.2/UserFiles");
	str = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">";
	str += "<html xmlns=\"http://www.w3.org/1999/xhtml\">";
	str += "<head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=gb2312\">";
	str += "<script src=\"../../../../scripts/re.js\" type=\"text/javascript\"></script>";
	str += "<link href=\"../../../../styles/pstb.css\" rel=\"stylesheet\" type=\"text/css\"/>";
	str += "<link href=\"../../../../styles/pbgs" + category
			+ ".css\" rel=\"stylesheet\" type=\"text/css\" />";
	str += "</head><body onLoad='ReImgSize();window.print()'>";
	prnhtml = str + prnhtml;

	prnhtml = prnhtml + "</body></html>";
	op = window
			.open('', '',
					'toolbar=yes,location=yes,status=yes,menubar=yes,scrollbars=yes,resizable=yes');
	op.document.writeln(prnhtml);
	op.document.close();
}

String.prototype.replaceAll = function(s1, s2) {
	return this.replace(new RegExp(s1, "gim"), s2);
}

// 说明：实现网页图片等比例缩放
function DrawImage(ImgD, FitWidth, FitHeight) {
	var image = new Image();
	image.src = ImgD.src;
	if (image.width > 0 && image.height > 0) {
		if (image.width / image.height >= FitWidth / FitHeight) {
			if (image.width > FitWidth) {
				ImgD.width = FitWidth;
				ImgD.height = (image.height * FitWidth) / image.width;
			} else {
				ImgD.width = image.width;
				ImgD.height = image.height;
			}
		} else {
			if (image.height > FitHeight) {
				ImgD.height = FitHeight;
				ImgD.width = (image.width * FitHeight) / image.height;
			} else {
				ImgD.width = image.width;
				ImgD.height = image.height;
			}
		}
	}
}

// Iframe窗口自适应高度 兼容IE6.0 FF2.0以上
function iframeFitHeight(obj) {
	var cwin = obj;
	if (document.getElementById) {
		if (cwin && !window.opera) {
			if (cwin.contentDocument && cwin.contentDocument.body.offsetHeight)
				cwin.height = cwin.contentDocument.body.offsetHeight;
			else if (cwin.Document && cwin.Document.body.scrollHeight)
				cwin.height = cwin.Document.body.scrollHeight;
		}
	}
}