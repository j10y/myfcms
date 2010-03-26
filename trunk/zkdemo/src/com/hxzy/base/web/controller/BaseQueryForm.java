/**
 * <p>项目名称：公共模块</p>
 * <p>版权所有 (c) 2005 湖北全达信息科技有限公司</p>
 * <p>作者：</p>
 * <p>版本：1.0</p>
 * <p>日期：2005-10-21</p>
 * <p>更新：</p>
 */
package com.hxzy.base.web.controller;

import java.util.Calendar;
import java.util.Date;

import com.hxzy.base.util.DateUtil;

/**
 * <p>
 * 类名: BaseQueryForm
 * </p>
 * <p>
 * 描述: 查询条件窗口基类，所有的查询条件窗口从此类继承
 * </p>
 */
public class BaseQueryForm extends BaseForm {

  /**
   * 描述: 开始时间
   */
  private String beginDateTime;

  /**
   * 描述: 结束时间
   */
  private String endDateTime;

  /**
   * 描述: 开始日期
   */
  private String beginDate;

  /**
   * 描述: 结束日期
   */
  private String endDate;

  /**
   * 描述: 页号
   */
  private String pageNo;

  /**
   * 描述: 每页记录数
   */
  private String recPerPage;

  /**
   * 描述: 部门
   */
  private String dept;

  /**
   * 描述: 查询标志
   */
  private String queryFlag;

  /**
   * 构造函数
   */
  public BaseQueryForm() {
    setDefaultValue();
  }

  /**
   * 描述: 设置默认值
   * 
   */
  protected void setDefaultValue() {
    //时间到天的设置默认开始时间为本月前2月第一天，结束时间为本月最后一天
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
    //时间到分钟的设置默认开始时间为本月前2月第一天零时零分，结束时间为下月第一天零时零分
    beginDateTime = DateUtil.toStringInYearMonthDayHourMinPattern(begin.getTime());
    begin.add(Calendar.MONTH, 3);
    endDateTime = DateUtil.toStringInYearMonthDayHourMinPattern(begin.getTime());
  }

  /**
   * 描述: 返回 beginDateTime
   */
  public String getBeginDateTime() {
    return beginDateTime;
  }

  /**
   * 描述: 设置 beginDateTime
   */
  public void setBeginDateTime(String beginDateTime) {
    this.beginDateTime = beginDateTime;
  }

  /**
   * 描述: 返回 dept
   */
  public String getDept() {
    return dept;
  }

  /**
   * 描述: 设置 dept
   */
  public void setDept(String dept) {
    this.dept = dept;
  }

  /**
   * 描述: 返回 endDateTime
   */
  public String getEndDateTime() {
    return endDateTime;
  }

  /**
   * 描述: 设置 endDateTime
   */
  public void setEndDateTime(String endDateTime) {
    this.endDateTime = endDateTime;
  }

  /**
   * 描述: 返回 pageNo
   */
  public String getPageNo() {
    return pageNo;
  }

  /**
   * 描述: 设置 pageNo
   */
  public void setPageNo(String pageNo) {
    this.pageNo = pageNo;
  }

  /**
   * 描述: 返回 recPerPage
   */
  public String getRecPerPage() {
    return recPerPage;
  }

  /**
   * 描述: 设置 recPerPage
   */
  public void setRecPerPage(String recPerPage) {
    this.recPerPage = recPerPage;
  }

  /**
   * 描述: 返回 beginDate
   */
  public String getBeginDate() {
    return beginDate;
  }

  /**
   * 描述: 设置 beginDate
   */
  public void setBeginDate(String beginDate) {
    this.beginDate = beginDate;
  }

  /**
   * 描述: 返回 endDate
   */
  public String getEndDate() {
    return endDate;
  }

  /**
   * 描述: 设置 endDate
   */
  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }

  /**
   * 描述: 返回 queryFlag
   */
  public String getQueryFlag() {
    return queryFlag;
  }

  /**
   * 描述: 设置 queryFlag
   */
  public void setQueryFlag(String queryFlag) {
    this.queryFlag = queryFlag;
  }
}

