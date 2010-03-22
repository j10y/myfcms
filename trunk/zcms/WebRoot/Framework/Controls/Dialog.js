function Dialog(){
		var strID,w,h,title,url;
		if(arguments.length==1){//兼容旧写法
			strID = arguments[0];
		}else{
			title = arguments[0];
			url = arguments[1];
			w = arguments[2];
			h = arguments[3];
			if(!Dialog.MaxID){
				Dialog.MaxID = 0;
			}
			Dialog.MaxID++;
			strID = "Dialog"+Dialog.MaxID;
		}
		if(!strID){
			alert("错误的Dialog ID!");
			return;
		}
		this.ID = strID;
		this.isModal = true;
		this.coverSelect = false;
		if(w){
			this.Width = w;
		}else{
			this.Width = 400;
		}
		if(h){
			this.Height = h;
		}else{
			this.Height = 300;
		}
		if(title){
			this.Title = title;
		}else{
			this.Title = "";
		}
		if(url){
			this.URL = url;
		}else{
			this.URL = null;
		}
		this.Top = 0;
		this.Left = 0;
		this.ParentWindow = null;
		this.onLoad = null;
		this.Window = null;

		this.DialogArguments = {};
		this.WindowFlag = false;
		this.Message = null;
		this.MessageTitle = null;
		this.ShowMessageRow = false;
		this.ShowButtonRow = true;
		this.Icon = null;
		this.bgdivID=null;
		this.Animator = true;
}

Dialog._Array = [];

Dialog.prototype.showWindow = function(){
	if(isIE){
		this.ParentWindow.showModalessDialog( this.URL, this.DialogArguments, "dialogWidth:" + this.Width + ";dialogHeight:" + this.Height + ";help:no;scroll:no;status:no") ;
	}
	if(isGecko){
		var sOption  = "location=no,menubar=no,status=no;toolbar=no,dependent=yes,dialog=yes,minimizable=no,modal=yes,alwaysRaised=yes,resizable=no";
		this.Window = this.ParentWindow.open( '', this.URL, sOption, true ) ;
		var w = this.Window;
		if ( !w ){
			alert( "发现弹出窗口被阻止，请更改浏览器设置，以便正常使用本功能!" ) ;
			return ;
		}
		w.moveTo( this.Left, this.Top ) ;
		w.resizeTo( this.Width, this.Height+30 ) ;
		w.focus() ;
		w.location.href = this.URL ;
		w.Parent = this.ParentWindow;
		w.dialogArguments = this.DialogArguments;
	}
}

Dialog.prototype.show = function(){
	var pw = $E.getTopLevelWindow();
	var doc = pw.document;
	var cw = doc.compatMode == "BackCompat"?doc.body.clientWidth:doc.documentElement.clientWidth;
	var ch = doc.compatMode == "BackCompat"?doc.body.clientHeight:doc.documentElement.clientHeight;//必须考虑文本框处于页面边缘处，控件显示不全的问题
	var sw = Math.max(doc.documentElement.scrollLeft, doc.body.scrollLeft);
	var sh = Math.max(doc.documentElement.scrollTop, doc.body.scrollTop);//考虑滚动的情况
	var mw = Math.max(doc.documentElement.scrollWidth, doc.body.scrollWidth);
	var mh = Math.max(doc.documentElement.scrollHeight, doc.body.scrollHeight);

	if(!this.ParentWindow){
	 	this.ParentWindow = window ;
	}
	this.DialogArguments._DialogInstance = this;
	this.DialogArguments.ID = this.ID;

	if(!this.Height){
		this.Height = this.Width/2;
	}

	if(this.Top==0){
		this.Top = (ch - this.Height - 30) / 2 + sh - 8;//有8像素的透明背景
	}
	if(this.Left==0){
		this.Left = (cw - this.Width - 24) / 2 +sw;
	}
	if(this.ShowButtonRow){//按钮行高36
		this.Top -= 18;
	}
	if(this.WindowFlag){
		this.showWindow();
		return;
	}
	var arr = [];
	arr.push("<table style='-moz-user-select:none;' oncontextmenu='stopEvent(event);' onselectstart='stopEvent(event);' border='0' cellpadding='0' cellspacing='0' width='"+(this.Width+26)+"'>");
	arr.push("  <tr style='cursor:move;'>");
	arr.push("    <td width='13' height='33' style=\"background-image:url("+Server.ContextPath+"Framework/Images/dialog_lt.png) !important;background-image: none;filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+Server.ContextPath+"Framework/Images/dialog_lt.png', sizingMethod='crop');\"><div style='width:13px;'></div></td>");
	arr.push("    <td height='33' style=\"background-image:url("+Server.ContextPath+"Framework/Images/dialog_ct.png) !important;background-image: none;filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+Server.ContextPath+"Framework/Images/dialog_ct.png', sizingMethod='crop');\"><div style=\"float:left;font-weight:bold; color:#FFFFFF; padding:9px 0 0 4px;\"><img src=\""+Server.ContextPath+"Framework/Images/icon_dialog.gif\" align=\"absmiddle\">&nbsp;"+this.Title+"</div>");
	arr.push("      <div style=\"position: relative;cursor:pointer; float:right; margin:5px 0 0; _margin:4px 0 0;height:17px; width:28px; background-image:url("+Server.ContextPath+"Framework/Images/dialog_closebtn.gif)\" onMouseOver=\"this.style.backgroundImage='url("+Server.ContextPath+"Framework/Images/dialog_closebtn_over.gif)'\" onMouseOut=\"this.style.backgroundImage='url("+Server.ContextPath+"Framework/Images/dialog_closebtn.gif)'\" drag='false' onClick=\"Dialog.getInstance('"+this.ID+"').CancelButton.onclick.apply(Dialog.getInstance('"+this.ID+"').CancelButton,[]);\"></div></td>");
	arr.push("    <td width='13' height='33' style=\"background-image:url("+Server.ContextPath+"Framework/Images/dialog_rt.png) !important;background-image: none;filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+Server.ContextPath+"Framework/Images/dialog_rt.png', sizingMethod='crop');\"><div style=\"width:13px;\"></div></td>");
	arr.push("  </tr>");
	arr.push("  <tr drag='false'><td width='13' style=\"background-image:url("+Server.ContextPath+"Framework/Images/dialog_mlm.png) !important;background-image: none;filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+Server.ContextPath+"Framework/Images/dialog_mlm.png', sizingMethod='crop');\"></td>");
	arr.push("    <td align='center' valign='top'>");
	arr.push("    <table width='100%' border='0' cellpadding='0' cellspacing='0' bgcolor='#FFFFFF'>");
	arr.push("        <tr id='_MessageRow_"+this.ID+"' style='display:none'>");
	arr.push("          <td height='50' valign='top'><table id='_MessageTable_"+this.ID+"' width='100%' border='0' cellspacing='0' cellpadding='8' style=\" background:#EAECE9 url("+Server.ContextPath+"Framework/Images/dialog_bg.jpg) no-repeat right top;\">");
	arr.push("              <tr><td width='25' height='50' align='right'><img id='_MessageIcon_"+this.ID+"' src='"+Server.ContextPath+"Framework/Images/window.gif' width='32' height='32'></td>");
	arr.push("                <td align='left' style='line-height:16px;'>");
	arr.push("                <h5 class='fb' id='_MessageTitle_"+this.ID+"'>&nbsp;</h5>");
	arr.push("                <div id='_Message_"+this.ID+"'>&nbsp;</div></td>");
	arr.push("              </tr></table></td></tr>");
	arr.push("        <tr><td align='center' valign='top'>");
	arr.push("          <iframe ");
	if(this.URL.indexOf(":")==-1){
		arr.push("src='"+Server.ContextPath+this.URL+"'");
	}else{
		arr.push("src='"+this.URL+"'");
	}
	arr.push(" id='_DialogFrame_"+this.ID+"' allowTransparency='true'  width='"+this.Width+"' height='"+this.Height+"' frameborder='0' style=\"background-color: #transparent; border:none;\"></iframe></td></tr>");
	arr.push("        <tr drag='false' id='_ButtonRow_"+this.ID+"'><td height='36'>");
	arr.push("            <div id='_DialogButtons_"+this.ID+"' style='text-align:right; border-top:#dadee5 1px solid; padding:8px 20px; background-color:#f6f6f6;'>");
	arr.push("           	<input id='_ButtonOK_"+this.ID+"'  type='button' value='确 定'>");
	arr.push("           	<input id='_ButtonCancel_"+this.ID+"'  type='button' onclick=\"Dialog.getInstance('"+this.ID+"').close();\" value='取 消'>");
	arr.push("            </div></td></tr>");
	arr.push("      </table><a href='#;' onfocus='$(\"_DialogFrame_"+this.ID+"\").focus();'></a></td>");
	arr.push("    <td width='13' style=\"background-image:url("+Server.ContextPath+"Framework/Images/dialog_mrm.png) !important;background-image: none;filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+Server.ContextPath+"Framework/Images/dialog_mrm.png', sizingMethod='crop');\"></td></tr>");
	arr.push("  <tr><td width='13' height='13' style=\"background-image:url("+Server.ContextPath+"Framework/Images/dialog_lb.png) !important;background-image: none;filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+Server.ContextPath+"Framework/Images/dialog_lb.png', sizingMethod='crop');\"></td>");
	arr.push("    <td style=\"background-image:url("+Server.ContextPath+"Framework/Images/dialog_cb.png) !important;background-image: none;filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+Server.ContextPath+"Framework/Images/dialog_cb.png', sizingMethod='crop');\"></td>");
	arr.push("    <td width='13' height='13' style=\"background-image:url("+Server.ContextPath+"Framework/Images/dialog_rb.png) !important;background-image: none;filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+Server.ContextPath+"Framework/Images/dialog_rb.png', sizingMethod='crop');\"></td>");
	arr.push("  </tr></table>");
	this.TopWindow = pw;

	var bgdiv = pw.$("_DialogBGDiv");
	if(!bgdiv){
		bgdiv = pw.document.createElement("div");
		bgdiv.id = "_DialogBGDiv";
		bgdiv.style.cssText = "background-color:#333;position:absolute;left:0px;top:0px;opacity:0;filter:alpha(opacity=0);width:100%;height:" + mh + "px;z-index:960";
		$E.hide(bgdiv);
	 	pw.$T("body")[0].appendChild(bgdiv);
	}

	var div = pw.$("_DialogDiv_"+this.ID);
	if(!div){
		div = pw.document.createElement("div");
		$E.hide(div);
		div.id = "_DialogDiv_"+this.ID;
		div.className = "dialogdiv";
		div.setAttribute("dragStart","Dialog.dragStart");
	 	pw.$T("body")[0].appendChild(div);
	}
	div.onmousedown = function(evt){
		var w = $E.getTopLevelWindow();
		w.DragManager.onMouseDown(evt||w.event,this);
	}

	this.DialogDiv = div;

	div.innerHTML = arr.join('\n');

	pw.$("_DialogFrame_"+this.ID).DialogInstance = this;

	pw.Effect.initCtrlStyle(pw.$("_ButtonOK_"+this.ID));
	pw.Effect.initCtrlStyle(pw.$("_ButtonCancel_"+this.ID));

	this.OKButton = pw.$("_ButtonOK_"+this.ID);
	this.CancelButton = pw.$("_ButtonCancel_"+this.ID);

	//显示标题图片
	if(this.ShowMessageRow){
		$E.show(pw.$("_MessageRow_"+this.ID));
		if(this.MessageTitle){
			pw.$("_MessageTitle_"+this.ID).innerHTML = this.MessageTitle;
		}
		if(this.Message){
			pw.$("_Message_"+this.ID).innerHTML = this.Message;
		}
	}

	//显示按钮栏
	if(!this.ShowButtonRow){
		pw.$("_ButtonRow_"+this.ID).hide();
	}

	if(this.OKEvent){
		this.OKButton.onclick = this.OKEvent;
	}
	if(this.CancelEvent){
		this.CancelButton.onclick = this.CancelEvent;
	}
	if(!this.AlertFlag){
		//$E.show(bgdiv);
		this.bgdivID = "_DialogBGDiv";
	}else{
		bgdiv = pw.$("_AlertBGDiv");
		if(!bgdiv){
			bgdiv = pw.document.createElement("div");
			bgdiv.id = "_AlertBGDiv";
			bgdiv.style.cssText = "background-color:#333;position:absolute;left:0px;top:0px;opacity:0;filter:alpha(opacity=0);width:100%;height:" + mh + "px;z-index:991";
			$E.hide(bgdiv);
		 	pw.$T("body")[0].appendChild(bgdiv);
		}
		//$E.show(bgdiv);
		this.bgdivID = "_AlertBGDiv";
	}
	if(bgdiv.style.display=="none"){
		if(this.coverSelect){
			bgdiv.innerHTML='<iframe src="about:blank" style="filter:alpha(opacity=0);" width="100%" height="100%"></iframe>';
		}
		if(this.Animator){
			$E.show(bgdiv);
			pw.Effect.fade(bgdiv,0,3,0.2);
		}else{
			pw.Effect.setAlpha(bgdiv,3)
			$E.show(bgdiv);
		}
	}
	this.DialogDiv.style.cssText = "position:absolute; display:block;z-index:"+(this.AlertFlag?992:990)+";left:"+this.Left+"px;top:"+this.Top+"px";

	//判断当前窗口是否是对话框，如果是，则将其置在bgdiv之后
	if(!this.AlertFlag){
		var win = window;
		var flag = false;
		while(win!=win.parent){//需要考虑父窗口是弹出窗口中的一个iframe的情况
			if(win._DialogInstance){
				win._DialogInstance.DialogDiv.style.zIndex = 959;
				flag = true;
				break;
			}
			win = win.parent;
		}
		if(!flag){
			//bgdiv.style.cssText = "background-color:#333;position:absolute;left:0px;top:0px;opacity:0;filter:alpha(opacity=0);width:100%;height:" + mh + "px;z-index:960";
			pw.Effect.setAlpha(bgdiv,3)
			$E.show(bgdiv);
		}
		this.ParentWindow.$D = this;
	}
	if(isIE){
		var pwbody=doc.getElementsByTagName(isQuirks?"BODY":"HTML")[0];
		pwbody.style.overflow="hidden";//禁止出现滚动条
	}

	//放入队列中，以便于ESC时正确关闭
	pw.Dialog._Array.push(this.ID);
}

Dialog.hideAllFlash = function(win){//非IE浏览器在对话框弹出时必须手工隐藏flash
	if(!win||!win.$T){//有可能是Dialog.alert()
		return;
	}
	return;//
	var swfs = win.$T("embed");
	for(var i=0;i<swfs.length;i++){
		try{
			swfs[i].OldStyle = swfs[i].style.display;
			swfs[i].style.display = 'none';
		}catch(ex){}
	}
	var fs = win.$T("iframe");
	for(var i=0;fs&&i<fs.length;i++){
		Dialog.hideAllFlash(fs[i].contentWindow);
	}
}

Dialog.showAllFlash = function(win){
	if(!win||!win.$T){
		return;
	}
	return;//
	var swfs = win.$T("embed");
	for(var i=0;i<swfs.length;i++){
		try{
			swfs[i].style.display = swfs[i].OldStyle;
		}catch(ex){}
	}
	var fs = win.$T("iframe");
	for(var i=0;fs&&i<fs.length;i++){
		Dialog.hideAllFlash(fs[i].contentWindow);
	}
}

Dialog.prototype.addParam = function(paramName,paramValue){
		this.DialogArguments[paramName] = paramValue;
}

Dialog.prototype.close = function(){
	if(this.WindowFlag){
		this.ParentWindow.$D = null;
		this.ParentWindow.$DW = null;
		this.Window.opener = null;
		this.Window.close();
		this.Window = null;
	}else{
		//如果上级窗口是对话框，则将其置于bgdiv前
		var pw = $E.getTopLevelWindow();
		var doc=pw.document;
		var win = window;
		var flag = false;
		while(win!=win.parent){
			if(win._DialogInstance){
				flag = true;
				win._DialogInstance.DialogDiv.style.zIndex = 960;
				break;
			}
			win = win.parent;
		}
		if(this.AlertFlag){
			if(this.coverSelect){
				pw.$("_AlertBGDiv").innerHTML='';
			}
			if(this.Animator){
				pw.Effect.fade(pw.$("_AlertBGDiv"),3,0,0.5,function(){$E.hide(pw.$("_AlertBGDiv"));});
			}else{
				$E.hide(pw.$("_AlertBGDiv"));
			}
		}
		if(!flag&&!this.AlertFlag){//此处是为处理弹出窗口被关闭后iframe立即被重定向时背景层不消失的问题
			if(this.coverSelect){
				pw.$("_DialogBGDiv").innerHTML='';
			}
			pw.eval('window._OpacityFunc = function(){var w = $E.getTopLevelWindow();$E.hide(w.$("_DialogBGDiv"));}');
			if(this.Animator){
				pw.Effect.fade(pw.$("_DialogBGDiv"),3,0,0.5,pw._OpacityFunc);
			}else{
				pw._OpacityFunc();
			}
		}
		this.DialogDiv.outerHTML = "";
		if(isIE){
			var pwbody=doc.getElementsByTagName(isQuirks?"BODY":"HTML")[0];
			pwbody.style.overflow="auto";//还原滚动条
		}
		pw.Dialog._Array.remove(this.ID);
	}
}

Dialog.prototype.addButton = function(id,txt,func){
	var html = "<input id='_Button_"+this.ID+"_"+id+"' type='button' value='"+txt+"'> ";
	var pw = $E.getTopLevelWindow();
	pw.$("_DialogButtons_"+this.ID).$T("input")[0].getParent("a").insertAdjacentHTML("beforeBegin",html);
	Effect.initCtrlStyle(pw.$("_Button_"+this.ID+"_"+id));
	pw.$("_Button_"+this.ID+"_"+id).onclick = func;
}

Dialog.prototype.resize = function(w,h){
	this.Width = w;
	this.Height = h;
	var pw = $E.getTopLevelWindow();
	var doc = pw.document;
	var sw = Math.max(document.documentElement.scrollLeft, doc.body.scrollLeft);
	var sh = Math.max(doc.documentElement.scrollTop, doc.body.scrollTop);//考虑滚动的情况
	var cw = doc.compatMode == "BackCompat"?doc.body.clientWidth:doc.documentElement.clientWidth;
	var ch = doc.compatMode == "BackCompat"?doc.body.clientHeight:doc.documentElement.clientHeight;//必须考虑文本框处于页面边缘处，控件显示不全的问题
	this.Top = (ch - this.Height - 30) / 2 + sh - 8;//有8像素的透明背景
	this.Left = (cw - this.Width - 24) / 2 +sw;
	if(this.ShowButtonRow){//按钮行高36
		this.Top -= 18;
	}
	this.DialogDiv.style.left = this.Left+"px";
	this.DialogDiv.style.top = this.Top+"px";
	this.DialogDiv.$T("table")[0].width = w+26;
	var frame = pw.$("_DialogFrame_"+this.ID);
	frame.width = this.Width;
	frame.height = this.Height;
}

Dialog.close = function(evt){
	try{
		window.Args._DialogInstance.close();
	}catch(ex){}
}

Dialog.closeEx = Dialog.closeAlert = Dialog.endWait = function(){
	try{
		var pw = $E.getTopLevelWindow()
		pw.Dialog.getInstance("_DialogAlert"+(pw.Dialog.AlertNo-1)).close();
		pw.clearTimeout(pw.Dialog.WaitID);
	}catch(ex){
		alert(ex.message);
	}
}

Dialog.getInstance = function(id){
	var pw = $E.getTopLevelWindow()
	var f = pw.$("_DialogFrame_"+id);
	if(!f){
		return null;
	}
	return f.DialogInstance;
}

Dialog.AlertNo = 0;
Dialog.alert = function(msg,func,w,h){
	var pw = $E.getTopLevelWindow()
	var diag = new Dialog("_DialogAlert"+pw.Dialog.AlertNo++);
	diag.ParentWindow = pw;
	diag.Width = w?w:300;
	diag.Height = h?h:120;
	diag.Title = "系统提示";
	diag.URL = "javascript:void(0);";
	diag.AlertFlag = true;
	diag.CancelEvent = function(){
		diag.close();
		if(func){
			func();
		}
	};
	diag.show();
	pw.$("_AlertBGDiv").show();
	$E.hide(pw.$("_ButtonOK_"+diag.ID));
	var diagFrame=pw.$("_DialogFrame_"+diag.ID);
	var win = diagFrame.contentWindow;
	var doc = win.document;
	doc.open();
	doc.write("<body oncontextmenu='return false;'></body>") ;
	var arr = [];
	arr.push("<table height='124' border='0' align='center' cellpadding='10' cellspacing='0'>");
	arr.push("<tr><td align='right'><img id='Icon' src='"+Server.ContextPath+"Framework/Images/icon_alert.gif' width='34' height='34' align='absmiddle'></td>");
	arr.push("<td align='left' id='Message' style='font-size:9pt'>"+msg+"</td></tr></table>");
	var div = doc.createElement("div");
	div.innerHTML = arr.join('');
	doc.body.appendChild(div);
	doc.close();
	var h = Math.max(doc.documentElement.scrollHeight, doc.body.scrollHeight);
	var w = Math.max(doc.documentElement.scrollWidth, doc.body.scrollWidth);
	if(w>300){
		if(w>900){
			w=900;
		}
		win.frameElement.width = w;
	}
	if(h>120){
		if(h>400){
			h=400;
		}
		win.frameElement.height = h;
	}
	doc = pw.document;
	var cw = doc.compatMode == "BackCompat"?doc.body.clientWidth:doc.documentElement.clientWidth;
	var ch = doc.compatMode == "BackCompat"?doc.body.clientHeight:doc.documentElement.clientHeight;
	var sw = Math.max(doc.documentElement.scrollLeft, doc.body.scrollLeft);
	var sh = Math.max(doc.documentElement.scrollTop, doc.body.scrollTop);//考虑滚动的情况
	var top = (ch - h - 30) / 2 + sh - 8;
	var left = (cw - w - 12) / 2 +sw;
	diag.DialogDiv.style.cssText = "position:absolute; display:block;z-index:992;left:"+left+"px;top:"+top+"px";
	diag.CancelButton.value = "确 定";
	diag.CancelButton.focus();
	pw.$("_DialogButtons_"+diag.ID).style.textAlign = "center";
}

Dialog.confirm = function(msg,func1,func2,w,h){
	var pw = $E.getTopLevelWindow();
	var diag = new Dialog("_DialogAlert"+pw.Dialog.AlertNo++);
	diag.Width = w?w:300;
	diag.Height = h?h:120;
	diag.Title = "信息确认";
	diag.URL = "javascript:void(0);";
	diag.AlertFlag = true;
	diag.CancelEvent = function(){
		diag.close();
		if(func2){
			func2();
		}
	};
	diag.OKEvent = function(){
		diag.close();
		if(func1){
			func1();
		}
	};
	diag.show();
	pw.$("_AlertBGDiv").show();
	var diagFrame=pw.$("_DialogFrame_"+diag.ID);
	var win = diagFrame.contentWindow;
	var doc = win.document;
	doc.open();
	doc.write("<body oncontextmenu='return false;'></body>") ;
	var arr = [];
	arr.push("<table height='100%' border='0' align='center' cellpadding='10' cellspacing='0'>");
	arr.push("<tr><td align='right'><img id='Icon' src='"+Server.ContextPath+"Framework/Images/icon_query.gif' width='34' height='34' align='absmiddle'></td>");
	arr.push("<td align='left' id='Message' style='font-size:9pt'>"+msg+"</td></tr></table>");
	var div = doc.createElement("div");
	div.innerHTML = arr.join('');
	doc.body.appendChild(div);
	doc.close();
	diag.CancelButton.focus();
	pw.$("_DialogButtons_"+diag.ID).style.textAlign = "center";
}

Dialog.wait = function(msg){//某些地方不需要进度条但后台执行时间又比较长的可以使用此方法
	var pw = $E.getTopLevelWindow();
	var script = [];
	Dialog.alert(msg,null);
	pw.Dialog.WaitID = pw.setTimeout(pw.Dialog.waitAction,1000);
	var diag = pw.Dialog.getInstance("_DialogAlert"+(pw.Dialog.AlertNo-1));
	diag.CancelButton.disable();
	diag.CancelButton.onclick = function(){};
	pw.Dialog.WaitSecondCount = 0;
}

Dialog.waitAction = function(){
	var pw = $E.getTopLevelWindow();
	var diag = pw.Dialog.getInstance("_DialogAlert"+(pw.Dialog.AlertNo-1));
	pw.Dialog.WaitSecondCount++;
	diag.CancelButton.value = "请等待("+pw.Dialog.WaitSecondCount+")..."
	pw.Dialog.WaitID = pw.setTimeout(pw.Dialog.waitAction,1000);
}

var _DialogInstance = window.frameElement?window.frameElement.DialogInstance:null;
Page.onDialogLoad = function(){
	if(_DialogInstance){
		if(_DialogInstance.Title){
			document.title = _DialogInstance.Title;
		}
		window.Args = _DialogInstance.DialogArguments;
		_DialogInstance.Window = window;
		window.Parent = _DialogInstance.ParentWindow;
	}
}
Page.onDialogLoad();

Page.onReady(function (){
	var d = _DialogInstance;
	if(d){
		try{
			d.ParentWindow.$DW = d.Window;
			var flag = false;
			if(!d.AlertFlag){
				var win = d.ParentWindow;
				while(win!=win.parent){
					if(win._DialogInstance){
						flag = true;
						break;
					}
					win = win.parent;
				}
				if(!flag){
					//if(d.Animator)
					//Effect.fade($E.getTopLevelWindow().$("_DialogBGDiv"),0,3,0.2);//如果不是对话框里弹出的对话框，则显示渐变效果
				}
			}
			if(d.AlertFlag){
				$E.show($E.getTopLevelWindow().$("_AlertBGDiv"));
			}
			if(d.ShowButtonRow&&$E.visible(d.CancelButton)){
				d.CancelButton.focus();
			}
			if(d.onLoad){
				Page.onLoad(function(){
					d.onLoad();
				});
			}
		}catch(ex){alert("DialogOnLoad:"+ex.message+"\t("+ex.fileName+" "+ex.lineNumber+")");}
	}
},4);

Dialog.onKeyDown = function(event){
	if(event.shiftKey&&event.keyCode==9){//屏蔽shift+tab键
		var pw = $E.getTopLevelWindow();
		if(pw.Dialog._Array.length>0){
			stopEvent(event);
			return false;
		}
	}
	if(event.keyCode==27){//按ESC键关闭Dialog
		var pw = $E.getTopLevelWindow();
		if(pw.Dialog._Array.length>0){
			Page.mousedown();
			Page.click();
			var diag = pw.Dialog.getInstance(pw.Dialog._Array[pw.Dialog._Array.length-1]);
			diag.CancelButton.onclick.apply(diag.CancelButton,[]);
		}
	}
};

Dialog.dragStart = function(evt){
	DragManager.doDrag(evt,this.getParent("div"));
}

Dialog.setPosition=function(){
	if(window.parent!=window)return;
	var pw = $E.getTopLevelWindow();
	var DialogArr=pw.Dialog._Array;
	if(DialogArr==null||DialogArr.length==0)return;

	for(i=0;i<DialogArr.length;i++)
	{
		pw.$("_DialogFrame_"+DialogArr[i]).DialogInstance.setPosition();
	}
}
Dialog.prototype.setPosition=function(){
	var pw = $E.getTopLevelWindow();
	var doc = pw.document;
	var cw = doc.compatMode == "BackCompat"?doc.body.clientWidth:doc.documentElement.clientWidth;
	var ch = doc.compatMode == "BackCompat"?doc.body.clientHeight:doc.documentElement.clientHeight;//必须考虑文本框处于页面边缘处，控件显示不全的问题
	var sl = Math.max(doc.documentElement.scrollLeft, doc.body.scrollLeft);
	var st = Math.max(doc.documentElement.scrollTop, doc.body.scrollTop);//考虑滚动的情况
	var sw = Math.max(doc.documentElement.scrollWidth, doc.body.scrollWidth);
	var sh = Math.max(doc.documentElement.scrollHeight, doc.body.scrollHeight);
	sw=Math.max(sw,cw);
	sh=Math.max(sh,ch);
	this.Top = (ch - this.Height - 30) / 2 + st - 8;//有8像素的透明背景
	this.Left = (cw - this.Width - 12) / 2 +sl;
	if(this.ShowButtonRow){//按钮行高36
		this.Top -= 18;
	}
	this.DialogDiv.style.top=this.Top+"px";
	this.DialogDiv.style.left=this.Left+"px";
	//pw.$(this.bgdivID).style.width= sw + "px";
	pw.$(this.bgdivID).style.height= sh + "px";
}

if(isIE){
	document.attachEvent("onkeydown",Dialog.onKeyDown);
	window.attachEvent('onresize',Dialog.setPosition);
}else{
	document.addEventListener("keydown",Dialog.onKeyDown,false);
	window.addEventListener('resize',Dialog.setPosition,false);
}
