 package com.htsoft.oa.service.system.impl;
 
 import com.htsoft.core.service.impl.BaseServiceImpl;
 import com.htsoft.core.util.AppUtil;
 import com.htsoft.oa.dao.system.FileAttachDao;
 import com.htsoft.oa.model.system.FileAttach;
 import com.htsoft.oa.service.system.FileAttachService;
 import java.io.File;
 import org.apache.commons.logging.Log;
 
 public class FileAttachServiceImpl extends BaseServiceImpl<FileAttach>
   implements FileAttachService
 {
   private FileAttachDao dao;
 
   public FileAttachServiceImpl(FileAttachDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 
   public void removeByPath(String filePath)
   {
     FileAttach fileAttach = this.dao.getByPath(filePath);
 
     String fullFilePath = AppUtil.getAppAbsolutePath() + "/attachFiles/" + filePath;
 
     this.logger.info("file:" + fullFilePath);
 
     File file = new File(fullFilePath);
 
     if (file.exists()) {
       file.delete();
     }
     if (fileAttach != null)
       this.dao.remove(fileAttach);
   }
 
   public FileAttach getByPath(String filePath)
   {
     return this.dao.getByPath(filePath);
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.system.impl.FileAttachServiceImpl
 * JD-Core Version:    0.5.4
 */