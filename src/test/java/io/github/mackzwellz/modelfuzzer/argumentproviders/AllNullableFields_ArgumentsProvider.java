package io.github.mackzwellz.modelfuzzer.argumentproviders;

import io.github.mackzwellz.modelfuzzer.sampledata.models.Model1;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Stream;

public class AllNullableFields_ArgumentsProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        List<Field> modelFields = List.of(Model1.class.getDeclaredFields());
        return modelFields.stream()
                .filter(f -> !f.getType().isPrimitive())
                .map(Arguments::of);
    }

}
