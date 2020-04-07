import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Day03MaximumSubarray implements Testable {

    public int maxSubArray(int[] nums) {
        
        int currentSum = nums[0];
        int maxSum     = nums[0];
        
        for (int i = 1; i < nums.length; i++) {
            
            currentSum = Math.max(nums[i], currentSum + nums[i]);
            maxSum = Math.max(currentSum, maxSum);
        }
        
        return maxSum;
    }
	
	@Override
	public boolean runTests() {
		
		Map<int[], Integer> testCases = new HashMap<int[], Integer>();
		testCases.put(new int[] {-2,1,-3,4,-1,2,1,-5,4}, 6);
		
		boolean allPass = true;

		for (Map.Entry<int[], Integer> entry : testCases.entrySet()) {
			
			int result = maxSubArray(entry.getKey());
			
			if (result != entry.getValue()) allPass = false;
			
			System.out.println(Tester.testResultString(
					Arrays.toString(entry.getKey()),		// input
					Integer.toString(entry.getValue()),		// expected output
					Integer.toString(result)));				// actual output
		}
		
		return allPass;
	}

}
