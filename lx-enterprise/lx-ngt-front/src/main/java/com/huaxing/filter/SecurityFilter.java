package com.huaxing.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
@Component
@ServletComponentScan
@WebFilter(urlPatterns = "/*")
public class SecurityFilter implements Filter {

    @Value("${lx.api.url.sso}")
    private String ssoUrl;
    @Value("${lx.api.url.ngt}")
    private String ngtUrl;

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub
        // GreenUrlSet.add("/");

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        // TODO Auto-generated method stub
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String uri = request.getRequestURI();

        log.debug("uri:" + uri);

        request.setAttribute("ssoUrl", ssoUrl);
        request.setAttribute("ngtUrl", ngtUrl);

        filterChain.doFilter(request, response);

    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub
    }

}