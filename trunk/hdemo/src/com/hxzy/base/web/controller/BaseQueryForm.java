/**
 * <p>��Ŀ���ƣ�����ģ��</p>
 * <p>��Ȩ���� (c) 2005 ����ȫ����Ϣ�Ƽ����޹�˾</p>
 * <p>���ߣ�</p>
 * <p>�汾��1.0</p>
 * <p>���ڣ�2005-10-21</p>
 * <p>���£�</p>
 */
package com.hxzy.base.web.controller;

import java.util.Calendar;
import java.util.Date;

import com.hxzy.base.util.DateUtil;

/**
 * <p>
 * ����: BaseQueryForm
 * </p>
 * <p>
 * ����: ��ѯ�������ڻ��࣬���еĲ�ѯ�������ڴӴ���̳�
 * </p>
 */
public class BaseQueryForm extends BaseForm {

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
   * ����: ҳ��
   */
  private String pageNo;

  /**
   * ����: ÿҳ��¼��
   */
  private String recPerPage;

  /**
   * ����: ����
   */
  private String dept;

  /**
   * ����: ��ѯ��־
   */
  private String queryFlag;

  /**
   * ���캯��
   */
  public BaseQueryForm() {
    setDefaultValue();
  }

  /**
   * ����: ����Ĭ��ֵ
   * 
   */
  protected void setDefaultValue() {
    //ʱ�䵽�������Ĭ�Ͽ�ʼʱ��Ϊ����ǰ2�µ�һ�죬����ʱ��Ϊ�������һ��
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
    //end.set(Calendar.HOUR_OF_DAY,
    // end.getActualMaximum(Calendar.HOUR_OF_DAY));
    //end.set(Calendar.MINUTE, end.getActualMaximum(Calendar.MINUTE));
    //ʱ�䵽���ӵ�����Ĭ�Ͽ�ʼʱ��Ϊ����ǰ2�µ�һ����ʱ��֣�����ʱ��Ϊ���µ�һ����ʱ���
    beginDateTime = DateUtil.toStringInYearMonthDayHourMinPattern(begin.getTime());
    begin.add(Calendar.MONTH, 3);
    endDateTime = DateUtil.toStringInYearMonthDayHourMinPattern(begin.getTime());
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
   * ����: ���� dept
   */
  public String getDept() {
    return dept;
  }

  /**
   * ����: ���� dept
   */
  public void setDept(String dept) {
    this.dept = dept;
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

  /**
   * ����: ���� pageNo
   */
  public String getPageNo() {
    return pageNo;
  }

  /**
   * ����: ���� pageNo
   */
  public void setPageNo(String pageNo) {
    this.pageNo = pageNo;
  }

  /**
   * ����: ���� recPerPage
   */
  public String getRecPerPage() {
    return recPerPage;
  }

  /**
   * ����: ���� recPerPage
   */
  public void setRecPerPage(String recPerPage) {
    this.recPerPage = recPerPage;
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
   * ����: ���� queryFlag
   */
  public String getQueryFlag() {
    return queryFlag;
  }

  /**
   * ����: ���� queryFlag
   */
  public void setQueryFlag(String queryFlag) {
    this.queryFlag = queryFlag;
  }
}

