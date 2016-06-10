import java.util.*;
/*
354. Russian Doll Envelopes

You have a number of envelopes with widths and heights given as a pair of integers (w, h).
One envelope can fit into another if and only if both the width and height of one envelope is greater than the width
and height of the other envelope.

What is the maximum number of envelopes can you Russian doll? (put one inside other)

Example:
Given envelopes = [[5,4],[6,4],[6,7],[2,3]], the maximum number of envelopes you can Russian doll is 
([2,3] => [5,4] => [6,7]).
*/

/**
 * DP + binary search solution
 * 
 * This solution is very similar to Longest_Increasing_Subsequence_p300_sol1, but we need some preprocecess here
 * We firstly sort the input matrix based on the int[0]. In case a[0] != b[0], then we put smaller int[] ahead.
 * In case a[0] == b[0], then we put the int[] with larger int[1] ahead.
 * 
 * Why is that? 
 * We want convert the problem into longest-increasing-subsequence, we firstly sort matrix based on int[0], then 
 * values on int[1] can be treated as the longest-increasing-subsequence problem.
 * so we put int[] with same int[0] and larger int[1] ahead, then we will count those int[]s only once.
 * ex. int[3][2], int[3][4], we have a increasing-subsequence 2->4 but with int[3][4], int[3][2], our subsequence
 * only has len 1
 * 
 * The rest of the problem is similar to Longest_Increasing_Subsequence_p300_sol1.
 * 
 * Time complexity: O(NlogN): sorting O(nlogN), binary_search O(nlogn)
 * Space complexity: O(N)
 * 
 * Sol1 is DP + binary_search solution, which is very quick
 * Sol2 is pure DP solution, which is close to brute-force solution and a little bit slow
 * 
 * @author hpPlayer
 * @date Jun 9, 2016 9:01:14 PM
 */
public class Russian_Doll_Envelopes_p354_sol1 {
    public int maxEnvelopes(int[][] envelopes) {
        //DP + binary_search solution. We firstly sort the matrix on int[0], then we search the longest-increasing
        //subsequence based on int[1]
        
        //boundary check
        if(envelopes.length == 0) return 0;
        
        //firstly sort the matrix based on int[0], In case int[0] are same, then we will put larger int[1] ahead
        //so that we only count those int[]s once with same int[0]
        Arrays.sort( envelopes, new Comparator<int[]>(){
            public int compare(int[] a, int[] b){
                if(a[0] == b[0]){
                    return b[1] - a[1];
                }else{
                    return a[0] - b[0];
                }
            }
        } );
        
        //points to the last created increasing-subsequence
        int curr_index = 0;
        //each cell represents the tail value in increasing-subsequences of different len
        int[] dp = new int[envelopes.length];
        dp[0] = envelopes[0][1];
        
        for(int i = 1; i < envelopes.length; i++){
            int num = envelopes[i][1];
            
            if( num < dp[0] ){
                //update dp[0] to num, so we can extend subsequence more easier with smaller tail
                dp[0] = num;
            }else if( num > dp[curr_index] ){
                //we can extend curr longest subsequence, very good!
                dp[++curr_index] = num;
            }else{
                //we find the index of first subsequence with tail value > num
                //then replace this tail value, so we can extend this subsequence more easier!
                int indexOfFirstLargerSubsequence = binary_search( dp, curr_index, num );
                
                dp[indexOfFirstLargerSubsequence] = num;
            }
        }
        
        //curr_index is 0 based, so we + 1 to get the len
        return curr_index + 1;
    }
    
    public int binary_search(int[] nums, int end, int target){
        //if we can't find target in nums, then this binary search method will return the index of first 
        //increasing-subsequence that has tail value > target
        
        int start = 0;
        
        while(start <= end){
            int mid = start + (end - start)/2;
            
            if( nums[mid] == target ){
                return mid;
            }else if(nums[mid] < target){
                //too small
                start = mid + 1;
            }else{
                //too large
                end = mid - 1;
            }
        }
        
        //when we check last index with start = end, if nums[start] > target, then we will move end, start stay still
        //if nums[start] < target, then we will move start
        //In either way, start always points to the first num > target, so we return start here
        return start;
    }
}
