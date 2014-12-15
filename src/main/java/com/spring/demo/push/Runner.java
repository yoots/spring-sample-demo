/*
 * Copyright (c) 2014 아시아나IDT
 * 
 * 이 프로그램은 저작권 보호법에 의해 보호됩니다.
 * 이 프로그램의 일부나 전부를 무단으로 복제하거나 배포하는 경우에는
 * 저작권의 침해가 되므로 주의하시기 바랍니다.
 */
package com.spring.demo.push;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;

import com.spring.demo.model.SendDto;

/**
 * 이 클래스는...
 *
 * @author user
 * Created on 2014. 8. 28.
 */

public class Runner implements Runnable {

	private SendDto sendDto; // push 데이터
	
	private Integer timeOut; // 작업 타임아웃 시간
	

	// 작업 객체를 생성함
	private RunnerExecutor executor = RunnerExecutor.getInstance();
	
	private List<PushSender> workList = new ArrayList<PushSender>();
	
	private List<SendDto> sendList;
	
	public Runner(SendDto sendDto , Integer timeOut) {
		
		this.sendDto = sendDto;
		this.timeOut = timeOut;
	}
	
	public Runner(SendDto sendDto) {
		this.sendDto = sendDto;
	}
	
	public Runner(List<SendDto> sendList) {
		
		this.sendList = sendList;
	}

	/**
	 * 작업을 강제 종료시킴
	 * @param pool
	 */
	public void shutdownAndAwaitTermination(ExecutorService pool) {

		pool.shutdown();
		
		try {
			
			if (!pool.awaitTermination(60, TimeUnit.SECONDS)) {
				pool.shutdownNow();
				if (!pool.awaitTermination(60, TimeUnit.SECONDS))
					System.err.println(this.sendDto.getPushData() + " did not terminate.");
			}
			
		} catch (InterruptedException ie) {
			pool.shutdownNow();
			Thread.currentThread().interrupt();
		}
		
		System.err.println(this.sendDto.getPushData() + " did terminate.");
	}
	
	/**
	 * 작업 실행 메소드
	 */
	public void run() {

		try {
			
			/**
			 * 대량 발송시...
			 */
			if(sendList != null && !sendList.isEmpty()){
				
				for(SendDto sendDto : sendList){
					workList.add(new PushSender(sendDto));
				}
			}
			else{		//단일건 발송시..
				workList.add(new PushSender(this.sendDto));
			}
			
			if(this.timeOut == null){
				//작업 실행기에 타임아웃 제한 시간을 설정한 후 실행함
				executor.invokeAll(workList, this.timeOut, TimeUnit.SECONDS);
			}
			else{
				// 작업 실행함
				executor.invokeAll(workList);
			}
			
		} catch (InterruptedException e) {
			
			e.printStackTrace();
			shutdownAndAwaitTermination(executor.getExecutor());
			
		} catch (NullPointerException e) {
			
			e.printStackTrace();
			shutdownAndAwaitTermination(executor.getExecutor());
			
		} catch (RejectedExecutionException e) {
			
			e.printStackTrace();
			shutdownAndAwaitTermination(executor.getExecutor());
			
		}

		// 작업 실행이 완료됨
		executor.shutdown();
	}

}
