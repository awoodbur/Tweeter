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
import edu.byu.cs.tweeter.view.response.UIFollowingResponse;

public class GetFollowingTask extends AsyncTask<FollowingRequest, Void, UIFollowingResponse> {

    private final GetFolloweesObserver observer;

    public interface GetFolloweesObserver {
        void followeesRetrieved(UIFollowingResponse followingResponse);
    }

    public GetFollowingTask(GetFolloweesObserver observer) {
        this.observer = observer;
    }

    @Override
    protected UIFollowingResponse doInBackground(FollowingRequest... followingRequests) {
        FollowingResponse followingResponse = FollowingPresenter.getInstance().getFollowing(followingRequests[0]);
        List<User> users = getAndroidUsersFromDomainUsers(followingResponse.getFollowees());
        return new UIFollowingResponse(users, followingResponse.hasMorePages());
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
    protected void onPostExecute(UIFollowingResponse uiFollowingResponse) {

        if(observer != null) {
            observer.followeesRetrieved(uiFollowingResponse);
        }
    }
}
