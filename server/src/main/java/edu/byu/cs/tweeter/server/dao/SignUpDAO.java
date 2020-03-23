package edu.byu.cs.tweeter.server.dao;

import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.SignUpRequest;
import edu.byu.cs.tweeter.model.service.response.SignUpResponse;

public class SignUpDAO {
    public SignUpResponse signUp(SignUpRequest request) {
        User user = new User(request.getFirstName(), request.getLastName(), request.getAlias(), request.getImageURL());
        String password = Database.getInstance().getUserPassword(user);
        if (password != null && !password.isEmpty()) {
            return new SignUpResponse("User already exists");
        } else {
            Database.getInstance().addUser(user, request.getPassword());
            Database.getInstance().authUser(user, "<auth_token>");
            return new SignUpResponse(user, "<auth_token>");
        }
    }
}
