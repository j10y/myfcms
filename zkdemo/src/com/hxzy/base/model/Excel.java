/**
 * <p>��Ŀ���ƣ�</p>
 * <p>��Ȩ���� (c) </p>
 * <p>�汾��1.0</p>
 * <p>���ڣ�Apr 12, 2010</p>
 * <p>���£�</p>
 */
package com.hxzy.base.model;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;

import jxl.Workbook;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

/**
 * @author xiacc
 * 
 * ������
 */
public class Excel {

	public static void write(List<Sheet> sheetList, OutputStream os) {
		WritableWorkbook wwb = null;
		WritableFont wf1 = new WritableFont(WritableFont.TIMES, 10, WritableFont.NO_BOLD, false,
				UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
		WritableCellFormat wff1 = new WritableCellFormat(wf1);
		
		try {
			wwb = Workbook.createWorkbook(os);
			int i = 0;
			for (Iterator<Sheet> itr = sheetList.iterator(); itr.hasNext();) {
				Sheet sheet = itr.next();
				int rowNum = 0;
				WritableSheet ws = wwb.createSheet(sheet.getName(), i);
				for (Iterator<Row> sheetItr = sheet.getRowList().iterator(); sheetItr.hasNext();) {
					Row row = sheetItr.next();
					int colunNum = 0;
					for (Iterator<Column> rowIterator = row.getColumnList().iterator(); rowIterator
							.hasNext();) {
						Column column = rowIterator.next();
						ws.addCell(new Label(colunNum, rowNum, column.getColumnLabel(), wff1));
						colunNum++;
					}
					rowNum++;
				}
				i++;
			}
			// д��Exel������
			wwb.write();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// �ر�Excel����������
			if(wwb != null){
				try {
					wwb.close();
					os.close();
				} catch (WriteException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
