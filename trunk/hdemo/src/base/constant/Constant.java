package base.constant;

/**
 * <p>��Ŀ���ƣ�����ģ��</p>
 * <p>��Ȩ���� (c) 2005 ����ȫ����Ϣ�Ƽ����޹�˾</p>
 * <p>���ߣ�</p>
 * <p>�汾��1.0</p>
 * <p>���ڣ�2007-9-3</p>
 * <p>���£�</p>
 */

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * <p>
 * ����: Constant
 * </p>
 * <p>
 * ����: ������
 * </p>
 */

public class Constant {

	// Attribute constant
	/**
	 * ����: �����û���Ϣ������Session�е�����
	 */
	public static final String ATTRIBUTE_USER_INFO = "userInfo";

	/**
	 * ����: �����������û���Ϣ��Application Context�е�����
	 */
	public static final String ATTRIBUTE_ONLINE_USER_INFO = "onlineUserInfo";

	/**
	 * ����: ������ʹ�����Application Context�е�����
	 */
	public static final String ATTRIBUTE_ACCESS_RULE = "accessRule";

	/**
	 * ����: �����û���¼��־��Session�е�����
	 */
	public static final String ATTRIBUTE_USER_LOGIN_FLAG = "userLoginFlag";

	/**
	 * ����: ϵͳ��ʾ��Ϣ��request�е����ԣ�ϵͳ��ʾҳ��ȡ������Ϣ��ҳ����ʾ
	 */
	public static final String ATTRIBUTE_SYSTEM_MESSAGE = "systemMessage";

	/**
	 * ����: model������request�е�����
	 */
	public static final String ATTRIBUTE_MODEL_DATA = "modelData";

	/**
	 * ����: Ӧ�ó�������ʵ��������request�е�����
	 */
	public static final String ATTRIBUTE_APP_TASK_INSTANCE = "appTaskInstance";

	/**
	 * ����: ҳ�������url��request�е�����
	 */
	public static final String ATTRIBUTE_REQUEST_URL = "requestUrl";

	/**
	 * ����: ����ҳ��url
	 */
	public static final String ATTRIBUTE_RETURN_URL = "returnUrl";

	/**
	 * ����: WebApplicationContext��ServletContext�е�����
	 */
	public static final String ATTRIBUTE_WEB_APPLICATION_CONTEXT = "webApplicationContext";

	/**
	 * ����: ������־�еĲ�������:����
	 */
	public static final String LOG_ADD = "����";

	/**
	 * ����: ������־�еĲ�������:�޸�
	 */
	public static final String LOG_MODIFY = "�޸�";

	/**
	 * ����: ������־�еĲ�������:ɾ��
	 */
	public static final String LOG_DELETE = "ɾ��";

	// Flag
	/**
	 * ����: �༭��־��������־
	 */
	public static final String FLAG_EDIT_ADD = "add";

	/**
	 * ����: �༭��־��������־
	 */
	public static final String FLAG_EDIT_ADD_END = "addEnd";

	/**
	 * ������ɾ����־
	 */
	public static final String FLAG_EDIT_DEL = "del";

	/**
	 * ��������ӷ���
	 */
	public static final String FLAG_EDIT_ADD_CLASS = "addclass";

	/**
	 * ��������ӷ���
	 */
	public static final String FLAG_EDIT_ADD_CLASS_END = "addclassend";

	/**
	 * ����: �༭��־���޸ı�־
	 */
	public static final String FLAG_EDIT_MODIFY = "modify";

	/**
	 * ����: �༭��־���޸ı�־
	 */
	public static final String FLAG_EDIT_MODIFY_END = "modifyend";

	/**
	 * ����: �༭��־���ظ���־
	 */
	public static final String FLAG_EDIT_REPLAY = "replay";

	/**
	 * ����: ����ȷ����Ŧ
	 */
	public static final String FLAG_SUBMIT_BUTTON_SAVE = "saveButton";

	/**
	 * ����: ����ȷ�����ύ��Ŧ
	 */
	public static final String FLAG_SUBMIT_BUTTON_SAVE_AND_SUBMIT = "saveAndSubmitButton";

	// Date format
	/**
	 * ��������ȷ�����ʱ���ʽ
	 */
	public static final DateFormat DATE_FORMAT_LONG_SECOND = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	/**
	 * ����: ��ȷ�����ӵ�ʱ���ʽ
	 */
	public static final DateFormat DATE_FORMAT_LONG = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm");

	/**
	 * ����: ��ȷ���ܵ�ʱ���ʽ
	 */
	public static final DateFormat DATE_FORMAT_LONG_WEEK = new SimpleDateFormat(
			"yyyy w");

	/**
	 * ����: ��ȷ�����ʱ���ʽ
	 */
	public static final DateFormat DATE_FORMAT_SHORT = new SimpleDateFormat(
			"yyyy-MM-dd");

	/**
	 * ����: ��ȷ���µ�ʱ���ʽ
	 */
	public static final DateFormat DATE_FORMAT_SHORTMONTH = new SimpleDateFormat(
			"yyyy-MM");

	/**
	 * ����: ��ȷ�����ʱ���ʽ
	 */
	public static final DateFormat DATE_FORMAT_YEAR = new SimpleDateFormat(
			"yyyy");

	/**
	 * ����: ��ȷ�����ʱ���ʽ�ַ���
	 */
	public static final String DATE_FORMAT_LONG_SECOND_STRING = "yyyy-MM-dd HH:mm:ss";

	/**
	 * ����: ��ȷ�����ӵ�ʱ���ʽ�ַ���
	 */
	public static final String DATE_FORMAT_LONG_STRING = "yyyy-MM-dd HH:mm";

	/**
	 * ����: ��ȷ�����ӵ�ʱ�����ĸ�ʽ�ַ���
	 */
	public static final String DATE_FORMAT_LONG_CHINESE_STRING = "yyyy��MM��dd�� HH:mm";

	/**
	 * ����: ��ȷ�����ʱ���ʽ�ַ���
	 */
	public static final String DATE_FORMAT_SHORT_STRING = "yyyy-MM-dd";

	/**
	 * ����:��ȷ���µ�ʱ���ʽ�ַ���;
	 */
	public static final String DATE_FORMAT_MONTH_STRING = "yyyy-MM";

	/**
	 * ����: ��ȷ�����ʱ���ʽ�ַ���
	 */
	public static final String DATE_FORMAT_YEAR_STRING = "yyyy";

	// Regular expression
	/**
	 * ����: ��ȷ�����ӵ�ʱ��������ʽ
	 */
	public static final String REGEXP_DATE_LONG = "\\d{4}-(([1-9])|(0[1-9])|(1[0-2]))-(([1-9])|(0[1-9])|([1-3][0-9]))\\s([0-9]|([0-1][0-9])|(2[0-3])):([0-9]|([0-5][0-9]))";

	/**
	 * ����: ��ȷ�����ʱ��������ʽ
	 */
	public static final String REGEXP_DATE_SHORT = "";

	/**
	 * ����: �������ֵ�������ʽ
	 */
	public static final String REGEXP_INTEGER = "^(\\d+)$";

	/**
	 * ����: �û������������ʽ��ֻ������1~20λ���ȵ�Ӣ�ĺ����֣�
	 */
	public static final String REGEXP_PASSWORD = "^((\\w){1,20})$";

	/**
	 * ����: �Ա�_��
	 */
	public static final String SEX_MAN = "��";

	/**
	 * ����: �Ա�_Ů
	 */
	public static final String SEX_WOMAN = "Ů";

	/**
	 * ����: ��Ŀ�����Ϣ
	 */
	public static final String NEWS = "newsChannel";

	/**
	 * ����: ��Ŀ���ר��
	 */
	public static final String SUBJECT = "subjectChannel";
}