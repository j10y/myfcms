package com.htsoft.core.log;

import com.htsoft.core.util.ContextUtil;
import com.htsoft.oa.model.system.AppUser;
import com.htsoft.oa.model.system.SystemLog;
import com.htsoft.oa.service.system.SystemLogService;
import java.lang.reflect.Method;
import java.util.Date;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;

public class LogAspect {

	@Resource
	private SystemLogService systemLogService;
	private Log logger = LogFactory.getLog(LogAspect.class);

	public Object doSystemLog(ProceedingJoinPoint point) throws Throwable {
		String methodName = point.getSignature().getName();

		if ((StringUtils.isNotEmpty(methodName)) && (!methodName.startsWith("set"))
				&& (!methodName.startsWith("get"))) {
			Class targetClass = point.getTarget().getClass();
			Method method = targetClass.getMethod(methodName, new Class[0]);

			if (method != null) {
				boolean hasAnnotation = method.isAnnotationPresent(Action.class);

				if (hasAnnotation) {
					Action annotation = (Action) method.getAnnotation(Action.class);

					String methodDescp = annotation.description();
					if (this.logger.isDebugEnabled()) {
						this.logger.debug("Action method:" + method.getName() + " Description:"
								+ methodDescp);
					}

					AppUser appUser = ContextUtil.getCurrentUser();
					if (appUser != null) {
						try {
							SystemLog sysLog = new SystemLog();

							sysLog.setCreatetime(new Date());
							sysLog.setUserId(appUser.getUserId());
							sysLog.setUsername(appUser.getFullname());
							sysLog.setExeOperation(methodDescp);

							this.systemLogService.save(sysLog);
						} catch (Exception ex) {
							this.logger.error(ex.getMessage());
						}
					}
				}

			}

		}

		return point.proceed();
	}
}
