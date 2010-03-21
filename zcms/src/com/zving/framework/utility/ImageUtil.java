 package com.zving.framework.utility;
 
 import com.zving.framework.Config;
 import java.awt.Dimension;
 import java.io.IOException;
 import magick.MagickException;
 
 public class ImageUtil
 {
   public static void main(String[] args)
     throws IOException, MagickException
   {
   }
 
   public static boolean existsImageMagick()
   {
     return ("1".equals(Config.getValue("ImageLibType")));
   }
 
   public static Dimension getDimension(String fileName)
     throws MagickException, IOException
   {
     if (existsImageMagick()) {
       return ImageMagickUtil.getDimension(fileName);
     }
 
     return ImageJDKUtil.getDimension(fileName);
   }
 
   public static void scaleRateImageFile(String fromFileName, String toFileName, int toWidth, int toHeight) throws IOException, MagickException
   {
     if (existsImageMagick()) {
       ImageMagickUtil.scaleRateImageFile(fromFileName, toFileName, toWidth, toHeight);
     }
     else
       ImageJDKUtil.scaleRateImageFile(fromFileName, toFileName, toWidth, toHeight);
   }
 
   public static void scaleRateImageFile(String fromFileName, String toFileName, double rate) throws MagickException, IOException
   {
     if (existsImageMagick()) {
       ImageMagickUtil.scaleRateImageFile(fromFileName, toFileName, rate);
     }
     else
       ImageJDKUtil.scaleRateImageFile(fromFileName, toFileName, rate);
   }
 
   public static void scaleFixedImageFile(String fromFileName, String toFileName, int toWidth, int toHeight) throws MagickException, IOException
   {
     if (existsImageMagick()) {
       ImageMagickUtil.scaleFixedImageFile(fromFileName, toFileName, toWidth, toHeight);
     }
     else
       ImageJDKUtil.scaleFixedImageFile(fromFileName, toFileName, toWidth, toHeight);
   }
 
   public static void pressText(String filePath, String pressText, int color, int fontSize, int position)
   {
     if (existsImageMagick()) {
       ImageMagickUtil.pressText(filePath, pressText, color, fontSize, position);
     }
     else
       ImageJDKUtil.pressText(filePath, pressText, color, fontSize, position);
   }
 
   public static final void pressImage(String targetImg, String pressImg, int position)
   {
     if (existsImageMagick()) {
       ImageMagickUtil.pressImage(targetImg, pressImg, position);
     }
     else
       ImageJDKUtil.pressImage(targetImg, pressImg, position);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.framework.utility.ImageUtil
 * JD-Core Version:    0.5.3
 */