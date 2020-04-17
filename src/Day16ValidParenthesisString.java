import java.util.HashMap;
import java.util.Map;

public class Day16ValidParenthesisString implements Testable {

    public boolean checkValidString(String s) {
        
        int extraLives = 0;
        int leftParens = 0;
        
        for (int i = 0; i < s.length(); i++) {
            
            char c = s.charAt(i);
            
            switch (c) {
                    
                case '(':
                    leftParens++;
                    break;
                case ')':
                    if (leftParens > 0)
                        leftParens--;
                    else if (extraLives > 0)
                        extraLives--;
                    else return false;
                    break;
                case '*':
                    if (leftParens > 0) {
                        leftParens--;
                        extraLives--;
                    }
                    else extraLives++;
                    break;
            }
        }
        
        return leftParens == 0;
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
