package com.htsoft.oa.action.admin;

import java.util.List;

import javax.annotation.Resource;

import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.util.JsonUtil;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.admin.CartRepair;
import com.htsoft.oa.service.admin.CartRepairService;

import flexjson.JSONSerializer;

public class CartRepairAction extends BaseAction {

	@Resource
	private CartRepairService cartRepairService;
	private CartRepair cartRepair;
	private Long repairId;

	public Long getRepairId() {
		return this.repairId;
	}

	public void setRepairId(Long repairId) {
		this.repairId = repairId;
	}

	public CartRepair getCartRepair() {
		return this.cartRepair;
	}

	public void setCartRepair(CartRepair cartRepair) {
		this.cartRepair = cartRepair;
	}

	public String list() {
		QueryFilter filter = new QueryFilter(getRequest());
		List list = this.cartRepairService.getAll(filter);
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':").append(
				filter.getPagingBean().getTotalItems()).append(",result:");
		JSONSerializer serializer = JsonUtil.getJSONSerializer(new String[] { "repairDate" });
		buff.append(serializer.exclude(new String[] { "class" }).serialize(list));
		buff.append("}");
		this.jsonString = buff.toString();
		return "success";
	}

	public String multiDel() {
		String[] ids = getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				this.cartRepairService.remove(new Long(id));
			}
		}
		this.jsonString = "{success:true}";
		return "success";
	}

	public String get() {
		CartRepair cartRepair = (CartRepair) this.cartRepairService.get(this.repairId);
		StringBuffer sb = new StringBuffer("{success:true,data:");
		JSONSerializer serializer = JsonUtil.getJSONSerializer(new String[] { "repairDate" });
		sb.append(serializer.exclude(new String[] { "class", "car.cartRepairs" }).serialize(
				cartRepair));
		sb.append("}");
		setJsonString(sb.toString());
		return "success";
	}

	public String save() {
		this.cartRepairService.save(this.cartRepair);
		setJsonString("{success:true}");
		return "success";
	}
}
