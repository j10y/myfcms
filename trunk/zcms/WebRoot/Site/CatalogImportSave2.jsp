<%@page import="java.io.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="../Include/Init.jsp"%>
<%@ taglib uri="controls" prefix="z"%>
<%

String parentID = request.getParameter("ParentID");
String type = request.getParameter("Type");
String text=request.getParameter("batchColumn");

//test
System.out.println("parentID:"+parentID);
System.out.println("type:"+type);
String uploadPath = "";
boolean isSucess = false;

String alias = Application.getCurrentSiteAlias();
String realDir = Config.getContextPath();
uploadPath = realDir+"/"+Config.getValue("UploadDir")+"/"+alias+"/upload/Temp/";

File path = new File(uploadPath);
if(!path.exists()){
    path.mkdirs();
}

String newFileName = User.getUserName()+"_"+System.currentTimeMillis()+".txt";
uploadPath = uploadPath+newFileName;
uploadPath = uploadPath.replace('\\', '/');
uploadPath = uploadPath.replaceAll("//", "/");
//test
System.out.println("上传路径:"+uploadPath);
FileWriter fw=null;
try{
  fw=new FileWriter(new File(uploadPath));
  fw.write(text);
  isSucess=true;
}catch(Exception e){
	e.printStackTrace();
}finally{
	if(fw!=null){
		fw.close();
	}
}

if(isSucess){
	out.println("<script>parent.location='CatalogImport2.jsp?ParentID="+parentID+"&Type="+type+"&FilePath="+uploadPath+"';</script>");
}else{
	out.println("<script>alert('上传失败');</script>");
}

%>
