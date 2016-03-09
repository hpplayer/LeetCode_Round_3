import java.util.*;

/*
103. Binary Tree Zigzag Level Order Traversal

Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).

For example:
Given binary tree {3,9,20,#,#,15,7},
    3
   / \
  9  20
    /  \
   15   7
return its zigzag level order traversal as:
[
  [3],
  [20,9],
  [15,7]
]
*/

public class Binary_Tree_Zigzag_Level_Order_Traversal_p103_sol1 {
	public static void main(String[] args){
		TreeNode a = new TreeNode(1);
		TreeNode b = new TreeNode(2);
		TreeNode c = new TreeNode(3);
		a.left = b;
		a.right = c;
		b.left = new TreeNode(4);
		c.right = new TreeNode(5);
		System.out.println(new Binary_Tree_Zigzag_Level_Order_Traversal_p103_sol1().zigzagLevelOrder(a));
	}
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        boolean needReverse = false;
        
        if(root == null) return new ArrayList<List<Integer>>();
        
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        
        Deque<TreeNode> que = new LinkedList<TreeNode>();
        
        que.offer(root);
        
        while(!que.isEmpty()){
            int size = que.size();
            LinkedList<Integer> temp = new LinkedList<Integer>();
            
            for(int i = 0; i < size; i++){          
                if(!needReverse){
                    TreeNode curr = que.pollLast();      
                    temp.offerLast(curr.val);
                    if(curr.left != null) que.offerFirst(curr.left);
                    if(curr.right != null) que.offerFirst(curr.right);
                } else{
                	TreeNode curr = que.pollFirst();     
                    temp.offerLast(curr.val);
                    if(curr.right != null) que.offerLast(curr.right);
                    if(curr.left != null) que.offerLast(curr.left);
                }
            }
            needReverse = needReverse? false : true;
            result.add(temp);
        }
        
        return result;
    }
}
