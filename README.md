## AssertJ examples

The **master** branch contains examples with the latest released version of AssertJ modules => you should be able to build it with `mvn install` command.

There are several branches that contain examples for the ongoing development versions of AssertJ modules. That means you have to build the according AssertJ module by your own before new features compile. The following table contains these special branches that are merged into the master each time the module is released.

| Branch                        | Modules                                    |
| ----------------------------- | ------------------------------------------ |
| with-latest-snapshot-versions | Core + all modules without specific branch |
| with-latest-swing-snapshot    | Swing                                      |

### AssertJ assertions examples

**assertj-examples** contains executable AssertJ assertions examples that you can run as JUnit tests.
Please have a look at **[assertions examples sources](assertions-examples/src/test/java/org/assertj/examples)**.

### AssertJ-Swing examples

**assertj-swing-aut** contains the AUT (application under test) that is tested by the examples.

**assertj-swing-junit-examples** contains executable AssertJ-Swing examples that you can inspect and run as JUnit tests.
Please have a look at **[swing-junit-examples sources](assertj-swing-junit-examples/src/test/java/org/assertj/swing/junit/examples)**.

**assertj-swing-testng-examples** contains executable AssertJ-Swing examples that you can inspect and run as TestNG tests.
Please have a look at **[swing-testng-examples sources](assertj-swing-testng-examples/src/test/java/org/assertj/swing/testng/examples)**.


## Contributing

Contributing is easy, only two rules to follow : 
* Use **[AssertJ code Eclipse formatting preferences](https://github.com/joel-costigliola/assertj-core/blob/master/src/ide-support/assertj-eclipse-formatter.xml)** (for Idea users, it is possible to import it)
* Add FUN examples ! ;-)

Thanks !
