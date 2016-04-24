/**
 * Binary Indexed Tree
 * 
 * In this solution, we build a BIT based on input.
 * BIT is built based on the binary representation of index. Lets firstly see the diagram of a BIT:
 *              0
 *     1     2     4     8
 *    - -  3   - 5   6 -   -     
 *                  -  7
 * (num listed in above diagram is just the indexing of each node, we will store range sum in each node)
 * 
 * The root node of BIT is a dummy node which has index 0 and it does not store any value.
 * 
 * The nodes in second layer are split based on power of 2s. So the starting node in second layer are 2^0 (1), 2^1(2),
 * 2^2(4), etc. The basic idea of BIT is that all positive number can by represented as sum of powers of 2. For example
 * 5 = 2^2 + 2^0. So we can split the whole range into several smaller range based on binary representation.
 * node 1 in second layer means the range from 0 to 0 in input array, node 2 in second layer means the range from 0 to
 * 3 in input array.
 * 
 * The nodes in third layer are the binary complementary nodes to second layer.
 * Lets look at node 2 and node 4 in second layer, they have a gap. And we need a node 3 to fill this gap, and this node
 * is located at third layer. If looking at the binary representation, 10 100, we need a node with 11. In BIT we will place
 * this node on the left of 3. (Notice: in BIT, we may not necessary only have 2 child, we may have 3 child nodes as well!!!!)
 * 
 * Same rules applied to following layers as well.
 * 
 * Due to the way we construct BIT, we notice that the parent node and each node are differed by the last set bit in binary 
 * representation. we can get parent node by removing the last set bit in binary representation. For example: 7(111)->6(110)
 * 
 * There are two important operations to a BIT: update and getSum
 * 
 * In update, we need find all nodes that are affected by updating current node. Since each node only contains the range_sum
 * we don't need to follow edges to update nodes as the edge connected node may not be affected, instead we may 
 * have sibling nodes affected. For example when cell[2] is updated, instead of update cell[3], we will update cell[4], cell[8]
 * and other related nodes as cell[3] only holds the sum ranged from 2(exclusive) to 3(inclusive) i.e. 3 to 3 but cells[4] holds
 * the sum ranged from 1 to 4 and cell[8] holds the sum ranged from 1 to 8, obviously they are both affected by updating cell[2]
 * To get the binary related next node in BIT, we need use n += n & -n. (this operation jsut add 1 to the last set bit in curr index)
 * 
 * In getSum, we need find all prev nodes that compose the target range. As mentioned above, nodes in each layer are 
 * just complementary nodes to nodes in above layer, so we just need to follow edges to accumulate range_sums until we 
 * reach root.For example to get the sum between 1 and 7. We need cell[7], cell[6] and cell[4]. Since cell[7] contains sum
 * between 7 and 7, cell[6] contains sum between 5 and 7 and cell[4] contains sum between 1 and 4. Their accumulated sum
 * represent the sum in range between 1 to 7  
 * To get the parent node, we need use n -= n & -n. (this operation just removes the last set bit in curr index)
 * 
 * In this solution we use an array to represent the tree. Since our BIT uses 0 as dummy node and we does not store 
 * value in dummy node, we need convert the 0-based indexing from input to make index adapt to our 1-based indexing
 * in BIT. To achieve that, we just need to add 1 to all input indexes.
 * 
 * Time complexity: 
 * Build Tree O(NlogN)
 * Update Tree O(logN)
 * SumQuery O(logN)
 * 
 * Space complexity:O(N)
 * 
 * Remark:
 * Unlike sol1, where indexing in tree starts with 1 as well but not including root node, here we include root node
 * so we need + 1 to convert indexing
 * 
 * @author hpPlayer
 * @date Apr 21, 2016 11:05:46 PM
 */
public class Range_Sum_Query_Mutable_p307_sol2 {
	public static void main(String[] args){
		Range_Sum_Query_Mutable_p307_sol2 test = new Range_Sum_Query_Mutable_p307_sol2(new int[]{1,3,5, 6, 7, 8,9,10,11});
		System.out.println(test.sumRange(0, 2));
		test.update(0, 3);
		System.out.println(test.sumRange(0, 2));
	}
	
    //BIT solution. BIT is super good in solving such sum range query problem.
    
    int[] nums;
    int[] BIT;
    int n;
    
    public Range_Sum_Query_Mutable_p307_sol2(int[] nums) {
        this.nums = nums;
        n = nums.length;
        //To make building tree more convenient, we let node with index 0 becomes a dummy node, and our real 
        //nodes start with index 1. This is because structure of BIT is based on binary representation, in other words, we decompose each indexing number into a series of numbers of power of 2. like 5 = 2^2 + 2^0, and
        //but we can't decompose index 0 into such numbers of power of 2, therefore our nodes start with index1
        BIT = new int[n + 1];
        buildTree();
    }

    public void buildTree(){
        //build tree is similar to update tree. we firstly update val in target node, then following rules
        //to update all other nodes that are related to this target node.
        //what is the rule? for updating/building tree, the rule is next index = curr index + (curr index & 2's complement of curr index) or j = i + (i & -i);
        //Notice: our tree is binary indexed, which means even if two nodes are not connected, they may still 
        //be related since they may be connected in binary representation
        for(int i = 0; i < n; i++){
            //since BIT[] starts with cell 1, we need plus 1 to nums[] indexing which starts with cell 0
            int j = i + 1;    
            //we keep updating BIT[] for each nums[] until we reach boundary of BIT[]
            while( j <= n ){
                //each node may already be updated in prev loop, we need use accumulative way to update cell 
                BIT[j] += nums[i];
                //go to next binary related node by following above rule 
                j += j & -j;
            }
        }
    }
    
    void update(int i, int val) {
        //update tree is similar to buildTree(), but now we only have one input
        
        //convert indexing system to start with 1
        int j = i + 1;
        //record diff, then update cells based on this diff
        int diff = val - nums[i];
        //update val in nums[] as well, in case we will use cell i afterward
        nums[i] = val;
        
        while(j <= n){
            //update each cell based on diff
            BIT[j] += diff;    
            //get next binary related node by following rules defined in buildTree()
            j += j & -j;
        }
    }

    public int sumRange(int i, int j) {
        //Our tree is rooted at dummy node with index 0 and built based on binary indexing,
        //in order to get sum query in any range, we will get sum range from 1 to i and j respectively,
        //then we can get range in i to j
        //Therefore, we need get sum range in 1 to j, and 1 to i -1, their difference will be sum in i to j
        return getSum(j) - getSum(i-1);
    }
    
    public int getSum(int i){
        //convert indexing system to start with 1
        int j = i + 1;
        int sum = 0;
        //to get sum in range 0 to i, we need to query the tree in reversed way as compared with buildTree()
        //In updating/building tree we need to update all following nodes that are binary related to updated/initalized node
        //in querying sum we need to get to know all previous nodes that binary related to given index i.
        //each prev node contains a sum info in its range, we need to accumualte those sums together to get sum
        //from 1 to i
        //Similarly here we use a rule to get the prev node:
        //prev index = curr index - (curr index & 2's complement of curr index) or j = i - (i & -i);
        //Notice: based on the structure of BIT, here when we follow the rule to find the prev node, we acutally
        //are following the edges in the BIT to go to parent node. In BIT, parent node can be got by removing the 
        //last set bit from binary representation like parent of 5 and 6 is 4 (101 and 110, after removing last set bit 1, we will get 100). And i - (i & -i) operation is doing this removal
        while( j > 0){
            sum += BIT[j];
            //get prev binary related node by following rules defined above.
            //In getSum(), the prev binary related node is just its parent node
            j -= j & -j;
        }
        return sum;
    }
}
//Your NumArray object will be instantiated and called as such:
//NumArray numArray = new NumArray(nums);
//numArray.sumRange(0, 1);
//numArray.update(1, 10);
//numArray.sumRange(1, 2);