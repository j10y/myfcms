package com.test;

import java.io.FileInputStream;
import java.io.InputStream;

import jxl.*;

public class Test {

	public void RaadXls() throws Exception {
		InputStream is = new FileInputStream("test2.xls");
		Workbook wb = Workbook.getWorkbook(is);
		Sheet[] sheets = wb.getSheets();// 获取所有的sheet
		for (int x = 0; x < sheets.length; x++) {
			Sheet s = wb.getSheet(x);// 获取sheet
			// System.out.println(s.getRows()+"sheet的行数");
			if (s.getRows() == 0) {// 判断sheet是否为空
				System.out.println("Sheet" + (x + 1) + "为空!");
				continue;
			} else {
				// 通用的获取cell值的方式,getCell(int column, int row) 行和列
				int Rows = s.getRows();// 总行
				int Cols = s.getColumns();// 总列
				System.out.println("当前工作表的名字:" + s.getName());
				System.out.println("总行数:" + Rows);
				System.out.println("总列数:" + Cols);
				String[][] str = new String[Rows][Cols];
				for (int i = 0; i < Rows; i++) {
					for (int j = 0; j < Cols; j++) {
						str[i][j] = (s.getCell(j, i)).getContents();// getCell(Col,Row)获得单元格的值
						System.out.print(str[i][j] + "\t");
					}
					System.out.print("\n");
				}
			}
		}
		wb.close();// 操作完成时，关闭对象，释放内存
	}
	
	public static void main(String[] args) {
		try {
			new Test().RaadXls();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
