import java.util.*;

/*
297. Serialize and Deserialize Binary Tree


Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer,
or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work.
You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.

For example, you may serialize the following tree

    1
   / \
  2   3
     / \
    4   5
as "[1,2,3,null,null,4,5]", just the same as how LeetCode OJ serializes a binary tree. You do not necessarily need to follow this format,
so please be creative and come up with different approaches yourself.
Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.
*/

/**
 * Queue & BFS solution
 * 
 * serialize() is easy, we just use BFS way to create a string for the input tree. Since we will also add char for null child, our string represents
 * a complete tree
 * deserialize() is a little tricky, we need add child nodes to parent node. But our string is a complete tree, actually we can guarantee we have
 * char representation of each node. Therefore we still use a queue. We firstly split the string by delimiter. Then we use a queue to store nodes
 * in same level, and use a scanner in split array to add nodes from next layer
 * ex: 1 23 4567. ########
 * we firstly read 1, and add 2,3 to be its left,right child, then we add 2,3 to queue
 * Then we read 2, and add 4,5 to be its left,right child, then we add 4,5 to queue
 * Then we read 3, and add 6,7 to be its left,right child, then we add 6,7 to queue
 * Final layer all are null nodes, therefore we do nothing
 * 
 * Time complexity: O(N)
 * Space complexity: O(N)
 * 
 * Remark:
 * 1. when checking if the input string is "#", we need use equals() not ==!!!!!!!!!!!!!!
 * 2. This solution is similar to Encode_and_Decode_Strings_p271_sol1
 * But in that problem, we need deal with boundary case that input string is empty or input string contains an empty char. If using default split(),
 * empty char will be treated as empty string and will not be listed in split array, therefore we need use split(str, -1) to force keep the empty char
 * But here we will never have such case. Our input data in deserialize() will either be empty string or string contains chars, therefore we can
 * use default split() to do the job
 * 
 * @author hpPlayer
 * @date Mar 1, 2016 10:49:56 AM
 */
public class Serialize_and_Deserialize_Binary_Tree_p297_sol1 {
	public static void main(String[] args){
		TreeNode a = null;
		Serialize_and_Deserialize_Binary_Tree_p297_sol1 test = new Serialize_and_Deserialize_Binary_Tree_p297_sol1();
		test.deserialize(test.serialize(a));
	}
    //Queue solution. In serialize() we use queue to do the BFS. In deserialize(), we use queue to do the reverse version of BFS
    //since we will create a string for the complete tree, in deserialize(), we are guaranteed to find two children for each non-leaf node
    
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        
        Queue<TreeNode> que = new LinkedList<TreeNode>();
        que.offer(root);
        
        while(!que.isEmpty()){
            TreeNode curr = que.poll();
            if(curr == null){
                sb.append("#").append(",");
            }else{
                sb.append(curr.val).append(",");
                que.offer(curr.left);
                que.offer(curr.right);
            }
        }

        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
    	//since we will create a string for input tree no matter it is a general tree or a null node
    	//we are guaranteed the input string is not null, at least it will contains "#" if input tree is a null node
        
        //we still use queue to do the reverse version of BFS to append nodes
        Queue<TreeNode> que = new LinkedList<TreeNode>();
        
        //split input string by delimiter
        String[] strs = data.split(",");

        //get root, remember using equals() to check if the input string is "#"!!!!!
        TreeNode root = strs[0].equals("#")? null :new TreeNode(Integer.valueOf(strs[0]));
        que.offer(root);
        
        //each time we will use two strings in input, therefore the step is += 2
        for(int i = 1; i < strs.length; i+=2){
            //each loop will add two children for a node from queue
            //if child is null, then we do nothing.
            //if child is non-null, then we need create a node and append to parent node, and add this node into queue for further process
            TreeNode curr = que.poll();
            
            if(!strs[i].equals("#")){
                //first node is left child
                curr.left = new TreeNode(Integer.valueOf(strs[i]));
                que.offer(curr.left);
            }
            
            if(!strs[i+1].equals("#")){
                //second node is right child
                curr.right = new TreeNode(Integer.valueOf(strs[i+1]));
                que.offer(curr.right);                
            }
        }
        
        return root;
    }
}
//Your Codec object will be instantiated and called as such:
//Codec codec = new Codec();
//codec.deserialize(codec.serialize(root));