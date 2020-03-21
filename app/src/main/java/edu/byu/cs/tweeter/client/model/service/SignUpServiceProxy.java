package edu.byu.cs.tweeter.client.model.service;

import java.io.IOException;

import edu.byu.cs.tweeter.client.net.ServerFacade;
import edu.byu.cs.tweeter.model.service.SignUpService;
import edu.byu.cs.tweeter.model.service.request.SignUpRequest;
import edu.byu.cs.tweeter.model.service.response.SignUpResponse;

public class SignUpServiceProxy implements SignUpService {

    private static final String URL_PATH = "/signup";

    private final ServerFacade serverFacade = new ServerFacade();

    @Override
    public SignUpResponse signUp(SignUpRequest request) throws IOException {
        return serverFacade.signUp(request, URL_PATH);
    }
}
