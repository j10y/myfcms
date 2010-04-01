/**
 * <p>��Ŀ���ƣ�</p>
 * <p>��Ȩ���� (c) </p>
 * <p>�汾��1.0</p>
 * <p>���ڣ�Apr 1, 2010</p>
 * <p>���£�</p>
 */
package com.hxzy.base.web.window;

import org.zkoss.zul.Messagebox;

/**
 * 
 * @���� :�Ի���ķ�װ
 * @������ gao_jie
 * @�������� Mar 4, 2009
 * @�汾 1.0
 * 
 */
public class Message {
	/**
	 * ��ʾ��ʾ��Ϣ
	 * 
	 * @param value
	 */
	public static void showInfo(String value) {
		try {
			Messagebox.show(value, "��ʾ", Messagebox.OK, Messagebox.INFORMATION);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ��ʾѯ����Ϣ
	 * 
	 * @param value
	 * @return
	 */
	public static int showQuestion(String value) {
		try {
			return Messagebox
					.show(value, "ѯ��", Messagebox.YES | Messagebox.NO, Messagebox.QUESTION);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return Messagebox.NO;
	}

	/**
	 * ��ʾ����
	 * 
	 * @param value
	 */
	public static void showWarning(String value) {
		try {
			Messagebox.show(value, "����", Messagebox.OK, Messagebox.EXCLAMATION);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ��ʾ����
	 * 
	 * @param value
	 */
	public static void showError(String value) {
		try {
			Messagebox.show(value, "����", Messagebox.OK, Messagebox.ERROR);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
