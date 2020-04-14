package edu.byu.cs.tweeter.server.lambda.signin;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.SignInRequest;
import edu.byu.cs.tweeter.model.service.request.SignUpRequest;
import edu.byu.cs.tweeter.model.service.response.SignInResponse;
import edu.byu.cs.tweeter.server.dao.AuthsDAO;
import edu.byu.cs.tweeter.server.dao.UsersDAO;

import static org.junit.jupiter.api.Assertions.*;

class SignInHandlerTest {

    private SignInHandler handler;
    private User user1;
    private String password;
    private String hashed_password;
    private List<String> tokens;

    @BeforeEach
    void setUp() {
        handler = new SignInHandler();

        user1 = new User("test99", "test99", "test99", "test99");
        password = "password";
        hashed_password = "5f4dcc3b5aa765d61d8327deb882cf99";
        tokens = new ArrayList<>();
        UsersDAO usersDAO = new UsersDAO();
        usersDAO.signUp(new SignUpRequest(user1.getFirstName(), user1.getLastName(), user1.getAlias(), hashed_password, user1.getImageUrl()));
    }

    @AfterEach
    void tearDown() {
        UsersDAO usersDAO = new UsersDAO();
        usersDAO.deleteUser(user1);

        AuthsDAO authsDAO = new AuthsDAO();
        for (String token : tokens) {
            authsDAO.deleteToken(token);
        }
    }

    @Test
    void signInNew() {
        SignInRequest request = new SignInRequest(user1.getAlias() + "new", password);
        SignInResponse response = handler.handleRequest(request, null);
        assertEquals("User not found", response.getMessage());
    }

    @Test
    void signInWrongPass() {
        SignInRequest request = new SignInRequest(user1.getAlias(), password + "wrong");
        SignInResponse response = handler.handleRequest(request, null);
        assertEquals("Invalid password", response.getMessage());
    }

    @Test
    void signIn() {
        SignInRequest request = new SignInRequest(user1.getAlias(), password);
        SignInResponse response = handler.handleRequest(request, null);
        assertEquals(user1, response.getUser());
        tokens.add(response.getToken());
    }
}