package io.github.mackzwellz.modelfuzzer.base.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectDeepCopyProvider {

    @SuppressWarnings("unchecked")
    public static <T> T getDeepCopyOrThrow(T source) {
        ObjectMapper objectMapper = ObjectMapperProvider.getObjectMapper();
        try {
            return (T) objectMapper.readValue(objectMapper.writeValueAsString(source), source.getClass());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
