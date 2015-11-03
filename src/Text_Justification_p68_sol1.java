import java.util.*;

/**
 * Several things that need to be noticed:
 * 1) there are two cases: a) general lines b) last line or line with single word, in which we have to left justify words
 * 
 * 2) For general lines, we won't add spaces after last word in each line. So when we count how many words can be added to current line,
 * we need set initial len to -1, which deduce one space from last word. Furthermore, when adding spaces after each word, we will
 * stop at the last word. In general lines, we will use equation to get avergeSpaces need to be added after each word and extraSpaces
 * need to be added to left possible words
 * 
 * 3) For last line or line with single word, we always left justified the words, and ADD ONLY ONE SPACE AFTER EACH WORD.
 * We will all remaining spaces after last word. So when adding spaces after each word, if we found we are dealing with special
 * cases we will stop after adding enough spaces after last word
 * 
 * @author hpPlayer
 * @date Nov 2, 2015 4:15:17 PM
 */

public class Text_Justification_p68_sol1 {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<String>();
        
        //i is the main cursor in words[], j is the cursor for each line
        //we will use j to check how many words can be added to each line,
        //then next i is just the index after j
        for(int i = 0, j = 0; i < words.length; i = j){
            //we set len to -1, so we can deduct the space after last word
            int len = -1;
            //if we still have words left and not reaching maxWidth, add word as many as possible, and we also count one space after each word
            for(; j < words.length && len + words[j].length() + 1 <= maxWidth; j++){
                len += words[j].length() + 1;
            }
            
            //spaces after each word
            int avergeSpaces = 0;
            //redundant spaces 
            int extraSpaces = 0;
            
            
            if(j != words.length && j != i + 1){
                //general case 
                
                //average spaces = remaining spaces / gaps we have among words
                //we add an extra 1 here to include the one space after each word when collecting words
                avergeSpaces = (maxWidth - len) / (j - i - 1) + 1;
                extraSpaces = (maxWidth - len) % (j - i - 1);
            }else{
                //specal cases, last line or a line with one word
                //for last line, we still add one space after each word
                //for line with single word, we won't add space after it
                avergeSpaces = j == words.length? 1 : 0;
            }
            
            StringBuilder sb = new StringBuilder();
            
            //scan words in current line
            for(int k = i; k < j; k++){
                sb.append(words[k]);
                if( k == j - 1){
                    //last word is a special case, for general lines, we just break the loop
                    //for last line or the line with one word, we will add extra spaces after it
                    
                    if(j == words.length || j == i + 1){
                        int diff = maxWidth - sb.length();
                        while(diff > 0){
                            sb.append(" ");
                            diff --;
                        }
                    }
                    
                    //after process the specal cases or in general case, we will break the loop after adding the last word
                    //i.e. skip adding spaces after the last word
                    break;
                }
                
                //add avergeSpaces after each word
                for(int m = 0; m < avergeSpaces; m++){
                    sb.append(" ");
                }
                
                //add extraSpaces only as left as possible
                if(extraSpaces > 0){
                    sb.append(" ");
                    extraSpaces --;
                }
            }
            
            result.add(sb.toString());
        }
        
        return result;
    }
}
