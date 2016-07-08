import java.util.*;

/*
373. Find K Pairs with Smallest Sums

You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.

Define a pair (u,v) which consists of one element from the first array and one element from the second array.

Find the k pairs (u1,v1),(u2,v2) ...(uk,vk) with the smallest sums.

Example 1:
Given nums1 = [1,7,11], nums2 = [2,4,6],  k = 3

Return: [1,2],[1,4],[1,6]

The first 3 pairs are returned from the sequence:
[1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
Example 2:
Given nums1 = [1,1,2], nums2 = [1,2,3],  k = 2

Return: [1,1],[1,1]

The first 2 pairs are returned from the sequence:
[1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
Example 3:
Given nums1 = [1,2], nums2 = [3],  k = 3 

Return: [1,3],[2,3]

All possible pairs are returned from the sequence:
[1,3],[2,3]
*/

/**
 * Heap solution
 * 
 * We can't use multiple pointers solution, since we may have the case that we choose to move a pointer in
 * one array but later a prev num in this array can make a smaller sum pair.
 * 
 * So we have to use a heap to get current smallest sum pair, and we also need to keep track the indexes of current
 * smallest sum pair. Then we will try to build next smaller sum by moving indexes for this current smallest sum pair.
 * 
 * However in this way, we may create duplicates since we move pointers just based on smallest sum pair,
 * not based on indexes. To avoid duplicates, we can use a HashSet or a matrix. To save space, I use a matrix
 * in this solution
 * 
 * Time complexity: O(klogK)
 * Space complexity: O(klogK)
 * 
 * @author hpPlayer
 * @date Jul 7, 2016 9:42:19 PM
 */
public class Find_K_Pairs_with_Smallest_Sums_p373_sol1 {
    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        //boundary check
        if(nums1.length == 0 || nums2.length == 0) return new ArrayList<int[]>();
        
        PriorityQueue<MyNode> pq = new PriorityQueue<MyNode>(new Comparator<MyNode>(){
            public int compare(MyNode a, MyNode b){
                return a.sum - b.sum;
            }
        });
        
        int m = nums1.length;
        int n = nums2.length;
        
        List<int[]> result = new ArrayList<int[]>();
        pq.offer( new MyNode(0, 0, nums1[0] + nums2[0]) );
        boolean[][] visited = new boolean[m][n];
        visited[0][0] = true;
        
        for(int i = 0; i < Math.min(m*n, k); i++){
            MyNode curr = pq.poll();
            
            result.add( new int[]{nums1[curr.x], nums2[curr.y]} );
            
            if( curr.x + 1 < m && !visited[curr.x+1][curr.y] ){
                pq.offer( new MyNode(curr.x + 1, curr.y, nums1[curr.x + 1] + nums2[curr.y] ) );
                visited[curr.x+1][curr.y] = true;
            } 
            
            if( curr.y + 1 < n && !visited[curr.x][curr.y+1] ){
                pq.offer( new MyNode(curr.x, curr.y + 1, nums1[curr.x] + nums2[curr.y + 1] ) );
                visited[curr.x][curr.y+1] = true;
            }
        }
        
        return result;
    }

    private class MyNode{
        int x;
        int y;
        int sum;
        
        MyNode(int x, int y, int sum){
            this.x = x;
            this.y = y;
            this.sum = sum;
        }
    }
}
