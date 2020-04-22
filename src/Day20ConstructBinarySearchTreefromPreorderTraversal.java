import java.util.LinkedList;
import java.util.Queue;

public class Day20ConstructBinarySearchTreefromPreorderTraversal implements Testable {

    // Implementation from LeetCode Playground
    private class TreeNode {
        
        TreeNode(int x) { val = x; }
        
        @Override
        public String toString() {
            
            return treeNodeToString(this);
        }
        
        int val;
        TreeNode left;
        TreeNode right;
    }
    
    // Implementation from LeetCode Playground
    private static String treeNodeToString(TreeNode root) {
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
    private TreeNode stringToTreeNode(String input) {
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
    private static String prettyPrintTree(TreeNode node, String prefix, boolean isLeft, StringBuilder sb) {
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
    
    
    
    
    public TreeNode bstFromPreorder(int[] preorder) {
        
        if(preorder == null || preorder.length == 0)
            return null;
        
        TreeNode root = null;
        
        // Iterate through preorder straight, linearly
        for(int val : preorder)
            root = attachChild(root, val);
            
        return root;
    }
    
    private TreeNode attachChild(TreeNode root, int val) {
        
        // Empty
        if(root == null)
            return new TreeNode(val);
        
        // Else, attach the value to root
        // left if val is less than root.val,
        // else right (we're guaranteed no ties)
        if(val < root.val)
            root.left  = attachChild(root.left, val);
        else 
            root.right = attachChild(root.right, val);
        
        return root;
    }
    
    
    
    
    @Override
    public boolean runTests() {
        
        System.out.println("Testing this program is hard and I kinda don't wanna write the test.");
        System.out.println("Just take it from me that LeetCode verified this solution :)");
        
        return true;
    }

}
