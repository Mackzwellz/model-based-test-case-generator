# Model-Based Test Case Generator

## Selling pitch / solution use cases

- Most basic use case: Test error-handling of your REST API and need to generate possible JSON payloads
    - Input: your model(s) + case generation rule(s)
    - Output: a set of JSON strings, per each generation rule case, per each field of the model

## Features

- Input options
    - Fine-tuning for case generation rules
        - Limit amount of generated cases to a bare useful minimum using pairwise solutions
        - Define custom logic for handling model fields (isRequired, isNullable etc.)
        - Define custom boundary values for model fields to generate cases using EQP / BVA testing techniques
        - Encode business logic to eliminate incompatible field combinations
        - Define and reuse business use cases as set of rules / starting points

- Output options
    - Other output formats (e.g. RPC, GraphQL) are (intended to be) supported!

### How is this tool different from others?

TODO comparison here

### Why another tool if there are existing solutions?

I'm tired of searching for a solution that fits all my needs and use cases, so I'm rawdogging my own. Let's see how it
goes.

#### Things to do

- [x] Implement proof of concept
    - [x] Prepare sample / hardcoded test data
    - [x] Prepare a couple of use cases for generation
    - [x] Add tests for these use cases
        - [x] Use JUnit data providers and parametrized tests
        - [ ] Better naming for test cases
        - [ ] Separate solution logic from test setup
- [ ] Implement generalized solution for POC cases
    - [x] 1 or multiple cases with all valid fields
    - [x] 1 case per field where it is null if data type is nullable (non-primitives)
    - [ ] construct 1 case per field where it is empty if data type allows it (Collection, String)
- [ ] Start fleshing out implementation for other use cases
    - [ ] ModelFieldState and ModelValueState
    - [ ] 1 case per field where value is out of usual distribution for data type (e.g. integer over/underflow)
    - [ ] 1 case per field where value is in usual distribution but not allowed by api (e.g. only range of 0..100 is
      allowed)
    - [ ] for Date/time type, add providers for past/current/future date generation
    - [ ] named cases and support for multiple valid setups
- [ ] Road to MVP
   - [ ] reusable setups
   - [ ] define cases as text (YAML?)
- [ ] Output-dependent cases
    - [ ] 1 case per field where field is not present in JSON
    - [ ] 1 case per field where field is present but has no value in JSON
    - [ ] 1 case per field where field:value are null:null