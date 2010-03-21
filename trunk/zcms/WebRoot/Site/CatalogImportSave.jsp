<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.zving.platform.*"%>
<%@page import="java.io.*"%>
<%@page import="java.util.*"%>
<%@page import="org.apache.commons.fileupload.*"%>
<%@page import="org.apache.commons.fileupload.servlet.*"%>
<%@page import="org.apache.commons.fileupload.disk.*"%>
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<%
String parentID = request.getParameter("ParentID");
String type = request.getParameter("Type");
String uploadPath = "",ext="";

FileItemFactory fileFactory = new DiskFileItemFactory();
ServletFileUpload fu = new ServletFileUpload(fileFactory);
List fileItems = fu.parseRequest(request);

String newFileName = "";
boolean isSucess = true;

fu.setHeaderEncoding("UTF-8");
Iterator iter = fileItems.iterator();
while (iter.hasNext()){
	FileItem item = (FileItem) iter.next();
	if (!item.isFormField()){
		String oldFileName = item.getName();
		System.out.println("UploadFileName:"+oldFileName);
		long size = item.getSize();
		if((oldFileName==null||oldFileName.equals("")) && size==0){
			continue;
		}
		oldFileName = oldFileName.substring(oldFileName.lastIndexOf("\\")+1);
		ext = oldFileName.substring(oldFileName.lastIndexOf("."));
		if(!ext.toLowerCase().equals(".txt")){
		  String referer = request.getHeader("Referer");
		  System.out.println( request.getParameter("DealType"));
			out.println("<script>alert('导入失败，只能导入txt格式的文件!');window.location='"+referer+"';</script>");
			return;
		}
		String alias = Application.getCurrentSiteAlias();
		String realDir = Config.getContextPath();
		uploadPath = realDir+"/"+Config.getValue("UploadDir")+"/"+alias+"/upload/Temp/";

		System.out.println("导入文本路径："+uploadPath);
		
		File path = new File(uploadPath);
		if(!path.exists()){
		    path.mkdirs();
		}
		
		
		newFileName = User.getUserName()+"_"+System.currentTimeMillis()+ext;
		uploadPath = uploadPath+newFileName;
		uploadPath = uploadPath.replace('\\', '/');
		System.out.println(uploadPath);
		try{
		  item.write(new File(uploadPath));
		  isSucess = true;
		}catch(Exception e){
			e.printStackTrace();
			isSucess = false;
		}
	}
}

if(isSucess){
	out.println("<script>parent.location='CatalogImport2.jsp?ParentID="+parentID+"&Type="+type+"&FilePath="+uploadPath+"';</script>");
}else{
	out.println("<script>alert('上传失败');</script>");
}

%>
