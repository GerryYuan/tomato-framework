package com.tomato.framework.plugin.rpc.metrics;

import com.tomato.framework.plugin.rpc.domain.ClassIdBean;
import com.tomato.framework.plugin.rpc.listener.StaticsFilterHelper;
import org.springframework.boot.actuate.endpoint.PublicMetrics;
import org.springframework.boot.actuate.metrics.Metric;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;


/**
 * dubbo metrics
 *
 * @author xionghui
 * @email xionghui.xh@alibaba-inc.com
 * @since 1.0.0
 */
@Component
public class DubboMetrics implements PublicMetrics {

  @Override
  public Collection<Metric<?>> metrics() {
    List<Metric<?>> metrics = new LinkedList<Metric<?>>();
    for (Map.Entry<ClassIdBean, Map<String, AtomicLong>> entry : StaticsFilterHelper.STATICS_DATA_MAP
        .entrySet()) {
      ClassIdBean classIdBean = entry.getKey();
      Map<String, AtomicLong> countMap = entry.getValue();
      for (Map.Entry<String, AtomicLong> entry1 : countMap.entrySet()) {
        metrics.add(new Metric<Number>("dubbo." + classIdBean + "." + entry1.getKey(),
            entry1.getValue().get()));
      }
    }
    for (Map.Entry<ClassIdBean, Map<String, AtomicLong>> entry : StaticsFilterHelper.STATICS_DATA_MAP
        .entrySet()) {
      ClassIdBean classIdBean = entry.getKey();
      Map<String, AtomicLong> countMap = entry.getValue();
      for (Map.Entry<String, AtomicLong> entry1 : countMap.entrySet()) {
        metrics.add(new Metric<Number>("dubbo." + classIdBean + "." + entry1.getKey(),
            entry1.getValue().get()));
      }
    }
    return metrics;
  }
}
