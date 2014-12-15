/*
 * Copyright (c) 2012 아시아나IDT
 * 
 * 이 프로그램은 저작권 보호법에 의해 보호됩니다.
 * 이 프로그램의 일부나 전부를 무단으로 복제하거나 배포하는 경우에는
 * 저작권의 침해가 되므로 주의하시기 바랍니다.
 */
package com.spring.demo.common;

import java.util.ArrayList;
import java.util.List;

/**
 * 이 클래스는...
 *
 * @author user
 * Created on 2012. 12. 28.
 */

public class Const {

	//로그인 체크 제외 페이지
	public static List<String> excludeUri= new ArrayList<String>();
	
	static{
		
		//세션 처리 제외 페이지
		excludeUri.add("/cop/login/sso.do");
		excludeUri.add("/cop/common/upload.json");
		excludeUri.add("/cop/common/imageUpload.json");
		excludeUri.add("/cop//common/mobileupload.json");
		excludeUri.add("/cop//common/multiupload.json");
		excludeUri.add("/cop/admin/login.do");
		excludeUri.add("/cop/admin/loginOk.do");
		
	}
	
	public static List<String> excludeKey = new ArrayList<String>();
	static{
		//세션 처리 제외 페이지
		excludeKey.add("mailBody");
		excludeKey.add("editor");
		excludeKey.add("content");
	}

}
