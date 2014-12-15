/*
 * Copyright (c) 2013 아시아나IDT
 * 
 * 이 프로그램은 저작권 보호법에 의해 보호됩니다.
 * 이 프로그램의 일부나 전부를 무단으로 복제하거나 배포하는 경우에는
 * 저작권의 침해가 되므로 주의하시기 바랍니다.
 * 
 * ProfileImage.java
 */
package com.spring.demo.common;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 이 클래스는... 
 * @author yoots
 *
 * Created on 2013. 8. 28.
 */
@Component
public class CreImage {

	/*
	 * spring DI를 이용....
	 */
	@Autowired(required=true)
	private HttpServletRequest request;
	
	/**
	 * 
	 * <pre>
	 * 프로필 이미지...
	 * @param fileId
	 * @param userAccount
	 * @return
	 * </pre>
	 */
	public String src(String userAccount){
		
		if(StringUtils.trimToEmpty(userAccount).length() <= 0){
			return (request.getContextPath())+"/Images/Common/user_nopicture.gif";
		}
		else{
			return "http://ttm.kumhoasiana.com/_MobileOffice_/Organize/UserPictureImage.aspx?UserAccount=" + userAccount;
		}
	}
	
	
}
