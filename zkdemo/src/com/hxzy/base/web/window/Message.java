/**
 * <p>项目名称：</p>
 * <p>版权所有 (c) </p>
 * <p>版本：1.0</p>
 * <p>日期：Apr 1, 2010</p>
 * <p>更新：</p>
 */
package com.hxzy.base.web.window;

import org.zkoss.zul.Messagebox;

/**
 * 
 * @功能 :对话框的封装
 * @创建人 gao_jie
 * @创建日期 Mar 4, 2009
 * @版本 1.0
 * 
 */
public class Message {
	/**
	 * 显示提示信息
	 * 
	 * @param value
	 */
	public static void showInfo(String value) {
		try {
			Messagebox.show(value, "提示", Messagebox.OK, Messagebox.INFORMATION);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 显示询问信息
	 * 
	 * @param value
	 * @return
	 */
	public static int showQuestion(String value) {
		try {
			return Messagebox
					.show(value, "询问", Messagebox.YES | Messagebox.NO, Messagebox.QUESTION);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return Messagebox.NO;
	}

	/**
	 * 显示警告
	 * 
	 * @param value
	 */
	public static void showWarning(String value) {
		try {
			Messagebox.show(value, "警告", Messagebox.OK, Messagebox.EXCLAMATION);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 显示错误
	 * 
	 * @param value
	 */
	public static void showError(String value) {
		try {
			Messagebox.show(value, "错误", Messagebox.OK, Messagebox.ERROR);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
