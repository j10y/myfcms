function Progress(taskID,title,w,h){
	this.TaskID = taskID;
	this.Title = title;
	if(h){
		this.Height = h;
	}else{
		this.Height = 150;
	}
	if(w){
		this.Width = w;
	}else{
		this.Width = 400;
	}
}

Progress.prototype.show = function(func){
	var pw = $E.getTopLevelWindow();
	var diag = new Dialog("DialogProgress"+this.TaskID);
	diag.Width = this.Width;
	diag.Height = this.Height;
	diag.Title = this.Title;
	diag.URL = "javascript:void(0);";
	var id = this.TaskID;
	diag.OKEvent = function(){
		Progress.stop(id);
	};
	diag.show();
	//pw.Effect.clear(pw.$("_DialogBGDiv"));//清除动画
	pw.Effect.fade(pw.$("_DialogBGDiv"),3,40,1);
	var win = pw.$("_DialogFrame_"+diag.ID).contentWindow;
	var doc = win.document;
	doc.open();
	doc.write("<style>.progressBox{border:#ddd 1px solid}");
	doc.write(".progressBg{ background:#ddd url("+Server.ContextPath+"Framework/Images/progressBg.gif);}");
	doc.write(".progressLight{font-size:5px; line-height:5px; color:#99bb00; background:#99bb00}");
	doc.write(".progressShadow{font-size:5px; line-height:6px; color:#779911; background:#779911}");
	doc.write("table,td{border-collapse: collapse; border-spacing: 0;}</style>");
	doc.write("<body oncontextmenu='return false;'></body>") ;
	var arr = [];
	arr.push("<table width='100%' height='100%' border='0'><tr>");
	arr.push("  <td align='center' valign='middle'>");
	arr.push("	<div id='Info' style='text-align:left;width:100%;font-size:12px'>&nbsp;</div><br>");
	arr.push("      <table width='100%' border='1' cellpadding='1' cellspacing='0' class='progressBox'>");
	arr.push("      <tr><td class='progressBg'>");
	arr.push("				<table width='1%' border='0' cellpadding='0' cellspacing='0' id='tableP'>");
	arr.push("          <tr><td class='progressLight'>-</td></tr>");
	arr.push("          <tr><td class='progressShadow'>-</td></tr>");
	arr.push("        </table></td></tr></table></td></tr></table>");
	var div = doc.createElement("div");
	div.innerHTML = arr.join('');
	doc.body.appendChild(div);
	doc.close();
	diag.ParentWindow.$D = diag;
	diag.ParentWindow.$DW = win;
	diag.OKButton.value = "取消当前任务";
	diag.CancelButton.value = "关闭进度窗口";
	this.Dialog = diag;
	this.Dialog.onComplete = func;
	Progress.getInfo(this.TaskID);
}

Progress.getInfo = function(taskID){
	var dc = new DataCollection();
	dc.add("TaskID",taskID);
	Server.sendRequest("com.zving.framework.messages.LongTimeTaskPage.getInfo",dc,function(response){
		if(response.Status==0){
			Dialog.alert(response.Message);
		}else{
			if(Dialog.getInstance("DialogProgress"+taskID)==null){
				return;//有可能已经被用户手工关闭
			}
			var info = response.get("CurrentInfo");
			if(info!=null){
				$DW.document.getElementById("Info").innerHTML = info;
			}
			if(response.get("CompleteFlag")=="1"){
				$DW.document.getElementById("tableP").width = "100%";
				var diag = Dialog.getInstance("DialogProgress"+taskID);
				diag.OKButton.hide();
				if(response.get("ErrorFlag")!="1"){
					if(diag.onComplete){
						try{
							diag.onComplete();
						}catch(ex){
							alert(ex.message);
						}
					}
				}
			}else{
				var percent = response.get("Percent");
				if(percent=="0"){
					percent = "1";
				}
				$DW.document.getElementById("tableP").width = percent+"%";
				window.setTimeout(Progress.getInfo,1000,taskID);
			}
		}
	});
}

Progress.stop = function(taskID){
	var dc = new DataCollection();
	dc.add("TaskID",taskID);
	Server.sendRequest("com.zving.framework.messages.LongTimeTaskPage.stop",dc,function(response){
		function isStopComplete(){
			Server.sendRequest("com.zving.framework.messages.LongTimeTaskPage.stop",dc,function(response2){
				if(response2.Status==1){
					Dialog.alert("任务己取消");
					Dialog.getInstance("DialogProgress"+taskID).close();
				}else{
					window.setTimeout(isStopComplete,1000,taskID);
				}
			});
		}
		Dialog.getInstance("DialogProgress"+taskID).CancelButton.value = "正在取消任务...";
		Dialog.getInstance("DialogProgress"+taskID).CancelButton.disable();
		isStopComplete();
	});
}
