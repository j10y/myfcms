var DataList = {};//循环内容自定义的可动态刷新的可分页的列表控件

DataList.init = function(ele){
	ele = $(ele);
	DataList.setParam(ele,Constant.ID, ele.id);
	DataList.setParam(ele,Constant.Method, ele.getAttribute("method"));
	DataList.setParam(ele,Constant.TagBody, ele.TagBody);	
}

DataList.getParam = function(ele,name){
	ele = $(ele);
	return ele.Params.get(name);
}

DataList.setParam = function(ele,name,value){
	ele = $(ele);
	if(!ele.Params){
		ele.Params = new DataCollection();
	}
	ele.Params.add(name,value);
}

DataList.loadData = function(ele,func){
	ele = $(ele);
	var id  = ele.id;
	Server.sendRequest("com.zving.framework.controls.DataListPage.doWork",ele.Params,function(response){
		var html = response.get("HTML");
		var p = ele.parentNode;
		while(p){
			if(p.tagName&&p.tagName!="TR"&&p.tagName!="TABLE"&&p.tagName!="TBODY"){
				var phtml = p.innerHTML;
				var i1 = phtml.indexOf("<!--_ZVING_DATALIST_START_" + id + "-->");
				var i2 = phtml.indexOf("<!--_ZVING_DATALIST_END_" + id + "-->");
				i2 = phtml.indexOf("-->",i2)+3;
				phtml = phtml.substring(0,i1)+html+phtml.substring(i2);
				p.innerHTML = phtml;
				break;
			}
			p = p.parentNode;
		}
		ele = $(id);		
		p = ele.parentNode;
		while(p){
			if(p.tagName){
				break;
			}
			p = p.parentNode;
		}
		eval(p.getElementsByTagName("script")[0].text);
		ele = null;
		p = null;
		if(func){
			func();
		}
	});
}
DataList.firstPage = function(ele){
	ele = $(ele);
	DataList.setParam(ele,Constant.PageIndex,0);
	DataList.loadData(ele);
}

DataList.lastPage = function(ele){
	ele = $(ele);
	var total = DataList.getParam(ele,Constant.PageTotal);
	var size = DataList.getParam(ele,Constant.Size);
	var max = Math.ceil(parseInt(total)*1.0/parseInt(size));
	DataList.setParam(ele,Constant.PageIndex,max-1);
	DataList.loadData(ele);
}

DataList.previousPage = function(ele){
	ele = $(ele);
	var index = DataList.getParam(ele,Constant.PageIndex);
	DataList.setParam(ele,Constant.PageIndex,parseInt(index)-1);
	DataList.loadData(ele);
}

DataList.nextPage = function(ele){
	ele = $(ele);	
	var index = DataList.getParam(ele,Constant.PageIndex);
	DataList.setParam(ele,Constant.PageIndex,parseInt(index)+1);
	DataList.loadData(ele);
}

DataList.clear = function(ele){
	ele = $(ele);
	ele.innerHTML = "";
}