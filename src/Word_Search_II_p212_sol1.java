import java.util.*;
/*
Word Search II 

* 
* Given a 2D board and a list of words from the dictionary, find all words in the board.
* 
* Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" 
* cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.
* 
* For example,
*   Given words = ["oath","pea","eat","rain"] and board = 
*   
*   [
*     ['o','a','a','n'],
*     ['e','t','a','e'],
*     ['i','h','k','r'],
*     ['i','f','l','v']
*   ]
*   
* Return ["eat","oath"].
* 
* Note:
* You may assume that all inputs are consist of lowercase letters a-z.
* 
* click to show hint.
* 
* You would need to optimize your backtracking to pass the larger test. Could you stop backtracking earlier?
* 
* If the current candidate does not exist in all words' prefix, you could stop backtracking immediately. 
* What kind of data structure could answer such query efficiently? Does a hash table work? Why or why not? 
* How about a Trie? If you would like to learn how to implement a basic trie, please work on this problem: 
* Implement Trie (Prefix Tree) first (p208).
*/

/**
 * Trie and backtracking solution
 * 
 * We use Trie structure to help us only search the valid paths. We use backtracking to build strings. To avoid search duplicate parts,
 * we only check if current char in the matrix has valid corresponding TrieNode in the Trie. That is we only pass TrieNode to each recursion
 * and we only check if current char in the matrix is a child of this TrieNode. To avoid revisit visited cells, we will mark visited
 * cell as '*'.
 * 
 * For Trie, actually we only need implement add() method since we only pass TrieNode and check current char, so no need to search
 * prefix or word.
 * 
 * Remark:
 * in case we many have several ways to get same string, we will set isEnd = false after we add it to the result in the first time.
 * 
 * Time complexity: since it involves recursion, we need use master's theorem to analyze. I will update it later after I have time
 * Space complexity: same as above 
 * 
 * @author hpPlayer
 * @date Nov 5, 2015 11:20:26 PM
 */
public class Word_Search_II_p212_sol1 {
    public List<String> findWords(char[][] board, String[] words) {
        for(String word : words){
            //add each word into Trie
            addWord(word);
        }
        
        List<String> result = new ArrayList<String>();
        
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                int index = board[i][j] - 'a';
                //looking for start char
                if(root.children[index] != null){
                    char c = board[i][j];
                    board[i][j] = '*';
                    DFS(result, "" + c, root.children[index], i, j, board);
                    board[i][j] = c;
                }
            }
        }
        
        return result;
    }
    
    public void DFS(List<String> result, String temp, TrieNode node, int x, int y, char[][] board){
        if(node.isEnd){
            //if we found a word in dict
            result.add(temp);
            //set isEnd = false, in case we have another path to build this string
            node.isEnd = false;
        }
        
        int[] xOffset = {1, -1, 0, 0};
        int[] yOffset = {0, 0, 1, -1};
        
        for(int i = 0; i < 4; i++){
            int newX = x + xOffset[i];
            int newY = y + yOffset[i];
            //to avoid visit back, we will set visited char to be "#""
            if(newX >= 0 && newX < board.length && newY >= 0 && newY < board[0].length && board[newX][newY] != '*'){
		        int index = board[newX][newY] - 'a';
		        //lets see if this char can be included in curr valid path
		        if(node.children[index] != null){
		            //not null, yes, it can be included!
		            char c = board[newX][newY];
		            board[newX][newY] = '*';
		            DFS(result, temp + c, node.children[index], newX, newY, board);
		            board[newX][newY] = c;
		        }
            }
        }
    }
    
    class TrieNode{
        TrieNode[] children;
        boolean isEnd;
        TrieNode(){
            children = new TrieNode[26];
            isEnd = false;
        }
    }
    
    TrieNode root = new TrieNode();
    
    public void addWord(String word){
    	TrieNode curr = root;
        for(int i = 0; i < word.length(); i++){
            int index = word.charAt(i) - 'a';
            if(curr.children[index] == null) curr.children[index] = new TrieNode();
            curr = curr.children[index];
        }
        curr.isEnd = true;
    }
}
