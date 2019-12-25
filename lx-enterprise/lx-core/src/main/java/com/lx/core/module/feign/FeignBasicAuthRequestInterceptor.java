package com.lx.core.module.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;

import java.util.Collection;

/**
 * Feign请求拦截器
 * @author yinjihuan
 * @create 2017-11-10 17:25
 **/
public class FeignBasicAuthRequestInterceptor  implements RequestInterceptor {

    public FeignBasicAuthRequestInterceptor() {

    }

    @Override
    public void apply(RequestTemplate template) {
       // template.header("Authorization", template.headers());
//        Map<String, String> attributes = RibbonFilterContextHolder.getCurrentContext().getAttributes();
//        for (String key :  attributes.keySet()) {
//            String value = attributes.get(key);
//            System.out.println("feign :" + key + "\t" + value);
//            template.header(key, value);
//        }

        Collection<String> strings = (Collection)template.headers().get("Authorization");
        template.header("Authorization", strings);
        template.header("From-MicroService", new String[]{String.valueOf(true)});
        template.header("Client-Key", new String[]{"azRmgIXTuHr8jWc6"});
    }
}
