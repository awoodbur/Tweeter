package edu.byu.cs.tweeter.server.lambda.signup;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.SignUpRequest;
import edu.byu.cs.tweeter.model.service.response.SignUpResponse;

import static org.junit.jupiter.api.Assertions.*;

class SignUpHandlerTest {

    private SignUpHandler handler;

    @BeforeEach
    void setUp() {
        handler = new SignUpHandler();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void signUpExists() {
        SignUpRequest request = new SignUpRequest("james", "kirk", "kirk", "password", "");
        SignUpResponse response = handler.handleRequest(request, null);
        assertEquals("User already exists", response.getMessage());
    }

    @Test
    void signUp() {
        SignUpRequest request = new SignUpRequest("test", "test", "test", "password", "");
        SignUpResponse response = handler.handleRequest(request, null);
        assertEquals(new User("test"), response.getUser());
    }
}