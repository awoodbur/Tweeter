package edu.byu.cs.tweeter.client.model.service;

import java.io.IOException;

import edu.byu.cs.tweeter.client.net.ServerFacade;
import edu.byu.cs.tweeter.model.service.FeedService;
import edu.byu.cs.tweeter.model.service.request.FeedRequest;
import edu.byu.cs.tweeter.model.service.response.FeedResponse;

public class FeedServiceProxy implements FeedService {

    private static final String URL_PATH = "/getfeed";

    private final ServerFacade serverFacade = new ServerFacade();

    @Override
    public FeedResponse getFeed(FeedRequest request) throws IOException {
        return serverFacade.getFeed(request, URL_PATH);
    }
}
