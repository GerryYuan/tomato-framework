package com.tomato.framework.rest.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestClientConfiguration {

	@Autowired
	private HttpMessageConverters httpMessageConverters;

	@Value("${httpclent.connectTimeout}")
	private Integer connectTimeout;

	@Value("${httpclent.connectionRequestTimeout}")
	private Integer readTimeout;

	@Bean
	public RestTemplate getClient() {

		SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
		requestFactory.setConnectTimeout(connectTimeout);
		requestFactory.setReadTimeout(readTimeout);

		RestTemplate restTemplate = new RestTemplate(httpMessageConverters.getConverters());
		restTemplate.setRequestFactory(requestFactory);
		restTemplate.setErrorHandler(new DefaultResponseErrorHandler());

		List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
		interceptors.add(new RestHeaderInterceptor());
		restTemplate.setInterceptors(interceptors);

		return restTemplate;
	}
}
