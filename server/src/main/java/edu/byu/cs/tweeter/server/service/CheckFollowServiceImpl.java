package edu.byu.cs.tweeter.server.service;

import edu.byu.cs.tweeter.model.service.CheckFollowService;
import edu.byu.cs.tweeter.model.service.request.CheckFollowRequest;
import edu.byu.cs.tweeter.model.service.response.CheckFollowResponse;
import edu.byu.cs.tweeter.server.dao.CheckFollowDAO;

public class CheckFollowServiceImpl implements CheckFollowService {

    @Override
    public CheckFollowResponse checkFollow(CheckFollowRequest request) {
        CheckFollowDAO dao = new CheckFollowDAO();
        return dao.checkFollow(request);
    }
}
