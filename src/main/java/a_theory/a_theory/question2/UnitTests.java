package a_theory.a_theory.question2;

public class UnitTests {

    //todo p1
    // What are unit tests? In your words (don't use wiki definitions)
    // Answer: Kind of testing when we test "units" of software separately. So we know that every single unit
    // works as it is supposed to

    //todo p2
    // Name 2 benefits of unit tests
    // 1 Detection of bugs at an early stage (problems are found before integration)
    // 2 Extra documentation. Teammate can quickly catch up of what is going on by looking at unit tests functionality

    //todo p3
    // Name 1 disadvantage of unit tests
    // 1 It might not cover integration bugs that could be found later as unit tests focus on individual components

    //todo p4
    // Name 2 unit test frameworks or libraries
    // 1 Mockito - mocking frameworks for Java classes
    // 2 Spring test - useful libraries for AT to Spring apps

    //todo p5
    // Would you use mocking with unit tests?
    // Yes/No: yes
    // Why?
    // Testing becomes easier if we simulate external objects, big db etc. In this scenario we can test it without
    // adding real object. e.g. real object sends us some data once a day but we don't want to wait so we simulate
    // it and change parameters.
}
