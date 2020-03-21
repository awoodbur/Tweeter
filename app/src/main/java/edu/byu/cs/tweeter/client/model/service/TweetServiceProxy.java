package edu.byu.cs.tweeter.client.model.service;

import java.io.IOException;

import edu.byu.cs.tweeter.client.net.ServerFacade;
import edu.byu.cs.tweeter.model.domain.Tweet;
import edu.byu.cs.tweeter.model.service.TweetService;
import edu.byu.cs.tweeter.model.service.response.Response;

public class TweetServiceProxy implements TweetService {

    private static final String URL_PATH = "/sharetweet";

    private final ServerFacade serverFacade = new ServerFacade();

    @Override
    public Response shareTweet(Tweet request) throws IOException {
        return serverFacade.shareTweet(request, URL_PATH);
    }
}
