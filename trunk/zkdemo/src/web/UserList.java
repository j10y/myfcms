/**
 * <p>项目名称：</p>
 * <p>版权所有 (c) </p>
 * <p>版本：1.0</p>
 * <p>日期：Mar 30, 2010</p>
 * <p>更新：</p>
 */
package web;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Components;
import org.zkoss.zk.ui.util.Composer;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.SimpleListModel;
import org.zkoss.zul.Textbox;

import com.hxzy.base.util.Pagination;
import com.hxzy.common.user.model.User;
import com.hxzy.common.user.service.UserService;

/**
 * @author xiacc
 * 
 * 描述：
 */
public class UserList implements Composer {

	protected Listbox listbox;

	private List list;
	
	@Autowired
	private UserService userService;
	//
	private Textbox search;

	

	public List all() {
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
		Pagination pagination = userService.findPageByCriteria(detachedCriteria, 2, 1);
		this.list = pagination;	
		return pagination;
	}	
	

	/* (non-Javadoc)
	 * @see org.zkoss.zk.ui.util.Composer#doAfterCompose(org.zkoss.zk.ui.Component)
	 */
	public void doAfterCompose(Component comp) throws Exception {
		Components.wireVariables(comp, this);
		Components.addForwards(comp, this);
		
	}
	
	public void onFind(){
		listbox.setModel(new SimpleListModel(all()));
	}


	

}
