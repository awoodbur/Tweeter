package edu.byu.cs.tweeter.server.service;

import java.sql.Timestamp;
import java.util.UUID;

import edu.byu.cs.tweeter.model.service.SignInService;
import edu.byu.cs.tweeter.model.service.request.SignInRequest;
import edu.byu.cs.tweeter.model.service.response.SignInResponse;
import edu.byu.cs.tweeter.server.dao.AuthsDAO;
import edu.byu.cs.tweeter.server.dao.UsersDAO;

public class SignInServiceImpl extends ServiceImpl implements SignInService {

    @Override
    public SignInResponse signIn(SignInRequest request) {
        AuthsDAO authsDAO = new AuthsDAO();
        UsersDAO usersDAO = new UsersDAO();

        SignInResponse response = usersDAO.signIn(request);
        if (response.isSuccess()) {
            String token = UUID.randomUUID().toString();
            long curr_time = new Timestamp(System.currentTimeMillis()).getTime();
            authsDAO.addToken(token, String.valueOf(curr_time));
            response.setToken(token);
        }
        return response;
    }
}
