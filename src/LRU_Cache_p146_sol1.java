import java.util.*;
/*
LRU Cache

Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and set.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
set(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity,
 it should invalidate the least recently used item before inserting a new item.
*/

/**
 * When using set(), we will have two cases. Case 1. we don't have such node yet. Therefore, we will create one, insert into list
 * then check if size has exceeded limits Case 2. We already have such node. So we need update its value, and update its poistion 
 * as well.
 * 
 * Sol1 is double-linkedList implementation
 * Sol2 is built-on linkedHashMap implementation
 * 
 * @author hpPlayer
 * @date Nov 2, 2015 8:13:24 PM
 */

public class LRU_Cache_p146_sol1 {
    private class Node{
        Node prev;
        Node next;
        
        int key;
        int val;
        
        Node(int key, int val){
            this.key = key;
            this.val = val;
        }
    }
    
    //most recently node will be inserted after head
    Node head;
    //node before tail node is the least recently used node
    Node tail;
    
    int size;
    int capacity;
    Map<Integer, Node> hs; 
    
    public LRU_Cache_p146_sol1(int capacity) {
        size = 0;
        this.capacity = capacity;
        
        hs = new HashMap<Integer, Node>();
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        if(!hs.containsKey(key)){
            return -1;
        }else{
            Node curr = hs.get(key);
            
            //move curr from old place to new place
            removeNode(curr);
            insertFront(curr);
            return curr.val;
        }
    }
    
    public void set(int key, int value) {
        if(!hs.containsKey(key)){
            //if we don't have this node, then we will create a new one
            Node newNode = new Node(key, value);
            hs.put(key, newNode);
            size ++;
            insertFront(newNode);
            
            if(size > capacity){
                //if size exceeds limit, we need remove the least recently used node
                hs.remove(tail.prev.key);
                removeNode(tail.prev);
                size --;
            }
        }else{
            //we need  update val of an existing node
            Node curr = hs.get(key);
            removeNode(curr);
            curr.val = value;
            insertFront(curr);
        }
    }
    
    public void insertFront(Node node){
        //handle connection with old most recently used node
        node.next = head.next;
        head.next.prev = node;
        
        //handle connection with head node
        head.next = node;
        node.prev = head;
    }
    
    public void removeNode(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
}
