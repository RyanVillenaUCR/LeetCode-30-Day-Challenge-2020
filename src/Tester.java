import java.util.ArrayList;
import java.util.List;

public class Tester {
	
	public static String testResultString(String input, String expectedOutput, String actualOutput) {
		
		return "Testing input " + input + ", expecting " + expectedOutput + ": "
				+ (expectedOutput.equals(actualOutput) ?
						"PASS" : 
						"FAIL, got " + actualOutput);
	}
	
	public static void runTests() {
		
		List<Testable> tests = new ArrayList<Testable>();
		
		// Select problems to test here
		tests.add(new Day03MaximumSubarray());
		tests.add(new Day04MoveZeroes());
		tests.add(new Day05BestTimeBuySellStockII());
		tests.add(new Day06GroupAnagrams());
		tests.add(new Day07CountNumbers());
		
		for (Testable testMe : tests) {
			
			System.out.println("===" + testMe.getClass().getSimpleName() + "===");
			System.out.println(testMe.runTests() ?
				testMe.getClass().getSimpleName() + " passed all tests!\n" :
				testMe.getClass().getSimpleName() + " failed testing...\n");
		}
			
	}
	
}
