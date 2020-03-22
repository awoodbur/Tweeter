package edu.byu.cs.tweeter.server.lambda.followuser;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.byu.cs.tweeter.model.service.request.FollowUserRequest;
import edu.byu.cs.tweeter.model.service.response.FollowUserResponse;
import edu.byu.cs.tweeter.server.service.FollowUserServiceImpl;

public class FollowUserHandler implements RequestHandler<FollowUserRequest, FollowUserResponse> {

    @Override
    public FollowUserResponse handleRequest(FollowUserRequest request, Context context) {
        FollowUserServiceImpl service = new FollowUserServiceImpl();
        return service.followUser(request);
    }
}
