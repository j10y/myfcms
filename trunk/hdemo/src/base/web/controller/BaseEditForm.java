/**
 * <p>��Ŀ���ƣ��人��ͬ��Ʊ������ϵͳ����ת��ƽ̨</p>
 * <p>��Ȩ���� (c) 2005 ����ȫ����Ϣ�Ƽ����޹�˾</p>
 * <p>���ߣ�</p>
 * <p>�汾��1.0</p>
 * <p>���ڣ�2008-11-19</p>
 * <p>���£�</p>
 */
package base.web.controller;

import java.util.Calendar;
import java.util.Date;

import base.util.DateUtil;

public class BaseEditForm extends BaseForm {

	/**
	 * ����: ��ʼʱ��
	 */
	private String beginDateTime;

	/**
	 * ����: ����ʱ��
	 */
	private String endDateTime;

	/**
	 * ����: ��ʼ����
	 */
	private String beginDate;

	/**
	 * ����: ��������
	 */
	private String endDate;

	/**
	 * ����: �༭��ʶ��
	 */
	private String editFlag;

	public BaseEditForm() {
		setDefaultValue();
	}

	/**
	 * ����: ����Ĭ��ֵ
	 * 
	 */
	protected void setDefaultValue() {
		// ʱ�䵽�������Ĭ�Ͽ�ʼʱ��Ϊ����ǰ2�µ�һ�죬����ʱ��Ϊ�������һ��
		Calendar begin = Calendar.getInstance();
		Calendar end = Calendar.getInstance();
		Date nowDateTime = DateUtil.getNowPreciseToDay();
		begin.setTime(nowDateTime);
		end.setTime(nowDateTime);
		begin.set(Calendar.DAY_OF_MONTH, 1);
		begin.add(Calendar.MONTH, -2);
		int dayOfMonth = end.getActualMaximum(Calendar.DAY_OF_MONTH);
		end.set(Calendar.DAY_OF_MONTH, dayOfMonth);
		beginDate = DateUtil.toStringInYearMonthDayPattern(begin.getTime());
		endDate = DateUtil.toStringInYearMonthDayPattern(end.getTime());
		// end.set(Calendar.HOUR_OF_DAY,
		// end.getActualMaximum(Calendar.HOUR_OF_DAY));
		// end.set(Calendar.MINUTE, end.getActualMaximum(Calendar.MINUTE));
		// ʱ�䵽���ӵ�����Ĭ�Ͽ�ʼʱ��Ϊ����ǰ2�µ�һ����ʱ��֣�����ʱ��Ϊ���µ�һ����ʱ���
		beginDateTime = DateUtil.toStringInYearMonthDayHourMinPattern(begin
				.getTime());
		begin.add(Calendar.MONTH, 3);
		endDateTime = DateUtil.toStringInYearMonthDayHourMinPattern(begin
				.getTime());
	}

	/**
	 * ����: ���� beginDate
	 */
	public String getBeginDate() {
		return beginDate;
	}

	/**
	 * ����: ���� beginDate
	 */
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	/**
	 * ����: ���� beginDateTime
	 */
	public String getBeginDateTime() {
		return beginDateTime;
	}

	/**
	 * ����: ���� beginDateTime
	 */
	public void setBeginDateTime(String beginDateTime) {
		this.beginDateTime = beginDateTime;
	}

	/**
	 * ����: ���� editFlag
	 */
	public String getEditFlag() {
		return editFlag;
	}

	/**
	 * ����: ���� editFlag
	 */
	public void setEditFlag(String editFlag) {
		this.editFlag = editFlag;
	}

	/**
	 * ����: ���� endDate
	 */
	public String getEndDate() {
		return endDate;
	}

	/**
	 * ����: ���� endDate
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/**
	 * ����: ���� endDateTime
	 */
	public String getEndDateTime() {
		return endDateTime;
	}

	/**
	 * ����: ���� endDateTime
	 */
	public void setEndDateTime(String endDateTime) {
		this.endDateTime = endDateTime;
	}

}
