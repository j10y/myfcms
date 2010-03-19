<%@ include file="../tagLib.jsp"%>
<HTML>
<HEAD>
<TITLE></TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=gb2312">
<link rel="stylesheet" href="styles/common/common.css" type="text/css">
</HEAD>
<BODY BGCOLOR=#FFFFFF LEFTMARGIN=0 TOPMARGIN=0 MARGINWIDTH=0
	MARGINHEIGHT=0>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td height="600" align="left" valign="top" bgcolor="#EFF2F4">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="12"></td>
			</tr>
		</table>
		<table width="225" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td width="15">&nbsp;</td>
				<td>
				<table width="195" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td colspan="3"><img src="images/index_9_r2_c2.png"
							width="195" height="54" /></td>
					</tr>
					<tr>
						<td width="10" background="images/index_t_r8_c4.png">&nbsp;</td>
						<td width="175" align="center" bgcolor="#CAD4EC" height="330"
							valign="top">
						<table width="175" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td>
								<div id="lll"
									style="overflow-y: scroll;width:175px;height:383px">
								<table width="145" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="30" align="center" valign="middle"
											background="images/index_t_r8_c5.png">
										<table width="145" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td width="40" align="center"><img
													src="images/20070730192048311.gif" width="16" height="16" />
												</td>
												<td>
												<div id="h1" align="left"><a
													href="<c:url value="/newsListQuery.do"/>"
													target="mainFrame" class="a_left"><SPAN id="m1"
													onclick="treeClick(this);"><fmt:message
													key="infoPublish.label.infoPublishManagement" /></SPAN></a>
												</td>
											</tr>
										</table>
										</td>
									</tr>
								
									<c:if test="${userInfo.userFunPriv['recycleBin'] != null}">
										<tr>
											<td height="30" align="center" valign="middle"
												background="images/index_t_r8_c5.png">
											<table width="145" border="0" cellspacing="0" cellpadding="0">
												<tr>
													<td width="40" align="center"><img
														src="images/20070730192048311.gif" width="16" height="16" />
													</td>
													<td>
													<div id="h2" align="left"><a
														href="<c:url value="/newsListRecycleBin.do"/>"
														target="mainFrame" class="a_left"><SPAN id="m3"
														onclick="treeClick(this);"><fmt:message
														key="infoPublish.label.infoPublishRecycleBinManagement" /></SPAN></a>
													</td>
												</tr>
											</table>
											</td>
										</tr>
									</c:if>
									<c:if test="${userInfo.userFunPriv['treeDict'] != null}">
										<tr>
											<td height="30" align="center" valign="middle"
												background="images/index_t_r8_c5.png" class="m_td">
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
												<tr>
													<td width="40" align="center"><img
														src="images/20070730192048311.gif" width="16" height="16" />
													</td>
													<td align="left"><a href="<c:url value="/tree.do"/>"
														target="mainFrame" class="a_left"><SPAN id="m4"
														onclick="treeClick(this);"><fmt:message
														key="tree.label.treeManagement" /></SPAN></a></td>
												</tr>
											</table>
											</td>
										</tr>
									</c:if>
									<c:if test="${userInfo.userFunPriv['softDown'] != null}">
										<tr>
											<td height="30" align="center" valign="middle"
												background="images/index_t_r8_c5.png">
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
												<tr>
													<td width="40" align="center"><img
														src="images/20070730192048311.gif" width="16" height="16" />
													</td>
													<td align="left"><a
														href="<c:url value="/softDownQuery.do"/>"
														target="mainFrame" class="a_left"><SPAN id="m5"
														onclick="treeClick(this);"><fmt:message
														key="softDown.label.softDownManagement" /></SPAN></a></td>
												</tr>
											</table>
											</td>
										</tr>
									</c:if>
									<!-- <tr>
								<td height="30" align="center" valign="middle"
									background="images/index_t_r8_c5.png">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="60" align="center"><img
											src="images/20070730192048311.gif" width="16" height="16" />
										</td>
										<td align="left"><a
											href="<c:url value="/dictionaryQuery.do"/>"
											target="mainFrame" class="a_left"><fmt:message
											key="dictionary.label.dictionaryManagement" /></a></td>
									</tr>
								</table>
								</td>
							</tr> 
									<c:if test="${userInfo.userFunPriv['survey'] != null}">
										<tr>
											<td height="30" align="center" valign="middle"
												background="images/index_t_r8_c5.png">
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
												<tr>
													<td width="40" align="center"><img
														src="images/20070730192048311.gif" width="16" height="16" />
													</td>
													<td align="left"><a
														href="<c:url value="/surveyQuery.do"/>" target="mainFrame"
														class="a_left"><SPAN id="m6"
														onclick="treeClick(this);"><fmt:message
														key="survey.label.surveyManagement" /></SPAN></a></td>
												</tr>
											</table>
											</td>
										</tr>
									</c:if>-->
									<c:if test="${userInfo.userFunPriv['flink'] != null}">
										<tr>
											<td height="30" align="center" valign="middle"
												background="images/index_t_r8_c5.png">
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
												<tr>
													<td width="40" align="center"><img
														src="images/20070730192048311.gif" width="16" height="16" />
													</td>
													<td align="left"><a
														href="<c:url value="/flinkQuery.do"/>" target="mainFrame"
														class="a_left"><SPAN id="m7"
														onclick="treeClick(this);"><fmt:message
														key="flink.label.flinkManagement" /></SPAN></a></td>
												</tr>
											</table>
											</td>
										</tr>
									</c:if>
									<c:if test="${userInfo.userFunPriv['interface'] != null}">
										<tr>
											<td height="30" align="center" valign="middle"
												background="images/index_t_r8_c5.png">
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
												<tr>
													<td width="40" align="center"><img
														src="images/20070730192048311.gif" width="16" height="16" />
													</td>
													<td align="left"><a
														href="<c:url value="/interfaceQuery.do"/>"
														target="mainFrame" class="a_left"><SPAN id="m8"
														onclick="treeClick(this);"><fmt:message
														key="index.label.indexManagement" /></SPAN></a></td>
												</tr>
											</table>
											</td>
										</tr>
									</c:if>
									<!-- <c:if test="${userInfo.userFunPriv['message'] != null}">
										<tr>
											<td height="30" align="center" valign="middle"
												background="images/index_t_r8_c5.png">
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
												<tr>
													<td width="40" align="center"><img
														src="images/20070730192048311.gif" width="16" height="16" />
													</td>
													<td align="left"><a
														href="<c:url value="/messageQuery.do?category=0"/>"
														target="mainFrame" class="a_left"><SPAN id="m9"
														onclick="treeClick(this);"><fmt:message
														key="message.label.messageManagement" /></SPAN></a></td>
												</tr>
											</table>
											</td>
										</tr>
									</c:if> -->
									<c:if test="${userInfo.userFunPriv['messageZDZ'] != null}">
										<tr>
											<td height="30" align="center" valign="middle"
												background="images/index_t_r8_c5.png">
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
												<tr>
													<td width="40" align="center"><img
														src="images/20070730192048311.gif" width="16" height="16" />
													</td>
													<td align="left"><a
														href="<c:url value="/messageZDZQuery.do?category=1"/>"
														target="mainFrame" class="a_left"><SPAN id="m18"
														onclick="treeClick(this);"><fmt:message
														key="message.label.messageZDZManagement" /></SPAN></a></td>
												</tr>
											</table>
											</td>
										</tr>
									</c:if>
									<c:if test="${userInfo.userFunPriv['messageZW'] != null}">
										<tr>
											<td height="30" align="center" valign="middle"
												background="images/index_t_r8_c5.png">
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
												<tr>
													<td width="40" align="center"><img
														src="images/20070730192048311.gif" width="16" height="16" />
													</td>
													<td align="left"><a
														href="<c:url value="/messageZWQuery.do?category=2"/>"
														target="mainFrame" class="a_left"><SPAN id="m19"
														onclick="treeClick(this);"><fmt:message
														key="message.label.messageZWManagement" /></SPAN></a></td>
												</tr>
											</table>
											</td>
										</tr>
									</c:if>
									
									<c:if test="${userInfo.userFunPriv['user'] != null}">
										<tr>
											<td height="30" align="center" valign="middle"
												background="images/index_t_r8_c5.png">
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
												<tr>
													<td width="40" align="center"><img
														src="images/20070730192048311.gif" width="16" height="16" />
													</td>
													<td align="left"><a
														href="<c:url value="/personQuery.do"/>" target="mainFrame"
														class="a_left"><SPAN id="m10"
														onclick="treeClick(this);"><fmt:message
														key="person.label.personManagement" /></SPAN></a></td>
												</tr>
											</table>
											</td>
										</tr>
									</c:if>
									<c:if test="${userInfo.userFunPriv['role'] != null}">
										<tr>
											<td height="30" align="center" valign="middle"
												background="images/index_t_r8_c5.png">
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
												<tr>
													<td width="40" align="center"><img
														src="images/20070730192048311.gif" width="16" height="16" />
													</td>
													<td align="left"><a
														href="<c:url value="/roleQuery.do"/>" target="mainFrame"
														class="a_left"><SPAN id="m11"
														onclick="treeClick(this);"><fmt:message
														key="role.label.roleManagement" /></SPAN></a></td>
												</tr>
											</table>
											</td>
										</tr>
									</c:if>
        							<c:if test="${userInfo.userFunPriv['organization'] != null}">
                                    <tr>
                                     <td height="30" align="center" valign="middle"
                                      background="images/index_t_r8_c5.png">
                                     <table width="100%" border="0" cellspacing="0"
                                      cellpadding="0">
                                      <tr>
                                       <td width="40" align="center"><img
                                        src="images/20070730192048311.gif" width="16" height="16" />
                                       </td>
                                       <td align="left"><a
                                        href="<c:url value="/organizationQuery.do"/>"
                                        target="mainFrame" class="a_left"><SPAN id="m12"
                                        onclick="treeClick(this);"><fmt:message
                                        key="org.label.organizationManagement" /></SPAN></a></td>
                                      </tr>
                                     </table>
                                     </td>
                                    </tr>
                                   </c:if>
									<c:if test="${userInfo.userFunPriv['log'] != null}">
										<tr>
											<td height="30" align="center" valign="middle"
												background="images/index_t_r8_c5.png">
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
												<tr>
													<td width="40" align="center"><img
														src="images/20070730192048311.gif" width="16" height="16" />
													</td>
													<td align="left"><a
														href="<c:url value="/logQuery.do"/>" target="mainFrame"
														class="a_left"><SPAN id="m14"
														onclick="treeClick(this);"><fmt:message
														key="log.label.logQuery" /></SPAN></a></td>
												</tr>
											</table>
											</td>
										</tr>
									</c:if>
									<c:if test="${userInfo.userFunPriv['addressBook'] != null}">
									<tr>
										<td height="30" align="center" valign="middle"
												background="images/index_t_r8_c5.png">
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
												<tr>
													<td width="40" align="center"><img
														src="images/20070730192048311.gif" width="16" height="16" />
													</td>
													<td align="left"><a
														href="<c:url value="addressBookQuery.do"/>" target="mainFrame"
														class="a_left"><SPAN id="m15"
														onclick="treeClick(this);"><fmt:message
														key="address.label.addressbook" /></SPAN></a></td>
												</tr>
											</table>
										</td>
									</tr>
									</c:if>
									<c:if test="${userInfo.userFunPriv['star'] != null}">
									<tr>
										<td height="30" align="center" valign="middle"
											background="images/index_t_r8_c5.png">
										<table width="100%" border="0" cellspacing="0"
											cellpadding="0">
											<tr>
												<td width="40" align="center"><img
													src="images/20070730192048311.gif" width="16" height="16" />
												</td>
												<td align="left"><a
													href="<c:url value="/starQuery.do"/>" target="mainFrame"
													class="a_left"><SPAN id="m16"
													onclick="treeClick(this);"><fmt:message
													key="star.label.star" /></SPAN></a></td>
											</tr>
										</table>
										</td>
									</tr>
									</c:if>
									<c:if test="${userInfo.userFunPriv['duty'] != null}">
									<tr>
										<td height="30" align="center" valign="middle"
											background="images/index_t_r8_c5.png">
										<table width="100%" border="0" cellspacing="0"
											cellpadding="0">
											<tr>
												<td width="40" align="center"><img
													src="images/20070730192048311.gif" width="16" height="16" />
												</td>
												<td align="left"><a
													href="<c:url value="/dutyQuery.do"/>" target="mainFrame"
													class="a_left"><SPAN id="m17"
													onclick="treeClick(this);"><fmt:message
													key="onduty.manager" /></SPAN></a></td>
											</tr>
										</table>
										</td>
									</tr>
									</c:if>
								</table>
								<div>
								</td>
							</tr>
						</table>
						</td>
						<td width="10" background="images/index_t_r8_c8.png">&nbsp;</td>
					</tr>
					<tr>
						<td><img src="images/index_7_18_r2_c2.png" width="10"
							height="10" /></td>
						<td height="10" background="images/index_7_18_r2_c4.png"></td>
						<td><img src="images/index_7_18_r2_c6.png" width="10"
							height="10" /></td>
					</tr>
				</table>
				</td>
			</tr>
		</table>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td>&nbsp;</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
<script type="text/javascript">
var sbSpeed=300;
var ss;
function sbMouseDown(target, up) {

  var targetdiv = document.getElementById(target);
  if (up==1) {
    
    targetdiv.scrollTop-=sbSpeed;
    
  } else {
    targetdiv.scrollTop+=sbSpeed;
  }
}

function noShowDiv() {
  if (document.all.m1) m1.style.color="#4348AF";
  if (document.all.m2) m2.style.color="#4348AF";
  if (document.all.m3) m3.style.color="#4348AF";
  if (document.all.m4) m4.style.color="#4348AF";
  if (document.all.m5) m5.style.color="#4348AF";
  if (document.all.m6) m6.style.color="#4348AF";
  if (document.all.m7) m7.style.color="#4348AF";
  if (document.all.m8) m8.style.color="#4348AF";
  if (document.all.m9) m9.style.color="#4348AF";
  if (document.all.m10) m10.style.color="#4348AF";
  if (document.all.m11) m11.style.color="#4348AF";
  if (document.all.m12) m12.style.color="#4348AF";
  if (document.all.m13) m13.style.color="#4348AF";
  if (document.all.m14) m14.style.color="#4348AF";
  if (document.all.m15) m15.style.color="#4348AF";
  if (document.all.m16) m16.style.color="#4348AF";
  if (document.all.m17) m17.style.color="#4348AF";
  if (document.all.m18) m18.style.color="#4348AF";
  if (document.all.m19) m19.style.color="#4348AF";
  if (document.all.m20) m20.style.color="#4348AF";
}
function treeClick(obj) {
  noShowDiv();
  obj.style.color='#FF6633';
}
</script>
</body>
</html>
