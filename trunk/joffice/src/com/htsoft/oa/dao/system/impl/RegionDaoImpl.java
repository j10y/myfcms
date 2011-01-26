 package com.htsoft.oa.dao.system.impl;
 
 import com.htsoft.core.dao.impl.BaseDaoImpl;
 import com.htsoft.oa.dao.system.RegionDao;
 import com.htsoft.oa.model.system.Region;
 import java.util.List;
 
 public class RegionDaoImpl extends BaseDaoImpl<Region>
   implements RegionDao
 {
   public RegionDaoImpl()
   {
     super(Region.class);
   }
 
   public List<Region> getProvince()
   {
     Long parentId = Long.valueOf(0L);
     String hql = "from Region r where r.parentId = ?";
     return findByHql(hql, new Object[] { parentId });
   }
 
   public List<Region> getCity(Long regionId)
   {
     String hql = "from Region r where r.parentId = ?";
     return findByHql(hql, new Object[] { regionId });
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.system.impl.RegionDaoImpl
 * JD-Core Version:    0.5.4
 */