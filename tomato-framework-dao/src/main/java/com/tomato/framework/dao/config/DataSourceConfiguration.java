package com.tomato.framework.dao.config;

import com.tomato.framework.dao.interceptor.PageInterceptor;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.jfaster.mango.datasource.SimpleDataSourceFactory;
import org.jfaster.mango.operator.InterceptorChain;
import org.jfaster.mango.operator.Mango;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class DataSourceConfiguration {

	@Value("${jdbc.driverClassName}")
	String driverClass;
	@Value("${jdbc.url}")
	String url;
	@Value("${jdbc.username}")
	String username;
	@Value("${jdbc.password}")
	String password;

	@Bean(destroyMethod = "close")
	public DataSource hikariDataSource() {
		return new HikariDataSource(hikariConfig());
	}

	@Bean
	public HikariConfig hikariConfig() {
		HikariConfig config = new HikariConfig();
		config.setPoolName("spring-datasource-HikariCP");
		config.setConnectionTestQuery("SELECT 1");
		config.setDataSourceClassName(driverClass);
		Properties dsProperties = new Properties();
		dsProperties.setProperty("url", url);
		dsProperties.setProperty("user", username);
		dsProperties.setProperty("password", password);
		config.setDataSourceProperties(dsProperties);
		return config;
	}

	@Bean
	public SimpleDataSourceFactory simpleDataSourceFactory() {
		SimpleDataSourceFactory dataSourceFactory = new SimpleDataSourceFactory();
		dataSourceFactory.setDataSource(hikariDataSource());
		return dataSourceFactory;
	}

	@Bean
	public Mango mango() {
		Mango mango = Mango.newInstance();
		mango.setDataSourceFactory(simpleDataSourceFactory());
		InterceptorChain interceptorChain = new InterceptorChain();
		interceptorChain.addInterceptor(new PageInterceptor());
		mango.setInterceptorChain(interceptorChain);
		return mango;
	}
}
