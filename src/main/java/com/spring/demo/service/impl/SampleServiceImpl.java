/*
 * Copyright (c) 2014 아시아나IDT
 * 
 * 이 프로그램은 저작권 보호법에 의해 보호됩니다.
 * 이 프로그램의 일부나 전부를 무단으로 복제하거나 배포하는 경우에는
 * 저작권의 침해가 되므로 주의하시기 바랍니다.
 */
package com.spring.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.demo.common.Paging;
import com.spring.demo.dao.SampleDao;
import com.spring.demo.model.EntityMap;
import com.spring.demo.model.ResultMap;
import com.spring.demo.service.SampleService;

/**
 * 이 클래스는...
 *
 * @author user
 * Created on 2014. 8. 22.
 */
@Service
public class SampleServiceImpl implements SampleService {

	@Autowired
	SampleDao sampleDao;

	/* (non-Javadoc)
	 * @see com.asiana.creworld.service.SampleService#getList(com.asiana.creworld.model.EntityMap)
	 */
	@Override
	public List<ResultMap> getList(EntityMap entityMap) {
		// TODO Auto-generated method stub
		
		return sampleDao.getList(entityMap);
	}

	/* (non-Javadoc)
	 * @see com.asiana.creworld.service.SampleService#getPagingList(com.asiana.creworld.common.Paging)
	 */
	@Override
	public Paging getPagingList(Paging paging) {
		// TODO Auto-generated method stub
		return sampleDao.getPagingList(paging);
	}
	
	
}
