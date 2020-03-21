package edu.byu.cs.tweeter.client.model.service;

import java.io.IOException;

import edu.byu.cs.tweeter.client.net.ServerFacade;
import edu.byu.cs.tweeter.model.service.UnfollowUserService;
import edu.byu.cs.tweeter.model.service.request.UnfollowUserRequest;
import edu.byu.cs.tweeter.model.service.response.UnfollowUserResponse;

public class UnfollowUserServiceProxy implements UnfollowUserService {

    private static final String URL_PATH = "/unfollowuser";

    private final ServerFacade serverFacade = new ServerFacade();

    @Override
    public UnfollowUserResponse unfollowUser(UnfollowUserRequest request) throws IOException {
        return serverFacade.unfollowUser(request, URL_PATH);
    }
}
