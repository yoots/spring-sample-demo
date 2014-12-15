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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.demo.common.BaseController;
import com.spring.demo.common.Paging;
import com.spring.demo.model.EntityMap;
import com.spring.demo.model.ResultMap;
import com.spring.demo.service.SampleService;

/**
 * 이 클래스는...
 *
 * @author user
 * Created on 2014. 8. 26.
 */
@Controller
@RequestMapping(value="/board/**")
public class BoardController extends BaseController {

	@Autowired
	SampleService sampleService;
	
	@RequestMapping(value="List")
	public ModelMap list(ModelMap modelMap , EntityMap<String,Object> entityMap ) {
		
		List<ResultMap> list = sampleService.getList(entityMap);
		
		Paging paging = new Paging(1, 10, 10, entityMap);
		
		modelMap.put("list", list);
		
		modelMap.put("paging", sampleService.getPagingList(paging));
		
		return modelMap;
	}
	
	@RequestMapping(value="List2")
	public String list2( EntityMap<String,Object> entityMap ) {
		
		ModelMap modelMap = new ModelMap();
		
		modelMap.put("list", sampleService.getList(entityMap));
		
		return "board/List";
	}
	
	@RequestMapping(value="List3.json")
	@ResponseBody
	public Map<String,Object> list3( EntityMap<String,Object> entityMap ) {
		
		Map<String,Object> jsonMap = new HashMap<String,Object>();
		
		jsonMap.put("status", "1");
		jsonMap.put("msg", "success");
		jsonMap.put("list", sampleService.getList(entityMap));
		
		return jsonMap;
	}
	
}
