/**
 * Block-swapping solution
 * 
 * Each time we place k elements into correct solution. Then doing the same thing to the remaining subarray. Since the subarray's len maybe shrink, we 
 * need to update k as well, otherwise we may have a k larger than the subarray, which means we have to rotate a loop before we can actually rotate some cells.
 * We also need two variables to hold the start of remaining subarray and len of remaining subarray. We iteratively do this until k = 0, which means we don't
 * need to rotate cells anymore. 
 * Since each time we will swap two blocks with k elemnts, this algorithm is also called Block-swapping solution
 * 
 * Time complexity: O(N), in case input k = 1, each time we will only put one cell into correct solution
 * Space complexity: O(1)
 * 
 * @author hpPlayer
 * @date Mar 11, 2016 12:23:15 PM
 */
public class Rotate_Array_p189_sol2 {
    public void rotate(int[] nums, int k) {
        //swapping-block algorithm, each time we will swap two blocks to put a block of elements into correct cells
        
        //len of input array, and later will be updated to len of subarray
        int len = nums.length;
        //remove redundant k
        k %= len;
        //start record the start of subarray 
        int start = 0;
        
        while(k > 0){
            //while there is still block left that need to be placed into correct solution, we will continue loop
            
            //each time we will a block of cells with k size
            for(int i = 0; i < k; i++){
                swap(start + i, start + len - k + i, nums);
            }
            
            //update start and len, since each time we swapped k cells, therefore new start will be += k, and new len will be -= k
            start += k;
            len -= k;
            
            //update k to match new shrinked subarray
            k %= len;
        }
    }
    
    public void swap(int left, int right, int[] nums){
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }
}
