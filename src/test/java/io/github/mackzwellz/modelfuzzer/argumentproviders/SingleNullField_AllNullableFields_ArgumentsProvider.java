package io.github.mackzwellz.modelfuzzer.argumentproviders;

import io.github.mackzwellz.modelfuzzer.sampledata.enums.Enum1;
import io.github.mackzwellz.modelfuzzer.sampledata.enums.Enum2;
import io.github.mackzwellz.modelfuzzer.sampledata.models.Model1;
import io.github.mackzwellz.modelfuzzer.testutils.ObjectDeepCopyProvider;
import io.github.mackzwellz.modelfuzzer.testutils.ReflectionUtil;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.lang.reflect.Field;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class SingleNullField_AllNullableFields_ArgumentsProvider implements ArgumentsProvider {

    private static final Model1 validModel = new Model1(
            true, false,
            1, 0,
            "stringvalue",
            Enum1.ONE, Enum2.PRIMITIVE,
            ZonedDateTime.now());

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        List<Field> modelFields = List.of(validModel.getClass().getDeclaredFields());
        List<Pair<Field, Model1>> model1List = new ArrayList<>();
        for (Field modelField : modelFields) {
            if (!modelField.getType().isPrimitive()) {
                Model1 model1 = ObjectDeepCopyProvider.getDeepCopyOrThrow(validModel);
                ReflectionUtil.setValue(modelField, model1, null);
                model1List.add(Pair.of(modelField, model1));
            }
        }
        return model1List.stream().map(Arguments::of);
    }

}
