package base.web.listener;


import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.web.context.WebApplicationContext;

import base.util.WebAppUtil;


/**
 * <p>
 * ����: ApplicationContextListener
 * </p>
 * <p>
 * ����: WebApplictionContext listener��
 * </p>
 */
public class ApplicationContextListener implements ApplicationListener {

  /*
   * @see org.springframework.context.ApplicationListener#onApplicationEvent(org.springframework.context.ApplicationEvent)
   */
  public void onApplicationEvent(ApplicationEvent evt) {
    // �ж��¼������Ƿ�ΪContextRefreshedEvent�������������ش���
    if ((evt instanceof ContextRefreshedEvent)) {
      ContextRefreshedEvent e = (ContextRefreshedEvent) evt;
      ApplicationContext context = e.getApplicationContext();
      // �ж�ApplicationContext�������Ƿ�ΪWebApplicationContext,�����������ش���
      if (context instanceof WebApplicationContext)
        // ��WebApplicationContext���������ΪServletContext������
        WebAppUtil.setWebApplicationContext(((WebApplicationContext) context)
            .getServletContext(), (WebApplicationContext) context);
    }

  }

}
