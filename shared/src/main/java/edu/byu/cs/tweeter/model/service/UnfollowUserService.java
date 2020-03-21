package edu.byu.cs.tweeter.model.service;

import java.io.IOException;

import edu.byu.cs.tweeter.model.service.request.UnfollowUserRequest;
import edu.byu.cs.tweeter.model.service.response.UnfollowUserResponse;

public interface UnfollowUserService {
    UnfollowUserResponse unfollowUser(UnfollowUserRequest request) throws IOException;
}
