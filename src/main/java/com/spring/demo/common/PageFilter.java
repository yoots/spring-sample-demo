/*
 * Copyright (c) 2014 아시아나IDT
 * 
 * 이 프로그램은 저작권 보호법에 의해 보호됩니다.
 * 이 프로그램의 일부나 전부를 무단으로 복제하거나 배포하는 경우에는
 * 저작권의 침해가 되므로 주의하시기 바랍니다.
 */
package com.spring.demo.common;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * 이 클래스는...
 *
 * @author yoots
 * Created on 2014. 7. 29.
 */

public class PageFilter extends OncePerRequestFilter {

	/* (non-Javadoc)
	 * @see org.springframework.web.filter.OncePerRequestFilter#doFilterInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		
		if(!StringUtils.equals("", contextPath)) {
			uri = StringUtils.substring(uri, contextPath.length());
		}
		
		//서블릿 context name
		request.setAttribute("root", contextPath);
		
		//domain uri name
		request.setAttribute("uri", uri);
		
		chain.doFilter(request, response);
	}
}