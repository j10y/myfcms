package com.zving.statical;

import com.zving.framework.data.DataRow;
import com.zving.framework.data.DataTable;
import com.zving.framework.data.QueryBuilder;

public class TemplateData {
	protected String FirstFileName;
	protected String OtherFileName;
	protected int PageSize = 20;

	protected int PageIndex = 0;
	protected int Total;
	protected int PageCount;
	protected DataTable ListTable;

	public DataTable getDataTable(String sql) {
		DataTable dt = new QueryBuilder(sql).executeDataTable();
		return dt;
	}

	public DataTable getPagedDataTable(DataTable dt) {
		int count = this.PageSize;
		if ((this.PageIndex + 1) * this.PageSize > this.Total) {
			count = this.Total - (this.PageIndex * this.PageSize);
		}
		Object[][] values = new Object[count][dt.getColCount()];
		for (int i = 0; i < count; ++i) {
			values[i] = dt.getDataRow(this.PageIndex * this.PageSize + i).getDataValues();
		}
		return new DataTable(dt.getDataColumns(), values);
	}

	public String getPreviousPage() {
		if (this.PageIndex == 1)
			return this.FirstFileName;
		if (this.PageIndex != 0)
			return this.OtherFileName.replaceAll("@INDEX", String.valueOf(this.PageIndex));
		if (this.PageIndex == 0) {
			return "#";
		}

		return null;
	}

	public int getPageCount() {
		return this.PageCount;
	}

	public void setPageCount(int pageCount) {
		this.PageCount = pageCount;
	}

	public int getPageIndex() {
		return this.PageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.PageIndex = pageIndex;
	}

	public int getTotal() {
		return this.Total;
	}

	public void setTotal(int total) {
		this.Total = total;
	}

	public String getNextPage() {
		if (this.PageIndex + 1 != this.PageCount) {
			return this.OtherFileName.replaceAll("@INDEX", String.valueOf(this.PageIndex + 2));
		}
		return "#";
	}

	public String getFirstPage() {
		return this.FirstFileName;
	}

	public String getLastPage() {
		return this.OtherFileName.replaceAll("@INDEX", String.valueOf(this.PageCount));
	}

	public String getFirstFileName() {
		return this.FirstFileName;
	}

	public void setFirstFileName(String firstFileName) {
		this.FirstFileName = firstFileName;
	}

	public String getOtherFileName() {
		return this.OtherFileName;
	}

	public void setOtherFileName(String otherFileName) {
		this.OtherFileName = otherFileName;
	}

	public int getPageSize() {
		return this.PageSize;
	}

	public void setPageSize(int pageSize) {
		this.PageSize = pageSize;
	}

	public String getPageBar(int id) {
		StringBuffer sb = new StringBuffer();

		sb.append("<table width='100%' border='0' class='noBorder' align='center'><tr>");
		sb
				.append("<td height='18' align='center' valign='middle' style='border-width: 0px;color:#525252'>");
		sb.append("共" + this.Total + "条记录，每页" + this.PageSize + "条，当前第<span class='fc_ch1'>"
				+ (this.PageIndex + 1) + "</span>/<span class='fc_ch1'>" + this.PageCount
				+ "</span>页&nbsp;&nbsp;&nbsp;&nbsp;");
		if (this.PageIndex > 0) {
			sb.append("<a href='" + getFirstPage() + "'><span class='fc_ch1'>第一页</span></a>|");
			sb.append("<a href='" + getPreviousPage() + "'><span class='fc_ch1'>上一页</span></a>|");
		} else {
			sb.append("<span class='fc_hui2'>第一页</span>|");
			sb.append("<span class='fc_hui2'>上一页</span>|");
		}
		if ((this.PageIndex + 1 != this.PageCount) && (this.PageCount > 0)) {
			sb.append("<a href='" + getNextPage() + "'><span class='fc_ch1'>下一页</span></a>|");
			sb.append("<a href='" + getLastPage() + "'><span class='fc_ch1'>最末页</span></a>");
		} else {
			sb.append("<span class='fc_hui2'>下一页</span>|");
			sb.append("<span class='fc_hui2'>最末页</span>");
		}

		sb.append("&nbsp;&nbsp;转到第<input id='_PageBar_Index_" + id
				+ "' type='text' size='4' style='width:30px' ");
		sb.append("style='' onKeyUp=\"value=value.replace(/\\D/g,'')\">页");

		sb
				.append("&nbsp;&nbsp;<input type='button' onclick=\"if(/[^\\d]/.test(document.getElementById('_PageBar_Index_"
						+ id
						+ "').value)){alert('错误的页码');$('_PageBar_Index_"
						+ id
						+ "').focus();}else if(document.getElementById('_PageBar_Index_"
						+ id
						+ "').value>"
						+ this.PageCount
						+ "){alert('错误的页码');document.getElementById('_PageBar_Index_"
						+ id
						+ "').focus();}else{var PageIndex = (document.getElementById('_PageBar_Index_"
						+ id
						+ "').value)>0?document.getElementById('_PageBar_Index_"
						+ id
						+ "').value:1;if(PageIndex==1){window.location='index.shtml'}else{window.location='index_'+PageIndex+'.shtml';}}\" style='' value='跳转'></td>");
		sb.append("</tr></table>");
		return sb.toString();
	}

	public String getPageBreakBar(int id) {
		StringBuffer sb = new StringBuffer();
		if (getTotal() > 1) {
			sb.append("<table width='100%' border='0' class='noBorder' align='center'><tr>");
			sb
					.append("<td height='18' align='center' valign='middle' style='border-width: 0px;color:#525252'>");

			if (this.PageIndex > 0)
				sb.append("<a href='" + getPreviousPage()
						+ "'><span class='fc_ch1'>上一页</span></a>&nbsp;&nbsp;");
			else {
				sb.append("<span class='fc_hui2'>上一页</span>&nbsp;&nbsp;");
			}

			StringBuffer pageList = new StringBuffer();
			for (int i = 0; i < this.PageCount; ++i) {
				String href = null;
				if (i == 0)
					href = this.FirstFileName;
				else {
					href = this.OtherFileName.replaceAll("@INDEX", String.valueOf(i + 1));
				}

				if (i == this.PageIndex) {
					pageList.append(" " + (i + 1));
				} else {
					pageList.append("  <a href=");
					pageList.append(href);
					pageList.append(">" + (i + 1) + "</a>");
				}
			}

			sb.append(pageList);

			if (this.PageIndex + 1 != this.PageCount)
				sb.append("&nbsp;&nbsp;<a href='" + getNextPage()
						+ "'><span class='fc_ch1'>下一页</span></a>&nbsp;");
			else {
				sb.append("&nbsp;&nbsp;<span class='fc_hui2'>下一页</span>&nbsp;");
			}

			sb.append("&nbsp;&nbsp;</td>");
			sb.append("</tr></table>");
		}
		return sb.toString();
	}

	public DataRow getPageRow() {
		StringBuffer pageList = new StringBuffer();
		for (int i = 0; i < this.PageCount; ++i) {
			String href = null;
			if (i == 0)
				href = this.FirstFileName;
			else {
				href = this.OtherFileName.replaceAll("@INDEX", String.valueOf(i + 1));
			}

			if (i == this.PageIndex) {
				pageList.append(" " + (i + 1));
			} else {
				pageList.append("  <a href=");
				pageList.append(href);
				pageList.append(">" + (i + 1) + "</a>");
			}
		}

		DataTable dataPage = new DataTable();
		String[] cols = { "Total", "PageCount", "PageSize", "FirstPage", "PrevPage", "NextPage",
				"LastPage", "PageIndex", "PageList" };
		String[] values = { String.valueOf(this.Total), String.valueOf(this.PageCount),
				String.valueOf(this.PageSize), getFirstPage(), getPreviousPage(), getNextPage(),
				getLastPage(), String.valueOf(this.PageIndex + 1), pageList.toString() };
		dataPage.insertColumns(cols);
		dataPage.insertRow(values);

		return dataPage.getDataRow(0);
	}

	public DataTable getListTable() {
		return this.ListTable;
	}

	public void setListTable(DataTable listTable) {
		this.ListTable = listTable;
	}
}

/*
 * Location: F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\ Qualified Name:
 * com.zving.statical.TemplateData JD-Core Version: 0.5.3
 */