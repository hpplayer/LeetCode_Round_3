import java.util.*;

/*
199. Binary Tree Right Side View

Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see
ordered from top to bottom.

For example:
Given the following binary tree,
   1            <---
 /   \
2     3         <---
 \     \
  5     4       <---
You should return [1, 3, 4].
*/	

/**
 * BFS solution
 * 
 * We just use BFS to travel this tree, and add the rightmost node in each level to the result list
 * 
 * Time complexity: O(n)
 * Space complexity: O(n/2)
 * 
 * Sol1 is intuitive BFS solution
 * Sol2 is tricky but beautiful DFS solution
 * 
 * @author hpPlayer
 * @date May 31, 2016 10:29:17 PM
 */
public class Binary_Tree_Right_Side_View_p199_sol1 {
    public List<Integer> rightSideView(TreeNode root) {
        //intuitive BFS solution
        
        //boundary check
        if(root == null) return new ArrayList<Integer>();
        
        Queue<TreeNode> que = new LinkedList<TreeNode>();
        que.offer(root);
        
        List<Integer> result = new ArrayList<Integer>();
        
        //Do BFS on input tree
        while(!que.isEmpty()){
            int size = que.size();
            for(int i = 0; i < size; i++){
                TreeNode curr = que.poll();
                //rightmost node in curr layer, add to result
                if(i == size - 1) result.add(curr.val);
                if(curr.left != null) que.offer(curr.left);
                if(curr.right != null) que.offer(curr.right);
            }
            
        }
        
        return result;
    }
}
