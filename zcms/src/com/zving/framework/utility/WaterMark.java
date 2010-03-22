 package com.zving.framework.utility;
 
 import com.sun.image.codec.jpeg.JPEGCodec;
 import com.sun.image.codec.jpeg.JPEGImageEncoder;
 import java.awt.Color;
 import java.awt.Font;
 import java.awt.Graphics;
 import java.awt.Image;
 import java.awt.image.BufferedImage;
 import java.io.File;
 import java.io.FileOutputStream;
 import javax.imageio.ImageIO;
 
 public class WaterMark
 {
   private String imageFile;
   private String fontName;
   private Color fontColor;
   private int fontSize;
   public int imageHeight;
   public int imageWidth;
 
   public WaterMark()
   {
     this.imageFile = "";
     this.fontName = "宋体";
     this.fontColor = Color.RED;
     this.fontSize = 14;
     this.imageHeight = 0;
     this.imageWidth = 0;
   }
 
   public void setFont(String fontName, int fontColor, int fontSize) {
     this.fontName = fontName;
     this.fontColor = new Color(fontColor);
     this.fontSize = fontSize;
   }
 
   public void openFile(String imgf) {
     this.imageFile = imgf;
     if (this.imageFile.equals(""))
       return;
     try {
       File _file = new File(this.imageFile);
       Image src = ImageIO.read(_file);
       int wideth = src.getWidth(null);
       int height = src.getHeight(null);
       this.imageWidth = wideth;
       this.imageHeight = height;
     } catch (Exception e) {
       e.printStackTrace();
     }
   }
 
   public void draw(String text, int position, int ws) throws Exception {
     if (this.imageFile.equals(""))
       return;
     try {
       File img = new File(this.imageFile);
       Image src = ImageIO.read(img);
       int width = src.getWidth(null);
       int height = src.getHeight(null);
       int left = 0;
       int top = 0;
       BufferedImage image = new BufferedImage(width, height, 1);
       Graphics g = image.createGraphics();
 
       g.drawImage(src, 0, 0, width, height, null);
       g.setColor(this.fontColor);
       g.setFont(new Font(this.fontName, 0, this.fontSize));
       int strlen = text.getBytes().length / 2;
       if (ws < 0) {
         ws = 8;
       }
 
       switch (position)
       {
       case 1:
         left = ws;
         top = this.fontSize + ws;
         break;
       case 2:
         left = width - (strlen * this.fontSize) - ws;
         top = this.fontSize + ws;
         break;
       case 3:
         left = (width - (strlen * this.fontSize)) / 2;
         top = (height - this.fontSize) / 2 + ws;
         break;
       case 4:
         left = ws;
         top = height - ws;
         break;
       case 5:
         left = width - (strlen * this.fontSize) - ws;
         top = height - ws;
         break;
       default:
         left = width - (strlen * this.fontSize) - ws;
         top = height - ws;
       }
 
       if (left < 0)
         left = 0;
       if (top < 0)
         top = 0;
       g.drawString(text, left, top);
       g.dispose();
       FileOutputStream out = new FileOutputStream(this.imageFile);
       JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
       encoder.encode(image);
       out.close();
     } catch (Exception e) {
       e.printStackTrace();
     }
   }
 
   public static void main(String[] args) {
     WaterMark waterMark = new WaterMark();
     waterMark.openFile("f:/test.jpg");
     try {
       waterMark.draw("泽元软件", 5, 8);
     }
     catch (Exception e) {
       e.printStackTrace();
     }
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.framework.utility.WaterMark
 * JD-Core Version:    0.5.3
 */