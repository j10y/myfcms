/**
 * <p>项目名称：</p>
 * <p>版权所有 (c) </p>
 * <p>版本：1.0</p>
 * <p>日期：Mar 29, 2010</p>
 * <p>更新：</p>
 */
package test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.util.RequestInterceptor;

/**
 * @author xiacc
 * 
 * 描述：
 */
public class ApplicationContextInterceptor implements RequestInterceptor {

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

}
