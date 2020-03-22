package edu.byu.cs.tweeter.server.service;

import edu.byu.cs.tweeter.model.service.SignOutService;
import edu.byu.cs.tweeter.model.service.request.SignOutRequest;
import edu.byu.cs.tweeter.model.service.response.SignOutResponse;
import edu.byu.cs.tweeter.server.dao.UsersDAO;

public class SignOutServiceImpl implements SignOutService {

    @Override
    public SignOutResponse signOut(SignOutRequest request) {
        UsersDAO dao = new UsersDAO();
        return dao.signOut(request);
    }
}
