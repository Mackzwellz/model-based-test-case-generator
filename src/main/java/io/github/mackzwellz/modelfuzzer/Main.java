package io.github.mackzwellz.modelfuzzer;

public class Main {

    // use Junit dataprovider +

    // construct 1 or multiple cases with all valid fields +
    // construct 1 case per field where it is null if data type is nullable +
    // construct 1 case per field where it is empty if data type allows it

    // construct at least 1 case per field where value is out of usual distribution for data type (e.g. integer over/underflow)
    // construct at least 1 case per field where value is in usual distribution but not allowed by api (e.g. only range of 0..100 is allowed)

    // for Date/time type, add providers for past/current/future date generation

    // construct 1 case per field where field is not present in JSON
    // construct 1 case per field where field is present but has no value in JSON

    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}