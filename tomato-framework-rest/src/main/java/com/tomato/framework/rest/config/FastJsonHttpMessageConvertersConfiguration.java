package com.tomato.framework.rest.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter4;
import com.google.common.collect.Lists;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;

import java.nio.charset.Charset;
import java.util.List;

@Configuration
@ConditionalOnClass({ JSON.class })
public class FastJsonHttpMessageConvertersConfiguration {

	private static final String DATA_FORMATE = "yyyy-MM-dd HH:mm:ss";

	@Bean
	public HttpMessageConverters fastJsonHttpMessageConverters() {
		FastJsonHttpMessageConverter4 fastConverter = new FastJsonHttpMessageConverter4();
		FastJsonConfig fastJsonConfig = new FastJsonConfig();

		MediaType type = new MediaType("application", "json");
		List<MediaType> mediaTypeList = Lists.newArrayList(new MediaType(type, Charset.defaultCharset()));
		fastConverter.setSupportedMediaTypes(mediaTypeList);

		fastJsonConfig.setDateFormat(DATA_FORMATE);

		// 禁止循环引用检测
		fastJsonConfig.setSerializerFeatures(SerializerFeature.DisableCircularReferenceDetect);

		fastConverter.setFastJsonConfig(fastJsonConfig);
		HttpMessageConverter<?> converter = fastConverter;
		return new HttpMessageConverters(converter);
	}
	
}
