/**
 * Radix sorting solution
 * 
 * For radix sort, we just look at each num in nums[] digit by digit.
 * For each digit, we use a count[] to store the appearances of each number. Then we assign num in nums[] based on its number in current digit
 * to a temp[]. Then we assign values from temp[] back to nums[] to prepare for next digit
 * 
 * Running time is O(kn) where k is the count of digits for max number, n is the length of input array
 * Space complexity is O(n)
 * 
 * @author hpPlayer
 * @date Nov 9, 2015 1:03:19 AM
 */
public class Maximum_Gap_p164_sol2 {
    public int maximumGap(int[] nums) {
        //radix sort solution
        
        radixSort(nums);
        
        int result = 0;
        
        for(int i = 1; i < nums.length; i++){
            result = Math.max(result, nums[i] - nums[i-1]);
        }
        
        return result;
    }
    
    public void radixSort(int[] nums){
        int max = 0;
        //get the max num
        for(int num : nums) max = Math.max(max, num);
        //exp helps us to get value in each digit
        int exp = 1;
        //temp is a temp array that help us to store values in sorting
        int[] temp = new int[nums.length];
        
        //while we still have not reached the first digit in max number
        while(max/exp > 0){
            //an array to count the appearance of each number in current digit
            int[] count = new int[10];
            
            for(int num : nums){
                //for each input num, get the number in current digit and increase the counter accordingly
                count[num/exp%10]++;
            }
            
            //readjust the count[], so that each number in current digit get its new range in nums array
            for(int i = 1; i < 10; i++){
                count[i] += count[i-1];
            }
            
            //assign each num in nums[] to temp array based on the number in current digit
            //we will read nums[] backward, so larger number in nums[] will still get larger index in temp[]
            for(int i = nums.length - 1; i >= 0; i--){
                //remember to decrease first, as the number in count is 1 based, and now we are assigning
                //values with 0 based
                temp[ --count[nums[i]/exp%10] ] = nums[i];
            }
            
            //copy temp to count
            for(int i = 0; i < nums.length; i++){
                nums[i] = temp[i];
            }
            
            //increase exp to look at the next digit
            exp *= 10;
        }
        
    }
}
