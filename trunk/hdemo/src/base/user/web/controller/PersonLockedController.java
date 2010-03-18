/**
 * <p>��Ŀ���ƣ�������˰��վ</p>
 * <p>��Ȩ���� (c) 2007 ����ȫ����Ϣ�Ƽ����޹�˾</p>
 * <p>���ߣ�</p>
 * <p>�汾��1.0</p>
 * <p>���ڣ�2007-8-17</p>
 * <p>���£�</p>
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
 * ����: PersonLockedController
 * </p>
 * <p>
 * ����: ����û�����Controller
 * </p>
 */
public class PersonLockedController extends BaseController {
    /**
     * ����: ��ԱManager
     */
    private BaseUserService baseUserService;

    /*
     * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse)
     */
    protected ModelAndView handleRequestInternal(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        //��ȡ��ɫID
        Long id = RequestUtils.getLongParameter(request, "id", 0);
        BaseUser person = baseUserService.findById(id);
        //�ж�Ҫɾ���ļ�¼�Ƿ���ڣ��粻������ת����ʾҳ��
        if (person == null) {
            return new ModelAndView("systemMessage",
                    Constant.ATTRIBUTE_SYSTEM_MESSAGE, this.getMessage(
                            "public.msg.dataDoesNotExist", request));
        }
        //�����ɫ����
        if (person.getIsLocked().longValue() == 1L){
            person.setIsLocked(new Long(0));
            person.setLockedTime(null);
        }
        //��ɫ����
        else
            person.setIsLocked(new Long(1));
        baseUserService.update(person);

        return new ModelAndView("redirect:"
                + RequestUtils.getStringParameter(request,
                        Constant.ATTRIBUTE_RETURN_URL, ""));
    }

	/**
	 * ���� baseUserService
	 */
	public BaseUserService getBaseUserService() {
		return baseUserService;
	}

	/**
	 * ���� baseUserService
	 */
	public void setBaseUserService(BaseUserService baseUserService) {
		this.baseUserService = baseUserService;
	}

    
  
}