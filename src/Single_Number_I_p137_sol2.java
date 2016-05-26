/**
 * Bit manipulation solution
 * 
 * We go through each index in 32 bits. And count how many 1 bit we have in that index. We will reset counter once
 * we have collected three 1 bits in that index, which comes from elements that appear three times. After the counting
 * is done, if counter != 0, then it indicates the single number have 1 in that index, we just set it to 1 in result
 * 
 * Time complexity: O(32N) = O(N)
 * Space complexity: O(1)
 * 
 * @author hpPlayer
 * @date May 26, 2016 12:02:19 AM
 */
public class Single_Number_I_p137_sol2 {
    public int singleNumber(int[] nums) {
        //bit manipulation solution. We build result num bit by bit
        int result = 0;
        
        //loop 32 times to update each bit
        for(int i = 0; i < 32; i++){
            //count how many 1 appears in this index
            int count = 0;
            for(int num : nums){
                if( ((num >> i)&1) == 1){
                    count++;
                    //reset count once 1 has appeared three times
                    //or we can use if(count == 3) count = 0 to reset counter when an element appears three times
                    count %= 3;
                }
            }
            //In above operation %= 3 has removed all other elements from this index. now count indicates
            //if we have 1 in this index that comes from single number
            if(count != 0) result |= (1 << i);
        }
        
        return result;
    }
}
