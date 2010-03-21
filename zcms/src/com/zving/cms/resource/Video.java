 package com.zving.cms.resource;
 
 import com.zving.cms.pub.CatalogUtil;
 import com.zving.cms.pub.SiteUtil;
 import com.zving.framework.Config;
 import com.zving.framework.Page;
 import com.zving.framework.ResponseImpl;
 import com.zving.framework.User;
 import com.zving.framework.data.DataTable;
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.data.Transaction;
 import com.zving.framework.utility.FileUtil;
 import com.zving.framework.utility.HtmlUtil;
 import com.zving.framework.utility.Mapx;
 import com.zving.framework.utility.NumberUtil;
 import com.zving.framework.utility.StringUtil;
 import com.zving.framework.utility.VideoUtil;
 import com.zving.platform.Application;
 import com.zving.platform.pub.NoUtil;
 import com.zving.schema.ZCVideoRelaSchema;
 import com.zving.schema.ZCVideoRelaSet;
 import com.zving.schema.ZCVideoSchema;
 import com.zving.schema.ZCVideoSet;
 import java.io.File;
 import java.util.Date;
 
 public class Video extends Page
 {
   public static Mapx initEditDialog(Mapx params)
   {
     ZCVideoSchema Video = new ZCVideoSchema();
     String Alias = Config.getValue("UploadDir") + "/" + SiteUtil.getAlias(Application.getCurrentSiteID()) + "/";
     Video.setID(Long.parseLong(params.get("ID").toString()));
     Video.fill();
     params = Video.toMapx();
     params.put("Alias", Alias);
     params.put("IsOriginal", HtmlUtil.codeToRadios("IsOriginal", "YesOrNo", Video.getIsOriginal()));
     return params;
   }
 
   public static Mapx initPlayDialog(Mapx params) {
     ZCVideoSchema Video = new ZCVideoSchema();
     String Alias = Config.getValue("UploadDir") + "/" + SiteUtil.getAlias(Application.getCurrentSiteID()) + "/";
     Video.setID(Long.parseLong(params.get("ID").toString()));
     Video.fill();
     String files = "";
     for (int i = 0; i < Video.getCount(); ++i) {
       if (i != 0) {
         files = files + "|";
       }
       files = files + ".." + Alias + Video.getPath() + i + "_" + Video.getFileName();
     }
     Mapx map = Video.toMapx();
     map.put("files", files);
     return map;
   }
 
   public void edit() {
     ZCVideoSchema video = new ZCVideoSchema();
     video.setID(Long.parseLong($V("ID")));
     video.fill();
     video.setValue(this.Request);
     video.setModifyTime(new Date());
     video.setModifyUser(User.getUserName());
     String StartSecond = $V("StartSecond");
     if (StringUtil.isNotEmpty(StartSecond)) {
       StartSecond = StartSecond.trim();
     }
     if (StringUtil.isNotEmpty(StartSecond)) {
       int ss = Integer.parseInt(StartSecond);
       VideoUtil.captureImage(Config.getContextRealPath() + Config.getValue("UploadDir") + "/" + 
         SiteUtil.getAlias(video.getSiteID()) + "/" + video.getPath() + "0_" + 
         video.getFileName(), Config.getContextRealPath() + Config.getValue("UploadDir") + 
         "/" + SiteUtil.getAlias(video.getSiteID()) + "/" + video.getPath() + video.getImageName(), ss);
     }
     if (video.update())
       this.Response.setLogInfo(1, "修改成功!");
     else
       this.Response.setLogInfo(0, "修改失败!");
   }
 
   public static Mapx initUploadDialog(Mapx params)
   {
     params.put("IsOriginal", HtmlUtil.codeToRadios("IsOriginal", "YesOrNo", "N"));
     DataTable dt = new QueryBuilder("select name,id from zccatalog ").executePagedDataTable(100, 0);
     params.put("Who", HtmlUtil.dataTableToOptions(dt));
     return params;
   }
 
   public void transfer() {
     long catalogID = Long.parseLong($V("CatalogID"));
     String ids = $V("IDs");
     if (!(StringUtil.checkID(ids))) {
       this.Response.setStatus(0);
       this.Response.setMessage("传入ID时发生错误!");
       return;
     }
     Transaction trans = new Transaction();
     trans.add(
       new QueryBuilder("update ZCVideo set catalogid=? ,CatalogInnerCode=? where ID in (" + ids + ")", 
       catalogID, CatalogUtil.getInnerCode(catalogID)));
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
     String InnerCode = new QueryBuilder("select InnerCode from zccatalog where ID = ?", catalogID).executeString();
     String newPath = "upload/Video/" + catalogPath;
     File dir = new File(Config.getContextRealPath() + Alias + newPath);
     if (!(dir.exists())) {
       dir.mkdirs();
     }
     String IDs = $V("IDs");
     if (!(StringUtil.checkID(IDs))) {
       this.Response.setStatus(0);
       this.Response.setMessage("传入ID时发生错误!");
       return;
     }
     ZCVideoSet videoSet = new ZCVideoSchema().query(new QueryBuilder(" where ID in (" + IDs + ")"));
     boolean flag = true;
     String msg = "";
     for (int i = 0; i < videoSet.size(); ++i) {
       ZCVideoSchema video = videoSet.get(i);
       String oldPath = Alias + video.getPath();
       String oldFileName = video.getFileName();
       String oldSrcFileName = video.getSrcFileName();
       String oldImageName = video.getImageName();
       video.setID(NoUtil.getMaxID("DocID"));
       video.setCatalogID(catalogID);
       video.setCatalogInnerCode(InnerCode);
       video.setPath(newPath);
       int random = NumberUtil.getRandomInt(10000);
       video.setFileName(video.getID() + random + ".flv");
       video.setImageName(video.getID() + random + ".jpg");
       video.setSrcFileName(video.getID() + NumberUtil.getRandomInt(10000) + "." + video.getSuffix());
 
       if (!(FileUtil.copy(Config.getContextRealPath() + oldPath + oldImageName, Config.getContextRealPath() + Alias + newPath + 
         video.getImageName()))) {
         msg = "复制失败，原因是：'" + video.getName() + "'的视频缩略图复制失败";
         flag = false;
         break;
       }
 
       if (!(FileUtil.copy(Config.getContextRealPath() + oldPath + oldSrcFileName, Config.getContextRealPath() + Alias + newPath + 
         video.getSrcFileName()))) {
         msg = "复制失败，原因是：'" + video.getName() + "'的原文件复制失败";
         flag = false;
         break;
       }
 
       for (int j = 0; j < video.getCount(); ++j) {
         if (FileUtil.copy(Config.getContextRealPath() + oldPath + j + "_" + oldFileName, Config.getContextRealPath() + Alias + newPath + 
           j + "_" + video.getFileName())) continue;
         msg = "复制失败，原因是：'" + video.getName() + "'的flv文件复制失败";
         flag = false;
         break;
       }
 
       if (!(flag)) {
         break;
       }
       video.setAddTime(new Date());
       video.setAddUser(User.getUserName());
     }
     if ((flag) && (videoSet.insert()))
       this.Response.setLogInfo(1, "复制视频成功");
     else
       this.Response.setLogInfo(0, msg);
   }
 
   public void del()
   {
     String IDs = $V("IDs");
     String Alias = Config.getValue("UploadDir") + "/" + SiteUtil.getAlias(Application.getCurrentSiteID()) + "/";
     if (!(StringUtil.checkID(IDs))) {
       this.Response.setStatus(0);
       this.Response.setMessage("传入ID时发生错误!");
       return;
     }
     ZCVideoSet VideoSet = new ZCVideoSchema().query(new QueryBuilder(" where id in (" + IDs + ")"));
     ZCVideoRelaSet VideoRelaSet = new ZCVideoRelaSchema().query(new QueryBuilder(" where id in (" + IDs + ")"));
     for (int i = 0; i < VideoSet.size(); ++i)
     {
       FileUtil.delete(Config.getContextRealPath() + Alias + VideoSet.get(i).getPath() + VideoSet.get(i).getSrcFileName());
 
       FileUtil.delete(Config.getContextRealPath() + Alias + VideoSet.get(i).getPath() + VideoSet.get(i).getImageName());
 
       for (int j = 0; j < VideoSet.get(i).getCount(); ++j) {
         FileUtil.delete(Config.getContextRealPath() + Alias + VideoSet.get(i).getPath() + j + "_" + VideoSet.get(i).getFileName());
       }
     }
     Transaction trans = new Transaction();
     trans.add(VideoSet, 5);
     trans.add(VideoRelaSet, 5);
     trans.add(
       new QueryBuilder("update zccatalog set total = (select count(*) from zcvideo where catalogID = ?) where ID = ?", 
       VideoSet.get(0).getCatalogID(), VideoSet.get(0).getCatalogID()));
     if (trans.commit())
       this.Response.setLogInfo(1, "删除视频成功");
     else
       this.Response.setLogInfo(0, "删除视频失败");
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.cms.resource.Video
 * JD-Core Version:    0.5.3
 */