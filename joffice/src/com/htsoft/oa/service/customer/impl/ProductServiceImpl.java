package com.htsoft.oa.service.customer.impl;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.oa.dao.customer.ProductDao;
import com.htsoft.oa.model.customer.Product;
import com.htsoft.oa.service.customer.ProductService;

public class ProductServiceImpl extends BaseServiceImpl<Product> implements ProductService {
	private ProductDao dao;

	public ProductServiceImpl(ProductDao dao) {
		super(dao);
		this.dao = dao;
	}
}


 
 
 
 
 