/*
 * Copyright (c) 2014 아시아나IDT
 * 
 * 이 프로그램은 저작권 보호법에 의해 보호됩니다.
 * 이 프로그램의 일부나 전부를 무단으로 복제하거나 배포하는 경우에는
 * 저작권의 침해가 되므로 주의하시기 바랍니다.
 */
package com.spring.demo.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.spring.demo.common.Paging;
import com.spring.demo.dao.SampleDao;

/**
 * 이 클래스는...
 *
 * @author user
 * Created on 2014. 8. 27.
 */
@Repository
public class SampleDaoImpl implements SampleDao {

	@Resource(name = "sqlSessionTemplate")
	private SqlSession sqlSession;
    
	@Override
	public List<Map<String, Object>> getList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("sample_list", map);
	}

	@Override
	public Paging getPagingList(Paging paging) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = sqlSession.selectList("sample_paging_list", paging);
		
		return paging;
	}

	
}
