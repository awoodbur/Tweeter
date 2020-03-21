package edu.byu.cs.tweeter.client.model.service;

import edu.byu.cs.tweeter.model.domain.Tweet;
import edu.byu.cs.tweeter.client.net.ServerFacade;
import edu.byu.cs.tweeter.model.service.response.Response;

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

    public Response shareTweet(Tweet tweet) {
        return serverFacade.shareTweet(tweet);
    }
}
