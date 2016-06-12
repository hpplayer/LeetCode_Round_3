import java.util.*;

/*
355. Design Twitter

Design a simplified version of Twitter where users can post tweets, follow/unfollow another user and is able to see the 10 most recent tweets in the user's news feed. Your design should support the following methods:

postTweet(userId, tweetId): Compose a new tweet.
getNewsFeed(userId): Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent.
follow(followerId, followeeId): Follower follows a followee.
unfollow(followerId, followeeId): Follower unfollows a followee.
Example:

Twitter twitter = new Twitter();

// User 1 posts a new tweet (id = 5).
twitter.postTweet(1, 5);

// User 1's news feed should return a list with 1 tweet id -> [5].
twitter.getNewsFeed(1);

// User 1 follows user 2.
twitter.follow(1, 2);

// User 2 posts a new tweet (id = 6).
twitter.postTweet(2, 6);

// User 1's news feed should return a list with 2 tweet ids -> [6, 5].
// Tweet id 6 should precede tweet id 5 because it is posted after tweet id 5.
twitter.getNewsFeed(1);

// User 1 unfollows user 2.
twitter.unfollow(1, 2);

// User 1's news feed should return a list with 1 tweet id -> [5],
// since user 1 is no longer following user 2.
twitter.getNewsFeed(1);
*/

/**
 * HashMap + PriorityQueue solution
 * 
 * We use two hashMaps to record the relationship between follower/followee and user/posts
 * We need to notice that the tweetId does not necessarily mean the tweet is posted early or lately.
 * We can have case that  postTweet(1,5) and postTweet(1,3), i.e. user 1 posts tweet 5 ahead of 3
 * So we have to give a timestamp to each tweet to help us track the order of tweet. Then we use a PriorityQueue
 * to sort tweets and return 10 most recent tweets.
 * 
 * The tricky part is boundary cases:
 * 1) follower follows itself (We can call follow(1, 1)). Our solution: put a checker in follow()
 * 2) we call unfollow() on follower who is not in our hashMap. Our solution: put a checker in unfollow()
 * 3) We call same follow() twice, then we may have duplicate info in HashMap. Our solution: we use a Hashset
 * to record the posts
 * 4) we may have users that have not posted anything yet, therefore when we read tweets from each user, we 
 * need have a checker to make sure our hashMap contains this user before proceed
 * 
 * Time complexity: 
 * getNewsFeed: O(MlogM) where m is total count of posts. In the worst case, a user may 
 * follow all other users, therefore we need to put all posts in the priorityQueue.
 * Others: O(1)
 * 
 * Space complexity: O(m + n), where m is total cout of posts, n is total count of users
 * 
 * Remark:
 * New problem, have not seen a better solution. Will update solution later if I found one
 * 
 * @author hpPlayer
 * @date Jun 11, 2016 4:12:42 PM
 */

public class Design_Twitter_p355_sol1 {
    //HashMap + priorityQueue solution. We uses HashMaps to record the data and use priorityQueue to sort tweets
    //based on the post order
    
    //hashMap that records the relationship between follower and followee
    //we use a HashSet here to make sure followee is unique in the hashmap
    HashMap<Integer, HashSet<Integer>> following;
    
    //HashMap that records the relationship between posts and user
    //we use a List<int[]> here, so we can have the order of tweet
    HashMap<Integer, List<int[]>> posting;
    
    //counter for tweet
    int count;
    
    /** Initialize your data structure here. */
    public Design_Twitter_p355_sol1() {
        following = new HashMap<Integer, HashSet<Integer>>();
        posting = new HashMap<Integer, List<int[]>>();
    }
    
    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        
        if( !posting.containsKey(userId) ){
            posting.put(userId, new ArrayList<int[]>());
        }
        
        posting.get(userId).add( new int[]{count++, tweetId} );
        
    }
    
    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by
     *  users who the user followed or by the user herself.
     *  Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        //firstly create a priorityQueue which sorts tweets based on timestamp
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>(){
           public int compare(int[] a, int[] b){
               return b[0] - a[0];
           }
        });
        
        //firstly pop posts from followee if curr user have followees
        if( following.containsKey(userId) ){
            for( Integer followeeId : following.get(userId) ){
                //if curr followee has posts
                if( posting.containsKey(followeeId) ){
                    for(int[] post :  posting.get(followeeId) ){
                        pq.offer(post);
                    }
                }
            }
        }
        
        
        //secondly pop posts from user itself
        if( posting.containsKey(userId) ){
            for(int[] post : posting.get(userId)){
                pq.offer(post);
            }
        }
        
        List<Integer> result = new ArrayList<Integer>();
        
        for( int i = 0; i < 10 && !pq.isEmpty(); i++ ){
            result.add( pq.poll()[1]  );
        }
        
        return result;
    }
    
    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        //boundary case followerId = followeeId, we will skip this case
        if( followerId == followeeId ) return;
        
        if( !following.containsKey(followerId) ){
            following.put( followerId, new HashSet<Integer>() );
        }
        
        following.get(followerId).add(followeeId);
        
    }
    
    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if( following.containsKey(followerId)  ){
            following.get(followerId).remove(followeeId);
        }
    }
}
/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */