<%@ page contentType="text/html; charset=GBK"%>
<%@ include file="../../tagLib.jsp"%>
<%@ include file="../ymPrompt.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<TITLE><fmt:message key="mainFrame.title" /></TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=gb2312">
<link rel="stylesheet" href="styles/common/common.css" type="text/css" />
<script type="text/javascript" src="scripts/common/common.js"></script>
<%@ include file="../appDictTreeSetup.jsp"%>
</head>
<body class="queryBody">
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
				<form method="post" action="<c:url value="/treeEdit.do"/>"
					id="test1"><input type="hidden" name="deleteUrl"
					id="deleteUrl" />
				<table width="97%" border="0" cellspacing="0" cellpadding="0"
					class="queryConditionTable">
					<tr class="queryConditionTitleRow">
						<td width="95"><img src="images/eis_p_b1.png" width="95"
							height="50" /></td>
						<td valign="bottom">
						<table width="90%" height="30" border="0" cellpadding="0"
							cellspacing="0">
							<tr>
								<td class="queryConditionTitleCell"><fmt:message
									key="public.label.queryCondition" /></td>
							</tr>
						</table>
						</td>
					</tr>
				</table>
				<table class="queryMiddleTable" width="95%" border="0"
					cellpadding="0" cellspacing="1">
					<tr class="queryMiddleRow">
						<td width="47%" class="queryMiddleCell"><input type="button"
							class="button_addChildNode" name="Submit2" onclick="addChild();">
						<input type="button" class="button_addBrotherNode" name="Submit3"
							onclick="addBrother();"> <input type="button"
							class="button_deleteNode" name="Submit4" onclick="delNode();">
						<input type="button" class="button_editNode" name="Submit4"
							onclick="updateNode();"></td>
					</tr>
				</table>
				<table width="97%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="95"><img src="images/eis_p_b3.png" width="95"
							height="50" /></td>
						<td valign="bottom" background="images/eis_p_b4.png">
						<table width="90%" height="30" border="0" cellpadding="0"
							cellspacing="0">
							<tr>
								<td class="queryConditionTitleCell"><c:if test="${childsiteId != 1}"><fmt:message
														key="infoPublish.label.orgCategoryOne" /></c:if><fmt:message
									key="tree.label.treeManagement" /></td>
							</tr>
						</table>
						</td>
					</tr>
				</table>
				<table width="97%" border="0" cellpadding="3" cellspacing="1"
					class="queryListTable">
					<tr class="queryListRow">
						<td class="queryListCell">
						<table width="90%" align="center">
							<tr>
								<td align="left"><script type="text/javascript">
var tree = new appTree("<c:out value="${parentTree.itemText}"/>","<c:out value="${parentTree.itemId}"/>");
tree.action = "JavaScript:putId('<c:out value="${parentTree.category}"/>', '<c:out value="${parentTree.itemId}"/>', '0', '<c:out value="${parentTree.itemText}"/>', '');";
var treeItems = new Array;
<c:forEach items="${treeList}" var="trees" varStatus="status">
	treeItems[<c:out value="${status.index}"/>] = new appDictTreeItem("<c:out value="${trees.itemText}"/>", "", "<c:out value="${trees.itemId}"/>", "<c:out value="${trees.parentId}"/>", "<c:out value="${trees.category}"/>", "<c:out value="${trees.itemText}"/>", "<c:out value="${trees.elderBrotherId}"/>");    
	treeItems[<c:out value="${status.index}"/>].target = "";
</c:forEach>
genTree(tree, treeItems);
document.write(tree);
tree.expand();
   
function putId(category,itemId,parentId,itemText,elderBrotherId){
	//alert(parentId);
	document.getElementById("parentId").value=parentId;
	document.getElementById("itemId").value=itemId;
	document.getElementById("itemText").value=itemText;
}
    
function setData(node){
	document.getElementById("parentId").value=node.uParentId;
	document.getElementById("itemId").value=node.uItemId;
	document.getElementById("itemText").value=node.uItemText;
}
//addChild
function addChild(){
	var node=getSelectedNode();
	if(node!=null){
		<c:if test="${childsiteId == 1}">
		if(node==tree){
			window.location.href='<c:url value="/treeEdit.do?parentId=0&itemId='+node.value+'&isBrother=0&referenceNodeId='+node.value+'&category=newsChannel&editFlag=add&childsiteId=${childsiteId}&returnUrl=${requestUrl}"/>';
		}else{
			window.location.href='<c:url value="/treeEdit.do?parentId='+node.uParentId+'&itemId='+node.uItemId+'&isBrother=0&referenceNodeId='+node.uItemId+'&category='+node.uCategory+'&editFlag=add&childsiteId=${childsiteId}&returnUrl=${requestUrl}"/>';
	    }
	    </c:if>
	    <c:if test="${childsiteId != 1}">
		if(node==tree){
			window.location.href='<c:url value="/treeChildEdit.do?parentId=0&itemId='+node.value+'&isBrother=0&referenceNodeId='+node.value+'&category=newsChannel&editFlag=add&childsiteId=${childsiteId}&returnUrl=${requestUrl}"/>';
		}else{
			window.location.href='<c:url value="/treeChildEdit.do?parentId='+node.uParentId+'&itemId='+node.uItemId+'&isBrother=0&referenceNodeId='+node.uItemId+'&category='+node.uCategory+'&editFlag=add&childsiteId=${childsiteId}&returnUrl=${requestUrl}"/>';
	    }
	    </c:if>
	}else{
		dataAlert("<fmt:message key="tree.label.selectNode"/>");
	}
}
//add Brother
function addBrother(){
	var node=getSelectedNode();
	if(node==null){
		dataAlert("<fmt:message key="tree.label.doNotaddBrother"/>");
	}
	else{
		if(node==tree){
			dataAlert("<fmt:message key="tree.label.doNotaddBrother"/>");
		}
		else{
			if(node.uItemId==""){
				dataAlert("<fmt:message key="tree.label.selectNode"/>");
			}
			else{
			<c:if test="${childsiteId == 1}">
				window.location.href='<c:url value="/treeEdit.do?parentId='+node.uParentId+'&itemId='+node.uItemId+'&isBrother=1&referenceNodeId='+node.uItemId+'&category='+node.uCategory+'&editFlag=add&childsiteId=${childsiteId}&returnUrl=${requestUrl}"/>';
			</c:if>
			<c:if test="${childsiteId != 1}">
				window.location.href='<c:url value="/treeChildEdit.do?parentId='+node.uParentId+'&itemId='+node.uItemId+'&isBrother=1&referenceNodeId='+node.uItemId+'&category='+node.uCategory+'&editFlag=add&childsiteId=${childsiteId}&returnUrl=${requestUrl}"/>';
			</c:if>
			}
		}
	}     
}
//delNode
function delNode(){
	var node=getSelectedNode();  
	if(node==null){
		dataAlert("<fmt:message key="tree.label.selectNode"/>");
	}
	else{
		if(node==tree){
			dataAlert("<fmt:message key="tree.label.doNotdel"/>");
		}
		else{
			<c:if test="${childsiteId == 1}">
			document.getElementById("deleteUrl").value = '<c:url value="/treeDelete.do?itemId='+node.uItemId+'&childsiteId=${childsiteId}&returnUrl=${requestUrl}"/>';
			</c:if>
			<c:if test="${childsiteId != 1}">
			document.getElementById("deleteUrl").value = '<c:url value="/treeChildDelete.do?itemId='+node.uItemId+'&childsiteId=${childsiteId}&returnUrl=${requestUrl}"/>';			
			</c:if>
			dataDeleteConfirm();
		}
	}
}
//updateNode    
function updateNode(){
	var node=getSelectedNode();
	if(node==null){
		dataAlert("<fmt:message key="tree.label.selectNode"/>");
	}
	else{
		if(node==tree){
			dataAlert("<fmt:message key="tree.label.doNotUpdateText"/>");
		}else{
		<c:if test="${childsiteId == 1}">
			window.location.href='<c:url value="/treeEdit.do?parentId='+node.uParentId+'&itemId='+node.uItemId+'&referenceNodeId='+node.uItemId+'&childsiteId=${childsiteId}&editFlag=modify&returnUrl=${requestUrl}"/>';
		</c:if>
		<c:if test="${childsiteId != 1}">
			window.location.href='<c:url value="/treeChildEdit.do?parentId='+node.uParentId+'&itemId='+node.uItemId+'&referenceNodeId='+node.uItemId+'&childsiteId=${childsiteId}&editFlag=modify&returnUrl=${requestUrl}"/>';
		</c:if>
		}
	}
}
function updateOrg(){
    var node=getSelectedNode();
	if(node==null){
		dataAlert("<fmt:message key="tree.label.selectNode"/>");
	}
	else{
		if(node==tree){
			dataAlert("父节点不能修改");
		}else{
			window.location.href='<c:url value="/treeOrg.do?treeId='+node.uItemId+'&editFlag=modify&childsiteId=${childsiteId}&returnUrl=${requestUrl}"/>';
		}
	}
}
</script></td>
							</tr>
						</table>
						</td>
					</tr>
				</table>
				<table class="queryBottomTable" width="95%" border="0"
					align="center" cellpadding="0" cellspacing="1">
					<tr class="queryBottomRow">
						<td class="queryBottomCell" width="47%">&nbsp;</td>
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
				<td><img src="images/index_t_r6_c10d.png" width="12"
					height="12" /></td>
				<td background="images/index_t_r6_c17bd.png"></td>
				<td><img src="images/index_t_r6_c17d.png" width="12"
					height="12" /></td>
				<td></td>
			</tr>
		</table>
		</td>
	</tr>
</table>
<script language="javascript">
  function getSelectedNode() { 
  var node = tree.getSelected();
  if(node!=null){
    return node;
    }
  }
</script>
</BODY>
</HTML>
