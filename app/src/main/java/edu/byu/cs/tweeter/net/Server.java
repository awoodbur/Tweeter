package edu.byu.cs.tweeter.net;

import edu.byu.cs.tweeter.net.request.FollowingRequest;
import edu.byu.cs.tweeter.net.response.FollowingResponse;

public interface Server {

    FollowingResponse getFollowees(FollowingRequest request);
}
