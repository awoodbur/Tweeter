package edu.byu.cs.tweeter.server.service;

import edu.byu.cs.tweeter.model.service.SignInService;
import edu.byu.cs.tweeter.model.service.request.SignInRequest;
import edu.byu.cs.tweeter.model.service.response.SignInResponse;
import edu.byu.cs.tweeter.server.dao.SignInDAO;
import edu.byu.cs.tweeter.server.dao.UsersDAO;

public class SignInServiceImpl implements SignInService {

    @Override
    public SignInResponse signIn(SignInRequest request) {
        SignInDAO dao = new SignInDAO();
        return dao.signIn(request);
    }
}
