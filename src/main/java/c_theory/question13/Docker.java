package c_theory.question13;

public class Docker {

    //todo A
    // What is a server?
    // Answer: Something (hardware or software) that provides functionality to "client"

    //todo B
    // What is the difference between build server and production server?
    // Answer: production server can't be accessed as easily as build server
    // e.g. many team member can shutdown build server while they can't do the same with prod one

    //todo C
    // What is docker?
    // Answer: software that packages up code and all its dependencies

    //todo D
    // Name and explain docker container benefits over virtual machine setup (java jar as system process and installed nginx)
    // 1 less management needed. We can run much more applications on one server.
    // It's possible to setup an environment for dev, testing and deployment
    // 2 lightweight - runs faster. Size of snapshots is times lower etc

    //todo E
    // Name and explain docker container drawback over virtual machine setup (java jar as system process and installed nginx)
    // 1 Less known security threats mitigation (kernel exploits, container breakouts etc.)

    //todo F
    // Name and describe tools for docker architecture
    // 1 Ansible. Automates docker tasks (container lifecycle, authentication ...)
    // 2 CircleCI - helps to run or build docker images

    //todo G
    // Name and explain why are companies slow in moving existing systems to docker architecture (do not explain why docker is bad)
    // 1 It's not always obvious why.
    // As a head of IT department person might not see serious issues with vm approach
    // 2 It takes time to migrate. Switching to dynamic environment requires full understand
    // how to handle all components in order. For instance, a company decided to use docker,
    // they did all the testing inside a container and as result faced many issues to fix
    // and the whole process was put on hold
}
