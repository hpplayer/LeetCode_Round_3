/*
160. Intersection of Two Linked Lists

Write a program to find the node at which the intersection of two singly linked lists begins.


For example, the following two linked lists:

A:          a1 ¡ú a2
                   ¨K
                     c1 ¡ú c2 ¡ú c3
                   ¨J            
B:     b1 ¡ú b2 ¡ú b3
begin to intersect at node c1.


Notes:

If the two linked lists have no intersection at all, return null.
The linked lists must retain their original structure after the function returns.
You may assume there are no cycles anywhere in the entire linked structure.
Your code should preferably run in O(n) time and use only O(1) memory.
*/

/**
 * Observation solution
 * 
 * We observe that we are only required to report the intersection node, and our difficulty is to eliminate the diff in the len before two lists meet.
 * To solve this problem, we can use a trick to scan the list twice. We will start scan the other list when one list end, the second time we are guaranteed
 * to find the intersection point otherwise two pointers will reach the end at the same time
 * 
 * Assume the len before intersection point in list A is a, the len before intersection point in list B is b
 * The intersection part has len c, then we will have
 * First pointer: a + c + b 
 * Second pointer: b + c + a
 * so we are guaranteed to meet in the second round if we have an intersection.
 * If we don't have intersection, then we will have 
 * a + c + b + c = b + c + a + c
 * Therefore we will reach the null node in same time 
 * 
 * Time complexity: O(N)
 * Space complexity: O(1)
 * 
 * @author hpPlayer
 * @date Mar 12, 2016 1:14:01 PM
 */

public class Intersection_of_Two_Linked_Lists_p160_sol1 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        //observation solution. We will scan two lists simultaneously. Once one list reaches the end, we will let it start scan the
        //other list. Then the other list reaches the end, and we will let pointer start scan this list
        //so if two lists have intersection, their diff in len will be offset during this reset, therefore we can find intersection
        //if we have one. Even if we don't have one, we will reach null node at the same time, therefore we will return anyway
        
        //boundary check
        if(headA == null || headB == null) return null;
        
        //two pointers that we used to scan lists
        ListNode nodeA = headA;
        ListNode nodeB = headB;
        
        //two pointers will be guaranteed to meet in the second round, ethier in intersection point or in the end null node
        while(nodeA != nodeB){
            //if pointer in list a reaches the end, then we let it start scan list b
            nodeA = nodeA == null? headB : nodeA.next;
            //if pointer in list b reaches the end, then we let it start scan list a
            nodeB = nodeB == null? headA : nodeB.next;
        }
        
        return nodeA;
    }
}
