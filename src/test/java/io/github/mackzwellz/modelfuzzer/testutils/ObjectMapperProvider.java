package io.github.mackzwellz.modelfuzzer.testutils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperProvider {

    public static ObjectMapper getObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        return objectMapper;
    }
}
