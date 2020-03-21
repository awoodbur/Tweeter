package edu.byu.cs.tweeter.model.service;

import java.io.IOException;

import edu.byu.cs.tweeter.model.service.request.SignUpRequest;
import edu.byu.cs.tweeter.model.service.response.SignUpResponse;

public interface SignUpService {
    SignUpResponse signUp(SignUpRequest request) throws IOException;
}
