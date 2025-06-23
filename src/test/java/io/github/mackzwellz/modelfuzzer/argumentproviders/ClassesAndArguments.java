package io.github.mackzwellz.modelfuzzer.argumentproviders;

import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@ArgumentsSource(SingleNullField_AllNullableFields_DynamicArgumentsProvider.class)
//changeme ^
public @interface ClassesAndArguments {

    Class<?>[] modelClasses();

    Class<? extends ArgumentsProvider>[] providers();
}
