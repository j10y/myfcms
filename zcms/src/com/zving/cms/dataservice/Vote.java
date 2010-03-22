package com.zving.cms.dataservice;

import com.zving.cms.datachannel.Deploy;
import com.zving.cms.pub.SiteUtil;
import com.zving.framework.Config;
import com.zving.framework.Page;
import com.zving.framework.ResponseImpl;
import com.zving.framework.User;
import com.zving.framework.controls.DataGridAction;
import com.zving.framework.data.DataRow;
import com.zving.framework.data.DataTable;
import com.zving.framework.data.QueryBuilder;
import com.zving.framework.data.Transaction;
import com.zving.framework.utility.DateUtil;
import com.zving.framework.utility.FileUtil;
import com.zving.framework.utility.HtmlUtil;
import com.zving.framework.utility.Mapx;
import com.zving.framework.utility.StringUtil;
import com.zving.platform.Application;
import com.zving.platform.pub.NoUtil;
import com.zving.schema.ZCVoteItemSchema;
import com.zving.schema.ZCVoteItemSet;
import com.zving.schema.ZCVoteSchema;
import com.zving.schema.ZCVoteSet;
import com.zving.schema.ZCVoteSubjectSchema;
import com.zving.schema.ZCVoteSubjectSet;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;

public class Vote extends Page {
	public static Mapx initDialog(Mapx params) {
		String ID = params.get("ID").toString();
		if (StringUtil.isEmpty(ID)) {
			Date date = new Date();
			params.put("StartDate", DateUtil.toString(date));
			params.put("StartTime", DateUtil.toTimeString(date));
			params.put("IPLimit", HtmlUtil.codeToRadios("IPLimit", "YesOrNo", "N", false));
			params.put("VerifyFlag", HtmlUtil.codeToRadios("VerifyFlag", "VerifyFlag", "N"));
			params.put("Width", "350");
		} else {
			ZCVoteSchema vote = new ZCVoteSchema();
			vote.setID(ID);
			vote.fill();
			params.putAll(vote.toMapx());
			params.put("IPLimit", HtmlUtil.codeToRadios("IPLimit", "YesOrNo", vote.getIPLimit(),
					false));
			params.put("VerifyFlag", HtmlUtil.codeToRadios("VerifyFlag", "VerifyFlag", vote
					.getVerifyFlag()));
			params.put("StartDate", DateUtil.toString(vote.getStartTime()));
			params.put("StartTime", DateUtil.toTimeString(vote.getStartTime()));
			params.put("EndDate", DateUtil.toString(vote.getEndTime()));
			params.put("EndTime", DateUtil.toTimeString(vote.getEndTime()));
		}
		return params;
	}

	public static Mapx JSCodeDialog(Mapx params) {
		String id = (String) params.get("ID");
		ZCVoteSchema vote = new ZCVoteSchema();
		vote.setID(id);
		vote.fill();
		String JSCode = "";
		JSCode = JSCode + "<div>调查：" + vote.getTitle() + "\n";
		JSCode = JSCode + "<!--" + vote.getTitle() + "-->\n";
		JSCode = JSCode
				+ "<script language='javascript' src='"
				+ new StringBuffer(String.valueOf(Config.getContextPath())).append(
						Config.getValue("Statical.TargetDir")).append("/").append(
						Application.getCurrentSiteAlias()).append("/js/vote_").append(vote.getID())
						.toString().replaceAll("//", "/") + ".js'></script>";
		JSCode = JSCode + "\n</div>";
		params.put("Title", vote.getTitle());
		params.put("JSCode", JSCode);
		return params;
	}

	public void getJSCode() {
		Mapx map = JSCodeDialog(this.Request);
		$S("JSCode", map.getString("JSCode"));
	}

	public static void dg1DataBind(DataGridAction dga) {
		String sql = "select * from ZCVote where SiteID = ? order by ID desc";
		if (dga.getTotal() == 0) {
			dga.setTotal(new QueryBuilder("select count(*) from ZCVote where SiteID = ?",
					Application.getCurrentSiteID()));
		}
		DataTable dt = new QueryBuilder(sql, Application.getCurrentSiteID()).executeDataTable();
		dt.decodeColumn("IPLimit", HtmlUtil.codeToMapx("IPLimit"));
		dga.bindData(dt);
	}

	public static void dg2DataBind(DataGridAction dga) {
		String sql = "select * from ZCVotelog where VoteID = ? order by ID desc";
		if (dga.getTotal() == 0) {
			dga.setTotal(new QueryBuilder("select count(*) from ZCVotelog where VoteID = ?", dga
					.getParam("ID")));
		}
		DataTable dt = new QueryBuilder(sql, dga.getParam("ID")).executeDataTable();
		dt.insertColumn("OtherContents");
		for (int i = 0; i < dt.getRowCount(); ++i) {
			String[] resArr = StringUtil.splitEx(dt.getString(i, "Result"), "$|");
			StringBuffer sb = new StringBuffer();
			for (int j = 0; j < resArr.length - 1; ++j) {
				String[] strArr = StringUtil.splitEx(resArr[j], "$&");
				if (strArr.length > 1) {
					sb.append(strArr[1]);
					sb.append("，");
				}
			}
			if (StringUtil.splitEx(sb.toString(), "，").length > 1) {
				dt.set(i, "OtherContents", sb.substring(0, sb.length() - 1));
			}
		}
		dga.bindData(dt);
	}

	public void add() {
		String Code = $V("Code");
		QueryBuilder qb = new QueryBuilder("select count(*) from ZCVote where Code = ?", Code);
		if (qb.executeInt() > 0) {
			this.Response.setLogInfo(0, "数据库中已经有相同的调用代码，麻烦您填写另外的调用代码，以免出错！");
			return;
		}
		ZCVoteSchema vote = new ZCVoteSchema();
		vote.setID(NoUtil.getMaxID("VoteID"));
		vote.setTitle($V("Title"));
		vote.setCode(Code);
		vote.setTotal(0L);
		vote.setIPLimit($V("IPLimit"));
		vote.setVerifyFlag($V("VerifyFlag"));
		vote.setWidth($V("Width"));
		if ((StringUtil.isEmpty($V("StartDate"))) || (StringUtil.isEmpty($V("StartTime"))))
			vote.setStartTime(new Date());
		else {
			vote.setStartTime(DateUtil.parse($V("StartDate") + " " + $V("StartTime"),
					"yyyy-MM-dd HH:mm:ss"));
		}
		if ((StringUtil.isNotEmpty($V("EndDate"))) && (StringUtil.isNotEmpty($V("EndTime")))) {
			vote.setEndTime(DateUtil.parse($V("EndDate") + " " + $V("EndTime"),
					"yyyy-MM-dd HH:mm:ss"));
		}
		vote.setSiteID(Application.getCurrentSiteID());
		vote.setAddTime(new Date());
		vote.setAddUser(User.getUserName());
		if (vote.insert())
			this.Response.setLogInfo(1, "新建调查成功！");
		else
			this.Response.setLogInfo(0, "新建调查失败！");
	}

	public void edit() {
		String Code = $V("Code");
		ZCVoteSchema vote = new ZCVoteSchema();
		vote.setID($V("ID"));
		vote.fill();

		vote.setTitle($V("Title"));
		vote.setCode(Code);
		vote.setIPLimit($V("IPLimit"));
		vote.setVerifyFlag($V("VerifyFlag"));
		vote.setWidth($V("Width"));
		if ((StringUtil.isEmpty($V("StartDate"))) || (StringUtil.isEmpty($V("StartTime"))))
			vote.setStartTime(new Date());
		else {
			vote.setStartTime(DateUtil.parse($V("StartDate") + " " + $V("StartTime"),
					"yyyy-MM-dd HH:mm:ss"));
		}
		if ((StringUtil.isNotEmpty($V("EndDate"))) && (StringUtil.isNotEmpty($V("EndTime")))) {
			vote.setEndTime(DateUtil.parse($V("EndDate") + " " + $V("EndTime"),
					"yyyy-MM-dd HH:mm:ss"));
		}
		vote.setSiteID(Application.getCurrentSiteID());
		if (vote.update()) {
			this.Response.setLogInfo(1, "修改调查成功！");
			generateJS(vote.getID());
		} else {
			this.Response.setLogInfo(0, "修改调查失败！");
		}
	}

	public static boolean generateJS(long ID) {
		return generateJS(String.valueOf(ID));
	}

	public static boolean generateJS(String ID) {
		ZCVoteSchema vote = new ZCVoteSchema();
		vote.setID(ID);
		vote.fill();
		StringBuffer sb = new StringBuffer();
		sb.append("document.write(\"<div id='vote_" + ID
				+ "' class='votecontainer' style='text-align:left' >\");\n");
		String serviceUrl = Config.getValue("ServicesContext");
		sb.append("document.write(\" <form id='voteForm_" + ID + "' name='voteForm_" + ID
				+ "' action='" + serviceUrl + Config.getValue("Vote.ActionURL")
				+ "' method='post' target='_blank'>\");\n");
		sb.append("document.write(\" <input type='hidden' id='ID' name='ID' value='" + ID
				+ "'>\");\n");
		sb
				.append("document.write(\" <input type='hidden' id='VoteFlag' name='VoteFlag' value='Y'>\");\n");
		sb.append("document.write(\" <dl>\");\n");
		ZCVoteSubjectSchema subject = new ZCVoteSubjectSchema();
		ZCVoteSubjectSet subjectSet = subject.query(new QueryBuilder(
				" where voteID =? order by ID", vote.getID()));
		for (int i = 0; i < subjectSet.size(); ++i) {
			subject = subjectSet.get(i);
			String type = "radio";
			if ("Y".equals(subject.getType())) {
				type = "checkbox";
			}
			sb.append("document.write(\"  <dt id='" + subject.getID() + "'>" + (i + 1) + "."
					+ subject.getSubject() + "</dt>\");\n");
			ZCVoteItemSchema item = new ZCVoteItemSchema();
			ZCVoteItemSet itemSet = item
					.query(new QueryBuilder("where voteID = ? and subjectID = ? order by ID", vote
							.getID(), subject.getID()));
			for (int j = 0; j < itemSet.size(); ++j) {
				item = itemSet.get(j);
				if ("0".equals(item.getItemType()))
					sb.append("document.write(\"\t\t\t\t<dd><label><input name='Subject_"
							+ subject.getID() + "' type='" + type + "' value='" + item.getID()
							+ "' />" + item.getItem() + "</label></dd>\");\n");
				else {
					sb.append("document.write(\"\t\t\t\t<dd><label><input name='Subject_"
							+ subject.getID() + "' type='" + type + "' value='" + item.getID()
							+ "' id='Subject_" + subject.getID() + "_Item_" + item.getID()
							+ "_Button'/>" + item.getItem() + "</label><input id='Subject_"
							+ subject.getID() + "_Item_" + item.getID() + "' name='Subject_"
							+ subject.getID() + "_Item_" + item.getID()
							+ "' type='text' value='' onClick=\\\"clickInput('Subject_"
							+ subject.getID() + "_Item_" + item.getID() + "');\\\"/></dd>\");\n");
				}
			}
		}
		if ("Y".equals(vote.getVerifyFlag())) {
			sb.append("document.write(\" </dl>\");\n");
			sb.append("document.write(\" <dl>\");\n");
			sb
					.append("document.write(\" <dd><img src='"
							+ Config.getContextPath()
							+ "AuthCode.jsp' alt='点击刷新验证码' height='16' align='absmiddle' style='cursor:pointer;' onClick=\\\"this.src='"
							+ Config.getContextPath()
							+ "AuthCode.jsp'\\\" />&nbsp; <input\tname='VerifyCode' type='text' style='width:60px' id='VerifyCode' class='inputText' onfocus='this.select();'/></dd>\");\n");
			sb.append("document.write(\" </dl>\");\n");
		}
		sb.append("document.write(\" <dl>\");\n");
		sb
				.append("document.write(\" <dd><input type='submit' value='提交' onclick='return checkVote("
						+ ID
						+ ");'>&nbsp;&nbsp<input type='button' value='查看' onclick='javascript:window.open(\\\""
						+ serviceUrl
						+ Config.getValue("Vote.ActionURL")
						+ "?ID="
						+ ID
						+ "\\\",\\\"VoteResult\\\")'></dd>\");\n");
		sb.append("document.write(\" </dl>\");\n");
		sb.append("document.write(\" </form>\");\n");
		sb.append("document.write(\"</div>\");\n");
		String file = new StringBuffer(String.valueOf(Config.getContextRealPath())).append(
				Config.getValue("Statical.TargetDir")).append("/").append(
				SiteUtil.getAlias(vote.getSiteID())).append("/js/").toString()
				.replaceAll("//", "/")
				+ "vote_" + ID + ".js";
		String path = file.substring(0, file.lastIndexOf("/"));
		File pathDir = new File(path);
		if (!(pathDir.exists())) {
			pathDir.mkdirs();
		}
		FileUtil.writeText(file, sb.toString());

		ArrayList deployList = new ArrayList();
		deployList.add(file);
		Deploy d = new Deploy();
		d.addJobs(vote.getSiteID(), deployList);
		return true;
	}

	public void del() {
		String ids = $V("IDs");
		if (!(StringUtil.checkID(ids))) {
			this.Response.setStatus(0);
			this.Response.setMessage("传入ID时发生错误!");
			return;
		}
		ZCVoteSchema vote = new ZCVoteSchema();
		ZCVoteSet set = vote.query(new QueryBuilder("where id in (" + ids + ")"));
		ZCVoteSubjectSchema subject = new ZCVoteSubjectSchema();
		ZCVoteSubjectSet subjectset = subject.query(new QueryBuilder(
				"where EXISTS (select * from zcvote b where zcvotesubject.voteid=b.id and b.id in ("
						+ ids + "))"));
		ZCVoteItemSchema voteItem = new ZCVoteItemSchema();
		ZCVoteItemSet itemset = voteItem.query(new QueryBuilder(
				"where EXISTS (select * from zcvote b where zcvoteitem.voteid=b.id and b.id in ("
						+ ids + "))"));

		Transaction trans = new Transaction();
		trans.add(set, 5);
		trans.add(subjectset, 5);
		trans.add(itemset, 5);
		trans.add(new QueryBuilder("delete from zcvotelog where voteid in (" + ids + ")"));
		if (trans.commit()) {
			this.Response.setMessage("删除成功!");
			this.Response.setStatus(1);
		} else {
			this.Response.setStatus(0);
			this.Response.setMessage("删除失败!");
		}
	}

	public void handStop() {
		String ids = $V("IDs");
		if (!(StringUtil.checkID(ids))) {
			this.Response.setStatus(0);
			this.Response.setMessage("传入ID时发生错误!");
			return;
		}

		Transaction trans = new Transaction();
		trans.add(new QueryBuilder("update zcvote set EndTime = ? where id in (" + ids + ") ",
				new Date()));
		if (trans.commit()) {
			this.Response.setMessage("手工终止成功!");
			this.Response.setStatus(1);
		} else {
			this.Response.setStatus(0);
			this.Response.setMessage("手工终止失败!");
		}
	}

	public static DataTable getVoteSubjects(Mapx params, DataRow parentDR) {
		String voteID = params.getString("ID");
		DataTable dt = new QueryBuilder("select * from ZCVoteSubject where voteID =? order by ID",
				voteID).executeDataTable();

		return dt;
	}

	public static DataTable getVoteItems(Mapx params, DataRow parentDR) {
		String voteID = params.getString("ID");
		DataTable dt = new QueryBuilder(
				"select * from ZCVoteItem where voteID =? and SubjectID = ? order by ID", voteID,
				parentDR.get("ID")).executeDataTable();
		dt.insertColumn("html");
		for (int i = 0; i < dt.getRowCount(); ++i) {
			String inputType = "";
			if ("Y".equals(parentDR.getString("Type")))
				inputType = "checkbox";
			else {
				inputType = "radio";
			}
			String html = "";
			if ("0".equals(dt.getString(i, "ItemType")))
				html = "<label><input name='Subject_" + dt.getString(i, "SubjectID") + "' type='"
						+ inputType + "' value='" + dt.getString(i, "id") + "' />"
						+ dt.getString(i, "item") + "</label>\n";
			else {
				html = "<label><input name='Subject_" + dt.getString(i, "SubjectID") + "' type='"
						+ inputType + "' value='" + dt.getString(i, "id") + "' id='Subject_"
						+ dt.getString(i, "SubjectID") + "_Item_" + dt.getString(i, "id")
						+ "_Button'/>" + dt.getString(i, "item") + "</label><input id='Subject_"
						+ dt.getString(i, "SubjectID") + "_Item_" + dt.getString(i, "id")
						+ "' name='Subject_" + dt.getString(i, "SubjectID") + "_Item_"
						+ dt.getString(i, "id")
						+ "' type='text' value='' onClick=\"clickInput('Subject_"
						+ dt.getString(i, "SubjectID") + "_Item_" + dt.getString(i, "id")
						+ "');\"/>\n";
			}
			dt.set(i, "html", html);
		}
		return dt;
	}
}

/*
 * Location: F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\ Qualified Name:
 * com.zving.cms.dataservice.Vote JD-Core Version: 0.5.3
 */