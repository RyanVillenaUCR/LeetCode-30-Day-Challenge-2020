import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class Day18MinimumPathSum implements Testable {

    private int[][] generateEmptyWeights(int[][] grid) {
        
        if (grid.length == 0)
            return new int[][] {};
        
        int[][] newGrid = new int[grid.length][];
        for (int i = 0; i < grid.length; i++) {
            newGrid[i] = new int[grid[0].length];
            Arrays.fill(newGrid[i], Integer.MAX_VALUE);
        }
        
        return newGrid;
    }
    
    private boolean hasNorth(int[][] grid, int row, int col) {
        
        return row != 0;
    }
    
    private boolean hasWest(int[][] grid, int row, int col)  {
        
        return col != 0;
    }
    
    private int[] stringToCoords(String str) {
        
        Scanner sc = new Scanner(str);
        int[] coords = new int[] { sc.nextInt(), sc.nextInt() };
        sc.close();
        return coords;
    }
    
    private String coordsToString(int a, int b) {
        
        return Integer.toString(a) + " " + Integer.toString(b);
    }
    
    private Set<String> generateChildren(int[][] grid, int row, int col) {
        
        Set<String> newCoords = new HashSet<String>();
        
        // Try to go down
        if (row != grid.length - 1)
            newCoords.add(coordsToString(row + 1, col));
        
        // Try to go right
        if (col != grid[0].length - 1)
            newCoords.add(coordsToString(row, col + 1));
        
        return newCoords;
    }
    
    public int minPathSum(int[][] grid) {
        
        if (grid.length == 0) return 0;
        
        int[][] weights = generateEmptyWeights(grid);
        
        Queue<String> frontier = new LinkedList<String>();
        frontier.add(coordsToString(0,0));
        
        while (!frontier.isEmpty()) {
            
            int[] pos = stringToCoords(frontier.remove());
            int row = pos[0];
            int col = pos[1];
            
            if (row == 0 && col == 0)
                weights[0][0] = grid[0][0];
            
            // Process current location
            else if (!hasNorth(grid, row, col))
                weights[row][col] =
                    weights[row][col - 1] + grid[row][col];
            
            else if (!hasWest(grid, row, col))
                weights[row][col] =
                    weights[row - 1][col] + grid[row][col];
            
            // Has both a north and a west
            else 
                weights[row][col] =
                    Math.min(Math.min(
                            weights[row - 1][col] + grid[row][col],
                            weights[row][col - 1] + grid[row][col]),
                            weights[row][col]);
            
            // Generate more locations, if any
            frontier.addAll(generateChildren(grid, row, col));
        }
        
        System.out.println("Weights:\n" + int2DArrToString(weights));
        return weights[grid.length - 1][grid[0].length - 1];
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
            
            System.out.println(Tester.testResultString( "\n" +
                    int2DArrToString(entry.getKey()),   // input
                    Integer.toString(entry.getValue()), // expected output
                    Integer.toString(result)));         // actual output
        }
        
        return allPass;
    }

}
