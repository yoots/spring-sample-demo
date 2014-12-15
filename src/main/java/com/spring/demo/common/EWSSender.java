/*
 * Copyright (c) 2014 아시아나IDT
 * 
 * 이 프로그램은 저작권 보호법에 의해 보호됩니다.
 * 이 프로그램의 일부나 전부를 무단으로 복제하거나 배포하는 경우에는
 * 저작권의 침해가 되므로 주의하시기 바랍니다.
 */
package com.spring.demo.common;

/**
 * 이 클래스는...
 *
 * @author user
 * Created on 2014. 8. 26.
 */

import java.net.URI;
import java.util.List;

import microsoft.exchange.webservices.data.BodyType;
import microsoft.exchange.webservices.data.EmailMessage;
import microsoft.exchange.webservices.data.ExchangeCredentials;
import microsoft.exchange.webservices.data.ExchangeService;
import microsoft.exchange.webservices.data.MessageBody;
import microsoft.exchange.webservices.data.WebCredentials;

import com.spring.demo.model.User;
import com.spring.demo.util.Utils;

public class EWSSender implements Runnable{
	
	private User user;
	private List<String> receive;
	private String strSubject;
	private String strMsg;
	private String strAttach;
	
	public EWSSender(User user, List<String> receive, String subject, String msg, String attachFile){
		this.user = user;
		this.receive = receive;
		this.strSubject = subject;
		this.strMsg = msg;
		this.strAttach = attachFile;
	}
	
	/**
	 * 
	 * <pre>
	 * createExchangeService
	 * @param user
	 * @return
	 * @throws Exception
	 * </pre>
	 */
	public ExchangeService createExchangeService(User user) throws Exception{
		
		String userid = user.getUserAccount();
		String password = TripleDesCipher.decrypt(user.getTdPassword());	//cop관리자 용 
		String companycode = user.getCompanyCode();
		String EWSURL = "";
		ExchangeService service = null;

		try {
			service = new ExchangeService();
			ExchangeCredentials credentials = new WebCredentials(userid, password);
			service.setCredentials(credentials);
									
			//익스체인지 메일서버 URL분기 (AA:아시아나항공 BX:에어부산 )
			if(companycode.equals("AA")||companycode.equals("BX")){
				EWSURL = "http://telweb.flyasiana.com/ews/exchange.asmx";
			} else {
				EWSURL = "http://telweb.asianaidt.com/ews/exchange.asmx";
			}
			
			service.setUrl(new URI(EWSURL));
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		return service;
	}
	
	/**
	 * 
	 * <pre>
	 * sendMail
	 * @param user
	 * @param receive
	 * @param subject
	 * @param msg
	 * @param attachFile
	 * @return
	 * </pre>
	 */
	public boolean send(User user, List<String> receive, String subject, String msg, String attachFile)  {
		try {
			ExchangeService service = createExchangeService(user);
			
			EmailMessage message = new EmailMessage(service);
			
			message.setSubject(subject); 
			msg = Utils.tagRestoration(msg);
			
			MessageBody msgBody = MessageBody.getMessageBodyFromText(msg);
			msgBody.setBodyType(BodyType.HTML);
			message.setBody(msgBody); 
			for(String receiver : receive){
				message.getToRecipients().add(receiver); 
			}
			message.sendAndSaveCopy();
			
			return true;
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("error" + e.getMessage());
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		
		this.send(user, receive, strSubject, strMsg, strAttach);
	}
}
