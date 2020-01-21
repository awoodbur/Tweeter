package edu.byu.cs.tweeter.model.net;

import edu.byu.cs.tweeter.model.net.request.FollowingRequest;
import edu.byu.cs.tweeter.model.net.response.FollowingResponse;

public interface Server {

    FollowingResponse getFollowees(FollowingRequest request);
}
