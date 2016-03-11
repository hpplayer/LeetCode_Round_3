import java.util.*;

/*
71. Simplify Path

Given an absolute path for a file (Unix-style), simplify it.

For example,
path = "/home/", => "/home"
path = "/a/./b/../../c/", => "/c"
click to show corner cases.

Corner Cases:
Did you consider the case where path = "/../"?
In this case, you should return "/".
Another corner case is the path might contain multiple slashes '/' together, such as "/home//foo/".
In this case, you should ignore redundant slashes and return "/home/foo".
*/		

/**
 * Stack solution
 * 
 * Since we may go to above dir if we see "..", we need a stack to record the last visited dir
 * We may have boundary cases that need to be handle:
 * 1) we are required to return to parent dir even when we are in root dir already, in this case we just return "/"
 * 2) we may have several delimiters that are together like "////", in this case we will treat them as a single /,
 * 3) we may have "." or "..", first sign asks us to stay in curr dir and second sign asks us to return the last dir
 * 4) all other chars in the input will be the dir name which we should insert into stack 
 * 
 * Time complexity: O(N), each dir will be at most visited twice
 * Space complexity: O(N)
 * 
 * @author hpPlayer
 * @date Mar 11, 2016 12:02:57 AM
 */

public class Simplify_Path_p71_sol1 {
    public String simplifyPath(String path) {
        //stack solution. Use a stack to store all dir names, that we either stay in curr dir or return to above dir based on the special chars we have
        
        //in this stack, we will only insert dir names
        Stack<String> stack = new Stack<String>();
        
        for(int i = 0; i < path.length(); i++){
            //for loop will stop at "/", inside the loop we need find strings before next "/"
            //once we found the dir name, we shall not revisitd those chars again, so we move index i inside loop as well
            
            StringBuilder sb = new StringBuilder();
            
            //as long as next char is delimiter our curr string should be dir name, so we just add into stringBuilder 
            while(i + 1 < path.length() && path.charAt(i+1) != '/'){
                sb.append(path.charAt(++i));
            }
            
            //we may have cases that there is no dir name between delimiter like "//", "///", in such case we just skip them
            if(sb.length() == 0) continue;
            
            //get dir name
            String temp = sb.toString();
            
            if(temp.equals("..")){
                //need go above dirs
                //since we may have cases that there is no above dir for root dir like "/../", then we should skip
                //for general cases, we just remove top(curr) dir from stack 
                if(!stack.isEmpty()) stack.pop();
            }else if(temp.equals(".")){
                //stay in curr dir, do nothing
                continue;
            }else{
                //a dir name, push to stack
                stack.push(temp);
            }
        }
        
        //build target string, since will read dir backward, we should also build the string backward
        //Notice: don't forget add delimiter back!!!!!!!!!!!!!!!!!!!!!!!
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()) sb.insert(0, "/" + stack.pop());
        
        //we may have cases that we are in root in the end which means we have nothing in stack
        //in such case we need manually create delimiter as root path
        return sb.length() == 0? "/" : sb.toString();
    }
}
