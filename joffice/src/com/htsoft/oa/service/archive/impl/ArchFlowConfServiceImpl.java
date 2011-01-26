 package com.htsoft.oa.service.archive.impl;
 
 import com.htsoft.core.service.impl.BaseServiceImpl;
 import com.htsoft.oa.dao.archive.ArchFlowConfDao;
 import com.htsoft.oa.model.archive.ArchFlowConf;
 import com.htsoft.oa.service.archive.ArchFlowConfService;
 
 public class ArchFlowConfServiceImpl extends BaseServiceImpl<ArchFlowConf>
   implements ArchFlowConfService
 {
   private ArchFlowConfDao dao;
 
   public ArchFlowConfServiceImpl(ArchFlowConfDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 
   public ArchFlowConf getByFlowType(Short archType)
   {
     return this.dao.getByFlowType(archType);
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.archive.impl.ArchFlowConfServiceImpl
 * JD-Core Version:    0.5.4
 */