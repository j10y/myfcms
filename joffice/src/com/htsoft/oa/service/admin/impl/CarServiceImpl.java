package com.htsoft.oa.service.admin.impl;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.oa.dao.admin.CarDao;
import com.htsoft.oa.model.admin.Car;
import com.htsoft.oa.service.admin.CarService;

public class CarServiceImpl extends BaseServiceImpl<Car> implements CarService {
	private CarDao dao;

	public CarServiceImpl(CarDao dao) {
		super(dao);
		this.dao = dao;
	}
}


 
 
 
 
 