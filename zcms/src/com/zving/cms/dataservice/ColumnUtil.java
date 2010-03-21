package com.zving.cms.dataservice;

import com.zving.framework.Config;
import com.zving.framework.controls.SelectTag;
import com.zving.framework.data.DataCollection;
import com.zving.framework.data.DataColumn;
import com.zving.framework.data.DataRow;
import com.zving.framework.data.DataTable;
import com.zving.framework.data.QueryBuilder;
import com.zving.framework.orm.SchemaSet;
import com.zving.framework.utility.HtmlUtil;
import com.zving.framework.utility.Mapx;
import com.zving.framework.utility.StringUtil;
import com.zving.platform.Application;
import com.zving.platform.pub.NoUtil;
import com.zving.schema.ZDColumnSchema;
import com.zving.schema.ZDColumnValueSchema;
import com.zving.schema.ZDColumnValueSet;

public class ColumnUtil {
	public static final String PREFIX = "_C_";
	public static final String RELA_TYPE_CATALOG_EXTEND = "0";
	public static final String RELA_TYPE_CATALOG_COLUMN = "1";
	public static final String RELA_TYPE_DOCID = "2";
	public static final String RELA_TYPE_GoodsTypeAttr = "3";
	public static final String RELA_TYPE_GoodsTypeParam = "4";
	public static String Input = "1";

	public static String Text = "2";

	public static String Select = "3";

	public static String Radio = "4";

	public static String Checkbox = "5";

	public static String DateInput = "6";

	public static String ImageInput = "7";

	public static String HTMLInput = "8";

	public static String TimeInput = "9";

	public static Mapx InputTypeMap = new Mapx();
	public static String STRING;
	public static String NUMBER;
	public static String INT;
	public static String EMAIL;
	public static Mapx VerifyTypeMap;
	public static String[][] IsMandatoryArray;

	static {
		InputTypeMap.put(Input, "文本框");
		InputTypeMap.put(Text, "多行文本框");
		InputTypeMap.put(Select, "下拉框");
		InputTypeMap.put(Radio, "单选框");
		InputTypeMap.put(Checkbox, "多选框");
		InputTypeMap.put(DateInput, "日期框");
		InputTypeMap.put(TimeInput, "时间框");
		InputTypeMap.put(ImageInput, "媒体库图片框");
		InputTypeMap.put(HTMLInput, "HTML");

		STRING = "1";

		NUMBER = "2";

		INT = "3";

		EMAIL = "5";

		VerifyTypeMap = new Mapx();

		VerifyTypeMap.put(STRING, "无");
		VerifyTypeMap.put(NUMBER, "数字");
		VerifyTypeMap.put(INT, "整数");
		VerifyTypeMap.put(EMAIL, "邮箱");

		IsMandatoryArray = new String[][] { { "Y", "必填" } };
	}

	public static DataTable getColumn(String relaType, long relaID) {
		return getColumn(relaType, relaID);
	}

	public static DataTable getColumn(String relaType, String relaID) {
		return new QueryBuilder(
				"select * from zdcolumn where id in (select columnid from zdcolumnrela where relatype=? and relaid = ?)",
				relaType, relaID).executeDataTable();
	}

	public static DataTable getColumn(String relaType, String relaID, String hidden) {
		return new QueryBuilder(
				"select * from zdcolumn where Prop1 != '1' and id in (select columnid from zdcolumnrela where relatype=? and relaid = ?)",
				relaType, relaID).executeDataTable();
	}

	public static DataTable getColumnValue(String relaType, long relaID) {
		return getColumnValue(relaType, relaID);
	}

	public static DataTable getColumnValue(String relaType, String relaID) {
		return new QueryBuilder("select * from zdcolumnvalue where relatype=? and relaid = ?",
				relaType, relaID).executeDataTable();
	}

	public static SchemaSet getValueFromRequest(long catalogID, long docID, DataCollection Request) {
		DataTable dt = getColumn("1", catalogID);
		ZDColumnValueSet set = new ZDColumnValueSet();
		for (int i = 0; i < dt.getRowCount(); ++i) {
			ZDColumnValueSchema value = new ZDColumnValueSchema();
			value.setID(NoUtil.getMaxID("ColumnValueID"));
			value.setColumnID(dt.getString(i, "ID"));
			value.setColumnCode(dt.getString(i, "Code"));
			value.setRelaType("2");
			value.setRelaID(String.valueOf(docID));
			ZDColumnSchema column = new ZDColumnSchema();
			column.setID(dt.getString(i, "ID"));
			column.fill();
			if (ImageInput.equals(column.getInputType())) {
				String textvalue = Request.getString("_C_" + value.getColumnCode());
				if ((StringUtil.isNotEmpty(textvalue)) && (textvalue.indexOf("upload") > 0)) {
					textvalue = textvalue.substring(textvalue.indexOf("upload"));
				}
				value.setTextValue(textvalue);
			} else {
				value.setTextValue(Request.getString("_C_" + value.getColumnCode()));
			}
			set.add(value);
		}
		return set;
	}

	public static void extendDocColumnData(DataTable dt, long catalogID) {
		extendDocColumnData(dt, catalogID);
	}

	public static void extendDocColumnData(DataTable dt, String catalogID) {
		DataTable columnDT = new QueryBuilder(
				"select columncode from zdcolumnrela where relatype='1' and relaid = ?", catalogID)
				.executeDataTable();
		String[] columnNames = (String[]) null;
		if (columnDT.getRowCount() > 0) {
			columnNames = new String[columnDT.getRowCount()];
			for (int i = 0; i < columnDT.getRowCount(); ++i)
				columnNames[i] = columnDT.getString(i, 0);
		} else {
			return;
		}
		int colCount = dt.getColCount();
		StringBuffer relaidsb = new StringBuffer();
		for (int i = 0; i < dt.getRowCount(); ++i) {
			if (i == 0)
				relaidsb.append("'");
			else {
				relaidsb.append(",'");
			}
			relaidsb.append(dt.getString(i, "ID"));
			relaidsb.append("'");
		}

		if (StringUtil.isEmpty(relaidsb.toString())) {
			return;
		}
		dt.insertColumns(columnNames);
		DataTable valueDT = new QueryBuilder(
				"select * from zdcolumnvalue where relatype='2' and relaid in (" + relaidsb + ")")
				.executeDataTable();
		if (valueDT.getRowCount() == 0) {
			return;
		}
		for (int j = 0; j < dt.getRowCount(); ++j)
			for (int k = colCount; k < dt.getColCount(); ++k)
				for (int index = 0; index < valueDT.getRowCount(); ++index)
					if ((dt.getString(j, "ID").equals(valueDT.getString(index, "RelaID")))
							&& (dt.getDataColumn(k).getColumnName().equals(valueDT.getString(index,
									"ColumnCode")))) {
						dt.set(j, k, valueDT.getString(index, "TextValue"));
						break;
					}
	}

	public static void extendCatalogColumnData(DataTable dt, String levelStr) {
		for (int i = 0; i < dt.getRowCount(); ++i) {
			DataRow dr = dt.getDataRow(i);
			extendCatalogColumnData(dr, levelStr);
		}
	}

	public static void extendCatalogColumnData(DataTable dt, long siteID, String levelStr) {
		for (int i = 0; i < dt.getRowCount(); ++i) {
			DataRow dr = dt.getDataRow(i);
			extendCatalogColumnData(dr, siteID, levelStr);
		}
	}

	public static void extendCatalogColumnData(DataRow dr, String levelStr) {
		extendCatalogColumnData(dr, Application.getCurrentSiteID(), levelStr);
	}

	public static void extendCatalogColumnData(DataRow dr, long siteID, String levelStr) {
		DataTable valueDT = new QueryBuilder(
				"select a.InputType,b.ColumnCode,b.TextValue from zdcolumn a,zdcolumnvalue b where a.ID = b.ColumnID and b.relatype='0' and b.relaid ='"
						+ dr.getString("ID") + "'").executeDataTable();
		if (valueDT.getRowCount() == 0) {
			return;
		}
		for (int j = 0; j < valueDT.getRowCount(); ++j)
			if (ImageInput.equals(valueDT.getString(j, "InputType")))
				dr.insertColumn(valueDT.getString(j, "ColumnCode"), levelStr
						+ valueDT.getString(j, "TextValue"));
			else
				dr.insertColumn(valueDT.getString(j, "ColumnCode"), valueDT.getString(j,
						"TextValue"));
	}

	public static String getHtml(String relaType, String relaID) {
		return getHtml(getColumn(relaType, relaID));
	}

	public static String getHtml(DataTable dt) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < dt.getRowCount(); ++i) {
			sb.append(getHtml(dt.getDataRow(i)));
		}
		return sb.toString();
	}

	private static String getHtml(DataRow dr) {
		return getHtml(dr, null);
	}

	public static String getHtml(String relaType, String relaID, String valueRelaType,
			String valueRelaID) {
		return getHtml(getColumn(relaType, relaID), getColumnValue(valueRelaType, valueRelaID));
	}

	public static String getHtml(String relaType, String relaID, String valueRelaType,
			String valueRelaID, String hidden) {
		return getHtml(getColumn(relaType, relaID, hidden), getColumnValue(valueRelaType,
				valueRelaID));
	}

	public static String getHtml(String relaType, String relaID, String hidden) {
		return getHtml(getColumn(relaType, relaID, hidden));
	}

	public static String getHtml(DataTable dt, DataTable valueDT) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < dt.getRowCount(); ++i) {
			sb.append(getHtml(dt.getDataRow(i), valueDT));
		}
		return sb.toString();
	}

	private static String getHtml(DataRow dr, DataTable valueDT) {
		String columnName = dr.getString("Name");
		String columnCode = dr.getString("Code");
		String inputType = dr.getString("inputType");
		String verifyType = dr.getString("verifyType");
		String listOption = dr.getString("listOption");
		String defaultValue = dr.getString("defaultValue");
		String isMandatory = dr.getString("IsMandatory");
		String maxLength = dr.getString("maxLength");
		String HTML = dr.getString("HTML");
		String verifyStr = "verify='" + columnName + "|";
		if ("Y".equals(isMandatory)) {
			verifyStr = verifyStr + "NotNull";
		}
		if (!(STRING.equals(verifyType))) {
			if (NUMBER.equals(verifyType))
				verifyStr = verifyStr + "&&Number";
			else if (INT.equals(verifyType))
				verifyStr = verifyStr + "&&Int";
			else if (EMAIL.equals(verifyType))
				verifyStr = verifyStr + "&&Email";
		}
		if ((StringUtil.isNotEmpty(maxLength)) && (!("0".equals(maxLength))))
			verifyStr = verifyStr + "&&Length<" + maxLength + "'";
		else {
			verifyStr = verifyStr + "'";
		}

		if (valueDT != null) {
			for (int i = 0; i < valueDT.getRowCount(); ++i) {
				DataRow r = valueDT.getDataRow(i);
				if ((columnCode.equalsIgnoreCase(r.getString("columnCode")))
						&& (r.getString("TextValue") != null)) {
					defaultValue = r.getString("TextValue");
				}
			}
		}

		columnCode = "_C_" + columnCode;
		StringBuffer sb = new StringBuffer();
		sb.append("<tr><td height='25' align='right' >");
		sb.append(columnName);
		sb.append("</td><td align='left' >");

		if (inputType.equals(Input)) {
			sb.append("<input type='text' size='50' id='" + columnCode + "' name='" + columnCode
					+ "' value='" + defaultValue + "' " + verifyStr + " />");
		}

		if (inputType.equals(Text)) {
			sb.append("<textarea style='width:" + dr.getString("ColSize") + "px;height:"
					+ dr.getString("RowSize") + "px' id='" + columnCode + "' name='" + columnCode
					+ "' " + verifyStr + ">" + defaultValue + "</textarea>");
		}

		if (inputType.equals(Select)) {
			SelectTag select = new SelectTag();
			select.setId(columnCode);
			if ("Y".equals(isMandatory)) {
				select.setVerify(columnName + "|NotNull");
			}
			String[] array = listOption.split("\\n");
			sb.append(select.getHtml(HtmlUtil.arrayToOptions(array, defaultValue, true)));
		}
		String[] array;
		if (inputType.equals(Radio)) {
			array = listOption.split("\\n");
			if ((StringUtil.isEmpty(defaultValue)) && (array.length > 0)) {
				defaultValue = array[0];
			}
			sb.append(HtmlUtil.arrayToRadios(columnCode, array, defaultValue));
		}

		if (inputType.equals(Checkbox)) {
			array = listOption.split("\\n");
			defaultValue = defaultValue.replaceAll("　　", ",");
			defaultValue = defaultValue.replaceAll("　", ",");
			defaultValue = defaultValue.replaceAll("  ", ",");
			defaultValue = defaultValue.replaceAll(" ", ",");
			defaultValue = defaultValue.replaceAll(",,", ",");
			defaultValue = defaultValue.replaceAll("，，", ",");
			defaultValue = defaultValue.replaceAll("，", ",");
			String[] checkedArray = defaultValue.split(",");
			sb.append(HtmlUtil.arrayToCheckboxes(columnCode, array, checkedArray));
		}

		if (inputType.equals(DateInput)) {
			sb.append("<input name='" + columnCode + "' id='" + columnCode + "' value='"
					+ defaultValue + "' type='text' size='14' ztype='Date' " + verifyStr + " />");
		}

		if (inputType.equals(TimeInput)) {
			sb.append("<input name='" + columnCode + "' id='" + columnCode + "' value='"
					+ defaultValue + "' type='text' size='10' ztype='Time' " + verifyStr + " />");
		}

		if (inputType.equals(ImageInput)) {
			defaultValue = dr.getString("defaultValue");
			if (StringUtil.isEmpty(defaultValue)) {
				defaultValue = Config.getContextPath() + Config.getValue("UploadDir") + "/"
						+ Application.getCurrentSiteAlias()
						+ "/upload/Image/nopicture.jpg".replaceAll("//", "/");
			}

			if (valueDT != null) {
				for (int i = 0; i < valueDT.getRowCount(); ++i) {
					DataRow r = valueDT.getDataRow(i);
					if ((columnCode.equalsIgnoreCase("_C_" + r.getString("columnCode")))
							&& (r.getString("TextValue") != null)) {
						defaultValue = Config.getContextPath()
								+ Config.getValue("UploadDir")
								+ "/"
								+ Application.getCurrentSiteAlias()
								+ "/"
								+ r.getString("TextValue").replaceAll("///", "/").replaceAll("//",
										"/");
					}
				}
			}
			sb.append("<img src='" + defaultValue.replaceAll("1_", "s_") + "' name='Img"
					+ columnCode + "' id='Img" + columnCode
					+ "'><input name='button' type='button' onClick=\"parent.upload('" + columnCode
					+ "');\" value='浏览...' /> 图片路径 <input type='text' id='" + columnCode
					+ "' name='" + columnCode + "' size='40' onblur='document.getElementById(\"Img"
					+ columnCode + "\").src=this.value;' value='" + defaultValue + "' " + verifyStr
					+ "/>");
		}

		if (inputType.equals(HTMLInput)) {
			sb.append(HTML);
		}
		sb.append("</td></tr>");
		return sb.toString();
	}

	public static String getText(String relaType, String relaID, String valueRelaType,
			String valueRelaID) {
		return getText(getColumn(relaType, relaID), getColumnValue(valueRelaType, valueRelaID));
	}

	public static String getText(DataTable dt, DataTable valueDT) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < dt.getRowCount(); ++i) {
			sb.append(getText(dt.getDataRow(i), valueDT));
		}
		return sb.toString();
	}

	private static String getText(DataRow dr, DataTable valueDT) {
		String columnName = dr.getString("Name");
		String columnCode = dr.getString("Code");
		String inputType = dr.getString("inputType");
		String defaultValue = dr.getString("defaultValue");
		for (int i = 0; i < valueDT.getRowCount(); ++i) {
			DataRow r = valueDT.getDataRow(i);
			if ((columnCode.equalsIgnoreCase(r.getString("columnCode")))
					&& (r.getString("TextValue") != null)) {
				defaultValue = r.getString("TextValue");
			}
		}
		columnCode = "_C_" + columnCode;
		StringBuffer sb = new StringBuffer();
		sb.append("<tr><td height='25' align='right' >");
		sb.append(columnName);
		sb.append("：</td><td>");

		if (inputType.equals("1")) {
			sb.append(defaultValue);
		}

		if (inputType.equals("2")) {
			sb.append(defaultValue);
		}

		if (inputType.equals("3")) {
			sb.append(defaultValue);
		}

		if (inputType.equals("4")) {
			sb.append(defaultValue);
		}

		if (inputType.equals("5")) {
			sb.append(defaultValue);
		}

		inputType.equals("6");

		inputType.equals("7");

		sb.append("</td></tr>");
		return sb.toString();
	}
}

/*
 * Location: F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\ Qualified Name:
 * com.zving.cms.dataservice.ColumnUtil JD-Core Version: 0.5.3
 */