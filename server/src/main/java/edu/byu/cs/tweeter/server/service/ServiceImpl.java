package edu.byu.cs.tweeter.server.service;

import java.sql.Timestamp;

import edu.byu.cs.tweeter.server.dao.AuthsDAO;

public class ServiceImpl {
    public void validateToken(String token) {
        long diff = 3600000L;   // Tokens valid for one hour

        if (token == null || token.isEmpty()) {
            throw new RuntimeException("401");
        }

        AuthsDAO authsDAO = new AuthsDAO();
        String resp = authsDAO.validateToken(token);
        if (resp == null || resp.isEmpty()) {
            throw new RuntimeException("401");
        }

        long timestamp = Long.parseLong(resp);
        long curr_time = new Timestamp(System.currentTimeMillis()).getTime();
        if (curr_time - timestamp > diff) {
            throw new RuntimeException("401");
        }
    }
}
