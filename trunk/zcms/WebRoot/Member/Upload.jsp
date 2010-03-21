<%@page import="com.zving.cms.pub.CatalogUtil"%>
<%@page import="com.zving.platform.pub.OrderUtil"%>
<%@page import="org.apache.commons.io.FileUtils"%>
<%@page import="com.zving.schema.ZCAttachmentSchema"%>
<%@page import="com.zving.framework.utility.NumberUtil"%>
<%@page import="com.zving.framework.utility.ChineseSpelling"%>
<%@page import="com.zving.schema.ZCCatalogSchema"%>
<%@page import="com.zving.member.Member"%>
<%@page import="com.zving.cms.site.Catalog"%>
<%@page import="com.zving.framework.data.QueryBuilder"%>
<%@page import="com.zving.framework.utility.StringUtil"%>
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@page import="com.zving.platform.pub.NoUtil"%>
<%@page import="com.zving.framework.Config"%>
<%@page import="org.apache.commons.fileupload.*"%>
<%@page import="java.io.*"%>
<%@page import="java.util.*"%>
<%
String Path = request.getParameter("Path");
String UserName = request.getParameter("UserName");
String Type = request.getParameter("Type");
String CatalogID = "";
String innerCode = "";
String SiteID    = "";
String SiteAlias = "";
if(StringUtil.isEmpty(Path)){
	Member m = new Member(UserName);
	m.fill();
	SiteID = m.getSiteID()+"";
	SiteAlias = new QueryBuilder("select Alias from ZCSite where ID = ?",m.getSiteID()).executeString();
	if(new QueryBuilder("select count(*) from zccatalog where SiteID = "+m.getSiteID()+" and Type= "+Catalog.ATTACHMENTLIB+" and Name = '会员上传'").executeInt()>0){
		CatalogID = new QueryBuilder("select ID from ZCCatalog where SiteID = "+m.getSiteID()+" and Type = "+Catalog.ATTACHMENTLIB+" and Name = '会员上传'").executeOneValue()+"";
		Path = "upload/Attach/"+new QueryBuilder("select Alias from ZCCatalog where ID = "+CatalogID).executeString()+"/";
		innerCode = CatalogUtil.getName(CatalogID);
	}else{
		CatalogID = NoUtil.getMaxID("CatalogID")+"";
		ZCCatalogSchema catalog = new ZCCatalogSchema();
		catalog.setID(CatalogID);
		catalog.setSiteID(m.getSiteID());
		catalog.setParentID(0);
		catalog.setInnerCode(NoUtil.getMaxNo("CatalogInnerCode", 4));
		catalog.setTreeLevel(1);
		catalog.setName("会员上传");
		catalog.setURL(" ");
		String alias = ChineseSpelling.getFirstAlpha("会员上传").toLowerCase();
		if(Catalog.checkAliasExists(alias,catalog.getParentID())){
			alias +=NoUtil.getMaxID("AliasNo");
		}
		catalog.setAlias(alias);
		catalog.setType(Catalog.ATTACHMENTLIB);
		catalog.setListTemplate("");
		catalog.setListNameRule("");
		catalog.setDetailTemplate("");
		catalog.setDetailNameRule("");
		catalog.setChildCount(0);
		catalog.setIsLeaf(1);
		catalog.setTotal(0);
		catalog.setOrderFlag(Catalog.getCatalogOrderFlag(0, catalog.getType()+""));
		catalog.setLogo("");
		catalog.setListPageSize(10);
		catalog.setPublishFlag("Y");
		catalog.setHitCount(0);
		catalog.setMeta_Keywords("");
		catalog.setMeta_Description("");
		catalog.setOrderColumn("");
		catalog.setImagePath("");
		catalog.setAddUser(m.getUserName());
		catalog.setAddTime(new Date());
		catalog.insert();
		Path = "upload/Attach/"+catalog.getAlias()+"/";
		innerCode = catalog.getInnerCode();
	}
	File f = new File(Config.getContextRealPath()+Config.getValue("UploadDir") + "/" + SiteAlias + "/"+Path);
	if(!f.exists()){
		f.mkdirs();
	}
}else{
	File dir = new File(Config.getContextRealPath()+Path);
	if(!dir.exists()){
		dir.mkdirs();
	}
}
FileItemFactory fileFactory = new DiskFileItemFactory();
ServletFileUpload fu = new ServletFileUpload(fileFactory);
List fileItems = fu.parseRequest(request);
String oldFileName = "";
String RealFileName = "";
String ext = "";
String AliasName = Config.getAppCode();
fu.setHeaderEncoding("UTF-8");
Iterator iter = fileItems.iterator();
FileItem uploadItem = null;
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
			if(Type!=null&&Type.equalsIgnoreCase("MemberAttach")){
				if(ext.equals("doc")||ext.equals("rar")||ext.equals("zip")||ext.equals("docx")||ext.equals("txt")||ext.equals("pdf")||ext.equals("xls")){
					long AttachID = NoUtil.getMaxID("DocID");
					int random = NumberUtil.getRandomInt(10000);
					String srcFileName = AttachID + "" + random + "." + ext;
					ZCAttachmentSchema attachment = new ZCAttachmentSchema();
					attachment.setID(AttachID);
					attachment.setName(oldFileName.substring(0,oldFileName.lastIndexOf(".")));
					attachment.setOldName(oldFileName);
					attachment.setSiteID(SiteID);
					attachment.setCatalogID(CatalogID);
					attachment.setCatalogInnerCode(innerCode);
					attachment.setPath(Path);
					attachment.setImagePath("");
					attachment.setFileName(srcFileName);
					attachment.setSrcFileName(srcFileName);
					attachment.setSuffix(ext);
					attachment.setIsLocked("N");
					attachment.setInfo(oldFileName);
					attachment.setSystem("CMS");
					attachment.setFileSize(FileUtils.byteCountToDisplaySize(uploadItem.getSize()));
					attachment.setAddTime(new Date());
					attachment.setAddUser(UserName);
					attachment.setOrderFlag(OrderUtil.getDefaultOrder());
					attachment.setModifyTime(new Date());
					attachment.setModifyUser(UserName);
					attachment.setProp1("0");
					if(uploadItem !=null){
						attachment.insert();
						uploadItem.write(new File(Config.getContextRealPath()+Config.getValue("UploadDir") + "/" + SiteAlias + "/"+Path+srcFileName));
						out.println("<script>window.parent.afterUploadAttach('"+AttachID+"','"+attachment.getName()+"."+ext+"');</script>");
					}
				}else{
					out.println("<script>alert('文件格式错误');</script>");
					return;
				}
			}else{
				if(ext.equals("gif")||ext.equals("jpg")||ext.equals("jpeg")||ext.equals("png")){
					long webAttachID = NoUtil.getMaxID("UserFileID");
					if(uploadItem !=null){
						RealFileName = webAttachID+"."+ext;
						uploadItem.write(new File(Config.getContextRealPath()+Path+RealFileName));
						out.println("<script>window.parent.afterUpload('/"+AliasName+Path+RealFileName+"');</script>");
					}
				}else{
					out.println("<script>alert('图片格式错误');</script>");
					return;
				}
			}
		}
	}
}

%>