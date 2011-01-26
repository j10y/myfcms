 package com.htsoft.oa.service.customer.impl;
 
 import com.htsoft.core.service.impl.BaseServiceImpl;
 import com.htsoft.oa.dao.customer.ProductDao;
 import com.htsoft.oa.model.customer.Product;
 import com.htsoft.oa.service.customer.ProductService;
 
 public class ProductServiceImpl extends BaseServiceImpl<Product>
   implements ProductService
 {
   private ProductDao dao;
 
   public ProductServiceImpl(ProductDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.customer.impl.ProductServiceImpl
 * JD-Core Version:    0.5.4
 */