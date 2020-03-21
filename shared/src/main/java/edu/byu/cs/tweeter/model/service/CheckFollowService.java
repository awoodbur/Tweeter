package edu.byu.cs.tweeter.model.service;

import java.io.IOException;

import edu.byu.cs.tweeter.model.service.request.CheckFollowRequest;
import edu.byu.cs.tweeter.model.service.response.CheckFollowResponse;

public interface CheckFollowService {
    CheckFollowResponse checkFollow(CheckFollowRequest request) throws IOException;
}
