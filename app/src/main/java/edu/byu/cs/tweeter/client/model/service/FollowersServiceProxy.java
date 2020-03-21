package edu.byu.cs.tweeter.client.model.service;

import java.io.IOException;

import edu.byu.cs.tweeter.client.net.ServerFacade;
import edu.byu.cs.tweeter.model.service.FollowersService;
import edu.byu.cs.tweeter.model.service.request.FollowersRequest;
import edu.byu.cs.tweeter.model.service.response.FollowersResponse;

public class FollowersServiceProxy implements FollowersService {

    private static final String URL_PATH = "/getfollowers";

    private final ServerFacade serverFacade = new ServerFacade();

    @Override
    public FollowersResponse getFollowers(FollowersRequest request) throws IOException {
        return serverFacade.getFollowers(request, URL_PATH);
    }
}
