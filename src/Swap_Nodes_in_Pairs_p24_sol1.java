/*
24. Swap Nodes in Pairs

Given a linked list, swap every two adjacent nodes and return its head.

For example,
Given 1->2->3->4, you should return the list as 2->1->4->3.

Your algorithm should use only constant space. You may not modify the values in the list, only nodes itself can be changed.
*/

/**
 * LinkedList solution
 * 
 * There are many ways to solve the problem, iterative, recursive, forward, backward.
 * Sol1 lists a recursive and backward solution since it is concise and simple
 * We use recursion to recursively get the new head of next pair, then swap current pair, and append new head to updated second node in current pair
 * then return updated first node in current pair to above recursin
 * 
 * Time complexity: O(N)
 * Space complexity: O(N)
 * 
 * Sol2 lists an iterative and forward solution
 * Sol3 lists a recursive and forward solution, but we need know the parent nodes to connect two pairs, therefore we have extra function in sol3
 * @author hpPlayer
 * @date Mar 10, 2016 3:34:33 PM
 */
public class Swap_Nodes_in_Pairs_p24_sol1 {
    public ListNode swapPairs(ListNode head) {
        //recursive backward solution. We will use each recursion to swap two nodes in pair, but we will do it backward, so
        //we just need to attach new head of next pair to current pair
        
        //we need make sure we have two nodes in pair
        //if not, then we reach boundary, return input
        if(head == null || head.next == null) return head;
        
        //the second node in current pair, head is the first node
        ListNode next = head.next;
        
        //we will swap pairs in backward direction
        ListNode nextPair = swapPairs(next.next);
        
        //swap current two nodes
        next.next = head;
        head.next = nextPair;
        
        //return new head from current pair, which is next Node
        return next;
    }
}
