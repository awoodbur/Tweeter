package edu.byu.cs.tweeter.model.service;

import java.io.IOException;

import edu.byu.cs.tweeter.model.service.request.SignOutRequest;
import edu.byu.cs.tweeter.model.service.response.SignOutResponse;

public interface SignOutService {
    SignOutResponse signOut(SignOutRequest request) throws IOException;
}
