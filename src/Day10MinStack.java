import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Stack;

public class Day10MinStack implements Testable {

    /** initialize your data structure here. */
    public Day10MinStack() {
        stack = new Stack<Integer[]>();
    }
    
    public void push(int x) {
        
        int minVal = stack.isEmpty() ? x : Math.min(stack.peek()[1], x);
        
        stack.push(new Integer[] { x, minVal });
    }
    
    public void pop() {
        
        stack.pop();
    }
    
    public int top() {
        
        return stack.peek()[0];
    }
    
    public int getMin() {
        
        return stack.peek()[1];
    }
    
    /**
     * All Integer[] elements are pairs.
     * The 0th element is the value,
     * and the 1st element is the minimum value
     * at that point in the stack.
     */
    private Stack<Integer[]> stack;
	
    
    
    
    
    
    private class TestCaseInput {
    	
    	TestCaseInput(String cmd, Integer arg) {
    		this.cmd = cmd;
    		this.arg = arg;
    	}
    	
    	@Override
    	public String toString() {
    		
    		if (arg == null) return cmd + "()";
    		else return cmd + "(" + Integer.toString(arg) + ")";
    	}
    	
    	String cmd;
    	Integer arg;
    }
    
	@Override
	public boolean runTests() {
		// TODO Auto-generated method stub
		Map<TestCaseInput[], Integer[]> testCases = new HashMap<TestCaseInput[], Integer[]>();
		testCases.put(new TestCaseInput[] {
				new TestCaseInput("MinStack", null),
				new TestCaseInput("push", -2),
				new TestCaseInput("push", 0),
				new TestCaseInput("push", -3),
				new TestCaseInput("getMin", null),
				new TestCaseInput("pop", null),
				new TestCaseInput("top", null),
				new TestCaseInput("getMin", null)
				}, new Integer[] {
				null,
				null,
				null,
				null,
				-3,
				null,
				0,
				-2
		});
		
		boolean allPass = true;
		
		// Iterate through all test cases
		for (Entry<TestCaseInput[], Integer[]> testCase : testCases.entrySet()) {
			
			Day10MinStack minStack = null;
			
			// Iterate through all commands
			for (int i = 0; i < testCase.getKey().length; i++) {
				
				String thisCommand = testCase.getKey()[i].cmd;
				Integer thisArg    = testCase.getKey()[i].arg;
				Integer expectedOutput = testCase.getValue()[i];
				Integer actualOutput   = null;
				
				// Execute the command
				if (thisCommand.equals("MinStack")) {
					
					assert(thisArg == null);
					minStack = new Day10MinStack();
				}
				
				else if (thisCommand.equals("push")) {
					
					assert(thisArg != null);
					minStack.push(thisArg);
				}
				
				else if (thisCommand.equals("pop")) {
					
					assert(thisArg == null);
					minStack.pop();
				}
				
				else if (thisCommand.equals("top")) {
					
					assert(thisArg == null);
					actualOutput = minStack.top();
				}
				
				else if (thisCommand.equals("getMin")) {
					
					assert(thisArg == null);
					actualOutput = minStack.getMin();
				}
				
				else assert(false) : "Unrecognized command " + thisCommand;
				
				// Print output based on command
				System.out.println(Tester.testResultString(
						testCase.getKey()[i].toString(),	// Input
						expectedOutput == null ? "null" : Integer.toString(expectedOutput),	// Expected output
						actualOutput   == null ? "null" : Integer.toString(actualOutput)));	// Actual output
				
				// Test output against test case,
				// including null
				if (expectedOutput != actualOutput)
					allPass = false;
			}
		}
		
		return allPass;
	}

}
