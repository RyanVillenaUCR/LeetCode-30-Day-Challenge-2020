import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

public class Day22SubarraySumEqualsK implements Testable {
    
    // Solution taken from:
    // https://leetcode.com/problems/subarray-sum-equals-k/solution/
    public int subarraySum(int[] nums, int k) {
        
        int count = 0,
            sum = 0;
        
        HashMap<Integer,Integer> sumOccurrences = new HashMap<Integer, Integer>();
        sumOccurrences.put(0, 1);
        
        for (int i = 0; i < nums.length; i++) {
            
            // Keep a running sum
            sum += nums[i];
            
            // If the sum (sum - k) occurs,
            // add its number of occurrences to count
            if (sumOccurrences.containsKey(sum - k))
                count += sumOccurrences.get(sum - k);
            
            // Regardless of whether not it was sum - k,
            // record the current sum into the map
            sumOccurrences.put(sum, sumOccurrences.getOrDefault(sum, 0) + 1);
        }
        
        return count;
    }
    
    
    
    
    
    private class TestCase {
        
        public TestCase(int[] nums, int k) {
            
            this.nums = nums;
            this.k = k;
        }
        
        @Override
        public String toString() {
            
            return "{" + Arrays.toString(nums) + ", " + Integer.toString(k) + "}";
        }
        
        @Override
        public boolean equals(Object other) {
            
            if (!(other instanceof TestCase)) return false;
            TestCase otherTC = (TestCase) other;
            return Arrays.equals(this.nums, otherTC.nums) && this.k == otherTC.k; 
        }
        
        @Override
        public int hashCode() {
            
            return this.toString().hashCode();
        }
        
        int[] nums;
        int k;
    }
    
    @Override
    public boolean runTests() {
        
        Map<TestCase, Integer> testCases = new HashMap<TestCase, Integer>();
        testCases.put(new TestCase(
                new int[] { 1,1,1 }, 2),
                2);
        testCases.put(new TestCase(
                new int[] { 1,1,1,2,1,1,2,1,1,2,0,0,1,1 }, 2),
                12);
        testCases.put(new TestCase(
                new int[] { 1,0,1,2 }, 3),
                2);
        testCases.put(new TestCase(
                new int[] { }, 69),
                0);
        testCases.put(new TestCase(
                new int[] { 420 }, 420),
                1);
        
        boolean allPass = true;
        
        for (Map.Entry<TestCase, Integer> entry : testCases.entrySet()) {
            
            int[] thisNums = entry.getKey().nums;
            int thisK = entry.getKey().k;
            int result = subarraySum(thisNums, thisK);
            
            if (result != entry.getValue()) allPass = false;
            
            System.out.println(Tester.testResultString(
                    entry.getKey().toString(),          // input
                    Integer.toString(entry.getValue()), // expected output
                    Integer.toString(result)));         // actual output
        }
        
        // TODO Auto-generated method stub
        return allPass;
    }

}
