package io.github.mackzwellz.modelfuzzer.base.definitions;

public enum FieldValueState {
    VALID_VALUE, // "fieldName": "value"
    INVALID_VALUE, // "fieldName": "vl"
    EMPTY_VALUE, // "fieldName": ""
    NULL_VALUE, // "fieldName": null
    NO_VALUE, // "fieldName":
    OOB_VALUE, // "fieldName": MAX_INTEGER+1
}
