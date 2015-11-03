/**
 * Bottom up merge sort
 * 
 * Each time we merge 2 lists, one from start and one from end.
 * We don't need extra container to hold lists here, instead, we just use the input array
 * We will merge logK times. Each merge will cost O(kn) time (like we merged k lists to lists[1], while we still need to merge 
 * lists[0] and lists[1]). So total time is O(nklogK) 
 * 
 * @author hpPlayer
 * @date Nov 2, 2015 11:39:15 PM
 */
public class Merge_k_Sorted_Lists_p23_sol2 {
    public ListNode mergeKLists(ListNode[] lists) {
    	//boundary check
    	if(lists.length == 0) return null;
    	
        //bottom up merge sort
        int start = 0, end = lists.length - 1;
        
        while(end > 0){
            start = 0;
            while(start < end){
                lists[start] = merge2Lists(lists[start], lists[end]);
                start ++;
                end --;
            }
        }
        
        return lists[0];
    }
    
    public ListNode merge2Lists(ListNode a, ListNode b){
        //standard way to merge 2 sorted lists
        ListNode dummy = new ListNode(-1);
        ListNode prev = dummy;
        
        while(a != null && b != null){
            if(a.val < b.val){
                prev.next = a;
                prev = a;
                a = a.next;
            }else{
                prev.next = b;
                prev = b;
                b = b.next;
            }
        }
        
        if(a != null) prev.next = a;
        if(b != null) prev.next = b;
        
        return dummy.next;
    }
}
