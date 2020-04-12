import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Day11DiameterOfBinaryTree implements Testable {

	// Implementation from LeetCode Playground
	private class TreeNode {
		
		TreeNode(int x) { val = x; }
		
		@Override
		public String toString() {
			
			return Day11DiameterOfBinaryTree.treeNodeToString(this);
		}
		
		int val;
		TreeNode left;
		TreeNode right;
	}
	
	// Implementation from LeetCode Playground
	public static String treeNodeToString(TreeNode root) {
        if (root == null) {
            return "[]";
        }
    
        String output = "";
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);
        while(!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.remove();
    
            if (node == null) {
              output += "null, ";
              continue;
            }
    
            output += String.valueOf(node.val) + ", ";
            nodeQueue.add(node.left);
            nodeQueue.add(node.right);
        }
        return "[" + output.substring(0, output.length() - 2) + "]";
    }
    
	// Implementation from LeetCode Playground
    public TreeNode stringToTreeNode(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return null;
        }
    
        String[] parts = input.split(",");
        String item = parts[0];
        TreeNode root = new TreeNode(Integer.parseInt(item));
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);
    
        int index = 1;
        while(!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.remove();
    
            if (index == parts.length) {
                break;
            }
    
            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int leftNumber = Integer.parseInt(item);
                node.left = new TreeNode(leftNumber);
                nodeQueue.add(node.left);
            }
    
            if (index == parts.length) {
                break;
            }
    
            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int rightNumber = Integer.parseInt(item);
                node.right = new TreeNode(rightNumber);
                nodeQueue.add(node.right);
            }
        }
        return root;
    }
    
    // Implementation from LeetCode Playground
    public static String prettyPrintTree(TreeNode node, String prefix, boolean isLeft, StringBuilder sb) {
        if (node == null) {
            return "Empty tree";
        }
    
        if (node.right != null) {
            prettyPrintTree(node.right, prefix + (isLeft ? "│   " : "    "), false, sb);
        }
    
        sb.append(prefix + (isLeft ? "└── " : "┌── ") + node.val + "\n");
    
        if (node.left != null) {
            prettyPrintTree(node.left, prefix + (isLeft ? "    " : "│   "), true, sb);
        }
        
        return sb.toString();
    }
    
    // Implementation from LeetCode Playground
    public static String prettyPrintTree(TreeNode node) {
        return prettyPrintTree(node,  "", true, new StringBuilder());
    }
	
    
    
    
    
    
    
    private Integer maxPath;    // can't pass Integer objects by ref...
    
    private int depth(TreeNode root) {
        
        if (root == null) return 0;
        else if (root.left == null && root.right == null) return 0;
        
        int leftDepth  = depth(root.left);
        int rightDepth = depth(root.right);
        
        maxPath = Math.max(maxPath,
                           leftDepth  + (root.left  == null ? 0 : 1) +
                           rightDepth + (root.right == null ? 0 : 1));
        
        return Math.max(leftDepth, rightDepth) + 1;
    }
    
    public int diameterOfBinaryTree(TreeNode root) {
        
        if (root == null) return 0;
        maxPath = 0;
        
        depth(root);
        
        return maxPath;    
    }
	
	@Override
	public boolean runTests() {
		
		Map<Integer[], Integer> testCases = new HashMap<Integer[], Integer>();
		testCases.put(new Integer[] {1,2,3,4,5}, 3);
		testCases.put(new Integer[] {1,2,3,4}, 3);
		testCases.put(new Integer[] {
				1,   2,   3,   null,null,
				6,   7,   8,   9,   10,
				null,12,  null,14,  15,
				null,null,18,  19,  20,
				null,22,  null,null,25,
				26,  null,null,null,30},
				8);
		
		boolean allPass = true;
		
		for (Map.Entry<Integer[], Integer> entry : testCases.entrySet()) {
			
			TreeNode thisRoot = stringToTreeNode(Arrays.toString(entry.getKey()));
			Integer thisDiameter = diameterOfBinaryTree(thisRoot);
			
			System.out.println(Tester.testResultString(
					"\n" + prettyPrintTree(thisRoot),
					Integer.toString(entry.getValue()),
					Integer.toString(thisDiameter)));
		}
		
		return allPass;
	}

}
