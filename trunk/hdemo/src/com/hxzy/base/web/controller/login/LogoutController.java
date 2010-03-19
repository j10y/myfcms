/**
 * <p>项目名称：公共模块</p>
 * <p>版权所有 (c) 2005 湖北全达信息科技有限公司</p>
 * <p>作者：</p>
 * <p>版本：1.0</p>
 * <p>日期：2007-9-4</p>
 * <p>更新：</p>
 */
package com.hxzy.base.web.controller.login;

import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.hxzy.base.constant.Constant;
import com.hxzy.base.web.controller.BaseController;
import com.hxzy.common.log.model.Log;
import com.hxzy.common.user.model.UserInfo;

/**
 * <p>
 * 类名: LogoutController
 * </p>
 * <p>
 * 描述: 注销登录Controller
 * </p>
 */
public class LogoutController extends BaseController {

	/*
	 * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 写操作日志
		if (this.getCurrentUserInfo(request).getUser() != null) {
			Log log = new Log();
			log.setUser(this.getCurrentUserInfo(request).getUser());
			log.setLogObject("用户退出");
			log.setLogAction("退出");
			log.setDetail("退出系统");
			logService.save(log);
		}
		 //从在线用户表中移除
		 UserInfo user = (UserInfo) request.getSession().getAttribute(Constant.ATTRIBUTE_USER_INFO);
		 TreeMap onlineUsers = 
			 (TreeMap) request.getSession().getServletContext().getAttribute(Constant.ATTRIBUTE_ONLINE_USER_INFO);
		 if (onlineUsers.get(user.getUser().getCode()) != null)
		 onlineUsers.remove(user.getUser().getCode());

		request.getSession().invalidate();
		response.sendRedirect(request.getContextPath() + "/login.do");
		return null;

	}

}