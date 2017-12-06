package com.tomato.framework.plugin.rpc;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * dubbo properties
 *
 * @author xionghui
 * @email xionghui.xh@alibaba-inc.com
 * @since 1.0.0
 */
@ConfigurationProperties(prefix = "spring.dubbo")
@Data
public class DubboProperties {
  /**
   * dubbo application name
   */
  private String appname;
  /**
   * dubbo registry address
   */
  private String registry;
  /**
   * communication protocol, default is dubbo
   */
  private String protocol = "dubbo";
  /**
   * dubbo listen port, default 20800
   */
  private int port = 20800;
  /**
   * dubbo thread count, default 200
   */
  private int threads = 200;

  /**
   * dubbo version, may override by {@link com.alibaba.dubbo.config.annotation.Service#version()}
   */
  private String version = "";

  /**
   * dubbo group, may override by {@link com.alibaba.dubbo.config.annotation.Service#group()}
   */
  private String group = "";

  @Override
  public String toString() {
    return "DubboProperties [appname=" + this.appname + ", registry=" + this.registry
        + ", protocol=" + this.protocol + ", port=" + this.port + ", threads=" + this.threads
        + ", version=" + this.version + ", group=" + this.group + "]";
  }
}
