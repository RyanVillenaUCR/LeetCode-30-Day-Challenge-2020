import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class Day12LastStoneWeight implements Testable {

	private class DescendingOrder implements Comparator<Integer> {

		@Override
		public int compare(Integer o1, Integer o2) {

			return (int) Math.signum(o2 - o1);
		}
	}
	
	public int lastStoneWeight(int[] stones) {
		
		Queue<Integer> pq = new PriorityQueue<Integer>(new DescendingOrder());
		for (int stone : stones)
			pq.add(stone);
		
		while (pq.size() > 1) {
			
			Integer stone1 = pq.remove();
			Integer stone2 = pq.remove();
			
			Integer smashed = Math.abs(stone1 - stone2);
			if (smashed != 0) pq.add(smashed);
		}
		
		return pq.isEmpty() ? 0 : pq.peek();
	}
	
	int lastStoneWeight(Integer[] stones) {
		
		int[] arr = new int[stones.length];
		for (int i = 0; i < stones.length; i++)
			arr[i] = stones[i];
		
		return lastStoneWeight(arr);
	}
	
	@Override
	public boolean runTests() {
		
		Map<Integer[], Integer> testCases = new HashMap<Integer[], Integer>();
		testCases.put(new Integer[] {2,7,4,1,8,1}, 1);
		
		boolean allPass = true;
		
		for (Map.Entry<Integer[], Integer> entry : testCases.entrySet()) {
			
			Integer result = lastStoneWeight(entry.getKey());
			
			System.out.println(Tester.testResultString(
					Arrays.toString(entry.getKey()), 	// input
					Integer.toString(entry.getValue()),	// expected output
					Integer.toString(result)));			// actual output
		}
		
		return allPass;
	}

}
