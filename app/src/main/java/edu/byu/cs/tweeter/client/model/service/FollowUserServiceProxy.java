package edu.byu.cs.tweeter.client.model.service;

import java.io.IOException;

import edu.byu.cs.tweeter.client.net.ServerFacade;
import edu.byu.cs.tweeter.model.service.FollowUserService;
import edu.byu.cs.tweeter.model.service.request.FollowUserRequest;
import edu.byu.cs.tweeter.model.service.response.FollowUserResponse;

public class FollowUserServiceProxy implements FollowUserService {

    private static final String URL_PATH = "/followuser";

    private final ServerFacade serverFacade = new ServerFacade();

    @Override
    public FollowUserResponse followUser(FollowUserRequest request) throws IOException {
        return serverFacade.followUser(request, URL_PATH);
    }
}
