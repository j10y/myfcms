 package com.zving.framework.utility;
 
 import java.awt.Image;
 import java.awt.image.ColorModel;
 import java.awt.image.ImageConsumer;
 import java.awt.image.ImageProducer;
 import java.io.IOException;
 import java.io.OutputStream;
 import java.util.Hashtable;
 
 abstract class ImageEncoder
   implements ImageConsumer
 {
   protected OutputStream out;
   private ImageProducer producer;
   private int width;
   private int height;
   private int hintflags;
   private boolean started;
   private boolean encoding;
   private IOException iox;
   private static final ColorModel rgbModel = ColorModel.getRGBdefault();
   private boolean accumulate;
   private int[] accumulator;
 
   public ImageEncoder(Image img, OutputStream out)
     throws IOException
   {
     this(img.getSource(), out);
   }
 
   public ImageEncoder(ImageProducer producer, OutputStream out)
     throws IOException
   {
     this.width = -1;
 
     this.height = -1;
 
     this.hintflags = 0;
 
     this.started = false;
 
     this.accumulate = false;
 
     this.producer = producer;
     this.out = out;
   }
 
   abstract void encodeStart(int paramInt1, int paramInt2)
     throws IOException;
 
   abstract void encodePixels(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int[] paramArrayOfInt, int paramInt5, int paramInt6)
     throws IOException;
 
   abstract void encodeDone()
     throws IOException;
 
   public synchronized void encode()
     throws IOException
   {
     this.encoding = true;
     this.iox = null;
     this.producer.startProduction(this);
     while (this.encoding)
       try {
         super.wait();
       }
       catch (InterruptedException localInterruptedException) {
       }
     if (this.iox != null)
       throw this.iox;
   }
 
   private void encodePixelsWrapper(int x, int y, int w, int h, int[] rgbPixels, int off, int scansize)
     throws IOException
   {
     if (!(this.started)) {
       this.started = true;
       encodeStart(this.width, this.height);
       if ((this.hintflags & 0x2) == 0) {
         this.accumulate = true;
         this.accumulator = new int[this.width * this.height];
       }
     }
     if (this.accumulate) {
       for (int row = 0; row < h; ++row)
         System.arraycopy(rgbPixels, row * scansize + off, this.accumulator, (y + row) * this.width + x, w);
     }
     else
       encodePixels(x, y, w, h, rgbPixels, off, scansize);
   }
 
   private void encodeFinish() throws IOException
   {
     if (this.accumulate) {
       encodePixels(0, 0, this.width, this.height, this.accumulator, 0, this.width);
       this.accumulator = null;
       this.accumulate = false;
     }
   }
 
   private synchronized void stop() {
     this.encoding = false;
     super.notifyAll();
   }
 
   public void setDimensions(int width, int height)
   {
     this.width = width;
     this.height = height;
   }
 
   public void setProperties(Hashtable props)
   {
   }
 
   public void setColorModel(ColorModel model)
   {
   }
 
   public void setHints(int hintflags) {
     this.hintflags = hintflags;
   }
 
   public void setPixels(int x, int y, int w, int h, ColorModel model, byte[] pixels, int off, int scansize) {
     int[] rgbPixels = new int[w];
     for (int row = 0; row < h; ++row) {
       int rowOff = off + row * scansize;
       for (int col = 0; col < w; ++col)
         rgbPixels[col] = model.getRGB(pixels[(rowOff + col)] & 0xFF);
       try
       {
         encodePixelsWrapper(x, y + row, w, 1, rgbPixels, 0, w);
       } catch (IOException e) {
         this.iox = e;
         stop();
         return;
       }
     }
   }
 
   public void setPixels(int x, int y, int w, int h, ColorModel model, int[] pixels, int off, int scansize) {
     if (model == rgbModel) {
       try {
         encodePixelsWrapper(x, y, w, h, pixels, off, scansize);
       } catch (IOException e) {
         this.iox = e;
         stop();
         return;
       }
     } else {
       int[] rgbPixels = new int[w];
       for (int row = 0; row < h; ++row) {
         int rowOff = off + row * scansize;
         for (int col = 0; col < w; ++col)
           rgbPixels[col] = model.getRGB(pixels[(rowOff + col)]);
         try
         {
           encodePixelsWrapper(x, y + row, w, 1, rgbPixels, 0, w);
         } catch (IOException e) {
           this.iox = e;
           stop();
           return;
         }
       }
     }
   }
 
   public void imageComplete(int status) {
     this.producer.removeConsumer(this);
     if (status == 4)
       this.iox = new IOException("image aborted");
     else {
       try {
         encodeFinish();
         encodeDone();
       } catch (IOException e) {
         this.iox = e;
       }
     }
     stop();
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.framework.utility.ImageEncoder
 * JD-Core Version:    0.5.3
 */