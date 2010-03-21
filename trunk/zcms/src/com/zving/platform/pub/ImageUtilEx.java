 package com.zving.platform.pub;
 
 import com.zving.cms.pub.SiteUtil;
 import com.zving.cms.resource.ConfigImageLib;
 import com.zving.framework.Config;
 import com.zving.framework.utility.FileUtil;
 import com.zving.framework.utility.ImageUtil;
 import com.zving.framework.utility.Mapx;
 import com.zving.platform.Application;
 import com.zving.schema.ZCImageSchema;
 import java.awt.Dimension;
 import java.io.PrintStream;
 import java.util.ArrayList;
 
 public class ImageUtilEx
 {
   public static ArrayList afterUploadImage(ZCImageSchema image, String absolutePath)
     throws NumberFormatException, Exception
   {
     return afterUploadImage(image, absolutePath, null);
   }
 
   public static ArrayList afterUploadImage(ZCImageSchema image, String absolutePath, Mapx fields) throws NumberFormatException, Exception
   {
     long t = System.currentTimeMillis();
     ArrayList imageList = new ArrayList();
     String imageFile = absolutePath + image.getSrcFileName();
 
     Dimension dim = ImageUtil.getDimension(imageFile);
 
     image.setWidth((int)dim.getWidth());
     image.setHeight((int)dim.getHeight());
 
     Mapx configFields = new Mapx();
     configFields.putAll(ConfigImageLib.getImageLibConfig(image.getSiteID()));
     if (fields != null) {
       configFields.putAll(fields);
     }
 
     int count = Integer.parseInt(configFields.get("Count").toString());
 
     for (int i = 1; i <= count; ++i) {
       if ((configFields == null) || ("1".equals(configFields.get("HasAbbrImage" + i)))) {
         String SizeType = (String)configFields.get("SizeType" + i);
         int Width = Integer.parseInt((String)configFields.get("Width" + i));
         int Height = Integer.parseInt((String)configFields.get("Height" + i));
         if ("1".equals(SizeType))
           Height = 0;
         else if ("2".equals(SizeType)) {
           Width = 0;
         }
         String abbrImage = absolutePath + i + "_" + image.getFileName();
 
         if ("3".equals(SizeType))
           ImageUtil.scaleFixedImageFile(imageFile, abbrImage, Width, Height);
         else {
           ImageUtil.scaleRateImageFile(imageFile, abbrImage, Width, Height);
         }
         image.setCount(count);
 
         if (i == 1)
         {
           String thumbFileName = absolutePath + "s_" + image.getFileName();
           ImageUtil.scaleRateImageFile(absolutePath + "1_" + image.getFileName(), thumbFileName, 120, 120);
           imageList.add(thumbFileName);
         }
 
         if ("1".equals(configFields.get("HasWaterMark" + i))) {
           if ("Image".equals(configFields.get("WaterMarkType" + i)))
             ImageUtil.pressImage(abbrImage, Config.getContextRealPath() + Config.getValue("UploadDir") + 
               "/" + SiteUtil.getAlias(Application.getCurrentSiteID()) + "/" + 
               configFields.get(new StringBuffer("Image").append(i).toString()), Integer.parseInt(configFields.get("Position" + i)
               .toString()));
           else {
             ImageUtil.pressText(abbrImage, (String)configFields.get("Text" + i), 
               Integer.parseInt(configFields.get("FontColor" + i).toString()), Integer.parseInt(configFields
               .get("FontSize" + i).toString()), Integer.parseInt(configFields.get("Position" + i)
               .toString()));
           }
         }
 
         imageList.add(abbrImage);
       }
 
     }
 
     if (image.getCount() == 0L) {
       FileUtil.copy(imageFile, absolutePath + "1_" + image.getFileName());
       image.setCount(1L);
       imageList.add(absolutePath + "1_" + image.getFileName());
 
       String thumbFileName = absolutePath + "s_" + image.getFileName();
       ImageUtil.scaleRateImageFile(absolutePath + "1_" + image.getFileName(), thumbFileName, 120, 120);
       imageList.add(thumbFileName);
     }
 
     if ((configFields == null) || ("1".equals(configFields.get("HasWaterMark")))) {
       if ("Image".equals(configFields.get("WaterMarkType")))
         ImageUtil.pressImage(absolutePath + image.getSrcFileName(), Config.getContextRealPath() + 
           Config.getValue("UploadDir") + "/" + SiteUtil.getAlias(Application.getCurrentSiteID()) + "/" + 
           configFields.get("Image"), Integer.parseInt(configFields.get("Position").toString()));
       else {
         ImageUtil.pressText(absolutePath + image.getSrcFileName(), (String)configFields.get("Text"), 
           Integer.parseInt(configFields.get("FontColor").toString()), Integer.parseInt(configFields.get(
           "FontSize").toString()), Integer.parseInt(configFields.get("Position").toString()));
       }
 
     }
 
     System.out.println("上传图片处理花费：" + (System.currentTimeMillis() - t) + "毫秒");
     return imageList;
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.platform.pub.ImageUtilEx
 * JD-Core Version:    0.5.3
 */