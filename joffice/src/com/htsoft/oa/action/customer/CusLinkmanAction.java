package com.htsoft.oa.action.customer;

import java.util.List;

import javax.annotation.Resource;

import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.customer.CusLinkman;
import com.htsoft.oa.model.customer.Customer;
import com.htsoft.oa.service.customer.CusLinkmanService;
import com.htsoft.oa.service.customer.CustomerService;

import flexjson.JSONSerializer;

public class CusLinkmanAction extends BaseAction {
	private Short isPrimary = 1;

	@Resource
	private CusLinkmanService cusLinkmanService;

	@Resource
	private CustomerService customerService;
	private CusLinkman cusLinkman;
	private Long linkmanId;

	public Long getLinkmanId() {
		return this.linkmanId;
	}

	public void setLinkmanId(Long linkmanId) {
		this.linkmanId = linkmanId;
	}

	public CusLinkman getCusLinkman() {
		return this.cusLinkman;
	}

	public void setCusLinkman(CusLinkman cusLinkman) {
		this.cusLinkman = cusLinkman;
	}

	public String list() {
		QueryFilter filter = new QueryFilter(getRequest());
		List list = this.cusLinkmanService.getAll(filter);

		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':").append(
				filter.getPagingBean().getTotalItems()).append(",result:");

		JSONSerializer json = new JSONSerializer();
		buff.append(json.exclude(new String[] { "class" }).serialize(list));
		buff.append("}");

		this.jsonString = buff.toString();

		return "success";
	}

	public String multiDel() {
		String[] ids = getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				this.cusLinkmanService.remove(new Long(id));
			}
		}

		this.jsonString = "{success:true}";

		return "success";
	}

	public String get() {
		CusLinkman cusLinkman = (CusLinkman) this.cusLinkmanService.get(this.linkmanId);

		JSONSerializer json = new JSONSerializer();

		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(json.exclude(new String[] { "class", "custoemr.class" }).serialize(cusLinkman));
		sb.append("}");
		setJsonString(sb.toString());
		return "success";
	}

	public String save() {
		boolean pass = false;
		StringBuffer buff = new StringBuffer("{");
		if (this.cusLinkman.getCustomerId() != null)
			if (this.cusLinkman.getIsPrimary().shortValue() != 1) {
				pass = true;
			} else if (this.cusLinkmanService.checkMainCusLinkman(this.cusLinkman.getCustomerId(),
					this.cusLinkman.getLinkmanId()))
				pass = true;
			else
				buff.append("msg:'该客户的主要联系人已存在,请保存为普通联系人!',");
		else {
			buff.append("msg:'所属客户不能为空.',");
		}
		if (pass) {
			this.cusLinkman.setCustomer((Customer) this.customerService.get(this.cusLinkman
					.getCustomerId()));
			this.cusLinkmanService.save(this.cusLinkman);
			buff.append("success:true}");
		} else {
			buff.append("failure:true}");
		}
		setJsonString(buff.toString());
		return "success";
	}

	public String find() {
		QueryFilter filter = new QueryFilter(getRequest());
		filter.addSorted("isPrimary", "desc");
		List<CusLinkman> list = this.cusLinkmanService.getAll(filter);

		StringBuffer buff = new StringBuffer("[");
		for (CusLinkman cusLinkman : list) {
			buff
					.append("['" + cusLinkman.getLinkmanId() + "','" + cusLinkman.getFullname()
							+ "'],");
		}
		if (list.size() != 0) {
			buff.deleteCharAt(buff.length() - 1);
		}
		buff.append("]");
		setJsonString(buff.toString());
		return "success";
	}
}
