package edu.byu.cs.tweeter.server.service;

import java.io.IOException;

import edu.byu.cs.tweeter.model.service.SignOutService;
import edu.byu.cs.tweeter.model.service.request.SignOutRequest;
import edu.byu.cs.tweeter.model.service.response.SignOutResponse;
import edu.byu.cs.tweeter.server.dao.SignOutDAO;

public class SignOutServiceImpl implements SignOutService {

    @Override
    public SignOutResponse signOut(SignOutRequest request) {
        SignOutDAO dao = new SignOutDAO();
        return dao.signOut(request);
    }
}
