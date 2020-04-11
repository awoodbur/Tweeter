package edu.byu.cs.tweeter.server.lambda.signup;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.byu.cs.tweeter.model.service.request.SignUpRequest;
import edu.byu.cs.tweeter.model.service.response.SignUpResponse;
import edu.byu.cs.tweeter.server.service.SignUpServiceImpl;

public class SignUpHandler implements RequestHandler<SignUpRequest, SignUpResponse> {

    @Override
    public SignUpResponse handleRequest(SignUpRequest request, Context context) {
        if (request.getFirstName() == null || request.getFirstName().isEmpty()
            || request.getLastName() == null || request.getLastName().isEmpty()
            || request.getAlias() == null || request.getAlias().isEmpty()
            || request.getPassword() == null || request.getPassword().isEmpty()) {
            throw new RuntimeException("400");
        }

        SignUpServiceImpl service;
        try {
            service = new SignUpServiceImpl();
        } catch (Exception e) {
            throw new RuntimeException("500");
        }
        return service.signUp(request);
    }
}
