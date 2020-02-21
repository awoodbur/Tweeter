package edu.byu.cs.tweeter.model.services;

import edu.byu.cs.tweeter.model.domain.Tweet;
import edu.byu.cs.tweeter.net.ServerFacade;
import edu.byu.cs.tweeter.net.response.TweetResponse;

public class TweetService {

    private static TweetService instance;

    private final ServerFacade serverFacade;

    public static TweetService getInstance() {
        if(instance == null) {
            instance = new TweetService();
        }

        return instance;
    }

    private TweetService() {
        serverFacade = new ServerFacade();
    }

    public TweetResponse shareTweet(Tweet tweet) {
        return serverFacade.shareTweet(tweet);
    }
}
