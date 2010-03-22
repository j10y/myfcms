 package com.zving.platform.pub;
 
 import com.zving.framework.utility.FileUtil;
 import com.zving.framework.utility.ImageUtil;
 import com.zving.framework.utility.VideoUtil;
 import com.zving.schema.ZCVideoSchema;
 
 public class VideoUtilEx
 {
   public static boolean afterUploadVideo(ZCVideoSchema video, String AbsolutePath, boolean hasImage)
     throws NumberFormatException, Exception
   {
     int[] WidthHeight = VideoUtil.getWidthHeight(AbsolutePath + video.getSrcFileName());
     video.setWidth(WidthHeight[0]);
     video.setHeight(WidthHeight[1]);
     if ("flv".equals(video.getSuffix().toLowerCase()))
       FileUtil.copy(AbsolutePath + video.getSrcFileName(), AbsolutePath + "0_" + video.getFileName());
     else {
       VideoUtil.convert2Flv(AbsolutePath + video.getSrcFileName(), AbsolutePath + "0_" + video.getFileName());
     }
 
     video.setDuration(VideoUtil.getDuration(AbsolutePath + "0_" + video.getFileName()));
     video.setCount(1L);
 
     if (hasImage)
       ImageUtil.scaleRateImageFile(AbsolutePath + video.getImageName(), AbsolutePath + video.getImageName(), 240, 240);
     else {
       VideoUtil.captureDefaultImage(AbsolutePath + "0_" + video.getFileName(), AbsolutePath + video.getImageName(), (int)video.getDuration());
     }
     return true;
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.platform.pub.VideoUtilEx
 * JD-Core Version:    0.5.3
 */