package edu.byu.cs.tweeter.server.service;

import edu.byu.cs.tweeter.model.service.SignUpService;
import edu.byu.cs.tweeter.model.service.request.SignUpRequest;
import edu.byu.cs.tweeter.model.service.response.SignUpResponse;
import edu.byu.cs.tweeter.server.dao.UsersDAO;

public class SignUpServiceImpl extends ServiceImpl implements SignUpService {

    @Override
    public SignUpResponse signUp(SignUpRequest request) {
        validateToken(request.getToken());
        UsersDAO dao = new UsersDAO();
        return dao.signUp(request);
    }
}
