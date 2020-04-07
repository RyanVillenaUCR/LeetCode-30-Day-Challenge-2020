import java.util.ArrayList;
import java.util.List;

public class Tester {
	
	public static void runTests() {
		
		List<Testable> tests = new ArrayList<Testable>();
		
		// Select problems to test here
		tests.add(new Day07CountNumbers());
		
		for (Testable testMe : tests) {
			
			System.out.println("===" + testMe.getClass().getSimpleName() + "===");
			System.out.println(testMe.runTests() ?
				testMe.getClass().getSimpleName() + " passed all tests!\n" :
				testMe.getClass().getSimpleName() + " failed testing...\n");
		}
			
	}
	
}
