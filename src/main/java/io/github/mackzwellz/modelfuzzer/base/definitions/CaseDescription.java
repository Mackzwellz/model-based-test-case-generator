package io.github.mackzwellz.modelfuzzer.base.definitions;


//@Data
public record CaseDescription() {

    static String caseName;
    static FieldNameState fieldNameState;
    static FieldTypeState fieldTypeState;
    static FieldValueState fieldValueState;

    @Override
    public String toString() {
        return String.format("Case Description [%s]: field %s, type %s, value %s",
                caseName, fieldNameState, fieldTypeState, fieldValueState);
    }
}
