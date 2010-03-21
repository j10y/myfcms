function ZCMSAD(PositionID) {
  this.ID        = PositionID;
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
  
  this.ShowAD  = showADContent;
}

function showADContent() {
  var content = this.ADContent;
  var str = "<div id='ZCMSAD_"+this.ADID+"' style='width:"+this.Width+"px; height:"+this.Height+"px; text-align:center'>";
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
  }else if(this.ADType == "text"){
	  var MaxDisPlay = new Number(AD.MaxDisPlay);
  	  var Count = new Number(AD.Count);
  	  if(MaxDisPlay < Count){
		AD.Count = AD.MaxDisPlay;
	  }
	  for(var i=0;i<AD.Count;i++){
	  	str += "<li><a href="+AD.Text[i].textLinkUrl+" style='color:"+AD.Text[i].TextColor+";' target='_blank' title='"+AD.Text[i].textContent+"'>"+AD.Text[i].textContent+"</a></li>";
	  }
  }
  str += "</div>";
  document.write(str);
}
