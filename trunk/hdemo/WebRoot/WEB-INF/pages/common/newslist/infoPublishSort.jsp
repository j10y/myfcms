<%@ page contentType="text/html; charset=GBK"%>
<%@ include file="../../tagLib.jsp"%>
<%@ include file="../calendar.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<HEAD>
<TITLE><fmt:message key="mainFrame.title" /></TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=gb2312">
<link rel="stylesheet" href="styles/common/common.css" type="text/css" />
<script type="text/javascript" src="scripts/common/common.js"></script>
</HEAD>
<BODY class="bellBody">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td height="12" class="topCell"></td>
	</tr>
	<tr>
		<td valign="top" class="topCell">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td width="15" height="12"></td>
				<td width="12"><img src="images/index_t_r6_c10.png" width="12"
					height="12" /></td>
				<td height="12" background="images/index_t_r6_c17b.png"></td>
				<td width="12"><img src="images/index_t_r6_c17.png" width="12"
					height="12" /></td>
				<td width="15" height="12"></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td background="images/index_t_r8_c10.png">&nbsp;</td>
				<!-- center -->
				<td class="centerCell">
				<form method="post" action="<c:url value="/newsListSort.do"/>"
					name="form1"><input type="hidden" name="isItemid"
					value="<c:out value="${formInfo.isItemid}"/>"> <input
					type="hidden" name="infoIds" id="infoIds"> <input
					type="hidden" name="returnUrl"
					value="<c:out value="${formInfo.returnUrl}"/>"><spring:bind
					path="formInfo.*">
					<c:if test="${status.error}">
						<table width="700" border="0" align="center" cellpadding="0"
							cellspacing="1" class="billMsgTable">
							<tr class="billMsgRow">
								<td class="billMsgCell"><c:forEach
									items="${status.errorMessages}" var="errorMessage">
									<c:out value="${errorMessage}" />
									<br>
								</c:forEach></td>
							</tr>
						</table>
					</c:if>
				</spring:bind>
				<table width="97%" border="0" cellspacing="0" cellpadding="0"
					class="bellTable">
					<tr class="bellTitleRow">
						<td width="95"><img src="images/eis_p_b1.png" width="95"
							height="50" /></td>
						<td valign="bottom">
						<table width="90%" height="30" border="0" cellpadding="0"
							cellspacing="0">
							<tr>
								<td class="bellTitleCell"><fmt:message
									key="infoPublish.label.infoPublishManagement" /><fmt:message
									key="public.label.sort" /></td>
							</tr>
						</table>
						</td>
					</tr>
				</table>
				<table width="97%" border="0" cellpadding="5" cellspacing="1"
					class="bellContentTable">
					<tr class="bellContentRow">
						<td class="bellContentDataCell" colspan="2"><c:out value="${formInfo.itemText}"/></td>
					</tr>
					<tr class="bellContentRow">
						<td width="80%" class="bellContentDataCell"><select
							id="infoId" NAME="infoId" style="width:550px;height:300px"
							MULTIPLE>
							<c:forEach items="${formInfo.newsList}" var="infoPublish"
								varStatus="status">
								<option value="<c:out value="${infoPublish.infoId}"/>"
									<c:if test="${status.count == 1}">selected</c:if>><c:out
									value="${infoPublish.title}" /></option>
							</c:forEach>
						</select></td>
						<td width="20%" class="bellContentDataCell"><input
							type="button" class="button_up" onclick="upmove()"><br>
						<br>
						<br>
						<input type="button" class="button_down" onclick="downmove()"></td>
					</tr>
					<tr class="bellBottomRow">
						<td class="bellBottomCell" colspan="2"><input class="button_save"
							type="button" name="saveButton" onclick="selId()">&nbsp;&nbsp;&nbsp;&nbsp;<input
							onclick="javascript:window.location='<c:url value ="${formInfo.returnUrl}"/>';"
							class="button_return" type="button" name="return" /></td>
					</tr>
				</table>
		</form>
		</td>
		<!-- center end -->
		<td background="images/index_t_r8_c19.png">&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td></td>
		<td><img src="images/index_t_r6_c10d.png" width="12" height="12" /></td>
		<td background="images/index_t_r6_c17bd.png"></td>
		<td><img src="images/index_t_r6_c17d.png" width="12" height="12" /></td>
		<td></td>
	</tr>
</table>
</td>
</tr>
</table>

<script language="JavaScript" type="text/JavaScript">
  function upmove()
  {
          var i;
          var selectlen = document.form1.infoId.length;

          var optionsnews = new Array(selectlen);;

          var selectedIndex = document.form1.infoId.selectedIndex;

      var textcc = new Array(selectlen);
      var valuecc = new Array(selectlen);

      for (i = 0 ; i < selectlen; i++)
      {
          textcc[i] = document.form1.infoId.options[i].text;
          valuecc[i] = document.form1.infoId.options[i].value;
          optionsnews[i] = new Option(textcc[i], valuecc[i]);
      }


  if (selectedIndex != 0)
  {
          for (i = selectlen-1 ; i >= 0; i--)
          {
              document.form1.infoId.options[i] = null;
      }

      for (i = 0 ; i < selectedIndex-1; i++)
      {
          document.form1.infoId.options[i] = optionsnews[i];
      }

      document.form1.infoId.options[selectedIndex-1] = optionsnews[selectedIndex];
      document.form1.infoId.options[selectedIndex] = optionsnews[selectedIndex-1];

      for (i = selectedIndex+1 ; i < selectlen; i++)
      {
          document.form1.infoId.options[i] = optionsnews[i];
      }

      document.form1.infoId.selectedIndex = selectedIndex-1;

     }

  }

  function downmove()
  {
          var i;
          var selectlen = document.form1.infoId.length;

          var optionsnews = new Array(selectlen);;

          var selectedIndex = document.form1.infoId.selectedIndex;

      var textcc = new Array(selectlen);
      var valuecc = new Array(selectlen);

      for (i = 0 ; i < selectlen; i++)
      {
          textcc[i] = document.form1.infoId.options[i].text;
          valuecc[i] = document.form1.infoId.options[i].value;
          optionsnews[i] = new Option(textcc[i], valuecc[i]);
      }


      if (selectedIndex != selectlen)
      {
              for (i = selectlen-1 ; i >= 0; i--)
              {
                  document.form1.infoId.options[i] = null;
          }

          for (i = 0 ; i < selectedIndex; i++)
          {
              document.form1.infoId.options[i] = optionsnews[i];
          }

          document.form1.infoId.options[selectedIndex] = optionsnews[selectedIndex+1];
          document.form1.infoId.options[selectedIndex+1] = optionsnews[selectedIndex];

          for (i = selectedIndex+2 ; i < selectlen; i++)
          {
              document.form1.infoId.options[i] = optionsnews[i];
          }
          document.form1.infoId.selectedIndex = selectedIndex+1;
      }
  }

	function selId(){
		  var infoIds = "";		  
	      for (var i = 0 ; i < document.form1.infoId.length; i++)
	      {
		        infoIds = infoIds + document.form1.infoId.options[i].value + ",";
	      }
		  document.form1.infoIds.value=infoIds;
		  document.form1.submit();
	}
</script>
</BODY>
</HTML>
