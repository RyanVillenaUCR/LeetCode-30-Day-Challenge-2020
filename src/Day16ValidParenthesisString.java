import java.util.HashMap;
import java.util.Map;

public class Day16ValidParenthesisString implements Testable {

    public boolean checkValidString(String s) {
        
        // Possible interval of open left brackets
        int lowerBound = 0, upperBound = 0;
        for (int i = 0; i < s.length(); i++) {
            
            char c = s.charAt(i);
            
            // Maintain lower bound
            //
            // If we see a (, lowerBound should increase,
            // as there's a larger interval of possibly open left brackets
            // Otherwise, if we see a ),
            // lowerBound should decrease (widen) since there's a
            // greater interval of possibly open left brackets,
            // but it cannot be less than 0
            lowerBound = Math.max(0,
                    lowerBound + (c == '(' ? 1 : -1));
            
            // Maintain upper bound
            //
            // If we see a ) or a *, upperBound should decrease,
            // as a left parenthesis may have been canceled out by it
            // Otherwise, if we see a (,
            // upperBound should obviously increase
            upperBound += c == '(' ? -1 : 1;
            
            // A negative upperBound means our string started with
            // too many )'s, and is irredeemable
            if (upperBound < 0) return false;
            
        }
        
        // At end of string processing,
        // it must be possible to have 0 open left parentheses
        return lowerBound == 0;
    }
    
    @Override
    public boolean runTests() {
        
        Map<String, Boolean> testCases = new HashMap<String, Boolean>();
        testCases.put("(*)", true);
        testCases.put("*)", true);
        testCases.put("(*", true);
        testCases.put("", true);
        testCases.put("(()", false);
        testCases.put("())*", false);
        testCases.put("()", true);
        testCases.put("*(", false);
        testCases.put(")*", false);
        testCases.put("(()())", true);
        testCases.put("((*)(*))", true);
        
        boolean allPass = true;
        
        for (Map.Entry<String, Boolean> entry : testCases.entrySet()) {
            
            Boolean result = checkValidString(entry.getKey());
            
            if (result != entry.getValue()) allPass = false;
            
            System.out.println(Tester.testResultString(
                    entry.getKey(),                     // input
                    Boolean.toString(entry.getValue()), // expected output
                    Boolean.toString(result)));         // actual output
        }
        
        return allPass;
    }

}
