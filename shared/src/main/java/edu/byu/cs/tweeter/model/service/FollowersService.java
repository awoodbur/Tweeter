package edu.byu.cs.tweeter.model.service;

import java.io.IOException;

import edu.byu.cs.tweeter.model.service.request.FollowersRequest;
import edu.byu.cs.tweeter.model.service.response.FollowersResponse;

public interface FollowersService {
    FollowersResponse getFollowers(FollowersRequest request) throws IOException;
}
