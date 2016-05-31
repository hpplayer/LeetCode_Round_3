/*
147. Insertion Sort List

Sort a linked list using insertion sort.

*/

/**
 * Insertion sort + LinkedList solution
 * 
 * First of all we need to be clear what is insertion sort.
 * Insertion sort is an intuitive sorting algorithm. We scan the input list from left to right, then for each newly input element, we will scan previously
 * sorted partial list and put it in the correct position. So the time complexity would be O(N^2)
 * 
 * Here we just implement this sorting algorithm on singly-linked list. We use a dummyHead to help us get the head of sorted partial list, and we use 
 * curr pointer to scan the input from left to right. To speed up the program, we will use another pointer sortedTail to point to the tail of sorted 
 * partial list. So that if newly input element is larger than all previously scanned elements, we can just attach it to the tail without go through visited
 * partial list. Therefore this pointer can help us improve the speed a lot.
 * 
 * Time complexity: O(n^2)
 * Space complexity: O(1)
 * 
 * @author hpPlayer
 * @date May 30, 2016 6:43:42 PM
 */
public class Insertion_Sort_List_p147_sol1 {
    public ListNode insertionSortList(ListNode head) {
        //Insertion sort + linked list solution. Based on the value of newly input element, we either attach it to the tail of
        //sorted partial list, or scan the sorted partial list and find the correct index to insert
        
        //create a dummy head to track the head of sorted list
        //We can set the node value to be -1 or int_min or whatever number we like since we will always start scan & compare
        //the sorted list from the dummy.next
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        
        //This pointer help us track the tail of sorted list.
        ListNode sortedTail = dummy.next;
        
        //ListNode that help us scan the list
        ListNode curr = head;
        
        while(curr != null){
            //we will firstly back up the curr.next node, so that we won't lose it after inserting it into sorted list
            ListNode next = curr.next;
            
            if( curr.val >= sortedTail.val ){
               //if we found newly input element >= sortedTail, then we can just append it to the tail of sorted list without
               //scanning the sorted list
               sortedTail.next = curr;
               sortedTail = curr;
               //set curr.next = null, so that our sorted list is always clean and neat
               //Notice: this is very important, otherwise if input is a single element, we will generate a cyclic list!!!!!!!!!!!!
               curr.next = null;
            }else{
                //if we found newly input element < sortedTail, then we have to scan the sorted list and find the correct index to insert
                
                //to insert a node in the sorted list, we need to find the last sorted element that < newly input element, then 
                //insert it after this found node
                //Notice: Since we always compare prev.next with curr, prev should start with dummy!!!!!!!!!!!!!!!!!!!!!
                ListNode prev = dummy;
                
                while(prev.next != null && prev.next.val < curr.val){
                    prev = prev.next;
                }
                
                curr.next = prev.next;
                prev.next = curr;
            }
            
            //update curr to be next
            curr = next;
        }
        
        return dummy.next;
    }
}
