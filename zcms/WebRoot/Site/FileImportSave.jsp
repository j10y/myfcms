<%@page import="java.io.*"%>
<%@page import="java.util.*"%>
<%@page import="org.apache.commons.fileupload.*"%>
<%@page import="org.apache.commons.fileupload.servlet.*"%>
<%@page import="org.apache.commons.fileupload.disk.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<%

String siteID = request.getParameter("SiteID");
String Path = request.getParameter("Path");
String uploadPath = Path+"/";
String ext="";
boolean isSucess = false;
FileItemFactory fileFactory = new DiskFileItemFactory();
ServletFileUpload fu = new ServletFileUpload(fileFactory);
List fileItems = fu.parseRequest(request);

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
		if(!ext.toLowerCase().equals(".htm")
		    &&!ext.toLowerCase().equals(".html")
		    &&!ext.toLowerCase().equals(".php")
		    &&!ext.toLowerCase().equals(".jsp")
		    &&!ext.toLowerCase().equals(".asp")
		    &&!ext.toLowerCase().equals(".css")
		    &&!ext.toLowerCase().equals(".js")
		    &&!ext.toLowerCase().equals(".jpg")
		    &&!ext.toLowerCase().equals(".gif")){
		  String referer = request.getHeader("Referer");
		  System.out.println( request.getParameter("DealType"));
			out.println("<script>alert('上传失败，只能导入HTML、HTM、JSP、PHP、ASP、CSS、JS、JPG、GIF格式的文件!');window.location='"+referer+"';</script>");
			return;
		}
		
		File path = new File(uploadPath);
		if(!path.exists()){
		    path.mkdirs();
		}
		
		uploadPath = uploadPath+oldFileName;
		System.out.println(uploadPath);
		try{
		  item.write(new File(uploadPath));
		  isSucess = true;
		}catch(Exception e){
			e.printStackTrace();
			return;
		}
	}
}
if(isSucess){
	out.println("<script>alert('上传成功');</script>");
	out.println("<script>parent.Parent.location.reload();parent.closeDialog();</script>");
}else{
	out.println("<script>alert('上传失败');</script>");
	out.println("<script>window.location = 'FileImportDialog.jsp?SiteID="+siteID+"&Path="+Path+"';</script>");
}

%>
