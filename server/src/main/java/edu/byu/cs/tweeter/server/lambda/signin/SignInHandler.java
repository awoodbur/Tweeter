package edu.byu.cs.tweeter.server.lambda.signin;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.byu.cs.tweeter.model.service.request.SignInRequest;
import edu.byu.cs.tweeter.model.service.response.SignInResponse;
import edu.byu.cs.tweeter.server.service.SignInServiceImpl;

public class SignInHandler implements RequestHandler<SignInRequest, SignInResponse> {

    @Override
    public SignInResponse handleRequest(SignInRequest request, Context context) {
        SignInServiceImpl service = new SignInServiceImpl();
        return service.signIn(request);
    }
}
