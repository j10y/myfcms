package com.htsoft.oa.action.admin;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.admin.DepreType;
import com.htsoft.oa.service.admin.DepreTypeService;
import com.htsoft.oa.service.admin.FixedAssetsService;
import java.lang.reflect.Type;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

public class DepreTypeAction extends BaseAction {

	@Resource
	private DepreTypeService depreTypeService;
	private DepreType depreType;

	@Resource
	private FixedAssetsService fixedAssetsService;
	private Long depreTypeId;

	public Long getDepreTypeId() {
		return this.depreTypeId;
	}

	public void setDepreTypeId(Long depreTypeId) {
		this.depreTypeId = depreTypeId;
	}

	public DepreType getDepreType() {
		return this.depreType;
	}

	public void setDepreType(DepreType depreType) {
		this.depreType = depreType;
	}

	public String list() {
		QueryFilter filter = new QueryFilter(getRequest());
		List list = this.depreTypeService.getAll(filter);
		Type type = new TypeToken() {
		}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':").append(
				filter.getPagingBean().getTotalItems()).append(",result:");
		Gson gson = new Gson();
		buff.append(gson.toJson(list, type));
		buff.append("}");
		this.jsonString = buff.toString();

		return "success";
	}

	public String multiDel() {
		String[] ids = getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				QueryFilter filter = new QueryFilter(getRequest());
				filter.addFilter("Q_depreType.depreTypeId_L_EQ", id);
				List list = this.fixedAssetsService.getAll(filter);
				if (list.size() > 0) {
					this.jsonString = "{success:false,message:'该折算类型下还有资产，请把该资产移走后，再进行删除！'}";
					return "success";
				}
				this.depreTypeService.remove(new Long(id));
			}
		}
		this.jsonString = "{success:true}";
		return "success";
	}

	public String get() {
		DepreType depreType = (DepreType) this.depreTypeService.get(this.depreTypeId);
		Gson gson = new Gson();

		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(depreType));
		sb.append("}");
		setJsonString(sb.toString());
		return "success";
	}

	public String save() {
		this.depreTypeService.save(this.depreType);
		setJsonString("{success:true}");
		return "success";
	}

	public String combox() {
		List<DepreType> list = this.depreTypeService.getAll();
		StringBuffer buff = new StringBuffer("[");
		for (DepreType depreType : list) {
			buff.append("['" + depreType.getDepreTypeId() + "','" + depreType.getTypeName() + "','"
					+ depreType.getCalMethod() + "'],");
		}
		if (list.size() > 0) {
			buff.deleteCharAt(buff.length() - 1);
		}
		buff.append("]");
		setJsonString(buff.toString());
		return "success";
	}
}
