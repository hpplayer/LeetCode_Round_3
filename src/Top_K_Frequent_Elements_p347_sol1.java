import java.util.*;

/*
347. Top K Frequent Elements

Given a non-empty array of integers, return the k most frequent elements.

For example,
Given [1,1,1,2,2,3] and k = 2, return [1,2].

Note: 
You may assume k is always valid, 1 ¡Ü k ¡Ü number of unique elements.
Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
*/

/**
 * PriorityQueue + HashMap solution
 * 
 * We use an inner class to get the count for each element. 
 * Then we maintain a min PriorityQueue. once pq size > k, we will pop the element with least occurrences
 * So that the update would be log(K), not log(N)
 * 
 * Finally we just need to read all elements in queue to the result list
 * 
 * Time complexity: O(NlogK)
 * Space complexity: O(k) as result list is O(k) size
 * 
 * Sol1 is the pq + hashMap solution, which is O(NlogK) time
 * Sol2 is bucketSort solution, which gives O(N) time 
 * 
 * @author hpPlayer
 * @date Jun 7, 2016 11:22:29 PM
 */
public class Top_K_Frequent_Elements_p347_sol1 {
    public List<Integer> topKFrequent(int[] nums, int k) {
        //PriorityQueue + HashMap solution. We maintain a minQ with k size, so the time complexity would be O(nlogK)
        
        //min queue, we will limit its size <= k
        PriorityQueue<MyNode> que = new PriorityQueue<MyNode>(new Comparator<MyNode>(){
            public int compare(MyNode node1, MyNode node2){
                //min queue
                return node1.count - node2.count;
            }
        });
        
        //result list
        List<Integer> result = new ArrayList<Integer>();
        //hashMap that can relates input with MyNode
        HashMap<Integer, MyNode> hs = new HashMap<Integer, MyNode>();
        
        //scan input list
        for(int num : nums){
            if(!hs.containsKey(num)){
                MyNode node = new MyNode(num);
                hs.put(num, node);
                que.offer(node);
            }else{
                MyNode node = hs.get(num);
                que.remove(node);
                node.count++;
                que.offer(node);
            }
            
            //maintain que size <= k
            while(que.size() > k){
                que.poll();
            }
        }
        
        //update result list
        while(!que.isEmpty()){
            result.add(que.poll().val);
        }
        
        return result;
    }
    
    private class MyNode{
        int count;
        int val;
        MyNode(int v){
            val = v;
            count = 1;
        }
    }
}
