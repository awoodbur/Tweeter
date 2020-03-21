package edu.byu.cs.tweeter.client.model.service;

import java.io.IOException;

import edu.byu.cs.tweeter.client.net.ServerFacade;
import edu.byu.cs.tweeter.model.service.ShareTweetService;
import edu.byu.cs.tweeter.model.service.request.ShareTweetRequest;
import edu.byu.cs.tweeter.model.service.response.ShareTweetResponse;

public class ShareTweetServiceProxy implements ShareTweetService {

    private static final String URL_PATH = "/sharetweet";

    private final ServerFacade serverFacade = new ServerFacade();

    @Override
    public ShareTweetResponse shareTweet(ShareTweetRequest request) throws IOException {
        return serverFacade.shareTweet(request, URL_PATH);
    }
}
