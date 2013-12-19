package com.test;

import jxl.*;
import jxl.write.*;
import jxl.write.Number;
import jxl.write.Boolean;

import java.io.*;

public class ExcelHandle {
	public ExcelHandle() {
	}

	/**
	 * ��ȡExcel
	 * 
	 * @param filePath
	 */
	public static void readExcel(String filePath) {
		try {
			InputStream is = new FileInputStream(filePath);
			Workbook rwb = Workbook.getWorkbook(is);
			// Sheet st = rwb.getSheet("0")���������ַ�����ȡsheet��,1Ϊ���֣�2Ϊ�±꣬��0��ʼ
			Sheet st = rwb.getSheet(0);
			Cell c00 = st.getCell(0, 0);
			// ͨ�õĻ�ȡcellֵ�ķ�ʽ,�����ַ���
			String strc00 = c00.getContents();
			// ���cell��������ֵ�ķ�ʽ
			if (c00.getType() == CellType.LABEL) {
				LabelCell labelc00 = (LabelCell) c00;
				strc00 = labelc00.getString();
			}
			// ���
			System.out.println(strc00);
			// �ر�
			rwb.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	/**
//	 * ���Excel
//	 * 
//	 * @param os
//	 */
//	public static void writeExcel(OutputStream os) {
//		try {
//			/**
//			 * ֻ��ͨ��API�ṩ�Ĺ�������������Workbook��������ʹ��WritableWorkbook�Ĺ��캯����
//			 * ��Ϊ��WritableWorkbook�Ĺ��캯��Ϊprotected����
//			 * method(1)ֱ�Ӵ�Ŀ���ļ��ж�ȡWritableWorkbook wwb =
//			 * Workbook.createWorkbook(new File(targetfile)); method(2)����ʵ����ʾ
//			 * ��WritableWorkbookֱ��д�뵽�����
//			 * 
//			 */
//			WritableWorkbook wwb = Workbook.createWorkbook(os);
//			// ����Excel������ ָ�����ƺ�λ��
//			WritableSheet ws = wwb.createSheet("Test Sheet 1", 0);
//
//			// **************�����������������*****************
//
//			// 1.���Label����
//			Label label = new Label(0, 0, "this is a label test");
//			ws.addCell(label);
//
//			// ��Ӵ�������formatting����
//			WritableFont wf = new WritableFont(WritableFont.TIMES, 18, WritableFont.BOLD, true);
//			WritableCellformat wcf = new WritableCellformat(wf);
//			Label labelcf = new Label(1, 0, "this is a label test", wcf);
//			ws.addCell(labelcf);
//
//			// ��Ӵ���������ɫ��formatting����
//			WritableFont wfc = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD, false, Underlinestyle.NO_UNDERLINE,
//					jxl.format.Colour.RED);
//			WritableCellformat wcfFC = new WritableCellformat(wfc);
//			Label labelCF = new Label(1, 0, "This is a Label Cell", wcfFC);
//			ws.addCell(labelCF);
//
//			// 2.���Number����
//			Number labelN = new Number(0, 1, 3.1415926);
//			ws.addCell(labelN);
//
//			// ��Ӵ���formatting��Number����
//			Numberformat nf = new Numberformat("#.##");
//			WritableCellformat wcfN = new WritableCellformat(nf);
//			Number labelNF = new jxl.write.Number(1, 1, 3.1415926, wcfN);
//			ws.addCell(labelNF);
//
//			// 3.���Boolean����
//			Boolean labelB = new jxl.write.Boolean(0, 2, false);
//			ws.addCell(labelB);
//
//			// 4.���DateTime����
//			jxl.write.DateTime labelDT = new jxl.write.DateTime(0, 3, new java.util.Date());
//			ws.addCell(labelDT);
//
//			// ��Ӵ���formatting��Dateformat����
//			Dateformat df = new Dateformat("dd MM yyyy hh:mm:ss");
//			WritableCellformat wcfDF = new WritableCellformat(df);
//			DateTime labelDTF = new DateTime(1, 3, new java.util.Date(), wcfDF);
//			ws.addCell(labelDTF);
//
//			// ���ͼƬ����,jxlֻ֧��png��ʽͼƬ
//			File image = new File("f:\\2.png");
//			WritableImage wimage = new WritableImage(0, 1, 2, 2, image);
//			ws.addImage(wimage);
//			// д�빤����
//			wwb.write();
//			wwb.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	/**
//	 * ������,�����޸�,����file1Ϊ��copy����file2Ϊ�޸ĺ󴴽��Ķ��������������ֵĻ����ǿ���һ��
//	 * ����Ԫ��ԭ�еĸ�ʽ�������ǲ���ȥ���ģ����ǻ��ǿ��Խ��µĵ�Ԫ�����μ���ȥ�� ��ʹ��Ԫ��������Բ�ͬ����ʽ����
//	 * 
//	 * @param file1
//	 * @param file2
//	 */
//	public static void modifyExcel(File file1, File file2) {
//		try {
//			Workbook rwb = Workbook.getWorkbook(file1);
//			// ��һ���ļ��ĸ���������ָ������д�ص�ԭ�ļ�
//			WritableWorkbook wwb = Workbook.createWorkbook(file2, rwb);// copy
//			WritableSheet ws = wwb.getSheet(0);
//			WritableCell wc = ws.getWritableCell(0, 0);
//			// �жϵ�Ԫ�������,������Ӧ��ת��
//			if (wc.getType == CellType.LABEL) {
//				Label label = (Label) wc;
//				label.setString("The value has been modified");
//			}
//			wwb.write();
//			wwb.close();
//			rwb.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	// ����
	public static void main(String[] args) {
		new ExcelHandle().readExcel("test.xls");
	}
}