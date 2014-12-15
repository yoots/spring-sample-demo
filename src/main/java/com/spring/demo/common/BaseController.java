/*
 * Copyright (c) 2014 아시아나IDT
 * 
 * 이 프로그램은 저작권 보호법에 의해 보호됩니다.
 * 이 프로그램의 일부나 전부를 무단으로 복제하거나 배포하는 경우에는
 * 저작권의 침해가 되므로 주의하시기 바랍니다.
 */
package com.spring.demo.common;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * 이 클래스는...
 *
 * @author yoots
 * Created on 2014. 8. 4.
 */
@Controller
public class BaseController {

	/*
	 * spring DI를 이용....
	 */
	@Autowired(required=true)
	protected HttpServletRequest request;
	
	
	/**
	 * 현재 Http client 에서 쿠키를 굽는다.
	 * @param key
	 * @param value
	 */
	public void setCookies(HttpServletResponse response , String key , String value){
		Cookie cookie = new Cookie(key, value);
		cookie.setPath("/");
		response.addCookie(cookie);
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void deleteCookie(HttpServletResponse response){
        
        Cookie[] cookies= request.getCookies();
        if(cookies != null && cookies.length > 0) {
            for(int i = 0; i < cookies.length; i++) {
                //쿠키 삭제
                cookies[i].setMaxAge(0);               //쿠키 유지기간을 0으로함
                response.addCookie(cookies[i]);      //쿠키저장
            }
        }
    }
	
	/**
	 * 현재 Http client 에서 쿠키값 체크..
	 * @param key
	 * @return
	 */
	public String getCookie(String key){
		
		String value = "";
		Cookie[] cookies = request.getCookies();

		if(cookies == null) return "";
		
        for(int i=0 ; i<cookies.length ; i++) {
            Cookie cookie = cookies[i];
            if(key.equals(cookies[i].getName())) {
                value = cookie.getValue();
                break;
            }
        }
		return value;
	}
	
	/**
	 * json 데이터 출력...
	 * @param str
	 */
	public void resWriter(HttpServletResponse response, String str){
		
		try{
			response.setContentType("text/html; charset=UTF-8");
			response.resetBuffer();
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print(str);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * json 데이터 출력...
	 * @param jsonMap
	 */
	public void jsonWriter(HttpServletResponse response, Map jsonMap){
		
		try{
			//json 형식으로 생성.
			JSONObject jsonObject = JSONObject.fromObject(jsonMap );
			response.setContentType("text/html; charset=UTF-8");
			response.resetBuffer();
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print(jsonObject.toString());
			
			jsonMap.clear();
			
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
}
