package edu.byu.cs.tweeter.server.dao;

import java.util.ArrayList;
import java.util.List;

import edu.byu.cs.tweeter.model.domain.Tweet;
import edu.byu.cs.tweeter.model.service.request.StoryRequest;
import edu.byu.cs.tweeter.model.service.response.StoryResponse;

public class StoryDAO {
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
}
