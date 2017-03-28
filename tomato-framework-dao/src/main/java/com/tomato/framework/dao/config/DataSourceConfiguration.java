package com.tomato.framework.dao.config;

import com.google.common.base.Splitter;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.tomato.framework.core.common.SpecialCharConst;
import com.tomato.framework.core.exception.DataSourceException;
import com.tomato.framework.core.util.EmptyUtils;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.jfaster.mango.datasource.DataSourceFactory;
import org.jfaster.mango.datasource.DataSourceType;
import org.jfaster.mango.datasource.MasterSlaveDataSourceFactory;
import org.jfaster.mango.datasource.SimpleDataSourceFactory;
import org.jfaster.mango.interceptor.InterceptorChain;
import org.jfaster.mango.operator.Mango;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Configuration
public class DataSourceConfiguration implements EnvironmentAware {

    private final static String MASTER = DataSourceType.MASTER.name().toLowerCase();

    private final static String SLAVE = DataSourceType.SLAVE.name().toLowerCase();

    private RelaxedPropertyResolver relaxedPropertyResolver;

    private List<DataSourceFactory> dataSourceFactories = Lists.newArrayList();

    private AtomicInteger dataSourceCount = new AtomicInteger(0);

    @Override
    public void setEnvironment(Environment environment) {
        relaxedPropertyResolver = new RelaxedPropertyResolver(environment, "jdbc.");
    }

    private void initDataSources() {
        String dsNames = relaxedPropertyResolver.getProperty("names");
        if (EmptyUtils.isEmpty(dsNames)) {//一个数据源
            Map<String, Object> dsParam = relaxedPropertyResolver.getSubProperties("");
            if (EmptyUtils.isEmpty(dsParam)) {
                return;
            }
            DataSource dataSource = new HikariDataSource(initHikariConfig(dsParam));
            dataSourceFactories.add(new SimpleDataSourceFactory(SimpleDataSourceFactory.DEFULT_NAME, dataSource));
        } else {
            List<String> dataSourceNames = Splitter.on(',').trimResults().omitEmptyStrings().splitToList(dsNames);
            Map<String, DataSource> masterDataSouces = Maps.newConcurrentMap();
            Multimap<String, DataSource> slaveDataSources = ArrayListMultimap.create();
            for (String dataSourceName : dataSourceNames) {//dataSourceName->master_tomato_framework,slave_tomato_framework_1
                Map<String, Object> dsParam = relaxedPropertyResolver.getSubProperties(dataSourceName + ".");
                if (EmptyUtils.isEmpty(dsParam)) {
                    throw new DataSourceException("properties file property main name jdbc." + dataSourceName + " is null, please check properties.");
                }
                DataSource dataSource = new HikariDataSource(initHikariConfig(dsParam));
                if (StringUtils.startsWithIgnoreCase(dataSourceName, MASTER)) {
                    String name = StringUtils.replace(dataSourceName, MASTER + SpecialCharConst.UNDERLINE, "");
                    if (EmptyUtils.isEmpty(name)) {
                        throw new DataSourceException("properties file property main name jdbc." + dataSourceName + " is null, please check properties.");
                    }
                    masterDataSouces.put(name, dataSource);
                } else if (StringUtils.startsWithIgnoreCase(dataSourceName, SLAVE)) {
                    String name = StringUtils.replace(dataSourceName, SLAVE + SpecialCharConst.UNDERLINE, "");
                    if (EmptyUtils.isEmpty(name)) {
                        throw new DataSourceException("properties file property main name jdbc." + dataSourceName + " is null, please check properties.");
                    }
                    //name-> tomato_framework_1,tomato_framework_2
                    Set<String> masterNames = masterDataSouces.keySet();
                    masterNames.forEach(masterName -> {
                        if (StringUtils.startsWithIgnoreCase(name, masterName)) {
                            slaveDataSources.put(masterName, dataSource);
                            return;
                        }
                    });
                } else {
                    throw new DataSourceException("properties file property name 'jdbc.names' no main with master or slave, please check properties.");
                }
            }
            if (EmptyUtils.isEmpty(masterDataSouces)) {
                throw new DataSourceException("master dataSource must not null, please chcek properties.");
            }
            masterDataSouces.forEach((name, master) -> {
                dataSourceFactories.add(new MasterSlaveDataSourceFactory(name, master, Lists.newArrayList(slaveDataSources.get(name))));
            });
        }

    }

    private HikariConfig initHikariConfig(Map<String, Object> porpertyMap) {
        HikariConfig config = new HikariConfig();
        config.setPoolName("spring-datasource-HikariCP-" + dataSourceCount.incrementAndGet());
        config.setConnectionTestQuery("SELECT 1");
        config.setDataSourceClassName((String) porpertyMap.get("driverClassName"));
        Properties dsProperties = new Properties();
        dsProperties.setProperty("url", (String) porpertyMap.get("url"));
        dsProperties.setProperty("user", (String) porpertyMap.get("username"));
        dsProperties.setProperty("password", (String) porpertyMap.get("password"));
        config.setDataSourceProperties(dsProperties);
        return config;
    }

    @Bean
    public Mango mango() {
        initDataSources();
        Mango mango = Mango.newInstance(dataSourceFactories);
        InterceptorChain interceptorChain = new InterceptorChain();
        interceptorChain.addInterceptor(new com.tomato.framework.dao.interceptor.MySQLPageInterceptor());
        mango.setInterceptorChain(interceptorChain);
        return mango;
    }
}
