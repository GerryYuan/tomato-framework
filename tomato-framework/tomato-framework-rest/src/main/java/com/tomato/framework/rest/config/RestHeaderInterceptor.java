package com.tomato.framework.rest.config;

import java.io.IOException;

import org.slf4j.MDC;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import com.tomato.framework.core.common.RestConst;

/**
 * restTemple请求消息头拦截器
 * 
 *
 * @author gerry
 * @version  1.0, 2016年9月6日下午2:39:04
 */
public class RestHeaderInterceptor implements ClientHttpRequestInterceptor{

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
		request.getHeaders().set(RestConst.REQUEST_ID, MDC.get(RestConst.REQUEST_ID));
		return execution.execute(request, body);
	}

}
