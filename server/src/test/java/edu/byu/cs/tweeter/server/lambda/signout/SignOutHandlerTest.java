package edu.byu.cs.tweeter.server.lambda.signout;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.SignInRequest;
import edu.byu.cs.tweeter.model.service.request.SignOutRequest;
import edu.byu.cs.tweeter.model.service.response.SignInResponse;
import edu.byu.cs.tweeter.model.service.response.SignOutResponse;
import edu.byu.cs.tweeter.server.lambda.signin.SignInHandler;

import static org.junit.jupiter.api.Assertions.*;

class SignOutHandlerTest {

    private SignOutHandler handler;

    @BeforeEach
    void setUp() {
        handler = new SignOutHandler();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void signOut() {
        SignInHandler setup_handler = new SignInHandler();
        SignInRequest setup = new SignInRequest("kirk", "password", "token");
        SignInResponse setup_resp = setup_handler.handleRequest(setup, null);
        User user = setup_resp.getUser();

        SignOutRequest request = new SignOutRequest(user, "token");
        SignOutResponse response = handler.handleRequest(request, null);
        assertTrue(response.isSuccess());
    }
}