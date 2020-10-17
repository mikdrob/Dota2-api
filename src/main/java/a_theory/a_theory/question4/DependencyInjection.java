package a_theory.a_theory.question4;

public class DependencyInjection {

    //todo
    // One of the reasons we use Spring is that it gives us good support for
    // Dependency Injection (DI) and Inversion of Control (IoC)

    //todo p1
    // In your words (do not use wiki definitions)
    // What is Dependency Injection?
    // Answer: in general it is when we get object from another object e.g. a class has dependency on another class

    //todo p2
    // Bring example from your code of Dependency Injection.
    // @Service
    //  public class DataQuery  {
    //    RestTemplate restTemplate = new RestTemplate();
    //    public DotaResponse dataQuery(String playerId){...}}

    //todo p3
    // Name 2 benefits of Dependency Injection
    // 1 Classes can pick up logic for creating "settings" objects from outside
    // so we don't violate "Single Responsibility Principle"
    // 2 we can mock interactions and pretend something happens to test it out

    //todo p4
    // Name 1 disadvantage of Dependency Injection
    // 1 Runtime type resolving reduces performance (but only a bit). Real drawbacks are out there if we configure it
    // manually but if a framework is in use then it all happens automatically and there is not much to say about cons
}
