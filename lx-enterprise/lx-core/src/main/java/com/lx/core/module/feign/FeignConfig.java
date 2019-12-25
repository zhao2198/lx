package com.lx.core.module.feign;

import com.fasterxml.jackson.core.JsonGenerator.Feature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.module.SimpleModule;
import feign.Logger;
import feign.Request;
import feign.codec.Decoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;


/**
 * description
 *
 * @Project FeignConfig(com.huaxing.config)
 * @Author  zhaowei
 * @Date    2019/10/7 17:05
 * @Version v1.1.0
 */

@Configuration
public class FeignConfig {


    @Bean
    public Decoder feignDecoder() {
        HttpMessageConverter jacksonConverter = new MappingJackson2HttpMessageConverter(customObjectMapper());
        ObjectFactory<HttpMessageConverters> objectFactory = () -> new HttpMessageConverters(jacksonConverter);
        return new ResponseEntityDecoder(new SpringDecoder(objectFactory));
    }


    public ObjectMapper customObjectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();
        //Customize as much as you want
        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        objectMapper.configure(Feature.WRITE_BIGDECIMAL_AS_PLAIN, true);

        objectMapper.setTimeZone(TimeZone.getTimeZone("GMT+08"));
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        return objectMapper;
    }


    /**
     * 日志级别
     * @return
     */
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    /*@Bean
    public Contract feignContract() {
        return new feign.Contract.Default();
    }*/

   /* @Bean
    public BasicAuthRequestInterceptor myBasicAuthRequestInterceptor(){
        return new BasicAuthRequestInterceptor("user","password");
    }*/
    /**
     * 创建Feign请求拦截器，在发送请求前设置认证的token,各个微服务将token设置到环境变量中来达到通用
     * @return
     */
    @Bean
    public FeignBasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new FeignBasicAuthRequestInterceptor();
    }
    @Bean
    public Request.Options options() {
        return new Request.Options(5000, 10000);
    }

    /*@Bean
    public Decoder decoder() {
     return new MyDecoder();
    }

    @Bean
    public Encoder encoder() {
        return new MyEncoder();
    }*/

//    @Bean
//    @Scope("prototype")
//    public Feign.Builder feignBuilder() {
//        return Feign.builder();
//    }




    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();

        mapper.registerModule(module);

        // 设置日期格式
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        converter.setObjectMapper(mapper);

        mapper.configure(Feature.WRITE_BIGDECIMAL_AS_PLAIN, true);

        mapper.setTimeZone(TimeZone.getTimeZone("GMT+08"));
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);


        // 设置中文编码格式
        List<MediaType> list = new ArrayList<MediaType>();
        list.add(MediaType.APPLICATION_JSON_UTF8);
        converter.setSupportedMediaTypes(list);

        return converter;
    }

}