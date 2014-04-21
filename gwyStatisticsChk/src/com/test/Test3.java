package com.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import jxl.Range;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class Test3 {
	public static void readExcel(String excelFileName) throws BiffException, IOException {

		Workbook rwb = null;

		// 创建输入流
		InputStream stream = new FileInputStream(excelFileName);

		// 获取Excel文件对象
		rwb = Workbook.getWorkbook(stream);

		// 选择第一个工作表
		Sheet sheet = rwb.getSheet(1);
		for (int j = 1; j < sheet.getRows(); j++) {
			for (int k = 0; k < sheet.getColumns(); k++) {
				String str = null;
				str = sheet.getCell(k, j).getContents();
				Range[] ranges = sheet.getMergedCells();
				for (Range r : ranges) {
					if (j > r.getTopLeft().getRow() && j <= r.getBottomRight().getRow() && k == r.getTopLeft().getColumn()) {
						str = sheet.getCell(r.getTopLeft().getColumn(), r.getTopLeft().getRow()).getContents();
					}
				}
				System.out.print("第" + j + "行，第" + k + "列的值：" + str + "\t");
			}
			System.out.println("");
		}
	}

	public static void main(String[] args) {

		try {
			Test3.readExcel("test.xls");// 文件存放地址
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
