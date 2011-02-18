package com.htsoft.core.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.Authentication;
import org.springframework.security.context.SecurityContext;
import org.springframework.security.context.SecurityContextHolder;

import com.htsoft.oa.model.system.AppUser;

public class ContextUtil {
	private static final Log logger = LogFactory.getLog(ContextUtil.class);

	public static AppUser getCurrentUser() {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		if (securityContext != null) {
			Authentication auth = securityContext.getAuthentication();
			if (auth != null) {
				Object principal = auth.getPrincipal();
				if (principal instanceof AppUser)
					return (AppUser) principal;
			} else {
				logger
						.warn("WARN: securityContext cannot be lookuped using SecurityContextHolder.");
			}
		}
		return null;
	}

	public static Long getCurrentUserId() {
		AppUser curUser = getCurrentUser();
		if (curUser != null)
			return curUser.getUserId();
		return null;
	}
}
