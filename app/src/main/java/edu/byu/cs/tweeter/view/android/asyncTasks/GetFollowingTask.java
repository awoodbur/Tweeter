package edu.byu.cs.tweeter.view.android.asyncTasks;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.byu.cs.tweeter.model.net.request.FollowingRequest;
import edu.byu.cs.tweeter.model.net.response.FollowingResponse;
import edu.byu.cs.tweeter.presenter.FollowingPresenter;
import edu.byu.cs.tweeter.view.android.domain.User;
import edu.byu.cs.tweeter.view.android.util.ImageUtils;

public class GetFollowingTask extends AsyncTask<FollowingRequest, Void, FollowingResponse<User>> {

    private final GetFolloweesObserver observer;

    public interface GetFolloweesObserver {
        void followeesRetrieved(FollowingResponse<User> followingResponse);
    }

    public GetFollowingTask(GetFolloweesObserver observer) {
        this.observer = observer;
    }

    @Override
    protected FollowingResponse<User> doInBackground(FollowingRequest... followingRequests) {
        FollowingResponse<edu.byu.cs.tweeter.model.domain.User> followingResponse = FollowingPresenter.getInstance().getFollowing(followingRequests[0]);
        List<User> users = getAndroidUsersFromDomainUsers(followingResponse.getFollowees());
        return new FollowingResponse<>(users, followingResponse.hasMorePages());
    }

    private List<User> getAndroidUsersFromDomainUsers(List<edu.byu.cs.tweeter.model.domain.User> domainUsers) {
        List<User> users = new ArrayList<>(domainUsers.size());

        for(edu.byu.cs.tweeter.model.domain.User domainUser : domainUsers) {

            Drawable drawable;

            try {
                drawable = ImageUtils.drawableFromUrl(domainUser.getImageUrl());
            } catch (IOException e) {
                Log.e(this.getClass().getName(), e.toString(), e);
                drawable = null;
            }

            users.add( new User(domainUser.getFirstName(), domainUser.getLastName(), domainUser.getImageUrl(), drawable) );
        }

        return users;
    }

    @Override
    protected void onPostExecute(FollowingResponse<User> followingResponse) {

        if(observer != null) {
            observer.followeesRetrieved(followingResponse);
        }
    }
}
