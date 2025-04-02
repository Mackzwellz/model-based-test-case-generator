package io.github.mackzwellz.modelfuzzer.sampledata.models;

import io.github.mackzwellz.modelfuzzer.sampledata.enums.Enum1;
import io.github.mackzwellz.modelfuzzer.sampledata.enums.Enum2;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@JsonIncludeProperties
public class Model1 {

    boolean primitiveBoolean;
    Boolean nullableBoolean;

    int primitiveInteger;
    Integer nullableInteger;

    String string;

    Enum1 enum1;
    Enum2 enum2;

    ZonedDateTime zonedDateTime;
}
