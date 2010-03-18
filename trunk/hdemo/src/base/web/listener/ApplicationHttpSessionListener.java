package base.web.listener;


/**
 * <p>��Ŀ���ƣ�����ģ��</p>
 * <p>��Ȩ���� (c) 2005 ����ȫ����Ϣ�Ƽ����޹�˾</p>
 * <p>���ߣ�</p>
 * <p>�汾��1.0</p>
 * <p>���ڣ�2005-11-28</p>
 * <p>���£�</p>
 */

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import base.constant.Constant;
import base.user.model.OnlineUserInfo;
import base.user.model.UserInfo;
import base.util.WebAppUtil;


/**
 * <p>
 * ����: ApplicationHttpSessionListener
 * </p>
 * <p>
 * ����: Ӧ�ó���Session������
 * </p>
 */
public class ApplicationHttpSessionListener implements HttpSessionAttributeListener, HttpSessionListener {

  public static HashMap sessionUserMap = new HashMap();

  /*
   * @see javax.servlet.http.HttpSessionListener#sessionCreated(javax.servlet.http.HttpSessionEvent)
   */
  public void sessionCreated(HttpSessionEvent arg0) {
    // TODO Auto-generated method stub

  }

  /*
   * @see javax.servlet.http.HttpSessionListener#sessionDestroyed(javax.servlet.http.HttpSessionEvent)
   */
  public void sessionDestroyed(HttpSessionEvent e) {
    HttpSession session = e.getSession();
    // ����SessionId��ȡ�û�����
    String userCode = (String) sessionUserMap.get(session.getId());
    sessionUserMap.remove(session.getId());
    if (userCode == null)
      return;
    // SessionʧЧʱ���û���Ϣ�������û�����ɾ��
    Map map = (Map) WebAppUtil.getOnlineUserInfo(session.getServletContext());
    if (map == null) {
      return;
    }
    OnlineUserInfo onlineUserInfo = (OnlineUserInfo) map.get(userCode);
    if (onlineUserInfo != null && onlineUserInfo.getUserSession() == session) {
      map.remove(userCode);
    }
  }
  
    /**
     * session ������
     * attributeAdded
     * ���session attributeֵʱ����
     */
	public void attributeAdded(HttpSessionBindingEvent se) {
        if(se.getName().equals(Constant.ATTRIBUTE_USER_INFO)){
        	UserInfo userInfo = (UserInfo)se.getValue();
	        try {
	            SessionCount.getInstance().addLogonUser(userInfo.getUser());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
	}
	
    /**
     * session ������
     * attributeRemoved
     * ��ǰsession attributeֵ��ɾ��ʱ���� 
     */
	public void attributeRemoved(HttpSessionBindingEvent se) {
        if(se.getName().equals(Constant.ATTRIBUTE_USER_INFO)){
        	UserInfo userInfo = (UserInfo)se.getValue();
            SessionCount.getInstance().delLogonUser(userInfo.getUser().getId().toString());
        }
		
	}
	
	public void attributeReplaced(HttpSessionBindingEvent se) {

	}

}