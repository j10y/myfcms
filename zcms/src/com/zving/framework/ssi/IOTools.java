 package com.zving.framework.ssi;
 
 import java.io.IOException;
 import java.io.InputStream;
 import java.io.OutputStream;
 import java.io.Reader;
 import java.io.Writer;
 
 public class IOTools
 {
   protected static final int DEFAULT_BUFFER_SIZE = 4096;
 
   public static void flow(Reader reader, Writer writer, char[] buf)
     throws IOException
   {
     int numRead;
     while ((numRead = reader.read(buf)) >= 0)
       writer.write(buf, 0, numRead);
   }
 
   public static void flow(Reader reader, Writer writer)
     throws IOException
   {
     char[] buf = new char[4096];
     flow(reader, writer, buf);
   }
 
   public static void flow(InputStream is, OutputStream os, byte[] buf)
     throws IOException
   {
     int numRead;
     while ((numRead = is.read(buf)) >= 0)
       os.write(buf, 0, numRead);
   }
 
   public static void flow(InputStream is, OutputStream os)
     throws IOException
   {
     byte[] buf = new byte[4096];
     flow(is, os, buf);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.framework.ssi.IOTools
 * JD-Core Version:    0.5.3
 */