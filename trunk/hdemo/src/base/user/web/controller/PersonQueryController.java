/**
 * <p>项目名称：公共模块</p>
 * <p>版权所有 (c) 2005 湖北全达信息科技有限公司</p>
 * <p>作者：</p>
 * <p>版本：1.0</p>
 * <p>日期：2007-9-3</p>
 * <p>更新：2007-9-4:增加查询条件的字符串格式化（去空格，判断百分号）</p>
 */
package com.hxzy.common.user.web.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.hxzy.base.util.Pagination;
import com.hxzy.base.util.StringUtil;
import com.hxzy.base.web.controller.BaseCommandController;
import com.hxzy.common.user.model.User;
import com.hxzy.common.user.service.UserService;

/**
 * <p>
 * 类名: PersonQueryController
 * </p>
 * <p>
 * 描述: 人员查询Controller类
 * </p>
 */
public class PersonQueryController extends BaseCommandController {

	/**
	 * 描述: 人员Manager
	 */
	private UserService userService;

	/**
	 * 描述: 数据字典Manager
	 */
//	private DictionaryManager dictionaryManager;

	/*
	 * @see com.quanda.common.web.controller.BaseCommandController#handle(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse, java.lang.Object,
	 *      org.springframework.validation.BindException)
	 */
	protected ModelAndView handle(HttpServletRequest request,
			HttpServletResponse response, Object o, BindException errors)
			throws Exception {
		PersonQueryForm form = (PersonQueryForm) o;
		HashMap map = new HashMap();
		// 形成查询条件数据，以便在窗口重新显示
		map.put("formInfo", form);
		// 形成查询条件数据，以传给Service对象进行查询处理，并将结果放入Map以便在页面显示
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(User.class);
		
		if (form.getName() != null && !"".equals(form.getName().trim())) {
			String name = StringUtil.formatQueryStr(form.getName());
			detachedCriteria.add(Restrictions.eq("name", name));
		}
		if (form.getCode() != null && !"".equals(form.getCode().trim())) {
			String code = StringUtil.formatQueryStr(form.getCode());
			detachedCriteria.add(Restrictions.eq("code", code));
		}

		int pageNo = StringUtil.String2Int(form.getPageNo());
		int pageSize = StringUtil.String2Int(form.getRecPerPage());
		
		Pagination pagination = userService.findPageByCriteria(detachedCriteria, pageSize, pageNo);

		// 进行查询处理并将结果放入Map以便在页面显示
		map.put("pagination", pagination);

		return new ModelAndView("common/person/personQuery", map);
	}

	/**
	 * 返回 userService
	 */
	public UserService getUserService() {
		return userService;
	}

	/**
	 * 设置 userService
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	


	
}