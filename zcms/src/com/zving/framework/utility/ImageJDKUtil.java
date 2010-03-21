 package com.zving.framework.utility;
 
 import com.sun.image.codec.jpeg.JPEGCodec;
 import com.sun.image.codec.jpeg.JPEGEncodeParam;
 import com.sun.image.codec.jpeg.JPEGImageEncoder;
 import java.awt.Color;
 import java.awt.Dimension;
 import java.awt.Font;
 import java.awt.Graphics;
 import java.awt.Graphics2D;
 import java.awt.Image;
 import java.awt.RenderingHints;
 import java.awt.Toolkit;
 import java.awt.color.ColorSpace;
 import java.awt.geom.AffineTransform;
 import java.awt.image.AffineTransformOp;
 import java.awt.image.BufferedImage;
 import java.awt.image.ColorConvertOp;
 import java.awt.image.CropImageFilter;
 import java.awt.image.FilteredImageSource;
 import java.awt.image.ImageFilter;
 import java.io.File;
 import java.io.FileOutputStream;
 import java.io.IOException;
 import java.io.PrintStream;
 import javax.imageio.ImageIO;
 
 public class ImageJDKUtil
 {
   public static BufferedImage scaleRate(BufferedImage srcImage, double rate)
   {
     return scaleRate(srcImage, rate, rate, null);
   }
 
   public static BufferedImage scaleRate(BufferedImage srcImage, int width, int height)
   {
     double w = srcImage.getWidth();
     double h = srcImage.getHeight();
     if ((w < width) && (h < height)) {
       return srcImage;
     }
     if (height == 0) {
       if (w <= width) {
         return srcImage;
       }
       return scaleRate(srcImage, width / w, width / w, null);
     }
     if (width == 0) {
       if (h <= height) {
         return srcImage;
       }
       return scaleRate(srcImage, height / h, height / h, null);
     }
 
     if (w / h > width / height) {
       return scaleRate(srcImage, width / w, width / w, null);
     }
     return scaleRate(srcImage, height / h, height / h, null);
   }
 
   public static BufferedImage gray(BufferedImage srcImage)
   {
     BufferedImage dstImage = new BufferedImage(srcImage.getWidth(), srcImage.getHeight(), srcImage.getType());
     Graphics2D g2 = dstImage.createGraphics();
     RenderingHints hints = g2.getRenderingHints();
     g2.dispose();
     ColorSpace grayCS = ColorSpace.getInstance(1003);
     ColorConvertOp colorConvertOp = new ColorConvertOp(grayCS, hints);
     colorConvertOp.filter(srcImage, dstImage);
     return dstImage;
   }
 
   public static BufferedImage scaleRate(BufferedImage srcImage, double xscale, double yscale, RenderingHints hints)
   {
     int width = (int)(srcImage.getWidth() * xscale);
     int height = (int)(srcImage.getHeight() * yscale);
     BufferedImage image = new BufferedImage(width, height, 1);
     image.getGraphics()
       .drawImage(srcImage.getScaledInstance(width, height, 16), 0, 0, null);
     return image;
   }
 
   public static BufferedImage scaleFixed(BufferedImage srcImage, int width, int height, boolean changeRate)
   {
     if (changeRate) {
       int srcWidth = srcImage.getWidth();
       int srcHeight = srcImage.getHeight();
       int w = 0;
       int h = 0;
       double wScale = srcWidth * 1.0D / width * 1.0D;
       double hScale = srcHeight * 1.0D / height * 1.0D;
 
       if (wScale > hScale) {
         int srcWidth2 = (int)(width * hScale);
         w = (srcWidth - srcWidth2) / 2;
         srcWidth = srcWidth2;
         h = 0;
       } else {
         int srcHeight2 = (int)(height * wScale);
         h = (srcHeight - srcHeight2) / 2;
         srcHeight = srcHeight2;
         w = 0;
       }
 
       ImageFilter cropFilter = new CropImageFilter(w, h, srcWidth, srcHeight);
       Image img = Toolkit.getDefaultToolkit().createImage(
         new FilteredImageSource(srcImage.getSource(), cropFilter));
       BufferedImage bi = new BufferedImage(srcWidth, srcHeight, 1);
       bi.getGraphics().drawImage(img, 0, 0, null);
       return scaleRate(bi, width, height);
     }
     BufferedImage bi = new BufferedImage(width, height, 1);
     bi.getGraphics().drawImage(srcImage.getScaledInstance(width, height, 16), 0, 0, 
       null);
     return bi;
   }
 
   public static void scaleFixedImageFile(String srcFile, String destFile, int width, int height) throws IOException
   {
     scaleFixedImageFile(srcFile, destFile, width, height, true);
   }
 
   public static void scaleFixedImageFile(String srcFile, String destFile, int width, int height, boolean changeRate)
     throws IOException
   {
     File f = new File(srcFile);
     try {
       BufferedImage image = ImageIO.read(f);
       BufferedImage newImage = scaleFixed(image, width, height, changeRate);
       writeImageFile(destFile, newImage);
     } catch (IOException e) {
       e.printStackTrace();
     }
   }
 
   public static void main(String[] args) throws IOException {
     scaleFixedImageFile("e:/3.jpg", "e:/4.jpg", 300, 300);
   }
 
   public static void scaleRateImageFile(String srcFile, String destFile, int width, int height)
     throws IOException
   {
     File f = new File(srcFile);
     try {
       BufferedImage image = ImageIO.read(f);
       BufferedImage newImage = scaleRate(image, width, height);
       writeImageFile(destFile, newImage);
     } catch (IOException e) {
       e.printStackTrace();
     }
   }
 
   public static void scaleRateImageFile(File srcFile, String destFile, int width, int height)
     throws IOException
   {
     File f = srcFile;
     try {
       BufferedImage image = ImageIO.read(f);
       BufferedImage newImage = scaleRate(image, width, height);
       writeImageFile(destFile, newImage);
     } catch (IOException e) {
       e.printStackTrace();
     }
   }
 
   public static void scaleRateImageFile(String srcFile, String destFile, double rate)
     throws IOException
   {
     File f = new File(srcFile);
     try {
       BufferedImage image = ImageIO.read(f);
       BufferedImage newImage = scaleRate(image, rate);
       writeImageFile(destFile, newImage);
     } catch (IOException e) {
       e.printStackTrace();
     }
   }
 
   public static void scaleRateImageFile(File srcFile, String destFile, double rate)
     throws IOException
   {
     File f = srcFile;
     try {
       BufferedImage image = ImageIO.read(f);
       BufferedImage newImage = scaleRate(image, rate);
       writeImageFile(destFile, newImage);
     } catch (IOException e) {
       e.printStackTrace();
     }
   }
 
   public static void grayImageFile(String srcFile, String destFile)
     throws IOException
   {
     writeImageFile(destFile, gray(ImageIO.read(new File(srcFile))));
   }
 
   public static void writeImageFile(String fileName, BufferedImage image) throws IOException {
     FileOutputStream fos = new FileOutputStream(fileName);
     if (fileName.toLowerCase().endsWith(".gif")) {
       GIFEncoder gif = new GIFEncoder(image, fos);
       gif.encode();
     }
     if ((fileName.toLowerCase().endsWith(".jpg")) || (fileName.toLowerCase().endsWith(".jpeg"))) {
       JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(fos);
       JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(image);
       param.setQuality(1.0F, false);
       encoder.encode(image);
     }
     fos.flush();
     fos.close();
   }
 
   public static Dimension getDimension(String fileName) throws IOException {
     File f = new File(fileName);
     return getDimension(f);
   }
 
   public static Dimension getDimension(File f) throws IOException {
     BufferedImage image = ImageIO.read(f);
     if (image == null) {
       return new Dimension(0, 0);
     }
     return new Dimension(image.getWidth(), image.getHeight());
   }
 
   public static final void pressImage(String targetImg, String pressImg, int position)
   {
     try
     {
       File file = new File(targetImg);
       Image src = ImageIO.read(file);
       int wideth = src.getWidth(null);
       int height = src.getHeight(null);
       if ((wideth <= 300) && (height <= 300)) {
         return;
       }
       BufferedImage image = new BufferedImage(wideth, height, 1);
       Graphics g = image.createGraphics();
       g.drawImage(src, 0, 0, wideth, height, null);
 
       File file_press = new File(pressImg);
       if (!(file_press.exists())) {
         LogUtil.warn("水印图片不存在：" + pressImg);
         return;
       }
       Image src_press = ImageIO.read(file_press);
       int wideth_press = src_press.getWidth(null);
       int height_press = src_press.getHeight(null);
 
       int x = 0; int y = 0;
       int bianju = 20;
       int[][][] positions = { 
         { { bianju, bianju }, { (wideth - wideth_press) / 2, bianju }, 
         { wideth - wideth_press - bianju, bianju } }, 
         { { bianju, (height - height_press) / 2 }, 
         { (wideth - wideth_press) / 2, (height - height_press) / 2 }, 
         { wideth - wideth_press - bianju, (height - height_press) / 2 } }, 
         { { bianju, height - height_press - bianju }, 
         { (wideth - wideth_press) / 2, height - height_press - bianju }, 
         { wideth - wideth_press - bianju, height - height_press - bianju } } };
       if (position == 0) {
         position = NumberUtil.getRandomInt(9) + 1;
       }
       x = positions[((position - 1) / 3)][((position - 1) % 3)][0];
       y = positions[((position - 1) / 3)][((position - 1) % 3)][1];
 
       g.drawImage(src_press, x, y, wideth_press, height_press, null);
 
       g.dispose();
       FileOutputStream out = new FileOutputStream(targetImg);
       JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
       encoder.encode(image);
       out.close();
     } catch (Exception e) {
       e.printStackTrace();
     }
   }
 
   public static final void pressImage(String targetImg, String pressImg)
   {
     pressImage(targetImg, pressImg, 9);
   }
 
   public static void pressText(String targetImg, String pressText, int color, int fontSize, int position) {
     pressText(targetImg, pressText, "宋体", 1, color, fontSize, position);
   }
 
   public static void pressText(String targetImg, String pressText, String fontName, int fontStyle, int color, int fontSize, int position)
   {
     try
     {
       File _file = new File(targetImg);
       Image src = ImageIO.read(_file);
       int wideth = src.getWidth(null);
       int height = src.getHeight(null);
       if ((wideth <= 300) && (height <= 300)) {
         return;
       }
       BufferedImage image = new BufferedImage(wideth, height, 1);
       Graphics g = image.createGraphics();
       g.drawImage(src, 0, 0, wideth, height, null);
       g.setColor(new Color(color));
       g.setFont(new Font(fontName, fontStyle, fontSize));
       int x;
       int y;
       if (position == 0) {
         x = NumberUtil.getRandomInt(wideth);
         x = (x < fontSize * 2) ? fontSize * 2 : x;
         y = NumberUtil.getRandomInt(height);
         y = (y < fontSize * 2 / 2) ? fontSize * 2 : y;
       } else {
         x = wideth * (position - 1) % 3 / 3 + fontSize * 2;
         y = height * (position - 1) / 3 / 3 + fontSize * 2;
       }
       if (x > wideth - (fontSize * pressText.length() * 4 / 3)) {
         x = wideth - (fontSize * pressText.length() * 4 / 3);
       }
       if (y > height - fontSize) {
         y = height - fontSize;
       }
 
       g.drawString(pressText, x, y);
       g.dispose();
       FileOutputStream out = new FileOutputStream(targetImg);
       JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
       encoder.encode(image);
       out.close();
     } catch (Exception e) {
       System.out.println(e);
     }
   }
 
   public static void transform(File src, File dest) {
     transform(src, dest, 1600);
   }
 
   public static void transform(File src, File dest, int nw) {
     try {
       AffineTransform transform = new AffineTransform();
       BufferedImage bis = ImageIO.read(src);
       int w = bis.getWidth();
       int h = bis.getHeight();
       int nh = nw * h / w;
       double sx = nw / w;
       double sy = nh / h;
       transform.setToScale(sx, sy);
       AffineTransformOp ato = new AffineTransformOp(transform, null);
       BufferedImage bid = new BufferedImage(nw, nh, 5);
       ato.filter(bis, bid);
       ImageIO.write(bid, "jpg", dest);
     }
     catch (Exception e) {
       e.printStackTrace();
     }
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.framework.utility.ImageJDKUtil
 * JD-Core Version:    0.5.3
 */