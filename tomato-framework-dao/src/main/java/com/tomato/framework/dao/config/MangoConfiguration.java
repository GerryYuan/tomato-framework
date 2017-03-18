package com.tomato.framework.dao.config;

import com.google.common.base.Splitter;
import org.jfaster.mango.plugin.spring.MangoDaoScanner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

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
