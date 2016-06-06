/*
335. Self Crossing

You are given an array x of n positive numbers. You start at point (0,0) and moves x[0] metres to the north,
then x[1] metres to the west, x[2] metres to the south, x[3] metres to the east and so on.
 In other words, after each move your direction changes counter-clockwise.

Write a one-pass algorithm with O(1) extra space to determine, if your path crosses itself, or not.

Example 1:
Given x = [2, 1, 1, 2],
©°©¤©¤©¤©´
©¦   ©¦
©¸©¤©¤©¤©à©¤©¤>
    ©¦

Return true (self crossing)
Example 2:
Given x = [1, 2, 3, 4],
©°©¤©¤©¤©¤©¤©¤©´
©¦      ©¦
©¦
©¦
©¸©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤©¤>

Return false (not self crossing)
Example 3:
Given x = [1, 1, 1, 1],
©°©¤©¤©¤©´
©¦   ©¦
©¸©¤©¤©¤©à>

Return true (self crossing)
*/

/**
 * Observation solution
 * 
 * If we assume our start line is a, then there are only two ways to cross line a:
 * 1) a line coming from left and going rightward
 * or 
 * 2) a line coming from right and going leftward 
 * 
 * In diagram representation, we will have:
 *             b                              b
   +----------------+             +----------------+
   |                |             |                |
   |                |             |                | a
 c |                |           c |                |
   |                | a           |                |    f
   +----------->    |             |                | <----+
            d       |             |                |      | e
                    |             |                       |
                                  +-----------------------+
                                               d
 * in case 2, we will have a special case that f = 0, and e stabs into a.
 * 
 * Requirement 1:
 * Both cases should have the condition that d >= b, we also require b > 0 to differentiate with initial case
 * Since inputs are all positive numbers, we are safe to use b > 0 as initial condition checker
 * 
 * Requirement 2:
 * In case 1), we should also require a >= c, so we can make sure line d can cross line a
 * In case 2), we got a little more requirements. We firstly need c - e >= 0 and c -e <= a so that we got no problems
 * with height. We then require f >= d - b, so that we got no problem with length. (Notice: since we have checked d >= b
 * before therefore we dont need to check it again here (Special case d == b and c -e >= 0 && a >= c - e && f = 0 have been covered)
 * 
 * Notice: We can rotate the diagram and apply above rules to all lines !!!!!!!!!!!!
 * 
 * Sol1 just applies rules above. We will update a, b, c, d, e, f to travel the graph and return TRUE as long as 
 * we found two lines crossed. Since we don't want to update values that will be used later, we update variables
 * backward.
 * 
 * Time complexity: O(N)
 * Space complexity: O(1)
 * 
 * @author hpPlayer
 * @date Jun 5, 2016 8:39:41 PM
 */
public class Self_Crossing_p335_sol1 {
    public boolean isSelfCrossing(int[] x) {
        //Observation solution. We observed that there are only two ways to make a line get crossed. Either forward
        //or backward, we just use and update variables to describe these two ways
        
        //since inputs are positive, we are safe to initialize those variables to be 0
        int b = 0, c = 0, d = 0, e = 0, f = 0;
        
        for(int a : x){
            //case 1, cross from left side
            //we need b > 0 so that we know its value has been updated, not initial one
            //since we require d >= b while b > 0, it means we have updated value in c already.
            //therefore there is no need to check if c > 0 here again. 
            if(d >= b && b > 0 && a >= c) return true;
            
            //case 2, cross from right side, we may need an extra edge (f) here 
            //we need c >= e here, so that the line from right side will not above start point of a
            //we need c- e <= a here, so that we will not have problems in height
            //we need d - f <= b here, so that line f can cross line a
            //In special case that e stabs into a, we will have d = b = 0 and f = 0, i.e. d - f = b => 0 = 0
            if(d >= b && b > 0 && c - e >= 0  && c - e <= a && d - f <= b) return true;
            
            //update variables backward so we wouldn't update variables before we need it later
            f = e;
            e = d;
            d = c;
            c = b;
            b = a;
        }
        //all lines checked, still no luck, return false
        return false;
    }
}
