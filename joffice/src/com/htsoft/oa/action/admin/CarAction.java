package com.htsoft.oa.action.admin;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.admin.Car;
import com.htsoft.oa.service.admin.CarService;
import java.lang.reflect.Type;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;

public class CarAction extends BaseAction {

	@Resource
	private CarService carService;
	private Car car;
	private Long carId;

	public Long getCarId() {
		return this.carId;
	}

	public void setCarId(Long carId) {
		this.carId = carId;
	}

	public Car getCar() {
		return this.car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public String list() {
		QueryFilter filter = new QueryFilter(getRequest());
		List list = this.carService.getAll(filter);
		Type type = new TypeToken() {
		}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':").append(
				filter.getPagingBean().getTotalItems()).append(",result:");
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat(
				"yyyy-MM-dd").create();
		buff.append(gson.toJson(list, type));
		buff.append("}");
		this.jsonString = buff.toString();
		return "success";
	}

	public String multiDel() {
		String[] ids = getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				this.carService.remove(new Long(id));
			}
		}
		this.jsonString = "{success:true}";
		return "success";
	}

	public String get() {
		Car car = (Car) this.carService.get(this.carId);
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat(
				"yyyy-MM-dd HH:mm:ss").create();

		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(car));
		sb.append("}");
		setJsonString(sb.toString());
		return "success";
	}

	public String save() {
		this.carService.save(this.car);
		setJsonString("{success:true}");
		return "success";
	}

	public String delphoto() {
		String strCarId = getRequest().getParameter("carId");
		if (StringUtils.isNotEmpty(strCarId)) {
			this.car = ((Car) this.carService.get(new Long(strCarId)));
			this.car.setCartImage("");
			this.carService.save(this.car);
			setJsonString("{success:true}");
		}
		return "success";
	}
}
