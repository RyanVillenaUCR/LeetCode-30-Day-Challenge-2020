To add a test case, always make sure to do the following:

* Make a class implementing the Testable interface
* Use the same naming conventions as the first 7 days
* When implementing runTests()
  * Use a Map to store testcases
    * See Day07CountingElements.runTests() for a good example
  * Display test results using Tester.testResultString()
* Increment Tester.HIGHEST_TEST
* Add the class to the switch statement in Tester.chooseTests()
* Actually test and make sure it passes!
* Update the README.md with the name and link to the problem
