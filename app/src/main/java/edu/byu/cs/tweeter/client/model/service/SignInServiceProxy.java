package edu.byu.cs.tweeter.client.model.service;

import java.io.IOException;

import edu.byu.cs.tweeter.client.net.ServerFacade;
import edu.byu.cs.tweeter.model.service.SignInService;
import edu.byu.cs.tweeter.model.service.request.SignInRequest;
import edu.byu.cs.tweeter.model.service.response.SignInResponse;

public class SignInServiceProxy implements SignInService {

    private static final String URL_PATH = "/signin";

    private final ServerFacade serverFacade = new ServerFacade();

    @Override
    public SignInResponse signIn(SignInRequest request) throws IOException {
        return serverFacade.signIn(request, URL_PATH);
    }
}
