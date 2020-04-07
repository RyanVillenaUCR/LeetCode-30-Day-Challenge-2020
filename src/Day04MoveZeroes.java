import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Day04MoveZeroes implements Testable {

    public void moveZeroes(int[] nums) {
        
        int numWalker = 0;
        int zeroes = 0;
        
        for (int arrWalker = 0; arrWalker < nums.length; arrWalker++) {
            
            // Shift nums to wherever numWalker is
            if (nums[arrWalker] != 0) {
                
                nums[numWalker] = nums[arrWalker];
                numWalker++;
            }
            
            // Else, keep track of zeroes for when we overwrite them later
            else zeroes++;
        }
        
        for (int zeroWalker = nums.length - 1; zeroWalker >= nums.length - zeroes; zeroWalker--)
            nums[zeroWalker] = 0;
    }
	
	@Override
	public boolean runTests() {
		// TODO Auto-generated method stub
		Map<int[], int[]> testCases = new HashMap<int[], int[]>();
		testCases.put(new int[] {0,1,0,3,12}, new int[] {1,3,12,0,0});
		
		boolean allPass = true;
		
		for (Map.Entry<int[], int[]> entry : testCases.entrySet()) {
			
			// moveZeroes works in place,
			// so use a copy of it instead of modifying the original in testCases
			int[] inputCopy = Arrays.copyOf(entry.getKey(), entry.getKey().length);
			moveZeroes(inputCopy);
			
			if (!Arrays.equals(inputCopy, entry.getValue())) allPass = false;
			
			System.out.println(Tester.testResultString(
					Arrays.toString(entry.getKey()),	// input
					Arrays.toString(entry.getValue()),	// expected output
					Arrays.toString(inputCopy)));		// actual output
		}
		
		return allPass;
	}

}
