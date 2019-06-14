package com.alibaba.boot.dubbo;

import com.tomato.framework.plugin.rpc.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * spring boot默认使用tomcat8，所以需要jdk7以上版本
 *
 * @author xionghui
 * @email xionghui.xh@alibaba-inc.com
 * @since 1.0.0
 */
@SpringBootApplication
@EnableDubboConfiguration
public class DubboProviderLauncher {

  public static void main(String[] args) {
    SpringApplication.run(DubboProviderLauncher.class, args);
  }
}
