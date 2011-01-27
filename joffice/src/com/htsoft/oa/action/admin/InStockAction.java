package com.htsoft.oa.action.admin;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.util.JsonUtil;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.admin.InStock;
import com.htsoft.oa.model.admin.OfficeGoods;
import com.htsoft.oa.service.admin.InStockService;
import com.htsoft.oa.service.admin.OfficeGoodsService;

import flexjson.JSONSerializer;

public class InStockAction extends BaseAction {

	@Resource
	private InStockService inStockService;
	private InStock inStock;

	@Resource
	private OfficeGoodsService officeGoodsService;
	private Long buyId;

	public Long getBuyId() {
		return this.buyId;
	}

	public void setBuyId(Long buyId) {
		this.buyId = buyId;
	}

	public InStock getInStock() {
		return this.inStock;
	}

	public void setInStock(InStock inStock) {
		this.inStock = inStock;
	}

	public String list() {
		QueryFilter filter = new QueryFilter(getRequest());
		List list = this.inStockService.getAll(filter);
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':").append(
				filter.getPagingBean().getTotalItems()).append(",result:");
		JSONSerializer serializer = JsonUtil.getJSONSerializer(new String[] { "inDate" });
		buff.append(serializer.exclude(new String[] { "class" }).serialize(list));
		buff.append("}");
		this.jsonString = buff.toString();
		return "success";
	}

	public String multiDel() {
		String[] ids = getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				this.inStockService.remove(new Long(id));
			}
		}

		this.jsonString = "{success:true}";

		return "success";
	}

	public String get() {
		InStock inStock = (InStock) this.inStockService.get(this.buyId);
		StringBuffer sb = new StringBuffer("{success:true,data:");
		JSONSerializer serializer = JsonUtil.getJSONSerializer(new String[] { "inDate" });
		sb.append(serializer.exclude(new String[] { "class" }).serialize(inStock));
		sb.append("}");
		setJsonString(sb.toString());
		return "success";
	}

	public String save() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss-SSS");
		this.inStock.setStockNo(sdf.format(new Date()));
		Integer inCount = this.inStock.getInCounts();
		BigDecimal price = this.inStock.getPrice();
		BigDecimal amount = null;
		if ((inCount != null) && (price != null)) {
			amount = price.multiply(BigDecimal.valueOf(inCount.intValue()));
		}
		this.inStock.setAmount(amount);
		Long goodsId = this.inStock.getGoodsId();
		OfficeGoods goods = (OfficeGoods) this.officeGoodsService.get(goodsId);
		if (this.inStock.getBuyId() == null) {
			goods.setStockCounts(Integer.valueOf(goods.getStockCounts().intValue()
					+ this.inStock.getInCounts().intValue()));
		} else {
			Integer newInCount = this.inStock.getInCounts();
			Integer oldInCount = this.inStockService.findInCountByBuyId(this.inStock.getBuyId());
			if (!oldInCount.equals(newInCount)) {
				goods.setStockCounts(Integer.valueOf(goods.getStockCounts().intValue()
						- oldInCount.intValue() + newInCount.intValue()));
			}
		}
		this.inStockService.save(this.inStock);
		this.officeGoodsService.save(goods);
		setJsonString("{success:true}");
		return "success";
	}
}
