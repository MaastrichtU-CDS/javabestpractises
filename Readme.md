# This is an example project for Java best practices

## When to use Java in CDS

- You want to write a large project with a considerable amount of custom code.

## When not to use Java in CDS:

- You just want to write a quick script mostly using existing libraries

### Advantages:

- Verbose: Java enforces certain rules, such as explicitly typing every variable. This makes it easier to understand the
  code as it is very easy to see what is going on. It also naturally prevents certain bugs & mistakes from occuring.
    - Short-term this is often annoying, but in the long-term it saves time. Especially when working in a larger
      project, or using someone else's code.
- Focus on professional settings: Java is often used in enterprise enviroments. This means it has a lot of professional
  tooling & libraries that supports the development. Consequently the tooling & libraries are often in a more mature
  state than the tooling available in other languages.
- Performance: Java is faster than R or Python
- Object Oriented: OO is an easy to understand programming paradigm.

### Disadvantages:

- Verbose: Java enforces certain rules. If you just want to write a simple 5-line script there is a lot of boiler-plate
  code.
- Performance: C/C++ is still faster
- Object Oriented: not all tasks benefit from an OO approach.
- Your favorite library might not be available in Java.

## Maven project

In this project we show some of the best practices for a Java maven project. The code in this project is not intended to
be perfectly optimized, it is simply an example of how to write "clean" code and use the various maven plugins to check
the quality of your code.

The pom of this project can be used as the basis of your own maven project. It contains the necessary dependencies to
set up:

1) Unit testing
2) Test coverage
3) Checkstyle checks
4) Dependency checks.

### Dependency checks

The dependency check plugin checks if all dependencies you've declared in your pom are actually used. It will complain
if you include dependencies that are not used. It will also throw an error if you use dependencies implicitly, you
should declare them explicitly.

### Checkstyle checks

The checkstyle plugin checks if the code you've written fulfills the checkstyle rules you have defined. The rules
included in checkstyle.xml are based on a set of commonly accepted rules (https://checkstyle.org/config_coding.html)
This is similar to PEP in python.

The rules contained in this set fall in two categories:

1) Code-style that is pure personal preference (e.g., do I place a space after { or not?)
2) Code-style that is indicative of common bugs (e.g., magic numbers)

It is important to note that these are merely guidelines and that the rules can be suppressed if needed for specific
cases in case you have a false positive. However, you should avoid this if at all possible as the main goal of these
rules is to create consistency. The benefits of these consistencies are worth the inconvenience of a few false
positives. It is also important to realize it is possible to set up most IDEA's to automatically apply as much of the
code-style as possible. Things like the correct amount of spaces around brackets can easily be automated, and this is
far less annoying than needing to this manually.

However, the style you should use in your project is not set in stone; you can change the checkstyle rules if you notice
a certain rule does not work for you. However, you should always be consistent within a given project.

#### Naming conventions

Checkstyle also covers naming conventions. Like with all rules this is open to personal preference although Java does
have some generally used conventions (i.e., classes use PascalCase and methods use camelCase).

However, there are 2 rules that should always hold:

1) Names should be descriptive. Others should be able to understand at a glance what a variable is purely based on its
   name
2) Avoid acronyms if possible. Your IDEA will auto-complete anyway, do not create unreadable names because you want to
   be lazy. This is especially relevant when it's acronyms you've made up yourself. Commonly known domain specific
   acronyms are acceptable.

#### Comments

Although good code is in theory self-explanatory without any extra information, this does not always hold in practise.
Adding in comments to explain what is going on can be very useful.

This can be form of javadoc comments, but it can also be very useful to add in-line comments in bits of code where
you're doing a complicated calculation. This is especially true when using more complex constructs, like recursion or
lambdas which are generally more difficult to read.

### Test coverage

The plugin for test-coverage tests how much of your code base is covered by your unit-tests As a general rule of thumb
people aim for 70-80% coverage. A high coverage is good because it means you will quickly find out if a new change
breaks some old code. However, bear in mind that a 100% coverage is unrealistic and not necessarily useful.

Again it is possible to suppress the coverage for specific classes or even packages, for example in this project the
packages game.gui and game.main are exempt from test coverage. Use this ability sparingly. More on what should be tested
can be found in the Unit-test section.

### Unit-tests

The unit-test plugin allows you to automatically run all your unit-tests when you build the project.

#### What is important to unit-test?

Unit-tests are important to ensure a developer doesn't accidentally break things with an update. However, not every
single line of code is worth writing a unit-test for.

Unit-tests should be written to test specific behaviours, not the implementations of that behaviour. If you test the
implementations then unit-tests will become extremely annoying during refactor-steps.

This means that you do not want to test methods like getters & setters. Nor do you want to test methods like
MainLoop.main that do nothing of significance on their own. You want to test methods that actually do something useful.

This also means that unit-testing a GUI is not particularly useful as a unit-test for the GUI would mostly concern the
implementation (i.e., is the right amount of buttons created?). It is possible to automate GUI-testing, and this is
important for integration-tests, but far less relevant for unit-tests.

This is why these two packages are excluded in this example project. Other examples of classes that are not particularly
interesting to unit-tests independently would be POJO's (Plain Old Java Objects). These classes only contain getters and
setters and should not be explicitly tested. However, they should be implicitly tested as part of a unit-test that test
actual functionality.

# Architecture:

In this project the basics of good architecture can be seen. These principles are not Java specific, but also hold for
other languages. The project is divided into three logical parts:

- The game logic
- The GUI
- The main loop for the program as a whole

These parts have as few dependencies on another as possible. Whenever you write code, you should try to make similar
divisions in your code. This division is important for three main reasons.

First you do not want to create a giant behemoth of a file that is responsible for everything. Dividing things up makes
the project far easier to maintain and work with.

Secondly, by dividing the code into logical subsets you can guarantee the various functions are independent of another.
The game logic should be able to work regardless of how the GUI is implemented.

Third, dividing the code up makes it easier to write small unit-tests. While a single unit-test that covers your entire
code-base can be useful in certain cases, it is often also very useful to have unittests that cover smaller steps in
your code. By testing smaller steps it'll be easier to determine where your error is happening. This is why this project
both has a GameTest, which tests basically everything, and individual unit-tests for the different player variants & the
board itself

Within these logical parts the code is further subdivided. The game-logic contains a class that keeps track of the main
game, a class that keeps track of the state of the board, and a package that implements various types of players.

In the implementation of players you can further see how this division can help you plugin different variants into a
generic framework. The core of players is the Player class, which is abstract, and there are three actual types of
players: HumanPlayer, RandomAi and AlphaBetaAi. Each of these players determines what move to play differently, but
thanks to the shared generic parent class they can be used interchangeably.


