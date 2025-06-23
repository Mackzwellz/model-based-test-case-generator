package io.github.mackzwellz.modelfuzzer.argumentproviders;

import io.github.mackzwellz.modelfuzzer.base.utils.ObjectDeepCopyProvider;
import io.github.mackzwellz.modelfuzzer.base.utils.ReflectionUtil;
import io.github.mackzwellz.modelfuzzer.sampledata.enums.Enum1;
import io.github.mackzwellz.modelfuzzer.sampledata.enums.Enum2;
import io.github.mackzwellz.modelfuzzer.sampledata.models.Model1;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.platform.commons.JUnitException;
import org.junit.platform.commons.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class SingleNullField_AllNullableFields_DynamicArgumentsProvider extends ClassesAndArgumentsProvider {

    private static final Model1 validModel = new Model1(
            true, false,
            1, 0,
            "stringvalue",
            Enum1.ONE, Enum2.PRIMITIVE,
            ZonedDateTime.now());

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context, ClassesAndArguments valueSource) {
        Class<?> clazz = valueSource.modelClasses()[0];
        ArgumentsProvider provider = instantiateArgumentsProvider(valueSource.providers()[0]);
        try {
            provider.provideArguments(context);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        List<Field> modelFields = List.of(clazz.getDeclaredFields());
        List<Pair<Field, Model1>> model1List = new ArrayList<>();
        for (Field modelField : modelFields) {
            if (!modelField.getType().isPrimitive()) {
                //todo find populator for model or throw unimplemented error
                Model1 model1 = ObjectDeepCopyProvider.getDeepCopyOrThrow(validModel);
                ReflectionUtil.setValue(modelField, model1, null);
                model1List.add(Pair.of(modelField, model1));
            }
        }
        return model1List.stream().map(Arguments::of);
    }

    @SuppressWarnings("ConstantConditions")
    private ArgumentsProvider instantiateArgumentsProvider(Class<? extends ArgumentsProvider> clazz) {
        try {
            return ReflectionUtils.newInstance(clazz);
        }
        catch (Exception ex) {
            if (ex instanceof NoSuchMethodException) {
                String message = String.format("Failed to find a no-argument constructor for ArgumentsProvider [%s]. "
                                + "Please ensure that a no-argument constructor exists and "
                                + "that the class is either a top-level class or a static nested class",
                        clazz.getName());
                throw new JUnitException(message, ex);
            }
            throw ex;
        }
    }

}
