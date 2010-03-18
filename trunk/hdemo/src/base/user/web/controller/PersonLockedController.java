/**
 * <p>项目名称：湖北国税网站</p>
 * <p>版权所有 (c) 2007 湖北全达信息科技有限公司</p>
 * <p>作者：</p>
 * <p>版本：1.0</p>
 * <p>日期：2007-8-17</p>
 * <p>更新：</p>
 */
package base.user.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.RequestUtils;
import org.springframework.web.servlet.ModelAndView;

import base.constant.Constant;
import base.user.model.BaseUser;
import base.user.service.BaseUserService;
import base.web.controller.BaseController;

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
    private BaseUserService baseUserService;

    /*
     * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse)
     */
    protected ModelAndView handleRequestInternal(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        //获取角色ID
        Long id = RequestUtils.getLongParameter(request, "id", 0);
        BaseUser person = baseUserService.findById(id);
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
        baseUserService.update(person);

        return new ModelAndView("redirect:"
                + RequestUtils.getStringParameter(request,
                        Constant.ATTRIBUTE_RETURN_URL, ""));
    }

	/**
	 * 返回 baseUserService
	 */
	public BaseUserService getBaseUserService() {
		return baseUserService;
	}

	/**
	 * 设置 baseUserService
	 */
	public void setBaseUserService(BaseUserService baseUserService) {
		this.baseUserService = baseUserService;
	}

    
  
}