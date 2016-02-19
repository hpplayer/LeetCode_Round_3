import java.util.*;

/*
331. Verify Preorder Serialization of a Binary Tree

One way to serialize a binary tree is to use pre-order traversal. When we encounter a non-null node, we record the node's value.
If it is a null node, we record using a sentinel value such as #.

_9_
/   \
3     2
/ \   / \
4   1  #  6
/ \ / \   / \
# # # #   # #
For example, the above binary tree can be serialized to the string "9,3,4,#,#,1,#,#,2,#,6,#,#", where # represents a null node.

Given a string of comma separated values, verify whether it is a correct preorder traversal serialization of a binary tree.
Find an algorithm without reconstructing the tree.

Each comma separated value in the string must be either an integer or a character '#' representing null pointer.

You may assume that the input format is always valid, for example it could never contain two consecutive commas such as "1,,3".

Example 1:
"9,3,4,#,#,1,#,#,2,#,6,#,#"
Return true

Example 2:
"1,#"
Return false

Example 3:
"9,#,#,1"
Return false
*/

/**
 * Stack solution
 * 
 * We use a stack to scan the input string from left to right. If we found a char is followed by two "#"s, then we can merge them into one "#" indicating
 * this tree has been done. Then we look backward to see if some previous chars can be further compressed due to current operation. 
 * If the input is valid, then we finally should get a single "#" in the stack means the whole tree has been done
 * To make the merge more convenient, we use a boolean[] with a pointer to implement the operation on a stack
 * 
 * Time complexity: O(N)
 * Space complexity: O(N)
 * 
 * Sol2 provides a even simpler solution that only count the num of incoming and outgoing edges
 * 
 * @author hpPlayer
 * @date Feb 18, 2016 10:16:46 PM
 */
public class Verify_Preorder_Serialization_of_a_Binary_Tree_p331_sol1 {
	public static void main(String[] args){
		String str = "1,#,#";
		String str2 = "9,3,4,#,#,1,#,#,2,#,6,#,#";
		System.out.println(isValidSerialization(str2));
	}
	
    public static boolean isValidSerialization(String preorder) {
        //Stack solution. We use a stack to hold all Nodes.
        //Basically we will merge nodes if we found a node is followed by two "#", "#". 
        //After that, we can convert this node to a "#" and further compress the tree-order
    	//we keep compressing the stack until we the end of stack becomes a non '#' char
        //if the given sequence is valid, then at last we should have only one "#" remaining in the stack
        //Notice: since we need frequently check nodes in the stack, it is better to use array or list to replace stack
        
        
        //here we use a boolean arary to replace stack, so we can immediately if a char is '#' at given index
        String[] strs = preorder.split(",");
        int n = strs.length;
        
        //we use StackIndex to indicate the last entry in stack now
        int StackIndex = 0;
        //true means a char is a '#'
        boolean[] stack = new boolean[n];
        
        
        for(int i = 0; i < n; i++){
            //early return
            if(stack[0]){
            	System.out.println("here");
            	return false; 
            }
            stack[StackIndex++] = strs[i].equals("#");
            while(StackIndex >= 3 && stack[StackIndex - 1] && stack[StackIndex - 2] && !stack[StackIndex - 3]){
                //we don't need to reset stack[] value after StackIndex i.e. stack[StackIndex - 2] and stack[StackIndex - 1]
            	//This is because we will set them anyway when we move pointer towards them
            	stack[StackIndex - 3] = true;
                
                StackIndex -= 2;
            }
        } 
        return StackIndex == 1 && stack[0];
    }

}
