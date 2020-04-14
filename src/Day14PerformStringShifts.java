import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Day14PerformStringShifts implements Testable {

    private int getNetShift(int[][] shift) {
        
        int shiftVal = 0;
                
        for (int[] thisShift : shift)
        	
        	shiftVal += thisShift[0] == 0 ?
        			-thisShift[1] :	// Subtract if shifting left
    				 thisShift[1];	// Add if shifting right
        
        return shiftVal;
    }
    
    public String stringShift(String s, int[][] shift) {
        
        // Compute where everything will end up,
        // then just perform one shift directly there,
        // rather than shifting the same string multiple times
        int shiftVal = getNetShift(shift);
        
        if (shiftVal == 0) return s;
        
        
        
        int len = s.length();
        char[] cStr = new char[len];
        
        for (int i = 0; i < len; i++) {
            
        	int newIndex = Math.floorMod(i + shiftVal, len);
            cStr[newIndex] = s.charAt(i);
        }
        
        return new String(cStr);
    }
    
    
	
	private static String doubleArrToString(int[][] arr) {
		
		String[] temp = new String[arr.length];
		for (int i = 0; i < arr.length; i++)
			temp[i] = Arrays.toString(arr[i]);
		
		return Arrays.toString(temp);
	}
	
	private class TestCase {
		
		public TestCase(String str, int[][] arr) {
			
			this.str = str;
			this.arr = arr;
		}
		
		@Override
		public String toString() {
			return str + " " + doubleArrToString(this.arr);
		}
		
		public String str;
		public int[][] arr;
	}
	
	@Override
	public boolean runTests() {

		Map<TestCase, String> testCases = new HashMap<TestCase, String>();
		testCases.put(new TestCase("abc", new int[][] {
			new int[] {0,1},
			new int[] {1,2}
		}), "cab");
		testCases.put(new TestCase("abcdefg", new int[][] {
			new int[] {1,1},
			new int[] {1,1},
			new int[] {0,2},
			new int[] {1,3}
		}), "efgabcd");
		testCases.put(new TestCase("abcdefg", new int[][] {
			new int[] {0,2}
		}), "cdefgab");
		testCases.put(new TestCase("wpdhhcj", new int[][] {
			new int[] {0,7},
			new int[] {1,7},
			new int[] {1,0},
			new int[] {1,3},
			new int[] {0,3},
			new int[] {0,6},
			new int[] {1,2}
		}), "hcjwpdh");
		
		
		boolean allPass = true;
		for (Map.Entry<TestCase, String> entry : testCases.entrySet()) {
			
			String inputStr = entry.getKey().str;
			int[][] inputArr = entry.getKey().arr;
			String result = stringShift(inputStr, inputArr);
			
			if (!result.equals(entry.getValue())) allPass = false;
			
			System.out.println(Tester.testResultString(
					entry.getKey().toString(),		// input
					entry.getValue(),				// expected output
					result));						// actual output
		}
		
		return allPass;
	}

}
