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

import com.hxzy.common.user.model.User;
import com.hxzy.common.user.service.UserService;

/**
 * @author xiacc
 *
 * ������
 */
public class Install {

	/**
	 * ������
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		UserService userService = (UserService) ac.getBean("userService");
		
		//��ʼ���û�
		User user = new User();		
		user.setUsername("admin5");
		user.setTruename("��������Ա2");
		user.setPassword("admin2");
		user.setLocked(false);
		user.setType(0L);		
		userService.save(user);


	}

}
