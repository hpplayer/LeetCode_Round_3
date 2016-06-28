/*
Maximum Gap

Given an unsorted array, find the maximum difference between the successive elements in its sorted form.

Try to solve it in linear time/space.

Return 0 if the array contains less than 2 elements.

You may assume all elements in the array are non-negative integers and fit in the 32-bit signed integer range.
*/

/**
 * Bucketing solution
 * 
 * We firstly get the max and min value in the input array. Then get the difference and divide it by the gaps we have. The result 
 * will be the least max gap we have for the input array. Then we let each bucket has range smaller than this gap value, so we just
 * need to look at the difference between consecutive buckets. Then we assign values in input array to different buckets and find the
 * max gap between two buckets
 * 
 * Remark:
 * 1. Remember to check the difference between min and max, in case all values are same!!!!!!!!!!!!!!!!!
 * 2. Take ceilings to get the gap and numOfBuckets
 * 3. Use prev variable to hold the previous bucket's max value in case two gaps have invalids buckets in between
 * 
 * 
 * Running time: O(n)
 * Space complexity: O(n)
 * 
 * Sol1 is a bucketing solution
 * Sol2 is a radix-sort solution
 * 
 * Both solution runs in O(n) time
 * 
 * Another bucketing problem:
 * Contains_Duplicate_III_p220_sol1
 * 
 * @author hpPlayer
 * @date Nov 9, 2015 12:31:29 AM
 */
public class Maximum_Gap_p164_sol1 {
    class bucket{
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
    }
    public int maximumGap(int[] nums) {
        //bucketing solution
        
        //boundary check
        if(nums.length < 2) return 0;
        
        int min = Integer.MAX_VALUE;
        int max = 0;
        
        
        for(int num : nums){
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        
        //in case all values are same
        if(max == min) return 0;
        
        //the variable gap is least maximum gap we will get for the input array 
        int gap = (int) (Math.ceil( (max-min)/(nums.length -1.0)));
        
        //we adjust the bucket size to make sure the range in each bucket will be smaller than gap
        //so at least we can guarantee to get the least maximum gap
        int numOfBuckets = (int) (Math.ceil((max-min)/(gap*1.0)) + 1);
        
        //create buckets
        bucket[] buckets = new bucket[numOfBuckets];
        
        //initialize buckets
        for(int i = 0; i < buckets.length; i++) buckets[i] = new bucket();
        
        //assign input num to each bucket
        for(int num : nums){
            //min may not be zero, our buckets are divided based on (max-min)
            //so we use (num-min)/gap to get correct index
            int index = (num - min)/gap;
            
            buckets[index].max = Math.max(buckets[index].max, num);
            buckets[index].min = Math.min(buckets[index].min, num);
        }
        
        //find the max gap among buckets
        int result = 0;
        //since buckets may not be consecutive, we need to use prev to record the max value of last valid bucket
        //we set its initial value to be min, so we won't have gap value between boundary and the first bucket  
        int prev = min;
        
        for(bucket temp : buckets){
            //if case we don't have value in current bucket, we just skip it
        	//we cant compare temp.min with Integer.MAX_VALUE, since int_max may appear in the input, so we may actualy have a 
        	//bucket with max = int_max. By contrast, we wouldn't have negative in the input, therefore if max = int_min, it means 
        	//we definitely don't have num in this bucket
            if(temp.max == Integer.MIN_VALUE) continue;
            //otherwise, we use current bucket.min - last valid bucket.max to get the gap between two buckets
            result = Math.max(result, temp.min - prev);
            //update prev to current bucket.max
            prev = temp.max;
        }
        
        return result;
    }
}
