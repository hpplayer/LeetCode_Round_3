/**
 * recursive ListNode solution
 * 
 * Similar to Remove_Duplicates_from_Sorted_List_I_p83_sol2, we still build and update the new list backward
 * but this time before we going into the child recursion, we will use a while loop to skip over all duplicate
 * nodes.
 * 
 * Time complexity: O(N)
 * Space complexity: O(N)
 * 
 * @author hpPlayer
 * @date May 17, 2016 10:32:28 PM
 */
public class Remove_Duplicates_from_Sorted_List_II_p82_sol2 {
    public ListNode deleteDuplicates(ListNode head) {
        //recursive ListNode solution. To key part of this recursion is that we need a while loop to skip over
        //all duiplicate nodes and only pass clean or new node to child recursions
        
        //boundary check
        if(head == null || head.next == null){
            return head;
        }
        
        ListNode next = head.next;
        
        if(head.val == next.val){
            //curr node is duplicate, we need skip all its duplicates, and only return updated list from child recursion
            while(next != null && head.val == next.val) next = next.next;
            
            //remove duplicates from input list. This is like prev.next = new next in iterative version
            return deleteDuplicates(next);
        }else{
            //curr node is non-duplicate, then append it to the head of updated list from child recursion
            head.next = deleteDuplicates(head.next);
            return head;
        }
    }
}
