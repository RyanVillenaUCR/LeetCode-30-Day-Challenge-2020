import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Day13ContiguousArray implements Testable {

    public int findMaxLength(int[] nums) {
        
        // +1 for 1, -1 for 0
        int sum = 0;
        int maxSpan = 0;
        
        // Maps sums to their first occurrence (an index)
        Map<Integer, Integer> leftEndpoints = new HashMap<Integer, Integer>();
        leftEndpoints.put(0, -1);	// Off-by-one things will skip this case otherwise
        
        for (int i = 0; i < nums.length; i++) {
        	
        	// sum++ for 1, sum-- for 0
        	sum += nums[i] == 0 ? -1 : 1;
        	
        	// If we've seen this sum before,
        	// increase its span,
        	// and possibly update maxSpan
        	if (leftEndpoints.containsKey(sum))
        		
        		maxSpan = Math.max(maxSpan,
        				i - leftEndpoints.get(sum)); // this is the span of the current interval
        	
        	else
        		leftEndpoints.put(sum, i);
        }
        
        return maxSpan;
    }
	
	@Override
	public boolean runTests() {
		
		Map<int[], Integer> testCases = new HashMap<int[], Integer>();
		testCases.put(new int[] { 0,1 }, 2);
		testCases.put(new int[] { 0,1,0 }, 2);
		testCases.put(new int[] { 0 }, 0);
		testCases.put(new int[] { 1 }, 0);
		testCases.put(new int[] { 1,0 }, 2);
		testCases.put(new int[] { 0,0,1,0,0,0,1,1 }, 6);
		testCases.put(new int[] { 1,1,1,0,0,1,0 }, 6);
		testCases.put(new int[] {}, 0);
		
		boolean allPass = true;
		
		for (Map.Entry<int[], Integer> entry : testCases.entrySet()) {
			
			Integer result = findMaxLength(entry.getKey());
			
			System.out.println(Tester.testResultString(
					Arrays.toString(entry.getKey()),	// input
					Integer.toString(entry.getValue()),	// expected output
					Integer.toString(result)));			// actual output
			
			if (entry.getValue() != result) allPass = false;
		}
		
		return allPass;
	}

}
