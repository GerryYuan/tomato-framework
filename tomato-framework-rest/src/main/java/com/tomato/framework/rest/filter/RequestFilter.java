package com.tomato.framework.rest.filter;

import com.google.common.base.Stopwatch;
import com.google.common.collect.Sets;
import com.tomato.framework.core.util.EmptyUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author Created by gerry
 * @version 1.0
 * @date 2017-04-16-21:59
 */
@Slf4j
public class RequestFilter implements javax.servlet.Filter {

    private static final String PARAM_NAME_EXCLUSIONS = "exclusions";
    private Set<String> excludesPattern;
    private String contextPath;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        Stopwatch sp = Stopwatch.createStarted();

        if (!(request instanceof HttpServletRequest && response instanceof HttpServletResponse)) {
            throw new ServletException("HttpFilter can't handle an non-http request");
        }

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String path = httpRequest.getRequestURI();
        if (EmptyUtils.isNotEmpty(httpRequest.getQueryString())) {
            path += "?" + httpRequest.getQueryString();
        }
        try {
            chain.doFilter(httpRequest, httpResponse);
        } finally {
            log.info("access url [{}], cost time [{}] ms )", path, sp.elapsed(TimeUnit.MILLISECONDS));
            MDC.clear();
        }
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        String exclusions = config.getInitParameter(PARAM_NAME_EXCLUSIONS);
        if (EmptyUtils.isNotEmpty(exclusions)) {
            excludesPattern = Sets.newHashSet(Arrays.asList(exclusions.split("\\s*,\\s*")));
        }

        ServletContext context = config.getServletContext();
        if (context.getMajorVersion() == 2 && context.getMinorVersion() < 5) {
            this.contextPath = null;
        } else {
            String contextPath = context.getContextPath();
            if (EmptyUtils.isEmpty(contextPath)) {
                contextPath = "/";
            }

            this.contextPath = contextPath;
        }
    }

    @Override
    public void destroy() {
    }
}
