import java.util.Arrays;
/*
 * 
307. Range Sum Query - Mutable 

Given an integer array nums, find the sum of the elements between indices i and j (i ¡Ü j), inclusive.

The update(i, val) function modifies nums by updating the element at index i to val.
Example:
Given nums = [1, 3, 5]

sumRange(0, 2) -> 9
update(1, 2)
sumRange(0, 2) -> 8

Note:
The array is only modifiable by the update function.
You may assume the number of calls to update and sumRange function is distributed evenly.

*/

/**
 * Segment tree array solution
 * 
 * We create a segment tree for the input num, and use an array to represent the tree
 * Segment tree:
 * a tree that contains information between a range. all inputs are in Leaf nodes. Other nodes just represent
 * the information between a range.
 * say our input is 4, 2, then our segment tree built for sum query will be :
 *   6  <- node we build to contain sum information
 * 4   2
 * Leaf node will contain n nodes (leaf node <-> input), therefore there will be totally 2 * n - 1 nodes in 
 * the tree. For convenience, we use a 2 * n array to represent segment tree, so our node order starts with 1,
 * and it makes us more easily to handle indexing. (Notice: we will never touch tree[0] since we have an extra cell)
 * 
 * Here is the basic idea:
 * we create the segment tree array, then iteratively update the array if the input is updated. If we need 
 * query sum in a range, then we iteratively visit the tree and accumulate the sum
 * 
 * For node n, why its left child and right child are 2n and 2n+1?
 * 
 * Perfect tree: except for the leaf nodes, all other nodes have two child nodes
 * We need to realize that in a perfect tree, the num of nodes in level n will be the total num of nodes 
 * in prev levels + 1. For example, if we have a perfect tree with 3 levels, prev two levels have 3 nodes,
 * the last level have 4 nodes. 
 * Now lets look at the node order in the perfect tree:
 * 
 * 					            n-1 
 *                              |
 *      		        .....   v
 * 		    o        o .......  o         o <- n
 *  	 o    o   o    o.....o     o   o     o <--last node in last lvl, if our order starts with 1, now it should be 2 * n + 1
 *                           ¡ü     ¡ü   ¡ü     ¡ü
 *                          2n-2  2n-1  2n   2n +1	
 *                             		
 * Here we observed that the order numbers of left and right child node are 2*n and 2*n+1 respectively, if 
 * start our node order with 1. Our segment tree may not be a perfect tree, but a complete tree where nodes
 * are as left as possible in last level if we do not have enough nodes. In such scenario, above observation
 * still apply
 *  
 * Why left child is in even order and right is in odd order? 
 * Think about base case, since we start order with 1, then:
 *  1 
 * 2 3
 * So we found left child is always even, right child is always odd
 * 
 * Time complexity:
 * Build segment tree array: O(2 * N), each cell in array will be updated
 * update: O(logN), our tree is a complete tree, and we need go through each layer to update the node
 * SumQuery: O(logN), we have preprocessed and updated the array, therefore we just need to query cell in
 * this step, we only need to look at left and right boundary nodes in each layer, and we need go through
 * logN layer
 * 
 * Space complexity:
 * O(2 * N) as we need an O(2 * N) array to store nodes
 * 
 * Sol1 provides a segment tree approach
 * Sol2 provides a BIT approach which is faster (O(2*N) vs O(NlogN))
 * 
 * @author hpPlayer
 * @date Apr 20, 2016 9:37:19 PM
 */
public class Range_Sum_Query_Mutable_p307_sol1 {
	public static void main(String[] args){
		Range_Sum_Query_Mutable_p307_sol1 test = new Range_Sum_Query_Mutable_p307_sol1(new int[]{1,3,5} );
		test.sumRange(0, 2);
	}
    //Segment tree solution (array version)
    //We can use segment tree to organize sums of subarray, and only update affected sums.
    
    //use array to represent segment tree
    int[] tree;
    //use n to store the len of array
    int n;
    
    public Range_Sum_Query_Mutable_p307_sol1(int[] nums) {
        n = nums.length;
        tree = new int[n * 2];
        buildTree(nums);
    }
    
    public void buildTree(int[] nums){
        //we will build the tree bottom-up, therefore we firstly fill the right part of tree[], which contains leaf nodes
        for(int i = n, j = 0; i < 2 * n; j++, i++){
            tree[i] = nums[j];
        }
        //then we will build tree layer by layer. Inside each layer, we will build the node from right to left
        //so overall the tree[] is updated from right to left
        for(int i = n-1; i >= 0; i--){
            //For each two child node, we will build a parent node in mid, therefore if two child nodes are 2i and 2i +1, then the root node will be i
            //we let left child to be even index and right child to be odd index
            tree[i] = tree[i*2] + tree[i*2 + 1];
        }
    }
    
    void update(int i, int val) {
        //we will update the segment tree bot-up, therefore we firstly update the leaf node
        i += n;
        tree[i] = val;
        
        //then update all affected ancestor nodes
        //array[0] does not represent any node(think about case that we have only one node in segment tree while the array still has len 2) 
        while(i >= 1){
        	//we iteratively update left and right based on curr i until i reaches 0
        	
            //define left and right child
            int left = i;
            int right = i;
            //based on index i, we will find if it is left or right child, therefore we can find the other child
            if( (i&1) == 0){
            	//i is left child, therefore we need update right child
                right++;
            }else{
            	//i is right child, therefore we need update left child
                left--;
            }
            
            //update parent node
            tree[i/2] = tree[left] + tree[right];
            //update index
            i/=2;
        }
    }

    public int sumRange(int i, int j) {
        //the given i and j defines the search range in leaf layer, then we can just get the sum by scanning the tree bot-up
        int sum = 0;
        i += n;
        j += n;
        
        //we will update sum inside the look therefore we need include i == j case 
        //we iteratively go through the tree and calculate the sum until whole range is covered
        while(i <= j){
            //if range left boundary is the right child of a node, then we will not visit this node to avoid add unnecessary vals,
            //instead, we just add val of curr left boundary node, and go to its sibling, then go to up layers from its sibling
            if( (i&1) == 1 ){
                sum += tree[i];
                i++;
            }
            
            //if range right boundary is the left child of a node, then we will not visit this node to avoid add unnecessary vals,
            //instead, we just add val of curr right boundary node, and go to its sibling, then go to up layers from its sibling
            if( (j&1) == 0){
                sum += tree[j];
                j--;
            }
            
            //otherwise we continue going up to the parent node whose value contains sum of curr subtree
            i /= 2;
            j /=2;
        }
        
        return sum;
    }
}
//Your NumArray object will be instantiated and called as such:
//NumArray numArray = new NumArray(nums);
//numArray.sumRange(0, 1);
//numArray.update(1, 10);
//numArray.sumRange(1, 2);