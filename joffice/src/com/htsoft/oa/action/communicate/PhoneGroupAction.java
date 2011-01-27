package com.htsoft.oa.action.communicate;

import com.google.gson.Gson;
import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.util.ContextUtil;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.communicate.PhoneGroup;
import com.htsoft.oa.model.system.AppUser;
import com.htsoft.oa.service.communicate.PhoneBookService;
import com.htsoft.oa.service.communicate.PhoneGroupService;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;

public class PhoneGroupAction extends BaseAction {

	@Resource
	private PhoneGroupService phoneGroupService;
	private PhoneGroup phoneGroup;

	@Resource
	private PhoneBookService phoneBookService;
	private Long groupId;

	public Long getGroupId() {
		return this.groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public PhoneGroup getPhoneGroup() {
		return this.phoneGroup;
	}

	public void setPhoneGroup(PhoneGroup phoneGroup) {
		this.phoneGroup = phoneGroup;
	}

	public String list() {
		List<PhoneGroup> list = this.phoneGroupService.getAll(ContextUtil.getCurrentUserId());
		String method = getRequest().getParameter("method");
		StringBuffer buff = new StringBuffer();
		int i = 0;
		if (StringUtils.isNotEmpty(method)) {
			buff.append("[");
		} else {
			++i;
			buff.append("[{id:'0',text:'联系人分组',expanded:true,children:[");
		}
		for (PhoneGroup pg : list) {
			buff.append("{id:'" + pg.getGroupId() + "',text:'" + pg.getGroupName()
					+ "',leaf:true},");
		}
		if (!list.isEmpty()) {
			buff.deleteCharAt(buff.length() - 1);
		}
		if (i == 0)
			buff.append("]");
		else {
			buff.append("]}]");
		}
		this.jsonString = buff.toString();
		return "success";
	}

	public String multiDel() {
		String[] ids = getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				Long groupId = new Long(id);
				PhoneGroup phoneGroup = (PhoneGroup) this.phoneGroupService.get(groupId);
				this.phoneGroupService.remove(groupId);
				List<PhoneGroup> list = this.phoneGroupService.findBySnDown(phoneGroup.getSn(),
						phoneGroup.getAppUser().getUserId());
				for (PhoneGroup pg : list) {
					pg.setSn(Integer.valueOf(pg.getSn().intValue() - 1));
					this.phoneGroupService.save(pg);
				}
			}
		}

		this.jsonString = "{success:true}";

		return "success";
	}

	public String count() {
		QueryFilter filter = new QueryFilter(getRequest());
		List pbList = this.phoneBookService.getAll(filter);
		setJsonString("{success:true,count:" + pbList.size() + "}");
		return "success";
	}

	public String get() {
		PhoneGroup phoneGroup = (PhoneGroup) this.phoneGroupService.get(this.groupId);
		Gson gson = new Gson();

		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(phoneGroup));
		sb.append("}");
		setJsonString(sb.toString());

		return "success";
	}

	public String save() {
		AppUser appUser = ContextUtil.getCurrentUser();
		Integer sn = this.phoneGroupService.findLastSn(appUser.getUserId());
		if (sn == null)
			sn = Integer.valueOf(0);
		this.phoneGroup.setAppUser(appUser);
		this.phoneGroup.setSn(Integer.valueOf(sn.intValue() + 1));
		this.phoneGroupService.save(this.phoneGroup);
		setJsonString("{success:true}");
		return "success";
	}

	public String move() {
		String strOpt = getRequest().getParameter("optId");
		String strGroupId = getRequest().getParameter("groupId");
		Long userId = ContextUtil.getCurrentUserId();
		if (StringUtils.isNotEmpty(strGroupId)) {
			Integer opt = Integer.valueOf(Integer.parseInt(strOpt));
			Long groupId = Long.valueOf(Long.parseLong(strGroupId));
			this.phoneGroup = ((PhoneGroup) this.phoneGroupService.get(groupId));
			Integer sn = this.phoneGroup.getSn();
			if ((opt.intValue() == 1) && (sn.intValue() > 1)) {
				PhoneGroup pg = this.phoneGroupService.findBySn(Integer.valueOf(sn.intValue() - 1),
						userId);
				pg.setSn(sn);
				this.phoneGroupService.save(pg);
				this.phoneGroup.setSn(Integer.valueOf(sn.intValue() - 1));
				this.phoneGroupService.save(this.phoneGroup);
			}

			if ((opt.intValue() == 2)
					&& (sn.intValue() < this.phoneGroupService.findLastSn(userId).intValue())) {
				PhoneGroup pg = this.phoneGroupService.findBySn(Integer.valueOf(sn.intValue() + 1),
						userId);
				pg.setSn(sn);
				this.phoneGroup.setSn(Integer.valueOf(sn.intValue() + 1));
				this.phoneGroupService.save(pg);
				this.phoneGroupService.save(this.phoneGroup);
			}

			if ((opt.intValue() == 3) && (sn.intValue() > 1)) {
				List<PhoneGroup> list = this.phoneGroupService.findBySnUp(sn, userId);
				for (PhoneGroup pg : list) {
					pg.setSn(Integer.valueOf(pg.getSn().intValue() + 1));
					this.phoneGroupService.save(pg);
				}
				this.phoneGroup.setSn(Integer.valueOf(1));
				this.phoneGroupService.save(this.phoneGroup);
			}

			if ((opt.intValue() == 4)
					&& (sn.intValue() < this.phoneGroupService.findLastSn(userId).intValue())) {
				List<PhoneGroup> list = this.phoneGroupService.findBySnDown(sn, userId);
				for (PhoneGroup pg : list) {
					pg.setSn(Integer.valueOf(pg.getSn().intValue() - 1));
					this.phoneGroupService.save(pg);
				}
				this.phoneGroup.setSn(Integer.valueOf(this.phoneGroupService.findLastSn(userId)
						.intValue() + 1));
				this.phoneGroupService.save(this.phoneGroup);
			}

			setJsonString("{success:true}");
		} else {
			setJsonString("{success:false}");
		}
		return "success";
	}
}
