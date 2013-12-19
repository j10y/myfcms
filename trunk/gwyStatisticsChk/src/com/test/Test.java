package com.test;

import java.io.FileInputStream;
import java.io.InputStream;

import jxl.*;

public class Test {

	public void RaadXls() throws Exception {
		InputStream is = new FileInputStream("test2.xls");
		Workbook wb = Workbook.getWorkbook(is);
		Sheet[] sheets = wb.getSheets();// ��ȡ���е�sheet
		for (int x = 0; x < sheets.length; x++) {
			Sheet s = wb.getSheet(x);// ��ȡsheet
			// System.out.println(s.getRows()+"sheet������");
			if (s.getRows() == 0) {// �ж�sheet�Ƿ�Ϊ��
				System.out.println("Sheet" + (x + 1) + "Ϊ��!");
				continue;
			} else {
				// ͨ�õĻ�ȡcellֵ�ķ�ʽ,getCell(int column, int row) �к���
				int Rows = s.getRows();// ����
				int Cols = s.getColumns();// ����
				System.out.println("��ǰ�����������:" + s.getName());
				System.out.println("������:" + Rows);
				System.out.println("������:" + Cols);
				String[][] str = new String[Rows][Cols];
				for (int i = 0; i < Rows; i++) {
					for (int j = 0; j < Cols; j++) {
						str[i][j] = (s.getCell(j, i)).getContents();// getCell(Col,Row)��õ�Ԫ���ֵ
						System.out.print(str[i][j] + "\t");
					}
					System.out.print("\n");
				}
			}
		}
		wb.close();// �������ʱ���رն����ͷ��ڴ�
	}
	
	public static void main(String[] args) {
		try {
			new Test().RaadXls();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
