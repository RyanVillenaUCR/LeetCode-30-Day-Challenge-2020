import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Arrays;

public class Day17NumberOfIslands implements Testable {

    private boolean[][] initEmptyGrid(char[][] grid) {
        
        boolean[][] empty = new boolean[grid.length][];
        for (int i = 0; i < grid.length; i++) {
            
            empty[i] = new boolean[grid[0].length];
            Arrays.fill(empty[i], false);
        }
            
        return empty;
    }
    
    private Set<int[]> getPossibleMoves(char[][] grid, boolean[][] history, int row, int col) {
        
        assert(0 <= col && col < history[0].length);
        assert(0 <= row && row < history.length);
        
        Set<int[]> moves = new HashSet<int[]>();
        
//        System.out.println("getPossibleMoves called with row " + Integer.toString(row)
//            + " and col " + Integer.toString(col));
        
        // Try adding north tile
        if (row != 0                        // Don't add if out of bounds
            && grid[row - 1][col] != '0'    // Don't add if it's not land
            && !history[row - 1][col])      // Don't add if we've already visited this tile
            moves.add(new int[] { row - 1, col });
        
        // Try adding south tile
        if (row != history.length - 1
            && grid[row + 1][col] != '0'
            && !history[row + 1][col])
            moves.add(new int[] { row + 1, col });
        
        // Try adding west tile
        if (col != 0
            && grid[row][col - 1] != '0'
            && !history[row][col - 1])
            moves.add(new int[] { row, col - 1 });
        
        // Try adding east tile
        if (col != history[0].length - 1
            && grid[row][col + 1] != '0'
            && !history[row][col + 1])
            moves.add(new int[] { row, col + 1 });
        
//      System.out.println("getPossibleMoves called with row " + Integer.toString(row)
//          + " and col " + Integer.toString(col) + ",\n"
//          + "  returning " + setOfIntArrsToString(moves) + "\n");
        return moves;
    }
    
    /**
     * If grid[row][col] is land, attempts to "fill in" the rest
     * of the island's corresponding elements in history.
     * If a new island was detected and filled, returns true.
     * Otherwise, if the land has previously been accounted for,
     * or if grid[row][col] is water, returns false;
     * @param grid Map of islands
     * @param history 1:1 representation of grid. True means that cell has been visited, false otherwise
     * @param col Column of the cell to try, aka 'j'
     * @param row Row of the cell to try, aka 'i'
     * @return True if we just filled in a new island, false otherwise
     */
    private boolean fillHistory(char[][] grid, boolean[][] history, int col, int row) {
        
        assert(0 <= row && row < grid.length && row < history.length)
            : "Row " + Integer.toString(row) + " out of bounds";
        assert(0 <= col && col < grid[0].length && col < history[0].length)
            : "Col " + Integer.toString(col) + " out of bounds";
        
        // Don't fill in if there's water,
        // or if it's already been filled
        if (grid[row][col] == '0' || history[row][col])
            return false;
        
        // If you're here, you've hit unexplored land
        int[] currentPos = new int[] { row, col };
        Queue<int[]> frontier = new LinkedList<int[]>(); // Order that it's traversed doesn't matter
        frontier.add(currentPos);
        
        while (!frontier.isEmpty()) {
            
            // Remove the current position from the frontier
            currentPos = frontier.remove();
            
            // Take care of current element
            history[currentPos[0]][currentPos[1]] = true;
            
            // Queue up next positions to explore
            frontier.addAll(getPossibleMoves(grid, history, currentPos[0], currentPos[1]));
            
        }
        
        return true;
    }
    
    public int numIslands(char[][] grid) {
        
        boolean[][] checked = initEmptyGrid(grid);
        int islands = 0;
        
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                
                islands += fillHistory(grid, checked, col, row) ? 1 : 0;
            }
        }
        
//        System.out.println("History: \n" + bool2DArrToString(checked));
        
        return islands;
    }
    
    
    
    
    
    
    
    private String char2DArrToString(char[][] grid) {
        
        StringBuilder sb = new StringBuilder();
        for (char[] row : grid)
            sb.append(Arrays.toString(row) + "\n");
        
        return sb.toString();
    }
    
    private String bool2DArrToString(boolean[][] grid) {
        
        StringBuilder sb = new StringBuilder();
        for (boolean[] row : grid)
            sb.append(Arrays.toString(row) + "\n");
        
        return sb.toString();
    }
    
    private String setOfIntArrsToString(Set<int[]> set) {
        
        StringBuilder sb = new StringBuilder(set.size());
        for (int[] thisArr : set)
            sb.append(Arrays.toString(thisArr));
        
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
        testCases.put(new char[][] {
            new char[] { '1' }
        }, 1);
        testCases.put(new char[][] {
            new char[] { '0' }
        }, 0);
        testCases.put(new char[][] {
            new char[] { '1','0','1','0' }
        }, 2);
        testCases.put(new char[][] {
            new char[] { '1' },
            new char[] { '0' },
            new char[] { '1' },
            new char[] { '0' }
        }, 2);
        testCases.put(new char[][] {
            new char[] { '1','1' },
            new char[] { '0','0' }
        }, 1);
        testCases.put(new char[][] {
            new char[] { '1', '1' },
            new char[] { '1', '1' }
        }, 1);
        testCases.put(new char[][]
            {}
        , 0);
        
        
        
        
        boolean allPass = true;
        
        for (Map.Entry<char[][], Integer> entry : testCases.entrySet()) {
            
            char[][] inputGrid = entry.getKey();
            Integer result = numIslands(inputGrid);
            
            if (result != entry.getValue()) allPass = false;
            
            System.out.println(Tester.testResultString(
                    "\n" + char2DArrToString(inputGrid),    // input
                    Integer.toString(entry.getValue()),     // expected output
                    Integer.toString(result)));             // actual output
        }
        
        return allPass;
    }

}
