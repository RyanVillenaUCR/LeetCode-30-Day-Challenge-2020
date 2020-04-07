import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Day05BestTimeBuySellStockII implements Testable {

    public int maxProfit(int[] prices) {
        
        int profit = 0;
        boolean buying = true;  // true if buying, false if selling
        
        for (int i = 0; i < prices.length - 1; i++) {
            
            // If buying, check next element
            // If it's greater than the current,
            // then "buy"
            if (buying && prices[i+1] > prices[i]) {
                
                profit -= prices[i];
                buying = false;
            }
            
            // If selling, check next element
            // If it's less than the current,
            // then "sell"
            if (!buying && prices[i+1] < prices[i]) {
                
                profit += prices[i];
                buying = true;
            }
        }
        
        // Sell on the last day if we were trying to sell
        // (We will never buy on the last day)
        if (!buying)
            profit += prices[prices.length - 1];
        
        // Return 0 if there's no way to make a profit
        return profit > 0 ? profit : 0;
    }
	
	@Override
	public boolean runTests() {

		Map<int[], Integer> testCases = new HashMap<int[], Integer>();
		testCases.put(new int[] {7,1,5,3,6,4}, 7);
		testCases.put(new int[] {1,2,3,4,5}, 4);
		testCases.put(new int[] {7,6,4,3,1}, 0);
		
    	boolean allPass = true;
    	
    	for (Map.Entry<int[], Integer> entry : testCases.entrySet()) {
    		
    		int result = maxProfit(entry.getKey());
    		
    		System.out.println(Tester.testResultString(
    				Arrays.toString(entry.getKey()),		// input
    				Integer.toString(entry.getValue()),		// expected output
    				Integer.toString(result)));				// actual output
    		
    		if (result != entry.getValue()) allPass = false;
    	}
    	
    	return allPass;
	}

}
