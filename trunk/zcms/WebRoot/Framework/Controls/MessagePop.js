function MsgPop(strMsg,strUrl,intH,intW,intTime){
		var pw = $E.getTopLevelWindow();
		var strID="Pop"+(pw.MsgPop?pw.MsgPop._Array.length:"");
		this.ID = strID;
		this.Width = intW?intW:320;
		this.Height = intH?intH:200;
		this.Bottom = 0;
		this.Right = 0;
		this.ParentWindow = null;
		this.onLoad = null;
		this.Window = null;
		
		this.Title = "";
		this.URL = strUrl?strUrl:null;
		this.Message = strMsg?strMsg:null;
		this.MessageTitle = null;
		this.Icon = null;
		this.Timeout=intTime?intTime:null;
}

MsgPop._Array = [];

MsgPop.prototype.show = function(){
	var pw = $E.getTopLevelWindow();
	var doc = pw.document;
	if ( !this.ParentWindow ){
	 	this.ParentWindow = window ;
	}
	var bgColorRGB= (parseInt(Math.random()*15)+230)+", "+(parseInt(Math.random()*10)+240)+", "+(parseInt(Math.random()*5)+250);
	var bdColorRGB= (parseInt(Math.random()*20)+170)+", "+(parseInt(Math.random()*20)+190)+", "+(parseInt(Math.random()*15)+210);
	var arr = [];
	arr.push('<div id="'+this.ID+'Body" style="width:'+this.Width+'px; background-color:rgb('+bgColorRGB+'); border:1px solid rgb('+bdColorRGB+'); border-bottom-width:1px; padding:4px; margin-top:0px;">');
	arr.push('<div><a style="font-size:16px; float:right; line-height:16px; text-decoration:none; margin:-3px 0 0; " href="javascript:void(1);" onClick="MsgPop.close(\''+this.ID+'\');">×</a><span style="float:right" id="'+this.ID+'CloseTip"></span></div>');
	if(this.URL != null){
		if(this.URL.indexOf(":")==-1){
			arr.push('<iframe src="'+Server.ContextPath+this.URL+'" ');
		}else{
			arr.push('<iframe src="'+this.URL+'" ');
		}
		arr.push(' id="_MsgPopFrame_'+this.ID+'" boxId="'+this.ID+'" allowTransparency="true"  width="100%" height="'+this.Height+'" frameborder="0" style="background-color: #transparent; border:1px solid #e0e9f1;border-color:#a8bdcf;"></iframe>');
	}else{
		arr.push(this.Message);
	}
	arr.push('');
	arr.push('</div>');
	
	this.TopWindow = pw;
	
	var boxdiv = pw.$("_MsgPopBoxDiv");
	if(!boxdiv){
		boxdiv = pw.document.createElement("div");	
		boxdiv.id = "_MsgPopBoxDiv";
		boxdiv.style.cssText="position:fixed; right:0; bottom:0; width:500px; overflow:visible;";
		if(isIE6){boxdiv.style.position="absolute"};
		//$E.hide(boxdiv);
	 	pw.$T("body")[0].appendChild(boxdiv);
	}

	this.MsgPopBoxDiv = boxdiv;
	var MsgPopDiv=pw.document.createElement("div");
	MsgPopDiv.id=this.ID;
	MsgPopDiv.innerHTML = arr.join('\n');
	MsgPopDiv.style.cssText="float:right;clear:right;visibility:hidden";
	boxdiv.appendChild(MsgPopDiv);
	pw.MsgPop._Array.push(this.ID);

	/*var endSize=$E.getDimensions(MsgPopDiv);
	MsgPopDiv.style.height="1px";
	Effect.size(MsgPopDiv,endSize,15);*/

	var endHeight={height:$E.getDimensions(MsgPopDiv).height};
	MsgPopDiv.style.height="1px";
	Effect.size(MsgPopDiv,endHeight,40);
	
	MsgPopDiv.style.visibility="";
	if(this.Timeout!=null){
		MsgPop.autoClose(this.ID,this.Timeout);
	}
	
}

MsgPop.autoClose = function(id,time){
	var pw = $E.getTopLevelWindow();
	if($(id)==null){
		return;
	}
	time-=1;
	pw.$(id+"CloseTip").innerHTML=time+"秒后关闭";
	if(time<=0){
		MsgPop.close(id);
	}
	else{
		MsgPop._closeHand = setTimeout(function(){	MsgPop.autoClose(id,time);},1000);
	}
}

MsgPop.prototype.close = function(){
	var pw = $E.getTopLevelWindow();
	this.MsgPopBoxDiv.outerHTML = "";
	pw.MsgPop._Array.remove(this.ID);
}

MsgPop.close = function(id){
	var pw = $E.getTopLevelWindow();
	Effect.fade(pw.$(id),100,0,10,function(){pw.$(id).outerHTML = "";pw.MsgPop._Array.remove(id);});
}
MsgPop.closeSelf = function(){//在MsgPop内的iframe里可以调用MsgPop.closeSelf方法来关闭自己所有的MsgPop
	if(window!=window.parent){
		var boxId = window.frameElement.getAttribute("boxId");
		if(boxId!=null&&boxId!=""){
			MsgPop.close(boxId);
		}
	}
}