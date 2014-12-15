/*
 * Copyright (c) 2014 아시아나IDT
 * 
 * 이 프로그램은 저작권 보호법에 의해 보호됩니다.
 * 이 프로그램의 일부나 전부를 무단으로 복제하거나 배포하는 경우에는
 * 저작권의 침해가 되므로 주의하시기 바랍니다.
 */
package com.spring.demo.common;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.ning.http.client.AsyncCompletionHandler;
import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.AsyncHttpClientConfig;
import com.ning.http.client.Request;
import com.ning.http.client.RequestBuilder;
import com.ning.http.client.Response;

/**
 * 이 클래스는...
 *
 * @author user
 * Created on 2014. 8. 28.
 */

public class AsyncHandler {
	
	private static boolean poolingEnabled = true;
	private static int defaultMaxConnectionsPerHost = 100;
	private static int defaultIdleTimeout = 2000;
	private static int defaultMaxConnections = 120;
	
	//싱글톤 인스턴스
	private volatile static AsyncHandler instance ;
	
	private volatile static AsyncHttpClient asyncHttpClient;
	
	private Logger logger = Logger.getLogger(getClass().getName());
	
	/**
	 * 생성자...
	 */
	private AsyncHandler(){}

	/**
	 * <pre>
	 * 객체 전달...
	 * @return
	 * </pre>
	 */
	public synchronized static AsyncHandler getInstance(){ 
		
		if(instance == null){
			synchronized(AsyncHandler.class) {
				if (instance == null) {
                	
					instance = new AsyncHandler();
                	
					createHttpClient();
				}
			}
		}
		
		return instance;
	}
	
	/**
	 * 
	 *
	 * @param uri
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public boolean get(String uri , Map<String,String> params) throws Exception {
		
		boolean isSuccess = true;
		StringBuilder buff = new StringBuilder(uri);
		
		if(params != null && !params.isEmpty()){
			
			Set<String> key = params.keySet();
			for(String s : key)
				buff.append(s + "=" + params.get(s) + "&");
		}
		
        try {
//        	asyncHttpClient = createHttpClient();
        	Request request = new RequestBuilder("GET").setUrl(buff.toString()).addHeader("Content-Type", "application/x-www-form-urlencoded").build();
        	asyncHttpClient.prepareRequest(request).execute(new AsyncCompletionHandler<String>() {
                @Override
                public String onCompleted(Response response) throws Exception {
                	String body = response.getResponseBody("utf-8");
                	logger.info(body);
                    return body;
                }
                @Override
                public void onThrowable(Throwable t) {
                }
        	});
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		return isSuccess;
	}
	
	/**
	 * 
	 *
	 * @param uri
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public boolean post(String uri , final Map<String,String> params) throws Exception {
		
		boolean isSuccess = true;
		
        try {
//        	asyncHttpClient = createHttpClient();
        	AsyncHttpClient.BoundRequestBuilder requestBuilder = asyncHttpClient.preparePost(uri).addHeader("Content-Type", "application/x-www-form-urlencoded");
        	
        	if(params != null && !params.isEmpty()){
    			Set<String> key = params.keySet();
    			for(String s : key)
    				requestBuilder.addQueryParameter(s, params.get(s));
    		}
        	
        	requestBuilder.execute(new AsyncCompletionHandler<String>() {
                @Override
                public String onCompleted(Response response) throws Exception {
                	String body = response.getResponseBody("utf-8");
                	logger.info(response.getResponseBody("utf-8"));
                    return body;
                }
                @Override
                public void onThrowable(Throwable t) {
                }
        	});
        	
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		return isSuccess;
	}

	/**
	 * 
	 * <pre>
	 * createHttpClient
	 * @return
	 * </pre>
	 */
	public static void createHttpClient(){
				
		if(asyncHttpClient == null){
			
			AsyncHttpClientConfig config = new AsyncHttpClientConfig.Builder()
			.setAllowPoolingConnection(poolingEnabled)
			.setMaximumConnectionsTotal(defaultMaxConnections)
			.setMaximumConnectionsPerHost(defaultMaxConnectionsPerHost)
			.setIdleConnectionInPoolTimeoutInMs(defaultIdleTimeout)
			.build();
			
			asyncHttpClient = new AsyncHttpClient(config);
		}
	}
}
