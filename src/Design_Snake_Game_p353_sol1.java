import java.util.*;

/*
353. Design Snake Game

Design a Snake game that is played on a device with screen size = width x height.
Play the game online if you are not familiar with the game.

The snake is initially positioned at the top left corner (0,0) with length = 1 unit.

You are given a list of food's positions in row-column order. When a snake eats the food, its length and
the game's score both increase by 1.

Each food appears one by one on the screen. For example, the second food will not appear until the first food
was eaten by the snake.

When a food does appear on the screen, it is guaranteed that it will not appear on a block occupied by the snake.

Example:
Given width = 3, height = 2, and food = [[1,2],[0,1]].

Snake snake = new Snake(width, height, food);

Initially the snake appears at position (0,0) and the food at (1,2).

|S| | |
| | |F|

snake.move("R"); -> Returns 0

| |S| |
| | |F|

snake.move("D"); -> Returns 0

| | | |
| |S|F|

snake.move("R"); -> Returns 1 (Snake eats the first food and right after that, the second food appears at (0,1) )

| |F| |
| |S|S|

snake.move("U"); -> Returns 1

| |F|S|
| | |S|

snake.move("L"); -> Returns 2 (Snake eats the second food)

| |S|S|
| | |S|

snake.move("U"); -> Returns -1 (Game over because snake collides with border)

*/
/**
 * Queue solution
 * 
 * Here we maintain two queues. One for snake body, one for food chain
 * In the move(), we will firstly get the next position of snake head (we call it next). 
 * Then we remove tail of snake, and compare next with each body position in the body Queue.
 * 
 * Why we don't just use a HashMap to cache the body position and make O(1) lookup time?
 * Because we will get memory limit exceed error
 * 
 * We use a counter to track the num of food we collect.
 * By default, we will always remove the tail node. But we allow the snake to have an extra body position
 * if it got a good. To do that we will compare count with body_queue length.
 * So if the snake get a food in move i, then in i + 1 move, we will not remove its tail and we will still
 * add a new head (next) to it
 * 
 * Remark:
 * For each move, we will remove old tail first (if we haven't got food in prev loop), then check new head
 * with body to make sure it does not hit itself
 * 
 * Time complexity: each movement: O(N) due to lookup in body_queue
 * Space complexity: O(N) since we need to record previous movements
 * 
 * @author hpPlayer
 * @date Jun 8, 2016 11:44:57 PM
 */
public class Design_Snake_Game_p353_sol1 {
    //Queue solution. use queue to update snake body and update food chain
    //we always insert in first, pop from last
    LinkedList<int[]> bodyChain;
    LinkedList<int[]> foodChain;
    int col_limit;
    int row_limit;
    //count is used to track how many food we got
    int count;
    
    /** Initialize your data structure here.
        @param width - screen width
        @param height - screen height 
        @param food - A list of food positions
        E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0]. */
    public Design_Snake_Game_p353_sol1(int width, int height, int[][] food) {
        row_limit = width;
        col_limit = height;
        bodyChain = new LinkedList<int[]>();
        foodChain = new LinkedList<int[]>();
        
        //update food chain queue
        for(int[] temp : food){
            foodChain.offerFirst(temp);
        }
        
        //update initial position
        bodyChain.offerFirst(new int[]{0, 0});
    }
    
    /** Moves the snake.
        @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down 
        @return The game's score after the move. Return -1 if game over. 
        Game over when snake crosses the screen boundary or bites its body. */
    public int move(String direction) {
        //get curr head position
        int[] head = bodyChain.peekFirst();
        int[] next = Arrays.copyOf(head, 2);
        
        //get next head position
        switch(direction){
            case "D":
                next[0]++;
                break;
            case "U":
                next[0]--;
                break;
            case "L":
                next[1]--;
                break;
            case "R":
                next[1]++;
                break;
        }
        
        //check if new head reaches boundary
        if( next[0] < 0 || next[0] >= col_limit || next[1] < 0 || next[1] >= row_limit  ){
            return -1;
        }
        
        //remove tail first, then we will check if new head hits body
        //In other words, this problem allows new head to be the position that old tail used to be
        //we will only move tail node if last movement does not get food
        //Think about base case, count = 0, bodyChain contains initial snake head, so we can easily get the 
        //if condition
        if( bodyChain.size() == count + 1 ){
            bodyChain.pollLast();
        }
        
        //check if new head hit body
        for(int[] temp : bodyChain){
            if( Arrays.equals(temp, next) ) return -1;
        }
        
        //add new head to body chain
        bodyChain.offerFirst( next );
        
        //update count and food chain based on new head
        if( Arrays.equals(next, foodChain.peekLast()  ) ){
            count++;
            foodChain.pollLast();
        }
        
        return count;
    }
    
	public static void main(String[] args) {
		int[][] food = { { 1, 2 }, { 0, 1 } };
		Design_Snake_Game_p353_sol1 test = new Design_Snake_Game_p353_sol1(3, 2, food);
		System.out.println(test.move("R"));
		System.out.println(test.move("D"));
		System.out.println(test.move("R"));
		System.out.println(test.move("U"));
		System.out.println(test.move("L"));
		
		/*
		int[][] food = { { 2, 0 }, { 0, 0 }, { 0, 2 }, { 0, 1 }, { 2, 2 }, { 0, 1 } };
		System.out.println(test.move("D"));
		System.out.println(test.move("D"));
		System.out.println(test.move("R"));
		System.out.println(test.move("U"));
		System.out.println(test.move("U"));
		System.out.println(test.move("L"));
		System.out.println(test.move("D"));
		System.out.println(test.move("R"));
		System.out.println(test.move("R"));
		System.out.println(test.move("U"));
		System.out.println(test.move("L"));
		System.out.println(test.move("L"));
		System.out.println(test.move("D"));
		System.out.println(test.move("R"));
		System.out.println(test.move("U"));
		*/
	}
}
