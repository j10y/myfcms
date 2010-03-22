 package com.zving.framework;
 
 import com.zving.framework.extend.ExtendManager;
 import com.zving.framework.utility.FileUtil;
 import com.zving.framework.utility.Mapx;
 import java.util.ArrayList;
 import java.util.List;
 import javax.servlet.http.HttpSession;
 import javax.servlet.http.HttpSessionEvent;
 import javax.servlet.http.HttpSessionListener;
 
 public class SessionListener
   implements HttpSessionListener
 {
   private static Mapx map = new Mapx();
 
   public void sessionCreated(HttpSessionEvent arg0)
   {
     Config.OnlineUserCount += 1;
     synchronized (SessionListener.class) {
       HttpSession hs = arg0.getSession();
       map.put(hs.getId(), hs);
       if (ExtendManager.hasAction("AfterSessionCreate"))
         ExtendManager.executeAll("AfterSessionCreate", new Object[] { hs });
     }
   }
 
   public void sessionDestroyed(HttpSessionEvent arg0)
   {
     Config.OnlineUserCount -= 1;
     User u = (User)arg0.getSession().getAttribute("_ZVING_USER");
     if (u != null) {
       if (u.getLoginFlag()) {
         Config.LoginUserCount -= 1;
       }
       if (Config.isDebugMode()) {
         FileUtil.delete(Config.getContextRealPath() + "WEB-INF/cache/" + u.SessionID);
       }
     }
     if (ExtendManager.hasAction("BeforeSessionDestroy")) {
       ExtendManager.executeAll("BeforeSessionDestroy", new Object[] { arg0.getSession() });
     }
     synchronized (SessionListener.class) {
       map.remove(arg0.getSession().getId());
     }
   }
 
   public static void clear()
   {
     synchronized (SessionListener.class) {
       map.clear();
     }
   }
 
   public static void forceExit()
   {
     synchronized (SessionListener.class) {
       Object[] ks = map.keyArray();
       Object[] vs = map.valueArray();
       HttpSession session = null;
       for (int i = 0; i < map.size(); ++i) {
         if (ks[i].equals(User.getSessionID())) {
           continue;
         }
         session = (HttpSession)vs[i];
         session.invalidate();
       }
     }
   }
 
   public static void forceExit(String sid)
   {
     synchronized (SessionListener.class) {
       HttpSession session = (HttpSession)map.get(sid);
       session.invalidate();
     }
   }
 
   public static User[] getUsers()
   {
     Object[] vs = map.keyArray();
     User[] arr = new User[vs.length];
     HttpSession session = null;
     for (int i = 0; i < vs.length; ++i) {
       session = (HttpSession)map.get(vs[i]);
       User u = (User)session.getAttribute("_ZVING_USER");
       arr[i] = u;
     }
     return arr;
   }
 
   public static User[] getUsers(String status)
   {
     Object[] vs = map.keyArray();
     ArrayList arr = new ArrayList(vs.length);
     HttpSession session = null;
     for (int i = 0; i < vs.length; ++i) {
       session = (HttpSession)map.get(vs[i]);
       User u = (User)session.getAttribute("_ZVING_USER");
       if (status.equalsIgnoreCase(u.Status)) {
         arr.add(u);
       }
     }
     return ((User[])arr.toArray(new User[arr.size()]));
   }
 
   public static List getUserNames(String status)
   {
     User[] arr = getUsers(status);
     List userNameArr = new ArrayList(arr.length);
     for (int i = 0; i < arr.length; ++i) {
       userNameArr.add(arr[i].UserName);
     }
     return userNameArr;
   }
 
   public static User getUser(String userName)
   {
     User[] users = getUsers();
     for (int i = 0; i < users.length; ++i) {
       if (userName.equals(users[i].UserName)) {
         return users[i];
       }
     }
     return null;
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.framework.SessionListener
 * JD-Core Version:    0.5.3
 */