package edu.byu.cs.tweeter.server.lambda.getuser;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.GetUserRequest;
import edu.byu.cs.tweeter.model.service.response.GetUserResponse;

import static org.junit.jupiter.api.Assertions.*;

class GetUserHandlerTest {

    private GetUserHandler handler;

    @BeforeEach
    void setUp() {
        handler = new GetUserHandler();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getUser() {
        GetUserRequest request = new GetUserRequest("kirk", "token");
        GetUserResponse response = handler.handleRequest(request, null);
        assertEquals(new User("kirk"), response.getUser());
    }
}