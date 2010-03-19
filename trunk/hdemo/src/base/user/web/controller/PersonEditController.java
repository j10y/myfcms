/**
 * <p>项目名称：公共模块</p>
 * <p>版权所有 (c) 2005 湖北全达信息科技有限公司</p>
 * <p>作者：</p>
 * <p>版本：1.0</p>
 * <p>日期：2007-9-3</p>
 * <p>更新：2007-9-4:增加人员口令格式判断，人员代码检测</p>
 */
package com.hxzy.common.user.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.bind.RequestUtils;
import org.springframework.web.servlet.ModelAndView;

import com.hxzy.base.constant.Constant;
import com.hxzy.base.exception.ApplicationException;
import com.hxzy.base.util.StringUtil;
import com.hxzy.base.web.controller.BaseFormController;
import com.hxzy.common.user.model.User;
import com.hxzy.common.user.model.UserInfo;
import com.hxzy.common.user.service.UserService;

/**
 * <p>
 * 类名: PersonEditController
 * </p>
 * <p>
 * 描述: 用户编辑Controller
 * </p>
 */
public class PersonEditController extends BaseFormController {

    /**
     * 描述: 用户Manager
     */
    private UserService userService;
    
    
    /*
     * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
     */
    protected Object formBackingObject(HttpServletRequest request)
            throws Exception {
        // 获取编辑标志
        String editFlag = RequestUtils.getStringParameter(request, "editFlag",
                "");

        // 创建一个空的Command对象
        PersonEditForm form = new PersonEditForm();
        // 根据编辑标志进行相关处理(新增时返回一个空Command对象，修改时返回一个包含数据的Command对象)
        if (Constant.FLAG_EDIT_MODIFY.equals(editFlag)) {
            // 修改时的处理,获取角色记录
            Long id = StringUtil.stringToLong(RequestUtils.getStringParameter(
                    request, "id", "0"));
            User person = userService.findById(id);
            if (person == null)
                throw new ApplicationException("exception.msg.dataDoesNotExist");
            // 将数据放入request以便使用
            request.setAttribute(Constant.ATTRIBUTE_MODEL_DATA, person);
            form.setId(person.getId().toString());
            form.setCode(person.getCode());
            form.setName(person.getName());
            form.setUserFlag(person.getUserFlag().toString());
        }
        return form;
    }

    /*
     * @see org.springframework.web.servlet.mvc.BaseCommandController#onBind(javax.servlet.http.HttpServletRequest,
     *      java.lang.Object, org.springframework.validation.BindException)
     */
    protected void onBind(HttpServletRequest request, Object o,
            BindException errors) throws Exception {
        PersonEditForm form = (PersonEditForm) o;
        if (form.getPassword() == null)
            form.setPassword("");
        // 判断用户是否已经存在
        if (Constant.FLAG_EDIT_ADD.equals(form.getEditFlag())) {
            List valiPersons = userService.findByProperty("code",form.getCode().trim());
            if (valiPersons != null && !valiPersons.isEmpty()) {
                String[] valiCode = new String[1];
                valiCode[0] = form.getCode().trim();
                errors.reject("public.msg.objectExist", valiCode, "");
            }
        }
        // 判断新口令是否符合规定的格式
        if (!(StringUtil.isMatcherPattern(Constant.REGEXP_PASSWORD, form
                .getPassword().trim())))
            errors.reject("person.msg.invaidPasswordFormat", null, "");
        // 判断口令与重复口令是否一致
        if (!form.getPassword().trim().equals(form.getPasswordRepeat().trim())) {
            errors.reject(
                    "person.msg.repeatPasswordIsNotIdenticalWithPassword",
                    null, "");
        }
    }

    /*
     * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse, java.lang.Object,
     *      org.springframework.validation.BindException)
     */
    protected ModelAndView onSubmit(HttpServletRequest request,
            HttpServletResponse response, Object o, BindException errors)
            throws Exception {
        Long childSiteId = (Long) request.getSession().getAttribute(
                "childSiteId");
        UserInfo user = (UserInfo) request.getSession().getAttribute(
                Constant.ATTRIBUTE_USER_INFO);
        PersonEditForm form = (PersonEditForm) o;
        User person = null;
        // 新增时的处理
        if (Constant.FLAG_EDIT_ADD.equals(form.getEditFlag())) {

        	person = new User();
            person.setCode(form.getCode().trim());
            person.setName(form.getName().trim());
            // String password = MD5.md5(form.getPassword().trim());
            // person.setPassword(password);
            person.setPassword(form.getPassword().trim());
            person.setUserFlag(new Long(0));
            person.setLastTime(null);
            person.setLoginFrequency(new Long(0));
            person.setIsLocked(new Long(0));
            person.setLockedTime(null);
            // 写入数据库
            userService.save(person);
        }
        // 修改时的处理
        if (Constant.FLAG_EDIT_MODIFY.equals(form.getEditFlag())) {
            person = (User) request.getAttribute(Constant.ATTRIBUTE_MODEL_DATA);
            person.setName(form.getName().trim());
            // 判断口令是否有输入，如无则不做修改
            if (!form.getPassword().equals(""))
                person.setPassword(form.getPassword().trim());
            // person.setPassword(MD5.md5(form.getPassword().trim()));
            person.setUserFlag(StringUtil.stringToLong(form.getUserFlag()));
            userService.update(person);
        }

        // 写操作日志
//        Log log = new Log();
//        log.setUser(this.getCurrentUserInfo(request).getUser());
//        log.setLogObject("用户");
//        if (Constant.FLAG_EDIT_MODIFY.equals(form.getEditFlag())) {
//            log.setLogAction("修改");
//            log.setDetail("修改用户:" + person.getName());
//
//        }
//        if (Constant.FLAG_EDIT_ADD.equals(form.getEditFlag())) {
//            log.setLogAction("增加");
//            log.setDetail("增加用户:" + person.getName());
//
//        }
//        logService.save(log);

        return new ModelAndView("redirect:" + form.getReturnUrl());
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