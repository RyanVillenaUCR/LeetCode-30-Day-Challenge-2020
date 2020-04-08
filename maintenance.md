To add a test case, always make sure to do the following:

* Make a class implementing the Testable interface
* Use the existing naming conventions for the new class
* When implementing runTests():
  * Use a Map to store testcases
    * See [Day07CountingElements.runTests()](https://github.com/RyanVillenaUCR/LeetCode-30-Day-Challenge-2020/blob/39fc9c40805281c54af6171ba05e9ebfedfd8d58/src/Day07CountingElements.java#L27) for a good example
  * Display test results using [Tester.testResultString()](https://github.com/RyanVillenaUCR/LeetCode-30-Day-Challenge-2020/blob/39fc9c40805281c54af6171ba05e9ebfedfd8d58/src/Tester.java#L9)
* Increment [Tester.HIGHEST_TEST](https://github.com/RyanVillenaUCR/LeetCode-30-Day-Challenge-2020/blob/39fc9c40805281c54af6171ba05e9ebfedfd8d58/src/Tester.java#L7)
* Add the class to the [switch statement in Tester.chooseTests()](https://github.com/RyanVillenaUCR/LeetCode-30-Day-Challenge-2020/blob/39fc9c40805281c54af6171ba05e9ebfedfd8d58/src/Tester.java#L39)
* Actually test and make sure it passes!
* Update the README.md with the name and link to the problem

_Note that the permalinks here link to an [earlier commit](https://github.com/RyanVillenaUCR/LeetCode-30-Day-Challenge-2020/tree/39fc9c40805281c54af6171ba05e9ebfedfd8d58) with out-of-date line numbers. Of course, please use the most recent commit as your first reference._
