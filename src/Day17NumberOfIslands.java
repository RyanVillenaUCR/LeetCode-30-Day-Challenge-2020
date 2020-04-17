import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;

public class Day17NumberOfIslands implements Testable {

    public int numIslands(char[][] grid) {
        
        return 69;
    }
    
    private String char2DArrToString(char[][] grid) {
        
        StringBuilder sb = new StringBuilder();
        for (char[] row : grid)
            sb.append(Arrays.toString(row) + "\n");
        
        return sb.toString();
    }
    
    @Override
    public boolean runTests() {
        
        Map<char[][], Integer> testCases = new HashMap<char[][], Integer>();
        testCases.put(new char[][] {
            new char[] { '1','1','1','1','0' },
            new char[] { '1','1','0','1','0' },
            new char[] { '1','1','0','0','0' },
            new char[] { '0','0','0','0','0' }
        }, 1);
        testCases.put(new char[][] {
            new char[] { '1','1','0','0','0' },
            new char[] { '1','1','0','0','0' },
            new char[] { '0','0','1','0','0' },
            new char[] { '0','0','0','1','1' }
        }, 3);
        testCases.put(new char[][] {
            new char[] { '1','1','1','1','1' },
            new char[] { '1','1','0','1','1' },
            new char[] { '1','0','1','0','1' },
            new char[] { '1','1','0','1','1' },
            new char[] { '1','1','1','1','1' }
        }, 2);
        
        
        boolean allPass = true;
        
        for (Map.Entry<char[][], Integer> entry : testCases.entrySet()) {
            
            char[][] inputGrid = entry.getKey();
            Integer result = numIslands(inputGrid);
            
            if (result != entry.getValue()) allPass = false;
            
            System.out.println(Tester.testResultString(
                    "\n" + char2DArrToString(inputGrid),           // input
                    Integer.toString(entry.getValue()),     // expected output
                    Integer.toString(result)));             // actual output
        }
        
        return allPass;
    }

}
