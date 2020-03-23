package edu.byu.cs.tweeter.server.dao;

import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.SignInRequest;
import edu.byu.cs.tweeter.model.service.response.SignInResponse;

public class SignInDAO {
    public SignInResponse signIn(SignInRequest request) {
        User user = Database.getInstance().getUser(request.getAlias());
        if (user == null) {
            return new SignInResponse("User not found");
        }

        String password = Database.getInstance().getUserPassword(user);
        if (password.equals(request.getPassword())) {
            Database.getInstance().authUser(user, "<auth_token>");
            return new SignInResponse(user, "<auth_token>");
        } else {
            return new SignInResponse("Invalid password");
        }
    }
}
