package com.tomato.framework.plugin.rpc.listener;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.common.utils.ConcurrentHashSet;
import com.alibaba.dubbo.rpc.Exporter;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.dubbo.rpc.listener.ExporterListenerAdapter;
import com.tomato.framework.plugin.rpc.domain.ClassIdBean;
import com.tomato.framework.plugin.rpc.domain.SpringBootStarterDobboConstants;

import java.util.Set;

/**
 * provider export listener
 *
 * @author xionghui
 * @email xionghui.xh@alibaba-inc.com
 * @since 1.0.0
 */
@Activate
public class ProviderExportListener extends ExporterListenerAdapter {
  // exported interfaces
  public static final Set<ClassIdBean> EXPORTEDINTERFACES_SET =
      new ConcurrentHashSet<ClassIdBean>();

  // exported urls
  public static final Set<URL> EXPORTED_URL = new ConcurrentHashSet<URL>();

  @Override
  public void exported(Exporter<?> exporter) throws RpcException {
    Invoker<?> invoker = exporter.getInvoker();
    Class<?> anInterface = invoker.getInterface();
    URL url = invoker.getUrl();
    String group = url.getParameter(SpringBootStarterDobboConstants.GROUP);
    String version = url.getParameter(SpringBootStarterDobboConstants.VERSION);
    ClassIdBean classIdBean = new ClassIdBean(anInterface, group, version);
    EXPORTEDINTERFACES_SET.add(classIdBean);
    if (!url.getProtocol().equals("injvm")) {
      EXPORTED_URL.add(url);
    }
  }

  @Override
  public void unexported(Exporter<?> exporter) {
    Invoker<?> invoker = exporter.getInvoker();
    Class<?> anInterface = invoker.getInterface();
    URL url = invoker.getUrl();
    String group = url.getParameter(SpringBootStarterDobboConstants.GROUP);
    String version = url.getParameter(SpringBootStarterDobboConstants.VERSION);
    ClassIdBean classIdBean = new ClassIdBean(anInterface, group, version);
    EXPORTEDINTERFACES_SET.remove(classIdBean);
    if (!url.getProtocol().equals("injvm")) {
      EXPORTED_URL.remove(url);
    }
  }
}
