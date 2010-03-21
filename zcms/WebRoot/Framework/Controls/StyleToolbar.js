/*
用于给某一文本框设置简单样式，如字体颜色，粗体，斜体，字体，字号
*/
var STYLETOOLBAR_ALL_TYPE = "FontFamily,FontSize,FontColor,Bold,Italic,UnderLine".split(",");
var STYLETOOLBAR_ALL_ATTR = "fontFamily,fontSize,color,fontWeight,fontStyle,textDecoration".split(",");
var STYLETOOLBAR_ALL_CSSATTR = "font-family,font-size,color,font-weight,font-style,text-decoration".split(",");

var StyleToolbar = function(id,targetEle,option){//默认option为全部
	this.ID = id;
	this.Target = $(targetEle);
	if(!option){
		this.Option = STYLETOOLBAR_ALL_TYPE;
	}else{
		this.Option = option.split(",");	
	}
};

StyleToolbar.prototype.show = function(){
	this.Html = [];
	this.Html.push("<div style='display:inline-block;display:-moz-inline-box; -moz-box-align:stretch;*zoom: 1;*display: inline;vertical-align:middle; overflow:hidden;'><input type='hidden' id='"+this.ID+"'/>");
	this.Html.push("<table id='"+this.ID+"_Table' cellspacing='0' cellpadding='0'><tr>");
	for(var i=0;i<this.Option.length;i++){
		this["add"+this.Option[i]]();
	}
	this.Html.push("</tr></table></div>");
	this.Target.insertAdjacentHTML("afterEnd",this.Html.join(''));
	Effect.initChildren(this.ID+"_Table");
	if($(this.ID+"_FontFamily")){
		Selector.initCtrl(this.ID+"_FontFamily");
	}	
	if($(this.ID+"_FontSize")){
		Selector.initCtrl(this.ID+"_FontSize");
	}
	$(this.ID).Instance = this;
}

StyleToolbar.prototype.addFontFamily = function(){
	this.Html.push("<td><div title='字体' ztype='Select' style='width:80px;display:none' id='"+this.ID+"_FontFamily' onchange=\"StyleToolbar.onChange('"+this.ID+"')\">");
	var fonts = "宋体;仿宋_GB2312;新宋体;隶书;楷体_GB2312;华文中宋;幼圆;黑体;Arial;Comic Sans MS;Courier New;Tahoma;Times New Roman;Verdana";
	var arr = [];
	fonts.split(";").each(function(str){
		arr.push("<span value='"+str+"'>"+str+"</span>");
	});
	this.Html.push(arr.join('\n')+"</div></td>");
}

StyleToolbar.prototype.addFontSize = function(){
	this.Html.push("<td><div title='字号' ztype='Select' style='width:60px;display:none' id='"+this.ID+"_FontSize' onchange=\"StyleToolbar.onChange('"+this.ID+"')\">");
	var fonts = "9,10,12,14,16,18,24,36";
	var arr = [];
	fonts.split(",").each(function(str){
		arr.push("<span value='"+str+"px' "+(str=="12"?"selected='true'":"")+">"+str+"</span>");
	});
	this.Html.push(arr.join('\n')+"</div></td>");
}

StyleToolbar.prototype.addFontColor = function(){
	this.Html.push("<td><input type='hidden' name='TextColor' id='"+this.ID+"_FontColor'/><div title='字体颜色' id='"+this.ID+"_FontColor_Div'");
	this.Html.push(" onclick='StyleToolbar.onClick(this);StyleToolbar.showColorSelector(this)' onmouseover='StyleToolbar.onMouseOver(this)' onmouseout='StyleToolbar.onMouseOut(this)'>");
	this.Html.push("<table cellspacing='0' cellpadding='0'><tbody><tr>");
	this.Html.push("<td><img src='"+Server.ContextPath+"Editor/editor/images/spacer.gif' style='background-position: 0px -704px; background-image: url("+Server.ContextPath+"Editor/editor/skins/office2003/fck_strip.gif);overflow: hidden;width: 16px;height: 16px;margin: 1px;'></td>");
	this.Html.push("<td><img height='3' width='5' src='"+Server.ContextPath+"Editor/editor/skins/office2003/images/toolbar.buttonarrow.gif'></td>");
	this.Html.push("<td width='6px'></td></tr></tbody></table>");
	this.Html.push("</div></td>");
}

StyleToolbar.prototype.addBold = function(){
	this.Html.push("<td><input type='hidden' id='"+this.ID+"_Bold'/><div title='加粗'");
	this.Html.push(" onclick=\"StyleToolbar.onClick(this);StyleToolbar.setBold('"+this.ID+"')\" onmouseover='StyleToolbar.onMouseOver(this)' onmouseout='StyleToolbar.onMouseOut(this)'>");
	this.Html.push("<img src='"+Server.ContextPath+"Editor/editor/images/spacer.gif' style='background-position: 0px -304px; background-image: url("+Server.ContextPath+"Editor/editor/skins/office2003/fck_strip.gif);overflow: hidden;width: 16px;height: 16px;margin: 1px;'>");
	this.Html.push("</div></td>");
}

StyleToolbar.prototype.addItalic = function(){
	this.Html.push("<td><input type='hidden' id='"+this.ID+"_Italic'/><div title='斜体'");
	this.Html.push(" onclick=\"StyleToolbar.onClick(this);StyleToolbar.setItalic('"+this.ID+"')\" onmouseover='StyleToolbar.onMouseOver(this)' onmouseout='StyleToolbar.onMouseOut(this)'>");
	this.Html.push("<img src='"+Server.ContextPath+"Editor/editor/images/spacer.gif' style='background-position: 0px -320px; background-image: url("+Server.ContextPath+"Editor/editor/skins/office2003/fck_strip.gif);overflow: hidden;width: 16px;height: 16px;margin: 1px;'>");
	this.Html.push("</div></td>");
}

StyleToolbar.prototype.addUnderLine = function(){
	this.Html.push("<td><input type='hidden' id='"+this.ID+"_UnderLine'/><div title='加下划线'");
	this.Html.push(" onclick=\"StyleToolbar.onClick(this);StyleToolbar.setUnderLine('"+this.ID+"')\" onmouseover='StyleToolbar.onMouseOver(this)' onmouseout='StyleToolbar.onMouseOut(this)'>");
	this.Html.push("<img src='"+Server.ContextPath+"Editor/editor/images/spacer.gif' style='background-position: 0px -336px; background-image: url("+Server.ContextPath+"Editor/editor/skins/office2003/fck_strip.gif);overflow: hidden;width: 16px;height: 16px;margin: 1px;'>");
	this.Html.push("</div></td>");
}

StyleToolbar.onClick = function(div){
	if(div.isClicked){
		div.style.border = 'none';
		div.style.background = 'none';
		div.isClicked = false;
	}else{
		div.style.border = "#316ac5 1px solid";
		div.style.backgroundColor = "#c1d2ee";
		div.isClicked = true;
	}
}

StyleToolbar.onMouseOver = function(div){
	div.style.border = "#316ac5 1px solid";
	div.style.backgroundColor = "#dff1ff";
}

StyleToolbar.onMouseOut = function(div){
	if(div.isClicked){
		div.style.border = "#316ac5 1px solid";
		div.style.backgroundColor = "#c1d2ee";
	}else{
		div.style.border = 'none';
		div.style.background =  "";
	}
}

StyleToolbar.onChange = function(id){
	var st = $(id).Instance;
	var arr = [];
	var t = st.Target;	
	var dim = t.getDimensions();
	t.hide();
	st.Option.each(function(str,i){
		var ele = $(id+"_"+str);
		if(ele){
			var v = $V(ele);			
			for(var j=i;j<STYLETOOLBAR_ALL_TYPE.length;j++){
				if(STYLETOOLBAR_ALL_TYPE[j]==str){
					var name = STYLETOOLBAR_ALL_ATTR[j];
					if(v){
						if(!t.DefaultStyle){
							t.DefaultStyle = {};
						}
						if(!t.DefaultStyle[name]){
							t.DefaultStyle[name] = t.currentStyle[name];
						}
						t.style[name] = v;
						arr.push(STYLETOOLBAR_ALL_CSSATTR[j]);
						arr.push(":");
						arr.push(v+";");
					}else{
						if(t.DefaultStyle&&t.DefaultStyle[name]){
							t.style[name] = t.DefaultStyle[name];
						}
					}
					break;
				}
			}
		}
	});
	var strStyle = arr.join('');
	$S(id,strStyle);
	//t.style.height = dim.height;
	//t.style.width = dim.width;
	t.show();
}

StyleToolbar.showColorSelector = function(div){
	if(!div.isClicked){
		return;
	}
	var id = div.id;
	id = id.substring(0,id.lastIndexOf('_'));
	var cs = new ColorSelector(id,div);
	cs.AfterSelect = function(targetID,color){
		$S(targetID,color);
		StyleToolbar.onChange(targetID.substring(0,targetID.indexOf('_')));		
	}
	cs.show();
}

StyleToolbar.setBold = function(id){
	var v = $V(id+"_Bold");
	if(v=="bold"){
		v = "normal";
	}else{
		v = "bold";	
	}
	$S(id+"_Bold",v);
	StyleToolbar.onChange(id);
}

StyleToolbar.setItalic = function(id){
	var v = $V(id+"_Italic");
	if(v=="italic"){
		v = "normal";
	}else{
		v = "italic";	
	}
	$S(id+"_Italic",v);
	StyleToolbar.onChange(id);
}

StyleToolbar.setUnderLine = function(id){
	var v = $V(id+"_UnderLine");
	if(v=="underline"){
		v = "none";
	}else{
		v = "underline";	
	}
	$S(id+"_UnderLine",v);
	StyleToolbar.onChange(id);
}

function ColorSelector(id,div){
	this.ID = id;
	this.Div = div;
}

ColorSelector.prototype.show = function(){
	var pw = $E.getTopLevelWindow();
	var win;
	if(!pw.$("_ColorSelector")){
		var div = pw.document.createElement("DIV");
		div.id = "_ColorSelector";
		div.style.cssText = "background-color:#fff;left:200;top:200;position:absolute;z-index:999;overflow: auto;white-space: nowrap;cursor: default;border: 1px solid #8f8f73;";
		div.innerHTML = "<iframe id='_ColorSelector_Frame' frameborder=0 scrolling=no width=152 height=114></iframe>";
		pw.document.body.appendChild(div);		
		win = pw.$("_ColorSelector_Frame").contentWindow;
		var doc = win.document;
		doc.open();
		var html = [];
		html.push("<style>");
		html.push("body{margin: 0px;padding-left: 2px;padding-right: 2px;padding-top: 2px;}");
		html.push("td{font-size:12px;}");
		html.push(".ColorBoxBorder{border: #808080 1px solid;position: static;}");
		html.push(".ColorBox{font-size: 1px;width: 10px;position: static;height: 10px;}");
		html.push(".ColorDeselected, .ColorSelected{cursor: default;}");
		html.push(".ColorDeselected{border: #ffffff 1px solid;padding: 2px;float: left;}");
		html.push(".ColorSelected{border: #330066 1px solid;padding: 2px;float: left; background-color: #c4cdd6;}");
		html.push("</style>");
		html.push("<body><table id='_TableColors' class='ForceBaseFont' style='tableLayout:fixed;' cellpadding='0' cellspacing='0' border='0' width='150'>");
		html.push('<tr><td colspan=8>');
		html.push("<div class='ColorDeselected' onclick=\"TopWindow.ColorSelector.getInstance('"+this.ID+"').setColor('');\" onmouseover=\"this.className='ColorSelected'\" style='width:95%' onmouseout=\"this.className='ColorDeselected'\">");
		html.push('<table cellspacing="0" cellpadding="0" width="100%" border="0"><tr>');
		html.push('<td><div class="ColorBox" style="background-color: #000000"></div></div></td>');
		html.push('<td nowrap width="100%" align="center">自动</td>');
		html.push('</tr></table>');
		html.push("</div></td></tr>");		

		var FontColors = '000000,993300,333300,003300,003366,000080,333399,333333,800000,FF6600,808000,808080,008080,0000FF,666699,808080,FF0000,FF9900,99CC00,339966,33CCCC,3366FF,800080,999999,FF00FF,FFCC00,FFFF00,00FF00,00FFFF,00CCFF,993366,C0C0C0,FF99CC,FFCC99,FFFF99,CCFFCC,CCFFFF,99CCFF,CC99FF,FFFFFF' ;
		var aColors = FontColors.toString().split(',');
		var iCounter = 0 ;
		while(iCounter < aColors.length){
			html.push("<tr>");
			for(var i=0;i<8;i++,iCounter++){
				if(iCounter<aColors.length){
					var colorParts = aColors[iCounter].split('/');
					var colorValue = '#' + colorParts[0];
					var colorName = colorParts[1] || colorValue;
				}
				html.push("<td><div class='ColorDeselected' onclick=\"TopWindow.ColorSelector.getInstance('"+this.ID+"').setColor('"+colorValue+"');\" onmouseover=\"this.className='ColorSelected'\" onmouseout=\"this.className='ColorDeselected'\">");
				html.push('<div class="ColorBoxBorder"><div class="ColorBox" style="background-color: ' + colorValue + '"></div></div>');
				html.push("</div></td>");
			}
			html.push("</tr>");
		}
		html.push("</table></table></body>");
		doc.write(html.join(''));
		doc.close();
	}else{
		win = pw.$("_ColorSelector_Frame").contentWindow;	
	}
	win.TopWindow = window;
	win.Instace = this;
	var csDiv = pw.$("_ColorSelector");
	var pos = $E.getPositionEx(this.Div);
	var dim = $E.getDimensions(this.Div);
	csDiv.style.top = (pos.y+dim.height)+"px";
	csDiv.style.left = pos.x+"px";	
	csDiv.show();
	pw.SourceWindow = window;
	Misc.lockScroll(window);
}

ColorSelector.prototype.setColor = function(color){
	if(this.AfterSelect){
		this.AfterSelect(this.ID,color);
	}
	StyleToolbar.onClick(this.Div);
	ColorSelector.close();
}

ColorSelector.getInstance = function(id){
	var pw = $E.getTopLevelWindow();
	var win = pw.$("_ColorSelector_Frame").contentWindow;
	return win.Instace;
}

ColorSelector.close = function(){
	var pw = $E.getTopLevelWindow();
	if(pw.$&&pw.$("_ColorSelector")&&pw.$("_ColorSelector").visible()){
		pw.$("_ColorSelector").hide();
		Misc.unlockScroll(pw.SourceWindow);
		pw.SourceWindow = null;
	}
}

Page.onMouseDown(ColorSelector.close);