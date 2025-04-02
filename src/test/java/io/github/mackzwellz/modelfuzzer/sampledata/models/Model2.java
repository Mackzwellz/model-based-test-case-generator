package io.github.mackzwellz.modelfuzzer.sampledata.models;

import io.github.mackzwellz.modelfuzzer.sampledata.enums.Enum2;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Model2 extends Model1 {

    Object object;
    List<Model1> model1List;
    List<List<String>> listOfLists;
    Map<Enum2, Model1> model1Map;

}

