package edu.byu.cs.tweeter.model.service;

import java.io.IOException;

import edu.byu.cs.tweeter.model.service.request.GetUserRequest;
import edu.byu.cs.tweeter.model.service.response.GetUserResponse;

public interface GetUserService {
    GetUserResponse getUser(GetUserRequest request) throws IOException;
}
