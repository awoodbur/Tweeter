package edu.byu.cs.tweeter.model.service;

import java.io.IOException;

import edu.byu.cs.tweeter.model.domain.Follow;
import edu.byu.cs.tweeter.model.service.response.Response;

public interface UserService {
    Response followUser(Follow request) throws IOException;
    Response unfollowUser(Follow request) throws IOException;
}
