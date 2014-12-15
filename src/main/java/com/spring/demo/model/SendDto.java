/*
 * Copyright (c) 2014 아시아나IDT
 * 
 * 이 프로그램은 저작권 보호법에 의해 보호됩니다.
 * 이 프로그램의 일부나 전부를 무단으로 복제하거나 배포하는 경우에는
 * 저작권의 침해가 되므로 주의하시기 바랍니다.
 */
package com.spring.demo.model;

import java.io.Serializable;

import javapns.notification.transmission.PushQueue;

/**
 * 이 클래스는...
 *
 * @author user
 * Created on 2014. 8. 27.
 */

public class SendDto implements Serializable{

	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 5838943941677037715L;
	
	
	private String strFuncId;			//서비스 아이디
	private String strPlatId;				//푸시 대상 플랫폼 아이디
	private String strPushData;		//푸시 데이터
	
	private String alert;
	private String badge;
	private String sound;
	private String custom;
	private String applicationIds;
	
	
	public SendDto(EntityMap<String,Object> entityMap){
		
		strFuncId = entityMap.get("strFuncId").toString();
		strPlatId = entityMap.get("strPlatId").toString();
		strPushData = entityMap.get("strPushData").toString();
		
	}
	
	public SendDto(String funcId, String platId, String pushData){
		strFuncId = funcId;
		strPlatId = platId;
		strPushData = pushData;
	}

	public String getFuncId(){
		return strFuncId;
	}
	
	public String getPlatId(){
		return strPlatId;
	}
	
	public String getPushData(){
		return strPushData;
	}
	
	public void setPlatId(String platId){
		strPlatId = platId;
	}

	/**
	 *
	 * @return 
	 */
	public String getStrFuncId() {
		return strFuncId;
	}

	/**
	 *
	 * @param strFuncId
	 */
	public void setStrFuncId(String strFuncId) {
		this.strFuncId = strFuncId;
	}

	/**
	 *
	 * @return 
	 */
	public String getStrPlatId() {
		return strPlatId;
	}

	/**
	 *
	 * @param strPlatId
	 */
	public void setStrPlatId(String strPlatId) {
		this.strPlatId = strPlatId;
	}

	/**
	 *
	 * @return 
	 */
	public String getStrPushData() {
		return strPushData;
	}

	/**
	 *
	 * @param strPushData
	 */
	public void setStrPushData(String strPushData) {
		this.strPushData = strPushData;
	}

	/**
	 *
	 * @return 
	 */
	public String getAlert() {
		return alert;
	}

	/**
	 *
	 * @param alert
	 */
	public void setAlert(String alert) {
		this.alert = alert;
	}

	/**
	 *
	 * @return 
	 */
	public String getBadge() {
		return badge;
	}

	/**
	 *
	 * @param badge
	 */
	public void setBadge(String badge) {
		this.badge = badge;
	}

	/**
	 *
	 * @return 
	 */
	public String getSound() {
		return sound;
	}

	/**
	 *
	 * @param sound
	 */
	public void setSound(String sound) {
		this.sound = sound;
	}

	/**
	 *
	 * @return 
	 */
	public String getCustom() {
		return custom;
	}

	/**
	 *
	 * @param custom
	 */
	public void setCustom(String custom) {
		this.custom = custom;
	}

	/**
	 *
	 * @return 
	 */
	public String getApplicationIds() {
		return applicationIds;
	}

	/**
	 *
	 * @param applicationIds
	 */
	public void setApplicationIds(String applicationIds) {
		this.applicationIds = applicationIds;
	}

	
	
}
