package com.lx.common.util;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;

/**
 * json工具类 Create Date: 2018年4月28日 上午9:21:10
 *
 * @version: V3.0.1
 * @author: zhao wei
 */
@Slf4j
public class JsonUtil {

    private static ObjectMapper mapper = new ObjectMapper();

    /**
     * @param obj
     * @return
     * @throws IOException
     */
    public static String bean2Json(Object obj) throws IOException {
        StringWriter sw = new StringWriter();
        JsonGenerator gen = new JsonFactory().createGenerator(sw);
        mapper.writeValue(gen, obj);
        gen.close();
        return sw.toString();
    }

    /**
     * @param jsonStr
     * @param objClass
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    public static <T> T json2Bean(String jsonStr, Class<T> objClass) {
        try {
            return mapper.readValue(jsonStr, objClass);
        } catch (Exception e) {

            return null;
        }
    }

    /**
     * json转map
     * Lian weimao CreateTime:2018年5月28日 上午10:34:59
     *
     * @param jsonStr
     * @param
     * @param keyClass
     * @param valueClass
     * @return
     */
    public static <K, V> HashMap<K, V> json2Map(String jsonStr, Class<K> keyClass, Class<V> valueClass) {
        JavaType mapType = getMapType(HashMap.class, keyClass, valueClass);
        try {
            return (HashMap<K, V>) mapper.readValue(jsonStr, mapType);
        } catch (IOException e) {
            return null;
        }
    }

    public static <K, V> HashMap<String, V> json2HashMap(String jsonStr, Class<V> valueClass) {
        return json2Map(jsonStr, String.class, valueClass);
    }

    /**
     * json转list
     * Lian weimao CreateTime:2018年5月28日 上午10:36:26
     *
     * @param jsonStr
     * @param listClass
     * @param elementClasses
     * @return
     */
    public static <V> List<V> json2List(String jsonStr, Class<?> listClass, Class<V> elementClasses) {
        JavaType mapType = getCollectionType(listClass, elementClasses);
        try {
            return (List<V>) mapper.readValue(jsonStr, mapType);
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * 获取list的类型
     * Lian weimao CreateTime:2018年5月28日 上午10:31:02
     *
     * @param collectionClass
     * @param elementClasses
     * @return
     */
    public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }

    /**
     * 获取map的类型
     * Lian weimao CreateTime:2018年5月28日 上午10:31:13
     *
     * @param mapClass
     * @param keyClass
     * @param valueClass
     * @return
     */
    public static JavaType getMapType(Class<?> mapClass, Class<?> keyClass, Class<?> valueClass) {
        return mapper.getTypeFactory().constructParametricType(mapClass, keyClass, valueClass);
    }
}
