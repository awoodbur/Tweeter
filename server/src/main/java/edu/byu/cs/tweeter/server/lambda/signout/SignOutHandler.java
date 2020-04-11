package edu.byu.cs.tweeter.server.lambda.signout;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.byu.cs.tweeter.model.service.request.SignOutRequest;
import edu.byu.cs.tweeter.model.service.response.SignOutResponse;
import edu.byu.cs.tweeter.server.service.SignOutServiceImpl;

public class SignOutHandler implements RequestHandler<SignOutRequest, SignOutResponse> {

    @Override
    public SignOutResponse handleRequest(SignOutRequest request, Context context) {
        if (request.getUser() == null) {
            throw new RuntimeException("400");
        }

        SignOutServiceImpl service;
        try {
            service = new SignOutServiceImpl();
        } catch (Exception e) {
            throw new RuntimeException("500");
        }
        return service.signOut(request);
    }
}
