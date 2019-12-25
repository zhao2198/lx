package com.lx.core.module.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.text.SimpleDateFormat;

@Configuration
public class JacksonConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(JacksonConfig.class);

    public JacksonConfig() {
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        // module.addSerializer(IBizEnum.class, new IBizEnumValueSerializer());
        //module.addSerializer(Long.class, new LongValueSerializer());
        // module.addSerializer(Date.class, new DateValueSerializer());
        //module.setDeserializers(this.deserializers());
        //module.addDeserializer(Date.class, new DateValueDeserializer());
        objectMapper.registerModule(module);
        //objectMapper.setSerializerProvider(new JsonSerializerProvider());
        return objectMapper;
    }

//    private SimpleDeserializers deserializers() {
//        return new SimpleDeserializers() {
//            public JsonDeserializer<?> findEnumDeserializer(Class<?> type, DeserializationConfig config, BeanDescription beanDesc) throws JsonMappingException {
//                Class<?>[] interfaces = type.getInterfaces();
//                JacksonConfig.LOGGER.debug("find deserializer: type : {}", type);
//                if (interfaces.length > 0 && Arrays.asList(interfaces).contains(IBizEnum.class)) {
//                    String targetClazz = type.getSimpleName();
//                    String iBizClazz = IBizEnumValueDeserializer.class.getSimpleName();
//                    JacksonConfig.LOGGER.debug("Deserializer register: {} {}", targetClazz, iBizClazz);
//                    return new IBizEnumValueDeserializer(type);
//                } else {
//                    return super.findEnumDeserializer(type, config, beanDesc);
//                }
//            }
//        };
//    }

    @Bean
    @Primary
    public Jackson2ObjectMapperBuilder objectMapperBuilder(ObjectMapper objectMapper) {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.propertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        builder.indentOutput(true).dateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        builder.configure(objectMapper);
        LOGGER.info("init JacksonConfig.");
        return builder;
    }
}
