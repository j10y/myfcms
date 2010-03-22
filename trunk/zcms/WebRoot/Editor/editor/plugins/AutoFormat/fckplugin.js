/*
Copyright by Zving Softwarey Ltd.
Author : wyuch@zving.com
*/
var AutoFormat= function() {this.Name = 'AutoFormat';}
AutoFormat.prototype.Execute = function () {AutoFormat.format();}
AutoFormat.prototype.GetState = function() {return FCK_TRISTATE_OFF;}
FCKCommands.RegisterCommand( 'AutoFormat',new AutoFormat()) ;
var AutoFormatItem = new FCKToolbarButton( 'AutoFormat', "自动排版" ) ;
AutoFormatItem.IconPath = FCKPlugins.Items['AutoFormat'].Path + 'AutoFormat.gif' ;
FCKToolbarItems.RegisterItem( 'AutoFormat', AutoFormatItem ) ;

AutoFormat.format = function(){
	FCKUndo.SaveUndoStep();
	var str = FCK.GetXHTML(false);	
	if(!str){
		return;//FCK.GetXHTML可能会返回空
	}
	str = AutoFormat.protectTable(str);
	var regex = /\<\s*br[\s\/]*?\>|\<\s*p .*?\>|\<[\s\/]*p[\s\/]*\>/gi;
	var pRegex = /\<[\s\/]*p .*?\>/i;
	var tagArr = [];
	var arr = [];
	if(!FCKBrowserInfo.IsIE){
		arr = str.split(regex);//注意,IE和FF对String.split(regex)的解释是不一样的,FF下两个相邻的匹配项之间会拆分出一个空字符串,而IE不会
	}
	var matcher;
	var lastIndex = 0;
	while ((matcher = regex.exec(str)) != null){		
		tagArr.push(matcher[0]);
		if(FCKBrowserInfo.IsIE){
			if(matcher.index>=lastIndex){
				arr.push(str.substring(lastIndex,matcher.index));
			}
			lastIndex = matcher.lastIndex;
		}
	}
	if(FCKBrowserInfo.IsIE){
		arr.push(str.substring(lastIndex));
	}
	var r = [];
	for(var i=0;i<arr.length;i++){
		var t = AutoFormat.dealOne(arr[i]);
		if(t.length>0){
			if(t!=AutoFormat.TablePlaceHolder){
				if(i!=0&&i<=tagArr.length&&pRegex.test(tagArr[i-1])){
					r.push(tagArr[i-1]);
				}else{
					r.push("<p>");
				}
				r.push(t);
				r.push("</p>\n");
			}else{
				r.push(t);
			}
		}
	}
	str = r.join('');
	str = AutoFormat.restoreTable(str);

	FCK.SetHTML(str); 	
	FCKUndo.SaveUndoStep() ;
}

AutoFormat.TablePlaceHolder = "<!--AUTOFORMAT_ZVING_TABLE_PALCEHOLDER-->";

AutoFormat.protectTable = function(str){
	AutoFormat.TableArr = [];
	var regex = /\<\s*table\s.*?\<\s*\/\s*table\s*\>/gi;
	while ((matcher = regex.exec(str)) != null){
		AutoFormat.TableArr.push(matcher[0]);
	}
	regex.lastIndex = -1;
	str = str.replace(regex,AutoFormat.TablePlaceHolder);
	return str;
}

AutoFormat.restoreTable = function(str){
	var index = 0;
	while(str.indexOf(AutoFormat.TablePlaceHolder)>=0){
		str = str.replace(AutoFormat.TablePlaceHolder,AutoFormat.TableArr[index++]);
	}
	return str;
}

AutoFormat.dealOne = function(str){
	var src = str;
	str = AutoFormat.trim(str);
	str = AutoFormat.trim(str);
	var prefix = "";
	while(/^\<\s*(font|b|strong)[\s\/\>]/gi.test(str)){//文本修饰
		var index = str.indexOf(">")+1;
		prefix = prefix+str.substring(0,index);
		str = AutoFormat.trim(str.substring(index));
	}
	if(str.charAt(0)=="<"){//里面可能是img,table之类的标签
		return src;
	}
	if(str.length==0){
		return "";
	}
	return prefix+"　　"+str;
}

AutoFormat.trim = function(str){
	str = str.replace(/(^\s*)|(\s*$)/g,"");
	var len = str.length;
	while(str.charAt(0)=='　'){
		str = str.substring(1);
	}
	str = str.replace(/^(\&nbsp\;\s*)+/,"");
	return str;
}