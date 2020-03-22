package edu.byu.cs.tweeter.server.dao;

import java.util.ArrayList;
import java.util.List;

import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.CheckFollowRequest;
import edu.byu.cs.tweeter.model.service.request.FollowUserRequest;
import edu.byu.cs.tweeter.model.service.request.FollowersRequest;
import edu.byu.cs.tweeter.model.service.request.FollowingRequest;
import edu.byu.cs.tweeter.model.service.request.UnfollowUserRequest;
import edu.byu.cs.tweeter.model.service.response.CheckFollowResponse;
import edu.byu.cs.tweeter.model.service.response.FollowUserResponse;
import edu.byu.cs.tweeter.model.service.response.FollowersResponse;
import edu.byu.cs.tweeter.model.service.response.FollowingResponse;
import edu.byu.cs.tweeter.model.service.response.UnfollowUserResponse;

public class FollowDAO {

    public FollowingResponse getFollowing(FollowingRequest request) {
        List<User> followees = Database.getInstance().getUserFollowing(request.getFollower());
        List<User> responseFollowees = new ArrayList<>(request.getLimit());

        boolean hasMorePages = false;
        if (request.getLimit() > 0 && followees != null) {
            int followeesIndex = Database.getInstance().getFolloweeIndex(request.getFollower(), request.getLastFollowee());

            for (int limitCounter = 0; followeesIndex < followees.size() && limitCounter < request.getLimit(); followeesIndex++, limitCounter++) {
                responseFollowees.add(followees.get(followeesIndex));
            }

            hasMorePages = followeesIndex < followees.size();
        }

        return new FollowingResponse(responseFollowees, hasMorePages);
    }

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

    public FollowUserResponse followUser(FollowUserRequest request) {
        Database.getInstance().addUserToFollowing(request.getFollowee(), request.getFollower());
        Database.getInstance().addFolloweeToUser(request.getFollower(), request.getFollowee());
        return new FollowUserResponse();
    }

    public UnfollowUserResponse unfollowUser(UnfollowUserRequest request) {
        Database.getInstance().removeUserFromFollowing(request.getFollowee(), request.getFollower());
        Database.getInstance().removeFolloweeFromUser(request.getFollower(), request.getFollowee());
        return new UnfollowUserResponse();
    }

    public CheckFollowResponse checkFollow(CheckFollowRequest request) {
        return new CheckFollowResponse(Database.getInstance().doesUserFollowUser(request.getFollowee(), request.getFollower()));
    }
}
