package edu.byu.cs.tweeter.server.dao;

import java.util.ArrayList;
import java.util.List;

import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.FollowersRequest;
import edu.byu.cs.tweeter.model.service.response.FollowersResponse;

public class FollowersDAO {
    public FollowersResponse getFollowers(FollowersRequest request) {
        List<User> followers = Database.getInstance().getUserFollowers(request.getFollowee());
        List<User> responseFollowers = new ArrayList<>(request.getLimit());

        boolean hasMorePages = false;
        if (request.getLimit() > 0 && followers != null) {
            int followersIndex = Database.getInstance().getFollowerIndex(request.getFollowee(), request.getLastFollower());

            for(int limitCounter = 0; followersIndex < followers.size() && limitCounter < request.getLimit(); followersIndex++, limitCounter++) {
                responseFollowers.add(followers.get(followersIndex));
            }

            hasMorePages = followersIndex < followers.size();
        }

        return new FollowersResponse(responseFollowers, hasMorePages);
    }
}
