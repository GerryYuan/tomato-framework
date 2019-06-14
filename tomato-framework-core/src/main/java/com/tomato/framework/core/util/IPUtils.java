package com.tomato.framework.core.util;

import com.google.common.collect.Lists;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class IPUtils {
    
    private static final String DEFAULT_LOCAL_IP = "127.0.0.1";
    private static volatile List<String> NETWORK_INTERFACE_NAMES;
    
    public static String getLocalIp() {
        String os = System.getProperty("os.name");
        if (os.toLowerCase().startsWith("Linux") || os.toLowerCase().startsWith("Mac")) {
            return getLocalHostLANAddress();
        } else {
            Enumeration<?> allNetInterfaces;
            try {
                allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            } catch (SocketException e) {
                log.warn(e.getMessage(), e);
                return DEFAULT_LOCAL_IP;
            }
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
                Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
                for (InetAddress address : Collections.list(addresses)) {
                    if (address instanceof Inet4Address) {
                        return address.getHostAddress();
                    }
                }
            }
        }
        return DEFAULT_LOCAL_IP;
    }
    
    private static String getLocalHostLANAddress() {
        if (NETWORK_INTERFACE_NAMES == null) {
            NETWORK_INTERFACE_NAMES = Lists.newArrayList("bond0", "eth0", "en0");
        }
        try {
            InetAddress candidateAddress = null;
            for (Enumeration<NetworkInterface> ifaces = NetworkInterface.getNetworkInterfaces();
                ifaces.hasMoreElements(); ) {
                NetworkInterface iface = ifaces.nextElement();
                if (!NETWORK_INTERFACE_NAMES.contains(iface.getDisplayName())) {
                    continue;
                }
                for (Enumeration<InetAddress> inetAddrs = iface.getInetAddresses(); inetAddrs.hasMoreElements(); ) {
                    InetAddress inetAddr = (InetAddress) inetAddrs.nextElement();
                    if (!inetAddr.isLoopbackAddress()) {
                        if (inetAddr.isSiteLocalAddress()) {
                            return inetAddr.getHostAddress();
                        } else if (candidateAddress == null) {
                            candidateAddress = inetAddr;
                        }
                    }
                }
            }
            if (candidateAddress != null) {
                return candidateAddress.getHostAddress();
            }
            InetAddress jdkSuppliedAddress = InetAddress.getLocalHost();
            if (jdkSuppliedAddress == null) {
                throw new UnknownHostException("The JDK InetAddress.getLocalHost() method unexpectedly returned null.");
            }
            return jdkSuppliedAddress.getHostAddress();
        } catch (Exception e) {
            UnknownHostException unknownHostException = new UnknownHostException(
                "Failed to determine LAN address: " + e);
            unknownHostException.initCause(e);
            log.error(e.getMessage(), e);
        }
        return DEFAULT_LOCAL_IP;
    }
    
    public static void main(String[] args) {
        System.out.println(IPUtils.getLocalIp());
    }
}
