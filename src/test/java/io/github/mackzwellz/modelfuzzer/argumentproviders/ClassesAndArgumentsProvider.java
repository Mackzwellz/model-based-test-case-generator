package io.github.mackzwellz.modelfuzzer.argumentproviders;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.AnnotationBasedArgumentsProvider;
import org.junit.jupiter.params.provider.Arguments;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

class ClassesAndArgumentsProvider extends AnnotationBasedArgumentsProvider<ClassesAndArguments> {

    @Override
    protected Stream<? extends Arguments> provideArguments(ExtensionContext context, ClassesAndArguments valueSource) {
        Object[] arguments = getArgumentsFromSource(valueSource);
        return Arrays.stream(arguments).map(Arguments::of);
    }

    protected Object[] getArgumentsFromSource(ClassesAndArguments valueSource) {
        // @formatter:off
            List<Object> arrays =
                    Stream.of(
                            valueSource.providers(),
                            valueSource.modelClasses()
                            )
                            //.filter(array -> Array.getLength(array) > 0)
                            .collect(toList());
            // @formatter:on

//        Preconditions.condition(arrays.size() == 1, () -> "Exactly one type of input must be provided in the @"
//                + ClassesAndArguments.class.getSimpleName() + " annotation, but there were " + arrays.size());

        Object originalArray = arrays.getFirst();
        return IntStream.range(0, Array.getLength(originalArray)) //
                .mapToObj(index -> Array.get(originalArray, index)) //
                .toArray();
    }

}
