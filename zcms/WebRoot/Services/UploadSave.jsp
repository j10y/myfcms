<%@page import="com.zving.platform.pub.NoUtil"%>
<%@page import="com.zving.framework.Config"%>
<%@page import="com.zving.framework.utility.*"%>
<%@page import="org.apache.commons.fileupload.*"%>
<%@page import="org.apache.commons.fileupload.servlet.*"%>
<%@page import="org.apache.commons.fileupload.disk.*"%>
<%@page import="java.io.*"%>
<%@page import="java.util.*"%>
<%
String path = (Config.getValue("UploadDir")+"/Link/").replaceAll("///","/").replaceAll("//","/");
String absolutePath = (Config.getContextRealPath()+path).replaceAll("//","/");
File dir = new File(absolutePath);
if(!dir.exists()){
	dir.mkdirs();
}
FileItemFactory fileFactory = new DiskFileItemFactory();
ServletFileUpload fu = new ServletFileUpload(fileFactory);
List fileItems = fu.parseRequest(request);
String PictureFile = "";
boolean returnStr = true;
fu.setHeaderEncoding("UTF-8");
fu.setSizeMax(1024 * 1024 * 200 * 10);
Iterator iter = fileItems.iterator();
Mapx filedMap = new Mapx();
try{
	while (iter.hasNext()){
		FileItem item = (FileItem) iter.next();
		filedMap.put(item.getFieldName(), item.getString("UTF-8"));
		if (!item.isFormField()){
			String oldFileName = item.getName();
			long size = item.getSize();
			if((oldFileName==null||oldFileName.equals("")) && size==0){
				continue;
			}
			System.out.println("UploadFileName:"+oldFileName);
			oldFileName = oldFileName.substring(oldFileName.lastIndexOf("\\")+1);
			String ext = oldFileName.substring(oldFileName.lastIndexOf("."));
			if(!".gif".equals(ext.toLowerCase())&&!".jpg".equals(ext.toLowerCase())&&!".jpeg".equals(ext.toLowerCase())&&!".png".equals(ext.toLowerCase())){
				out.println("<script>alert('上传失败，只能导入gif、jpg、jpeg、png格式的文件!');</script>");
				return;
			}
			PictureFile = StringUtil.getChineseFirstAlpha(filedMap.getString("Name")).toLowerCase()+NoUtil.getMaxID("pictureID")+ext;
			item.write(new File(absolutePath+PictureFile));
			returnStr = true;
			break;
		}else{
			returnStr = false;
		}
	}
} catch (Exception e) {
	e.printStackTrace();
}

out.println("<script>window.parent.afterSave('"+returnStr+"','/Link/"+PictureFile+"','"+Config.getContextPath()+path+PictureFile+"');</script>");
%>