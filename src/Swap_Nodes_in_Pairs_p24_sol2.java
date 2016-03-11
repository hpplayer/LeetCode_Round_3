/**
 * LinkedList solution
 * 
 * Here we use an iterative forward way to swap nodes in pair
 * We need use an extra variable to record the tail of last pair. Then in current pair, we will firstly backup the head of next pair. Then we swap nodes
 * in current pairs and attach new Head to tail of last pair, and let updated second node to the head of next pair
 * 
 * Time complexity: O(N)
 * Space complexity: O(1)
 * 
 * Remark:
 * Since we will do nothing when we reach the tail of list (only one node or no node left), we have to connect it before we visit it. Therefore we need
 * connect updated second node in current pair with the head of next pair as well!!!!!!!!
 * 
 * @author hpPlayer
 * @date Mar 10, 2016 3:56:27 PM
 */
public class Swap_Nodes_in_Pairs_p24_sol2 {
    public ListNode swapPairs(ListNode head) {
        //iterative forward solution, we will record the second node of last pair, then append new first node in current pair to it.
        
        //need a dummy node to hold the updated first node in first pair
        ListNode dummy = new ListNode(-1);
        //in case we only have one or no node in input, where we will not start the loop, we connect dummy with list first
        //at least there is no bad influence when doing this
        dummy.next = head;
        
        //prev records the second node of last pair, we initialize it as dummy node
        ListNode prev = dummy;
        
        //each iteration will swap two nodes in a pair, therefore we need we have at least two nodes left
        while(head != null && head.next != null){
            //second node in current pair
            ListNode next = head.next;
            //first node in next pair
            ListNode nextPair = next.next;
            
            //swap nodes in current pair
            head.next = nextPair;
            next.next = head;
            
            //append updated first node to prev
            prev.next = next;
            
            //update head and prev
            prev = head;
            head = nextPair;
        }
        
        return dummy.next;
    }
}
