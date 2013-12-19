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
	 * 读取Excel
	 * 
	 * @param filePath
	 */
	public static void readExcel(String filePath) {
		try {
			InputStream is = new FileInputStream(filePath);
			Workbook rwb = Workbook.getWorkbook(is);
			// Sheet st = rwb.getSheet("0")这里有两种方法获取sheet表,1为名字，2为下标，从0开始
			Sheet st = rwb.getSheet(0);
			Cell c00 = st.getCell(0, 0);
			// 通用的获取cell值的方式,返回字符串
			String strc00 = c00.getContents();
			// 获得cell具体类型值的方式
			if (c00.getType() == CellType.LABEL) {
				LabelCell labelc00 = (LabelCell) c00;
				strc00 = labelc00.getString();
			}
			// 输出
			System.out.println(strc00);
			// 关闭
			rwb.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	/**
//	 * 输出Excel
//	 * 
//	 * @param os
//	 */
//	public static void writeExcel(OutputStream os) {
//		try {
//			/**
//			 * 只能通过API提供的工厂方法来创建Workbook，而不能使用WritableWorkbook的构造函数，
//			 * 因为类WritableWorkbook的构造函数为protected类型
//			 * method(1)直接从目标文件中读取WritableWorkbook wwb =
//			 * Workbook.createWorkbook(new File(targetfile)); method(2)如下实例所示
//			 * 将WritableWorkbook直接写入到输出流
//			 * 
//			 */
//			WritableWorkbook wwb = Workbook.createWorkbook(os);
//			// 创建Excel工作表 指定名称和位置
//			WritableSheet ws = wwb.createSheet("Test Sheet 1", 0);
//
//			// **************往工作表中添加数据*****************
//
//			// 1.添加Label对象
//			Label label = new Label(0, 0, "this is a label test");
//			ws.addCell(label);
//
//			// 添加带有字型formatting对象
//			WritableFont wf = new WritableFont(WritableFont.TIMES, 18, WritableFont.BOLD, true);
//			WritableCellformat wcf = new WritableCellformat(wf);
//			Label labelcf = new Label(1, 0, "this is a label test", wcf);
//			ws.addCell(labelcf);
//
//			// 添加带有字体颜色的formatting对象
//			WritableFont wfc = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD, false, Underlinestyle.NO_UNDERLINE,
//					jxl.format.Colour.RED);
//			WritableCellformat wcfFC = new WritableCellformat(wfc);
//			Label labelCF = new Label(1, 0, "This is a Label Cell", wcfFC);
//			ws.addCell(labelCF);
//
//			// 2.添加Number对象
//			Number labelN = new Number(0, 1, 3.1415926);
//			ws.addCell(labelN);
//
//			// 添加带有formatting的Number对象
//			Numberformat nf = new Numberformat("#.##");
//			WritableCellformat wcfN = new WritableCellformat(nf);
//			Number labelNF = new jxl.write.Number(1, 1, 3.1415926, wcfN);
//			ws.addCell(labelNF);
//
//			// 3.添加Boolean对象
//			Boolean labelB = new jxl.write.Boolean(0, 2, false);
//			ws.addCell(labelB);
//
//			// 4.添加DateTime对象
//			jxl.write.DateTime labelDT = new jxl.write.DateTime(0, 3, new java.util.Date());
//			ws.addCell(labelDT);
//
//			// 添加带有formatting的Dateformat对象
//			Dateformat df = new Dateformat("dd MM yyyy hh:mm:ss");
//			WritableCellformat wcfDF = new WritableCellformat(df);
//			DateTime labelDTF = new DateTime(1, 3, new java.util.Date(), wcfDF);
//			ws.addCell(labelDTF);
//
//			// 添加图片对象,jxl只支持png格式图片
//			File image = new File("f:\\2.png");
//			WritableImage wimage = new WritableImage(0, 1, 2, 2, image);
//			ws.addImage(wimage);
//			// 写入工作表
//			wwb.write();
//			wwb.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	/**
//	 * 拷贝后,进行修改,其中file1为被copy对象，file2为修改后创建的对象；如果不想改名字的话他们可以一样
//	 * 尽单元格原有的格式化修饰是不能去掉的，我们还是可以将新的单元格修饰加上去， 以使单元格的内容以不同的形式表现
//	 * 
//	 * @param file1
//	 * @param file2
//	 */
//	public static void modifyExcel(File file1, File file2) {
//		try {
//			Workbook rwb = Workbook.getWorkbook(file1);
//			// 打开一个文件的副本，并且指定数据写回到原文件
//			WritableWorkbook wwb = Workbook.createWorkbook(file2, rwb);// copy
//			WritableSheet ws = wwb.getSheet(0);
//			WritableCell wc = ws.getWritableCell(0, 0);
//			// 判断单元格的类型,做出相应的转换
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

	// 测试
	public static void main(String[] args) {
		new ExcelHandle().readExcel("test.xls");
	}
}