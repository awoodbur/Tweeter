package edu.byu.cs.tweeter.server.service;

import java.sql.Timestamp;
import java.util.UUID;

import edu.byu.cs.tweeter.model.service.SignUpService;
import edu.byu.cs.tweeter.model.service.request.SignUpRequest;
import edu.byu.cs.tweeter.model.service.response.SignUpResponse;
import edu.byu.cs.tweeter.server.dao.AuthsDAO;
import edu.byu.cs.tweeter.server.dao.UsersDAO;

public class SignUpServiceImpl extends ServiceImpl implements SignUpService {

    @Override
    public SignUpResponse signUp(SignUpRequest request) {
        AuthsDAO authsDAO = new AuthsDAO();
        UsersDAO usersDAO = new UsersDAO();

        try {
            request.setPassword(generateHash(request.getPassword()));
            request.setImageURL(uploadImage(request.getImageURL(), request.getAlias()));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("500");
        }

        SignUpResponse response = usersDAO.signUp(request);
        if (response.isSuccess()) {
            String token = UUID.randomUUID().toString();
            long curr_time = new Timestamp(System.currentTimeMillis()).getTime();
            authsDAO.addToken(token, String.valueOf(curr_time));
            response.setToken(token);
        }
        return response;
    }
}
