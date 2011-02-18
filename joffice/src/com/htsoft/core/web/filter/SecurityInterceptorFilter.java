package com.htsoft.core.web.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.AccessDeniedException;
import org.springframework.security.Authentication;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.htsoft.core.security.SecurityDataSource;

public class SecurityInterceptorFilter extends OncePerRequestFilter {
	private HashMap<String, Set<String>> roleUrlsMap = null;
	private SecurityDataSource securityDataSource;

	public void setSecurityDataSource(SecurityDataSource securityDataSource) {
		this.securityDataSource = securityDataSource;
	}

	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
			FilterChain chain) throws ServletException, IOException {
		String url = request.getRequestURI();

		if (StringUtils.hasLength(request.getContextPath())) {
			String contextPath = request.getContextPath();
			int index = url.indexOf(contextPath);
			if (index != -1) {
				url = url.substring(index + contextPath.length());
			}
		}
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		boolean isSuperUser = false;
		for (int i = 0; i < auth.getAuthorities().length; ++i) {
			if ("超级管理员".equals(auth.getAuthorities()[i].getAuthority())) {
				isSuperUser = true;
				break;
			}
		}
		if ((!isSuperUser) && (!isUrlGrantedRight(url, auth))) {
			if (this.logger.isDebugEnabled()) {
				this.logger.info("ungranted url:" + url);
			}
			throw new AccessDeniedException("Access is denied! Url:" + url + " User:"
					+ SecurityContextHolder.getContext().getAuthentication().getName());
		}

		if (this.logger.isDebugEnabled()) {
			this.logger.debug("pass the url:" + url);
		}

		chain.doFilter(request, response);
	}

	private boolean isUrlGrantedRight(String url, Authentication auth) {
		for (GrantedAuthority ga : auth.getAuthorities()) {
			Set urlSet = (Set) this.roleUrlsMap.get(ga.getAuthority());

			if ((urlSet != null) && (urlSet.contains(url))) {
				return true;
			}
		}
		return false;
	}

	public void loadDataSource() {
		this.roleUrlsMap = this.securityDataSource.getDataSource();
	}

	public void afterPropertiesSet() throws ServletException {
		loadDataSource();
		if (this.roleUrlsMap == null)
			throw new RuntimeException("没有进行设置系统的权限匹配数据源");
	}
}
