/*
 * Copyright (c) 2013 아시아나IDT
 * 
 * 이 프로그램은 저작권 보호법에 의해 보호됩니다.
 * 이 프로그램의 일부나 전부를 무단으로 복제하거나 배포하는 경우에는
 * 저작권의 침해가 되므로 주의하시기 바랍니다.
 */
package com.spring.demo.common;

import java.util.ResourceBundle;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

/**
 * 프로퍼티 값 추출....
 *
 * @author user
 * Created on 2013. 1. 8.
 */

public class Config {

	/*
	 * 프로퍼티
	 */
	public static ResourceBundle rb;
	
	static{
		rb = ResourceBundle.getBundle("property.config");
	}
	
	/**
	 * 속성값을 가져 온다.
	 * @param key
	 * @return String
	 */
    public static String getProperty(String key){
    	
    	return StringUtils.defaultString(rb.getString(key),"");
    }
    
    /**
	 * 속성값을 가져 온다.
	 * @param key
	 * @return int
	 */
    public static int getInt(String key){
    	
    	return NumberUtils.toInt(rb.getString(key) , 0);
    }
    
    public static String STR_CONFIG_PATH						=	"./conf/";
	public static String STR_CONFIG_FILENAME					=	"push.properties";
	
	public static String STR_PUSH_PLAT_ID_GCM					=	"0";
	public static String STR_PUSH_PLAT_ID_APNS					=	"1";
	
	public static String STR_PUSH_INTERNAL_FUNCTION_COUNT		=	"push.internal.function.count";	
	public static String STR_PUSH_INTERNAL_FUNCTION_ID			=	"push.internal.function.id.";
	
	public static String STR_PUSH_LOGGER_RAW_DATA_BACKUP		=	"push.logger.raw.data.backup";
			
	public static String STR_PUSH_MONITOR_RAW_DATA_FILEPATH		=	"push.monitor.raw.data.filepath";
	public static String STR_PUSH_MONITOR_RAW_DATA_FILENAME		=	"push.monitor.raw.data.filename";
	public static String STR_PUSH_MONITOR_RAW_DATA_HOST			=	"push.monitor.raw.data.host";
	public static String STR_PUSH_MONITOR_RAW_DATA_PORT			=	"push.monitor.raw.data.port";
	public static String STR_PUSH_MONITOR_RAW_DATA_PERIOD		=	"push.monitor.raw.data.period";
	
	public static String STR_PUSH_SEND_GCM_ONCE_MAX_COUNT		=	"push.send.gcm.once.max.count";
	
	public static String STR_PUSH_SEND_APNS_MIN_COUNT			=	"push.send.apns.thread.min.count";
	public static String STR_PUSH_SEND_APNS_MAX_COUNT			=	"push.send.apns.thread.max.count";
	public static String STR_PUSH_SEND_GCM_MIN_COUNT			=	"push.send.gcm.thread.min.count";
	public static String STR_PUSH_SEND_GCM_MAX_COUNT			=	"push.send.gcm.thread.max.count";
	public static String STR_PUSH_APNS_FEEDBACK_MIN_COUNT		=	"push.apns.feedback.thread.min.count";
	public static String STR_PUSH_APNS_FEEDBACK_MAX_COUNT		=	"push.apns.feedback.thread.max.count";
	
	public static String STR_IOS_CERT_FILEPATH					=	".ios.cert.filepath";
	public static String STR_IOS_CERT_FILENAME_REAL				=	".ios.cert.filename.real";
	public static String STR_IOS_CERT_FILENAME_TEST				=	".ios.cert.filename.test";
	public static String STR_IOS_CERT_PASSWORD					=	".ios.cert.password";
	public static String STR_IOS_FEEDBACK_QUERY_PERIOD			=	".ios.feedback.query.period";
	public static String STR_IOS_THREAD_COUNT					=	".ios.thread.count";
	
	public static String STR_ANDROID_API_KEY					=	".android.api.key";
	
	public static String STR_FORWARD_URL_CONNECTION_TIMEOUT		=	".forward.url.connection.timeout";
	public static String STR_FORWARD_URL_INVALID_APP_ID			=	".forward.url.invalid.app.id";
	public static String STR_FORWARD_URL_UPDATE_APP_ID			=	".forward.url.update.app.id";
						
	public static final int		INT_RETRY_SENDING_MSG			=	5;
	
	//푸시 데이터 규격(Raw Data)
	//전체 3부분(년월일, 시간, 데이터)
	public static final int STR_IDX_DATA_DATE					=	0;
	public static final int STR_IDX_DATA_TIME					=	1;
	public static final int STR_IDX_DATA_BODY					=	2;
	public static final int STR_IDX_DATA_TOTAL_LEN				=	3;
	
	//데이터 3부분(서비스 아이디, 플랫폼 아이디, 부가 데이터)
	public static final int STR_IDX_BODY_SVC_ID					=	0;
	public static final int STR_IDX_BODY_PLAT_ID				=	1;
	public static final int STR_IDX_BODY_SUB_CONTENTS			=	2;
	public static final int STR_IDX_BODY_TOTAL_LEN				=	3;
	
	//부가데이터 6부분(유져, 어플아이디, 얼러트, 빼지, 사운드, 커스텀)
	public static final int RAW_DATA_USER_IDS			= 0;
	public static final int RAW_DATA_APPLICATION_IDS	= 1;
	public static final int RAW_DATA_ALERT				= 2;
	public static final int RAW_DATA_BADGE				= 3;
	public static final int RAW_DATA_SOUND				= 4;
	public static final int RAW_DATA_CUSTOM				= 5;
	public static final int RAW_DATA_TOTAL_LEN			= 6;
	
	public static final int ITEM_CUSTOM_KEY				= 0;
	public static final int ITEM_CUSTOM_VAL				= 1;
	public static final int ITEM_CUSTOM_TOTAL_LEN		= 2;
	
	public static final String GCM_KEY_ALERT			= "alert";
	public static final String GCM_KEY_BADGE			= "badge";
	public static final String GCM_KEY_SOUND			= "sound";
}
