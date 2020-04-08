import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Day02HappyNumber implements Testable {

    private int expand(int n) {
        
        String s = Integer.toString(n);
        int newInt = 0;
        
        for (int i = 0; i < s.length(); i++) {
            
            int val = Character.getNumericValue(s.charAt(i));
            newInt += val * val;
        }
        
        return newInt;
    }
    
    public boolean isHappy(int n) {
        
        Set<Integer> history = new HashSet<Integer>();
        
        // Will exit when we see a cycle
        while (!history.contains(n)) {
            
            if (n == 1)
                return true;
            
            history.add(n);
            n = expand(n);
            
        }
        
        // If you're here, you're a cycle
        return false;
    }
	
	@Override
	public boolean runTests() {
		
		Map<Integer, Boolean> testCases = new HashMap<Integer, Boolean>();
		testCases.put(19, true);
		
		
		boolean allPass = true;
		
		for (Map.Entry<Integer, Boolean> entry : testCases.entrySet()) {
			
			boolean result = isHappy(entry.getKey());
			
			if (result != entry.getValue()) allPass = false;
			
			System.out.println(Tester.testResultString(
					Integer.toString(entry.getKey()),
					Boolean.toString(entry.getValue()),
					Boolean.toString(result)));
		}
		
		// TODO Auto-generated method stub
		return allPass;
	}

}
