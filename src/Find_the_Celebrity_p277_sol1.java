/*
277. Find the Celebrity

Suppose you are at a party with n people (labeled from 0 to n - 1) and among them, there may exist one celebrity.
The definition of a celebrity is that all the other n - 1 people know him/her but he/she does not know any of them.

Now you want to find out who the celebrity is or verify that there is not one. The only thing you are allowed to do is to ask questions like:
"Hi, A. Do you know B?" to get information of whether A knows B. You need to find out the celebrity (or verify there is not one) by asking as few
questions as possible (in the asymptotic sense).

You are given a helper function bool knows(a, b) which tells you whether A knows B. Implement a function int findCelebrity(n), your function should
minimize the number of calls to knows.

Note: There will be exactly one celebrity if he/she is in the party. Return the celebrity's label if there is a celebrity in the party. If there is no
celebrity, return -1.
*/

/**
 * Observation + brain-storming solution
 * 
 * We observed that each people should know the celebrity while the celebrity does not everyone, it means each people has an outgoing to celebrity,
 * while the celebrity itself does not have any outgoing edges. 
 * So in this solution, we scan each people and replace the candidate if we found in some point the candidate know the people in current index. Since
 * each people will be called at least once, and each people know the celebrity, therefore each prev candidate must be replaced when we pick the celebrity 
 * Since celebrity does not know anyone else, we can safely reach the end without replacing anyone else.
 * 
 * Based on the rule above, the last candidate alive must be the correct candidate or a false candidate meets our partial requirement. In the second case,
 * we don't have correct candidate at all.
 * 
 * In the first scan we only check the one direction, i.e. the candidate does not know anyone after it. But we may have some people does not the 
 * candidate as well. Also, we didn't check the part before candidate, where candidate may even know some people there.
 * Therefore we need a second scan with strict rules to verify the current candidate is correct. 
 * 
 * Time complexity: O(N)
 * Space complexity: O(1)
 * 
 * @author hpPlayer
 * @date Mar 9, 2016 7:07:50 PM
 */
public class Find_the_Celebrity_p277_sol1 {
	/* The knows API is defined in the parent class Relation.
    boolean knows(int a, int b); */
	boolean knows(int a, int b){return true;};
	
    public int findCelebrity(int n) {
        //observation + brainstorming soltuion. Since each people know the celeb rity while celebrity does not know everyone
        //therefore in the first loop, we will update the candidate as long as it knows someone else, this decision will make sure 
        //the celebrity will be selected when we move there, and we will not replace it by other candidates since celebrity does not know anyone else
        
        int candidate = 0;
        
        for(int i = 1; i < n; i++){
            //update candidate as long as it knows someone else
            if(knows(candidate, i)) candidate = i;
        }
        
        //if we have celebrity, we are guaranteed to pick it when we move to its index
        //but we may also do not have celebrity, and pick an incorrect candidate. Therefore now we need another loop to verify our choice
        for(int i = 0; i < n; i++){
            //skip itself
            if(i == candidate) continue;
            //if someone does not know candidate or candidate know someone, we will return false
            if(knows(candidate, i) || !knows(i, candidate)) return -1;
        }
        
        return candidate;
    }
}
