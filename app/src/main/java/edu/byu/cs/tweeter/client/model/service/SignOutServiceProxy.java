package edu.byu.cs.tweeter.client.model.service;

import java.io.IOException;

import edu.byu.cs.tweeter.client.net.ServerFacade;
import edu.byu.cs.tweeter.model.service.SignOutService;
import edu.byu.cs.tweeter.model.service.request.SignOutRequest;
import edu.byu.cs.tweeter.model.service.response.SignOutResponse;

public class SignOutServiceProxy implements SignOutService {

    private static final String URL_PATH = "/signout";

    private final ServerFacade serverFacade = new ServerFacade();

    @Override
    public SignOutResponse signOut(SignOutRequest request) throws IOException {
        return serverFacade.signOut(request, URL_PATH);
    }
}
