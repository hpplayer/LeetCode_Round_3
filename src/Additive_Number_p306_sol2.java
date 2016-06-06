import java.math.BigInteger;

/**
 * Observation + BigInteger solution
 * 
 * The basic idea is similar to sol1, but now we convert isValid() to an iterative solution and make a minor change
 * in isAdditiveNumber()
 * 
 * Time complexity: exponential
 * Space complexity: O(1) as we don't need extra space
 * 
 * @author hpPlayer
 * @date Jun 5, 2016 1:59:05 PM
 */

public class Additive_Number_p306_sol2 {
    public boolean isAdditiveNumber(String num) {
        //observation + BigInteger iterative solution
        
        int n = num.length();
        
        //determine num1 and num2
        for(int lenOfNum1 = 1; lenOfNum1 < num.length() - lenOfNum1; lenOfNum1++){
            //check heading 0s in num1
            if(num.charAt(0) == '0' && lenOfNum1 > 1) return false;
            BigInteger num1 = new BigInteger( num.substring(0, lenOfNum1) );
            
            for(int lenOfNum2 = 1;  Math.max(lenOfNum1, lenOfNum2) <= n - lenOfNum1 - lenOfNum2; lenOfNum2++){
                //check heading 0s in num2
                if( num.charAt(lenOfNum1) == '0' && lenOfNum2 > 1 ) break;
                BigInteger num2 = new BigInteger( num.substring(lenOfNum1, lenOfNum1 + lenOfNum2) );
                
                //since we use lenOfnum1 and lenOfnum2 to get starting index of num3, we dont need to cut input,
                //just pass input as a whole
                if(isValid(num1, num2, num)) return true;
            }
        }
        
        //all num1 and num2 checked still not valid, return false
        return false;
    }
    
    public boolean isValid(BigInteger num1, BigInteger num2, String remainingStr){
        //get length
        int n1 = num1.toString().length();
        int n2 = num2.toString().length();
        
        //string form of num3
        String sumString;
        
        //index is starting point of num3. Then loop ends when index reaches tail of remainingStr
        for(int index = n1 + n2; index < remainingStr.length(); index += sumString.length() ){
            //get num3
            BigInteger sum = num1.add(num2);
            //update sumString
            sumString = sum.toString();
            
            //check if remainingStr starts with num3
            //we have startsWith(string, indexOffset). Therefore we just pass num3 and index and check
            if( !remainingStr.startsWith(sumString, index) ) return false;
            
            //check is valid, update num1 and num2
            num1 = num2;
            num2 = sum;
        }
        
        //index reaches end, return true
        return true;
    }
}
