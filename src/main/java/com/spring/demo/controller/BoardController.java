/*
 * Copyright (c) 2014 아시아나IDT
 * 
 * 이 프로그램은 저작권 보호법에 의해 보호됩니다.
 * 이 프로그램의 일부나 전부를 무단으로 복제하거나 배포하는 경우에는
 * 저작권의 침해가 되므로 주의하시기 바랍니다.
 */
package com.spring.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.spring.demo.common.BaseController;
import com.spring.demo.common.Paging;
import com.spring.demo.service.SampleService;

/**
 * 이 클래스는...
 *
 * @author user
 * Created on 2014. 8. 26.
 */
@Controller
@RequestMapping(value="/board/")
public class BoardController extends BaseController {

	@Autowired
	SampleService sampleService;
	
	@RequestMapping(value="List")
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response, Map<String, Object> params ) {
		
		List<Map<String, Object>> list = sampleService.getList(params);
		
		Paging paging = new Paging(1, 10, 10, params);
		ModelAndView mav = new ModelAndView();
		return mav;
		
	}
	
	@RequestMapping(value="List2")
	public String list2(@RequestBody Map<String, Object> params ) {
		
		ModelMap modelMap = new ModelMap();
		
		modelMap.put("list", sampleService.getList(params));
		
		return "board/List";
	}
	
	@RequestMapping(value="List3.json")
	public @ResponseBody Map<String,Object> list3( @RequestBody Map<String, Object> params ) {
		
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		
		jsonMap.put("status", "1");
		jsonMap.put("msg", "success");
		jsonMap.put("list", sampleService.getList(params));
		
		return jsonMap;
	}
	
}
