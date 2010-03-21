<%@ taglib uri="controls" prefix="z"%>
<z:init  method="com.zving.cms.document.Weekwork.initClient">
<link href="../images/common.css" rel="stylesheet" type="text/css" />
<link href="../images/index.css" rel="stylesheet" type="text/css" />
	<table border="0" cellspacing="0" cellpadding="0" class="anpai" width="210" height="95">		
         <tr style=" background:url(../images/xuxian2.gif) no-repeat center bottom;">
           <td  width="30%" align="center" valign="top" style="color:#3b7cb2; font-weight:bold;" >今日：</td>
           <td width="70%" align="left" valign="top" ><a href="#" target="_blank"><marquee behavior="scroll" direction="up" scrollamount="1" scrolldelay="100">${today}</marquee></a></td>
         </tr>
      
         <tr>
           <td align="center" valign="top" style="color:#3b7cb2; font-weight:bold;">明日：</td>
           <td align="left" valign="top"><a href="#" target="_blank"><marquee behavior="scroll" direction="up" scrollamount="1" scrolldelay="100">${tomorow}</marquee></a></td>
          </tr>
     </table>
</z:init>