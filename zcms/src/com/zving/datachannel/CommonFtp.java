 package com.zving.datachannel;
 
 import java.io.File;
 import java.io.FileInputStream;
 import java.io.IOException;
 import java.io.InputStream;
 import java.io.PrintStream;
 import org.apache.commons.net.ftp.FTPClient;
 import org.apache.commons.net.ftp.FTPReply;
 
 public class CommonFtp
 {
   private static boolean DEBUG = false;
   FTPClient ftp;
 
   public synchronized void connect(String host)
     throws IOException
   {
     connect(host, 21);
   }
 
   public synchronized void connect(String host, int port) throws IOException {
     connect(host, port, "anonymous", "anonymous@a.com");
   }
 
   public synchronized void connect(String host, int port, String user, String pass) throws IOException {
     try {
       this.ftp = new FTPClient();
 
       this.ftp.connect(host);
       System.out.println("Connected to " + host + ".");
       int reply = this.ftp.getReplyCode();
       if (!(FTPReply.isPositiveCompletion(reply))) {
         this.ftp.disconnect();
         System.err.println("FTP server refused connection.");
         throw new IOException("FTP server refused connection.");
       }
 
       if (!(this.ftp.login(user, pass))) {
         this.ftp.logout();
         throw new IOException("FTP server refused connection.");
       }
     }
     catch (IOException ex) {
       throw new IOException("Connect to " + host + ":" + port + " Error:  " + ex);
     }
   }
 
   public synchronized void disconnect() throws IOException {
     if (this.ftp == null) {
       return;
     }
 
     if (!(this.ftp.isConnected())) return;
     try {
       this.ftp.disconnect();
     } catch (IOException f) {
       throw new IOException("Disconnect Error : " + f);
     }
   }
 
   public synchronized boolean stor(String srcFile, String tarFile)
     throws Exception
   {
     boolean retval = true;
     String Path = tarFile.substring(0, tarFile.lastIndexOf("/"));
     if (DEBUG)
       System.out.println(Path);
     try {
       retval = this.ftp.makeDirectory(Path);
     } catch (Exception e) {
       e.printStackTrace();
     }
     try
     {
       File file_in = new File(srcFile);
       if (file_in.isDirectory())
         throw new IOException("FTP cannot upload a directory.");
       this.ftp.setFileType(2);
       this.ftp.enterLocalPassiveMode();
 
       InputStream input = new FileInputStream(srcFile);
       this.ftp.storeFile(tarFile, input);
       input.close();
     }
     catch (Exception ex) {
       retval = false;
       throw new Exception("UpLoad File " + srcFile + " to " + tarFile + " Failure! " + ex);
     }
     return retval;
   }
 
   public synchronized boolean delete(String filePath)
     throws Exception
   {
     boolean retval = true;
     if (DEBUG)
       System.out.println(filePath);
     try
     {
       File file_in = new File(filePath);
       if (file_in.isDirectory())
         throw new IOException("FTP cannot delete a directory.");
       this.ftp.setFileType(2);
       this.ftp.enterLocalPassiveMode();
 
       retval = this.ftp.deleteFile(filePath);
     }
     catch (Exception ex) {
       retval = false;
       throw new Exception("Delete File " + filePath + " Failure! " + ex);
     }
     return retval;
   }
 
   public static void main(String[] args) {
     CommonFtp ftp = new CommonFtp();
     try {
       ftp.connect("10.1.43.58", 21, "root", "1234");
       ftp.stor("f:/test.html", "/test/test.html");
     }
     catch (IOException e) {
       e.printStackTrace();
     }
     catch (Exception e) {
       e.printStackTrace();
     }
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.datachannel.CommonFtp
 * JD-Core Version:    0.5.3
 */