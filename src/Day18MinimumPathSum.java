import java.util.Map;
import java.util.Arrays;
import java.util.HashMap;

public class Day18MinimumPathSum implements Testable {

    private int[][] generateWeights(int[][] grid) {
        
        if (grid.length == 0)
            return new int[][] {};
        
        int[][] newGrid = new int[grid.length][];
        for (int i = 0; i < grid.length; i++) {
            newGrid[i] = new int[grid[0].length];
            
        }
        
        return newGrid;
    }
    
    public int minPathSum(int[][] grid) {
        
        int[][] weights = generateWeights(grid);
        
        
    }
    
    
    private String int2DArrToString(int[][] arr) {
        
        StringBuilder sb = new StringBuilder();
        for (int[] thisArr : arr)
            sb.append(Arrays.toString(thisArr) + "\n");
        
        return sb.toString();
    }
    
    @Override
    public boolean runTests() {
        
        Map<int[][], Integer> testCases = new HashMap<int[][], Integer>();
        testCases.put(new int[][] {
            new int[] { 1,3,1 },
            new int[] { 1,5,1 },
            new int[] { 4,2,1 }
        }, 7);
        
        boolean allPass = true;
        
        for (Map.Entry<int[][], Integer> entry : testCases.entrySet()) {
            
            int result = minPathSum(entry.getKey());
            
            if (result != entry.getValue()) allPass = false;
            
            System.out.println(Tester.testResultString(
                    int2DArrToString(entry.getKey()),   // input
                    Integer.toString(entry.getValue()), // expected output
                    Integer.toString(result)));         // actual output
        }
        
        return allPass;
    }

}
