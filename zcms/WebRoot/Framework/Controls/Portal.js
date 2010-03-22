var Portal = {};
Portal.gridClassName = "portal-dashedBox";
Portal.dropGird =null;
Portal.dragFeedObj =null;

Portal.dragStart = function(evt){
	var thisObjArr=this.feedObj.parentObj.feeds;
	Portal.dragFeedObj=thisObjArr.remove(this.feedObj,1)[0];//从portalDate里删除正在被拖动的portalet对应的feed
	//console.log("获得Portal.dragFeedObj" + Portal.dragFeedObj);
	DragManager.doDrag(evt);
	Portal.getDropGird(this);
	this.style.filter = "alpha(opacity=70)";
	this.style.opacity = 0.7;
}

Portal.dragEnd = function(evt){
	if(Portal.dragFeedObj==null)return;
	if(this.nextSibling!=Portal.dropGird){
		Portal.dropGird.parentNode.insertBefore(this, Portal.dropGird);//把正在拖拽的portalet插入到虚线框的前面
	}
	var targetObj=Portal.dropGird.parentEle.feedObj,
	targetObjArr=targetObj.feeds,
	targetIndex=Portal.dropGird.feedIndex;
	//sourceObjArr=this.feedObj.parentObj.feeds;
	if(targetObjArr&&targetObjArr[targetIndex]!=this.feedObj){
		targetObjArr.insert(targetIndex,Portal.dragFeedObj);
		this.feedObj.parentObj=targetObj;
	}
	Portal.dragFeedObj =null;
	Portal.dropGird.style.display="none";
	this.style.position="";
	this.style.width="";
	this.style.filter = "";
	this.style.opacity = "";
	Portal.getDropRegion();
	//console.log(portalData);
}

Portal.portalData2JSON=function(){//返回portalData为JSON格式的字符串
	if(!portalData){alert("无法获得配置信息（portalData）");return;}
	tmpData=portalData.clone();
	for(var i=0;i<tmpData.length;i++){
		delete tmpData[i].element;
		//console.log(tmpData[i].element);
		for(var j=0;j<tmpData[i].feeds.length;j++){
			delete tmpData[i].feeds[j].parentObj;
			delete tmpData[i].feeds[j].element;
		}
	}
	//console.log(JSON.toString(tmpData));
	//return JSON.toString(tmpData);
}

Portal.getDropGird=function(ele){
	var ele = ele||this;
	if(!Portal.dropGird){
		var box = document.createElement('div');
		box.className=Portal.gridClassName;
		Portal.dropGird=box;
	};
	Portal.dropGird.style.display="";
	Portal.dropGird.style.height=$E.getDimensions(ele).height-4+"px";
	ele.parentNode.insertBefore(Portal.dropGird,ele.nextSibling);
	
	var elefeedObj=ele.feedObj,
	elefeedParentObj=elefeedObj.parentObj;
	Portal.dropGird.PosAndDim=Portal.getPosAndDim(Portal.dropGird);
	Portal.dropGird.parentEle=elefeedParentObj.element;
	Portal.dropGird.feedIndex=elefeedParentObj.feeds.indexOf(elefeedObj);
	return Portal.dropGird;
}

Portal.getPosAndDim=function(ele){
	var pos=$E.getPosition(ele);
	var dim=$E.getDimensions(ele);
	return {x:parseInt(pos.x),y:parseInt(pos.y),w:dim.width,h:dim.height}
}

Portal.isInner=function(curPos,elePosAndDim){
	return (elePosAndDim.x<curPos.x && elePosAndDim.x+elePosAndDim.w>curPos.x && elePosAndDim.y<curPos.y && elePosAndDim.y+elePosAndDim.h>curPos.y)?true:false;
}

Portal.draging = function(evt){
	var curPos = getEventPositionLocal(evt);
	for(var i=0;i<portalData.length;i++){
		if(Portal.isInner(curPos,portalData[i].element.PosAndDim)){//光标在某一列内
		//console.log("当前列内portalet个数为"+portalData[i].feeds.length)
			if(portalData[i].feeds.length<1){//如果这一列里没有portalet
				portalData[i].element.appendChild(Portal.dropGird);
				Portal.dropGird.parentEle=portalData[i].element;
				Portal.dropGird.feedIndex=0;
				Portal.getDropRegion();	
				return;
			}
			if(Portal.isInner(curPos,Portal.dropGird.PosAndDim))return;//如果光标所在位置为虚线框所在的位置
			var feed=null,where=null;//遍历判断光标所在座标对应的 feed对象、对应的portalet（网页元素），网页元素的上半部或下半部份
			for(var j=0;j<portalData[i].feeds.length;j++){
				//if(portalData[i].feeds[j].element==this) continue;//正在拖拽的portalet不再用来检测
				if(Portal.isInner(curPos,portalData[i].feeds[j].element.PosAndDim)){//如果正在拖拽的portalet在某个feed所标记的坐标内
					feed=portalData[i].feeds[j];
					if(Portal.dropGird.nextSibling==feed.element){
						//console.log("这个元素在虚线框的下面");
						where="after";
					}else{
						where=(curPos.y<(feed.element.PosAndDim.y+feed.element.PosAndDim.h/2))?"before":"after";
					}
					Portal.dropGird.parentEle=portalData[i].element;
					Portal.dropGird.feedIndex= where=="after"?j+1:j;
					break;
				}
			}
			if(!feed){//在列内，却不在列内的某个portalet上
				var curColFeeds=portalData[i].feeds;
				var dostIndex=curColFeeds.length-1;
				feed=curColFeeds[dostIndex];//获取该列的最后一个feed
				if(feed.element==this){dostIndex--;feed=curColFeeds[dostIndex];}//正在拖拽的portalet不再用来检测
				if(!feed)return;
				if(curPos.y>feed.element.PosAndDim.y+feed.element.PosAndDim.h){//如果被拖动的portalet在某一列内，并在这一列的portalet下面的空白区域
					if(this.feedObj.parentObj==portalData[i]&&this.nextSibling==Portal.dropGird)return;//如果被拖动的portalet在是本列的最后一个并且虚线框已经出现则不进行操作
					//console.log("正拖拽到列"+portalData[i].id+"的底部");
					where="after";
					Portal.dropGird.parentEle=portalData[i].element;
					Portal.dropGird.feedIndex=dostIndex+1;
				}else{
					return;
				}
			}
			//console.log("Portal.dropGird.feedIndex: "+Portal.dropGird.feedIndex);
			Portal.notifyOver(feed.element,where);
			break;
		}
	}
}

Portal.notifyOver=function(ele,where){//当移到一个portalet上时，更改虚线框的位置
	switch(where){
		case "before":
			ele.parentNode.insertBefore(Portal.dropGird,ele);
			break;
		case "after":
			ele.nextSibling?ele.parentNode.insertBefore(Portal.dropGird,ele.nextSibling):ele.parentNode.appendChild(Portal.dropGird);
			break;
	}
	Portal.getDropRegion();	
}

Portal.Init = function(){//把页面上的portalet与portalData相关联
	if(!portalData){alert("无法获得配置信息（portalData）");return;}
	for(var i=0;i<portalData.length;i++){
		var colEle=$(portalData[i].id);
		if(!colEle){alert("当前页面找不到配置信息中所提供的id为 "+portalData[i].id+" 的页面元素");return;}
		colEle.feedObj=portalData[i];
		colEle.PosAndDim=Portal.getPosAndDim(colEle);
		portalData[i].element=colEle;
		//console.log(portalData[i].element);
		for(var j=0;j<portalData[i].feeds.length;j++){
			portalData[i].feeds[j].parentObj=portalData[i];
			var modEle=$(portalData[i].feeds[j].id);
			if(!modEle){alert("当前页面找不到配置信息中所提供的 #"+portalData[i].id+" 下id为 "+portalData[i].feeds[j].id+" 的页面元素");return;}
			modEle.feedObj=portalData[i].feeds[j];			
			modEle.PosAndDim=Portal.getPosAndDim(modEle);
			portalData[i].feeds[j].element=modEle;
			//console.log(portalData[i].feeds[j].element);
		}
	}

	if(isIE6){
		var arrLi=$T("li");
		for(var i=0,len=arrLi.length;i<len;i++){
			if(arrLi[i].className=="portal-mnu"){//ie6下对“设置”按钮添加下拉菜单功能
				arrLi[i].onmouseenter=function() {
					this.style.position="relative";
					this.childNodes[1].style.display = "block";
				};
				arrLi[i].onmouseleave=function() {
					this.style.position="static";
					this.childNodes[1].style.display = "none";
				};
			}
		}
	}
}

Portal.getDropRegion = function(){//把所有portalet的坐标存入portalData相应的对象里。
	if(!portalData){alert("无法获得配置信息（portalData）");return;}
	for(var i=0,plen=portalData.length;i<plen;i++){
		if(!portalData[i])alert("ERROR: portalData["+i+"] " + portalData[i]);
		var colEle=portalData[i].element;
		colEle.PosAndDim=Portal.getPosAndDim(colEle);
		for(var j=0,flen=portalData[i].feeds.length;j<flen;j++){
			if(!portalData[i].feeds[j])alert("ERROR: portalData["+i+"].feeds["+j+"] " + portalData[i].feeds[j]);
			var modEle=portalData[i].feeds[j].element;
			modEle.PosAndDim=Portal.getPosAndDim(modEle);
		}
	}
	if(Portal.dropGird)	Portal.dropGird.PosAndDim=Portal.getPosAndDim(Portal.dropGird);
}

Portal.removePortalet=function(id){
	var ele=$(id);
	var feedobj=ele.feedObj;
	feedobj.parentObj.feeds.remove(feedobj);
	ele.parentNode.removeChild(ele);
	Portal.getDropRegion();	
}

Portal.togglePortalet=function(ele,id){
	if(ele.className=="portal-exp"){
		ele.className="portal-col";
		ele.title="展开";
		$(id).addClassName("portal-hiddenBody");
	}else{
		ele.className="portal-exp";
		ele.title="折叠";
		$(id).removeClassName("portal-hiddenBody");
	}
}
Portal.addPortlet=function(feedObj){
	var id=feedObj.id,widgetType=feedObj.widgetType,contentUrl=feedObj.contentUrl,title=feedObj.title;
	if(id==undefined||widgetType==undefined){Dialog.alert("错误的参数"); return;}
	if($(id)){Dialog.alert("已经存在名为"+title+"的模块了!");return;}
	
	var dc = new DataCollection();
	dc.add("Code",id);
	Server.sendRequest("com.zving.portal.Portal.getPortletHtml",dc,function(response){
			var newMod=document.createElement("div");
			newMod.style.display="none";
			portalData[0].element.insertBefore(newMod,portalData[0].element.firstChild);
			newMod.outerHTML=response.get("Html");
			
			portalData[0].feeds.insert(0,feedObj);
			portalData[0].feeds[0].parentObj=portalData[0];
			var modEle=$(portalData[0].feeds[0].id);
			if(!modEle){alert("当前页面找不到配置信息中所提供的 #"+portalData[0].id+" 下id为 "+portalData[0].feeds[0].id+" 的页面元素");return;}
			modEle.feedObj=portalData[0].feeds[0];
			modEle.PosAndDim=Portal.getPosAndDim(modEle);
			portalData[0].feeds[0].element=modEle;
			Portal.getDropRegion();
	});
	
}

Page.onLoad(function(){
	Portal.Init();
},1);
