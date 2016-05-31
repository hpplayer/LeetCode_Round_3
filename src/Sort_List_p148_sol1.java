/*
148. Sort List

Sort a linked list in O(n log n) time using constant space complexity.
*/

/**
 * Bottom-up MergeSort solution
 * 
 * Traditional merge-sort is going top-down way, where we recursively split input list into half and half from top to 
 * bottom, then merge split list from bottom to top.
 * Bottom-up merge sort skips the half split part, instead, doing read and split at the same time. In the first round,
 * we only read two nodes a time, then merge the them. In the second round, we read 4 nodes a time, then merge them.
 * We iteratively do this until the len of group >= input len 
 * 
 * Time complexity: O(NlogN)
 * Space complexity: O(1)
 * 
 * Remark:
 * 1) Only iterative solution will give O(1) space complexity. recursive version may give non-constant space complexity due
 * to the size of stack
 * 2) We can only double the group size each time. This is because we merge two groups each time.
 * If we use other parameter like 3, then we will miss some groups.
 * Ex: input(3,2,1) and we use 3 as increment for loop, then we will return result (2, 3, 1) in second round, and we
 * missed the last group
 * 
 * @author hpPlayer
 * @date May 30, 2016 9:25:46 PM
 */
public class Sort_List_p148_sol1 {
    public ListNode sortList(ListNode head) {
        //bottom-up mergeSort solution. We will read input based on group, then we do merge and sort at the same time. Each time group size will double. We repeatedly do this until group size > input size
        
        //boundary check
        if(head == null || head.next == null) return head;
        
        //use dummy to track the head of sorted list
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        
        //get the input size
        int len = 0;
        ListNode curr = head;
        while(curr != null){
            curr = curr.next;
            len++;
        }
        
        for(int groupSize = 1; groupSize < len; groupSize*=2){
            //each time we will double the group size
            
            //tail is the last node of last group, we initialize it with dummy
            ListNode tail = dummy;
            //curr is the pointer that we used to scan input
            //Notice: we have to initialize curr with dummy.next since we are constantly updating the head node!!!!!!!!!!
            curr = dummy.next;
            
            while(curr != null){
                //firstHead is the head of first group
                ListNode firstHead = curr;
                //secondHead is the head of second group
                ListNode secondHead = nextHead(firstHead, groupSize);
                //thirdhead is the head of third group, we will move curr to thirdHead in next loop
                ListNode thirdHead = nextHead(secondHead, groupSize); 
                
                //merge two groups each time and update tail to be the last node in the merged group
                tail = merge(firstHead, secondHead, tail);
                
                curr = thirdHead;
            }
        }
        
        return dummy.next;
    }
    
    public ListNode merge(ListNode node1, ListNode node2, ListNode tail){
         //merge is a function that merges two lists and attach it to the tail node, then return the last node
         //in the merged group to update the tail node
         while(node1 != null && node2 != null){
             if(node1.val < node2.val){
                 tail.next = node1;
                 node1 = node1.next;
                 tail = tail.next;
             }else{
                 tail.next = node2;
                 node2 = node2.next;
                 tail = tail.next;                 
             }
         }
         
         if(node1 != null) tail.next = node1;
         if(node2 != null) tail.next = node2;
         
         while(tail.next != null) tail = tail.next;
         
         return tail;
    }
    
    public ListNode nextHead(ListNode node, int groupSize){
        //nextHead is a function that used to find the head of next group based on curr head and curr group size
        
        for(int i = 1; i < groupSize && node != null; i++){
            //we may not have enough node in last group, so we need check node != null as well 
            node = node.next;
        }
        
        //last group
        if(node == null) return null;
        
        //we will cut curr group and next group, so that we can freely manipulate them
        ListNode temp = node.next;
        node.next = null;
        return temp;
    }
}
