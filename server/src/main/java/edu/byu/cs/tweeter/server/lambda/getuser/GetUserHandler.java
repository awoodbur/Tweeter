package edu.byu.cs.tweeter.server.lambda.getuser;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.byu.cs.tweeter.model.service.request.GetUserRequest;
import edu.byu.cs.tweeter.model.service.response.GetUserResponse;
import edu.byu.cs.tweeter.server.service.GetUserServiceImpl;

public class GetUserHandler implements RequestHandler<GetUserRequest, GetUserResponse> {

    @Override
    public GetUserResponse handleRequest(GetUserRequest request, Context context) {
        if (request.getAlias() == null || request.getAlias().isEmpty()) {
            throw new RuntimeException("400");
        }

        GetUserServiceImpl service;
        try {
            service = new GetUserServiceImpl();
        } catch (Exception e) {
            throw new RuntimeException("500");
        }
        return service.getUser(request);
    }
}
