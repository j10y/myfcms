var _zcms_d,_zcms_s,_zcms_c,_zcms_l,_zcms_t,_zcms_s;
var _zcms_st=new Date().getTime();
var _zcms_stat = function(param){
	var p = {};
	if(param){
		var arr = param.split("&");
		for(var i=0;i<arr.length;i++){
			if(arr[i]){
				var arr2 = arr[i].split("=");
				if(arr2[0]){
					p[arr2[0]] = arr2[1];
				}
			}
		}
	}
	_zcms_d = p["Dest"];
	_zcms_s = p["SiteID"];
	_zcms_c = p["CatalogInnerCode"];
	_zcms_l = p["LeafID"];
	_zcms_t = p["Type"];
	p["sr"] = screen.width+"x"+screen.height;
	p["cd"] = screen.colorDepth;
	p["fv"] = _zcms_stat.fv();
	p["ce"] = _zcms_stat.ce();	
	p["je"] = _zcms_stat.je();
	p["la"] = navigator.systemLanguage?navigator.systemLanguage:navigator.language;
	p["cs"] = document.charset;
	
	p["vq"] = _zcms_stat.vq();	
	p["Referer"] = encodeURI(document.referrer);
	p["Title"] = encodeURI(document.title);
	p["URL"] = encodeURI(location.href);
	p["Host"] = location.host;
	var dest = _zcms_d;
	p["Dest"] = false;
	dest = dest+"?"+_zcms_stat.mq(p);
	document.write("<script src='"+dest+"' type='text/javascript'></script>");
};

_zcms_stat.mq = function(map){
	var sb = [];
	for(var prop in map){
		if(map[prop]){
			sb.push(prop+"="+map[prop]);
		}
	}	
	return sb.join("&");
}

_zcms_stat.trim = function(str){
	return str.replace(/(^\s*)|(\s*$)/g,"");
}


_zcms_stat.je = function(){
	var je="";
	var n=navigator;
	je = n.javaEnabled()?1:0;
	return je;
} 

_zcms_stat.fv = function(){
	var f="",n=navigator;	
	if(n.plugins && n.plugins.length){
		for(var ii=0;ii<n.plugins.length;ii++){
			if(n.plugins[ii].name.indexOf('Shockwave Flash')!=-1){
				f=n.plugins[ii].description.split('Shockwave Flash ')[1];
				break;
			}
		}
	}else if(window.ActiveXObject){
		for(var ii=10;ii>=2;ii--){
			try{
				var fl=eval("new ActiveXObject('ShockwaveFlash.ShockwaveFlash."+ii+"');");
				if(fl){
					f=ii + '.0'; break;
				}
			}catch(e){} 
		} 
	}
	return f;
}

_zcms_stat.ce = function(){
	var c_en = (navigator.cookieEnabled)? 1 : 0;
	return c_en;
}

_zcms_stat.vq = function(){
  var cs = document.cookie.split("; ");
  var name = _zcms_s+"_vq";
  var vq = 1;
  for(i=0; i<cs.length; i++){
	  var arr = cs[i].split("=");
	  var n = _zcms_stat.trim(arr[0]);
	  var v = arr[1]?_zcms_stat.trim(arr[1]):"";
	  if(n==name){
	  	vq = parseInt(v)+1;
	  	break;
	  }
	}
	var expires = new Date(new Date().getTime()+365*10*24*60*60*1000).toGMTString();
	var cv = name+"="+vq+";expires="+expires+";path=/;";
	document.cookie = cv;
	return vq;
}

var _tmp_beforeunload = window.onbeforeunload;
window.onbeforeunload = function(){
	if(_zcms_d){ 
		var p = {};
		p["Event"] = "Unload";
		p["LeafID"] = _zcms_l;
		p["SiteID"] = _zcms_s;
		p["CatalogInnerCode"] = _zcms_c;
		p["Type"] = _zcms_t;
		p["StickTime"] = (new Date().getTime()-_zcms_st)/1000;
		var dest = _zcms_d+"?"+_zcms_stat.mq(p);
		var s = document.createElement("script");
		s.src = dest;
		document.body.appendChild(s);	
	}
	if(_tmp_beforeunload){
		_tmp_beforeunload();
	}	
}