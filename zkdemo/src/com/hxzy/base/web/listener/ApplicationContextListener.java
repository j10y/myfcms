package com.hxzy.base.web.listener;


import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.web.context.WebApplicationContext;

import com.hxzy.base.util.WebAppUtil;


/**
 * <p>
 * 类名: ApplicationContextListener
 * </p>
 * <p>
 * 描述: WebApplictionContext listener类
 * </p>
 */
public class ApplicationContextListener implements ApplicationListener {

  /*
   * @see org.springframework.context.ApplicationListener#onApplicationEvent(org.springframework.context.ApplicationEvent)
   */
  public void onApplicationEvent(ApplicationEvent evt) {
    // 判断事件类型是否为ContextRefreshedEvent，如是则进行相关处理
    if ((evt instanceof ContextRefreshedEvent)) {
      ContextRefreshedEvent e = (ContextRefreshedEvent) evt;
      ApplicationContext context = e.getApplicationContext();
      // 判断ApplicationContext的类型是否为WebApplicationContext,如是则进行相关处理
      if (context instanceof WebApplicationContext)
        // 将WebApplicationContext对象放设置为ServletContext的属性
        WebAppUtil.setWebApplicationContext(((WebApplicationContext) context)
            .getServletContext(), (WebApplicationContext) context);
    }

  }

}
