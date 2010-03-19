function putInput(msgId,divId) {
	var innerText = "<form id=replayform name=replayform action=\"messagereplay.do\" method=\"post\">";
		innerText+="<input type=\"hidden\" name=\"returnurl\" id=\"returnurl\"  value=\"<c:out value='${requesturl}'/>";
		innerText+="<input type=\"hidden\" name=\"msgId\" id=\"msgId\"  value='"+ msgId +"'/>";
		innerText+="<table width=\"100%\">";
		innerText+="<tr><td><textarea name=\"content\" style=\"width: 99%;height: 100px;\"></textarea></td></tr>";
		innerText+="<tr><td valign=bottom><button type=submit>&nbsp;»Ø¸´&nbsp;</button></td></tr>";
		innerText+="</table></form>";
	
		var replayDiv = document.getElementById(divId);
		replayDiv.innerText = inerText;
	
}


			