/*
142. Linked List Cycle II  

Given a linked list, return the node where the cycle begins. If there is no cycle, return null.

Note: Do not modify the linked list.

Follow up:
Can you solve it without using extra space?
*/

/**
 * Math + two pointer solutions
 * 
 * Assume distance_before_loop = L, len_of_loop = C
 * firstly, we move fast pointer 2X faster than slow pointer, when slow pointer begin access loop, we have:
 * fast pointer have traveled L distance inside the loop, and now fast pointer need traversal C - L distance 
 * to catch slow pointer. When they meet for the first time, slow pointer just traveled C-L distance inside 
 * the loop, in other words, now slow pointer and fast pointer are both C-L distance away from cycle_access_point. 
 * If they move forward C-(C-L) i.e. L steps, they will reach the cycle_access_point again. L is just the 
 * distance_before_loop. So we reset slow pointer to head and let fast pointer moves same speed with slow pointer,
 * then they will meet again in the cycle_access_point.
 * 
 * Time complexity: O(N)
 * Without cycle: we use O(N) steps to reach tail
 * With cycle:
 * O(2*N)
 * meet for the first time costs O(N) (details can be found in Linked_List_Cycle_I_p141_sol1)
 * meet for the second time costs O(L), which is < O(N)
 * 
 * Space complexity:
 * O(1)
 * 
 * @author hpPlayer
 * @date Apr 27, 2016 11:07:40 PM
 */
public class Linked_List_Cycle_II_p142_sol1 {
    public ListNode detectCycle(ListNode head) {
        //two pointer + math solution. We still use fast and slow pointers to traverse the list
        //But this time We reset slow pointer to head when two pointer meet for the first time.
        //Then move two pointers with same speed, they will meet in the cycle accessing point when they meet
        //for the second time
        
        ListNode fast = head;
        ListNode slow = head;
        
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
            
            //this time we will reset slow pointer to head node and reduce fast pointer speed to find cycle accessing node
            if(slow == fast){
                //reset slow pointer
                slow = head;
                //reduce speed of faster pointer, and move two pointer to find second meeting point
                while(fast != slow){
                    fast = fast.next;
                    slow = slow.next;
                }
                //return meeting point
                return slow;
            }
        }
        
        //if we did not return from loop, then it means we don't have cycle in input
        return null;
    }
}
