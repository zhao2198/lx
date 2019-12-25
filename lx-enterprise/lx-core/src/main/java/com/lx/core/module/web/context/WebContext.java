package com.lx.core.module.web.context;


import javax.servlet.http.HttpServletRequest;

public class WebContext {

    private static final ThreadLocal<HttpServletRequest> CONTEXT_REQUEST = new ThreadLocal();
    private static final ThreadLocal<Boolean> CONTEXT_DISABLE_TENANT = new ThreadLocal();
    private static final ThreadLocal<String> CONTEXT_REMOTE_IP = new ThreadLocal();

    private WebContext() {
    }

    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) CONTEXT_REQUEST.get();
    }

    public static void cacheRequest(HttpServletRequest request) {
        CONTEXT_REQUEST.set(request);
    }

    public static void clearRequest() {
        CONTEXT_REQUEST.remove();
    }


    public static void cacheDisableTenantFlag(Boolean yes) {
        CONTEXT_DISABLE_TENANT.set(yes);
    }

    public static Boolean isDisableTenant() {
        return (Boolean) CONTEXT_DISABLE_TENANT.get();
    }

    public static void cleanDisableTenantFlag() {
        CONTEXT_DISABLE_TENANT.remove();
    }

    public static void cacheRemoteIp(String ip) {
        CONTEXT_REMOTE_IP.set(ip);
    }

    public static String getRemoteIp() {
        return (String) CONTEXT_REMOTE_IP.get();
    }

    public static void cleanRemoteIp() {
        CONTEXT_REMOTE_IP.remove();
    }

    public static void clear() {
        clearRequest();
        cleanDisableTenantFlag();
        cleanRemoteIp();
    }
}
