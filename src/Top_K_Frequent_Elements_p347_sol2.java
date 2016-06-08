import java.util.*;

/**
 * Bucket sort solution
 * 
 * We firstly scan input and use a HashMap to record the occurrences of each input
 * Then we create an array of buckets. The occurrence is bounded by input size, so we are guaranteed each
 * input will be assigned to a bucket. Finally we need to scan buckets from large to small and update result
 * list accordingly
 * 
 * Time complexity:O(N)
 * Space complexity: O(N) as each element will be stored in hashMap
 * 
 * @author hpPlayer
 * @date Jun 7, 2016 11:43:58 PM
 */
public class Top_K_Frequent_Elements_p347_sol2 {
    public List<Integer> topKFrequent(int[] nums, int k) {
        //bucket sort solution. Use a hashMap to record input and its occurrences and use a buckets array to
        //assign each input into a bucket
        
        int n = nums.length;
        //HashMap that records the occurrences of each input
        HashMap<Integer, Integer> hs = new HashMap<Integer, Integer>();
        //an array of buckets. ith bucket means appearing ith times
        //The occurrences of an element <= n
        List<Integer>[] buckets = new List[n+1];
        //result list
        List<Integer> result = new ArrayList<Integer>();
        
        //update hashMap
        for(int num : nums){
            if(!hs.containsKey(num)){
                hs.put(num, 1);
            }else{
                hs.put(num, hs.get(num) + 1);
            }
        }
        
        //assign each element into bucket
        for(Integer num : hs.keySet()){
            int bucket = hs.get(num);
            if( buckets[bucket] == null ){
                buckets[bucket] = new ArrayList<Integer>();
            }
            buckets[bucket].add(num);
        }
        
        //read buckets backward and update result list
        for(int i = n; i > 0 && result.size() < k; i--){
            //we may not necessarily have elements in each bucket, then we need do null check first
            if( buckets[i] != null ) result.addAll( buckets[i] );
        }
        
        return result;
    }
}
