/*
211. Add and Search Word - Data structure design

Design a data structure that supports the following two operations:

void addWord(word)
bool search(word)
search(word) can search a literal word or a regular expression string containing only letters a-z or .. A
. means it can represent any one letter.

For example:

addWord("bad")
addWord("dad")
addWord("mad")
search("pad") -> false
search("bad") -> true
search(".ad") -> true
search("b..") -> true
Note:
You may assume that all words are consist of lowercase letters a-z.

click to show hint.

You should be familiar with how a Trie works. If not, please work on this problem: Implement Trie (Prefix Tree) first.
*/

/**
 * Trie solution
 * 
 * AddWord() is similar to Implement_Trie_Prefix_Tree_p208_sol1.
 * search() is different, since we allow use to search "." We need try all possible next TrieNodes. To improve the speed, we need modify 
 * search() to allow start search in mid, we will call it DFS()
 * 
 * Time complexity: add: 26 * O(N) where N is word length, search: O(26^n) where N is word length.
 * In worst case, input only contains ".", therefore in each layer, we need search all 26 child TrieNodes
 * 
 * Space complexity: O(26 * N)
 * 
 * @author hpPlayer
 * @date Feb 29, 2016 9:30:13 PM
 */
public class Add_and_Search_Word_Data_structure_design_p211_sol1 {
    //Trie solution. This problem allows user to search word contains "*", so we need use DFS to try all possible substrings when encountering "*"

    private class TrieNode{
        boolean isEnd;
        TrieNode[] chars;
        TrieNode(){
            isEnd = false;
            chars = new TrieNode[26];
        }
    }
    
    //we don't have constructor now, so we need initialize root here
    private TrieNode root = new TrieNode();
    
    // Adds a word into the data structure.
    public void addWord(String word) {
        //add word is same with Implement_Trie_Prefix_Tree_p208_sol1
        TrieNode curr = root;
        for(int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            if(curr.chars[c - 'a'] == null) curr.chars[c - 'a'] = new TrieNode();
            curr = curr.chars[c - 'a'];
        }
        curr.isEnd = true;
    }

    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public boolean search(String word) {
        //search is different from Implement_Trie_Prefix_Tree_p208_sol1, if we encounter ".", then we need try all possible next TrieNode
        //therefore we need a function that can let us begin search in mid
        return DFS(word, root);
    }
    
    public boolean DFS(String word, TrieNode curr){
        for(int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            if(c == '.'){
                //we need try all possible TrieNode
                for(TrieNode temp : curr.chars){
                    //skip null node
                    if(temp == null) continue;
                    //start search with next char, if return ture, then we succeed, return true
                    if(DFS(word.substring(i+1), temp)) return true;
                }
                //all trieNodes tried, still not succeed
                return false;
            }else{
                if(curr.chars[c - 'a'] == null) return false;
                curr = curr.chars[c - 'a'];
            }
        }
        //input TrieNode will never be null.
        //if we cant go into loop, then curr will be root
        //if we can go into loop, then we will never call searchPart will null TrieNode        
        return curr.isEnd;
    }
}

//Your WordDictionary object will be instantiated and called as such:
//WordDictionary wordDictionary = new WordDictionary();
//wordDictionary.addWord("word");
//wordDictionary.search("pattern");