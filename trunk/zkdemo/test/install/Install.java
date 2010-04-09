/**
 * <p>��Ŀ���ƣ�</p>
 * <p>��Ȩ���� (c) </p>
 * <p>�汾��1.0</p>
 * <p>���ڣ�Mar 31, 2010</p>
 * <p>���£�</p>
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
 * ������
 */
public class Install {

	/**
	 * ������
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");

//		 ��ʼ���û�
		 UserService userService = (UserService) ac.getBean("userService");
				
		 User user = new User();
		 user.setUsername("admin");
		 user.setTruename("��������Ա");
		 user.setPassword("admin");
		 user.setLocked(false);
		 user.setType(0L);
		 userService.save(user);
		
		 // ��ʼ����ɫ
		 RoleService roleService = (RoleService) ac.getBean("roleService");
				
		 Role role = new Role();
		 role.setRoleName("��������Ա");
		 role.setRemarks("ӵ������Ȩ��");
		 role.setCommon(false);
		 roleService.save(role);

		PrivilegeService privilegeService = (PrivilegeService) ac.getBean("privilegeService");

		Privilege privilege = new Privilege();
				
		 privilege.setPrivName("ϵͳ����");
		 privilege.setPrivCode("systemManage");
				
		 privilegeService.save(privilege);

		 Privilege parent = privilegeService.findByProperty("privCode",
		 "systemManage").get(0);
				
		 privilege = new Privilege();
		 privilege.setPrivName("�û�����");
		 privilege.setPrivCode("userQuery");
		 privilege.setParent(parent);
		 privilegeService.save(privilege);

		
	}

	

}
