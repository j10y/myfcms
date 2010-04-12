/**
 * <p>项目名称：</p>
 * <p>版权所有 (c) </p>
 * <p>版本：1.0</p>
 * <p>日期：Apr 12, 2010</p>
 * <p>更新：</p>
 */
package com.hxzy.base.model;

import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;

import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.write.WritableFont;

/**
 * @author xiacc
 * 
 * 描述：
 */
public class Excel {

	public static void write(List<Sheet> sheetList, OutputStream os) {
		jxl.write.WritableWorkbook wwb;
		jxl.write.WritableFont wf1 = new jxl.write.WritableFont(WritableFont.ARIAL, 20,
				WritableFont.BOLD, false, UnderlineStyle.DOUBLE_ACCOUNTING,
				jxl.format.Colour.BRIGHT_GREEN);
		jxl.write.WritableCellFormat wff1 = new jxl.write.WritableCellFormat(wf1);
		try {
			// wff1.setBackground(jxl.format.Colour.VIOLET2);
			// wff1.setBackground(jxl.format.Colour.VIOLET2);
			wwb = Workbook.createWorkbook(os);
			int i = 0;
			for (Iterator<Sheet> itr = sheetList.iterator(); itr.hasNext();) {
				Sheet sheet = itr.next();
				int rowNum = 0;
				jxl.write.WritableSheet ws = wwb.createSheet(sheet.getName(), i);
				for (Iterator<Row> sheetItr = sheet.getRowList().iterator(); sheetItr.hasNext();) {
					Row row = sheetItr.next();
					int colunNum = 0;
					for (Iterator<Column> rowIterator = row.getColumnList().iterator(); rowIterator
							.hasNext();) {
						Column column = rowIterator.next();
						ws.addCell(new jxl.write.Label(colunNum, rowNum, column.getColumnLabel(),
								wff1));
						colunNum++;
					}
					rowNum++;
				}
				i++;
			}
			// 写入Exel工作表
			wwb.write();
			// 关闭Excel工作薄对象
			wwb.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
