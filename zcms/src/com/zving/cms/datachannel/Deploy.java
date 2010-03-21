 package com.zving.cms.datachannel;
 
 import com.zving.cms.pub.SiteUtil;
 import com.zving.cms.site.FileList;
 import com.zving.datachannel.CommonFtp;
 import com.zving.framework.Config;
 import com.zving.framework.User;
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.data.Transaction;
 import com.zving.framework.utility.Errorx;
 import com.zving.framework.utility.FileUtil;
 import com.zving.framework.utility.LogUtil;
 import com.zving.framework.utility.Mapx;
 import com.zving.framework.utility.StringUtil;
 import com.zving.platform.Application;
 import com.zving.platform.pub.NoUtil;
 import com.zving.schema.ZCDeployConfigSchema;
 import com.zving.schema.ZCDeployConfigSet;
 import com.zving.schema.ZCDeployJobSchema;
 import com.zving.schema.ZCDeployJobSet;
 import com.zving.schema.ZCDeployLogSchema;
 import java.io.File;
 import java.io.PrintStream;
 import java.util.ArrayList;
 import java.util.Date;
 import org.apache.commons.httpclient.HttpClient;
 import org.apache.commons.httpclient.HttpConnectionManager;
 import org.apache.commons.httpclient.HttpStatus;
 import org.apache.commons.httpclient.methods.PostMethod;
 import org.apache.commons.httpclient.methods.multipart.FilePart;
 import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
 import org.apache.commons.httpclient.methods.multipart.Part;
 import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
 import org.apache.commons.httpclient.params.HttpMethodParams;
 import org.apache.commons.logging.Log;
 
 public class Deploy
 {
   public static final int READY = 0;
   public static final int EXECUTION = 1;
   public static final int SUCCESS = 2;
   public static final int FAIL = 3;
   public static final String TemplateDIR = "template";
   public static final Mapx depolyStatus = new Mapx();
 
   static {
     depolyStatus.put("0", "就绪");
     depolyStatus.put("1", "执行中");
     depolyStatus.put("2", "成功");
     depolyStatus.put("3", "失败");
   }
 
   public boolean addOneJob(long configID, boolean immediate)
   {
     ZCDeployJobSchema job = new ZCDeployJobSchema();
     ZCDeployConfigSchema config = new ZCDeployConfigSchema();
     config.setID(configID);
     if (!(config.fill())) {
       return false;
     }
 
     String staticDir = Config.getContextRealPath() + Config.getValue("Statical.TargetDir").replace('\\', '/');
     String sourcePath = staticDir + "/" + Application.getCurrentSiteAlias() + config.getSourceDir();
     job.setID(NoUtil.getMaxID("DeployJobID"));
     job.setConfigID(config.getID());
     job.setSource(sourcePath);
     job.setMethod(config.getMethod());
 
     String targetDir = config.getTargetDir();
     if (StringUtil.isEmpty(targetDir)) {
       targetDir = "/";
     }
     else if (!(targetDir.endsWith("/"))) {
       targetDir = targetDir + "/";
     }
 
     job.setTarget(config.getTargetDir());
     job.setSiteID(config.getSiteID());
     job.setHost(config.getHost());
     job.setPort(config.getPort());
     job.setUserName(config.getUserName());
     job.setPassword(config.getPassword());
     job.setStatus(0L);
     job.setAddTime(new Date());
     job.setAddUser(User.getUserName());
 
     Transaction trans = new Transaction();
     trans.add(job, 1);
     if (trans.commit()) {
       if (immediate) {
         executeJob(job);
       }
       return true;
     }
     LogUtil.getLogger().info("添加部署任务时，数据库操作失败");
     return false;
   }
 
   public boolean addJobs(long siteID, ArrayList list)
   {
     ZCDeployJobSet set = getJobs(siteID, list);
     Transaction trans = new Transaction();
     trans.add(set, 1);
     if (trans.commit()) {
       return true;
     }
     LogUtil.getLogger().info("添加部署任务时，数据库操作失败");
     return false;
   }
 
   public ZCDeployJobSet getJobs(long siteID, ArrayList list)
   {
     ZCDeployJobSet jobSet = new ZCDeployJobSet();
     for (int j = 0; j < list.size(); ++j) {
       String srcPath = (String)list.get(j);
       if (StringUtil.isEmpty(srcPath)) {
         continue;
       }
       srcPath = srcPath.replace('\\', '/').replaceAll("///", "/").replaceAll("//", "/");
 
       String baseDir = Config.getContextRealPath() + Config.getValue("Statical.TargetDir").replace('\\', '/');
       baseDir = baseDir + "/" + SiteUtil.getAlias(siteID);
 
       baseDir = baseDir.replaceAll("///", "/");
       baseDir = baseDir.replaceAll("//", "/");
       srcPath = srcPath.replaceAll(baseDir, "");
 
       ZCDeployConfigSchema config = new ZCDeployConfigSchema();
 
       QueryBuilder qb = new QueryBuilder(" where siteid=? and ? like concat(sourcedir,'%')", siteID, srcPath);
       if (Config.isSQLServer()) {
         qb.setSQL(" where siteid=? and charindex(sourcedir,?)=0");
       }
       if (Config.isDB2()) {
         qb.setSQL(" where siteid=? and locate(sourcedir,'" + srcPath + "')=0");
         qb.getParams().remove(qb.getParams().size() - 1);
       }
 
       ZCDeployConfigSet set = config.query(qb);
 
       for (int i = 0; i < set.size(); ++i) {
         config = set.get(i);
         String target = config.getTargetDir();
 
         String filePath = srcPath;
         if (!(config.getSourceDir().equals("/"))) {
           filePath = srcPath.replaceAll(config.getSourceDir(), "");
         }
         target = dealFileName(target, filePath);
 
         ZCDeployJobSchema job = new ZCDeployJobSchema();
         job.setID(NoUtil.getMaxID("DeployJobID"));
         job.setConfigID(config.getID());
         job.setSource(baseDir + srcPath);
         job.setMethod(config.getMethod());
         job.setTarget(target);
         job.setSiteID(config.getSiteID());
         job.setHost(config.getHost());
         job.setPort(config.getPort());
         job.setUserName(config.getUserName());
         job.setPassword(config.getPassword());
         job.setRetryCount(0L);
         job.setStatus(0L);
         job.setAddTime(new Date());
         if (User.getCurrent() != null)
           job.setAddUser(User.getUserName());
         else {
           job.setAddUser("SYS");
         }
 
         jobSet.add(job);
       }
     }
 
     return jobSet;
   }
 
   private String dealFileName(String part1, String part2) {
     if ((part1.equals("")) || (part2.equals("")))
       return part1 + part2;
     if ((!(part1.endsWith("/"))) && (!(part2.startsWith("/"))))
       return part1 + "/" + part2;
     if ((part1.endsWith("/")) && (part2.startsWith("/"))) {
       return part1 + part2.substring(1);
     }
     return part1 + part2;
   }
 
   public boolean executeJob(long jobID)
   {
     ZCDeployJobSchema job = new ZCDeployJobSchema();
     job.setID(jobID);
     if (!(job.fill())) {
       return false;
     }
     return executeJob(job);
   }
 
   public boolean executeJob(ZCDeployJobSchema job)
   {
     ZCDeployLogSchema jobLog = new ZCDeployLogSchema();
     jobLog.setID(NoUtil.getMaxID("DeployLogID"));
     jobLog.setSiteID(job.getSiteID());
     jobLog.setJobID(job.getID());
     jobLog.setBeginTime(new Date());
 
     if (job.getStatus() == 3L) {
       job.setRetryCount(job.getRetryCount() + 1L);
     }
 
     String message = "";
     String deployMethod = job.getMethod();
 
     String sourceFile = job.getSource();
     if (sourceFile.indexOf("template") != -1) {
       LogUtil.getLogger().info("模板文件" + sourceFile + "不复制，跳过");
       return true;
     }
 
     if ("DIR".equals(deployMethod)) {
       String target = job.getTarget();
       target = target.replace('\\', '/');
       String targetDir = target.substring(0, target.lastIndexOf("/"));
       File dir = new File(targetDir);
       if (!(dir.exists())) {
         dir.mkdirs();
       }
       if (!(targetDir.endsWith("/template")))
         if (FileUtil.copy(sourceFile, target)) {
           message = "成功复制文件" + sourceFile + "到" + target;
           LogUtil.getLogger().info(message);
           job.setStatus(2L);
         } else {
           message = "失败：复制文件" + sourceFile + "到" + target;
           LogUtil.getLogger().info(message);
           job.setStatus(3L);
 
           Errorx.addError(message);
         }
     }
     else if ("HTTP".equals(deployMethod)) {
       String url = sourceFile;
       if (url.indexOf("?") != -1)
         url = url + "&TargetPath=" + job.getTarget();
       else {
         url = url + "?TargetPath=" + job.getTarget();
       }
       String source = job.getSource();
       if (httpCopyFile(url, source)) {
         job.setStatus(2L);
         message = "http上传成功";
       } else {
         job.setStatus(3L);
         message = "http上传失败";
         Errorx.addError(message);
       }
       job.setStatus(2L);
     } else if ("FTP".equals(deployMethod)) {
       try {
         ftpCopyFile(sourceFile, job.getTarget(), job.getHost(), (int)job.getPort(), job.getUserName(), job
           .getPassword());
         job.setStatus(2L);
         message = "FTP上传成功";
       }
       catch (Exception e) {
         job.setStatus(3L);
         message = e.getMessage();
         Errorx.addError(message);
       }
     }
 
     jobLog.setMessage(message);
     jobLog.setEndTime(new Date());
 
     job.setModifyTime(new Date());
     if (User.getCurrent() == null)
       job.setModifyUser("admin");
     else {
       job.setModifyUser(User.getUserName());
     }
 
     Transaction trans = new Transaction();
     trans.add(jobLog, 1);
     trans.add(job, 2);
 
     LogUtil.getLogger().info(message);
 
     if (trans.commit()) {
       return true;
     }
     LogUtil.getLogger().info("添加部署任务时，数据库操作失败");
     Errorx.addError(message);
     return false;
   }
 
   public boolean httpCopyFile(String url, String sourceFilePath)
   {
     boolean flag = true;
     File sourceFile = new File(sourceFilePath);
     HttpClient client = new HttpClient();
     PostMethod filePost = new PostMethod(url);
     try {
       LogUtil.getLogger().info("上传文件 " + sourceFilePath + " 到 " + url);
       Part[] parts = { new FilePart("UploadFile", sourceFile) };
       filePost.getParams().setBooleanParameter("http.protocol.expect-continue", false);
       filePost.setRequestEntity(new MultipartRequestEntity(parts, filePost.getParams()));
       client.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
       int status = client.executeMethod(filePost);
       if (status == 200)
         LogUtil.getLogger().info("上传完成, response=" + filePost.getResponseBodyAsString());
       else {
         LogUtil.getLogger().info("上传失败, response=" + HttpStatus.getStatusText(status));
       }
       flag = true;
     } catch (Exception ex) {
       ex.printStackTrace();
       flag = false;
     } finally {
       filePost.releaseConnection();
     }
 
     return flag;
   }
 
   public void ftpCopyFile(String srcFile, String tarFile, String server, int port, String userName, String password) throws Exception
   {
     CommonFtp ftp = new CommonFtp();
     try {
       ftp.connect(server, port, userName, password);
       FileList f = new FileList();
       srcFile = srcFile.replaceAll("///", "/");
       srcFile = srcFile.replaceAll("//", "/");
       String path = srcFile;
       ArrayList list = f.getAllFiles(path);
       for (int i = 0; i < list.size(); ++i) {
         String name = (String)list.get(i);
         if (name.indexOf("template") != -1) {
           continue;
         }
         name = name.replace('\\', '/');
         String targetName = name.replaceAll(path, "");
         ftp.stor(name, tarFile + targetName);
       }
 
       ftp.disconnect();
       System.out.println("FTP 上传成功.");
     } catch (Exception ioe) {
       ftp.disconnect();
       throw new Exception("FTP 上传失败：" + ioe);
     }
   }
 
   public void ftpDeleteFile(String filePath, String server, int port, String userName, String password) throws Exception
   {
     CommonFtp ftp = new CommonFtp();
     try {
       ftp.connect(server, port, userName, password);
       filePath = filePath.replaceAll("///", "/");
       if (ftp.delete(filePath)) {
         System.out.println("FTP 上传成功.");
       }
 
       ftp.disconnect();
     } catch (Exception ioe) {
       ftp.disconnect();
       throw new Exception("FTP 上传失败：" + ioe);
     }
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.cms.datachannel.Deploy
 * JD-Core Version:    0.5.3
 */