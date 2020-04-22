import java.util.Map;
import java.util.Arrays;
import java.util.HashMap;

public class Day18MinimumPathSum implements Testable {

    public int minPathSum(int[][] grid) {
        
        if (grid.length == 0 || grid[0].length == 0)
            return 0;
        
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[i].length; j++) {
                
                // Top row, excluding 0,0
                if (i == 0 && j > 0) 
                    
                    grid[i][j] = grid[i][j] + grid[i][j - 1];
                
                else if (j == 0 && i > 0)
                    
                     grid[i][j] = grid[i][j] + grid[i - 1][j];
                
                else if (i != 0 && j != 0)
                     grid[i][j] = grid[i][j] + Math.min(grid[i][j - 1], grid[i - 1][j]);
            }
        }
        return grid[grid.length - 1][grid[0].length - 1];
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
        testCases.put(new int[][] {
            new int[] { 1,3,1,4 },
            new int[] { 1,2,5,1 },
            new int[] { 4,2,7,1 }
        }, 11);
        testCases.put(new int[][] {
            new int[] { 69 }
        }, 69);
        testCases.put(new int[][]
            {}
        , 0);
        testCases.put(new int[][] {
            new int[] {7,1,3,5,8,9,9,2,1,9,0,8,3,1,6,6,9,5},
            new int[] {9,5,9,4,0,4,8,8,9,5,7,3,6,6,6,9,1,6},
            new int[] {8,2,9,1,3,1,9,7,2,5,3,1,2,4,8,2,8,8},
            new int[] {6,7,9,8,4,8,3,0,4,0,9,6,6,0,0,5,1,4},
            new int[] {7,1,3,1,8,8,3,1,2,1,5,0,2,1,9,1,1,4},
            new int[] {9,5,4,3,5,6,1,3,6,4,9,7,0,8,0,3,9,9},
            new int[] {1,4,2,5,8,7,7,0,0,7,1,2,1,2,7,7,7,4},
            new int[] {3,9,7,9,5,8,9,5,6,9,8,8,0,1,4,2,8,2},
            new int[] {1,5,2,2,2,5,6,3,9,3,1,7,9,6,8,6,8,3},
            new int[] {5,7,8,3,8,8,3,9,9,8,1,9,2,5,4,7,7,7},
            new int[] {2,3,2,4,8,5,1,7,2,9,5,2,4,2,9,2,8,7},
            new int[] {0,1,6,1,1,0,0,6,5,4,3,4,3,7,9,6,1,9}
        }, 85);
        
        boolean allPass = true;
        
        for (Map.Entry<int[][], Integer> entry : testCases.entrySet()) {
            
            int result = minPathSum(entry.getKey());
            
            if (result != entry.getValue()) allPass = false;
            
            System.out.println(Tester.testResultString( "\n" +
                    int2DArrToString(entry.getKey()),   // input
                    Integer.toString(entry.getValue()), // expected output
                    Integer.toString(result)));         // actual output
        }
        
        return allPass;
    }

}
