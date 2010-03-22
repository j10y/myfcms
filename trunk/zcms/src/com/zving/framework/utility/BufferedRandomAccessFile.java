 package com.zving.framework.utility;
 
 import java.io.IOException;
 import java.io.RandomAccessFile;
 
 public class BufferedRandomAccessFile extends RandomAccessFile
 {
   private String fileName;
 
   public BufferedRandomAccessFile(String fileName, String mode)
     throws IOException
   {
     super(fileName, mode);
     this.fileName = fileName;
   }
 
   public void delete() {
     FileUtil.delete(this.fileName);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.framework.utility.BufferedRandomAccessFile
 * JD-Core Version:    0.5.3
 */