package com.tomato.framework.dao.config;

import javax.sql.DataSource;

import org.jfaster.mango.plugin.spring.MangoDaoScanner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Splitter;

@Configuration
@ConditionalOnBean(DataSource.class)
public class MangoConfiguration {
	
	@Bean
	public MangoDaoScanner scan(@Value("${mango.scanner.packages}") String packages) {
		MangoDaoScanner mangoDaoScanner = new MangoDaoScanner();
		mangoDaoScanner.setPackages(Splitter.on(';').trimResults().omitEmptyStrings().splitToList(packages));
		return mangoDaoScanner;
	}
}
