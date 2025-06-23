package io.github.mackzwellz.modelfuzzer.base.definitions;

import lombok.Data;

import java.lang.reflect.Type;

@Data
public class CaseDefinition<T> {

    CaseDescription caseDescription;
    Type modelType;
    T modelValue;
}
