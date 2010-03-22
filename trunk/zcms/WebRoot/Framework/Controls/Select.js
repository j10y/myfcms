var Selector = {};

Selector.show = function(ele){
	var pw = $E.getTopLevelWindow();
	ele = $E.getParent("div",ele);
	var url = ele.$A("listurl");
	pw.SourceWindow = window;
	if(ele.id!=pw.Selector.showingID||!pw.Selector.showingID){
		var div = pw.$("_SelectorDiv_");
		if(!div){
			div = pw.document.createElement("div");	
			div.id = "_SelectorDiv_";
			div.style.position = "absolute";//不能直接隐藏，否则会导致Gecko下currentStyle失败。
			div.style.left = 0;
			div.style.top = 0;
			div.style.width = 0;	
			div.style.height = 0;
		 	pw.$T("body")[0].appendChild(div);
		 	div = $(div);
		}
	 	if(url){
	 		if(!pw.$("_SelectorFrame"+ele.id)||pw.$("_SelectorFrame"+ele.id).SourcePathName!=window.location.pathname){
	 			if(url.indexOf(":")==-1){
					url = Server.ContextPath+url;
				}
 				div.innerHTML = "<iframe id='_SelectorFrame"+ele.id+"' frameborder='0' width='100%' src='"+url+"'></iframe>";
			 	var fwin = pw.$("_SelectorFrame"+ele.id).contentWindow;
			 	pw.$("_SelectorFrame"+ele.id).SourcePathName = window.location.pathname;
			 	fwin._OnLoad = function(){
			 		Selector.removeFrameMouseDownEvent(fwin);
			 		fwin.SelectedText = ele.textField.value;
			 		fwin.SelectedValue = ele.value;
			 		fwin.SelectorID = ele.id;
			 		if(ele._ScrollTop){
			 			fwin.document.body.scrollTop = ele._ScrollTop;
			 		}
			 		Selector.setListURLStyle(ele.id);
				}
 			}else{
 				Selector.setListURLStyle(ele.id);
 				var fwin = pw.$("_SelectorFrame"+ele.id).contentWindow;
			 	fwin.SelectedText = ele.textField.value;
		 		fwin.SelectedValue = ele.value;
 			}
	 	}else{
	 		if(!pw.$("_SelectorFrame")){
	 			div.innerHTML = "<iframe id='_SelectorFrame' frameborder='0' width='100%' src='about:blank'></iframe>";
				var win = pw.$("_SelectorFrame").contentWindow;
				var doc = win.document;
				doc.open();
				doc.write("<style type='text/css'>* { box-sizing: border-box; -moz-box-sizing: border-box; -khtml-box-sizing: border-box; -webkit-box-sizing: border-box; }");
				doc.write("html,body {scrollbar-arrow-color: #68a;scrollbar-3dlight-color: #acd;scrollbar-shadow-color: #9bc;scrollbar-face-color: #def;scrollbar-darkshadow-color: #def;scrollbar-highlight-color: #fff;scrollbar-track-color: #eee;}");
				doc.write("body{margin: 0;padding: 0;color: #444;min-height:100%;height: auto;_height:100%;_overflow:auto;text-align: justify;text-justify: inter-ideograph;font: 12px/1.4 Tahoma, SimSun, Verdana, sans-serif;background: #ffffff;}");
				doc.write("body,a,div,img{ margin: 0; padding: 0;box-sizing: border-box; -moz-box-sizing: border-box; -khtml-box-sizing: border-box; -webkit-box-sizing: border-box;}");
				doc.write("div,p,span{font: 12px/1.4 Tahoma, SimSun, Verdana, sans-serif;word-break: break-all;};");
				doc.write(".optgroup {position:absolute;z-index:666;left:0;top:0;color: #369;}");
				doc.write(".optgroup div{padding:2px;overflow: auto;overflow-x: hidden;max-height:300px;color: #369;border: 1px solid #678;background: #f7fafc url("+Server.ContextPath+"Platform/Images/textarea_bg.gif) repeat 0 2px;width:auto;z-index:888;}");
				doc.write(".optgroup a{cursor:default;display:block;color: #369;white-space: nowrap;padding:1px 3px 2px 6px;_padding:0 3px 0 6px;height:18px;min-width:2em;text-decoration:none;}");
				doc.write(".optgroup a:hover,.optgroup a.ahover{color: #cff;text-decoration:none;background:#49e url("+Server.ContextPath+"Framework/Images/optionbg_over.gif) repeat-x center;}");
				doc.write(".optgroup a.ahover{background-image:none;}</style>");
				doc.write("<body onselectstart='return false;' style='margin: 0px;-moz-user-select:none;' oncontextmenu='return false;'></body>") ;
				try{doc.close();}catch(ex){}//Firefox下可能会报错
				Selector.setListStyle(ele.id);
	 		}else{
	 			Selector.setListStyle(ele.id);
	 		}
	 	}
	}
	Misc.lockScroll(window);
}

Selector.setListURLStyle = function(id){
	var pw = $E.getTopLevelWindow();
	pw.Selector.showingID = id;
	var ele = $(id);
	var div = pw.$("_SelectorDiv_");
	div.show();
	var dim = $E.getDimensions(ele);
	var pos = $E.getPositionEx(ele);
	var lw = ele.$A("listwidth")?parseInt(ele.$A("listwidth")):0;
	var lh = ele.$A("listheight")?parseInt(ele.$A("listheight")):0;
	var frame = pw.$("_SelectorFrame"+id);
	if(!lw){
		lw = dim.width;	
	}
	frame.width = lw;
	if(!lh){
		lh = 150;
	}
	frame.height = lh;
	pos = $E.computePosition(pos.x,pos.y,pos.x,pos.y+dim.height,"all",lw,lh,pw);	
	div.style.cssText = "position:absolute;display:inline-block;z-index:999;width:"+lw+"px;height:"+lh+"px;left:"+pos.x+"px;top:"+pos.y+"px";
	frame.style.border = "1px solid #678";
}

Selector.setListStyle = function(id){
	var pw = $E.getTopLevelWindow();
	pw.Selector.showingID = id;
	var html = $(id+"_list").outerHTML;
	var div = pw.$("_SelectorDiv_");
	div.show();
	var dim = $E.getDimensions(id);
	var pos = $E.getPositionEx(id);
	div.style.cssText = "position:absolute; display:inline-block;z-index:999;width:"+dim.width+"px;left:-1000px;top:-1000px";
	var frame = pw.$("_SelectorFrame");
	var doc = frame.contentWindow.document;
	frame.contentWindow.TopWindow = pw;
	html = html.replace(/Selector\./g,"TopWindow.Selector.");
	if(doc.body.childNodes.length==0){
		var listdiv = doc.createElement("div");
		listdiv.innerHTML = html;
		doc.body.appendChild(listdiv);
	}else{
		doc.body.childNodes[0].innerHTML = html;
	}
	var list = doc.getElementById(id+"_list");
	list.style.display='inline';
	list = doc.getElementById(id+"_ul");
	//设置listwidht,listheight
	var dimlist = $E.getDimensions(list);
	div = Selector.getSourceDiv(id);
	var lw = div.$A("listwidth")?parseInt(div.$A("listwidth")):0;
	var lh = div.$A("listheight")?parseInt(div.$A("listheight")):0;
	//alert([lw,dim.width]);
	if(!lw){
		lw = dim.width;	
	}
	frame.width = lw;
	list.style.width = lw +"px";
	if(!lh||lh>dimlist.height){
		lh = dimlist.height;
	}
	list.style.height = lh +"px";		
	frame.height = lh;

	if(div.options.length>0){
		list.getElementsByTagName("a")[div.selectedIndex].className = "ahover";//选中
		if(div._ScrollTop){
			list.scrollTop = div._ScrollTop;
		}
	}
	
	pos = $E.computePosition(pos.x,pos.y,pos.x,pos.y+dim.height,"right",lw,lh,pw);
	div = pw.$("_SelectorDiv_");
	div.style.left = pos.x + 2 + "px";
	div.style.top = pos.y +2 + "px";
}

Selector.removeFrameMouseDownEvent = function(win){
	var arr = win.Page.mouseDownFunctions;
	if(arr){
		for(var i=0;i<arr.length;i++){
			if(arr[i]==win.Selector.close){
				arr.remove(arr[i]);
			}
		}
	}
}

Selector.onArrowMouseOver = function(ele){
	var div = ele.parentElement;
	if(div.options.length>0){
		div.options[div.selectedIndex].className = "";
	}
	$E.addClassName("zSelectMouseOver",div);
}

Selector.onArrowMouseOut = function(ele){
	var div = ele.parentElement;
	$E.removeClassName("zSelectMouseOver",div);
}

Selector.getSourceDiv = function(id){//要兼顾初次加载的情况
	var div = null;
	var pw = $E.getTopLevelWindow();
	if(pw.SourceWindow&&pw.SourceWindow.$){
		div = pw.SourceWindow.$(id);
	}
	if(!div){
		div = $(id);
		if(!div||!div.InitFlag){
			alert("发生致命错误，显示列表时未找到"+id+"对应的Selector元素!");	
		}
	}
	return div;
}

Selector.setSelectedIndex = function(ele,index){
	var oldValue = ele.value;
	var item = $(ele.Items[index]);
	ele.ItemClickFlag = true;
	if(isIE){
		ele.value = item.$A("value");
	}else{
		ele._value = item.$A("value");
	}
	ele.textField.value = item.innerText;
	var pw = $E.getTopLevelWindow();
	ele.ItemClickFlag = false;
	if(oldValue != item.$A("value")){
		try{
			Selector.invokeOnChange(ele);
		}catch(ex){alert("Selector.invokeOnChange():"+ex.message);};
	}
}

Selector.onItemClick = function(ele,flag){//注意，此方法是从未引用Main.js的iframe中调用
	var id = ele.parentNode.id;
	id = id.substring(0,id.lastIndexOf("_"));
	var pw = $E.getTopLevelWindow();
	var div = pw.$("_SelectorDiv_");
	div = Selector.getSourceDiv(id);	
	var oldValue = div.value;
	div.textField.value = isGecko?ele.textContent:ele.innerText;
	div.ItemClickFlag = true;
	if(isIE){
		div.value = ele.getAttribute("value");
	}else{
		div._value = ele.getAttribute("value");
	}
	var items = div.options;
	for(var i=0;i<items.length;i++){
		if(items[i].getAttribute("value")==ele.getAttribute("value")){
			div._selectedIndex = i;			
			if(isIE){
				div.selectedIndex = i
			}
		}
	}
	div.ItemClickFlag = false;
	
	if(pw.$("_SelectorFrame")){
		div._ScrollTop = ele.parentNode.scrollTop;//列表再次展开时定位用
	}
	if(oldValue!=ele.getAttribute("value")){
		try{
			Selector.invokeOnChange(div);
		}catch(ex){alert("Selector.invokeOnChange():"+ex.message);};
	}
	Selector.close();
}

Selector.close = function(evt){
	var pw = $E.getTopLevelWindow();
	if(pw.Selector && pw.Selector.showingID){
	    if(pw.SourceWindow){
	  		var ctrl = pw.SourceWindow.$(pw.Selector.showingID).textField;
	  		if(evt){//在文本框上点击也会引发全局的onMouseDown事件，从而调用Selector.close
	  			evt = getEvent(evt);
	  			if(evt.srcElement==ctrl){
	  					return;
	  			}
	  		}
	  		//需要检查输入的值
	  		var ele = ctrl.parentNode;
	  		var verify = ele.$A("verify");
	  		var anyFlag = false;
	  		if(verify){
	  			var arr = verify.split("\&\&");
	  			for(var i=0;i<arr.length;i++){
	  				if(arr[i]=="Any"){
	  					anyFlag = true;	
	  				}
	  			}
	  		}
	  		if(!anyFlag&&ele.input){
					var txt = ele.textField.value.trim();
					var arr = ele.options;
					var len = arr.length;
					var flag = false;
					for(var i=0;i<len;i++){
						var innerText = isGecko?arr[i].textContent:arr[i].innerText;//Firefox下未始化innerText属性，原因待查
						if(innerText.trim()==txt){
							if(arr[i].getAttribute("value")!=ele.value){
								var frame = pw.$("_SelectorFrame");
								var doc = frame.contentWindow.document;
								var list = doc.getElementById(ele.id+"_ul");
								Selector.onItemClick(list.getElementsByTagName("a")[i]);
							}
							flag = true;
							break;
						}
					}
					if(!flag&&arr.length>0){
						ele.textField.value = isGecko?arr[ele.selectedIndex].textContent:arr[ele.selectedIndex].innerText;
					}
				}

	    	try{ctrl.onblur.apply(ctrl,[]);}catch(ex){}
 	    	Misc.unlockScroll(pw.SourceWindow);			
	    }
	    $E.hide(pw.$("_SelectorDiv_"));
			//pw.SourceWindow = null;//有可能导致找不到DIV
    	pw.Selector.showingID = false;
  }
}

Selector.onItemMouseOver = function(ele){
	var id = ele.parentNode.id;
	id = id.substring(0,id.lastIndexOf("_"));
	var div = Selector.getSourceDiv(id);
	ele.parentNode.getElementsByTagName("a")[div.selectedIndex].className = "";
	ele.className = "liOver";
}

Selector.setInput = function(ele,flag){
	if(!flag||flag=="false"){
		if(isIE){
			ele.textField.onselectstart = stopEvent;
			ele.textField.onmousedown = stopEvent;
		}else{
			ele.textField.style.MozUserSelect = "none";
			ele.textField.onmousedown = function(evt){
				evt = getEvent(evt);
				var pw = $E.getTopLevelWindow();
				var div = evt.srcElement.parentElement;
				if(div.id==pw.Selector.showingID&&pw.SourceWindow==window){
					return stopEvent(evt);
				}
			}
		}
		$E.removeClassName("zSelectEditable",ele);
		ele.textField.oncontextmenu  = stopEvent;
		ele.textField.onblur = null;
	}else{
		if(isIE){
			ele.textField.onselectstart = null;
			ele.textField.onmousedown = null;
		}else{
			ele.textField.style.MozUserSelect = "";
		}
		$E.addClassName("zSelectEditable",ele);
		ele.textField.onkeydown = Selector.onKeyDown;
		ele.textField.onkeyup = Selector.onKeyUp;
	}
	if(isGecko){
			ele._input = flag===true||flag=="true";
	}
}

Selector.onDoubleClick = function(evt){
		evt = getEvent(evt);
		Selector.show(evt.srcElement);
}

Selector.onClick = function(evt){
	evt = getEvent(evt);
	var ele = $(evt.srcElement.parentNode);
	if("true"==ele.$A("lazy")){
		ele.loadData(function(){
			$E.getTopLevelWindow().Selector.showingID = 0;
			Selector.show(ele.textField);	
		});
	}
}

Selector.onKeyDown = function(evt){
	evt = getEvent(evt);
	var txt = evt.srcElement;
	var div = txt.parentNode;
	
	//未加载
	if("true"==div.$A("lazy")){
		ele.loadData(function(){
			$E.getTopLevelWindow().Selector.showingID = 0;
			Selector.show(ele.textField);	
		});
		return;
	}
	
	var pw = $E.getTopLevelWindow();
	if(div.$A("listurl")){//自定义的下拉框调用自定义的nextItem(),previousItem()
		var win = pw.$("_SelectorFrame"+div.id).contentWindow;
		if(evt.keyCode==38){
			if(win.nextItem){
				win.nextItem();
			}
		}else	if(evt.keyCode==40){
			if(win.previousItem){
				win.previousItem();
			}
		}
		stopEvent(evt);
		return;
	}
	if(!pw.$("_SelectorDiv_")||!pw.$("_SelectorDiv_").visible()){
		Selector.show(txt);
		div.KeyShowFlag = true;
		stopEvent(evt);
		return;
	}
	if(evt.keyCode==38){
		Selector.moveItem(div,false);
	}else	if(evt.keyCode==40){
		Selector.moveItem(div,true);
	}else	if(!evt.ctrlKey&&!evt.shiftKey&&!evt.altKey&&evt.keyCode!=9&&!div.input){//tab键
		stopEvent(evt);
	}
}

Selector.moveItem = function(ele,flag){//移动下拉列表中的当前选中项，要考虑有一部分被隐藏的情况
	var pw = $E.getTopLevelWindow();
	var frame = pw.$("_SelectorFrame");
	var doc = frame.contentWindow.document;
	var list = doc.getElementById(ele.id+"_ul");
	var arr = doc.getElementsByTagName("a");
	var currentItem = arr[ele.selectedIndex];
	var nextItem = null;
	var start = 0;
	if($E.visible(currentItem)){
		start = ele.selectedIndex;
	}	
	var len = arr.length;
	ele.ItemClickFlag = true;
	if(flag){
		for(var i=start+1;i<len;i++){
			var a = arr[i];
			var d = a.style.display;
			if(!d||d.toLowerCase()!='none'){
				ele.selectedIndex = i;
				nextItem = a;
				break;
			}
		}
	}else{
		for(var i=start-1;i>=0;i--){
			var a = arr[i];
			var d = a.style.display;
			if(!d||d.toLowerCase()!='none'){
				ele.selectedIndex = i;
				nextItem = a;
				break;
			}
		}
	}
	ele.ItemClickFlag = false;
	if(nextItem){
		currentItem.className = "hover";//取消选中
		nextItem.className = "ahover";//选中
		ele.textField.value = isGecko?nextItem.textContent:nextItem.innerText;
		var pos1 = $E.getPosition(currentItem);
		var pos2 = $E.getPosition(nextItem);
		list.scrollTop = list.scrollTop+pos2.y-pos1.y;
	}
}

Selector.onKeyUp = function(evt){
	evt = getEvent(evt);
	var pw = $E.getTopLevelWindow();
	if(evt.keyCode>=37&&evt.keyCode<=40){
		return;
	}
	var txt = evt.srcElement;
	var div = txt.parentElement;
	if(div.KeyShowFlag){
		div.KeyShowFlag = false;
		return;
	}
			
	var v = txt.value.replace(/　/g,"").trim();
	if(div.$A("listurl")){//自定义的下拉框调用自定义的onKeyUp
		var win = pw.$("_SelectorFrame"+div.id).contentWindow;
		if(win.onTextChange){
			win.onTextChange(v);
		}
		return;
	}
	var frame = pw.$("_SelectorFrame");
	var doc = frame.contentWindow.document;
	var arr = doc.getElementsByTagName("a");
	if(evt.keyCode==13){//回车
		Selector.onItemClick(arr[div.selectedIndex]);
	}
	if(!evt.ctrlKey&&!evt.shiftKey&&!evt.altKey&&evt.keyCode!=9&&div.input){//按Tab从别的控件转移焦点时应该显示全部选项
		Selector.filter(div,v);
	}
}

Selector.filter  = function(ele,v){
	var pw = $E.getTopLevelWindow();
	var frame = pw.$("_SelectorFrame");
	var doc = frame.contentWindow.document;
	var arr = doc.getElementsByTagName("a");
	var len = arr.length;
	v = v.toLowerCase();
	for(var i=0;i<len;i++){
		var a = arr[i];
		var str = isGecko?a.innerHTML:a.innerText;
		str = str.replace(/　/g,"").trim().toLowerCase();
		if(str.indexOf(v)>=0){
			a.style.display = '';
		}else{
			a.style.display = 'none';
		}
	}
}

Selector.invokeOnChange = function(_ele){
	if(!_ele.InitFlag){
		var _cv = _ele.getAttribute("onChange");
		if(_cv){			
			var pw = $E.getTopLevelWindow();
			if(pw.SourceWindow){
				pw.SourceWindow.eval(_cv);
			}else{
				eval(_cv);
			}
		}
	}
}

Selector.setValueEx = function(ele,v,t){
	ele = $(ele);
	ele.value = v;
	ele.textField.value = t;
}

Selector.setValue = function(ele,v){
		if(ele.$A("listURL")){
			ele.textField.value = v;
			ele._value = v;
		}else{
			var flag = false;
			var len = ele.Items.length;
			for(var i=0;i<len;i++){
				if($(ele.Items[i]).$A("value") == v){
					ele.textField.value = ele.Items[i].innerText;
					ele._value = v;
					if(isGecko){
						ele._selectedIndex = i;
					}else{
						ele.ItemClickFlag = true;
						ele.selectedIndex = i;
						ele.ItemClickFlag = false;	
					}
					flag = true;
					break;
				}
			}
			if(!flag&&ele.input){
				ele.textField.value = v;
				ele._value = v;
			}
		}
		Selector.invokeOnChange(ele);
}


Selector.setDisabled = function(ele,flag){
	if(flag||flag=="true"){
		ele.textField.disabled = true;
		$E.addClassName("zSelectDisabled",ele);
		ele.arrow.onmouseover =  stopEvent;
		ele.arrow.onmouseout =  stopEvent;
		ele.arrow.onclick =  stopEvent;
		ele.arrow.onmousedown =  stopEvent;
		ele.textField.style.color = "#aaa";
		ele.textField.ondblclick = stopEvent;
		ele.textField.onkeydown = stopEvent;
		ele.textField.onkeyup = stopEvent;
		ele.textField.onclick = stopEvent;
	}else{
		ele.textField.disabled = false;
		$E.removeClassName("zSelectDisabled",ele);
		ele.textField.ondblclick = Selector.onDoubleClick;
		ele.arrow.onmouseover =  function(){Selector.onArrowMouseOver(this)};
		ele.arrow.onmouseout =  function(){Selector.onArrowMouseOut(this)};
		ele.arrow.onmousedown =  function(evt){
			var pw = $E.getTopLevelWindow();
			var div = $E.getParent("div",this);
			if(pw.Selector&&pw.Selector.showingID==div.id){
				return;
			}
			Selector.show(this);
			this.parentElement.textField.onfocus.apply(this.parentElement.textField,arguments);
			stopEvent(evt);
		};
		ele.textField.style.color = "";
		ele.textField.onkeydown = Selector.onKeyDown;
		ele.textField.onkeyup = Selector.onKeyUp;
		ele.textField.onclick = Selector.onClick;
		ele.arrow.onclick = Selector.onClick;
	}
}

Selector.LastID = new Date().getTime();
Selector.initHtml = function(ele){
	var id = ele.id;
	if(id==null||id==""||id=="_ZVING_NOID_"){
		ele.id = id = Selector.LastID++;//产生随机ID
		ele.setAttribute("id",id);
	}
	var name = ele.$A("name");
	if(!name){
		name = id;
	}else{
		ele.removeAttribute("name");	
	}
	var items = ele.$T("span");
	var selectedIndex = 0;
	var selectedFlag = true;
	//以下兼容旧写法
	var len = items.length;
	var value = ele.$A("value");
	if(len>0||ele.innerHTML.trim()==""){
		var verify = ele.$A("verify");
		var condition = ele.$A("condition");
		var verifyStr = "";
		if(verify){
			verifyStr = "verify=\""+verify+"\"";
			if(condition){
				verifyStr += " condition=\""+condition+"\"";
			}
		}
		var classStr = ele.$A("zclass");
		if(classStr){
			classStr = "class=\""+classStr+"\"";
		}else{
			classStr = "";	
		}
		ele.className = "zSelect";
		var styleText = "";
		if(ele.$A("styleOriginal")&&ele.$A("styleOriginal")!="NULL"){
			styleText = ele.$A("styleOriginal");
		}else{
			var getWidth = ele.style.cssText;
			var ifWidth = /(.*width: *)([0-9]+)(px *;*.*)/gi;
			if(ifWidth.test(getWidth)){
				getWidth = getWidth.replace(ifWidth,
											function($1, $2, $3, $4){
												//alert("\n$1: "+$1+"\n$2: "+$2+"\n$3: "+$3+"\n$4: "+$4+"\n");
												return $3;
											}
											);
				styleText = "width:"+getWidth+"px;";
			}else{
				styleText = isIE?"width:115px;":"width:118px;";
			}
		}
		ele.style.cssText = "display:inline-block; *zoom: 1;*display: inline;vertical-align:middle;position:relative;height:20px;white-space: nowrap;padding:0 0 0 5px;margin-right:3px;";

		var arr = [];
		arr.push("<input type='text' id='"+id+"_textField' ztype='select' "+verifyStr+" "+classStr+" name='"+name+"' autocomplete='off' style='height:18px;line-height:18px;_line-height:12px; padding:0;padding-top:2px;vertical-align:top;border:0 none; background:transparent none; cursor:default;"+(isIE8?"line-height:18px;":"")+styleText+"' value=''/>");
		arr.push("<img class='arrowimg' src='"+Server.ContextPath+"Framework/Images/blank.gif' width='18' height='20' id='"+id+"_arrow' style='position:relative; left:-17px; margin-right:-18px; cursor:pointer; width:18px; height:20px;vertical-align:top; '/>");
		arr.push("<div id='"+id+"_list' class='optgroup' style='text-align:left;display:none;'>");
		arr.push("<div id='"+id+"_ul' style='left:-1px; width:-1px;'>");
		
		for(var i=0;i<len;i++){
			var iv = items[i].getAttribute("value");
			if(selectedFlag&&iv==value){
				selectedIndex = i;
				selectedFlag = false;
			}
			if(selectedFlag&&items[i].getAttribute("selected")!=null){
				selectedIndex = i;
			}
			arr.push("<a href=\"javascript:void(0);\" onclick=\"Selector.onItemClick(this);\" onmouseover='Selector.onItemMouseOver(this)' hidefocus value=\""+iv+"\">"+items[i].innerHTML+"</a>");
		}
		arr.push("</div>");
		arr.push("</div>");
		var html = arr.join('');
		if(html.indexOf("_ZVING_NOID_")>0){
			html = html.replace("_ZVING_NOID_",id);
		}
		ele.innerHTML = html;
	}else{
		ele.textField = ele.childNodes[0];
		if(ele.textField.style.width==""){
			ele.textField.style.width=isIE?"115px":"118px";
		}
		selectedIndex = parseInt(ele.$A("selectedIndex"));
		var html = ele.innerHTML;
		if(html.indexOf("_ZVING_NOID_")>0){
			ele.innerHTML = html.replace("_ZVING_NOID_",id);
		}
	}
	
	items = ele.$T("a");
	if(items.length>0&&value!=items[selectedIndex].$A("value")){//此处是因为z:init中可能会替换掉value的值
		var len = items.length;
		for(var i=0;i<len;i++){
			if(value!=null&&value!=''){
				if($(items[i]).$A("value")==value){
					selectedIndex = i;
					break;
				}
			}else{
				if(items[i].$A("selected")=="true"){
					selectedIndex = i;
					break;
				}			
			}
		}	
	}
	
	ele.InitFlag = true;
	if(isGecko){
		ele._selectedIndex = selectedIndex;
	}else{
		ele.selectedIndex = selectedIndex;
	}
}

Selector.initMethod  = function(ele){
	ele.textField = ele.childNodes[0];
	ele.name = ele.textField.name;
	ele.type = "select-one";
	ele.arrow = ele.childNodes[1];
	ele.options = ele.Items = $(ele.id+"_ul").$T("a");	
	if(!ele.$A("listurl")){//自定义页面不需要如下方法
		ele.Items = $(ele.id+"_ul").$T("a");	
		if(isIE){
			ele.value = ele.Items.length>0?$(ele.Items[ele.selectedIndex]).$A("value"):"";
		}else{
			ele._value = ele.Items.length>0?$(ele.Items[ele._selectedIndex]).$A("value"):"";
		}
		ele.form = ele.getParent("form");
		ele.length = ele.options.length;
		
		ele.setParam = function(k,v){
			if(!this.Params){
				this.Params = new DataCollection();
			}
			this.Params.add(k,v);
		}
		
		ele.loadData = function(func){
			Selector.loadData(this,func);
		}
		
		ele.setLinkAge = function(field,target){
			if(this.DataSource){
				if(this.selectedIndex==0){
					$S(target,null);
				}else{
					var dr = this.DataSource.Rows[this.selectedIndex-1];//前边默认有空行
					if(dr){
						$S(target,dr.get(field));
					}
				}
			}
		}
		
		ele.clear = function(){
			this.value = "";
			this.value = "";
			this.textField.value = "";
			if(!this.$T("DIV")[0].childNodes[0]){
				return;//已经是空的了
			}
			this.$T("DIV")[0].childNodes[0].innerHTML = "";
			this.Items = this.$T("DIV")[0].childNodes[0].$T("a");
			this.value = "";
			this.textField.value = "";
		}
		
		ele.getText = function(){
			return this.textField.value;
		}
		
		ele.remove = function(index){
			if(index<0||index>=this.Items.length){
				alert("下拉框不能移除index="+index+"的选项!");
				return;
			}
			this.Items[index].outerHTML = "";
			this.options = this.Items = this.$T("DIV")[0].childNodes[0].$T("a");
			if(this.selectedIndex==index){
				this.selectedIndex = index;//重新设置一遍
			}
		}
		
		ele.addBatch = function(arr,index){
			var showValue = ele.$A("showValue")=="true";
			var html = [];
			for(var i=0;i<arr.length;i++){
				var text = arr[i][0];
				var value = arr[i][1];
				if(showValue){
					if(text){
						text = value+'-'+text;	
					}else{
						text = "";	
					}
				}
				html.push("<a href=\"javascript:void(0);\" onclick=\"Selector.onItemClick(this);\" onmouseover='Selector.onItemMouseOver(this)'  hidefocus value=\""+value+"\">"+text+"</a>");
			}
			if(!this.Items||this.Items.length==0){
				this.$T("DIV")[0].childNodes[0].innerHTML = html.join('\n');
				this.options = this.Items = this.$T("DIV")[0].childNodes[0].$T("a");
				return;
			}
			var lastIndex = this.Items.length-1;
			if(index!=null){
				index = parseInt(index);
				if(index > lastIndex){
					index = lastIndex;
				}
			}else{
				index = lastIndex;
			}
			this.Items[index].insertAdjacentHTML("afterEnd",html.join('\n'));
			this.options = this.Items = this.$T("DIV")[0].childNodes[0].$T("a");
			if(this.Items.length>10){
				this.$T("DIV")[0].childNodes[0].style.height = "15em";
			}
		}
		
		ele.add = function(text,value,index){
			ele.addBatch([[text,value]],index);
		}
		try{
			ele.selectedIndex = ele.selectedIndex;
			if(ele.Items.length>0){
				ele.textField.value = isGecko?ele.Items[ele._selectedIndex].textContent:ele.Items[ele.selectedIndex].innerText;
			}
		}catch(ex){alert(ex.message+ele.id)}
	}
	

	ele.textField.onfocus = function(){
		Selector.show(this);
	}
		
	var disabled = ele.$A("disabled");
	ele.disabled = false;
	if(isIE){
		Selector.initMethodIE(ele);
	}else{
		Selector.initMethodGecko(ele);
	}
	ele.input = ele.$A("input")=='true'?true:false;
	ele.disabled = disabled;
	//alert([ele.$A("input"),ele.$A("input")=='true']);
	if(isIE){
		Selector.setInput(ele,ele.input);
		Selector.setDisabled(ele,disabled);
	}
	ele.InitFlag = false;
}

Selector.initMethodIE = function(ele){
	ele.onpropertychange = function(){
		var s = event.srcElement;
		var v = s[event.propertyName];
		switch(event.propertyName.toLowerCase()){
			case "disabled":
				Selector.setDisabled(s,v);
				break;
			case "selectedindex":
				if(!s.ItemClickFlag){
					if(s.Items.length>0){
						Selector.setSelectedIndex(s,s.selectedIndex);
					}
				}
				break;
			case "input":
				Selector.setInput(s,s.input);
				break;
			case "size":
				break;
			case "value":
				if(!s.ItemClickFlag){
					Selector.setValue(s,v);
				}
				break;
		};
	}
}
	
Selector.initMethodGecko = function(ele){
	ele.__defineGetter__("disabled",function(flag){
		return this.textField.disabled;	
	});

	ele.__defineSetter__("disabled",function(flag){
		Selector.setDisabled(this,flag);
	});
	
	ele.__defineGetter__("selectedIndex",function(){
		return this._selectedIndex;
	});
	
	ele.__defineSetter__("selectedIndex",function(index){
		index = parseInt(index);
		if(index>=0&&index<this.Items.length){
			this._selectedIndex = index
		}else{
			return;	
		}
		Selector.setSelectedIndex(this,this._selectedIndex);
	});
	
	ele.__defineGetter__("input",function(){
		return this._input;
	});
	
	ele.__defineSetter__("input",function(flag){
		this._input = flag!="false";
		Selector.setInput(this,this._input);
	});
	
	ele.__defineGetter__("size",function(){
		return this._size;
	});
	
	ele.__defineSetter__("size",function(size){
		this._Size = size;
		
	});
	
	ele.__defineGetter__("value",function(){
		return this._value;
	});
	
	ele.__defineSetter__("value",function(v){
		Selector.setValue(this,v);
	});
}

Selector.initCtrl = function(ele){
	ele = $(ele);
	Selector.initHtml(ele);
	Selector.initMethod(ele);		
}

Selector.setReturn = function(t,v){
	var id = window.SelectorID;
	var pw = $E.getTopLevelWindow();
	var div = Selector.getSourceDiv(id);	
	var oldValue = div.value;
	div.textField.value = t;
	if(isIE){
		div.ItemClickFlag = true;
		div.value = v;
		div.ItemClickFlag = false;
	}else{
		div._value = v;
	}
	div._ScrollTop = Math.max(document.documentElement.scrollTop, document.body.scrollTop);
	if(oldValue!=v){
		try{
			Selector.invokeOnChange(div);
		}catch(ex){alert("Selector.invokeOnChange():"+ex.message);};
	}
	if(pw.Selector.showingID){
    	$E.hide(pw.$("_SelectorDiv_"));
    	pw.Selector.showingID = 0;
  }
}

Selector.verifyInput = function(ele){//检查input=true时输入的值是否正确
	var ele = ele.parentNode;
	var txt = ele.textField.value.trim();
	var arr = ele.options;
	var len = arr.length;
	var flag = false;
	for(var i=0;i<len;i++){
		var innerText = isGecko?arr[i].textContent:arr[i].innerText;
		if(innerText.trim()==txt){
			flag = true;
			break;
		}
	}
	return flag;
}

Selector.loadData = function(ele,func){
	if(!ele.Params){
		ele.Params = new DataCollection();
	}
	if(!ele.$A("code")&&!ele.$A("method")){
		alert("未设置code或method属性!");
		return;
	}
	if(ele.Params.get("CodeType")==null){//第一次Params中没有数据，所以从HTML属性中取，以后则以Params中数据为准
		ele.Params.add("CodeType",ele.$A("code"));
	}
	if(ele.Params.get("Method")==null){
		ele.Params.add("Method",ele.$A("method"));
	}
	if(ele.Params.get("ConditionField")==null){
		ele.Params.add("ConditionField",ele.$A("conditionField"));
	}
	if(ele.Params.get("ConditionValue")==null){
		ele.Params.add("ConditionValue",ele.$A("conditionValue"));
	}
	Server.sendRequest("com.zving.framework.controls.CodeSourcePage.getData",ele.Params,function(response){
		var dt = response.get("DT");
		ele.clear();
		if(dt){
			var vs = dt.Values;
			var len = vs.length;
			var html = [];
			if(ele.$A("defaultblank")!="false"){
				html.push("<a href=\"javascript:void(0);\" onclick=\"Selector.onItemClick(this);\" hidefocus value=\"\"></a>");
			}
			var flag = "true"==ele.$A("showvalue");
			for(var i=0;i<len;i++){
				var text=vs[i][1],value=vs[i][0];
				if(flag){
					text = value+"-"+text;
				}
				html.push("<a href=\"javascript:void(0);\" onclick=\"Selector.onItemClick(this);\" hidefocus value=\""+value+"\">"+text+"</a>");
			}
			ele.$T("DIV")[0].childNodes[0].innerHTML = html.join('\n');
			ele.options = ele.Items = ele.$T("DIV")[0].childNodes[0].$T("a");
			if(ele.Items.length>10){
				ele.$T("DIV")[0].childNodes[0].style.height = "15em";
			}
		}
		ele.setAttribute("lazy",false);
		ele.selectedIndex = 0;
		ele.DataSource = dt;
		if(func){
			try{
				func();
			}catch(ex){}
		}
	});
}

Page.onMouseDown(Selector.close);
