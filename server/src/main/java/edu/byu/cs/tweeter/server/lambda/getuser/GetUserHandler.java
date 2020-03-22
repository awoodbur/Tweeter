package edu.byu.cs.tweeter.server.lambda.getuser;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.byu.cs.tweeter.model.service.request.GetUserRequest;
import edu.byu.cs.tweeter.model.service.response.GetUserResponse;
import edu.byu.cs.tweeter.server.service.GetUserServiceImpl;

public class GetUserHandler implements RequestHandler<GetUserRequest, GetUserResponse> {

    @Override
    public GetUserResponse handleRequest(GetUserRequest request, Context context) {
        GetUserServiceImpl service = new GetUserServiceImpl();
        return service.getUser(request);
    }
}
