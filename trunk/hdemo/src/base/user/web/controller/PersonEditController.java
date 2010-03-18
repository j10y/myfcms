/**
 * <p>��Ŀ���ƣ�����ģ��</p>
 * <p>��Ȩ���� (c) 2005 ����ȫ����Ϣ�Ƽ����޹�˾</p>
 * <p>���ߣ�</p>
 * <p>�汾��1.0</p>
 * <p>���ڣ�2007-9-3</p>
 * <p>���£�2007-9-4:������Ա�����ʽ�жϣ���Ա������</p>
 */
package base.user.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.bind.RequestUtils;
import org.springframework.web.servlet.ModelAndView;

import base.constant.Constant;
import base.exception.ApplicationException;
import base.log.model.Log;
import base.user.model.BaseUser;
import base.user.model.UserInfo;
import base.user.service.BaseUserService;
import base.util.StringUtil;
import base.web.controller.BaseFormController;

/**
 * <p>
 * ����: PersonEditController
 * </p>
 * <p>
 * ����: �û��༭Controller
 * </p>
 */
public class PersonEditController extends BaseFormController {

    /**
     * ����: �û�Manager
     */
    private BaseUserService baseUserService;
    
    
    /*
     * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
     */
    protected Object formBackingObject(HttpServletRequest request)
            throws Exception {
        // ��ȡ�༭��־
        String editFlag = RequestUtils.getStringParameter(request, "editFlag",
                "");

        // ����һ���յ�Command����
        PersonEditForm form = new PersonEditForm();
        // ���ݱ༭��־������ش���(����ʱ����һ����Command�����޸�ʱ����һ���������ݵ�Command����)
        if (Constant.FLAG_EDIT_MODIFY.equals(editFlag)) {
            // �޸�ʱ�Ĵ���,��ȡ��ɫ��¼
            Long id = StringUtil.stringToLong(RequestUtils.getStringParameter(
                    request, "id", "0"));
            BaseUser person = baseUserService.findById(id);
            if (person == null)
                throw new ApplicationException("exception.msg.dataDoesNotExist");
            // �����ݷ���request�Ա�ʹ��
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
        // �ж��û��Ƿ��Ѿ�����
        if (Constant.FLAG_EDIT_ADD.equals(form.getEditFlag())) {
            List valiPersons = baseUserService.findByProperty("code",form.getCode().trim());
            if (valiPersons != null && !valiPersons.isEmpty()) {
                String[] valiCode = new String[1];
                valiCode[0] = form.getCode().trim();
                errors.reject("public.msg.objectExist", valiCode, "");
            }
        }
        // �ж��¿����Ƿ���Ϲ涨�ĸ�ʽ
        if (!(StringUtil.isMatcherPattern(Constant.REGEXP_PASSWORD, form
                .getPassword().trim())))
            errors.reject("person.msg.invaidPasswordFormat", null, "");
        // �жϿ������ظ������Ƿ�һ��
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
        BaseUser person = null;
        // ����ʱ�Ĵ���
        if (Constant.FLAG_EDIT_ADD.equals(form.getEditFlag())) {

        	person = new BaseUser();
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
            // д�����ݿ�
            baseUserService.save(person);
        }
        // �޸�ʱ�Ĵ���
        if (Constant.FLAG_EDIT_MODIFY.equals(form.getEditFlag())) {
            person = (BaseUser) request.getAttribute(Constant.ATTRIBUTE_MODEL_DATA);
            person.setName(form.getName().trim());
            // �жϿ����Ƿ������룬���������޸�
            if (!form.getPassword().equals(""))
                person.setPassword(form.getPassword().trim());
            // person.setPassword(MD5.md5(form.getPassword().trim()));
            person.setUserFlag(StringUtil.stringToLong(form.getUserFlag()));
            baseUserService.update(person);
        }

        // д������־
//        Log log = new Log();
//        log.setUser(this.getCurrentUserInfo(request).getUser());
//        log.setLogObject("�û�");
//        if (Constant.FLAG_EDIT_MODIFY.equals(form.getEditFlag())) {
//            log.setLogAction("�޸�");
//            log.setDetail("�޸��û�:" + person.getName());
//
//        }
//        if (Constant.FLAG_EDIT_ADD.equals(form.getEditFlag())) {
//            log.setLogAction("����");
//            log.setDetail("�����û�:" + person.getName());
//
//        }
//        logService.save(log);

        return new ModelAndView("redirect:" + form.getReturnUrl());
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