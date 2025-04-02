package io.github.mackzwellz.modelfuzzer.tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.mackzwellz.modelfuzzer.argumentproviders.SimpleArgumentsProvider;
import io.github.mackzwellz.modelfuzzer.argumentproviders.SingleNullField_AllFields_ArgumentsProvider;
import io.github.mackzwellz.modelfuzzer.argumentproviders.SingleNullField_AllNullableFields_ArgumentsProvider;
import io.github.mackzwellz.modelfuzzer.sampledata.models.Model1;
import io.github.mackzwellz.modelfuzzer.testutils.ObjectMapperProvider;
import io.github.mackzwellz.modelfuzzer.testutils.ReflectionUtil;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.NullSource;

import java.lang.reflect.Field;
import java.util.List;

class BaseTest {

    private static final ObjectMapper objectMapper = ObjectMapperProvider.getObjectMapper();

    @ParameterizedTest
    @NullSource
    @ArgumentsSource(SimpleArgumentsProvider.class)
    void simpleArgumentTest(Model1 model1) throws JsonProcessingException {
        String jsonResult = objectMapper.writeValueAsString(model1);
        System.out.println(jsonResult);
        Assertions.assertFalse(jsonResult.isEmpty());
    }

    @ParameterizedTest
    @ArgumentsSource(SingleNullField_AllFields_ArgumentsProvider.class)
    void iterateOverAllFieldsFromArgumentAccessorTest(ArgumentsAccessor argumentsAccessor) throws JsonProcessingException {
        int invocationIndex = argumentsAccessor.getInvocationIndex() - 1;
        Model1 model1 = (Model1) argumentsAccessor.get(0); //there is only one model per test
        String jsonResult = objectMapper.writeValueAsString(model1);
        System.out.println(jsonResult);

        List<Field> modelFields = List.of(model1.getClass().getDeclaredFields());
        Field fieldToCheck = modelFields.get(invocationIndex);
        Object valueToCheck = ReflectionUtil.getValue(fieldToCheck, model1);
        if (!fieldToCheck.getType().isPrimitive()) {
            Assertions.assertNull(valueToCheck, String.format("Field %s is not null", fieldToCheck));
        }
        Assertions.assertFalse(jsonResult.isEmpty());
    }

    @ParameterizedTest
    @ArgumentsSource(SingleNullField_AllNullableFields_ArgumentsProvider.class)
    void setNullToEachNullableFieldTest(Pair<Field, Model1> fieldModel1Pair) throws JsonProcessingException {
        String jsonResult = objectMapper.writeValueAsString(fieldModel1Pair.getValue());
        System.out.println(jsonResult);
        Field fieldToCheck = fieldModel1Pair.getKey();
        Object valueToCheck = ReflectionUtil.getValue(fieldToCheck, fieldModel1Pair.getValue());
        if (!fieldToCheck.getType().isPrimitive()) {
            Assertions.assertNull(valueToCheck, String.format("Field %s is not null", fieldToCheck));
        }
        Assertions.assertFalse(jsonResult.isEmpty());
    }
}
