import java.util.*;

/*
170. Two Sum III - Data structure design

Design and implement a TwoSum class. It should support the following operations: add and find.

add - Add the number to an internal data structure.
find - Find if there exists any pair of numbers which sum is equal to the value.

For example,
add(1); add(3); add(5);
find(4) -> true
find(7) -> false
Show Company Tags
Show Tags
Show Similar Problems
*/


/**
 * HashMap solution
 * 
 * Use HashMap to record the inputs. Since we may have the extreme case that two sums are from the same input which appears twice, we
 * have to use HashMap to record the occurrence of each input. During the find(), we will search each key in HashMap and return true
 * if we can find the (value - key) as another key in HashMap
 * 
 * The original solution costs time around 400ms
 * Here we use LinkedHashMap to make the access to keySet() memory becomes sequential visit instead of random visit, so the time reduced to 130ms
 * (Notice: we need to import linkedHashMap api manually in LeetCode)
 * 
 * Time complexity: add() O(1), find() O(N)
 * Space complexity: O(N)
 * 
 * Remark:
 * In this solution we have O(1) in add() and O(N) in find(). We can also modify the program so that add costs O(N) and find() costs O(1)
 * But this modification will cause LTE. In reality, this is a trade-off. We need to check what the possible ratio between add() and find() 
 * before we decide how to program the solution
 * 
 * @author hpPlayer
 * @date Mar 7, 2016 10:54:06 PM
 */

public class Two_Sum_III_Data_structure_design_p170_sol1 {
	public static void main(String[] args){
		Two_Sum_III_Data_structure_design_p170_sol1 test = new Two_Sum_III_Data_structure_design_p170_sol1();
		test.add(0);
		test.add(-1);
		test.add(1);
		System.out.println(test.find(0));
	}
	
    //we use LinkedHashMap to make the access to memory in scanning keySet() becomes sequential
    LinkedHashMap<Integer, Integer> hs = new LinkedHashMap<Integer, Integer>();
    
    // Add the number to an internal data structure.
	public void add(int number) {
        //add() here costs O(1)
        if(!hs.containsKey(number)){
            hs.put(number, 1);
        }else{
            //we need two sum in this problem, therefore we can update occurences to 2 for all inputs that apperance > 1
            //no need to update to 3, 4, etc.
            hs.put(number, 2);
        }
	}

    // Find if there exists any pair of numbers which sum is equal to the value.
	public boolean find(int value) {
	    //find() here costs O(N), we will search each key and find if the (value - key) exists in hashMap
	    for(Integer key : hs.keySet()){
	        int diff = value - key;
	        if(hs.containsKey(diff)){
	            //if we found the other part in hashmap
	            if(diff != key){
	                //if diff != key, we return true directly
	                return true;
	            }else{
	                //if diff == key, then we need check the occurence of key, to make sure it appears more than once
	                if(hs.get(diff) > 1) return true;
	            }
	        }
	    }
	    
	    //all keys checked, still not find result
	    return false;
	}
}
