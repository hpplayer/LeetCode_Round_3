/*
370. Range Addition

Assume you have an array of length n initialized with all 0's and are given k update operations.

Each operation is represented as a triplet: [startIndex, endIndex, inc] which increments each element
of subarray A[startIndex ... endIndex] (startIndex and endIndex inclusive) with inc.

Return the modified array after all k operations were executed.

Example:

Given:

    length = 5,
    updates = [
        [1,  3,  2],
        [2,  4,  3],
        [0,  2, -2]
    ]

Output:

    [-2, 0, 3, 5, 3]
Explanation:

Initial state:
[ 0, 0, 0, 0, 0 ]

After applying operation [1, 3, 2]:
[ 0, 2, 2, 2, 0 ]

After applying operation [2, 4, 3]:
[ 0, 2, 5, 5, 3 ]

After applying operation [0, 2, -2]:
[-2, 0, 3, 5, 3 ]

Hint:

Thinking of using advanced data structures? You are thinking it too complicated.
For each update operation, do you really need to update all elements between i and j?
Update only the first and end element is sufficient.
The optimal time complexity is O(k + n) and uses O(1) extra space.
*/

/**
 * Brainstorming solution
 * 
 * This idea behind this solution is very similar to subarray sum solution, where we cache the sums
 * from 0 to index i then do math calculation to get the subarray sum.
 * 
 * Here instead, we will mark the start and end + 1 indexes and just add offset there
 * We add offset to index start and add -offset to end + 1, so starting from start, array will begin
 * have the offset and starting from end + 1, array will not have offset. After that we just need 
 * one more scan the accumulate offsets
 * 
 * ex: input [0, 2, 2], [1, 2, 1], len 4
 * during update: array =>  [2, 0, 0, -2] => [2, 1, 0, -3]
 * last scan: array => [2, 3, 3, 0]
 * 
 * Time complexity: O(k + n)
 * Space complexity: O(1)
 * 
 * @author hpPlayer
 * @date Jun 28, 2016 10:14:12 PM
 */
public class Range_Addition_p370_sol1 {
    public int[] getModifiedArray(int length, int[][] updates) {
        int[] result = new int[length];
        
        for(int[] update : updates){
            int start = update[0];
            int end = update[1];
            int val = update[2];
            
            //update start cell to mark where to start adding this offset
            result[start] += val;
            //mark end + 1 cell to mark where to stop adding this offset
            if( end + 1 < length ) result[end + 1] -= val; 
        }
        
        //since array starts with 1, result[i-1] actually contain offsets that we need to add at index i
        //if we dont want some offsets from it, the curr value in result[i] already have its negative value of 
        //those offsets
        for(int i = 1; i < length; i++){
            result[i] += result[i-1];
        }
        
        return result;
    }
}
