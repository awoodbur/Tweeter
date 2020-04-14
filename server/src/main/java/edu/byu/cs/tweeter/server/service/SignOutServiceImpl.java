package edu.byu.cs.tweeter.server.service;

import edu.byu.cs.tweeter.model.service.SignOutService;
import edu.byu.cs.tweeter.model.service.request.SignOutRequest;
import edu.byu.cs.tweeter.model.service.response.SignOutResponse;
import edu.byu.cs.tweeter.server.dao.UsersDAO;

public class SignOutServiceImpl extends ServiceImpl implements SignOutService {

    @Override
    public SignOutResponse signOut(SignOutRequest request) {
        validateToken(request.getToken());
        UsersDAO dao = new UsersDAO();
        return dao.signOut(request);
    }
}
