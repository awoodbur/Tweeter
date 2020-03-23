package edu.byu.cs.tweeter.server.dao;

import java.util.ArrayList;
import java.util.List;

import edu.byu.cs.tweeter.model.domain.Tweet;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.FeedRequest;
import edu.byu.cs.tweeter.model.service.request.ShareTweetRequest;
import edu.byu.cs.tweeter.model.service.request.StoryRequest;
import edu.byu.cs.tweeter.model.service.response.FeedResponse;
import edu.byu.cs.tweeter.model.service.response.ShareTweetResponse;
import edu.byu.cs.tweeter.model.service.response.StoryResponse;

public class TweetsDAO {

    public ShareTweetResponse shareTweet(ShareTweetRequest request) {
        Database.getInstance().addTweet(request.getTweet());
        return new ShareTweetResponse();
    }

    public FeedResponse getFeed(FeedRequest request) {
        Database.getInstance().sortTweets();

        List<User> following = Database.getInstance().getUserFollowing(request.getUser());
        List<Tweet> responseTweets = new ArrayList<>(request.getLimit());

        boolean hasMorePages = false;
        if (request.getLimit() > 0 && following != null) {
            int tweetIndex = Database.getInstance().getTweetIndex(request.getLastTweet());

            int limitCounter = 0;
            while (tweetIndex < Database.getInstance().getNumTweets() && limitCounter < request.getLimit()) {
                Tweet tweet = Database.getInstance().getTweet(tweetIndex);
                User author = tweet.getAuthor();
                if (following.contains(author)) {
                    responseTweets.add(tweet);
                    limitCounter++;
                }
                tweetIndex++;
            }

            hasMorePages = tweetIndex < Database.getInstance().getNumTweets();
        }

        return new FeedResponse(responseTweets, hasMorePages);
    }

    public StoryResponse getStory(StoryRequest request) {
        Database.getInstance().sortTweets();

        List<Tweet> responseTweets = new ArrayList<>(request.getLimit());

        boolean hasMorePages = false;
        if (request.getLimit() > 0) {
            int tweetIndex = Database.getInstance().getTweetIndex(request.getLastTweet());

            int limitCounter = 0;
            while (tweetIndex < Database.getInstance().getNumTweets() && limitCounter < request.getLimit()) {
                Tweet tweet = Database.getInstance().getTweet(tweetIndex);
                if (tweet.getAuthor().equals(request.getUser())) {
                    responseTweets.add(tweet);
                    limitCounter++;
                }
                tweetIndex++;
            }

            hasMorePages = tweetIndex < Database.getInstance().getNumTweets();
        }

        return new StoryResponse(responseTweets, hasMorePages);
    }

    public void reset() {
        Database.getInstance().resetDatabase();
    }
}
