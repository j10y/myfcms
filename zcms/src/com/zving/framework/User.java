package com.zving.framework;

import com.zving.framework.utility.Mapx;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String ONLINE = "online";
	public static final String OFFLINE = "offline";
	public static final String BUSY = "busy";
	public static final String AWAY = "away";
	public static final String HIDDEN = "hidden";
	public static final Mapx USER_STATUS_MAP = new Mapx();
	public String Type;
	public String Status;
	public String UserName;
	public String RealName;
	public String BranchInnerCode;
	public boolean isLogin = false;

	public boolean isMember = true;

	public boolean isManager = false;
	public String SessionID;
	public Mapx Map = new Mapx();
	private static ThreadLocal UserLocal;

	static {
		USER_STATUS_MAP.put("online", "在线");
		USER_STATUS_MAP.put("offline", "下线");
		USER_STATUS_MAP.put("busy", "忙碌");
		USER_STATUS_MAP.put("away", "离开");
		USER_STATUS_MAP.put("hidden", "隐身");

		UserLocal = new ThreadLocal();
	}

	public static String getUserName() {
		return getCurrent().UserName;
	}

	public static void setUserName(String UserName) {
		getCurrent().UserName = UserName;
	}

	public static String getRealName() {
		return getCurrent().RealName;
	}

	public static void setRealName(String RealName) {
		getCurrent().RealName = RealName;
	}

	public static String getBranchInnerCode() {
		return getCurrent().BranchInnerCode;
	}

	public static void setBranchInnerCode(String BranchInnerCode) {
		getCurrent().BranchInnerCode = BranchInnerCode;
	}

	public static String getType() {
		return getCurrent().Type;
	}

	public static void setType(String Type) {
		getCurrent().Type = Type;
	}

	public static String getStatus() {
		return getCurrent().Status;
	}

	public static void setStatus(String Status) {
		getCurrent().Status = Status;
	}

	public static int getValueCount() {
		return getCurrent().Map.size();
	}

	public static Object getValue(Object key) {
		return getCurrent().Map.get(key);
	}

	public static Mapx getValues() {
		return getCurrent().Map;
	}

	public static void setValue(Object key, Object value) {
		getCurrent().Map.put(key, value);
	}

	public static boolean isLogin() {
		return getCurrent().isLogin;
	}

	public static void setLogin(boolean isLogin) {
		if (isLogin) {
			if (!(getCurrent().isLogin))
				Config.LoginUserCount += 1;
		} else {
			Config.LoginUserCount -= 1;
		}
		getCurrent().isLogin = isLogin;
	}

	public static void setCurrent(User user) {
		UserLocal.set(user);
	}

	public static User getCurrent() {
		return ((User) UserLocal.get());
	}

	protected static void cacheUser(User u) {
		try {
			FileOutputStream f = new FileOutputStream(Config.getContextRealPath()
					+ "WEB-INF/cache/" + u.SessionID);
			ObjectOutputStream s = new ObjectOutputStream(f);
			s.writeObject(u);
			s.flush();
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected static User getCachedUser(String sessionID) {
		try {
			File in = new File(Config.getContextRealPath() + "WEB-INF/cache/" + sessionID);
			if (in.exists()) {
				ObjectInputStream s = new ObjectInputStream(new FileInputStream(in));
				Object o = s.readObject();
				if (User.class.isInstance(o)) {
					s.close();
					in.delete();
					User u = (User) o;
					if (u.isLogin) {
						Config.LoginUserCount += 1;
					}
					return u;
				}
				s.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void destory() {
		setCurrent(new User());
	}

	public static String getSessionID() {
		return getCurrent().SessionID;
	}

	protected static void setSessionID(String sessionID) {
		getCurrent().SessionID = sessionID;
	}

	public static boolean isMember() {
		return getCurrent().isMember;
	}

	public static void setMember(boolean isMember) {
		getCurrent().isMember = isMember;
	}

	public static boolean isManager() {
		return getCurrent().isManager;
	}

	public static void setManager(boolean isManager) {
		getCurrent().isManager = isManager;
	}

	public boolean getLoginFlag() {
		return this.isLogin;
	}

	public boolean getManagerFlag() {
		return this.isManager;
	}
}

/*
 * Location: F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\ Qualified Name:
 * com.zving.framework.User JD-Core Version: 0.5.3
 */