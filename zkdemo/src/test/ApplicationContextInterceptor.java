/**
 * <p>��Ŀ���ƣ�</p>
 * <p>��Ȩ���� (c) </p>
 * <p>�汾��1.0</p>
 * <p>���ڣ�Mar 29, 2010</p>
 * <p>���£�</p>
 */
package test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.EventInterceptor;

/**
 * @author xiacc
 * 
 * ������
 */
public class ApplicationContextInterceptor implements EventInterceptor {

	public void handle(Session session, HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println(request.getServletPath());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.zkoss.zk.ui.util.RequestInterceptor#request(org.zkoss.zk.ui.Session,
	 *      java.lang.Object, java.lang.Object)
	 */
	public void request(Session session, Object request, Object response) {
		this.handle(session, (HttpServletRequest) request, (HttpServletResponse) response);
	}

	/* (non-Javadoc)
	 * @see org.zkoss.zk.ui.util.EventInterceptor#afterProcessEvent(org.zkoss.zk.ui.event.Event)
	 */
	public void afterProcessEvent(Event event) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.zkoss.zk.ui.util.EventInterceptor#beforePostEvent(org.zkoss.zk.ui.event.Event)
	 */
	public Event beforePostEvent(Event event) {
		// TODO Auto-generated method stub
		return event;
	}

	/* (non-Javadoc)
	 * @see org.zkoss.zk.ui.util.EventInterceptor#beforeProcessEvent(org.zkoss.zk.ui.event.Event)
	 */
	public Event beforeProcessEvent(Event event) {
		System.out.println(event.getName());
		return event;
	}

	/* (non-Javadoc)
	 * @see org.zkoss.zk.ui.util.EventInterceptor#beforeSendEvent(org.zkoss.zk.ui.event.Event)
	 */
	public Event beforeSendEvent(Event event) {
		// TODO Auto-generated method stub
		return event;
	}

}
