package com.tomato.framework.plugin.rpc.health;

import com.alibaba.dubbo.rpc.service.EchoService;
import com.tomato.framework.plugin.rpc.DubboConsumerAutoConfiguration;
import com.tomato.framework.plugin.rpc.domain.ClassIdBean;
import com.tomato.framework.plugin.rpc.listener.ConsumerSubscribeListener;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

/**
 * dubbo health indicator
 *
 * @author xionghui
 * @email xionghui.xh@alibaba-inc.com
 * @since 1.0.0
 */
@Component
public class DubboHealthIndicator extends AbstractHealthIndicator {

  @Override
  protected void doHealthCheck(Health.Builder builder) throws Exception {
    for (ClassIdBean classIdBean : ConsumerSubscribeListener.SUBSCRIBEDINTERFACES_SET) {
      Object service = DubboConsumerAutoConfiguration.DUBBO_REFERENCES_MAP.get(classIdBean);
      EchoService echoService = (EchoService) service;
      if (echoService != null) {
        echoService.$echo("Hello");
        builder.withDetail(classIdBean.toString(), true);
      }
    }
    builder.up();
  }

}
