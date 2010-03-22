<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@page import="com.zving.schema.ZCMessageBoardSchema"%>
<%@page import="com.zving.platform.pub.NoUtil"%>
<%@page import="com.zving.framework.data.Transaction"%>
<%@page import="com.zving.framework.utility.StringUtil"%>
<%@page import="com.zving.framework.data.QueryBuilder"%>
<%@page import="com.zving.framework.Config"%>
<%@page import="org.apache.commons.fileupload.*"%>
<%@page import="java.io.*"%>
<%@page import="java.util.*"%>
<%
String AddUser = request.getParameter("AddUser");
String Tel = request.getParameter("Tel");
String Address = request.getParameter("Address");
String Email = request.getParameter("Email");
String Title = request.getParameter("Title");
String Content = request.getParameter("Content");
String SiteID = request.getParameter("SiteID");
if(StringUtil.isEmpty(SiteID)){
	SiteID = new QueryBuilder("select ID from zcsite order by AddTime desc").executeOneValue()+"";
}
String SiteAlias = new QueryBuilder("select Alias from ZCSite where ID = ?",SiteID).executeString(); 

if(StringUtil.isEmpty(SiteAlias)){
	return;
}
if(StringUtil.isNotEmpty(AddUser)&&StringUtil.isNotEmpty(Email)&&StringUtil.isNotEmpty(Title)&&StringUtil.isNotEmpty(Content)){
	String Path = "/attach/";
	System.out.print(Config.getContextRealPath()+Config.getValue("UploadDir") + "/" + SiteAlias + "/"+Path);
	File dir = new File(Config.getContextRealPath()+Config.getValue("UploadDir") + "/" + SiteAlias + "/"+Path);
	if(!dir.exists()){
		dir.mkdirs();
	}
	FileItemFactory fileFactory = new DiskFileItemFactory();
	ServletFileUpload fu = new ServletFileUpload(fileFactory);
	List fileItems = fu.parseRequest(request);
	String oldFileName = "";
    String RealFileName = "";
	String ext = "";
	fu.setHeaderEncoding("UTF-8");
	Iterator iter = fileItems.iterator();
	FileItem uploadItem = null;
	Transaction trans = new Transaction();
	while (iter.hasNext()){
		FileItem item = (FileItem) iter.next();
		if (!item.isFormField()){
			oldFileName = item.getName();
			System.out.println("-----UploadFileName:-----" + oldFileName);
			long size = item.getSize();
			if((oldFileName==null||oldFileName.equals("")) && size==0){
				continue;
			} else {
				if(size>2000000){
					out.println("<script>alert('文件太大，请上传合适大小的文件(不大于2MB)');</script>");
					return;
				}
				uploadItem = item;
				oldFileName = oldFileName.replaceAll("\\\\", "/");
				oldFileName = oldFileName.substring(oldFileName.lastIndexOf("/") + 1);
				ext = oldFileName.substring(oldFileName.lastIndexOf(".")+1);
				ext = ext.toLowerCase();
				long webAttachID = NoUtil.getMaxID("webAttachID");
				if(uploadItem !=null){
					RealFileName = webAttachID+"."+ext;
					uploadItem.write(new File(Config.getContextRealPath()+Config.getValue("UploadDir") + "/" + SiteAlias + "/"+Path+RealFileName));
				}
			}
		}
	}
	ZCMessageBoardSchema message = new ZCMessageBoardSchema();
	message.setID(NoUtil.getMaxID("MessageBoardID"));
	message.setSiteID(SiteID);
	message.setType("message");
	message.setTitle(Title);
	message.setContent(Content);
	message.setPublishFlag("1");
	message.setReplyFlag("0");
	message.setEmail(Email);
	message.setTel(Tel);
	message.setAddress(Address);
	if(StringUtil.isNotEmpty(RealFileName)){
		message.setAttachPath(Path+RealFileName);
		message.setProp1(oldFileName);
	}
	message.setAddUser(AddUser);
	message.setAddUserIP(request.getRemoteHost());
	message.setAddTime(new Date());
	trans.add(message,Transaction.INSERT);
	if(trans.commit()){
		out.println("<script>alert('留言成功');</script>");
		out.println("<script>window.parent.afterUpload();</script>");
	}else{
		out.println("<script>alert('上传出现错误');</script>");
		return;
	}
}else{
	out.println("<script>alert('系统错误');</script>");
	return;
}

%>