package edu.byu.cs.tweeter.model.service;

import java.io.IOException;

import edu.byu.cs.tweeter.model.service.request.SignInRequest;
import edu.byu.cs.tweeter.model.service.response.SignInResponse;

public interface SignInService {
    SignInResponse signIn(SignInRequest request) throws IOException;
}
