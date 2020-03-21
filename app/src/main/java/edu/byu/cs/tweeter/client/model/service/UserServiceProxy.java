package edu.byu.cs.tweeter.client.model.service;

import java.io.IOException;

import edu.byu.cs.tweeter.client.net.ServerFacade;
import edu.byu.cs.tweeter.model.domain.Follow;
import edu.byu.cs.tweeter.model.service.UserService;
import edu.byu.cs.tweeter.model.service.response.Response;

public class UserServiceProxy implements UserService {

    private static final String F_URL_PATH = "/follow";
    private static final String UF_URL_PATH = "/unfollow";

    private final ServerFacade serverFacade = new ServerFacade();

    @Override
    public Response followUser(Follow request) throws IOException {
        return serverFacade.followUser(request, F_URL_PATH);
    }

    @Override
    public Response unfollowUser(Follow request) throws IOException {
        return serverFacade.unfollowUser(request, UF_URL_PATH);
    }
}
