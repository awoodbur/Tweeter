package edu.byu.cs.tweeter.server.lambda.signin;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.SignInRequest;
import edu.byu.cs.tweeter.model.service.response.SignInResponse;

import static org.junit.jupiter.api.Assertions.*;

class SignInHandlerTest {

    private SignInHandler handler;

    @BeforeEach
    void setUp() {
        handler = new SignInHandler();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void signInNew() {
        SignInRequest request = new SignInRequest("none", "none", "token");
        SignInResponse response = handler.handleRequest(request, null);
        assertEquals("User not found", response.getMessage());
    }

    @Test
    void signInBadPass() {
        SignInRequest request = new SignInRequest("kirk", "bad password", "token");
        SignInResponse response = handler.handleRequest(request, null);
        assertEquals("Invalid password", response.getMessage());
    }

    @Test
    void signIn() {
        SignInRequest request = new SignInRequest("kirk", "password", "token");
        SignInResponse response = handler.handleRequest(request, null);
        assertEquals(new User("kirk"), response.getUser());
    }
}