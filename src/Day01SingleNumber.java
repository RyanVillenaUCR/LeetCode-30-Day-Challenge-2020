import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Day01SingleNumber implements Testable {

    private int noMemSingleNumber(int[] nums) {
        
        int x = 0;
        
        for (int num : nums)
            x ^= num;
        
        return x;
    }
    
    private Integer memSingleNumber(int[] nums) {
        
        Set<Integer> history = new HashSet<Integer>();
        
        for (int num : nums) {
            
            if (history.contains(num))
                history.remove(num);
            
            else 
                history.add(num);
        }
        
        return history.iterator().next();
    }
    
    public int singleNumber(int[] nums) {
        
        return noMemSingleNumber(nums);
        // return memSingleNumber(nums);
    }
	
	@Override
	public boolean runTests() {
		
		Map<int[], Integer> testCases = new HashMap<int[], Integer>();
		testCases.put(new int[] {2,2,1}, 1);
		testCases.put(new int[] {4,1,2,1,2}, 4);
		
		boolean allPass = true;
		
		for (Map.Entry<int[], Integer> entry : testCases.entrySet()) {
			
			int result = singleNumber(entry.getKey());
			
			if (result != entry.getValue()) allPass = false;
			
			System.out.println(Tester.testResultString(
					Arrays.toString(entry.getKey()),		// input
					Integer.toString(entry.getValue()),		// expected output
					Integer.toString(result)));				// actual output
		}
		
		return allPass;
	}

}
