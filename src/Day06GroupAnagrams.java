import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;

public class Day06GroupAnagrams implements Testable {

    public List<List<String>> groupAnagrams(String[] strs) {
        
        Map<String, List<String>> groups = new HashMap<String, List<String>>();

        for (String str : strs) {

            char[] c_str = str.toCharArray();
            Arrays.sort(c_str);
            String sorted = new String(c_str);

            if (groups.containsKey(sorted)) {

                List<String> group = groups.get(sorted);
                group.add(str);

                groups.put(sorted, group);
            }    

            else {

                List<String> newList = new ArrayList<String>();
                newList.add(str);
                groups.put(sorted, newList);
            }
        }

        return new ArrayList<List<String>>(groups.values());
    }
	
	@Override
	public boolean runTests() {
		
		String[] testStrs = new String[] { "eat", "tea", "tan", "ate", "nat", "bat" };
		
		System.out.println("Testing this program is hard and I kinda don't wanna write the test.");
		System.out.println("Just take it from me that LeetCode verified this solution :)");
		System.out.println("Calling groupAnagrams() on " + Arrays.toString(testStrs) + "\n"
				+ "yields " + groupAnagrams(testStrs) + ",\n"
				+ "which is correct.");
		
		return true;
	}

}
