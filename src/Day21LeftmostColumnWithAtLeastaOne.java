import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Day21LeftmostColumnWithAtLeastaOne implements Testable {

    public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        
        List<Integer> dims = binaryMatrix.dimensions();
        int rows = dims.get(0), cols = dims.get(1);
        
        int result = -1;
        int thisRow = 0, thisCol = cols - 1;
        
        while (thisRow < rows && thisCol >= 0) {
            
            if (binaryMatrix.get(thisRow, thisCol) == 1) {
                
                result = thisCol;
                thisCol--;
            }
            
            else
                thisRow++;
        }
        
        return result;
    }
    
    
    
    
    
    
    // NOT guaranteed to be LeetCode's implementation
    // of this class, but my own.
    // I intentionally made this really inefficient,
    // as the point of this question
    // was to optimize an otherwise easy problem.
    // In particular, calling get() is linear, not constant.
    private class BinaryMatrix {
        
        public BinaryMatrix(int[][] arr) {
            
            grid = new LinkedList< List<Integer> >();
            for (int[] row : arr) {
                
                List<Integer> newRow = new LinkedList<Integer>();
                
                for (int element : row) {
                    assert(element == 0 || element == 1) :
                        "You didn't give me a binary matrix,\n" +
                        "You gave me a matrix with element " + Integer.toString(element);
                    newRow.add(element);
                }
                    
                
                grid.add(newRow);
            }
            
            assert(arr.length == grid.size());
            if (arr.length > 0)
                assert(arr[0].length == grid.get(0).size());
        }
        
        public int get(int x, int y) {

            return grid.get(x).get(y);
        }
        
        public List<Integer> dimensions() {
            
            return Arrays.asList(new Integer[] {
                    grid.size(),
                    grid.size() == 0 ? 0 : grid.get(0).size()
            });
        }
                
        @Override
        public String toString() {
            
            StringBuilder sb = new StringBuilder();
            for (List<Integer> row : grid)
                sb.append(row.toString() + "\n");
            
            return sb.toString();
        }
        
        @Override
        public boolean equals(Object other) {
            
            if (!(other instanceof BinaryMatrix)) return false;
            
            BinaryMatrix otherBM = (BinaryMatrix) other;
            return this.grid.equals(otherBM.grid);
        }
        
        @Override
        public int hashCode() {
            
            return grid.hashCode();
        }
        
        private List<List<Integer>> grid;
    }
    
    @Override
    public boolean runTests() {
        
        Map<BinaryMatrix, Integer> testCases = new HashMap<BinaryMatrix, Integer>();
        testCases.put(new BinaryMatrix(new int[][] {
            new int[] { 0,0 },
            new int[] { 1,1 }
        }), 0);
        testCases.put(new BinaryMatrix(new int[][] {
            new int[] { 0,0 },
            new int[] { 0,1 }
        }), 1);
        testCases.put(new BinaryMatrix(new int[][] {
            new int[] { 0,0 },
            new int[] { 0,0 }
        }), -1);
        testCases.put(new BinaryMatrix(new int[][] {
            new int[] { 0,0,0,1 },
            new int[] { 0,0,1,1 },
            new int[] { 0,1,1,1 }
        }), 1);
        
        boolean allPass = true;
        
        for (Map.Entry<BinaryMatrix, Integer> entry : testCases.entrySet()) {
            
            int result = leftMostColumnWithOne(entry.getKey());
            
            if (result != entry.getValue()) allPass = false;
            
            System.out.println(Tester.testResultString(
                    "\n" + entry.getKey().toString(),   // input
                    Integer.toString(entry.getValue()), // expected value
                    Integer.toString(result)));         // actual value
        }
        
        return allPass;
    }

}
