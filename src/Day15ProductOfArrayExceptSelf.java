import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;

public class Day15ProductOfArrayExceptSelf implements Testable {

    public int[] productExceptSelf(int[] nums) {
    	
    	if (nums.length == 0)
    		return new int[] {};
    	else if (nums.length == 1)
    		return new int[] { nums[0] };
        
    	int[] output = new int[nums.length];
    	
    	// Pass through output, populating with lefts
    	output[0] = 1;
    	for (int i = 1; i < nums.length; i++)
    		output[i] = output[i-1] * nums[i-1];
    	
    	// Now, multiply each element with rights
    	int rightProd = nums[nums.length - 1];
    	for (int i = nums.length - 2; i >= 0; i--) {	// skip the last element
    		
    		output[i] *= rightProd;
    		rightProd *= nums[i];
    	}
    		
    	return output;
    }
	
	@Override
	public boolean runTests() {
		
		Map<int[], int[]> testCases = new HashMap<int[], int[]>();
		testCases.put(new int[] { 1,2,3,4 }, new int[] { 24,12,8,6 });
		testCases.put(new int[] { 2,4,6,8 }, new int[] { 192,96,64,48 });
		
		boolean allPass = true;
		
		for (Map.Entry<int[], int[]> entry : testCases.entrySet()) {
			
			int[] result = productExceptSelf(entry.getKey());
			
			if (!Arrays.equals(result, entry.getValue())) allPass = false;
			
			System.out.println(Tester.testResultString(
					Arrays.toString(entry.getKey()),	// input
					Arrays.toString(entry.getValue()),	// expected output
					Arrays.toString(result)));			// actual output
		}
		
		// TODO Auto-generated method stub
		return allPass;
	}

}
