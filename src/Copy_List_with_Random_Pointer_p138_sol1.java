import java.util.*;

/*
Copy List with Random Pointer

A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.

Return a deep copy of the list.
*/

/**
 * Hash Table problem
 * 
 * Here is the basic idea:
 * We make copy of the input list through the "next" pointer. To avoid duplicate copy we use a HashMap to store copied nodes. 
 * To make a copy of a node, we need deal with 3 variables: 1) next node 2) random node 3) label.
 * If we don't have 1) and 2) in Hash table, we firstly create a simple copy of them, then we will make a full copy when we move to 
 * that node. This operation can help us make the logic more clear i.e. we only add details to the node when we move to this node.
 * 
 * Remark:
 * 1) To avoid stack overflow problem, it is better to use the iterative version.
 * 2) We may have O(1) space solution if using c++ and manipulating pointers, but not in Java.
 * 3) Space complexity: O(n), Time complexity: O(n)
 * 
 * @author hpPlayer
 * @date Jan 18, 2016 8:59:02 PM
 */

public class Copy_List_with_Random_Pointer_p138_sol1 {
    public RandomListNode copyRandomList(RandomListNode head) {
        if(head == null) return null;
        
        HashMap<RandomListNode, RandomListNode> hs = new HashMap<RandomListNode, RandomListNode>();
        
        //create a deep copy of head and use two pointers point the copy, so we can return the copied head later
        RandomListNode dummy = new RandomListNode(head.label);
        RandomListNode curr = dummy;
        
        //go through the list and copy each node
        while(head != null){
            //check next
            if(head.next != null){
                //check if we have created a copy before
                if(!hs.containsKey(head.next)) hs.put(head.next, new RandomListNode(head.next.label));
                curr.next = hs.get(head.next);
            }
            
            //check random
            if(head.random != null){
                //check if we have created a copy before
                if(!hs.containsKey(head.random)) hs.put(head.random, new RandomListNode(head.random.label));
                curr.random = hs.get(head.random);
            }
            
            //move to next node
            curr = curr.next;
            head = head.next;
        }
        
        return dummy;
    }
}
