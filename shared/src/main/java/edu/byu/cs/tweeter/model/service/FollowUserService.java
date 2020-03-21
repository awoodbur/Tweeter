package edu.byu.cs.tweeter.model.service;

import java.io.IOException;

import edu.byu.cs.tweeter.model.service.request.FollowUserRequest;
import edu.byu.cs.tweeter.model.service.response.FollowUserResponse;

public interface FollowUserService {
    FollowUserResponse followUser(FollowUserRequest request) throws IOException;
}
