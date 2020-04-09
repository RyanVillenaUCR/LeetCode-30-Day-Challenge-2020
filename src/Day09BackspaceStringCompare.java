import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Day09BackspaceStringCompare implements Testable {

	private int progressToNextCharBackwards(String str, int currentPos) {
		
		currentPos--;
		
		System.err.print("Using string " + str + ", moving pos " + currentPos);
		
		if (currentPos < 0) return currentPos;
		
		int backspaces = 0;
		do {

			// Walk currentPos past any # characters,
			// plus any regular chars based on backspaces
			if (str.charAt(currentPos) == '#') {
				
				backspaces++;
				currentPos--;
			}
			else if (backspaces > 0) { // This means currentPos is at a non-# char
				
				currentPos--;
				backspaces--;	// Reset in case there are more backspaces in this loop
			}
			
		} while (currentPos >= 0 &&
				(backspaces > 0 || str.charAt(currentPos) == '#'));
		
		System.err.println(" to " + currentPos);
		return currentPos;
	}
	
    public boolean backspaceCompare(String S, String T) {
        
        if (S.equals("") && T.equals("")) return true;
        
        int sWalker = S.length();
        int tWalker = T.length();
        
        // Start both at end and iterate backwards
        while (sWalker >= 0 && tWalker >= 0) {
            
            // Progress sWalker to the next non-deleted char,
            // keeping track of backspaces
            sWalker = progressToNextCharBackwards(S, sWalker);
            
            // Also progress tWalker to the next non-deleted char
            tWalker = progressToNextCharBackwards(T, tWalker);
            
            // Now, sWalker and tWalker either point
            // to the next non-deleted char,
            // or are negative (they've passed the beginning of the string)
            
            // If we can test sWalker and tWalker,
            // then test their chars
            // If they're equal, keep progressing the loop
            // Otherwise, return false immediately
            if ((sWalker >= 0 && tWalker >= 0)
            		&& S.charAt(sWalker) != T.charAt(tWalker)) {
            	
            	System.err.println("Just compared " +
            		"S[" + sWalker + "] \'" + S.charAt(sWalker) + "\' with " +
            		"T[" + tWalker + "] \'" + T.charAt(tWalker) + "\', returning false");
            	
            	return false;
            }
            	
        }
        
        // If both have scanned through completely
        // and haven't ever printed different characters,
        // return true
        return true;
    }
	
    public boolean backspaceCompare(String[] st) {
    	
    	assert(st.length == 2) :
    		"Test case " + Arrays.toString(st) + " invalid";
    	return backspaceCompare(st[0], st[1]);
    }
    
	@Override
	public boolean runTests() {

		Map<String[], Boolean> testCases = new HashMap<String[], Boolean>();
		testCases.put(new String[] { "ab#c", "ad#c" }, true);
		testCases.put(new String[] { "ab##", "c#d#" }, true);
		testCases.put(new String[] { "a##c", "#a#c" }, true);
		testCases.put(new String[] { "a#c",  "b"    }, false);
		testCases.put(new String[] { "abcd", "abcdab##c#" }, true);
		testCases.put(new String[] { "abcde","abcde" },true);
		testCases.put(new String[] { "bxj##tw", "bxo#j##tw" }, true);
		testCases.put(new String[] { "bxj##tw", "bxj###tw" }, false);
		
		boolean allPass = true;
		
		for (Map.Entry<String[], Boolean> entry : testCases.entrySet()) {
			
			boolean result = backspaceCompare(entry.getKey());
			
			System.out.println(Tester.testResultString(
					Arrays.toString(entry.getKey()),	// input
					Boolean.toString(entry.getValue()),	// expected output
					Boolean.toString(result)));			// actual output
			
			if (result != entry.getValue()) allPass = false;
		}
		
		return allPass;
	}

}
