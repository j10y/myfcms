 package com.zving.cms.resource;
 
 import com.zving.cms.datachannel.Publisher;
 import com.zving.cms.pub.CatalogUtil;
 import com.zving.cms.pub.SiteUtil;
 import com.zving.framework.Config;
 import com.zving.framework.Page;
 import com.zving.framework.ResponseImpl;
 import com.zving.framework.User;
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.data.Transaction;
 import com.zving.framework.utility.FileUtil;
 import com.zving.framework.utility.Mapx;
 import com.zving.framework.utility.NumberUtil;
 import com.zving.framework.utility.StringUtil;
 import com.zving.platform.Application;
 import com.zving.platform.pub.NoUtil;
 import com.zving.schema.ZCImageRelaSchema;
 import com.zving.schema.ZCImageRelaSet;
 import com.zving.schema.ZCImageSchema;
 import com.zving.schema.ZCImageSet;
 import java.io.File;
 import java.util.Date;
 
 public class Image extends Page
 {
   public static Mapx initEditDialog(Mapx params)
   {
     String Alias = Config.getContextPath() + Config.getValue("UploadDir") + "/" + SiteUtil.getAlias(Application.getCurrentSiteID()) + "/".replaceAll("//", "/");
     String ids = params.getString("IDs");
     if (StringUtil.isNotEmpty(ids)) {
       String[] IDs = ids.split(",");
       ZCImageSchema Image = new ZCImageSchema();
       Image.setID(Long.parseLong(IDs[0]));
       Image.fill();
       params.putAll(Image.toMapx());
       params.put("IDCount", IDs.length);
       params.put("Alias", Alias);
       params.put("IDs", ids);
     }
     return params;
   }
 
   public static Mapx initViewDialog(Mapx params) {
     String Alias = Config.getValue("UploadDir") + "/" + SiteUtil.getAlias(Application.getCurrentSiteID()) + "/";
     Mapx map = ConfigImageLib.getImageLibConfig(Application.getCurrentSiteID());
     ZCImageSchema Image = new ZCImageSchema();
     Image.setID(params.getString("ID"));
     Image.fill();
     params.putAll(Image.toMapx());
     params.put("Alias", Alias);
     params.put("backID", 
       new QueryBuilder("select id from zcimage where CatalogID=? and id>? order by id asc", 
       Image.getCatalogID(), Image.getID()).executeString());
     params.put("nextID", 
       new QueryBuilder("select id from zcimage where CatalogID=? and id<? order by id desc", 
       Image.getCatalogID(), Image.getID()).executeString());
     params.put("imageID", Image.getID());
     String XunHuan1 = "";
     String XunHuan2 = "";
     for (int i = 1; i <= Image.getCount(); ++i) {
       String Width = map.getString("Width" + i);
       String Height = map.getString("Height" + i);
       XunHuan1 = XunHuan1 + "<a href='#;' hidefocus id='" + i + "' name='tab' onClick='onTabClick(this);'>缩略图" + i + "<br>" + 
         "(" + Width + "×" + Height + ")</a>";
       XunHuan2 = XunHuan2 + "<tr id='img" + 
         i + 
         "' name='Image' style='display:none' align='center'><td align='center' valign='middle' height='500'><img src='.." + 
         Alias + Image.getPath() + i + "_" + Image.getFileName() + "?" + Image.getModifyTime() + "' ></td></tr>";
     }
     params.put("XunHuan1", XunHuan1);
     params.put("XunHuan2", XunHuan2);
     return params;
   }
 
   public void edit() {
     ZCImageSchema image = new ZCImageSchema();
     image.setID(Long.parseLong($V("ID")));
     image.fill();
     image.setValue(this.Request);
     image.setModifyTime(new Date());
     image.setModifyUser(User.getUserName());
     if (image.update())
       this.Response.setLogInfo(1, "修改成功");
     else
       this.Response.setLogInfo(0, "修改失败");
   }
 
   public void imagesEdit()
   {
     String ids = $V("IDs");
     if (!(StringUtil.checkID(ids))) {
       this.Response.setStatus(0);
       this.Response.setMessage("传入ID时发生错误!");
       return;
     }
     if ((ids.indexOf("\"") >= 0) || (ids.indexOf("'") >= 0)) {
       this.Response.setStatus(0);
       this.Response.setMessage("传入ID时发生错误!");
       return;
     }
     Transaction trans = new Transaction();
     ZCImageSchema images = new ZCImageSchema();
     ZCImageSet set = images.query(new QueryBuilder("where id in (" + ids + ")"));
     for (int i = 0; i < set.size(); ++i) {
       images = set.get(i);
       images.setValue(this.Request);
       trans.add(images, 2);
     }
     if (trans.commit()) {
       this.Response.setStatus(1);
       this.Response.setMessage("修改成功！");
     } else {
       this.Response.setStatus(0);
       this.Response.setMessage("操作数据库时发生错误!");
     }
   }
 
   public void transfer()
   {
     String IDs = $V("IDs");
     if (!(StringUtil.checkID(IDs))) {
       this.Response.setStatus(0);
       this.Response.setMessage("传入ID时发生错误!");
       return;
     }
     long catalogID = Long.parseLong($V("CatalogID"));
     Transaction trans = new Transaction();
     trans.add(
       new QueryBuilder("update ZCImage set catalogid=? ,CatalogInnerCode=? where ID in (" + $V("IDs") + ")", catalogID, 
       CatalogUtil.getInnerCode(catalogID)));
     if (trans.commit())
       this.Response.setLogInfo(1, "转移成功");
     else
       this.Response.setLogInfo(0, "转移失败");
   }
 
   public void copy()
   {
     long catalogID = Long.parseLong($V("CatalogID"));
     String Alias = Config.getValue("UploadDir") + "/" + SiteUtil.getAlias(Application.getCurrentSiteID()) + "/";
     String catalogPath = CatalogUtil.getPath(catalogID);
     String InnerCode = new QueryBuilder("select InnerCode from zccatalog where ID = ?", catalogID)
       .executeString();
     String newPath = "upload/Image/" + catalogPath;
     String contextRealPath = Config.getContextRealPath();
     File dir = new File(contextRealPath + Alias + newPath);
     if (!(dir.exists())) {
       dir.mkdirs();
     }
     String IDs = $V("IDs");
     if (!(StringUtil.checkID(IDs))) {
       this.Response.setStatus(0);
       this.Response.setMessage("传入ID时发生错误!");
       return;
     }
     ZCImageSet imageSet = new ZCImageSchema().query(new QueryBuilder(" where ID in (" + IDs + ")"));
     boolean flag = true;
     String msg = "";
     for (int i = 0; i < imageSet.size(); ++i) {
       ZCImageSchema image = imageSet.get(i);
       String oldPath = Alias + image.getPath();
       String oldFileName = image.getFileName();
       String oldSrcFileName = image.getSrcFileName();
       image.setID(NoUtil.getMaxID("DocID"));
       image.setCatalogID(catalogID);
       image.setCatalogInnerCode(InnerCode);
       image.setPath(newPath);
       image.setFileName(image.getID() + NumberUtil.getRandomInt(10000) + 
         oldFileName.substring(oldFileName.lastIndexOf(".")));
       image.setSrcFileName(image.getID() + NumberUtil.getRandomInt(10000) + 
         oldSrcFileName.substring(oldSrcFileName.lastIndexOf(".")));
       if (!(FileUtil.copy(contextRealPath + oldPath + oldSrcFileName, contextRealPath + Alias + newPath + 
         image.getSrcFileName()))) {
         msg = "复制失败，原因是：'" + image.getName() + "'的原图复制失败";
         flag = false;
         break;
       }
       if (!(FileUtil.copy(contextRealPath + oldPath + "s_" + oldFileName, contextRealPath + Alias + newPath + "s_" + 
         image.getFileName()))) {
         msg = "复制失败，原因是：'" + image.getName() + "'的系统缩略图复制失败";
         flag = false;
         break;
       }
       int count = (int)image.getCount();
       for (int j = 1; j <= count; ++j) {
         if (FileUtil.copy(contextRealPath + oldPath + j + "_" + oldFileName, contextRealPath + Alias + newPath + 
           j + "_" + image.getFileName())) continue;
         msg = "复制失败，原因是：'" + image.getName() + "'的第" + j + "张缩略图复制失败";
         flag = false;
         break;
       }
 
       if (!(flag)) {
         break;
       }
 
       image.setAddTime(new Date());
       image.setAddUser(User.getUserName());
     }
 
     if ((flag) && (imageSet.insert()))
       this.Response.setLogInfo(1, "复制图片成功");
     else
       this.Response.setLogInfo(0, msg);
   }
 
   public void del()
   {
     String IDs = $V("IDs");
     if (!(StringUtil.checkID(IDs))) {
       this.Response.setStatus(0);
       this.Response.setMessage("传入ID时发生错误!");
       return;
     }
     String Alias = Config.getValue("UploadDir") + "/" + SiteUtil.getAlias(Application.getCurrentSiteID()) + "/";
     Transaction trans = new Transaction();
     ZCImageSet ImageSet = new ZCImageSchema().query(new QueryBuilder(" where id in (" + IDs + ")"));
     ZCImageRelaSet ImageRelaSet = new ZCImageRelaSchema().query(new QueryBuilder(" where id in (" + IDs + ")"));
     for (int i = 0; i < ImageSet.size(); ++i) {
       ZCImageSchema image = ImageSet.get(i);
       FileUtil.delete(Config.getContextRealPath() + Alias + image.getPath() + image.getSrcFileName());
       FileUtil.delete(Config.getContextRealPath() + Alias + image.getPath() + "s_" + image.getFileName());
       int count = (int)image.getCount();
       for (int j = 1; j <= count; ++j) {
         FileUtil.delete(Config.getContextRealPath() + Alias + image.getPath() + j + "_" + image.getFileName());
       }
     }
 
     trans.add(ImageSet, 5);
     trans.add(ImageRelaSet, 5);
     trans.add(
       new QueryBuilder("update zccatalog set total = (select count(*) from zcimage where catalogID =?) where ID =?", ImageSet
       .get(0).getCatalogID(), ImageSet.get(0).getCatalogID()));
     if (trans.commit())
     {
       Publisher p = new Publisher();
       for (int i = 0; i < ImageSet.size(); ++i) {
         p.deletePubishedFile(ImageSet.get(i));
       }
       this.Response.setLogInfo(1, "删除图片成功");
     } else {
       this.Response.setLogInfo(0, "删除图片失败");
     }
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.cms.resource.Image
 * JD-Core Version:    0.5.3
 */