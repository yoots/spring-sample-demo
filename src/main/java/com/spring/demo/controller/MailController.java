/*
 * Copyright (c) 2014 아시아나IDT
 * 
 * 이 프로그램은 저작권 보호법에 의해 보호됩니다.
 * 이 프로그램의 일부나 전부를 무단으로 복제하거나 배포하는 경우에는
 * 저작권의 침해가 되므로 주의하시기 바랍니다.
 */
package com.spring.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.demo.common.BaseController;
import com.spring.demo.common.EWSExecutor;
import com.spring.demo.model.EntityMap;
import com.spring.demo.model.User;

/**
 * 이 클래스는...
 *
 * @author user
 * Created on 2014. 8. 26.
 */
@Controller
@RequestMapping (value="/mail")
public class MailController extends BaseController {

	
	@RequestMapping(value="Send")
	public void send(HttpServletResponse response , EntityMap<String,Object> entityMap ) {
		
		EWSExecutor executor = EWSExecutor.getInstance();
		
		//executor.execute(new User(), new ArrayList() , "메일 제목", "메일 내용", null);
		
		executor.shutdown();
		
		//json 데이터
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		
		jsonWriter(response, jsonMap);
	}
}
