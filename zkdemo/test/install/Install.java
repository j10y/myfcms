/**
 * <p>项目名称：</p>
 * <p>版权所有 (c) </p>
 * <p>版本：1.0</p>
 * <p>日期：Mar 31, 2010</p>
 * <p>更新：</p>
 */
package install;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hxzy.common.user.model.Privilege;
import com.hxzy.common.user.model.Role;
import com.hxzy.common.user.model.User;
import com.hxzy.common.user.service.PrivilegeService;
import com.hxzy.common.user.service.RoleService;
import com.hxzy.common.user.service.UserService;

/**
 * @author xiacc
 * 
 * 描述：
 */
public class Install {

	/**
	 * 描述：
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");

//		 初始化用户
		 UserService userService = (UserService) ac.getBean("userService");
				
		 User user = new User();
		 user.setUsername("admin");
		 user.setTruename("超级管理员");
		 user.setPassword("admin");
		 user.setLocked(false);
		 user.setType(0L);
		 userService.save(user);
		
		 // 初始化角色
		 RoleService roleService = (RoleService) ac.getBean("roleService");
				
		 Role role = new Role();
		 role.setRoleName("超级管理员");
		 role.setRemarks("拥有所有权限");
		 role.setCommon(false);
		 roleService.save(role);

		PrivilegeService privilegeService = (PrivilegeService) ac.getBean("privilegeService");

		Privilege privilege = new Privilege();
				
		 privilege.setPrivName("系统管理");
		 privilege.setPrivCode("systemManage");
				
		 privilegeService.save(privilege);

		 Privilege parent = privilegeService.findByProperty("privCode",
		 "systemManage").get(0);
				
		 privilege = new Privilege();
		 privilege.setPrivName("用户管理");
		 privilege.setPrivCode("userQuery");
		 privilege.setParent(parent);
		 privilegeService.save(privilege);

		
	}

	

}
