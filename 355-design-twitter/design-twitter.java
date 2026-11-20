import java.util.*;

class Twitter {

    // Stores: follower -> set of followees
    private Map<Integer, Set<Integer>> following;

    // Stores: user -> list of tweets
    private Map<Integer, List<Tweet>> tweets;

    // Global timestamp to determine tweet order
    private int time;

    // Tweet class
    private static class Tweet {
        int tweetId;
        int time;

        Tweet(int tweetId, int time) {
            this.tweetId = tweetId;
            this.time = time;
        }
    }

    public Twitter() {
        following = new HashMap<>();
        tweets = new HashMap<>();
        time = 0;
    }

    public void postTweet(int userId, int tweetId) {

        tweets.putIfAbsent(userId, new ArrayList<>());

        tweets.get(userId).add(
            new Tweet(tweetId, time++)
        );
    }

    public List<Integer> getNewsFeed(int userId) {

        PriorityQueue<Tweet> pq = new PriorityQueue<>(
            (a, b) -> Integer.compare(b.time, a.time)
        );

        // User's own tweets
        if (tweets.containsKey(userId)) {
            pq.addAll(tweets.get(userId));
        }

        // Tweets of followed users
        if (following.containsKey(userId)) {

            for (int followee : following.get(userId)) {

                if (tweets.containsKey(followee)) {
                    pq.addAll(tweets.get(followee));
                }
            }
        }

        List<Integer> result = new ArrayList<>();

        // Get 10 most recent tweets
        int count = 0;

        while (!pq.isEmpty() && count < 10) {

            Tweet tweet = pq.poll();

            result.add(tweet.tweetId);

            count++;
        }

        return result;
    }

    public void follow(int followerId, int followeeId) {

        if (followerId == followeeId) {
            return;
        }

        following.putIfAbsent(
            followerId,
            new HashSet<>()
        );

        following.get(followerId).add(followeeId);
    }

    public void unfollow(int followerId, int followeeId) {

        if (following.containsKey(followerId)) {
            following.get(followerId).remove(followeeId);
        }
    }
}