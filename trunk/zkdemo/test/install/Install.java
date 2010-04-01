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

import com.hxzy.common.user.model.User;
import com.hxzy.common.user.service.UserService;

/**
 * @author xiacc
 *
 * 描述：
 */
public class Install {

	/**
	 * 描述：
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		UserService userService = (UserService) ac.getBean("userService");
		
		//初始化用户
		User user = new User();		
		user.setUsername("admin5");
		user.setTruename("超级管理员2");
		user.setPassword("admin2");
		user.setLocked(false);
		user.setType(0L);		
		userService.save(user);


	}

}
