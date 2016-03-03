/*
161. One Edit Distance

Given two strings S and T, determine if they are both one edit distance apart.
*/


/**
 * String & two pointer solution
 * 
 * First of all, we should be clear that the problem requires two inputs have exact same one edit distance, which means inputs like "aa" vs "bb" or "aa" vs
 * "aa" are both false.
 * 
 * This solution uses two pointers to scan input. If we found one mismatch, then we will try to fix it. Since the problem states one edit distance, 
 * there will be two cases 1) same len, then we have to replace it, if we add/remove a char, there will be at least two differences 2) different len,
 * then we have to remove the char from longer string or add char to shorter string. In either case, we just need to keep pointer in short string standstill
 * and move pointer in longer string forward. 
 * 
 * 
 * Time complexity: O(n) where n is the len of shorter input
 * Space complexity: O(1)
 * 
 * Remark:
 * At first I was thinking this problem is similar to Edit_Distance_p72_sol1, and we can use DP matrix to solve the problem
 * True, we can use dp matrix to solve it with the cost of O(mn) both in time and space, but the problem only asks whether we can match inputs in one edit
 * distance, so we actually can do it much simpler. Sol1 just use O(n) time complexity with O(1) space complexity, where n is the len of shortest input.
 * 
 * Sol2 uses the similar idea, but we use the built-in equals() to compare the rest substring
 * 
 * @author hpPlayer
 * @date Mar 3, 2016 3:00:01 PM
 */
public class One_Edit_Distance_p161_sol1 {
    public boolean isOneEditDistance(String s, String t) {
        //two pointer solution. We use two pointers to scan chars in two inputs. if we found one mismatch, then we will try to fix it by either replacing current char or inserting/deleting char
        //We can convert insertion and delete into one case by adjusting the input order.
        //After the fix, we will compare the remaining part of two input strings.
        //If there is no mismatch in two inputs after the loop, then we require one str has one more char after it, so we can have exactly one edit distance
        
        int m = s.length(), n = t.length();
        
        //we adjust the inputs order, so we will always have str s.len < str t.len
        if(m > n) return isOneEditDistance(t, s);
        
        //we have more than 2 char difference in len, we return false directly
        if(n - m > 1) return false;
        
        //count will record how many mismatch we have
        int count = 0;
        
        //pointer i is pointer in s
        //pointer j is pointer in t
        //now we will compare each char in s and t until we reach the end of shorter string s
        for(int i = 0, j = 0; i < m; i++, j++){
            if(s.charAt(i) != t.charAt(j)){
                //if we found one pair of mismatch chars
                if(m == n){
                    //if two string have same length, then we will replace current char
                    //so we move both pointers forward
                    count++;
                }else{
                    //if two string have different length, then we will remove char from longer str t
                    //so we keep pointer in s standstill while move pointer in t forward
                    i--;
                    count++;
                }
                //there are at least two mismatch, return false
                if(count > 1) return false;
            }
        }
        
        //we have already taken one mismatch above
        //since we require s.length = t.length - (-1), so after above process, we either have found 1 mismatch or 0 mismatch
        //if we found > 1 mismatch, we will return inside the loop therefore there will be two cases:
        //if we have one difference, then we return true directly
        //if we have not found one difference, then we must require len(t) - len(s) = 1
        //is it possible that we have count = 1 and n - m = 1? Yes, like "ac" "aac", after one deletion, they are matched
        return count == 1 || (count == 0 && n - m == 1); 
    }
}
