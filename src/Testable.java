import java.util.Arrays;
import java.util.Map;

public interface Testable<K, V> {

	Map<K, V> getTestCases();
	
	static boolean runTests(Map<K, V> testCases) {
		
    	boolean allPass = true;
    	
    	for (Map.Entry<K, V> entry : testCases.entrySet()) {
    		
    		V result = countElements(entry.getKey());
    		
    		System.out.println("Testing input " + entry.getKey() 
    			+ ", expecting " + entry.getValue() + ": "
    			+ (result.equals(entry.getValue()) ? "PASS" : "FAIL, got " + result));
    		
    		if (result != entry.getValue()) allPass = false;
    	}
    	
    	return allPass;
	}
}
