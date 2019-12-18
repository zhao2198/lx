package com.lx.core.module.jackson.deserializer;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BooleanValueDeserializer<Boolean> extends JsonDeserializer<Boolean> {
    private static final Logger LOGGER = LoggerFactory.getLogger(BooleanValueDeserializer.class);

    public BooleanValueDeserializer() {
    }

    public Boolean deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        Boolean value = null;

        try {
            String s = jsonParser.getValueAsString();
            LOGGER.debug("BooleanValueDeserializer . deserialize[{}]", s);
            return value;
        } catch (Exception var5) {
            var5.printStackTrace();
            return null;
        }
    }
}
