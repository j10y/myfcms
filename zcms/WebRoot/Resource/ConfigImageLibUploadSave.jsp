<%@page import="java.io.*"%>
<%@page import="java.util.*"%>
<%@page import="org.apache.commons.fileupload.*"%>
<%@page import="org.apache.commons.fileupload.servlet.*"%>
<%@page import="org.apache.commons.fileupload.disk.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<%
String ImageFileIndex = "";
String path = ("upload/Image/").replaceAll("//","/");
String imageSrc = "";
String image = "";
String absolutePath = (Config.getContextRealPath()+Config.getValue("UploadDir")+"/"+Application.getCurrentSiteAlias()+"/"+path).replaceAll("//","/");
File dir = new File(absolutePath);
if(!dir.exists()){
	dir.mkdirs();
}

FileItemFactory fileFactory = new DiskFileItemFactory();
ServletFileUpload fu = new ServletFileUpload(fileFactory);
List fileItems = fu.parseRequest(request);

fu.setHeaderEncoding("UTF-8");
Iterator iter = fileItems.iterator();

while (iter.hasNext()){
	FileItem item = (FileItem) iter.next();
	if (!item.isFormField()){
		String oldFileName = item.getName();
		long size = item.getSize();
		if((oldFileName==null||oldFileName.equals("")) && size==0){
			continue;
		}
		System.out.println("UploadFileName:"+oldFileName);
		oldFileName = oldFileName.substring(oldFileName.lastIndexOf("\\")+1);
		String ext = oldFileName.substring(oldFileName.lastIndexOf("."));
		if(!ext.toLowerCase().equals(".gif")&&!ext.toLowerCase().equals(".jpg")&&!ext.toLowerCase().equals(".jpeg")&&!ext.toLowerCase().equals(".png")){
			out.println("<script>alert('上传失败，只能导入gif、jpg、jpeg格式的文件!');</script>");
			return;
		}
		item.write(new File(absolutePath+item.getFieldName()+ext));
		ImageFileIndex = item.getFieldName();
		image = path+item.getFieldName()+ext;
		imageSrc = (Config.getContextPath()+Config.getValue("UploadDir")+"/"+Application.getCurrentSiteAlias()+"/"+path).replaceAll("//","/")+item.getFieldName()+ext;
	}
}
out.println("<script>window.parent.afterUpload('"+ImageFileIndex+"','"+imageSrc+"','"+image+"');</script>");
%>
