package com.hxzy.base.constant;

/**
 * <p>项目名称：公共模块</p>
 * <p>版权所有 (c) 2005 湖北全达信息科技有限公司</p>
 * <p>作者：</p>
 * <p>版本：1.0</p>
 * <p>日期：2007-9-3</p>
 * <p>更新：</p>
 */

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * <p>
 * 类名: Constant
 * </p>
 * <p>
 * 描述: 常量类
 * </p>
 */

public class Constant {

	// Attribute constant
	/**
	 * 描述: 操作用户信息对象在Session中的属性
	 */
	public static final String ATTRIBUTE_USER_INFO = "userInfo";

	/**
	 * 描述: 在所有在线用户信息在Application Context中的属性
	 */
	public static final String ATTRIBUTE_ONLINE_USER_INFO = "onlineUserInfo";

	/**
	 * 描述: 网络访问规则在Application Context中的属性
	 */
	public static final String ATTRIBUTE_ACCESS_RULE = "accessRule";

	/**
	 * 描述: 操作用户登录标志在Session中的属性
	 */
	public static final String ATTRIBUTE_USER_LOGIN_FLAG = "userLoginFlag";

	/**
	 * 描述: 系统提示信息在request中的属性，系统提示页面取出此信息在页面显示
	 */
	public static final String ATTRIBUTE_SYSTEM_MESSAGE = "systemMessage";

	/**
	 * 描述: model数据在request中的属性
	 */
	public static final String ATTRIBUTE_MODEL_DATA = "modelData";

	/**
	 * 描述: 应用程序流程实例数据在request中的属性
	 */
	public static final String ATTRIBUTE_APP_TASK_INSTANCE = "appTaskInstance";

	/**
	 * 描述: 页面请求的url在request中的属性
	 */
	public static final String ATTRIBUTE_REQUEST_URL = "requestUrl";

	/**
	 * 描述: 返回页面url
	 */
	public static final String ATTRIBUTE_RETURN_URL = "returnUrl";

	/**
	 * 描述: WebApplicationContext在ServletContext中的属性
	 */
	public static final String ATTRIBUTE_WEB_APPLICATION_CONTEXT = "webApplicationContext";

	/**
	 * 描述: 操作日志中的操作类型:增加
	 */
	public static final String LOG_ADD = "增加";

	/**
	 * 描述: 操作日志中的操作类型:修改
	 */
	public static final String LOG_MODIFY = "修改";

	/**
	 * 描述: 操作日志中的操作类型:删除
	 */
	public static final String LOG_DELETE = "删除";

	// Flag
	/**
	 * 描述: 编辑标志：新增标志
	 */
	public static final String FLAG_EDIT_ADD = "add";

	/**
	 * 描述: 编辑标志：新增标志
	 */
	public static final String FLAG_EDIT_ADD_END = "addEnd";

	/**
	 * 描述：删除标志
	 */
	public static final String FLAG_EDIT_DEL = "del";

	/**
	 * 描述：添加分类
	 */
	public static final String FLAG_EDIT_ADD_CLASS = "addclass";

	/**
	 * 描述：添加分类
	 */
	public static final String FLAG_EDIT_ADD_CLASS_END = "addclassend";

	/**
	 * 描述: 编辑标志：修改标志
	 */
	public static final String FLAG_EDIT_MODIFY = "modify";

	/**
	 * 描述: 编辑标志：修改标志
	 */
	public static final String FLAG_EDIT_MODIFY_END = "modifyend";

	/**
	 * 描述: 编辑标志：回复标志
	 */
	public static final String FLAG_EDIT_REPLAY = "replay";

	/**
	 * 描述: 保存确定按纽
	 */
	public static final String FLAG_SUBMIT_BUTTON_SAVE = "saveButton";

	/**
	 * 描述: 保存确定并提交按纽
	 */
	public static final String FLAG_SUBMIT_BUTTON_SAVE_AND_SUBMIT = "saveAndSubmitButton";

	// Date format
	/**
	 * 描述：精确到秒的时间格式
	 */
	public static final DateFormat DATE_FORMAT_LONG_SECOND = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	/**
	 * 描述: 精确到分钟的时间格式
	 */
	public static final DateFormat DATE_FORMAT_LONG = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm");

	/**
	 * 描述: 精确到周的时间格式
	 */
	public static final DateFormat DATE_FORMAT_LONG_WEEK = new SimpleDateFormat(
			"yyyy w");

	/**
	 * 描述: 精确到天的时间格式
	 */
	public static final DateFormat DATE_FORMAT_SHORT = new SimpleDateFormat(
			"yyyy-MM-dd");

	/**
	 * 描述: 精确到月的时间格式
	 */
	public static final DateFormat DATE_FORMAT_SHORTMONTH = new SimpleDateFormat(
			"yyyy-MM");

	/**
	 * 描述: 精确到年的时间格式
	 */
	public static final DateFormat DATE_FORMAT_YEAR = new SimpleDateFormat(
			"yyyy");

	/**
	 * 描述: 精确到秒的时间格式字符串
	 */
	public static final String DATE_FORMAT_LONG_SECOND_STRING = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 描述: 精确到分钟的时间格式字符串
	 */
	public static final String DATE_FORMAT_LONG_STRING = "yyyy-MM-dd HH:mm";

	/**
	 * 描述: 精确到分钟的时间中文格式字符串
	 */
	public static final String DATE_FORMAT_LONG_CHINESE_STRING = "yyyy年MM月dd日 HH:mm";

	/**
	 * 描述: 精确到天的时间格式字符串
	 */
	public static final String DATE_FORMAT_SHORT_STRING = "yyyy-MM-dd";

	/**
	 * 描述:精确到月的时间格式字符串;
	 */
	public static final String DATE_FORMAT_MONTH_STRING = "yyyy-MM";

	/**
	 * 描述: 精确到年的时间格式字符串
	 */
	public static final String DATE_FORMAT_YEAR_STRING = "yyyy";

	// Regular expression
	/**
	 * 描述: 精确到分钟的时间正则表达式
	 */
	public static final String REGEXP_DATE_LONG = "\\d{4}-(([1-9])|(0[1-9])|(1[0-2]))-(([1-9])|(0[1-9])|([1-3][0-9]))\\s([0-9]|([0-1][0-9])|(2[0-3])):([0-9]|([0-5][0-9]))";

	/**
	 * 描述: 精确到天的时间正则表达式
	 */
	public static final String REGEXP_DATE_SHORT = "";

	/**
	 * 描述: 整型数字的正则表达式
	 */
	public static final String REGEXP_INTEGER = "^(\\d+)$";

	/**
	 * 描述: 用户密码的正则表达式（只能输入1~20位长度的英文和数字）
	 */
	public static final String REGEXP_PASSWORD = "^((\\w){1,20})$";

	/**
	 * 描述: 性别_男
	 */
	public static final String SEX_MAN = "男";

	/**
	 * 描述: 性别_女
	 */
	public static final String SEX_WOMAN = "女";

	/**
	 * 描述: 栏目类别：信息
	 */
	public static final String NEWS = "newsChannel";

	/**
	 * 描述: 栏目类别：专题
	 */
	public static final String SUBJECT = "subjectChannel";
}