package io.github.mackzwellz.modelfuzzer.argumentproviders;

import io.github.mackzwellz.modelfuzzer.sampledata.enums.Enum1;
import io.github.mackzwellz.modelfuzzer.sampledata.enums.Enum2;
import io.github.mackzwellz.modelfuzzer.sampledata.models.Model1;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.time.ZonedDateTime;
import java.util.stream.Stream;

public class SimpleArgumentsProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(
                //null object
//                Arguments.of(null),
                //all fields are null
                Arguments.of(new Model1()),
                // all fields have valid values
                Arguments.of(new Model1(
                        true, false,
                        1, 0,
                        "stringvalue",
                        Enum1.ONE, Enum2.PRIMITIVE,
                        ZonedDateTime.now()))
        );
    }

}
