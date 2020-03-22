package edu.byu.cs.tweeter.server.lambda.signup;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.byu.cs.tweeter.model.service.request.SignUpRequest;
import edu.byu.cs.tweeter.model.service.response.SignUpResponse;
import edu.byu.cs.tweeter.server.service.SignUpServiceImpl;

public class SignUpHandler implements RequestHandler<SignUpRequest, SignUpResponse> {

    @Override
    public SignUpResponse handleRequest(SignUpRequest request, Context context) {
        SignUpServiceImpl service = new SignUpServiceImpl();
        return service.signUp(request);
    }
}
