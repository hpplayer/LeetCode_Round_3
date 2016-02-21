/**
 * Naive approach
 * 
 * The definition of H-index can be seen from below graph:
 *  
 * citations(ascending)
 * |../
 * | / ..
 * |/_45__ papers (ascending)
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
 * 
 * @author hpPlayer
 * @date Feb 20, 2016 2:34:03 PM
 */
public class H_Index_p274_sol1 {

}
