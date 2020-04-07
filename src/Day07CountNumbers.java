import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Day07CountNumbers implements Testable {

    public static int countElements(int[] arr) {
        
    	Set<Integer> elements = new HashSet<Integer>();
    	for (int num : arr)
    		elements.add(num);
    	
    	int sum = 0;
    	
    	for (int num : arr) {
    		
    		if (elements.contains(num + 1))
    			sum++;
    	}
    	
    	return sum;
    }
    
    public boolean runTests() {
    	
    	Map<int[], Integer> testCases = new HashMap<int[], Integer>();
    	testCases.put(new int[] {1,2,3}, 2);
    	testCases.put(new int[] {1,1,3,3,5,5,7,7}, 0);
    	testCases.put(new int[] {1,3,2,3,5,0}, 3);
    	testCases.put(new int[] {1,1,2,2}, 2);
    	testCases.put(new int[] {1,1,2}, 2);
    	
    	boolean allPass = true;
    	
    	for (Map.Entry<int[], Integer> entry : testCases.entrySet()) {
    		
    		int result = countElements(entry.getKey());
    		
    		System.out.println(Tester.testResultString(
				Arrays.toString(entry.getKey()), 		// input
				Integer.toString(entry.getValue()),		// expected output
				Integer.toString(result)));				// actual output
    		
    		if (result != entry.getValue()) allPass = false;
    	}
    	
    	return allPass;
    }
	
}
