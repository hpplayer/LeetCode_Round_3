import java.util.*;

/*
Given a binary tree, return all root-to-leaf paths.

For example, given the following binary tree:

   1
 /   \
2     3
 \
  5
All root-to-leaf paths are:

["1->2->5", "1->3"]
*/

/**
 * BFS solution
 * 
 * Each time we add a non-null node to stack, and append its val after its parent node's string variable
 * 
 * Sol1 is iterative version
 * sol2 is recursive version
 * 
 * Time complexity: O(N)
 * Space complexity: O(N)
 * 
 * @author hpPlayer
 * @date Feb 11, 2016 11:27:13 AM
 */
public class Binary_Tree_Paths_p257_sol1 {
    class MyNode{
        TreeNode node;
        String str;
        MyNode(TreeNode n, String s){
            str = s;
            node = n;
        }
    }
    
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<String>();
        if(root == null) return result;
        
        Stack<MyNode> stack = new Stack<MyNode>();
        stack.push(new MyNode(root, root.val+""));
        
        while(!stack.isEmpty()){
            MyNode curr = stack.pop();
            if(curr.node.left == null && curr.node.right == null) result.add(curr.str);
            if(curr.node.left != null) stack.push(new MyNode(curr.node.left, curr.str + "->" + curr.node.left.val));
            if(curr.node.right != null) stack.push(new MyNode(curr.node.right, curr.str + "->" + curr.node.right.val));
        }
        
        return result;
    }
}
