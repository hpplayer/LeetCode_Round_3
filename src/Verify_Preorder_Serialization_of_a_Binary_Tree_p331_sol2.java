/**
 * Tree solution
 * 
 * Basically this solution uses the count of incoming and outgoing edges to make sure the tree is valid
 * Each null node has one incoming edge
 * Each normal node has one incoming edge and two outgoing edges
 * Root only has two outgoing edges
 *  
 * Considering the whole tree, the diff should be 0, since root node does not have incoming edge
 * 
 * Initially we set the diff to be - 1, to remove the count from the incoming edge to root node
 * During the process, we firstly count the incoming edges, then count the outgoing edges.
 * If the tree is balanced and valid, then the count of incoming edges will never make diff > 0 since it should offset some outgoing from above layer
 * If we found diff > 0, then just report invalid input. Then we count the outgoing edges only for those normals nodes as null node does not have 
 * outgoing edges. Finally we just check the final diff to see if it is 0. 
 * 
 * Remark:
 * We can't count the outgoing and incoming edges at the same time. Otherwise the order of "#" and normal node will not be guaranteed
 * Time complexity: O(n)
 * Space complexity: O(n)
 * 
 * @author hpPlayer
 * @date Feb 18, 2016 10:44:31 PM
 */
public class Verify_Preorder_Serialization_of_a_Binary_Tree_p331_sol2 {
	public static void main(String[] args){
		String str = "1,#,#";
		String str2 = "#,7,6,9,#,#,#";
		System.out.println(isValidSerialization(str2));
	}
	
    public static boolean isValidSerialization(String preorder) {
        //This is a solution that we will count the num of outgoing and incoming edges and calculate the diff between them
        //for a null node, it has only one incoming edge
        //for a non-null node, it has only one incoming edge and two outgoing edges (except for the root)
        //Root node does not have incoming edge, but to treat it like genereal nodes we will pre-set the diff to include it
        //So for a valid binary tree, we let diff to be num Of incoming - num Of outgoing, during the traversal, we should never 
        //get the diff > 0 and we should finally get diff = 0, as we have deduct the incoming edge for root node
        
        String[] strs = preorder.split(",");
        
        //set to -1 to deduct the incoming edge for root
        int diff = -1;
        
        for(int i = 0; i < strs.length; i++){
            //we firstly count the incoming edge
            //we can't count incoming and outgoing edge at the same time. Otherwise we will not guarantee the order or "#" and normal node are correct
            if(++diff > 0) return false;
            
            //then we count the outgoing edge only for normal node
            if(!strs[i].equals("#")) diff -= 2; 
        }
        
        //then we check the diff to see if it is 0
        return diff == 0;
    }
}
