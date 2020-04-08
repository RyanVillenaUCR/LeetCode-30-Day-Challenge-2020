import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Day08MiddleOfTheLinkedList implements Testable {

	public class ListNode {
		int val;
		ListNode next;
		ListNode(int x) { val = x; }
		
		@Override
		public String toString() {
			
			StringBuilder sb = new StringBuilder();
			ListNode walker = this;
			while (walker != null) {
				
				sb.append(walker.val);
				sb.append(walker.next == null ? "" : ", ");
				walker = walker.next;
			}
			return sb.toString();
		}
		
		@Override
		public boolean equals(Object other) {
			
			if (!(other instanceof ListNode)) return false;
			
			ListNode otherListNode = (ListNode) other;
			
			return this == otherListNode;
		}
		
		@Override
		public int hashCode() {
			
			return Integer.hashCode(this.val)
					* (this.next == null? 31 : Integer.hashCode(this.next.val) - 3);
		}
	}

	private ListNode makeTestListNode(List<Integer> src) {
		
		if (src.isEmpty()) return null;
		
		Iterator<Integer> it = src.iterator();

		ListNode head = new ListNode(it.next());
		ListNode walker = head;
		
		while (it.hasNext()) {
			
			walker.next = new ListNode(it.next());
			walker = walker.next;
		}
		
		return head;
	}
	
    public ListNode middleNode(ListNode head) {
        
        // First, count nodes
        int size = 0;
        ListNode walker = head;
        while (walker != null) {
            walker = walker.next;
            size++;
        }
        
        int desiredIndex = size / 2;
        walker = head;
        
        // Now, manually progress to the desired node
        for (int i = 0; i < desiredIndex; i++)           
            walker = walker.next;
        
        return walker;
    }

	@Override
	public boolean runTests() {
		// TODO Auto-generated method stub
		
		// Test ListNode initialization from a List
//		List<Integer> ll_src = Arrays.asList(new Integer[] {0,1,2} );
//		ListNode testHead = makeTestListNode(ll_src);
//		System.out.println("Testhead list: " + testHead.toString());
		List<Integer> testCase1 = Arrays.asList(new Integer[] {1,2,3,4,5});
		List<Integer> testCase2 = Arrays.asList(new Integer[] {1,2,3,4,5,6});
		
		Map<ListNode, Integer> testCases = new HashMap<ListNode, Integer>();
		testCases.put(makeTestListNode(testCase1), 3);
		testCases.put(makeTestListNode(testCase2), 4);
		
		
		
		boolean allPass = true;
		
		for (Map.Entry<ListNode, Integer> entry : testCases.entrySet()) {
			
			Integer result = middleNode(entry.getKey()).val;
			
			if (result != entry.getValue()) allPass = false;
			
			System.out.println(Tester.testResultString(
					entry.getKey().toString(),			// input
					Integer.toString(entry.getValue()),	// expected output
					Integer.toString(result)));
		}
		
		return allPass;
	}

}
