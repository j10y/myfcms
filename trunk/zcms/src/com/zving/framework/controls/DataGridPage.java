package com.zving.framework.controls;

import com.zving.framework.Constant;
import com.zving.framework.Current;
import com.zving.framework.Page;
import com.zving.framework.RequestImpl;
import com.zving.framework.data.DataTable;
import com.zving.framework.data.QueryBuilder;
import com.zving.framework.utility.DateUtil;
import com.zving.framework.utility.HtmlUtil;
import com.zving.framework.utility.Mapx;
import com.zving.framework.utility.ServletUtil;
import com.zving.framework.utility.StringUtil;
import java.io.OutputStream;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DataGridPage extends Page {
	public void doWork() {
		try {
			DataGridAction dga = new DataGridAction();

			dga.setTagBody(StringUtil.htmlDecode($V("_ZVING_TAGBODY")));
			String method = $V("_ZVING_METHOD");
			dga.setMethod(method);

			dga.setID($V("_ZVING_ID"));
			dga.setPageFlag("true".equalsIgnoreCase($V("_ZVING_PAGE")));
			dga.setMultiSelect(!("false".equalsIgnoreCase($V("_ZVING_MULTISELECT"))));
			dga.setAutoFill(!("false".equalsIgnoreCase($V("_ZVING_AUTOFILL"))));
			dga.setScroll("true".equalsIgnoreCase($V("_ZVING_SCROLL")));
			dga.setLazy("true".equalsIgnoreCase($V("_ZVING_LAZY")));
			if (StringUtil.isNotEmpty($V("_ZVING_CACHESIZE"))) {
				dga.setCacheSize(Integer.parseInt($V("_ZVING_CACHESIZE")));
			}
			dga.setParams(Current.getRequest());
			dga.Response = Current.getResponse();

			if (dga.isPageFlag()) {
				dga.setPageIndex(0);
				if ((this.Request.get("_ZVING_PAGEINDEX") != null)
						&& (!(this.Request.get("_ZVING_PAGEINDEX").equals("")))) {
					dga.setPageIndex(Integer.parseInt(this.Request.get("_ZVING_PAGEINDEX")
							.toString()));
				}
				if (dga.getPageIndex() < 0) {
					dga.setPageIndex(0);
				}
				dga.setPageSize(Integer.parseInt($V("_ZVING_SIZE")));
			}

			HtmlTable table = new HtmlTable();
			table.parseHtml(dga.getTagBody());
			dga.setTemplate(table);
			dga.parse();

			String strInsertRowIndex = this.Request.getString("_ZVING_INSERTROW");
			if (StringUtil.isNotEmpty(strInsertRowIndex)) {
				DataTable dt = (DataTable) this.Request.get("_ZVING_DataTable");
				this.Request.remove("_ZVING_DataTable");
				this.Request.remove("_ZVING_INSERTROW");
				dga.bindData(dt);

				HtmlTR tr = dga.getTable().getTR(1);
				$S("TRAttr", tr.getAttributes());
				for (int i = 0; i < tr.Children.size(); ++i) {
					$S("TDAttr" + i, tr.getTD(i).getAttributes());
					$S("TDHtml" + i, tr.getTD(i).getInnerHTML());
				}
				return;
			}

			Current.invokeMethod(method, new Object[] { dga });
			$S("HTML", dga.getHtml());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void toExcel(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding(Constant.GlobalCharset);
		response.reset();
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=Excel_"
				+ DateUtil.getCurrentDateTime("yyyyMMddhhmmss") + ".xls");
		try {
			String xls = "_Excel_";
			Mapx params = ServletUtil.getParameterMap(request);
			String ID = params.getString(xls + "_ZVING_ID");
			String tagBody = params.getString(xls + "_ZVING_TAGBODY");
			String pageIndex = params.getString(xls + "_ZVING_PAGEINDEX");
			String pageSize = params.getString(xls + "_ZVING_SIZE");
			String pageTotal = params.getString(xls + "_ZVING_PAGETOTAL");
			String method = params.getString(xls + "_ZVING_METHOD");
			String pageFlag = params.getString(xls + "_ZVING_PAGE");
			String excelPageFlag = params.getString(xls + "_ZVING_ToExcelPageFlag");
			String strWidths = params.getString(xls + "_ZVING_Widths");
			String strIndexes = params.getString(xls + "_ZVING_Indexes");
			String strRows = params.getString(xls + "_ZVING_Rows");

			if ((tagBody != null) && (!(tagBody.equals("")))) {
				tagBody = StringUtil.htmlDecode(tagBody);
			}
			DataGridAction dga = new DataGridAction();
			HtmlTable table = new HtmlTable();
			dga.setMethod(method);
			dga.setID(ID);
			dga.setTagBody(tagBody);
			if ("1".equals(excelPageFlag)) {
				if ("true".equals(pageFlag)) {
					dga.setPageFlag(true);
					dga.setPageIndex(0);
					dga.setPageSize(Integer.parseInt(pageTotal));
				}

			} else if ("true".equals(pageFlag)) {
				dga.setPageFlag(true);
				dga.setPageIndex((StringUtil.isEmpty(pageIndex)) ? 0 : Integer.parseInt(pageIndex));
				dga.setPageSize((StringUtil.isEmpty(pageSize)) ? 0 : Integer.parseInt(pageSize));
			}

			table.parseHtml(dga.getTagBody());
			dga.setTemplate(table);
			dga.parse();

			OutputStream os = response.getOutputStream();

			Current.init(request, response, method);
			Mapx map = Current.getRequest();
			Object[] ks = map.keyArray();
			for (int i = 0; i < ks.length; ++i) {
				String k = ks[i].toString();
				if (k.startsWith(xls)) {
					Object v = map.get(k);
					map.remove(k);
					map.put(k.substring(xls.length()), v);
				}
			}
			dga.setParams(map);
			dga.Response = Current.getResponse();
			Current.invokeMethod(method, new Object[] { dga });

			String[] rows = (String[]) null;
			if (StringUtil.isNotEmpty(strRows)) {
				rows = strRows.split(",");
			}

			HtmlTable ht = dga.getTable();
			if ((ht.getChildren().size() > 0)
					&& ("blank".equalsIgnoreCase(ht.getTR(ht.getChildren().size() - 1)
							.getAttribute("ztype")))) {
				ht.removeTR(ht.getChildren().size() - 1);
			}
			HtmlUtil.htmlTableToExcel(os, ht, strWidths.split(","), strIndexes.split(","), rows);

			os.flush();
			os.close();

			os = null;
			response.flushBuffer();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void sqlBind(DataGridAction dgp) {
		dgp.bindData(new QueryBuilder((String) dgp.getParams().get("_ZVING_DATAGRID_SQL")));
	}
}

/*
 * Location: F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\ Qualified Name:
 * com.zving.framework.controls.DataGridPage JD-Core Version: 0.5.3
 */