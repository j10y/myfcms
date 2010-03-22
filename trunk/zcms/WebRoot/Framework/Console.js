var getDebugModeValue=Cookie.get("_DEBUGMODE");
if(getDebugModeValue==="true"){
	_DEBUGMODE = true;
}else{
	_DEBUGMODE = false;
}

var Console = {};
Console.cache=[];
Console.history=[];
Console.historyIndex=null;
Console.constr = function(value,inObject,inArray,link){
	var isArray = false, isHash, isElement = false, vtype=typeof value, result=[];
  if(vtype=="object"){
		if(Object.prototype.toString.call(value) === "[object Date]"){
			vtype = "date";
		} else if(Object.prototype.toString.call(value) === "[object String]"){
			vtype = "string";
		} else if(Object.prototype.toString.call(value) === "[object Boolean]"){
			vtype = "boolean";
		} else if(Object.prototype.toString.call(value) === "[object RegExp]"){
			vtype = "regexp";
		}
	}
	try{
		if(value instanceof Array&&typeof _object!="function"){
			isArray = true;
		}
		if(typeof value=="object"&&(value==window||value instanceof Object)&&!value.nodeName){
			isHash = true;
		}
		if(value!=undefined&&Boolean(value.nodeName)&&Boolean(value.nodeType)){
			isElement = true;
		}
		if(value==null||vtype=="number"||vtype=="string"||vtype=="boolean"||(vtype=="function"&&value.nodeName!="OBJECT")||vtype=="regexp"||vtype=="date"){
			if(value==null){
			  if(value===undefined){
					result.push("<span style='color:#999'>undefined</span>");
			  } else {
					result.push("<span style='color:#999'>null</span>");
			  }
			}else if (vtype=="regexp") {
			  result.push("<span style='color:#360'>" + value + "</span>");
			}else if (vtype=="date") {
			  result.push("<span style='color:#060;'>'" + value + "'</span>");
			} else if (vtype=="number") {
			  result.push("<span style='color:#009'>" + value + "</span>");
			} else if (vtype=="boolean") {
			  result.push("<span style='color:#00c'>" + value + "</span>");
			} else if(vtype=="function"){
				if(inObject||inArray){
			  result.push("<span style='color:#696; text-decoration:underline; cursor:pointer;' onclick='Console.log(Console.cache[" +( Console.cache.push( value ) -1 )+"])'>function()</span>");
				} else {
			  result.push("<span style='color:#090'>"+value.toString().substring(0,100)+(value.toString().length>100?" ...":"")+"</span>");
				}
			} else {
			  result.push("<span style='color:#e22'>"+( !inObject&&!inArray?value : "\""+value.substring(0,100)+(value.length>100?" ...":"")+"\"" ).replace(/\n/g,"\\n").replace(/\s/g,"&nbsp;").replace(/>/g,"&#62;").replace(/</g,"&#60;")+"</span>");
			}
		}else if(isElement){
      if(value.nodeType==3){
        result.push(Console.constr(value.nodeValue));
      }else if(inObject){
        result.push("<span style='color:#999'>"+value.nodeName.toLowerCase()+"</span>");
      } else {
        result.push("<span style='color:#00f;"+ ( !inObject&&!inArray?"'":"text-decoration:underline; cursor:pointer;' onclick='Console.log(Console.cache[" +(Console.cache.push( value ) -1 )+"])'" ) + "'>");
        if(inArray){
          result.push(value.nodeName.toLowerCase());
          if(value.getAttribute){
            if(value.getAttribute&&value.getAttribute("id")){
              result.push("<span style='color:#009'>#"+value.getAttribute("id")+"</span>");
            }
            var elClass = value.getAttribute(document.all?"className":"class")||"";
            result.push(!elClass?"":"<span style='color:#f00'>."+elClass.split(" ")[0]+"</span>");
          }
          result.push("</span>");
        } else {
          result.push("<span style='color:#009'>&#60;<span style='color:#00f'>"+ value.nodeName.toLowerCase());
          if(value.attributes){
            for(var i=0,len=value.attributes.length; i<len; i++){
              var item = value.attributes[i];
              if(!document.all||(item.nodeValue&&typeof item.nodeValue!="function")){
                result.push(" <span style='color:#009'>"+item.nodeName+"=\"<span style='color:#f00'>"+item.nodeValue+"</span>\"</span>");
              }
            }
          }
          result.push("</span>&#62;</span>");
        }
      }
    }else if(isArray||isHash){
      if(isArray){
        if(inObject){
          result.push("<span style='color:#999;text-decoration:underline; cursor:pointer;' onclick='Console.log(Console.cache[" +(Console.cache.push( value ) -1 )+"])'>["+value.length+"]</span>");
        } else {
          result.push("<span style='font-weight:normal'>[ ");
          for(var i=0,len=value.length; i<len; i++){
            if((inObject||inArray)&&i>3){
              result.push(", <span style='color:#999;font-weight:bold'>"+(len-4)+" More...</span>");
              break;
            }
            result.push( (i > 0 ? ", " : "") + Console.constr(value[i], false, true, true) );
          }
          result.push(" ]</span>");
        }
      } else if(inObject){
        result.push("<span  style='color:#009; text-decoration:underline; cursor:pointer;' onclick='Console.log(Console.cache[" +( Console.cache.push( value ) -1 )+"])'>Object</span>");
      } else {
        result.push("<span  style='color:#060;font-weight:normal;"+ ( !inObject&&!inArray?"'":" text-decoration:underline; cursor:pointer;' onclick='Console.log(Console.cache[" +( Console.cache.push( value ) -1 )+"])'" ) + ">Object");
        var i=0;
        for(var key in value){
          var val = value[key];
          if((inObject||inArray)&&i>3){
            result.push(" <span style='color:#999;font-weight:bold;'>More...</span>");
            break;
          }
          result.push(" "+key+"="+Console.constr(val,true));
          i++;
        }
        result.push("</span>");
      }
    } else {
      result.push(["<span style='color:#999;'>"+value+"</span>"]);
    }
  } catch(e){
    result.push("..");
  }
  return result.join("");
};
Console.inputKeydown=function(evt){
	evt = window.event||evt;
	var consoleInput=$("_ConsoleInput");
	if(evt.keyCode==13){
		Console.eval(consoleInput.value.replace(/(^\s*)|(\s*$)/g,""));
	}else if(evt.keyCode==40){
		if(Console.history[Console.historyIndex+1]){
			Console.historyIndex+=1;
			consoleInput.value=Console.history[Console.historyIndex];
		}
	}else if(evt.keyCode==38){
		if(Console.history[Console.historyIndex-1]){
			Console.historyIndex-=1;
			consoleInput.value=Console.history[Console.historyIndex];
		}
	}
}
Console.eval=function(cmd){
	var result;
	if(cmd.length==0)
		return;
	$("_ConsoleInput").value = "";
	Console.historyIndex = Console.history.push(cmd);
	try {
		result = eval.call(window,cmd);
		if(cmd.substr(0,7)!="Console"){
			Console.log('>> '+cmd);
			Console.log(result);
		}
	} catch(e){
		Console.log(e);
	}
};
Console.getConsoleDiv=function(){
	if(!$("_Console")){
		var ConsoleDiv = document.createElement("div");
		ConsoleDiv.id = "_Console";
		ConsoleDiv.style.cssText = "position:absolute; z-index:9999; left:0%;top:"+Math.max(document.documentElement.scrollTop, document.body.scrollTop)+"px; width:62%; background-color:#fff; border:1px solid #359; opacity:0.9; filter:alpha(opacity=90); padding:4px;"

		ConsoleDiv.innerHTML=
		'<div id="_ConsoleHead"; style="background-color:#cde; height:20px; color:#000; font-size:12px; line-height:20px; cursor:move;">\
		<a style="color:#123; float:right; text-decoration:none; margin:1px 2px 0;" href="javascript:Console.hide();void(0);">[隐藏]</a><a style="color:#123; float:right; text-decoration:none; margin:1px 2px 0;" href="javascript:Console.clear();void(0);">[清空]</a><a style="color:#123; float:right; text-decoration:none; margin:1px 2px 0;" href="javascript:void(0);" onclick="javascript:this.innerHTML=Console.switchDebugMode()?\'[调试模式:开]\':\'[调试模式:关]\';void(0);">[切换调试模式]</a>\
		</div>\
		<div id="_ConsoleBody"; style="font-size:12px; line-height:1.2;color:#333; width:100%; max-height:200px; overflow:auto;">\
		</div>\
		<div id="_ConsoleFoot" style="font-size:12px; line-height:2; color:#333; border-top:1px solid #ccc">&gt;&gt; <input type="text" id="_ConsoleInput" style="width:94%" />\
		</div>';
		document.getElementsByTagName("BODY")[0].appendChild(ConsoleDiv);
		var ConsoleDivHead=$("_ConsoleHead");
		var ConsoleDivBody=$("_ConsoleBody");
		if(isIE6)ConsoleDivBody.style.height='200px';
		var ConsoleDivFoot=$("_ConsoleFoot");
		if(DragManager){
			ConsoleDivBody.setAttribute("drag","false");
			ConsoleDivFoot.setAttribute("drag","false");
			ConsoleDiv.setAttribute("dragstart","DragManager.doDrag");
			ConsoleDiv.onmousedown = function(evt){DragManager.onMouseDown(evt,this);};//注册拖拽方法
		}
		$("_ConsoleInput").onkeydown = Console.inputKeydown;
		ConsoleDiv=null;
	}
	return $("_Console");
};
Console.log = function(){
	if(!_DEBUGMODE)return false;
	if(arguments.length<1)return;
	var bgColor = bgColor||"#fff";
	var ConsoleDiv = Console.getConsoleDiv();
	ConsoleDiv.style.display = "";
	var ConsoleDivTop=ConsoleDiv.style.top.replace(/\D/gi,"");
	if(ConsoleDivTop<Math.max(document.documentElement.scrollTop, document.body.scrollTop)||ConsoleDivTop>Math.max(document.documentElement.scrollTop, document.body.scrollTop)+(document.compatMode == "BackCompat"?document.body.clientHeight:document.documentElement.clientHeight)){
		ConsoleDiv.style.top=Math.max(document.documentElement.scrollTop, document.body.scrollTop)+"px";
	}
	var newItem=document.createElement("div");
	newItem.style.cssText="border-top:1px solid #cde; padding:3px;font-family:'Courier New'; font-size:13px; background-color:"+bgColor;
  var content = [];
  for(var i=0, len=arguments.length; i<len; i++){
    content.push( window.Console.constr(arguments[i],false,false,true) );
  }
	newItem.innerHTML= content.join(" ");
	var ConsoleDivBody=$("_ConsoleBody");
	ConsoleDivBody.appendChild(newItem);
	ConsoleDivBody.scrollTop = 9999;
	ConsoleDivBody=null;
};

Console.show=function(){
	var ConsoleDiv = Console.getConsoleDiv();
	ConsoleDiv.style.display='';
};
Console.hide=function(){
	$('_Console').style.display='none';
};
Console.clear=function(){
	$('_ConsoleBody').innerHTML='';
	Console.cache.length=0;
};
Console.switchDebugMode=function(status){
	if(status===undefined){
		_DEBUGMODE=!_DEBUGMODE;
	}else{
		_DEBUGMODE=status;
	}
	if(_DEBUGMODE){
		Cookie.set("_DEBUGMODE","true",60*60*24*100);
		Console.log("调试模式开启。");
		$("_ConsoleInput").focus();
	}else{
		Cookie.set("_DEBUGMODE","false",60*60*24*100);
	}
	return _DEBUGMODE;
};
Console.attachOnkeyup = function(event){
	if(event.altKey&&event.shiftKey&&event.ctrlKey){
		if(event.keyCode==68){
			Console.switchDebugMode();
		}
	}
};

if(window.console){
	Console = window.console;
}else{
	if(_DEBUGMODE){
		window.onerror = function(message,file,line){
				if($("_Console").style.display=="none")return;
				Console.log('脚本错误：行:'+line+'  错误:'+message+'  文件:'+file);
		};
	};
	if(isIE){
		document.attachEvent("onkeyup",Console.attachOnkeyup);
	}else{
		document.addEventListener("keyup",Console.attachOnkeyup,false);
	}
};


