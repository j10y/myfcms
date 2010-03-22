 package com.zving.framework.utility;
 
 import java.awt.Color;
 import java.awt.Font;
 import java.awt.FontMetrics;
 import java.awt.Graphics;
 import java.awt.image.BufferedImage;
 import java.io.ByteArrayOutputStream;
 import java.io.File;
 import java.io.FileOutputStream;
 import java.io.IOException;
 import java.io.OutputStream;
 import java.io.PrintStream;
 import javax.imageio.ImageIO;
 import org.apache.commons.logging.Log;
 
 public class BarCode
 {
   public static String FORMAT_JPEG = "jpg";
 
   public static String FORMAT_GIF = "gif";
 
   public static String FORMAT_PNG = "png";
 
   public static String FORMAT_BMP = "bmp";
 
   public static int TYPE_CODE39 = 1;
 
   private BufferedImage image = null;
 
   private String codeStr = "";
 
   private String codeBinary = "";
 
   private String m_fileFormat = FORMAT_JPEG;
 
   private int codeType = TYPE_CODE39;
 
   private boolean isTextVisable = true;
 
   private String bgColor = "#FFFFFF";
 
   private String fgColor = "000000";
 
   private int xMargin = 10;
 
   private int yMargin = 10;
 
   private int barHeight = 40;
 
   private int barWidth = 1;
 
   private int barRatio = 3;
 
   public BarCode(String code) {
     this.codeStr = code;
   }
 
   public BufferedImage getImage()
   {
     generatedImage();
     return this.image;
   }
 
   public void getOutputStream(OutputStream os) throws IOException
   {
     generatedImage();
     if (this.m_fileFormat.equals(FORMAT_GIF)) {
       GIFEncoder gif = new GIFEncoder(this.image, os);
       gif.encode();
     } else {
       ImageIO.write(this.image, this.m_fileFormat, os);
     }
   }
 
   public void writeToFile(String fileName) throws IOException
   {
     generatedImage();
     File f = new File(fileName);
     OutputStream os = new FileOutputStream(f);
     GIFEncoder gif = new GIFEncoder(this.image, os);
     gif.encode();
     os.close();
   }
 
   public byte[] getBytes() throws IOException
   {
     generatedImage();
     ByteArrayOutputStream bos = new ByteArrayOutputStream();
     GIFEncoder gif = new GIFEncoder(this.image, bos);
     gif.encode();
     return bos.toByteArray();
   }
 
   public void generatedImage()
   {
     int w_Start = this.xMargin;
     int h_Start = this.yMargin;
     if (this.codeType == TYPE_CODE39) {
       this.codeBinary = Code39.transferCode(this.codeStr);
     }
     int barWidthTotal = 0;
 
     for (int i = 0; i < this.codeBinary.length(); ++i) {
       if (this.codeBinary.charAt(i) == '1')
         barWidthTotal += this.barRatio * this.barWidth;
       else {
         barWidthTotal += this.barWidth;
       }
     }
     int imageWidth = this.xMargin * 2 + barWidthTotal;
     int imageHeight = h_Start + this.barHeight + ((h_Start > 15) ? h_Start : 15);
     this.image = new BufferedImage(imageWidth, imageHeight, 1);
     setImageBgColor(this.image, this.bgColor);
     Graphics g = this.image.getGraphics();
     Font font = new Font(null, 0, 11);
     g.setFont(font);
     g.setColor(Color.decode(this.fgColor));
     for (int i = 0; i < this.codeBinary.length(); ++i) {
       if (this.codeBinary.charAt(i) == '1') {
         if (i % 2 == 0) {
           g.fillRect(w_Start, h_Start, this.barRatio * this.barWidth, this.barHeight);
         }
         w_Start += this.barRatio * this.barWidth;
       } else {
         if (i % 2 == 0) {
           g.fillRect(w_Start, h_Start, this.barWidth, this.barHeight);
         }
         w_Start += this.barWidth;
       }
     }
     if (this.isTextVisable) {
       FontMetrics fonM = g.getFontMetrics();
       int yFont = fonM.getAscent() + fonM.getDescent();
       int xFont = fonM.stringWidth(this.codeStr);
       g.drawString(this.codeStr.toUpperCase(), imageWidth / 2 - (xFont / 2), h_Start + yFont - 3 + this.barHeight);
     }
   }
 
   public void setBgColor(String str)
   {
     this.bgColor = str;
   }
 
   public void setCodeType(int type)
   {
     this.codeType = type;
   }
 
   public void setFormatType(String str)
   {
     this.m_fileFormat = str;
   }
 
   public void setForeColor(String str)
   {
     this.fgColor = str;
   }
 
   public void setTextVisable(boolean bFlag)
   {
     this.isTextVisable = bFlag;
   }
 
   public void setXMargin(int x)
   {
     this.xMargin = x;
   }
 
   public void setYMargin(int y)
   {
     this.yMargin = y;
   }
 
   public void setBarWidth(int bw)
   {
     this.barWidth = bw;
   }
 
   public void setBarHeight(int bh)
   {
     this.barHeight = bh;
   }
 
   public void setBarRatio(int br)
   {
     this.barRatio = br;
   }
 
   private static void setImageBgColor(BufferedImage image, String strColor)
   {
     Graphics g = image.getGraphics();
     String t_Str = new String(strColor);
     Color t_Color = g.getColor();
     if (!(t_Str.startsWith("#"))) {
       t_Str = "#" + t_Str;
     }
     if (t_Str.length() != 7) {
       LogUtil.getLogger().warn("BarCode:错误的颜色值" + t_Str);
       return;
     }
     g.setColor(Color.decode(t_Str));
     g.fillRect(0, 0, image.getWidth(), image.getHeight());
     g.setColor(t_Color);
   }
 
   public static void main(String[] args)
   {
     String code = "SINO2345";
     System.out.println(Code39.transferCode(code));
     BarCode barcode = new BarCode(code);
     barcode.setFormatType(FORMAT_GIF);
     try
     {
       System.out.println(barcode.getBytes().length);
     } catch (IOException ex) {
       System.out.println("条形码工具BarCode:写文件错误");
     }
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.framework.utility.BarCode
 * JD-Core Version:    0.5.3
 */