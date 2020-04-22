import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Day19SearchinRotatedSortedArray implements Testable {

    public int search(int[] nums, int target) {
        
        int len = nums.length;
        if (len == 0)
            return -1;
        
        else if (len == 1)
            return nums[0] == target ? 0 : -1;
        
        
        
        int mid,
            left  = 0,
            right = len - 1;
        
        mid = (left + right) / 2;
        
        // Once left and right are not aligned,
        // it's not here, so return -1
        while (left <= right) {
            
            // If sorted as usual
            if (nums[left] <= nums[mid]) {
                
                // Check left boundary
                if (nums[left] == target)
                    return left;
                
                // If target is somewhere in the left half,
                // shift mid
                else if (nums[left] < target && nums[mid] >= target)
                    mid = (left + mid) / 2;
                
                // Else, target is somewhere in the right half,
                // so shift left and shift mid
                else {
                    left = mid + 1;
                    mid = (left + right) / 2;
                }
            }
            
            // If left side is unsorted, that means the pivot is here,
            // so shift mid
            else {
                mid = (left + mid) / 2;
            }
        }
        
        
        return -1;
    }
    
    
    
    private class TestCase {
        
        public TestCase(int[] nums, int target) {
            
            this.nums = nums;
            this.target = target;
        }
        
        @Override
        public String toString() {
            
            return "{" + Arrays.toString(nums) + ", " + Integer.toString(target) + "}";
        }
        
        @Override
        public boolean equals(Object other) {
            
            if (!(other instanceof TestCase)) return false;
            
            TestCase otherTC = (TestCase) other;
            
            return Arrays.equals(this.nums, otherTC.nums) && this.target == otherTC.target;
        }
        
        @Override
        public int hashCode() {
            
            return this.toString().hashCode();
        }
        
        private int[] nums;
        private int target;
    }
    
    @Override
    public boolean runTests() {
        
        Map<TestCase, Integer> testCases = new HashMap<TestCase, Integer>();
        testCases.put(new TestCase(
                new int[] { 4,5,6,7,0,1,2 }, 0),    // input
                4);                                 // expected output
        testCases.put(new TestCase(
                new int[] { 4,5,6,7,0,1,2 }, 3),
                -1);
        
        
        boolean allPass = true;
        
        for (Map.Entry<TestCase, Integer> entry : testCases.entrySet()) {
            
            int[] thisNums = entry.getKey().nums;
            int thisTarget = entry.getKey().target;
            int result = search(thisNums, thisTarget);
            
            if (result != entry.getValue()) allPass = false;
            
            System.out.println(Tester.testResultString(
                    entry.getKey().toString(),          // input
                    Integer.toString(entry.getValue()), // expected output
                    Integer.toString(result)));         // actual output
        }
        
        return allPass;
    }

}
