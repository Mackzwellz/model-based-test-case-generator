package io.github.mackzwellz.modelfuzzer.base.definitions;

public enum FieldNameState {
    VALID_NAME, // "fieldName": value
    INVALID_NAME, // "fielNm": value
    EMPTY_NAME, // "": value
    NULL_NAME, // null: value
    NO_NAME, // : value
}
