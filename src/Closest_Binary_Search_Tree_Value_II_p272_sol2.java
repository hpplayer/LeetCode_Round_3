import java.util.*;
/**
 * In-order solution
 * 
 * 
 * Basically, this solution is similar to sol1, but now we will not use two stacks, instead, we will repeatedly check the result list
 * to decide when we need to stop visit the tree and when we need to continue visit the tree.
 * In this solution, we use a LinkedList to store values, and use in-order traversal to visit the tree.
 * In case that list size reaches k, then we will check the list and decide whether we need to continue.
 * We always add node to the last of list, so we need compare the diff(target, current Node) and diff(target, list[0]). If the first diff
 * is larger, then it means we don't need to continue as we are supposed to visit right subtree next step which will give a larger diff.
 * If the second diff is larger, then we need remove list[0], and still keep visit the tree.
 * 
 * After we done the visit of left subtree, we need to see the return result. If result is true, then it means some values in left subtree
 * are already too large, so we don't need to consider current node and its right subtree, and we just need to return result
 * 
 * Time complexity: O(N)
 * 
 * 
 * @author hpPlayer
 * @date Feb 8, 2016 3:03:00 PM
 */
public class Closest_Binary_Search_Tree_Value_II_p272_sol2 {
	   public List<Integer> closestKValues(TreeNode root, double target, int k) {
	        LinkedList<Integer> list = new LinkedList<Integer>();
	        inOrder(root, list, target, k);
	        
	        return list;
	    }
	    
	    public boolean inOrder(TreeNode root, LinkedList<Integer> list, double target, int k){
	        if(root == null) return false;
	        
	        //in-order traversal
	        
	        //firstly visit left subtree
	        //if left subtree already give true result, that means some smaller values in left tree are already too large
	        //and current root will give larger difference, not alone nodes in right subtree, so we can return result now
	        if(inOrder(root.left, list, target, k)){
	            return true;
	        }
	        
	        //before we add current root to list, we need check if list.size() has reached k
	        //so we always keep list size to be k, not exceed it
	        
	        if(list.size() == k){
	            //if size = k, then we need to determine we continue visit current subtree or just stop here
	            //compare abs(root - target) with abs(list[0] - target)
	            //if we found list[0] - target gives smaller difference, then we stop now 
	            //otherwise, we will continue
	            
	            if(Math.abs(root.val - target) < Math.abs(list.getFirst() - target)){
	                //we will continue, and remove first Element in list
	                list.removeFirst();
	            }else{
	                //we will stop here
	                return true;
	            }
	            
	        }

	        //after above preprocess, now we can will continue and add current node to list
	        
	        list.addLast(root.val);
	        
	        return inOrder(root.right, list, target, k);
	    }
}
