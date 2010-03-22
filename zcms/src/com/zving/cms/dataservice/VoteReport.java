package com.zving.cms.dataservice;

import com.zving.cms.stat.report.ChartUtil;
import com.zving.cms.stat.report.ReportUtil;
import com.zving.framework.Ajax;
import com.zving.framework.data.DataRow;
import com.zving.framework.data.DataTable;
import com.zving.framework.data.QueryBuilder;
import com.zving.framework.utility.Mapx;
import com.zving.schema.ZCVoteSchema;

public class VoteReport extends Ajax {
	public void getVoteData() {
		String subjectID = $V("SubjectID");
		DataTable dt = new QueryBuilder("select item,score from zcvoteitem where subjectid = ?",
				subjectID).executeDataTable();
		ReportUtil.computeRate(dt, "Score", "Rate");
		ReportUtil.addSuffix(dt, "Rate", "%");
		$S("Data", ChartUtil.getPie3DChart(dt));
	}

	public static Mapx init(Mapx params) {
		String ID = params.getString("ID");
		ZCVoteSchema vote = new ZCVoteSchema();
		vote.setID(ID);
		vote.fill();
		params.put("Title", vote.getTitle());
		return params;
	}

	public static DataTable getList1(Mapx params, DataRow parentDR) {
		String voteID = params.getString("ID");
		String type = "单选";
		DataTable dt = new QueryBuilder("select * from ZCVoteSubject where voteID =? order by ID",
				voteID).executeDataTable();
		dt.insertColumn("count1");
		dt.insertColumn("Type1");
		for (int i = 0; i < dt.getRowCount(); ++i) {
			DataTable dt1 = new QueryBuilder(
					"select * from zcvoteitem where voteID =? and SubjectID=? order by ID", voteID,
					dt.get(i, "ID")).executeDataTable();
			dt.set(i, "count1", dt1.getRowCount() + 1);
			if ("Y".equals(dt.getString(i, "Type")))
				type = "多选";
			else {
				type = "单选";
			}
			dt.set(i, "Type1", type);
		}

		return dt;
	}

	public static DataTable getList2(Mapx params, DataRow parentDR) {
		String voteID = params.getString("ID");
		DataTable dt = new QueryBuilder(
				"select * from zcvoteitem where voteID =? and SubjectID=? order by ID", voteID,
				parentDR.getString("ID")).executeDataTable();
		dt.insertColumn("count");

		dt.insertColumn("percent");
		long total = 0L;
		for (int i = 0; i < dt.getRowCount(); ++i) {
			total += dt.getLong(i, "Score");
			dt.set(i, "count", i + 1);
		}
		for (int i = 0; i < dt.getRowCount(); ++i) {
			long score = dt.getLong(i, "Score");
			if (total == 0L)
				dt.set(i, "percent", 0.0D);
			else if (score <= total) {
				dt.set(i, "percent", Math.round(score * 100.0D / total));
			}
		}
		return dt;
	}
}

/*
 * Location: F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\ Qualified Name:
 * com.zving.cms.dataservice.VoteReport JD-Core Version: 0.5.3
 */