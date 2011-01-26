<%
	String basePath=request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="msthemecompatible" content="no">
		<title>HOME</title>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/ext3/resources/css/ext-all.css" />

		<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/admin.css"/>
		
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/ext3/ux/css/ux-all.css"/>
		<script type="text/javascript" src="<%=basePath%>/js/dynamic.jsp"></script>
		<script type="text/javascript" src="<%=basePath%>/ext3/adapter/ext/ext-base-debug.js"></script>
		<script type="text/javascript" src="<%=basePath%>/ext3/ext-all-debug.js"></script>
		<script type="text/javascript" src="<%=basePath%>/ext3/ext-lang-zh_CN.js"></script>
				
		<script type="text/javascript" src="<%=basePath%>/js/App.import.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/core/AppUtil.js"></script>
		
		<script type="text/javascript" src="<%=basePath%>/js/core/ScriptMgr.js"></script>		
		<script type="text/javascript" src="<%=basePath%>/js/core/TreeSelector.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/fckeditor/fckeditor.js"></script>
  		<script type="text/javascript" src="<%=basePath%>/js/selector/UserSelector.js"></script>
  		
  		<script type="text/javascript" src="<%=basePath%>/js/flow/FormDesignWindow.js"></script>
  		
  		
	    <script type="text/javascript">
	    	
	    	Ext.onReady(function(){
	    		new FormDesignWindow().show();
	    	});
	    </script>
</head>
<body>
	
</body>
</html>