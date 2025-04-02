package io.github.mackzwellz.modelfuzzer.testutils;

import java.lang.reflect.Field;

public class ReflectionUtil {

    public static void setValue(Field fieldToUpdate, Object objectToUpdate, Object valueToSet) {
        Object oldValue = null;
        try {
            fieldToUpdate.setAccessible(true);
            oldValue = fieldToUpdate.get(objectToUpdate);
            fieldToUpdate.set(objectToUpdate, valueToSet);
        } catch (IllegalAccessException e) {
            System.err.println(String.format("Couldn't set field %s to have value %s. Keeping value %s.", fieldToUpdate, null, oldValue));
            throw new RuntimeException(e);
        }
    }

    public static Object getValue(Field fieldToGet, Object fromObject) {
        try {
            fieldToGet.setAccessible(true);
            return fieldToGet.get(fromObject);
        } catch (IllegalAccessException e) {
            System.err.println(String.format("Couldn't get value of field %s", fieldToGet));
            throw new RuntimeException(e);
        }
    }

}
