package edu.byu.cs.tweeter.server.lambda.signout;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.SignInRequest;
import edu.byu.cs.tweeter.model.service.request.SignOutRequest;
import edu.byu.cs.tweeter.model.service.response.SignInResponse;
import edu.byu.cs.tweeter.model.service.response.SignOutResponse;
import edu.byu.cs.tweeter.server.dao.AuthsDAO;
import edu.byu.cs.tweeter.server.lambda.signin.SignInHandler;

import static org.junit.jupiter.api.Assertions.*;

class SignOutHandlerTest {

    private SignOutHandler handler;
    private String token;
    private User user1;

    @BeforeEach
    void setUp() {
        handler = new SignOutHandler();

        user1 = new User("test99", "test99", "test99", "test99");
        token = "new_token";
        AuthsDAO authsDAO = new AuthsDAO();
        authsDAO.addToken(token, "12345");
    }

    @AfterEach
    void tearDown() {
        AuthsDAO authsDAO = new AuthsDAO();
        authsDAO.deleteToken(token);
    }

    @Test
    void signOut() {
        SignOutRequest request = new SignOutRequest(user1, token);
        SignOutResponse response = handler.handleRequest(request, null);
        assertTrue(response.isSuccess());

        AuthsDAO authsDAO = new AuthsDAO();
        String timestamp = authsDAO.validateToken(token);
        assertNull(timestamp);
    }
}