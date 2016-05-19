import java.util.*;

/**
 * Iterative solution
 * 
 * The basic idea is same to sol1. But here we just MyNode and a stack to mimic the recursion processes
 * 
 * Time complexity: constant as len of IP is fixed
 * Space complexity: same as above
 * 
 * @author hpPlayer
 * @date May 18, 2016 11:26:30 PM
 */

public class Restore_IP_Addresses_p93_sol2 {
    private class MyNode{
        String s;
        String IP;
        int count;
        MyNode(String s, String IP, int count){
            this.s = s;
            this.IP = IP;
            this.count = count;
        }
    }
    
    public List<String> restoreIpAddresses(String s) {
        //Iterative solution. Just translate recursive solution to iterative one 
        
        List<String> result = new ArrayList<String>();
        Stack<MyNode> stack = new Stack<MyNode>();
        
        MyNode root = new MyNode(s, "", 0);
        stack.push(root);
        
        while(!stack.isEmpty()){
            MyNode curr = stack.pop();
            if(curr.count == 4){
                if(curr.s.length() == 0){
                    result.add(curr.IP);
                }
                continue;
            }
            
            for(int i = 1; i <= Math.min(curr.s.length(), 3); i++){
                //we don't allow a segment have heading 0 while len > 1
                if(curr.s.charAt(0) == '0' && i > 1) continue;
                String seg = curr.s.substring(0, i);
                if(!isValid(seg)) continue;
                String temp = curr.count == 0? seg : curr.IP + "." + seg;
                stack.push(new MyNode(curr.s.substring(i), temp, curr.count + 1));
            }
        }
        
        return result;
    }
    
    public boolean isValid(String s){
        return Integer.valueOf(s) < 256;
    }
}
