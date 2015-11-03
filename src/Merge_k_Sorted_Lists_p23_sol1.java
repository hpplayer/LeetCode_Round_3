import java.util.*;

/*
Merge k Sorted Lists

Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
*/

/**
 * PriorityQueue solution.
 * 
 * We create a priorityQueue that holds all current heading nodes of all linkedList.
 * We will only add non-null nodes into the pq.
 * 
 * So time complexity will be O(knlogk) (log k to adjust heap, and k*n nodes will in heap once)
 * and space complexity will be O(k)
 * 
 * Remark:
 * Even in initialization, we may still get some null lists. So we have to check null even in initialization.
 * 
 * Sol1 is the min heap solution costs O(nklogk) times
 * Sol2 is the bottom-up merge sort solution costs O(nklogk) times
 * 
 * But sol1 needs extra container, so sol2 is better
 * 
 * @author hpPlayer
 * @date Nov 2, 2015 11:28:17 PM
 */

public class Merge_k_Sorted_Lists_p23_sol1 {
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<ListNode>( new Comparator<ListNode>(){
            public int compare(ListNode a, ListNode b){
                return a.val - b.val;
            }         	
        });
        
        for(ListNode curr : lists){
        	//even in initialization, we still have to check null
        	if(curr != null) pq.offer(curr);
        }
        
        ListNode dummy = new ListNode(-1);
        ListNode prev = dummy;
        
        while(!pq.isEmpty()){
        	//pop current min node, add to result list
            ListNode curr = pq.poll();
            prev.next = curr;
            prev = curr;
            
            //insert its next node into pq, if it has one
            if(curr.next != null) pq.offer(curr.next);
        }
        
        return dummy.next;
    }
}
