package com.zving.member;

import com.zving.bbs.admin.ForumUtil;
import com.zving.cms.pub.SiteUtil;
import com.zving.framework.Ajax;
import com.zving.framework.Config;
import com.zving.framework.CookieImpl;
import com.zving.framework.RequestImpl;
import com.zving.framework.ResponseImpl;
import com.zving.framework.User;
import com.zving.framework.data.DataTable;
import com.zving.framework.data.QueryBuilder;
import com.zving.framework.utility.Mapx;
import com.zving.framework.utility.ServletUtil;
import com.zving.framework.utility.StringUtil;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;

public class Login extends Ajax {
	public static Mapx initSiteLinks(Mapx params) {
		String SiteID = params.getString("SiteID");
		String UserName = User.getUserName();
		String SiteLinks = "";
		if (StringUtil.isEmpty(SiteID)) {
			if (StringUtil.isEmpty(UserName)) {
				params.put("SiteLinks", "获取网站链接失败");
				return params;
			}
			Member member = new Member(UserName);
			member.fill();
			SiteID = String.valueOf(member.getSiteID());
		}

		if ((User.isLogin()) && (User.isMember())) {
			params.put("display", "none");
		}
		String SiteAlias = SiteUtil.getAlias(SiteID);
		String Path = Config.getContextPath() + Config.getValue("UploadDir") + "/" + SiteAlias
				+ "/";
		DataTable dt = new QueryBuilder(
				"select * from zccatalog where Type = 1 and ParentID = 0 and SiteID = ?", SiteID)
				.executePagedDataTable(10, 0);
		SiteLinks = SiteLinks + "<a href='" + Path + "'>首页</a>&nbsp;&nbsp;&nbsp;";
		if ((dt != null) && (dt.getRowCount() > 0)) {
			for (int i = 0; i < dt.getRowCount(); ++i) {
				SiteLinks = SiteLinks + "<a href='" + Path + dt.getString(i, "URL") + "'>"
						+ dt.getString(i, "Name") + "</a>&nbsp;&nbsp;&nbsp;";
			}
		}
		params.put("SiteID", SiteID);
		params.put("SiteLinks", SiteLinks);
		return params;
	}

	public void doLogin() {
		String userName = $V("UserName");
		String passWord = $V("PassWord");
		int CookieTime = Integer.parseInt($V("CookieTime"));
		Member member = new Member(userName);
		if (member.IsExists()) {
			member.fill();
			if (member.checkPassWord(passWord)) {
				String VerifyCookie = StringUtil.md5Hex(member.getPassword().substring(0, 10)
						+ System.currentTimeMillis());
				this.Cookie.setCookie("VerifyCookie", VerifyCookie, CookieTime);
				this.Cookie.setCookie("UserName", userName, CookieTime);
				User.setManager(false);
				User.setLogin(true);
				User.setUserName(userName);
				User.setRealName(member.getName());
				User.setType(member.getType());
				User.setMember(true);
				User.setValue("SiteID", member.getSiteID());
				User.setValue("UserName", member.getUserName());
				User.setValue("Type", member.getType());

				ForumUtil.allowVisit(String.valueOf(member.getSiteID()));
				if (StringUtil.isNotEmpty(member.getName()))
					User.setValue("Name", member.getName());
				else {
					User.setValue("Name", member.getUserName());
				}
				member.setProp1(VerifyCookie);
				member.setMemberLevel((String) new QueryBuilder(new StringBuffer(
						"select ID from ZDMemberLevel where Score <= ").append(member.getScore())
						.append(" order by Score desc").toString()).executeOneValue());
				member.setLastLoginIP(this.Request.getClientIP());
				member.setLastLoginTime(new Date());
				member.update();
				this.Response.setLogInfo(1, "登录成功，欢迎您 " + userName);
				this.Response.put("UserName", userName);
				this.Response.put("Name", member.getName());
				this.Response.put("Type", member.getType());
			} else {
				this.Response.setLogInfo(0, "用户名或密码错误，请重新输入");
			}
		} else {
			this.Response.setLogInfo(0, "用户名不存在，请重新输入");
		}
	}

	public static void checkAndLogin(HttpServletRequest request) {
		String VerifyCookie = ServletUtil.getCookieValue(request, "VerifyCookie");
		String UserName = ServletUtil.getCookieValue(request, "UserName");
		if ((!(User.isManager())) && (!(User.isLogin())) && (StringUtil.isNotEmpty(UserName))) {
			Member member = new Member(UserName);
			if ((!(member.fill())) || (!(StringUtil.isNotEmpty(member.getProp1())))
					|| (!(member.getProp1().equalsIgnoreCase(VerifyCookie))))
				return;
			User.setManager(false);
			User.setLogin(true);
			User.setUserName(member.getUserName());
			User.setRealName(member.getName());
			User.setType(member.getType());
			User.setMember(true);
			User.setValue("SiteID", member.getSiteID());
			User.setValue("UserName", member.getUserName());
			User.setValue("Type", member.getType());

			ForumUtil.allowVisit(String.valueOf(member.getSiteID()));
			if (StringUtil.isNotEmpty(member.getName()))
				User.setValue("Name", member.getName());
			else {
				User.setValue("Name", member.getUserName());
			}
			member.setProp1(VerifyCookie);
			member.setLastLoginIP(request.getRemoteAddr());
			member.setLastLoginTime(new Date());
			member.update();
		}
	}

	public void Logout() {
		this.Cookie.setCookie("VerifyCookie", "", 0);
		this.Cookie.setCookie("UserName", "", 0);
		String SiteID = String.valueOf(User.getValue("SiteID"));
		User.setUserName("");
		User.setLogin(false);
		User.setMember(false);
		User.destory();
		this.Response.setStatus(1);
		this.Response.setMessage(SiteID);
	}

	public void loginComment(HttpServletRequest request, String userName, String passWord) {
		Member member = new Member(userName);
		if (member.IsExists()) {
			member.fill();
			if (member.checkPassWord(passWord)) {
				String VerifyCookie = StringUtil.md5Hex(member.getPassword().substring(0, 10)
						+ System.currentTimeMillis());
				this.Cookie = new CookieImpl(request);
				this.Cookie.setCookie("VerifyCookie", VerifyCookie, 0);
				this.Cookie.setCookie("UserName", userName, 0);
				User.setManager(false);
				User.setLogin(true);
				User.setUserName(userName);
				User.setRealName(member.getName());
				User.setType(member.getType());
				User.setMember(true);
				User.setValue("SiteID", member.getSiteID());
				User.setValue("UserName", member.getUserName());
				User.setValue("Type", member.getType());

				ForumUtil.allowVisit(String.valueOf(member.getSiteID()));
				if (StringUtil.isNotEmpty(member.getName()))
					User.setValue("Name", member.getName());
				else {
					User.setValue("Name", member.getUserName());
				}
				member.setProp1(VerifyCookie);
				member.setMemberLevel((String) new QueryBuilder(new StringBuffer(
						"select ID from ZDMemberLevel where Score <= ").append(member.getScore())
						.append(" order by Score desc").toString()).executeOneValue());
				member.setLastLoginIP(request.getRemoteAddr());
				member.setLastLoginTime(new Date());
				member.update();
				this.Response.setLogInfo(1, "登录成功，欢迎您 " + userName);
				this.Response.put("UserName", userName);
				this.Response.put("Name", member.getName());
				this.Response.put("Type", member.getType());
			} else {
				this.Response.setLogInfo(0, "用户名或密码错误，请重新输入");
			}
		} else {
			this.Response.setLogInfo(0, "用户名不存在，请重新输入");
		}
	}
}

/*
 * Location: F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\ Qualified Name:
 * com.zving.member.Login JD-Core Version: 0.5.3
 */