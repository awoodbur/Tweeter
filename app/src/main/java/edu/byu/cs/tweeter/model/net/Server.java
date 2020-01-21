package edu.byu.cs.tweeter.model.net;

import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.net.request.FollowingRequest;
import edu.byu.cs.tweeter.model.net.response.FollowingResponse;

public interface Server {

    FollowingResponse<User> getFollowees(FollowingRequest request);
}
