/*
 * Copyright (c) 2014 아시아나IDT
 * 
 * 이 프로그램은 저작권 보호법에 의해 보호됩니다.
 * 이 프로그램의 일부나 전부를 무단으로 복제하거나 배포하는 경우에는
 * 저작권의 침해가 되므로 주의하시기 바랍니다.
 */
package com.spring.demo.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 리스트형 페이징 클래스
 *
 * @author yoots
 * Created on 2014. 7. 31.
 */
public class Paging {

	private int pageSize 	= 10;		// 한페이지당 게시물수
	private int blockSize 	= 10;      	// 블럭당 페이지수
	private int totalCount = 0;		// 전체 레코드수
	private int totalPage 	= 0;		// 전체 페이지수
	
	private int pageNo		= 1 ;		// 현재 페이지
	private int stnum		= 0 ;		// 시작 로우
	private int ednum		= 0 ;		// 끝 로우
	private int nextNo 	= 0;
	
	private int minSeq		= 0 ;		// 시작 로우
	private int maxSeq		= 0 ;		// 끝 로우
	
	private Map<String,Object> pageMap = new HashMap<String, Object>();
	
	private String script = "javascript:goPage";
	
	private String extVal = "0";
	
	/**
	 * 
	 * @param npage
	 */
	public Paging(int pageNo){
		this.pageNo = pageNo;
		setRowNum();
	}
	
	/**
	 * 
	 * @param npage
	 * @param pageSize
	 * @param blockSize
	 */
	public Paging(int pageNo, int pageSize, int blockSize){
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.blockSize = blockSize;
		setRowNum();
	}
	
	
	/**
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param blockSize
	 */
	public Paging(int pageNo, int pageSize, int blockSize , Map<String,Object> map){
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.blockSize = blockSize;
		this.pageMap.putAll(map);
		setRowNum();
	}
	
	/**
	 * 
	 * <pre>
	 * setMap
	 * @param map
	 * </pre>
	 */
	public void setPageMap(Map<String,Object> map){
		this.pageMap.putAll(map);
	}
	
	/**
	 * 
	 * <pre>
	 * getMap
	 * @return
	 * </pre>
	 */
	public Map getPageMap(){
		return pageMap;
	}
	
	/**
	 * 쿼리 이후의 result 데이터
	 * @return
	 */
	public List<Map<String,Object>> getList(){
		return (List<Map<String,Object>>)pageMap.get("metaList");
	}
	
	/**
	 * @param key
	 * @param value
	 * @return
	 * @see java.util.Map#put(java.lang.Object, java.lang.Object)
	 */
	public Object put(String key, Object value) {
		return pageMap.put(key, value);
	}
	
	
	/**
	 * start rownum 과 end rownum 구한다~
	 *
	 */
	private void setRowNum(){
		this.stnum = ((this.pageNo-1) * this.pageSize) + 1; 	// rownum 시작 번호
		this.ednum = this.pageSize * this.pageNo; 		// rownum 끝 번호
		
		pageMap.put("pageNo", pageNo);
		pageMap.put("pageSize", pageSize);
		
		pageMap.put("startNum", stnum);
		pageMap.put("endNum", ednum);
	}
	
	/**
	 *
	 * @return 
	 */
	public int getTotalCount() {
		return totalCount;
	}
	
	/**
	 *
	 * @param totalCount
	 */
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		
		this.nextNo = (totalCount - (pageNo - 1) * pageSize) + 1;
		
		this.setTotalPage();
	}

	public int getStartNum(){
		return this.stnum;
	}
	
	public int getEndNum(){
		return this.ednum;
	}
	
	public int getTotalPage(){
		return this.totalPage;
	}
	
	/**
	 * 전체 페이지수를 구한다~
	 *
	 */
	private void setTotalPage(){
		if(this.totalCount % this.pageSize == 0){
			this.totalPage = this.totalCount / this.pageSize;
		} else{
			this.totalPage = (this.totalCount / this.pageSize) + 1;
		}		
		if(this.totalPage == 0){
			this.totalPage = 1;
		}
	}
	
	
	/**
	 *
	 * @return 
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 *
	 * @param pageSize
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 *
	 * @return 
	 */
	public int getBlockSize() {
		return blockSize;
	}

	/**
	 *
	 * @param blockSize
	 */
	public void setBlockSize(int blockSize) {
		this.blockSize = blockSize;
	}


	/**
	 * @return the pageNo
	 */
	public int getPageNo() {
		return pageNo;
	}

	/**
	 * @param pageNo the pageNo to set
	 */
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	/**
	 *
	 * @param totalPage
	 */
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	/**
	 *
	 * @return 
	 */
	public int getNextNo() {
		return --nextNo;
	}

	/**
	 *
	 * @param nextNum
	 */
	public void setNextNo(int nextNo) {
		this.nextNo = nextNo;
	}
	
	
	/**
	 * @return the minSeq
	 */
	public int getMinSeq() {
		return minSeq;
	}

	/**
	 * @param minSeq the minSeq to set
	 */
	public void setMinSeq(int minSeq) {
		this.minSeq = minSeq;
	}

	/**
	 * @return the maxSeq
	 */
	public int getMaxSeq() {
		return maxSeq;
	}

	/**
	 * @param maxSeq the maxSeq to set
	 */
	public void setMaxSeq(int maxSeq) {
		this.maxSeq = maxSeq;
	}

	
	
	/**
	 * @return the extVal
	 */
	public String getExtVal() {
		return extVal;
	}

	/**
	 * @param extVal the extVal to set
	 */
	public void setExtVal(String extVal) {
		this.extVal = extVal;
	}

	/**
	 * 
	 * <pre>
	 * 사용할 자바 스크립트 추가..
	 * @param str
	 * </pre>
	 */
	public void setScript(String str){
		this.script = "javascript:"+str;
	}
	
	/**
	 * 페이징
	 * @param totalCount 전체카운트
	 * @param isDisable 이미지활성화 true-비활성화 false-활성화
	 * @return String
	 */
	public String getPaging(){
		
		//전체카운터가 0일 경우 빈값
		if(this.totalCount == 0) return "";
		
		StringBuilder navi 	= new StringBuilder();
		
		int blockCnt = 1; //현재 블럭 번호
		blockCnt = (pageNo - 1) / blockSize + 1;
		int startPage 	= ((blockCnt-1)*blockSize)+1; 	//블럭시작페이지
		//int nextPage 	= (blockCnt*blockSize)+1; 		//다음블럭시작페이지
		//int prePage 	= ((blockCnt-2)*blockSize)+1; 	//이전블럭시작페이지
		int nextPage 	= pageNo+1; 		//다음시작페이지
		int prePage 	= pageNo-1; 		//이전시작페이지
		
		
		navi.append("<div class=\"paging\">");
		
		//이전페이지
		if(totalPage > blockSize) {
			navi.append("<div class=\"prevBtnGroup\">");
			if(prePage > 0){
				navi.append("<a href=\""+script+"(1);\" title=\"첫 페이지\" class=\"prevEnd\"><span>prevEnd</span></a>");
				navi.append("<a href=\""+script+"("+prePage+");\" title=\"이전 페이지\" class=\"prev\"><span>prev</span></a>");
			} else{
				navi.append("<a href=\"#\" title=\"첫 페이지\" class=\"prevEnd\"><span>prevEnd</span></a>");
				navi.append("<a href=\"#\" title=\"이전 페이지\" class=\"prev\"><span>prev</span></a>");
			}
			navi.append("</div>");
		}
		
		navi.append("<ul>");
				
		//페이지
		for(int i = startPage; i < (startPage + blockSize); i++){
			if(i > totalPage) break;
			
			if(i == pageNo){
				navi.append("<li class=\"pageOn\"><span>" + i + "</span></li>");
			} else{
				navi.append("<li><a href=\""+script+"("+i+");\" title=\"1\"><span>" + i + "</span></a></li>");
			}
		}
		
		navi.append("</ul>");
				
		
		//다음페이지
		if(totalPage > blockSize) {
			navi.append("<div class=\"nextBtnGroup\">");
			
			if(nextPage <= totalPage){
				navi.append("<a href=\""+script+"("+nextPage+");\" title=\"다음 페이지\" class=\"next\"><span>next</span></a>");
				navi.append("<a href=\""+script+"("+totalPage+");\" title=\"끝 페이지\" class=\"nextEnd\"><span>nextEnd</span></a>");
			} else{
				navi.append("<a href=\"#\" title=\"다음 페이지\" class=\"next\"><span>next</span></a>");
				navi.append("<a href=\"#\" title=\"끝 페이지\" class=\"nextEnd\"><span>nextEnd</span></a>");
			}
			navi.append("</div>");
		}
		
		navi.append("</div>\n");
		
		return navi.toString();
	}
	
	/**
	 * 페이징
	 * @param totalCount 전체카운트
	 * @param isDisable 이미지활성화 true-비활성화 false-활성화
	 * @return String
	 */
	public String getExtPaging(){
		
		//전체카운터가 0일 경우 빈값
		if(this.totalCount == 0) return "";
		
		StringBuilder navi 	= new StringBuilder();
		
		int blockCnt = 1; //현재 블럭 번호
		blockCnt = (pageNo - 1) / blockSize + 1;
		int startPage 	= ((blockCnt-1)*blockSize)+1; 	//블럭시작페이지
		//int nextPage 	= (blockCnt*blockSize)+1; 		//다음블럭시작페이지
		//int prePage 	= ((blockCnt-2)*blockSize)+1; 	//이전블럭시작페이지
		int nextPage 	= pageNo+1; 		//다음시작페이지
		int prePage 	= pageNo-1; 		//이전시작페이지
		
		
		navi.append("<div class=\"paging\">");
		
		//이전페이지
		if(totalPage > blockSize) {
			navi.append("<div class=\"prevBtnGroup\">");
			if(prePage > 0){
				navi.append("<a href=\""+script+"("+extVal+",1);\" title=\"첫 페이지\" class=\"prevEnd\"><span>prevEnd</span></a>");
				navi.append("<a href=\""+script+"("+extVal+","+prePage+");\" title=\"이전 페이지\" class=\"prev\"><span>prev</span></a>");
			} else{
				navi.append("<a href=\"#\" title=\"첫 페이지\" class=\"prevEnd\"><span>prevEnd</span></a>");
				navi.append("<a href=\"#\" title=\"이전 페이지\" class=\"prev\"><span>prev</span></a>");
			}
			navi.append("</div>");
		}
		
		navi.append("<ul>");
				
		//페이지
		for(int i = startPage; i < (startPage + blockSize); i++){
			if(i > totalPage) break;
			
			if(i == pageNo){
				navi.append("<li class=\"pageOn\"><span>" + i + "</span></li>");
			} else{
				navi.append("<li><a href=\""+script+"("+extVal+","+i+");\" title=\"1\"><span>" + i + "</span></a></li>");
			}
		}
		
		navi.append("</ul>");
				
		
		//다음페이지
		if(totalPage > blockSize) {
			navi.append("<div class=\"nextBtnGroup\">");
			
			if(nextPage <= totalPage){
				navi.append("<a href=\""+script+"("+extVal+","+nextPage+");\" title=\"다음 페이지\" class=\"next\"><span>next</span></a>");
				navi.append("<a href=\""+script+"("+extVal+","+totalPage+");\" title=\"끝 페이지\" class=\"nextEnd\"><span>nextEnd</span></a>");
			} else{
				navi.append("<a href=\"#\" title=\"다음 페이지\" class=\"next\"><span>next</span></a>");
				navi.append("<a href=\"#\" title=\"끝 페이지\" class=\"nextEnd\"><span>nextEnd</span></a>");
			}
			navi.append("</div>");
		}
		
		navi.append("</div>\n");
		
		return navi.toString();
	}
	
}