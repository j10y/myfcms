/**
 * <p>项目名称：湖北国税网站</p>
 * <p>版权所有 (c) 2007 湖北全达信息科技有限公司</p>
 * <p>作者：</p>
 * <p>版本：1.0</p>
 * <p>日期：2007-8-17</p>
 * <p>更新：</p>
 */
package com.hxzy.common.user.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.RequestUtils;
import org.springframework.web.servlet.ModelAndView;

import com.hxzy.base.constant.Constant;
import com.hxzy.base.web.controller.BaseController;
import com.hxzy.common.user.model.User;
import com.hxzy.common.user.service.UserService;

/**
 * <p>
 * 类名: PersonLockedController
 * </p>
 * <p>
 * 描述: 解除用户锁定Controller
 * </p>
 */
public class PersonLockedController extends BaseController {
    /**
     * 描述: 人员Manager
     */
    private UserService userService;

    /*
     * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse)
     */
    protected ModelAndView handleRequestInternal(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        //获取角色ID
        Long id = RequestUtils.getLongParameter(request, "id", 0);
        User person = userService.findById(id);
        //判断要删除的记录是否存在，如不存在则转入提示页面
        if (person == null) {
            return new ModelAndView("systemMessage",
                    Constant.ATTRIBUTE_SYSTEM_MESSAGE, this.getMessage(
                            "public.msg.dataDoesNotExist", request));
        }
        //解除角色锁定
        if (person.getIsLocked().longValue() == 1L){
            person.setIsLocked(new Long(0));
            person.setLockedTime(null);
        }
        //角色锁定
        else
            person.setIsLocked(new Long(1));
        userService.update(person);

        return new ModelAndView("redirect:"
                + RequestUtils.getStringParameter(request,
                        Constant.ATTRIBUTE_RETURN_URL, ""));
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