import java.util.*;
/*
Contains Duplicate III

Given an array of integers, find out whether there are two distinct indices i and j in the array
 such that the difference between nums[i] and nums[j] is at most t and the difference between i and j is at most k.
*/

/**
 * Bucketing + sliding window solution
 * 
 * We use two pointer to keep a sliding window of size k
 * Then we use bucketing algorithm inside the window to find two values within t range
 * 
 * In sliding window, we have to move left pointer with right pointer after the window size become k, so we can always maintain a window size with k
 * 
 * In the bucketing, we maintain numbers in each bucket are within k range. say t = 3, then, we should have [1,2,3,4]
 * So the way we used to get bucket number is num/(t+1). We may have negative number in the input as well. We would get error if abs(t) > abs(neg)
 * like t : 1 and input is [-1,0,1], then -1/2 = 0/2 = 1/2 = 0, so it would be incorrect. In this solution, we have to subtract Integer.MIN_VALUE
 * for each input to convert negatives to positives. This problem also have large t as well, so it is better for us to use long type through
 * the whole program
 * 
 * In the bucketing, if we found any two numbers are in same bucket or in neighbor buckets but have difference <= t, then we should return 
 * true.
 * 
 * Time complexity: O(n) as bucketing costs O(n), sliding window also costs(O(n))
 * Space complexity: O(t) as we have to maintain a set of buckets with size O(t)
 * 
 * Sol1 is the bucketing + sliding window solution. We use bucketing to sort numbers in each window
 * Sol2 is the TreeSet + sliding window solution, we use TreeSet to sort numbers in each window
 * 
 * Another bucketing problem: Maximum_Gap_p164_sol1
 * 
 * @author hpPlayer
 * @date Nov 11, 2015 11:58:09 PM
 */
public class Contains_Duplicate_III_p220_sol1 {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        //bucketing solution, we hold a set of buckets in which index range is <= k
        //we use bucketing technique to find if there is any two value is in same bucket that has val difference < t
        
        //boundary check
        if(nums.length == 0 || t < 0 || k < 1) return false;
        
        //Since we need to dynamically maintain the buckets, it is better to use hashMap than fixed array 
        //There are large data in the input, so we have to use long type
        HashMap<Long, Long> buckets = new HashMap<Long, Long>();
        //left variable controls the boundary of buckets 
        int left = 0;
        
        for(int i = 0; i < nums.length; i++){
            if(i > k){//after right boundary reach k, each time we move right pointer, we should move left pointer as well
                //We may have negative case which could cause trouble in bucketing, ex: -1/5 = 1/5 = 0
                //so we have to let val = val - Integer.MIN_VALUE, and let neg convert to positive
                long val = (long) nums[left] - Integer.MIN_VALUE;
                //to include numbers within range t into one bucket, we need let val/(t+1)
                //like t = 3, and one bucket should include [0, 1, 2, 3]
                //we have large input t as well, so case it to long first 
                long bucket = val / ((long) t + 1);
                
                buckets.remove(bucket);
                left ++;
            }
            
            //get bucket number
            long val = (long) nums[i] - Integer.MIN_VALUE;
            long bucket =  val / ((long) t + 1);
            
            //if two values in same bucket, or they are in neghbor cells but have difference < k, we found duplicate!
            if(buckets.containsKey(bucket) || buckets.containsKey(bucket-1) && val - buckets.get(bucket-1) <= t ||buckets.containsKey(bucket+1) && buckets.get(bucket + 1) - val<= t){
                return true;
            }
            
            //put current val into bucket
            buckets.put(bucket, val);
        }
        
        return false;
    }
}
