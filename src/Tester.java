import java.util.ArrayList;
import java.util.List;

public class Tester {

    public static final Integer LOWEST_TEST = 1;
    public static final Integer HIGHEST_TEST = 17;
	
	public static String testResultString(String input, String expectedOutput, String actualOutput) {
		
		return "Testing input " + input + ", expecting " + expectedOutput + ": "
				+ (expectedOutput.equals(actualOutput) ?
						"PASS" : 
						"FAIL, got " + actualOutput);
	}
	
	private static List<Testable> chooseTests(String[] args) {
		
		List<Testable> tests = new ArrayList<Testable>();
		
		for (String arg : args) {
			
			Integer testNumber = null;
			
			try {
				
				testNumber = Integer.parseInt(arg);
				
			} catch (NumberFormatException e) {

                System.out.println("Input \"" + arg + "\" isn't an integer. Skipping that one.\n");
				continue;
			}
			
			if (testNumber != null
				&& LOWEST_TEST <= testNumber
				&& testNumber <= HIGHEST_TEST) {
				
				switch (testNumber) {
				
				case 1:
					tests.add(new Day01SingleNumber());
                    break;
                case 2:
                    tests.add(new Day02HappyNumber());
                    break;
                case 3:
                    tests.add(new Day03MaximumSubarray());
                    break;
                case 4:
                    tests.add(new Day04MoveZeroes());
                    break;
                case 5:
                    tests.add(new Day05BestTimeBuySellStockII());
                    break;
                case 6:
                    tests.add(new Day06GroupAnagrams());
                    break;
                case 7:
                    tests.add(new Day07CountingElements());
                    break;
                case 8:
                	tests.add(new Day08MiddleOfTheLinkedList());
                	break;
                case 9:
                	tests.add(new Day09BackspaceStringCompare());
                	break;
                case 10:
                	tests.add(new Day10MinStack());
                	break;
                case 11:
                	tests.add(new Day11DiameterOfBinaryTree());
                	break;
                case 12:
                	tests.add(new Day12LastStoneWeight());
                	break;
                case 13:
                	tests.add(new Day13ContiguousArray());
                	break;
                case 14:
                	tests.add(new Day14PerformStringShifts());
                	break;
                case 15:
                	tests.add(new Day15ProductOfArrayExceptSelf());
                	break;
                case 16:
                    tests.add(new Day16ValidParenthesisString());
                    break;
                case 17:
                    tests.add(new Day17NumberOfIslands());
                    break;
                default:
                    assert(LOWEST_TEST <= testNumber
                        && testNumber <= HIGHEST_TEST)
                        : "Did you forget to change LOWEST_TEST or HIGHEST_TEST, Ryan?";
                    break;
				}
			} else {
                System.out.println("Input " + arg
                    + " is outside of the range ["
                    + LOWEST_TEST + ", " + HIGHEST_TEST
                    + "]. Skipping that one.\n");
            }
		}

        return tests;
	}
	
	public static void runTests(String[] args) {
		
		List<Testable> tests = new ArrayList<Testable>();
		
		// If no args given,
		// test all
		if (args == null || args.length == 0) {

            List<String> strings = new ArrayList<String>();
            for (int i = LOWEST_TEST; i <= HIGHEST_TEST; i++)
                strings.add(Integer.toString(i));

            tests.addAll(chooseTests(strings.toArray(new String[0])));
		}
		
		// Else, just test those selected
        else
            tests.addAll(chooseTests(args));
		

		
		for (Testable testMe : tests) {
			
			System.out.println("===" + testMe.getClass().getSimpleName() + "===");
			System.out.println(testMe.runTests() ?
				testMe.getClass().getSimpleName() + " passed all tests!\n" :
				testMe.getClass().getSimpleName() + " failed testing...\n");
		}
			
	}
	
}
