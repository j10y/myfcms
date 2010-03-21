var Application = {};

Application.CurrentSite;

Application.isHMenu=false;

Application.onMainMenuClick = function(ele,flag){//flag仅在回退/前进时置为true
	if(Application.LastClickMainMenu){
		Application.LastClickMainMenu.className='';
	}
	Application.LastClickMainMenu = ele;
	ele.className = "liOver";
	var parent = $("_ChildMenu");
	var menu = $("_Child"+ele.id);
	if(!menu){
		var arr = ele.ChildArray;
		var str = [];
		for(var i=0;i<arr.length;i++){
			var id = "_ChildMenuItem_"+arr[i][0];
			str.push("<div id='"+id+"' class='divtab' onClick='Application.onChildMenuClick(this)' onMouseOver='Application.onChildMenuMouseOver(this)' onMouseOut='Application.onChildMenuMouseOut(this)' url='"+arr[i][2]+"'><img src='"+arr[i][3]+"' /><b><span>"+arr[i][1]+"</span></b></div>");
		}
		menu = document.createElement("div");
		menu.id = "_Child"+ele.id;
		menu.innerHTML = str.join("");
		parent.appendChild(menu);
	}
	var childs = parent.childNodes;
	for(var i=0;i<childs.length;i++){
		$E.hide(childs[i]);
	}
	$E.show(menu);
	Tab.setDivtabWidth(menu);
	if(!ele.CurrentItem){
		ele.CurrentItem = "_ChildMenuItem_"+arr[0][0];
	}
	Application.onChildMenuClick($(ele.CurrentItem),flag);
}

Application.onMainMenuMouseOver = function(ele){
	ele.className='liOver';
}

Application.onMainMenuMouseOut = function(ele){
	if(ele!=Application.LastClickMainMenu){
		ele.className='';
	}
}

var StartTime;
Application.onChildMenuClick = function(ele,flag){//flag仅在回退/前进时置为true
	StartTime = new Date().getTime();
	if(Application.LastClickMainMenu){
		Application.LastClickMainMenu.CurrentItem = ele.id;
	}
	//设置hash
	window.location.hash = ele.parentElement.id.substr("_Child_Menu_".length)+"_"+ele.id.substr("_ChildMenuItem_".length);
	if(!flag){
		var url = ele.getAttribute("url");
		$("_MainArea").src = url;
	}
	Tab.onTabClick(ele);
}

Application.onChildMenuMouseOver = function(ele){
	Tab.onTabMouseOver(ele);
}

Application.onChildMenuMouseOut = function(ele){
	Tab.onTabMouseOut(ele);
}

Page.onLoad(function(){
	if($("_Navigation")){
		if(window.location.hash){
			var arr = window.location.hash.split("_");
			var mainEle = $("_Menu_"+arr[0].substr(1));
			if (mainEle) {
				var childItem = null;
				for(var i=0;i<mainEle.ChildArray.length;i++){
					if(mainEle.ChildArray[i][0]==arr[1]){
						childItem = "_ChildMenuItem_"+arr[1];
						break;
					}
				}
				if (childItem) {
					mainEle.CurrentItem = childItem;
					Application.onMainMenuClick(mainEle);
				} else {
					mainEle.CurrentItem = "_ChildMenuItem_"+mainEle.ChildArray[0][0];
					Application.onMainMenuClick(mainEle);
				}
			} else {
				Application.onMainMenuClick($("_Navigation").$T("li")[0]);
			}
		}else{
			Application.onMainMenuClick($("_Navigation").$T("li")[0]);
		}
	}else if(window.frameElement&&window.frameElement.id=="_MainArea"){
		Page.mousedown();//使用浏览器回退按钮时可能有控件未关闭
		if(!_DialogInstance&&parent.Dialog._Array){//可能有对话框未关闭
			for(var i=0;i<parent.Dialog._Array.length;i++){
				parent.$("_DialogDiv_"+parent.Dialog._Array[i]).outerHTML = "";
				parent.$("_AlertBGDiv").hide();
				parent.$("_DialogBGDiv").hide();
			}
		}
		parent.Application.setCurrentMenu(window.location.href);
		//alert(new Date().getTime()-window.parent.StartTime);//用于测试页面加载速度
	}
	
	// 取当前站点的id
	var win = $E.getTopLevelWindow();
	if(!win.Priv){
		if(window.opener){
			win = window.opener.$E.getTopLevelWindow();
		}
	}
	if(win.Priv){
		Application.CurrentSite = win.$V("_SiteSelector");
	}
	Application.layoutAdjust();
})

Application.setCurrentMenu = function(url){
	if(url.indexOf("#")>0){
		url = url.substring(0,url.indexOf("#"));
	}
	if($("_Navigation")){
		var arr = $("_Navigation").$T("li");
		for(var i=0;i<arr.length;i++){
			var carr = arr[i].ChildArray;
			for(var j=0;j<carr.length;j++){
				if(url.indexOf(carr[j][2])>=0){
					Application.onMainMenuClick(arr[i],true);
					Application.onChildMenuClick($("_ChildMenuItem_"+carr[j][0]),true);
					return;
				}	
			}
		}
	}
}

Application.layoutAdjust = function(){
	if($("_Navigation")){
		if (document.body.clientWidth<900){
			if($("_VMenutable").innerHTML.length>40){
				$("_HMenutable").innerHTML=$("_VMenutable").innerHTML;
				$("_VMenutable").innerHTML="<div style='width:3px'></div>";
				Application.isHMenu=true;
			}
			Tab.setDivtabWidth($("_ChildMenu"));//方法定义在Tabpage.js 用于调整标签按钮宽度
		}else{
			if($("_HMenutable").innerHTML.length>0){
				$("_VMenutable").innerHTML=$("_HMenutable").innerHTML;
				$("_HMenutable").innerHTML="";
			}
				Application.isHMenu=false;
		}
		Tab.initFrameHeight("_MainArea"); 
	}
}

Application.SiteChange = [];

Application.onSiteChange = function(func){
	Application.SiteChange.push(func);
}

Application.onChildSiteChange = function(){
	for(var i=0;i<Application.SiteChange.length;i++){
		Application.SiteChange[i]();
	}
}

Application.onParentSiteChange = function(){
	if($("_Navigation")){
		var dc = new DataCollection();
		dc.add("SiteID",$V("_SiteSelector"));
		Cookie.set("DocList.LastCatalog","0","2100-01-01");
		Cookie.set("Resource.LastImageLib","0","2100-01-01");
  	Cookie.set("Resource.LastVideoLib","0","2100-01-01");
  	Cookie.set("Resource.LastAudioLib","0","2100-01-01");
  	Cookie.set("Resource.LastAttachLib","0","2100-01-01");
		Server.sendRequest("com.zving.platform.Application.changeSite",dc,function(){
			window.location.reload();
		});
	}
}

Application.setAllPriv = function(PrivType,id){
	if(!id){
		id = "0";//解决有根节点权限没有任何子节点权限时报错的问题
	}
	var buttons = $T("a");
	var win = $E.getTopLevelWindow();
	if(!win.Priv){
		if(window.opener){
			win = window.opener.$E.getTopLevelWindow();
		}
	}
	if(win.Priv){
		for(var i=0;i<buttons.length;i++){
			if(buttons[i].$A("priv")){
				win.Priv.setBtn(PrivType,id,buttons[i].$A("priv"),buttons[i]);
			}
		}
	}
}

Application.getPriv = function(privType,id,code){
	if(!id){
		id = "0";//解决有根节点权限没有任何子节点权限时报错的问题
	}
	var win = $E.getTopLevelWindow();
	if(!win.Priv){
		if(window.opener){
			win = window.opener.$E.getTopLevelWindow();
		}
	}
	if(win.Priv){
		return win.Priv.getPriv(privType,id,code);
	}
	return false;
}

Application.logout = function(){
	Dialog.confirm("你确认要退出系统吗？",function(){
	   Server.sendRequest("com.zving.platform.Application.logout");
	});	
}

Application.changePassword = function(){
	var d = new Dialog("ChangePassword");
	d.Widht = 450;
	d.Height = 150;
	d.Title = "修改密码";
	d.URL = "Platform/ChangePasswordDialog.jsp";
	d.OKEvent = function(){
		if($DW.Verify.hasError()){
			return;
		}
		var dc = $DW.Form.getData("FChangePassword");
		Server.sendRequest("com.zving.platform.Application.changePassword",dc,function(response){
			if(response.Status==1){
				Dialog.alert(response.Message);
				$D.close();
			}else{
				Dialog.alert(response.Message);
			}
		});
	}
	d.onLoad = function(){
		$DW.$("OldPassword").focus();	
	}
	d.show();
}


if(document.attachEvent){
	window.attachEvent('onresize',Application.layoutAdjust);
}else{
	window.addEventListener('resize',Application.layoutAdjust,false);
}
