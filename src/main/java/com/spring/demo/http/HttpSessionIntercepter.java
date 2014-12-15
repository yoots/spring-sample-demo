/*
 * Copyright (c) 2013 아시아나IDT
 * 
 * 이 프로그램은 저작권 보호법에 의해 보호됩니다.
 * 이 프로그램의 일부나 전부를 무단으로 복제하거나 배포하는 경우에는
 * 저작권의 침해가 되므로 주의하시기 바랍니다.
 */
package com.spring.demo.http;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


/**
 * 이 클래스는...
 * 
 * @author user Created on 2013. 1. 11.
 */

public class HttpSessionIntercepter extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

		
		String rootPath = request.getContextPath();
		String currentURI = request.getRequestURI();
		
		request.setAttribute("root",rootPath);
		
//		if(request.getSession().getAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME) == null){
//			//브라우저 언어 설정...
//			Locale locale = new Locale(request.getLocale().getLanguage());
//			request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, locale);
//		}
		
		return true;
		//파일 업로드에서 파폭에서는 header 값의 문제로 패스 시킨다.
//		if(Const.excludeUri.contains(currentURI)){
//			return true;
//		}
//		else{
//			try {
//				if(currentURI.indexOf("/admin/")>-1){		//어드민 체크....
//					if(request.getSession().getAttribute("adminUser") != null){
//						return true;
//					}
//					else{
//						RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/login.do");
//						dispatcher.forward(request, response);
//						return true;
//					}
//				}
//				else{
//		
//					User user = (User)request.getSession().getAttribute("copTelepiaUser");
//					if (user != null && user.getUserAccount() != null) {  // 유저 세션 체크
//						
//						String header = request.getHeader("cookie");
//						
//						if(user.isCopMenuAuth()){		//메뉴 인증이 된 사람만 통과...
//							if(header.indexOf("Telepia") > -1){
//								if(header.indexOf(user.getUserAccount()) > -1 || Const.excludeUri.contains(currentURI)){		//세션값과 쿠키 값이 동일하면 패스
//									return true;
//								}
//								else {												// 다르면 다시 로그인 처리....
//									RequestDispatcher dispatcher = request.getRequestDispatcher("/login/sso.do");
//									dispatcher.forward(request, response);
//									return true;
//								}
//							}
//							else
//								return true;
//						}
//						else{		//메뉴 인증이 안되면 로그인 페이지..
//							RequestDispatcher dispatcher = request.getRequestDispatcher("/login/sso.do");
//							dispatcher.forward(request, response);
//							return true;
//						}
//					}
//					else {
//						request.setAttribute("referer", StringUtils.remove(currentURI,rootPath));
//						RequestDispatcher dispatcher = request.getRequestDispatcher("/login/sso.do");
//						dispatcher.forward(request, response);
//						return true;
//					}
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//				return false;
//			}
//		}
	}
	
}
