package com.lx.core.common.web.controller;


import com.lx.common.entity.AbstractEntity;
import com.lx.common.util.JsonUtil;
import com.lx.core.common.utils.RestResultUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

public abstract class BaseController<M extends AbstractEntity, ID extends Serializable> {

    protected HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    protected HttpServletResponse getResponse() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    }

    protected HttpSession getSession() {
        return getRequest().getSession();
    }


    /**
     * 向前台返回失败的消息 Lian weimao CreateTime:2018年6月27日 下午3:45:52
     *
     * @param msg
     * @param response
     */
    public void responseFaildMsg(String msg, HttpServletResponse response) {
        String json = "";
        try {
            json = JsonUtil.bean2Json(RestResultUtil.failed(msg));
        } catch (IOException e) {
            json = "";
            e.printStackTrace();
        }
        responseJson(json, response);
    }

    /**
     * 返回成功的消息 Lian weimao CreateTime:2018年6月27日 下午3:46:07
     *
     * @param msg
     * @param response
     */
    public void responseSuccessMsg(String msg, HttpServletResponse response) {
        String json = "";
        try {
            json = JsonUtil.bean2Json(RestResultUtil.ok(msg));
        } catch (IOException e) {
            json = "";
            e.printStackTrace();
        }
        responseJson(json, response);
    }

    public void responseJson(String json, HttpServletResponse response) {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter pw = null;
        try {
            pw = response.getWriter();
            pw.print(json);
            pw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            pw.close();
        }
    }

    /**
     * 获取真实ip
     *
     * @param request
     * @return
     */
    public String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
            return ip;
        }
        ip = request.getHeader("X-Forwarded-For");
        if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个IP值，第一个为真实IP。
            int index = ip.indexOf(',');
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        } else {
            return request.getRemoteAddr();
        }
    }

}
