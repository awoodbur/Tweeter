package edu.byu.cs.tweeter.client.model.service;

import java.io.IOException;

import edu.byu.cs.tweeter.client.net.ServerFacade;
import edu.byu.cs.tweeter.model.service.GetUserService;
import edu.byu.cs.tweeter.model.service.request.GetUserRequest;
import edu.byu.cs.tweeter.model.service.response.GetUserResponse;

public class GetUserServiceProxy implements GetUserService {

    private static final String URL_PATH = "/getuserbyalias";

    private final ServerFacade serverFacade = new ServerFacade();

    @Override
    public GetUserResponse getUser(GetUserRequest request) throws IOException {
        return serverFacade.getUser(request, URL_PATH);
    }
}
