function ZCMSAD(PositionID) {
  this.ID         = PositionID;
  this.ADID		  = 0;
  this.ADType	  = "";
  this.ADName	  = "";
  this.ADContent = "";
  this.PaddingLeft = 0;
  this.PaddingTop  = 0;
  this.Width = 0;
  this.Height = 0;
  this.IsHitCount = "N";
  this.UploadFilePath = "";
  
  this.Step = 1;
  this.Delay= 20;
  this.WindowHeight = 0;
  this.WindowWidth = 0;
  this.Yon = 0;
  this.Xon = 0;
  this.Pause = true;
  this.Interval = null;
  
  this.ShowAD  = showADContent;
  this.Start   = doStart;
}

function showADContent() {
  var content = this.ADContent;
  var str = "<div id='ZCMSAD_"+this.ADID+"' style='left:"+this.PaddingLeft+"px;top:"+this.PaddingTop+"px;width:"+this.Width+"px; height:"+this.Height+"px; position: absolute;visibility: visible;z-index:999999;' onMouseOver='"+this.ID+"_pause_resume();' onMouseOut='"+this.ID+"_pause_resume();'>";
  var AD = eval('('+content+')');
  if (this.ADType == "image") {
	  str += "<a href='"+AD.Images[0].imgADLinkUrl+"' target='"+((AD.imgADLinkTarget == "Old") ? "_self" : "_blank") + "'>";
	  str += "<img title='"+AD.Images[0].imgADAlt+"' src='"+this.UploadFilePath+AD.Images[0].ImgPath+"' width='"+this.Width+"' height='"+this.Height+"' style='border:0px;'>";
	  str += "</a>";
  }else if(this.ADType == "flash"){
	  str += "<object classid='clsid:D27CDB6E-AE6D-11cf-96B8-444553540000' width='"+this.Width+"' height='"+this.Height+"' id='FlashAD_"+this.ADID+"'>";
	  str += "<param name='movie' value='"+this.UploadFilePath+AD.Flashes[0].SwfFilePath+"' />"; 
      str += "<param name='quality' value='high' />";
      str += "<param name='wmode' value='transparent'/>";
      str += "<param name='swfversion' value='8.0.35.0' />";
	  str += "<embed wmode='transparent' src='"+this.UploadFilePath+AD.Flashes[0].SwfFilePath+"' quality='high' pluginspage='http://www.macromedia.com/go/getflashplayer' type='application/x-shockwave-flash' width='"+this.Width+"' height='"+this.Height+"'></embed>";
      str += "</object>";	  
  }
  str += "<div style='text-align:right;'><a href='#' onclick='javascript:document.getElementById(\"ZCMSAD_"+this.ADID+"\").style.display=\"none\"'>关闭</a></div>";
  str += "</div>";
  document.write(str);
}

function changePos(float) {
	float.WindowWidth  = document.documentElement.clientWidth;
	float.WindowHeight = document.documentElement.clientHeight;
	document.getElementById("ZCMSAD_"+float.ADID).style.left = (float.PaddingLeft + document.documentElement.scrollLeft)+"px";
	document.getElementById("ZCMSAD_"+float.ADID).style.top = (float.PaddingTop + document.documentElement.scrollTop)+"px";
	if (float.Yon){
		float.PaddingTop = float.PaddingTop + float.Step;
	}else{
		float.PaddingTop = float.PaddingTop - float.Step;
	}
	if (float.PaddingTop < 0){
		float.Yon = 1;
		float.PaddingTop = 0;
	}
	if (float.PaddingTop >= (float.WindowHeight - float.Height)){
		float.Yon = 0;float.PaddingTop = (float.WindowHeight - float.Height);
	}
	if (float.Xon){
		float.PaddingLeft = float.PaddingLeft + float.Step;
	}else{
		float.PaddingLeft = float.PaddingLeft - float.Step;
	}
	if (float.PaddingLeft < 0){
		float.Xon = 1;
		float.PaddingLeft = 0;
	}
	if (float.PaddingLeft >= (float.WindowWidth - float.Width)){
		float.Xon = 0;
		float.PaddingLeft = (float.WindowWidth - float.Width);   
	}
}
	
function doStart(float){
	return function(){
        changePos(float);
    }
}
