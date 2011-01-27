package com.htsoft.oa.action.system;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.util.AppUtil;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.system.SysConfig;
import com.htsoft.oa.service.system.SysConfigService;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

public class SysConfigAction extends BaseAction {

	@Resource
	private SysConfigService sysConfigService;
	private SysConfig sysConfig;
	private Long configId;

	public Long getConfigId() {
		return this.configId;
	}

	public void setConfigId(Long configId) {
		this.configId = configId;
	}

	public SysConfig getSysConfig() {
		return this.sysConfig;
	}

	public void setSysConfig(SysConfig sysConfig) {
		this.sysConfig = sysConfig;
	}

	public String list() {
		QueryFilter filter = new QueryFilter(getRequest());
		List list = this.sysConfigService.getAll(filter);

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
				this.sysConfigService.remove(new Long(id));
			}
		}

		this.jsonString = "{success:true}";

		return "success";
	}

	public String get() {
		SysConfig sysConfig = (SysConfig) this.sysConfigService.get(this.configId);

		Gson gson = new Gson();

		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(sysConfig));
		sb.append("}");
		setJsonString(sb.toString());

		return "success";
	}

	public String save() {
		Map con = AppUtil.getSysConfig();
		Map map = getRequest().getParameterMap();
		Iterator it = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String key = (String) entry.getKey();
			SysConfig conf = this.sysConfigService.findByKey(key);
			String[] value = (String[]) entry.getValue();
			conf.setDataValue(value[0]);
			this.sysConfigService.save(conf);
			con.remove(key);
			con.put(key, value[0]);
		}

		AppUtil.reloadSysConfig();

		setJsonString("{success:true}");
		return "success";
	}

	public String load() {
		Map conf = this.sysConfigService.findByType();
		Iterator it = conf.entrySet().iterator();
		StringBuffer buff = new StringBuffer("[");
		while (it.hasNext()) {
			Map.Entry type = (Map.Entry) it.next();
			String typeName = (String) type.getKey();
			buff.append("{xtype:'fieldset',title:'" + typeName
					+ "',layout:'form',width:650,items:[");
			List<SysConfig> list = (List) type.getValue();
			if (typeName.equals("验证码配置")) {
				for (SysConfig con : list)
					buff
							.append(
									"{xtype:'container',style:'padding-bottom:3px;',layout:'column',items:[{xtype:'label',style:'font-weight:bold;',text:'")
							.append(con.getConfigName())
							.append(
									":',width:100},{xtype:'combo',mode:'local',editable:false,triggerAction:'all',store:[['1','开启验证码'],['2','屏蔽验证码']],width:300,allowBlank:false,hiddenName:'")
							.append(con.getConfigKey()).append("',value:'").append(
									con.getDataValue())
							.append("'},{xtype:'label',width:200,text:'").append(
									con.getConfigDesc()).append("'}]},");
			} else {
				for (SysConfig con : list) {
					buff
							.append(
									"{xtype:'container',style:'padding-bottom:3px;',layout:'column',items:[{xtype:'label',style:'font-weight:bold;',text:'")
							.append(con.getConfigName())
							.append(
									":',width:100},{xtype:'textfield',width:300,allowBlank:false,id:'")
							.append(con.getConfigKey()).append("',name:'").append(
									con.getConfigKey()).append("',value:'").append(
									con.getDataValue())
							.append("'},{xtype:'label',width:200,text:'").append(
									con.getConfigDesc()).append("'}]},");
				}
			}
			if (list.size() > 0) {
				buff.deleteCharAt(buff.length() - 1);
			}
			buff.append("]},");
		}
		if (conf.size() > 0) {
			buff.deleteCharAt(buff.length() - 1);
		}
		buff.append("]");
		setJsonString("{success:true,data:" + buff.toString() + "}");
		return "success";
	}
}

/*
 * Location:
 * E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name: com.htsoft.oa.action.system.SysConfigAction JD-Core Version:
 * 0.5.4
 */