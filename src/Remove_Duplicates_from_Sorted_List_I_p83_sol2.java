/**
 * Recursive ListNode solution
 * 
 * Since we use recursion here, We will update the list backward
 * If next node returned from recursion is same with curr node, then we discard curr node and return next node
 * (So we can preserve the list we built previously)
 * If next node returned from recursion is different with curr node, then we make them connected and return curr node
 * (since we may have removed some nodes between them, initally they may be disconnected)
 * 
 * Time complexity: O(N)
 * Space complexity: O(N) (due to the size of stack)
 * 
 * @author hpPlayer
 * @date May 17, 2016 9:24:05 PM
 */
public class Remove_Duplicates_from_Sorted_List_I_p83_sol2 {
    public ListNode deleteDuplicates(ListNode head) {
        //recursive ListNode solution. We recursively visit the list and think backward.
        //if next node returned from deeper recursion == curr Node, then we discard curr Node and return next node
        //so we can keep list returned from deeper recursion
        //if next node returned from deeper recursion != curr Node, then we append next node to curr node, so
        //they are connected now
        
        //boundary check, return once we reach tail node
        if(head == null || head.next == null) return head;
        
        //get next node from child recursions
        ListNode next = deleteDuplicates(head.next);
        
        if(next.val == head.val){
            //if they have same value, then discard head node and return next node, so we can return 
            //list built from child recursions
            return next;
        }else{
            //diff value, then keep both, and make them connected and return head node
            head.next = next;
            return head;
        }
        
    }
}
