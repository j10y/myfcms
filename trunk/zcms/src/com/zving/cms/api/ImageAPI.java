 package com.zving.cms.api;
 
 import com.zving.cms.datachannel.Deploy;
 import com.zving.cms.pub.CatalogUtil;
 import com.zving.cms.pub.SiteUtil;
 import com.zving.framework.Config;
 import com.zving.framework.User;
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.data.Transaction;
 import com.zving.framework.orm.Schema;
 import com.zving.framework.utility.FileUtil;
 import com.zving.framework.utility.NumberUtil;
 import com.zving.platform.pub.ImageUtilEx;
 import com.zving.platform.pub.NoUtil;
 import com.zving.schema.ZCImageSchema;
 import java.io.File;
 import java.io.PrintStream;
 import java.util.ArrayList;
 import java.util.Date;
 
 public class ImageAPI
   implements APIInterface
 {
   private boolean isCreateSchema = false;
   private String ImageHtml;
   private ZCImageSchema image;
 
   public static void main(String[] args)
   {
     User u = new User();
     User.setCurrent(u);
     User.setUserName("admin");
     User.setBranchInnerCode("86");
 
     ImageAPI image = new ImageAPI();
     image.setFileName("i:/1.jpg");
     image.setCatalogID(5725L);
     String filePath = image.getImageHtml();
     System.out.println(filePath);
     image.insert();
 
     image = new ImageAPI();
     image.setFileName("i:/2.jpg");
     image.setCatalogID(6038L);
     image.insert();
   }
 
   public ImageAPI() {
     this.image = new ZCImageSchema();
   }
 
   public ImageAPI(ZCImageSchema image) {
     this.image = image;
   }
 
   public void setFileName(String fileName) {
     this.image.setFileName(fileName);
   }
 
   public void setCatalogID(long catalogID) {
     this.image.setCatalogID(catalogID);
   }
 
   public String getImageHtml()
   {
     if (!(this.isCreateSchema)) {
       createSchema();
     }
     return this.ImageHtml;
   }
 
   private void createSchema() {
     if (!(this.isCreateSchema)) {
       String fileName = this.image.getFileName();
       fileName = fileName.replaceAll("\\\\", "/");
       String name = fileName.substring(fileName.lastIndexOf("/") + 1, fileName.lastIndexOf("."));
       this.image.setFileName(name);
       String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
 
       long imageID = NoUtil.getMaxID("DocID");
       this.image.setID(imageID);
       this.image.setName(name);
       this.image.setOldName(this.image.getName());
       if (this.image.getCatalogID() == 0L) {
         System.out.println("必须CatalogID不能为空");
         return;
       }
       this.image.setSiteID(CatalogUtil.getSiteID(this.image.getCatalogID()));
       String innerCode = new QueryBuilder("select innercode from zccatalog where id=?", this.image.getCatalogID())
         .executeString();
       this.image.setCatalogInnerCode(innerCode);
       this.image.setPath("upload/Image/" + CatalogUtil.getPath(this.image.getCatalogID()));
       this.image.setFileName(imageID + NumberUtil.getRandomInt(10000) + ".jpg");
       this.image.setSrcFileName(imageID + NumberUtil.getRandomInt(10000) + ".jpg");
       this.image.setSuffix(suffix);
       this.image.setWidth(0L);
       this.image.setHeight(0L);
       this.image.setCount(2L);
       this.image.setAddTime(new Date());
       this.image.setAddUser("admin");
       this.ImageHtml = ".." + Config.getValue("UploadDir") + SiteUtil.getAlias(this.image.getSiteID()) + "/" + 
         this.image.getPath() + this.image.getSrcFileName();
       this.isCreateSchema = true;
       dealImageFile(fileName);
     }
   }
 
   public long insert() {
     Transaction trans = new Transaction();
     if (insert(trans) > 0L) {
       if (trans.commit()) {
         return 1L;
       }
       return -1L;
     }
     return -1L;
   }
 
   public long insert(Transaction trans)
   {
     if (!(this.isCreateSchema)) {
       createSchema();
     }
     trans.add(this.image, 1);
     trans
       .add(new QueryBuilder(
       "update zccatalog set total = (select count(*) from zcimage where catalogID = zccatalog.ID) where type=? and id =?", 
       4L, this.image.getID()));
     return 1L;
   }
 
   private boolean dealImageFile(String srcFileName) {
     String dir = Config.getContextRealPath() + Config.getValue("UploadDir") + SiteUtil.getAlias(this.image.getSiteID()) + 
       "/" + this.image.getPath();
     new File(dir).mkdirs();
     if (FileUtil.copy(srcFileName, dir + this.image.getSrcFileName())) {
       try {
         ArrayList imageList = ImageUtilEx.afterUploadImage(this.image, dir);
 
         Deploy d = new Deploy();
         d.addJobs(this.image.getSiteID(), imageList);
         return true;
       } catch (NumberFormatException e) {
         e.printStackTrace();
         return false;
       } catch (Exception e) {
         e.printStackTrace();
         return false;
       }
     }
     return false;
   }
 
   public boolean delete() {
     return false;
   }
 
   public boolean setSchema(Schema schema) {
     this.image = ((ZCImageSchema)schema);
     return false;
   }
 
   public boolean update() {
     return false;
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.cms.api.ImageAPI
 * JD-Core Version:    0.5.3
 */