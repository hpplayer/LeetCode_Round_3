import java.util.*;

public class Strobogrammatic_Number_II_p247_sol2 {
    public List<String> findStrobogrammatic(int n) {
        LinkedList<String> result = new LinkedList<String>();
        
        if( (n&1) == 1){
            n--;
        	result.addAll(Arrays.asList(new String[]{"0", "1", "8"}));
        }else{
            result.add("");
        }
        String[][] base = { {"0", "0"}, {"1", "1"}, {"8", "8"}, {"6", "9"}, {"9", "6"} };
        
        while(n > 0){
            n-=2;
            int size = result.size();
            for(int i = 0; i < size; i++){
                String temp = result.pollFirst();
                for(int j = n == 0? 1 : 0; j < base.length; j++){
                    result.offerLast(base[j][0] + temp + base[j][1]);
                }
            }
        }
        
        return result;
    }
}
