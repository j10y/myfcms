function Menu(){
	this.X = null;
	this.Y = null;
	this.Param = null;
	this.Items = [];
	this.Event = null;
	
	Menu.prototype.setEvent = function(evt){
		var pos = getEventPosition(evt);
		this.X = pos.x;
		this.Y = pos.y;
		this.Event = evt;
	}
	
	Menu.prototype.setPosition = function(x,y){
		this.X = x;
		this.Y = y;
	}
	
	Menu.prototype.setParam = function(param){
		this.Param = param;	
	}
	// disabled 表示这个菜单项是否灰掉
	Menu.prototype.addItem = function(str,func,icon,disabled){
		this.Items.push([str,func,icon,disabled]);
	}
	
	Menu.prototype.onClick = function(index){
		if(!this.Items[index][3]){
			var func = this.Items[index][1];
			Menu.close();
			func.apply(this,[this.Param]);
		}
	}
	
	Menu.prototype.getHtml = function(){
		var html = [];
		html.push("<html><head><style>");
		html.push("body {margin: 0px;background: #FFFFFF;color: #444444}");
		html.push("td {font-size: 12px;}");
		html.push(".imgdisabled img{opacity: 0.5; filter: gray Alpha(Opacity=50);}");
		html.push("</style></head><body>");
		html.push("<table border='0' id='_ContextMenu_Table' cellspacing='1' cellpadding='0' oncontextmenu='TopWindow.stopEvent(event);' onselectstart='TopWindow.stopEvent(event);' style='-moz-user-select:none;border:1px solid #8F8F73;background:#FFFFFF;'><tr><td>");
		for(var i=0;i<this.Items.length;i++){
			if(this.Items[i][0]=="-"){//分隔线
				html.push("<table width='100%' border='0' cellspacing='0' cellpadding='0' height='1' style='padding-top:0px;padding-bottom:0px;height:1px; font-size:0px;background:#B9B99D;'><tr><td style='background:#D6DFF7; width:24px;' width='24'></td><td style='background:#B9B99D; width:66px;'></td></tr></table>");
				continue;
			}
			if(true==this.Items[i][3]){//表示这个菜单项是否灰掉
				html.push("<table width='100%' border='0' cellspacing='0' cellpadding='0' class='imgdisabled' style='font-size:12px'><tr>");	
				if(this.Items[i][2]){
					html.push("<td height='22' width='24' align='center' valign='middle' style='background:#DDF;'><img src='"+Server.ContextPath+this.Items[i][2]+"' width='20' height='20' hspace='2' vspace='1'/></td>");
				}else{
					html.push("<td height='22' width='24' align='center' valign='middle' style='background:#DDF;'><img src='"+Server.ContextPath+"Framework/Images/blank.gif' width='20' height='20' hspace='2' vspace='1'/></td>");
				}
				html.push("<td style='background:#F7F8FD; color:#999999; padding-left:10px;' nowrap='true'>"+this.Items[i][0]+"</td>");
				html.push("<td style='background:#F7F8FD; width:15px; text-align:center' width='15'>&nbsp;</td>");
			}else{
				html.push("<table width='100%' border='0' cellspacing='0' cellpadding='0' style='cursor:pointer;font-size:12px' onclick='TopWindow.Menu.getInstance().onClick("+i+")' onmouseout='TopWindow.Menu.onMouseOut(this)' onmouseover='TopWindow.Menu.onMouseOver(this)'><tr>");			
				if(this.Items[i][2]){
					html.push("<td height='22' width='24' align='center' valign='middle' style='background:#DDF;'><img src='"+Server.ContextPath+this.Items[i][2]+"' width='20' height='20' hspace='2' vspace='1'/></td>");
				}else{
					html.push("<td height='22' width='24' align='center' valign='middle' style='background:#DDF;'><img src='"+Server.ContextPath+"Framework/Images/blank.gif' width='20' height='20' hspace='2' vspace='1'/></td>");
				}
				html.push("<td style='background:#F7F8FD; padding-left:10px;cursor:pointer;' nowrap='true'>"+this.Items[i][0]+"</td>");
				html.push("<td style='background:#F7F8FD; width:15px; text-align:center' width='15'>&nbsp;</td>");
			}
			html.push("</tr></table>");
		}
		html.push("</td></tr></table>");
		html.push("<script>function $(ele){return document.getElementById(ele);}function init(){TopWindow.Menu.adjustSize();}</script></body></html>");
		return html.join('\n');
	}
	
	Menu.prototype.setPosition = function(){
		var pw = $E.getTopLevelWindow();
		var div = pw.$("_DivContextMenu");
		//div.style.left = -1000+"px";
		//div.style.top = -1000+"px";
		$E.show(div);
		var win = pw.$("_ContextMenu_Frame").contentWindow;
		var mH = win.$("_ContextMenu_Table").offsetHeight;
		var mW = win.$("_ContextMenu_Table").offsetWidth;
		var sW = Math.max(document.documentElement.scrollLeft, document.body.scrollLeft);
		var sH = Math.max(document.documentElement.scrollTop, document.body.scrollTop);
		
		var mX = 0;
		var mY = 0;
		var pos = $E.computePosition(this.X,this.Y,this.X,this.Y,"all",mW,mH,pw);
		mX = pos.x + sW;
		mY = pos.y + sH;
		
		pw.$("_DivContextMenu").style.width = mW + "px";
		pw.$("_DivContextMenu").style.height = mH + "px";//再次设置高宽是因为若不设置高宽，窗口滚动一段后，在靠近右边缘处右击，菜单可能会变形
		mX = mX < 0 ? 0 : mX;
		div.style.left = mX + "px";
		div.style.top = mY + "px";
	}
	
	Menu.prototype.show = function(){
		if(this.Items.length==0){
			return;
		}
		var pw = $E.getTopLevelWindow();
		var ele;
		if(!pw.$("_DivContextMenu")){
			ele = pw.document.createElement('div');
			ele.id = "_DivContextMenu";
			ele.style.position = "absolute";
			ele.style.zIndex = "999";
			ele.style.left = 0;
			ele.style.top = 0;
			ele.style.backgroundColor="#890";
			ele.innerHTML = "<iframe id='_ContextMenu_Frame' frameborder='0' scrolling='no' width='100'></iframe>";
			pw.document.body.appendChild(ele);
			var win = pw.$("_ContextMenu_Frame").contentWindow;
			var doc = win.document;
			doc.open();
			doc.write(this.getHtml());
			doc.close();				
			win.TopWindow = pw;
			win.init();
		}else{
			ele = pw.$("_DivContextMenu");
			ele.show();
			var frame = pw.$("_ContextMenu_Frame");
			frame.show();
			var win = pw.$("_ContextMenu_Frame").contentWindow;
			var doc = win.document;
			doc.open();
			doc.write(this.getHtml());
			doc.close();			
			win.TopWindow = pw;
			win.init();
		}	
		ele.onclick = stopEvent;
		ele.Instance = this;
		this.setPosition();
	}
	
}

Menu.adjustSize = function(){
	var win = $("_ContextMenu_Frame").contentWindow;
	var dim = $E.getDimensions(win.$("_ContextMenu_Table"));
	win.frameElement.height = dim.height;
	win.frameElement.width = dim.width;	
}

Menu.close = function(){
	var div = $E.getTopLevelWindow().$("_DivContextMenu")
	if(div){
		div.hide();
	}		
}

Menu.getInstance = function(){
	var div = $E.getTopLevelWindow().$("_DivContextMenu")
	if(div){
		return div.Instance;
	}		
}

Menu.onMouseOver = function(table){
	var arr = table.rows[0].cells;
	arr[0].style.backgroundColor = "#466CA6";
	arr[1].style.backgroundColor = "#7096FA";
	arr[1].style.color = "#FFFFFF";
	arr[2].style.backgroundColor = "#7096FA";
}

Menu.onMouseOut = function(table){
	var arr = table.rows[0].cells;
	arr[0].style.backgroundColor = "#D6DFF7";
	arr[1].style.backgroundColor = "#F7F8FD";
	arr[1].style.color = "";
	arr[2].style.backgroundColor = "#F7F8FD";
}

Page.onClick(Menu.close);

document.attachEvent("oncontextmenu",function(){
	//Menu.close();
});