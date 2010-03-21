<%@page import="com.zving.platform.pub.*"%>
<%@page import="java.io.*"%>
<%@page import="java.util.*"%>
<%@page import="org.apache.commons.fileupload.*"%>
<%@page import="org.apache.commons.fileupload.servlet.*"%>
<%@page import="org.apache.commons.fileupload.disk.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%
	String path = Config.getValue("UploadDir") + "/" + Application.getCurrentSiteAlias();
	String absolutePath = Config.getContextRealPath() + path + "/flash/";
	File dir = new File(absolutePath);
	if (!dir.exists()) {
		dir.mkdirs();
	}

	FileItemFactory fileFactory = new DiskFileItemFactory();
	ServletFileUpload fu = new ServletFileUpload(fileFactory);
	List fileItems = fu.parseRequest(request);
	long SwfID = 0;
	String SwfFileStr = "";
	fu.setHeaderEncoding("UTF-8");
	Iterator iter = fileItems.iterator();
	String returnStr = "";
	while (iter.hasNext()) {
		FileItem item = (FileItem) iter.next();
		if (!item.isFormField()) {
			String SwfFileName = "";
			String oldFileName = item.getName();
			long size = item.getSize();
			if ((oldFileName == null || oldFileName.equals("")) && size == 0) {
				continue;
			}
			System.out.println("UploadFileName:" + oldFileName);
			oldFileName = oldFileName.substring(oldFileName.lastIndexOf("\\") + 1);
			String ext = oldFileName.substring(oldFileName.lastIndexOf(".")+1);
			if (!ext.toLowerCase().equals("swf")) {
				returnStr = "上传失败，只能导入 swf 格式的文件!";
			} else {
				SwfID = NoUtil.getMaxID("SwfID");
				SwfFileName = SwfID + "." + ext;
				item.write(new File(absolutePath + SwfFileName));
				SwfFileStr += "flash/" + SwfFileName;
			}
		}
	}
	out.println("<script>window.parent.afterUploadSwf('" + returnStr + "','" + SwfFileStr + "');</script>");
%>
