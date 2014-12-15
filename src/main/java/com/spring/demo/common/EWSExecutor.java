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
 * @author yoots
 *
 * Created on 2014. 8. 8.
 */
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import com.spring.demo.model.User;

/**
 * 이 클래스는...
 *
 * @author yoots Created on 2014. 7. 21.
 */

public class EWSExecutor {
	
	private static final int MAX_POOL = 100;
	
	//싱글톤 인스턴스
	private volatile static EWSExecutor instance ;
	
	//ThreadPool Size 를 지정한다.
    private volatile static ExecutorService execute = Executors.newFixedThreadPool(MAX_POOL);
	
	/**
	 * 생성자...
	 */
	private EWSExecutor(){}

	/**
	 * <pre>
	 * 객체 전달...
	 * @return
	 * </pre>
	 */
	public static EWSExecutor getInstance(){ 
		
		if(instance == null){
			
			synchronized(EWSExecutor.class) {
				
				if (instance == null) {
                	instance = new EWSExecutor();
				}
			}
		}
		
		return instance;
	}
	
	/**
	 * 
	 *
	 * @return
	 */
	public ExecutorService getExecutor(){
		
		return this.execute;
	}

	/**
	 * @param timeout
	 * @param unit
	 * @return
	 * @throws InterruptedException
	 * @see java.util.concurrent.ExecutorService#awaitTermination(long, java.util.concurrent.TimeUnit)
	 */
	public boolean awaitTermination(long timeout, TimeUnit unit)
			throws InterruptedException {
		return execute.awaitTermination(timeout, unit);
	}

	/**
	 * 
	 *
	 * @param request
	 */
	public void execute(User user, List<String> receive, String subject, String msg, String attachFile) {
		this.execute(new EWSSender(user, receive, subject, msg, attachFile));
	}
	
	/**
	 * @param command
	 * @see java.util.concurrent.Executor#execute(java.lang.Runnable)
	 */
	public void execute(Runnable command) {
		execute.execute(command);
	}

	/**
	 * @return
	 * @see java.util.concurrent.ExecutorService#isShutdown()
	 */
	public boolean isShutdown() {
		return execute.isShutdown();
	}

	/**
	 * @return
	 * @see java.util.concurrent.ExecutorService#isTerminated()
	 */
	public boolean isTerminated() {
		return execute.isTerminated();
	}

	/**
	 * 
	 * @see java.util.concurrent.ExecutorService#shutdown()
	 */
	public void shutdown() {
		execute.shutdown();
	}

	/**
	 * @return
	 * @see java.util.concurrent.ExecutorService#shutdownNow()
	 */
	public List<Runnable> shutdownNow() {
		return execute.shutdownNow();
	}

	/**
	 * @param task
	 * @return
	 * @see java.util.concurrent.ExecutorService#submit(java.util.concurrent.Callable)
	 */
	public <T> Future<T> submit(Callable<T> task) {
		return execute.submit(task);
	}

	/**
	 * @param task
	 * @param result
	 * @return
	 * @see java.util.concurrent.ExecutorService#submit(java.lang.Runnable, java.lang.Object)
	 */
	public <T> Future<T> submit(Runnable task, T result) {
		return execute.submit(task, result);
	}

	/**
	 * @param task
	 * @return
	 * @see java.util.concurrent.ExecutorService#submit(java.lang.Runnable)
	 */
	public Future<?> submit(Runnable task) {
		return execute.submit(task);
	}
	
	
	
}
