import java.util.*;

/*
314. Binary Tree Vertical Order Traversal

Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by column).

If two nodes are in the same row and column, the order should be from left to right.

Examples:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its vertical order traversal as:
[
  [9],
  [3,15],
  [20],
  [7]
]
Given binary tree [3,9,20,4,5,2,7],
    _3_
   /   \
  9    20
 / \   / \
4   5 2   7
return its vertical order traversal as:
[
  [4],
  [9],
  [3,5,2],
  [20],
  [7]
]
*/	
		

/**
 * HashMap + BFS solution
 * 
 * This problem requires us to group the nodes based on the column number. So as what we did in problem 
 * Group_Shifted_Strings_p249_sol1, we can use HashMap to help do that. To achieve the goal we let the key be 
 * the column number and the value be the list that contains all nodes in this column. 
 * This problem asks us to insert nodes from top->bot and from left->right, so we use BFS to help us get nodes in order
 * 
 * Time complexity: O(N)
 * Space complexity: O(N)
 * 
 * Remark:
 * 
 * In this solution, we use MyNode to track column number but We can use two queues to track current column number
 *  
 * 
 * @author hpPlayer
 * @date Feb 28, 2016 7:28:32 PM
 */
public class Binary_Tree_Vertical_Order_Traversal_p314_sol1 {
    private class MyNode{
        int col;
        TreeNode node;
        MyNode(int c, TreeNode n){
            col = c;
            node = n;
        }
    }
    
    public List<List<Integer>> verticalOrder(TreeNode root) {
        //BFS + HashMap solution. Since the problem requires us to put nodes in same col from top->bot order,
        //we have to use BFS otherwise, we will have bot node inserted into result first
        //We use a HashMap to record the nodes and the columns it located. So all nodes in same column will be recorded
        
        //boundary check
        if(root == null) return new ArrayList<List<Integer>>();
        //hs map used to record nodes in same column
        //key is col number and value is nodes in that column
        Map<Integer, List<Integer>> hs = new HashMap<Integer, List<Integer>>();
        
        //record left & right boundary so we can scan the HashMap
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        
        Queue<MyNode> que = new LinkedList<MyNode>();
        
        que.offer(new MyNode(0, root));
        
        while(!que.isEmpty()){
            MyNode curr = que.poll();
            if(!hs.containsKey(curr.col)) hs.put(curr.col, new ArrayList<Integer>());
            
            //insert current node into HashMap
            hs.get(curr.col).add(curr.node.val);
            
            //update min and max value if possible
            min = Math.min(min, curr.col);
            max = Math.max(max, curr.col);
            
            //going left, let col number - 1
            if(curr.node.left != null) que.offer(new MyNode(curr.col-1, curr.node.left));
            //going right, let col number + 1
            if(curr.node.right != null) que.offer(new MyNode(curr.col+1, curr.node.right));
        }
        
        //create result list
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        
        for(int i = min; i <= max; i++){
            //since the tree is connected, we must have each col between min and max
            //Otherwise there is no way we can "jump" from col i-1 to another col i+1
            result.add(hs.get(i));
        }
        
        return result;
    }
}
