package edu.byu.cs.tweeter.server.lambda.signin;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.byu.cs.tweeter.model.service.request.SignInRequest;
import edu.byu.cs.tweeter.model.service.response.SignInResponse;
import edu.byu.cs.tweeter.server.service.SignInServiceImpl;

public class SignInHandler implements RequestHandler<SignInRequest, SignInResponse> {

    @Override
    public SignInResponse handleRequest(SignInRequest request, Context context) {
        if (request.getAlias() == null || request.getAlias().isEmpty()
            || request.getPassword() == null || request.getPassword().isEmpty()) {
            throw new RuntimeException("400");
        }

        SignInServiceImpl service;
        try {
            service = new SignInServiceImpl();
        } catch (Exception e) {
            throw new RuntimeException("500");
        }
        return service.signIn(request);
    }
}
