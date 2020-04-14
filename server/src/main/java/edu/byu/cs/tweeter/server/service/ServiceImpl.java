package edu.byu.cs.tweeter.server.service;

import java.sql.Timestamp;

import edu.byu.cs.tweeter.server.dao.AuthsDAO;

public class ServiceImpl {

    public void validateToken(String token) {
        long valid_length = 3600000L; // Tokens valid for 1 hour

        AuthsDAO authsDAO = new AuthsDAO();
        long timestamp = Long.parseLong(authsDAO.validateToken(token));
        long curr_time = new Timestamp(System.currentTimeMillis()).getTime();

        if (curr_time - timestamp > valid_length) {
            authsDAO.deleteToken(token);
            throw new RuntimeException("401");
        }
    }
}
