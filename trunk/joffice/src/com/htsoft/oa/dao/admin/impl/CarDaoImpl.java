 package com.htsoft.oa.dao.admin.impl;
 
 import com.htsoft.core.dao.impl.BaseDaoImpl;
 import com.htsoft.oa.dao.admin.CarDao;
 import com.htsoft.oa.model.admin.Car;
 
 public class CarDaoImpl extends BaseDaoImpl<Car>
   implements CarDao
 {
   public CarDaoImpl()
   {
     super(Car.class);
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.admin.impl.CarDaoImpl
 * JD-Core Version:    0.5.4
 */