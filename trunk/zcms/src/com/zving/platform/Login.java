package com.zving.platform;

import com.zving.cms.pub.PubFun;
import com.zving.framework.Config;
import com.zving.framework.CookieImpl;
import com.zving.framework.Page;
import com.zving.framework.RequestImpl;
import com.zving.framework.ResponseImpl;
import com.zving.framework.User;
import com.zving.framework.data.DataCollection;
import com.zving.framework.data.DataRow;
import com.zving.framework.data.DataTable;
import com.zving.framework.data.QueryBuilder;
import com.zving.framework.utility.Filter;
import com.zving.framework.utility.Mapx;
import com.zving.framework.utility.StringUtil;
import com.zving.schema.ZDUserSchema;
import com.zving.schema.ZDUserSet;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Login extends Page {
	public static void ssoLogin(HttpServletRequest request, HttpServletResponse response,
			String username) {
		if (username == null) {
			return;
		}
		ZDUserSchema user = new ZDUserSchema();
		user.setUserName(username);
		ZDUserSet userSet = user.query();

		if ((!(Config.isAllowLogin)) && (!(username.equalsIgnoreCase("admin")))) {
			UserLog
					.log("Log", "Login", "临时禁止登录.用户名：" + username, request.getRemoteAddr(),
							username);
			return;
		}

		if ((userSet == null) || (userSet.size() < 1)) {
			UserLog.log("Log", "Login", "SSO登陆失败.用户名：" + username, request.getRemoteAddr(),
					username);
		} else {
			user = userSet.get(0);
			User.setUserName(user.getUserName());
			User.setRealName(user.getRealName());
			User.setBranchInnerCode(user.getBranchInnerCode());
			User.setType(user.getType());
			User.setValue("Prop1", user.getProp1());
			User.setValue("Prop2", user.getProp2());
			User.setValue("Prop3", user.getProp3());
			User.setValue("Prop4", user.getProp4());
			User.setManager(true);
			User.setLogin(true);

			UserLog.log("Log", "Login", "登录成功", request.getRemoteAddr());

			DataTable dt = new QueryBuilder(
					"select name,id from zcsite order by BranchInnerCode ,orderflag ,id")
					.executeDataTable();
			dt = dt.filter(new Filter() {
				public boolean filter(Object obj) {
					DataRow dr = (DataRow) obj;
					return Priv.getPriv(User.getUserName(), "site", dr.getString("ID"),
							"site_browse");
				}
			});
			if (dt.getRowCount() > 0)
				Application.setCurrentSiteID(dt.getString(0, 1));
			else {
				Application.setCurrentSiteID("");
			}
			try {
				String path = request.getParameter("Referer");
				System.out.println("Referer:" + path);
				if (StringUtil.isNotEmpty(path)) {
					response.sendRedirect(path);
					return;
				}
				response.sendRedirect("Application.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void submit() {
		Object authCode = User.getValue("_ZVING_AUTHKEY");
		if ((authCode == null) || (!(authCode.equals($V("VerifyCode"))))) {
			this.Response.setStatus(0);
			this.Response.setMessage("验证码输入错误");
			return;
		}
		String Password = StringUtil.md5Hex($V("Password"));
		ZDUserSchema user = new ZDUserSchema();
		user.setUserName($V("UserName").toLowerCase());
		user.setPassword(Password);
		ZDUserSet userSet = user.query();

		if ((!(Config.isAllowLogin)) && (!(user.getUserName().equalsIgnoreCase("admin")))) {
			UserLog.log("Log", "Login", "临时禁止登录.用户名" + $V("UserName"), this.Request.getClientIP(),
					$V("UserName"));

			this.Response.setStatus(0);
			this.Response.setMessage("临时禁止登录，请与系统管理员联系!");

			return;
		}

		if ((userSet == null) || (userSet.size() < 1)) {
			UserLog.log("Log", "Login", "登陆失败.用户名：" + $V("UserName"), this.Request.getClientIP(),
					$V("UserName"));

			this.Response.setStatus(0);
			this.Response.setMessage("用户名或密码输入错误");
		} else {
			user = userSet.get(0);
			if ((!("admin".equalsIgnoreCase(user.getUserName()))) && ("S".equals(user.getStatus()))) {
				UserLog.log("Log", "Login", "登陆失败.用户名：" + $V("UserName") + "已停用", this.Request
						.getClientIP(), $V("UserName"));

				this.Response.setStatus(0);
				this.Response.setMessage("该用户处于停用状态，请联系管理员！");
				return;
			}
			User.setUserName(user.getUserName());
			User.setRealName(user.getRealName());
			User.setBranchInnerCode(user.getBranchInnerCode());
			User.setType(user.getType());
			User.setValue("Prop1", user.getProp1());
			User.setValue("Prop2", user.getProp2());
			User.setValue("Prop3", user.getProp3());
			User.setValue("Prop4", user.getProp4());
			User.setManager(true);
			User.setLogin(true);

			UserLog.log("Log", "Login", "登陆成功", this.Request.getClientIP());

			DataTable dt = new QueryBuilder(
					"select name,id from zcsite order by BranchInnerCode ,orderflag ,id")
					.executeDataTable();
			dt = dt.filter(new Filter() {
				public boolean filter(Object obj) {
					DataRow dr = (DataRow) obj;
					return Priv.getPriv(User.getUserName(), "site", dr.getString("ID"),
							"site_browse");
				}
			});
			String siteID = getCookie().getCookie("SiteID");
			if ((StringUtil.isNotEmpty(siteID))
					&& (Priv.getPriv(User.getUserName(), "site", siteID, "site_browse"))) {
				Application.setCurrentSiteID(siteID);
			} else if (dt.getRowCount() > 0)
				Application.setCurrentSiteID(dt.getString(0, 1));
			else {
				Application.setCurrentSiteID("");
			}

			this.Response.setStatus(1);
			redirect("Application.jsp");
		}
	}

	public void getAllPriv() {
		getAllPriv(this.Response);
	}

	public static DataCollection getAllPriv(DataCollection Response) {
		if ("admin".equalsIgnoreCase(User.getUserName())) {
			Response.put("isBranchAdmin", "Y");
		} else {
			String UserName = User.getUserName();
			long SiteID = Application.getCurrentSiteID();
			List roleCodeList = PubFun.getRoleCodesByUserName(UserName);
			Response.put("isBranchAdmin", "N");
			StringBuffer privTypes = new StringBuffer();
			StringBuffer privTypeItems = new StringBuffer();
			Object[] ks = Priv.PRIV_MAP.keyArray();
			Object[] vs = Priv.PRIV_MAP.valueArray();
			for (int i = 0; i < Priv.PRIV_MAP.size(); ++i) {
				if ("menu".equals(ks[i])) {
					continue;
				}
				privTypes.append(ks[i].toString());
				privTypes.append(",");
				Mapx map = (Mapx) vs[i];
				Object[] ks2 = map.keyArray();
				for (int j = 0; j < map.size(); ++j) {
					privTypeItems.append(ks2[j].toString());
					privTypeItems.append(",");
				}
			}
			privTypes.deleteCharAt(privTypes.length() - 1);
			privTypeItems.deleteCharAt(privTypeItems.length() - 1);
			Response.put("privTypes", privTypes.toString());
			Response.put("privTypeItems", privTypeItems.toString());
			Response.put("roleCodes", (roleCodeList == null) ? "" : StringUtil.join(roleCodeList
					.toArray()));

			QueryBuilder qb = new QueryBuilder(
					"select ID,Code,Value from ZDPrivilege where OwnerType =? and Owner=? and PrivType='site' and ID =?");

			qb.add("U");
			qb.add(UserName);
			qb.add(SiteID);
			Response.put("siteDT", qb.executeDataTable());

			for (int i = 0; i < Priv.PRIV_MAP.size(); ++i) {
				if ("menu".equals(ks[i]))
					continue;
				if ("site".equals(ks[i])) {
					continue;
				}
				qb = new QueryBuilder(
						"select ID,Code,Value from ZDPrivilege where OwnerType =? and Owner=? and PrivType=? and exists (select '' from ZCCatalog where SiteID =? and ZCCatalog.InnerCode = ZDPrivilege.ID ) ");
				qb.add("U");
				qb.add(UserName);
				qb.add(ks[i]);
				qb.add(SiteID);
				Response.put(ks[i] + "DT", qb.executeDataTable());
			}
			if (roleCodeList == null) {
				return Response;
			}
			for (int i = 0; i < roleCodeList.size(); ++i) {
				qb = new QueryBuilder(
						"select ID,Code,Value from ZDPrivilege where OwnerType =? and Owner=? and PrivType='site' and ID =?");

				qb.add("R");
				qb.add(roleCodeList.get(i));
				qb.add(SiteID);
				Response.put(roleCodeList.get(i) + "site" + "DT", qb.executeDataTable());
				for (int j = 0; j < Priv.PRIV_MAP.size(); ++j) {
					if ("menu".equals(ks[j]))
						continue;
					if ("site".equals(ks[j])) {
						continue;
					}
					qb = new QueryBuilder(
							"select ID,Code,Value from ZDPrivilege where OwnerType =? and Owner=? and PrivType=? and exists (select '' from ZCCatalog where SiteID =? and ZCCatalog.InnerCode = ZDPrivilege.ID ) ");
					qb.add("R");
					qb.add(roleCodeList.get(i));
					qb.add(ks[j]);
					qb.add(SiteID);
					Response.put(roleCodeList.get(i) + ks[j].toString() + "DT", qb
							.executeDataTable());
				}
			}
		}
		return Response;
	}

	public void getVerifyCode() {
		if (!(this.Request.getClientIP().equals("127.0.0.1"))) {
			return;
		}
		this.Response.put("VerifyCode", User.getValue("_ZVING_AUTHKEY"));
	}
}

/*
 * Location: F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\ Qualified Name:
 * com.zving.platform.Login JD-Core Version: 0.5.3
 */