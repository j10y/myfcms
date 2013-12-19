package com.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class Test2 {

	public static int getLetterNumber(char cha) {
		// 如果输入的不是字母，就抛出异常
		if (!Character.isLetter(cha)) {
			throw new RuntimeException("输入的字符不属于英语26个字母");
		}
		// 大小写字母均可判断
		char ch = Character.toLowerCase(cha);
		int num = ch - 'a' + 1;
		// System.out.println(cha + "是英文字母表中第" + num + "个字母");
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

			Sheet[] sheets1 = wb1.getSheets();// 获取所有的sheet
			Sheet[] sheets2 = wb2.getSheets();// 获取所有的sheet

			Sheet s1 = wb1.getSheet(1);// 获取sheet
			Sheet s2 = wb2.getSheet(1);// 获取sheet

			int r1 = s1.getRows();
			int c1 = s1.getColumns();

			int r2 = s2.getRows();
			int c2 = s2.getColumns();

			String str1 = s1.getCell(3, 7).getContents();

			String str2 = s2.getCell(this.getLetterNumber('t'), 20).getContents();

			if (isNum(str1) && isNum(str2)) {
				if (Integer.parseInt(str1) == Integer.parseInt(str2)) {
					System.out.println("相等");

				}

				if (Integer.parseInt(str1) > Integer.parseInt(str2)) {
					System.out.println("大于");
				}

				if (Integer.parseInt(str1) < Integer.parseInt(str2)) {
					System.out.println("小于");
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
