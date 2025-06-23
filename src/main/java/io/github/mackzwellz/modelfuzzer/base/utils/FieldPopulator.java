package io.github.mackzwellz.modelfuzzer.base.utils;

import java.lang.reflect.Field;
import java.util.Collection;

public class FieldPopulator {

    public static <ObjectType, ValueType> ObjectType setFieldsToBe(ObjectType object, Collection<Field> fields,
                                                                 ValueType value) {
        for (Field field : fields) {
            ReflectionUtil.setValue(field, object, value);
        }
        return object;
    }
}
