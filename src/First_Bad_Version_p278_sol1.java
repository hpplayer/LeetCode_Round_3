/*
First Bad Version

You are a product manager and currently leading a team to develop a new product.
Unfortunately, the latest version of your product fails the quality check.
Since each version is developed based on the previous version, all the versions after a bad version are also bad.

Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the following ones to be bad.

You are given an API bool isBadVersion(version) which will return whether version is bad.
Implement a function to find the first bad version. You should minimize the number of calls to the API.
*/

/**
 * Binary search problem
 * 
 * We just use standard binary search method to locate the first bad version
 * 
 * Time complexity is O(logN), space complexity is O(1)
 * 
 * @author hpPlayer
 * @date Nov 4, 2015 2:05:56 AM
 */
public class First_Bad_Version_p278_sol1 {
    public int firstBadVersion(int n) {
        int start = 1, end = n;
        
        while(start < end){
            int mid = start + (end - start)/2;
            if(isBadVersion(mid)){
                //if mid is bad, then we need to search left part and include mid
                end = mid;
            }else{
                //if mid is not bad, then we need to search right part and exclude mid
                start = mid + 1;
            }
        }
        
        //since we stop the while look when start == end, return either start or end both ok
        return start;
    }
    
    boolean isBadVersion(int version){return true;}; 
}
