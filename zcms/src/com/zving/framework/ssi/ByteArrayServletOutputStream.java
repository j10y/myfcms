 package com.zving.framework.ssi;
 
 import java.io.ByteArrayOutputStream;
 import javax.servlet.ServletOutputStream;
 
 public class ByteArrayServletOutputStream extends ServletOutputStream
 {
   protected ByteArrayOutputStream buf = null;
 
   public ByteArrayServletOutputStream()
   {
     this.buf = new ByteArrayOutputStream();
   }
 
   public byte[] toByteArray()
   {
     return this.buf.toByteArray();
   }
 
   public void write(int b)
   {
     this.buf.write(b);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.framework.ssi.ByteArrayServletOutputStream
 * JD-Core Version:    0.5.3
 */