/**
 * <p>项目名称：</p>
 * <p>版权所有 (c) </p>
 * <p>版本：1.0</p>
 * <p>日期：Mar 26, 2010</p>
 * <p>更新：</p>
 */
package web;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.zkoss.zul.Textbox;

import com.hxzy.base.util.Pagination;
import com.hxzy.base.web.intercepter.Authenticatable;
import com.hxzy.common.user.model.User;
import com.hxzy.common.user.service.UserService;

/**
 * @author xiacc
 *
 * 描述：
 */
public class UserWindow extends BaseWindow implements Authenticatable{
	
	@Autowired
	private UserService userService;
	
	private Textbox search;
	
	private String code;
	
	private String needAuthentication;

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
	

	/* (non-Javadoc)
	 * @see web.BaseWindow#find(java.lang.String, java.lang.String, java.lang.Object[])
	 */
	@Override
	public void onFind() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(User.class);

		if(StringUtils.hasText(search.getValue())){
			detachedCriteria.add(
					Restrictions.or(
							Restrictions.like("name",search.getValue(),MatchMode.ANYWHERE),
							Restrictions.like("code",search.getValue(),MatchMode.ANYWHERE)
					));
			
			detachedCriteria.add(
					Restrictions.or(
							Restrictions.like("password",search.getValue(),MatchMode.ANYWHERE),
							Restrictions.like("code",search.getValue(),MatchMode.ANYWHERE)
					));
		}
		Pagination pagination = userService.findPageByCriteria(detachedCriteria, pg.getPageSize(), pg.getActivePage()+1);
		pg.setTotalSize(pagination.getTotalCount());
		this.list = pagination;		
		binder.loadComponent(listbox);
		
	}

	/**
	 * 返回 code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 设置 code
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/* (non-Javadoc)
	 * @see com.hxzy.base.web.intercepter.Authenticatable#needAuthentication()
	 */
	public boolean needAuthentication() {
		if ("false".equals(needAuthentication))
			return false;
		else
			return true;
	}

	/**
	 * 设置 needAuthentication
	 */
	public void setNeedAuthentication(String needAuthentication) {
		this.needAuthentication = needAuthentication;
	}
	
	
	
	

}
