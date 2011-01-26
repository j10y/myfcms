package com.htsoft.oa.action.admin;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.admin.AssetsType;
import com.htsoft.oa.service.admin.AssetsTypeService;
import com.htsoft.oa.service.admin.FixedAssetsService;
import java.lang.reflect.Type;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

public class AssetsTypeAction extends BaseAction {

	@Resource
	private AssetsTypeService assetsTypeService;
	private AssetsType assetsType;

	@Resource
	private FixedAssetsService fixedAssetsService;
	private Long assetsTypeId;

	public Long getAssetsTypeId() {
		return this.assetsTypeId;
	}

	public void setAssetsTypeId(Long assetsTypeId) {
		this.assetsTypeId = assetsTypeId;
	}

	public AssetsType getAssetsType() {
		return this.assetsType;
	}

	public void setAssetsType(AssetsType assetsType) {
		this.assetsType = assetsType;
	}

	public String list() {
		QueryFilter filter = new QueryFilter(getRequest());
		List list = this.assetsTypeService.getAll(filter);

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

	public String tree() {
		List<AssetsType> list = this.assetsTypeService.getAll();
		StringBuffer sb = new StringBuffer("[{id:'0',text:'资产类型',expanded:true,children:[");
		for (AssetsType type : list) {
			sb.append("{id:'" + type.getAssetsTypeId() + "',text:'" + type.getTypeName()
					+ "',leaf:true},");
		}
		if (list.size() > 0) {
			sb.deleteCharAt(sb.length() - 1);
		}
		sb.append("]}]");
		setJsonString(sb.toString());
		return "success";
	}

	public String multiDel() {
		String[] ids = getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				QueryFilter filter = new QueryFilter(getRequest());
				filter.addFilter("Q_assetsType.assetsTypeId_L_EQ", id);
				List list = this.fixedAssetsService.getAll(filter);
				if (list.size() > 0) {
					this.jsonString = "{success:false,message:'该类型下还有资产，请将资产移走后再进行删除！'}";
					return "success";
				}
				this.assetsTypeService.remove(new Long(id));
			}
		}

		this.jsonString = "{success:true}";

		return "success";
	}

	public String get() {
		AssetsType assetsType = (AssetsType) this.assetsTypeService.get(this.assetsTypeId);
		Gson gson = new Gson();

		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(assetsType));
		sb.append("}");
		setJsonString(sb.toString());
		return "success";
	}

	public String save() {
		this.assetsTypeService.save(this.assetsType);
		setJsonString("{success:true}");
		return "success";
	}

	public String combox() {
		List<AssetsType> list = this.assetsTypeService.getAll();
		StringBuffer buff = new StringBuffer("[");
		for (AssetsType assetsType : list) {
			buff.append("['" + assetsType.getAssetsTypeId() + "','" + assetsType.getTypeName()
					+ "'],");
		}
		if (list.size() > 0) {
			buff.deleteCharAt(buff.length() - 1);
		}
		buff.append("]");
		setJsonString(buff.toString());
		return "success";
	}
}
