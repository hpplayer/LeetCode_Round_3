/*
Implement Trie (Prefix Tree)

Implement a trie with insert, search, and startsWith methods.

Note:
You may assume that all inputs are consist of lowercase letters a-z.
*/

/**
 * Trie problem
 * 
 * We use an array with len 26 to include children from 'a' to 'z' and it will be faster than hashMap
 * The code body for search() and startsWith() are almost same 
 * 
 * Time complexity: 
 * all three operations runs in O(n), where n is the length of string s
 * space complexity is O(26n), as we have an array of len 26 for each char in string s
 * 
 * @author hpPlayer
 * @date Nov 5, 2015 10:49:01 PM
 */

public class Implement_Trie_Prefix_Tree_p208_sol1 {
	class TrieNode {
	    // Initialize your data structure here.
	    TrieNode[] children;
	    boolean isEnd;
	    public TrieNode() {
	        children = new TrieNode[26];
	        isEnd = false;
	    }
	}
    private TrieNode root;

    public Implement_Trie_Prefix_Tree_p208_sol1() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        TrieNode curr = root;
        for(int i = 0; i < word.length(); i++){
            int index = word.charAt(i) - 'a';
            if(curr.children[index] == null){
                curr.children[index] = new TrieNode();
            }
            curr = curr.children[index];
        }
        curr.isEnd = true;
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        TrieNode curr = root;
        for(int i = 0; i < word.length(); i++){
            int index = word.charAt(i) - 'a';
            if(curr.children[index] == null) return false;
            curr = curr.children[index];
        }
        return curr.isEnd;
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        TrieNode curr = root;
        for(int i = 0; i < prefix.length(); i++){
            int index = prefix.charAt(i) - 'a';
            if(curr.children[index] == null) return false;
            curr = curr.children[index];
        }
        return true;
    }
}
//Your Trie object will be instantiated and called as such:
//Trie trie = new Trie();
//trie.insert("somestring");
//trie.search("key");