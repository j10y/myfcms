package com.htsoft.oa.action.admin;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.util.JsonUtil;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.admin.DepreType;
import com.htsoft.oa.model.admin.FixedAssets;
import com.htsoft.oa.service.admin.DepreRecordService;
import com.htsoft.oa.service.admin.DepreTypeService;
import com.htsoft.oa.service.admin.FixedAssetsService;

import flexjson.JSONSerializer;

public class FixedAssetsAction extends BaseAction {

	@Resource
	private FixedAssetsService fixedAssetsService;
	private FixedAssets fixedAssets;

	@Resource
	private DepreRecordService depreRecordService;

	@Resource
	private DepreTypeService depreTypeService;
	private Long assetsId;

	public Long getAssetsId() {
		return this.assetsId;
	}

	public void setAssetsId(Long assetsId) {
		this.assetsId = assetsId;
	}

	public FixedAssets getFixedAssets() {
		return this.fixedAssets;
	}

	public void setFixedAssets(FixedAssets fixedAssets) {
		this.fixedAssets = fixedAssets;
	}

	public String list() {
		QueryFilter filter = new QueryFilter(getRequest());
		List list = this.fixedAssetsService.getAll(filter);
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':").append(
				filter.getPagingBean().getTotalItems()).append(",result:");
		JSONSerializer serializer = JsonUtil.getJSONSerializer(new String[] { "buyDate",
				"startDepre", "manuDate" });
		buff.append(serializer.exclude(new String[] { "class" }).serialize(list));
		buff.append("}");
		this.jsonString = buff.toString();
		return "success";
	}

	public String multiDel() {
		String[] ids = getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				this.fixedAssetsService.remove(new Long(id));
			}
		}
		this.jsonString = "{success:true}";

		return "success";
	}

	public String get() {
		FixedAssets fixedAssets = (FixedAssets) this.fixedAssetsService.get(this.assetsId);
		StringBuffer sb = new StringBuffer("{success:true,data:");
		JSONSerializer serializer = JsonUtil.getJSONSerializer(new String[] { "manuDate",
				"buyDate", "startDepre" });
		sb.append(serializer.exclude(new String[] { "class" }).serialize(fixedAssets));
		sb.append("}");
		setJsonString(sb.toString());
		return "success";
	}

	public String save() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss-SSSS");
		if (this.fixedAssets.getAssetsId() == null) {
			this.fixedAssets.setAssetsNo(sdf.format(new Date()));
		}
		Long typeId = this.fixedAssets.getDepreType().getDepreTypeId();
		if (typeId != null) {
			DepreType depreType = (DepreType) this.depreTypeService.get(typeId);
			if (depreType.getCalMethod().shortValue() != 2) {
				BigDecimal remainRate = this.fixedAssets.getRemainValRate();
				BigDecimal depreRate = new BigDecimal("1").subtract(
						remainRate.divide(new BigDecimal("100"))).divide(
						this.fixedAssets.getIntendTerm(), 2, 2);
				this.fixedAssets.setDepreRate(depreRate);
			}
		}
		this.fixedAssetsService.save(this.fixedAssets);
		setJsonString("{success:true}");
		return "success";
	}
}
