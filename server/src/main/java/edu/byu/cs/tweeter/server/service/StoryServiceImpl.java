package edu.byu.cs.tweeter.server.service;

import edu.byu.cs.tweeter.model.service.StoryService;
import edu.byu.cs.tweeter.model.service.request.StoryRequest;
import edu.byu.cs.tweeter.model.service.response.StoryResponse;
import edu.byu.cs.tweeter.server.dao.StoriesDAO;

public class StoryServiceImpl extends ServiceImpl implements StoryService {

    @Override
    public StoryResponse getStory(StoryRequest request) {
        validateToken(request.getToken());
        StoriesDAO dao = new StoriesDAO();
        return dao.getStory(request);
    }
}
