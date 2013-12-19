package com.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class Test2 {

	public static int getLetterNumber(char cha) {
		// �������Ĳ�����ĸ�����׳��쳣
		if (!Character.isLetter(cha)) {
			throw new RuntimeException("������ַ�������Ӣ��26����ĸ");
		}
		// ��Сд��ĸ�����ж�
		char ch = Character.toLowerCase(cha);
		int num = ch - 'a' + 1;
		// System.out.println(cha + "��Ӣ����ĸ���е�" + num + "����ĸ");
		return num;
	}

	public static boolean isNum(String str) {
		return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
	}

	public void check() {

		try {
			InputStream is1 = new FileInputStream("test1.xls");
			InputStream is2 = new FileInputStream("test2.xls");
			Workbook wb1 = Workbook.getWorkbook(is1);
			Workbook wb2 = Workbook.getWorkbook(is2);

			Sheet[] sheets1 = wb1.getSheets();// ��ȡ���е�sheet
			Sheet[] sheets2 = wb2.getSheets();// ��ȡ���е�sheet

			Sheet s1 = wb1.getSheet(1);// ��ȡsheet
			Sheet s2 = wb2.getSheet(1);// ��ȡsheet

			int r1 = s1.getRows();
			int c1 = s1.getColumns();

			int r2 = s2.getRows();
			int c2 = s2.getColumns();

			String str1 = s1.getCell(3, 7).getContents();

			String str2 = s2.getCell(this.getLetterNumber('t'), 20).getContents();

			if (isNum(str1) && isNum(str2)) {
				if (Integer.parseInt(str1) == Integer.parseInt(str2)) {
					System.out.println("���");

				}

				if (Integer.parseInt(str1) > Integer.parseInt(str2)) {
					System.out.println("����");
				}

				if (Integer.parseInt(str1) < Integer.parseInt(str2)) {
					System.out.println("С��");
				}
			}

			System.out.println(str1 + "," + str2);

		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		new Test2().check();
	}

}
