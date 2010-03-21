 package com.zving.datachannel;
 
 import java.io.DataOutputStream;
 import java.io.IOException;
 import java.io.PrintStream;
 import java.io.RandomAccessFile;
 import sun.net.TelnetOutputStream;
 import sun.net.ftp.FtpClient;
 
 public class SimpleJavaFtp
 {
   private String server;
   private String userName;
   private String password;
   private int port;
   private String fileName;
   private String ftpDir;
 
   public SimpleJavaFtp(String server, String userName, String password, int port)
   {
     this.server = server;
     this.userName = userName;
     this.password = password;
     this.port = port;
   }
 
   public boolean uploadFile(String filePath, String ftpDir) {
     FtpClient ftp = null;
     try {
       ftp = new FtpClient(this.server, this.port);
       ftp.login(this.userName, this.password);
       ftp.binary();
     } catch (Exception ex) {
       System.out.println("FtpServer错误：" + ex.toString());
       ex.printStackTrace();
       return false;
     }
 
     if ((ftpDir != null) && (!("".equals(ftpDir)))) {
       try {
         ftp.cd(ftpDir);
       } catch (IOException e) {
         e.printStackTrace();
       }
     }
     try
     {
       RandomAccessFile sendFile = new RandomAccessFile(filePath, "r");
       sendFile.seek(0L);
 
       TelnetOutputStream outs = ftp.put(filePath.substring(filePath.lastIndexOf("/") + 1));
       DataOutputStream outputs = new DataOutputStream(outs);
       while (sendFile.getFilePointer() < sendFile.length()) {
         int ch = sendFile.read();
         outputs.write(ch);
       }
 
       outs.close();
       sendFile.close();
 
       ftp.closeServer();
     } catch (Exception sendFile) {
       System.out.println("鏂囦欢涓婁紶澶辫触..." + ex.toString());
       ex.printStackTrace();
       return false;
     }
     return true;
   }
 
   public static void main(String[] args) {
     SimpleJavaFtp ftp = new SimpleJavaFtp("127.0.0.1", "root", "1234", 21);
     ftp.uploadFile("E:/RECEIVE_MMS/2008-08-30/13951900000_19-12-24-171/xxxx.txt", "mms");
   }
 
   public String getFileName()
   {
     return this.fileName;
   }
 
   public void setFileName(String fileName) {
     this.fileName = fileName;
   }
 
   public String getFtpDir() {
     return this.ftpDir;
   }
 
   public void setFtpDir(String ftpDir) {
     this.ftpDir = ftpDir;
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.datachannel.SimpleJavaFtp
 * JD-Core Version:    0.5.3
 */