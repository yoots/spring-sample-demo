/*
 * Copyright (c) 2014 아시아나IDT
 * 
 * 이 프로그램은 저작권 보호법에 의해 보호됩니다.
 * 이 프로그램의 일부나 전부를 무단으로 복제하거나 배포하는 경우에는
 * 저작권의 침해가 되므로 주의하시기 바랍니다.
 */
package com.spring.demo.push;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.concurrent.Callable;

import javapns.notification.PushNotificationPayload;
import javapns.notification.transmission.PushQueue;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;

import com.google.android.gcm.server.Constants;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
import com.spring.demo.common.AsyncHandler;
import com.spring.demo.common.Config;
import com.spring.demo.model.SendDto;

/**
 * 이 클래스는...
 *
 * @author user
 * Created on 2014. 8. 28.
 */

public class PushSender  implements Callable<Boolean> {

	//log4j 로거
	private Logger logger = Logger.getLogger(getClass().getName());
	
	private static final int REGISTRATION_ID_DELETE = 0;
	private static final int REGISTRATION_ID_UPDATE = 1;
	
	private SendDto sendDto;
	
	public PushSender(SendDto sendDto){
		this.sendDto = sendDto;
	}
    
    /**
     * 작업을 수행함
     */
    public Boolean call() throws Exception {
    	try{
			if(sendDto.getPlatId().equals(Config.STR_PUSH_PLAT_ID_APNS)){
				processAPNS();
			}else if(sendDto.getPlatId().equals(Config.STR_PUSH_PLAT_ID_GCM)){
				processGCM();
			}
			 return true;
		}catch(Exception e){
			e.printStackTrace();
			 return false;
		}
    }
    
    /**
     * iOS 발송
     * @throws Exception
     */
    public void processAPNS() throws Exception{
		
		//푸시 데이터가 없을경우...
		if(sendDto == null){
			return;
		}
		
		//푸시 필수 항목 
		if(StringUtils.isBlank(sendDto.getAlert()) && StringUtils.isNotBlank(sendDto.getBadge()) && StringUtils.isNotBlank(sendDto.getSound()) ){
			return;
		}
		
		//보낼 데이터 먼저 파싱 처리
		//커스텀 영역만 파싱처리하면 됨
		//PushNotificationPayload payload = new PushNotificationPayload();
		PushNotificationPayload payload = PushNotificationPayload.complex();
		
		if(StringUtils.isNotBlank(sendDto.getAlert())){
			payload.addAlert(sendDto.getAlert());
			
		}
		if(StringUtils.isNotBlank(sendDto.getBadge())){
			payload.addBadge(NumberUtils.toInt(sendDto.getBadge()));
		}
		
		if(StringUtils.isNotBlank(sendDto.getSound())){
			payload.addSound(sendDto.getSound());
		}
		
		StringTokenizer stCustom = new StringTokenizer(sendDto.getCustom(), "`");
		
		while(stCustom.hasMoreElements()){
			String strCur = stCustom.nextToken();
			String[] arrCstm = strCur.split("\\^", Config.ITEM_CUSTOM_TOTAL_LEN);
			if(arrCstm.length < Config.ITEM_CUSTOM_TOTAL_LEN){
				continue;
			}
			if(!arrCstm[Config.ITEM_CUSTOM_KEY].equals("")){
				payload.addCustomDictionary(arrCstm[Config.ITEM_CUSTOM_KEY], arrCstm[Config.ITEM_CUSTOM_VAL]);	
			}		
		}
		
		//사용자 아이디 집합 : 실제 푸시 전송시는 사용하지 않음(문제 발생시 확인용)
		//어플리케이션 아이디 집합 파싱
		
		//큐에 전송 위임
		PushQueue queue = RunnerExecutor.getInstance().getQueue();
		
		StringTokenizer stAppIds = new StringTokenizer(sendDto.getApplicationIds(), "`");
		while(stAppIds.hasMoreElements()){
			String strAppId = stAppIds.nextToken();
			
			
			if(!strAppId.equals("")){
				try{
					queue.add(payload, strAppId);	
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		
	}
	
    /**
     * Android 발송
     * @throws Exception
     */
	public void processGCM() throws Exception{
		
		int nSendOnceMaxCnt = NumberUtils.toInt(Config.getProperty(Config.STR_PUSH_SEND_GCM_ONCE_MAX_COUNT));
		
		//푸시 데이터가 없을경우...
		if(sendDto == null){
			return;
		}
		
		//푸시 필수 항목 
		if(StringUtils.isBlank(sendDto.getAlert()) && StringUtils.isNotBlank(sendDto.getBadge()) && StringUtils.isNotBlank(sendDto.getSound()) ){
			return;
		}
		
		//보낼 데이터 먼저 파싱 처리
		//커스텀 영역만 파싱처리하면 됨
		//루프는 가급적 한번만 돌리기 위하여 파싱시 값 세팅이 같이 이루어지게 구성
		Message.Builder mb = new Message.Builder();
		if(StringUtils.isNotBlank(sendDto.getAlert())){
			mb.addData(Config.GCM_KEY_ALERT, sendDto.getAlert());
		}
		if(StringUtils.isNotBlank(sendDto.getBadge())){
			mb.addData(Config.GCM_KEY_BADGE, sendDto.getBadge());
		}
		if(StringUtils.isNotBlank(sendDto.getSound())){
			mb.addData(Config.GCM_KEY_SOUND, sendDto.getSound());
		}
		
		StringTokenizer stCustom = new StringTokenizer(sendDto.getCustom(), "`");
		
		while(stCustom.hasMoreElements()){
			String strCur = stCustom.nextToken();
			String[] arrCstm = strCur.split("\\^", Config.ITEM_CUSTOM_TOTAL_LEN);
			if(arrCstm.length < Config.ITEM_CUSTOM_TOTAL_LEN){
				continue;
			}
			if(!arrCstm[Config.ITEM_CUSTOM_KEY].equals("")){	
				mb.addData(URLEncoder.encode(arrCstm[Config.ITEM_CUSTOM_KEY], "UTF-8"), URLEncoder.encode(arrCstm[Config.ITEM_CUSTOM_VAL], "UTF-8"));
			}		
		}
		
		//사용자 아이디 집합 : 실제 푸시 전송시는 사용하지 않음(문제 발생시 확인용)
		//어플리케이션 아이디 집합 파싱
		//GCM은 동일 메세지에 대해 레지스트레이션 아이디를 복수개로 전송 처리 할 수 있음 : 1000개까지 지원
		int nTokenIdx = 0;
		List<String> lRegIds = new ArrayList<String>();
		StringTokenizer stAppIds = new StringTokenizer(sendDto.getApplicationIds(), "`");
		while(stAppIds.hasMoreElements()){
			lRegIds.clear();
			
			while(stAppIds.hasMoreElements()){
				String strAppId = stAppIds.nextToken();
				if(!strAppId.equals("")){
					lRegIds.add(strAppId);
					nTokenIdx++;
					
					//GCM 한 번 처리 용량만큼만 진행 후 계속 진행
					if(nTokenIdx >= nSendOnceMaxCnt){
						nTokenIdx = 0;
						break;
					}
				}
			}
			
			//전송 처리
			Message message = mb.build();
			Sender sender = new Sender(Config.getProperty(Config.STR_ANDROID_API_KEY));
			
			MulticastResult multiResult = sender.send(message, lRegIds, Config.INT_RETRY_SENDING_MSG);
	
			boolean isFirstDel = true;
			boolean isFirstUp = true;
			StringBuffer bufDel = new StringBuffer();
			StringBuffer bufUp = new StringBuffer();
			if (multiResult != null) {
				List<Result> resultList = multiResult.getResults();
				int nResultIdx = 0;
				
				for (Result result : resultList) {
					
					if(result.getMessageId() != null){
						String canonicalRegId = result.getCanonicalRegistrationId();
						if (canonicalRegId != null) {
							// same device has more than on registration ID: update database
							if(isFirstUp){
								isFirstUp = false;
							}else{
								bufUp.append("|");
							}
							bufUp.append(lRegIds.get(nResultIdx)+"`"+canonicalRegId);
						}
					}else{
						String strErr = result.getErrorCodeName();
						if (strErr.equals(Constants.ERROR_NOT_REGISTERED)
								|| strErr.equals(Constants.ERROR_INVALID_REGISTRATION)){
							// application has been removed from device - unregister database
							//데이터베이스에 새로운 레지스트레이션 아이디 등록 필요 상황 -> 현재 조회된 레지스트레이션 아이디 삭제 처리
							//구분자를 이용한 아이디 조합의 스트링 생성
							if(isFirstDel){
								isFirstDel = false;
							}else{
								bufDel.append("|");
							}
							bufDel.append(lRegIds.get(nResultIdx));
						}
					}	//if
					
					nResultIdx++;
				}	//for
				
				//푸시 업무 모바일 기기 관리 페이지에 해당 레스트레이션 아이디 삭제 처리 요청
				manageRegistrationId(REGISTRATION_ID_DELETE, bufDel);
				
				//푸시 업무 모바일 기기 관리 페이지에 해당 레스트레이션 아이디 변경 처리 요청
				manageRegistrationId(REGISTRATION_ID_UPDATE, bufUp);
			}
		}
	}
	
	public void manageRegistrationId(int nState, StringBuffer buf){
		
		//삭제 처리할 스트링이 없을 경우 리턴!
		if(buf.length() <= 0){
			return;
		}
		
		String strFuncId = sendDto.getFuncId();
		
		//GCM result 전송 처리 - 업무단 URL에 접속하여 데이터 전달 ======================================
		String url = null;
		
		try{
			
			if(nState == REGISTRATION_ID_DELETE){
				url = Config.getProperty(strFuncId + Config.STR_FORWARD_URL_INVALID_APP_ID);
			}else if(nState == REGISTRATION_ID_UPDATE){
				url =Config.getProperty(strFuncId + Config.STR_FORWARD_URL_UPDATE_APP_ID);
			}else{
				logger.info("[INFO][GCM][abnormal status] managing reg id, state:"+nState+", buf:"+buf.toString());
				return;
			}
			
			Map<String,String> params = new HashMap<String,String>();
			params.put("platform_id", Config.STR_PUSH_PLAT_ID_GCM);
			params.put("application_id", buf.toString());
			
			//비동기 post 처리..
			AsyncHandler.getInstance().post(url, params);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}