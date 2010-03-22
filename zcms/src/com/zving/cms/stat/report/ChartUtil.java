package com.zving.cms.stat.report;

import com.zving.framework.data.DataColumn;
import com.zving.framework.data.DataTable;

public class ChartUtil {
	public static String getPie3DChart(DataTable dt, double rate) {
		ReportUtil.prepareForPie3D(dt, rate);
		return getPie3DChart(dt);
	}

	public static String getPie3DChart(DataTable dt, int count) {
		ReportUtil.prepareForPie3D(dt, count);
		return getPie3DChart(dt);
	}

	public static String getPie3DChart(DataTable dt, int count, double rate) {
		ReportUtil.prepareForPie3D(dt, count, rate);
		return getPie3DChart(dt);
	}

	public static String getPie3DChart(DataTable dt) {
		StringBuffer xml = new StringBuffer();
		String[] Colors = { "FF0000", "006F00", "CCCC00", "0D8ECF", "04D215", "B0DE09", "F8FF01",
				"FF9E01", "FF6600", "814EE6", "F234B0", "FF9966", "0099FF", "993300", "333300",
				"003300", "003366", "000080", "333399", "333333", "800000", "FF6600", "808000",
				"808080", "008080", "0000FF", "666699", "808080", "FF9900", "99CC00", "339966",
				"33CCCC", "3366FF", "800080", "999999", "FF00FF", "FFCC00", "FFFF00", "00FF00",
				"00FFFF", "00CCFF", "993366", "C0C0C0", "FF99CC", "FFCC99", "FFFF99", "CCFFCC",
				"CCFFFF", "99CCFF", "CC99FF", "FFFFFF", "1D8BD1", "F1683C", "2AD62A" };
		xml
				.append("<graph baseFontSize=\"12\" showNames=\"1\" animation=\"0\" nameTBDistance=\"20\" showPercentageInLabel=\"1\">");
		for (int j = 0; j < dt.getRowCount(); ++j) {
			xml.append("<set value=\"" + dt.get(j, 1) + "\" name=\"" + dt.get(j, 0) + "\" color=\""
					+ Colors[(j % Colors.length)] + "\"/>");
		}
		xml.append("</graph>");
		return xml.toString();
	}

	public static String getLine2DChart(DataTable dt, int labelCount) {
		StringBuffer xml = new StringBuffer();
		String[] Colors = { "1D8BD1", "F1683C", "2AD62A", "FF0000", "006F00", "CCCC00", "0D8ECF",
				"04D215", "B0DE09", "F8FF01", "FF9E01", "FF6600", "814EE6", "F234B0", "FF9966",
				"0099FF", "993300", "333300", "003300", "003366", "000080", "333399", "333333",
				"800000", "FF6600", "808000", "808080", "008080", "0000FF", "666699", "808080",
				"FF9900", "99CC00", "339966", "33CCCC", "3366FF", "800080", "999999", "FF00FF",
				"FFCC00", "FFFF00", "00FF00", "00FFFF", "00CCFF", "993366", "C0C0C0", "FF99CC",
				"FFCC99", "FFFF99", "CCFFCC", "CCFFFF", "99CCFF", "CC99FF", "FFFFFF" };
		xml
				.append("<graph lineThickness='0' canvasBorderThickness='0' alternateHGridAlpha='5' canvasBorderColor='666666' divLineColor='ff5904' divLineAlpha='20' showAlternateHGridColor='1' AlternateHGridColor='ff5904' hovercapbg='FFECAA' hovercapborder='F47E00' formatNumberScale='0' decimalPrecision='0' showvalues='0' numdivlines='4' numVdivlines='0' rotateNames='0'>");
		xml.append("<categories>");
		int space = new Double(Math.ceil(dt.getRowCount() * 0.95D / labelCount)).intValue();
		int count = 0;
		for (int i = 0; i < dt.getRowCount(); ++i) {
			if (i % space == 0) {
				++count;
			}
			xml.append("<category name='" + dt.get(i, 0) + "' "
					+ (((i % space == 0) && (count <= labelCount)) ? "" : "showName='0'") + "/>");
		}
		xml.append("</categories>");
		for (int i = 1; i < dt.getColCount(); ++i) {
			String color = Colors[((i - 1) % Colors.length)];
			xml.append("<dataset seriesName='" + dt.getDataColumn(i).getColumnName() + "' color='"
					+ color + "' anchorBorderColor='" + color + "' anchorBgColor='" + color + "'>");
			for (int j = 0; j < dt.getRowCount(); ++j) {
				xml.append("<set value='" + dt.get(j, i) + "'/>");
			}
			xml.append("</dataset>");
		}
		xml.append("</graph>");
		return xml.toString();
	}

	public static String getMixed2DChart(DataTable dt, String yColumn, int labelCount) {
		StringBuffer xml = new StringBuffer();
		String[] Colors = { "F6BD0F", "8BBA00", "FF0000", "2AD62A", "006F00", "CCCC00", "0D8ECF",
				"04D215", "B0DE09", "F8FF01", "FF9E01", "FF6600", "814EE6", "F234B0", "FF9966",
				"0099FF", "993300", "333300", "003300", "003366", "000080", "333399", "333333",
				"800000", "FF6600", "808000", "808080", "008080", "0000FF", "666699", "808080",
				"FF9900", "99CC00", "339966", "33CCCC", "3366FF", "800080", "999999", "FF00FF",
				"FFCC00", "FFFF00", "00FF00", "00FFFF", "00CCFF", "993366", "C0C0C0", "FF99CC",
				"FFCC99", "FFFF99", "CCFFCC", "CCFFFF", "99CCFF", "CC99FF", "FFFFFF" };
		xml
				.append("<graph lineThickness='0' canvasBorderThickness='0' alternateHGridAlpha='5' canvasBorderColor='666666' divLineColor='ff5904' divLineAlpha='20' showAlternateHGridColor='1' AlternateHGridColor='ff5904' hovercapbg='FFECAA' hovercapborder='F47E00' formatNumberScale='0' decimalPrecision='0' showvalues='0' numdivlines='4' numVdivlines='0' rotateNames='0'>");
		xml.append("<categories>");
		int space = new Double(Math.ceil(dt.getRowCount() * 0.95D / labelCount)).intValue();
		int count = 0;
		for (int i = 0; i < dt.getRowCount(); ++i) {
			if (i % space == 0) {
				++count;
			}
			xml.append("<category name='" + dt.get(i, 0) + "' "
					+ (((i % space == 0) && (count <= labelCount)) ? "" : "showName='0'") + "/>");
		}
		xml.append("</categories>");
		for (int i = 1; i < dt.getColCount(); ++i) {
			String color = Colors[((i - 1) % Colors.length)];
			xml.append("<dataset seriesName='" + dt.getDataColumn(i).getColumnName() + "' color='"
					+ color + "' anchorBorderColor='" + color + "' anchorBgColor='" + color + "'");
			if (dt.getDataColumn(i).getColumnName().equalsIgnoreCase(yColumn)) {
				xml.append(" parentYAxis='S'");
			}
			xml.append(">");
			for (int j = 0; j < dt.getRowCount(); ++j) {
				xml.append("<set value='" + dt.get(j, i) + "'/>");
			}
			xml.append("</dataset>");
		}
		xml.append("</graph>");
		return xml.toString();
	}
}

/*
 * Location: F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\ Qualified Name:
 * com.zving.cms.stat.report.ChartUtil JD-Core Version: 0.5.3
 */