package io.github.mackzwellz.modelfuzzer.argumentproviders;

import io.github.mackzwellz.modelfuzzer.sampledata.enums.Enum1;
import io.github.mackzwellz.modelfuzzer.sampledata.enums.Enum2;
import io.github.mackzwellz.modelfuzzer.sampledata.models.Model1;
import io.github.mackzwellz.modelfuzzer.testutils.ObjectDeepCopyProvider;
import io.github.mackzwellz.modelfuzzer.testutils.ReflectionUtil;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.lang.reflect.Field;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class SingleNullField_AllFields_ArgumentsProvider implements ArgumentsProvider {

    private static final Model1 validModel = new Model1(
            true, false,
            1, 0,
            "stringvalue",
            Enum1.ONE, Enum2.PRIMITIVE,
            ZonedDateTime.now());

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        List<Field> modelFields = List.of(validModel.getClass().getDeclaredFields());
        List<Model1> model1List = new ArrayList<>(modelFields.size());
        for (Field modelField : modelFields) {
            Model1 model1 = ObjectDeepCopyProvider.getDeepCopyOrThrow(validModel);
            if (!modelField.getType().isPrimitive()) {
                ReflectionUtil.setValue(modelField, model1, null);
            }
            model1List.add(model1);
        }
        return model1List.stream().map(Arguments::of);
    }

}
