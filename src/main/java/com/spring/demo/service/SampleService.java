/*
 * Copyright (c) 2014 아시아나IDT
 * 
 * 이 프로그램은 저작권 보호법에 의해 보호됩니다.
 * 이 프로그램의 일부나 전부를 무단으로 복제하거나 배포하는 경우에는
 * 저작권의 침해가 되므로 주의하시기 바랍니다.
 */
package com.spring.demo.service;

import java.util.List;
import java.util.Map;

import com.spring.demo.common.Paging;

/**
 * 이 클래스는...
 *
 * @author user
 * Created on 2014. 8. 22.
 */
public interface SampleService {
	
	public List<Map<String,Object>> getList(Map<String,Object> map);
	
	public Paging getPagingList(Paging paging);
}
