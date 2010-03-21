package com.zving.cms.dataservice;

import com.zving.framework.User;
import com.zving.framework.data.QueryBuilder;
import com.zving.framework.data.Transaction;
import com.zving.framework.utility.Mapx;
import com.zving.framework.utility.ServletUtil;
import com.zving.framework.utility.StringUtil;
import com.zving.platform.pub.NoUtil;
import com.zving.schema.ZCVoteLogSchema;
import com.zving.schema.ZCVoteSchema;
import com.zving.schema.ZCVoteSubjectSchema;
import com.zving.schema.ZCVoteSubjectSet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class VoteResult {
	public static boolean vote(HttpServletRequest request, HttpServletResponse response) {
		String ID = request.getParameter("ID");
		String IP = request.getRemoteHost();
		ZCVoteSchema vote = new ZCVoteSchema();
		vote.setID(ID);
		if (!(vote.fill())) {
			try {
				response.getWriter().write(
						"<script language='javascript' >alert('此调查不存在，调查ID：" + ID + "');</script>");
				return false;
			} catch (IOException e) {
				e.printStackTrace();

			}

		}
		String voteFlag = request.getParameter("VoteFlag");
		if ("Y".equals(voteFlag)) {
			String VerifyCode = request.getParameter("VerifyCode");
			Object authCode = User.getValue("_ZVING_AUTHKEY");
			if ((!("N".equals(vote.getVerifyFlag())))
					&& (((authCode == null) || (!(authCode.equals(VerifyCode)))))) {
				try {
					response.getWriter().write(
							"<script language='javascript' >alert('投票失败，验证码输入错误');</script>");
					return false;
				} catch (IOException e) {
					e.printStackTrace();

				}
			}
			boolean flag = true;
			Date now = new Date();
			if (now.before(vote.getStartTime())) {
				try {
					response.getWriter().write(
							"<script language='javascript' >alert('对不起，此调查还没有开始！开始时间为："
									+ vote.getStartTime() + "，请您到时候再来投票');</script>");
					return false;
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
			flag = false;

			if ((vote.getEndTime() != null) && (now.after(vote.getEndTime()))) {
				try {
					response
							.getWriter()
							.write(
									"<script language='javascript' >alert('对不起，此调查已经结束，不再接受投票！');</script>");
					return false;
				} catch (IOException e) {
					e.printStackTrace();

				}
			}
			flag = false;

			if ((flag) && ("Y".equals(vote.getIPLimit()))) {
				int count = new QueryBuilder(
						"select count(*) from zcvotelog where IP = ? and voteID = ?", IP, ID)
						.executeInt();
				if (count <= 0) {
					try {
						response
								.getWriter()
								.write(
										"<script language='javascript' >alert('您已经投过票了，所以本次投票无效！');</script>");
						return false;
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

			flag = false;

			String slit = "$|";
			if (flag) {
				Mapx map = ServletUtil.getParameterMap(request);
				ZCVoteSubjectSchema subject = new ZCVoteSubjectSchema();
				ZCVoteSubjectSet subjectSet = subject.query(new QueryBuilder(
						" where voteID =? order by ID", ID));
				StringBuffer resultsb = new StringBuffer();
				StringBuffer itemIDsb = new StringBuffer();
				Transaction trans = new Transaction();
				for (int i = 0; i < subjectSet.size(); ++i) {
					subject = subjectSet.get(i);
					String value = map.getString("Subject_" + subject.getID());
					if (StringUtil.isNotEmpty(value)) {
						String[] arrs = StringUtil.splitEx(value, ",");
						for (int j = 0; j < arrs.length; ++j) {
							value = arrs[j];
							if (value.startsWith("Item_")) {
								itemIDsb.append(value.substring(5) + ",");
								resultsb.append(map.getString(new StringBuffer("Subject_").append(
										subject.getID()).append("_").append(value).toString())
										+ slit);
							} else {
								itemIDsb.append(value + ",");
								String itemTextValue = map.getString("Subject_" + subject.getID()
										+ "_Item_" + value);
								if (StringUtil.isNotEmpty(itemTextValue))
									resultsb.append(value + "$&" + itemTextValue + slit);
								else
									resultsb.append(value + slit);
							}
						}
					} else {
						try {
							response.getWriter().write(
									"<script language='javascript' >alert('对不起，此项还没有投票:"
											+ subject.getSubject() + "');</script>");
							return false;
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
				trans.add(new QueryBuilder("update zcvoteitem set score=score+1 where id in ("
						+ itemIDsb.deleteCharAt(itemIDsb.length() - 1) + ")"));
				trans.add(new QueryBuilder("update zcvote set total=total+1 where id = ?", ID));
				ZCVoteLogSchema voteLog = new ZCVoteLogSchema();
				voteLog.setID(NoUtil.getMaxID("VoteLogID"));
				voteLog.setIP(IP);
				voteLog.setVoteID(ID);
				voteLog.setResult(resultsb.toString());
				voteLog.setAddTime(new Date());
				trans.add(voteLog, 1);
				if (trans.commit()) {
					try {
						response.getWriter().write(
								"<script language='javascript' >alert('投票成功，谢谢您的关注！');</script>");
						return true;
					} catch (IOException e) {
						e.printStackTrace();
					}

				}

				try {
					response.getWriter().write(
							"<script language='javascript' >alert('对不起，投票失败！');</script>");
					return false;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return true;
	}
}