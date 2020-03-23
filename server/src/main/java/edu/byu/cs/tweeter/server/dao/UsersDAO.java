package edu.byu.cs.tweeter.server.dao;

import java.util.Map;

import javax.xml.crypto.Data;

import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.GetUserRequest;
import edu.byu.cs.tweeter.model.service.request.SignInRequest;
import edu.byu.cs.tweeter.model.service.request.SignOutRequest;
import edu.byu.cs.tweeter.model.service.request.SignUpRequest;
import edu.byu.cs.tweeter.model.service.response.GetUserResponse;
import edu.byu.cs.tweeter.model.service.response.SignInResponse;
import edu.byu.cs.tweeter.model.service.response.SignOutResponse;
import edu.byu.cs.tweeter.model.service.response.SignUpResponse;

public class UsersDAO {

    public SignInResponse signIn(SignInRequest request) {
        User user = Database.getInstance().getUser(request.getAlias());
        if (user == null) {
            return new SignInResponse("User not found");
        }

        String password = Database.getInstance().getUserPassword(user);
        if (password.equals(request.getPassword())) {
            Database.getInstance().authUser(user, "<auth_token>");
            return new SignInResponse(user);
        } else {
            return new SignInResponse("Invalid password");
        }
    }

    public SignUpResponse signUp(SignUpRequest request) {
        User user = new User(request.getFirstName(), request.getLastName(), request.getAlias(), request.getImageURL());
        String password = Database.getInstance().getUserPassword(user);
        if (password != null && !password.isEmpty()) {
            return new SignUpResponse("User already exists");
        } else {
            Database.getInstance().addUser(user, request.getPassword());
            Database.getInstance().authUser(user, "<auth_token>");
            return new SignUpResponse(user);
        }
    }

    public SignOutResponse signOut(SignOutRequest request) {
        Database.getInstance().deauthUser(request.getUser());
        return new SignOutResponse();
    }

    public GetUserResponse getUser(GetUserRequest request) {
        return new GetUserResponse(Database.getInstance().getUser(request.getAlias()));
    }

    public void reset() {
        Database.getInstance().resetDatabase();
    }
}
