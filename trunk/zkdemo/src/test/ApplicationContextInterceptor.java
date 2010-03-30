/**
 * <p>项目名称：</p>
 * <p>版权所有 (c) </p>
 * <p>版本：1.0</p>
 * <p>日期：Mar 29, 2010</p>
 * <p>更新：</p>
 */
package test;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zkplus.util.ThreadLocalListener;

import com.hxzy.base.web.intercepter.Authenticatable;

import web.UserWindow;

/**
 * @author xiacc
 * 
 * 描述：
 */
public class ApplicationContextInterceptor extends ThreadLocalListener {

	/* (non-Javadoc)
	 * @see org.zkoss.zkplus.util.ThreadLocalListener#init(org.zkoss.zk.ui.Component, org.zkoss.zk.ui.event.Event)
	 */
	@Override
	public boolean init(Component comp, Event evt) {
		
		if(comp instanceof Authenticatable){
			
			System.out.println("ThreadLocalListenerThreadLocalListenerThreadLocalListenerThreadLocalListener");
			
			Authenticatable authenticatable = (Authenticatable)comp;
			
			System.out.println(authenticatable.needAuthentication());
			
			
			
		}
		
		System.out.println(evt.getName());
		return super.init(comp, evt);
	}
	
	
	
	
	

//	public void handle(Session session, HttpServletRequest request, HttpServletResponse response) {
//		
//		System.out.println(request.getServletPath());
//
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see org.zkoss.zk.ui.util.RequestInterceptor#request(org.zkoss.zk.ui.Session,
//	 *      java.lang.Object, java.lang.Object)
//	 */
//	public void request(Session session, Object request, Object response) {
//		this.handle(session, (HttpServletRequest) request, (HttpServletResponse) response);
//	}
//
//	/* (non-Javadoc)
//	 * @see org.zkoss.zk.ui.util.EventInterceptor#afterProcessEvent(org.zkoss.zk.ui.event.Event)
//	 */
//	public void afterProcessEvent(Event event) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	/* (non-Javadoc)
//	 * @see org.zkoss.zk.ui.util.EventInterceptor#beforePostEvent(org.zkoss.zk.ui.event.Event)
//	 */
//	public Event beforePostEvent(Event event) {
//		// TODO Auto-generated method stub
//		return event;
//	}
//
//	/* (non-Javadoc)
//	 * @see org.zkoss.zk.ui.util.EventInterceptor#beforeProcessEvent(org.zkoss.zk.ui.event.Event)
//	 */
//	public Event beforeProcessEvent(Event event) {
//		System.out.println(event.getName());
//		return event;
//	}
//
//	/* (non-Javadoc)
//	 * @see org.zkoss.zk.ui.util.EventInterceptor#beforeSendEvent(org.zkoss.zk.ui.event.Event)
//	 */
//	public Event beforeSendEvent(Event event) {
//		// TODO Auto-generated method stub
//		return event;
//	}

}
