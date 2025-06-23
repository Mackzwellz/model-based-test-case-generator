package io.github.mackzwellz.modelfuzzer.base.utils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class FieldExtractor {

    public static Stream<Field> getMatching(Class<?> clazz, Predicate<Field> predicate) {
        return Arrays.stream(clazz.getDeclaredFields())
                .filter(predicate);
    }

    public static Stream<Field> getNullable(Class<?> clazz) {
        return getMatching(clazz, field -> !field.getType().isPrimitive());
    }

    public static Stream<Field> getByFieldNames(Class<?> clazz, Collection<String> names) {
        return getMatching(clazz, field -> names.stream()
                .anyMatch(n -> field.getName().equals(n)));
    }

    // get initialized with defaults (boolean, int, ...)

    // get null by default (initialized with null)

    // get empty by default (initialized with empty collection, empty string)

    //TODO write criteria for recursion until basic java types found (check AssertJ?)

}
