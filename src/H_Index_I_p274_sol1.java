import java.util.Arrays;

/*
274. H-Index

Given an array of citations (each citation is a non-negative integer) of a researcher, write a function to compute the researcher's h-index.

According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her N papers have at least h citations each,
and the other N − h papers have no more than h citations each."

For example, given citations = [3, 0, 6, 1, 5], which means the researcher has 5 papers in total and each of them had received 3, 0, 6, 1, 5 citations respectively.
Since the researcher has 3 papers with at least 3 citations each and the remaining two with no more than 3 citations each, his h-index is 3.

Note: If there are several possible values for h, the maximum one is taken as the h-index.

Hint:

An easy approach is to sort the array first.
What are the possible values of h-index?
A faster approach is to use extra space.
*/

/**
 * Naive approach
 * 
 * The definition of H-index can be seen from below graph:
 *  
 * citations(ascending)
 * |.. /
 * |. / 
 * | /  ...  
 * |/_45__ papers (ascending)
 * 
 * (we should have less papers having larger citations while more papers having smaller citations)
 * 
 * The H-index is the papers index that most close to the 45-diagonal. It must in the part above diagonal. If it is in below part, then we wouldn't 
 * have enough papers to reach citations with value of papers index
 * 
 * We firstly sort the input array. So smaller citations will be put in front, however this also indicates it has most papers. Why? because all later
 * papers will have citations larger than those smaller citations, therefore the paper index will actually be reversed. We are actually scanning
 * the paper index from right to left, but for citation index, we are still scanning it in ascending order
 * 
 * So here is the basic idea:
 * we firstly sort the input array, then compare each citation value with (len - index). We will stop and return result as long as we found those
 * two values are same, or the citation value > (len - index)
 * 
 * In extreme case that we couldn't find H-index in the input array, like input [0], we should return 0. As H-index does not exist
 * 
 * Time complexity: O(NlogN + N)
 * Space complexity: O(N)
 * 
 * Notice:
 * It is possible that we have H-index as len. Example: [1]. So H-index ranges from 0 to len
 * So the range of H-index is from 0 -> len. 0 means none num of papers meet the requirement while len means total num of papers meet the requirement
 * 
 * sol1 is based on the graph and more widely used due to its convenience but it is slow, as we need to sort first
 * In sol1, we scan the paper axis backward and find the first index that gives citations[i] >= n - i where n - i is num of books as well as the 
 * index in paper axis. Because small citation val has large paper num value and vice versa, n - i actually help us get the correct val at paper axis
 * 
 * sol2 is based on the definition the problem gives us. It is faster
 * In sol2, it is more understandable to look the definition only, as we have converted the citation values, and we can no longer use the graph 
 * to anaylze the solution
 * 
 * @author hpPlayer
 * @date Feb 20, 2016 2:34:03 PM
 */
public class H_Index_I_p274_sol1 {
    public int hIndex(int[] citations) {
        //we firstly sort the array, and put smaller citations in front. Our array will then have those property: for citation c at index i
        //we will have (len - i) papers that have citations >= c. Therefore when we are reading the array forward, we are actually reading the 
    	//paper axis backward, because we are reading smaller citations first and they can be accumulated.
    	//Our task is to find the index i that has c >= (len - i)
        
        Arrays.sort(citations);
        int len = citations.length;
        
        for(int i = 0; i < len; i++){
        	//len - i is the num of books that has citation >= i;
        	//citations[i] is the val of citations at index i
            if(citations[i] >= len - i) return len - i;
        }
        
        //if no citations in our book can meet the requirement, then we need to return 0
        
        return 0;
    }
}
