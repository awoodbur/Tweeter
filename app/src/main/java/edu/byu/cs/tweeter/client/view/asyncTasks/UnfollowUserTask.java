package edu.byu.cs.tweeter.client.view.asyncTasks;

import android.os.AsyncTask;

import edu.byu.cs.tweeter.model.domain.Follow;
import edu.byu.cs.tweeter.model.service.response.Response;
import edu.byu.cs.tweeter.client.presenter.UserPresenter;

public class UnfollowUserTask extends AsyncTask<Follow, Void, Response> {

    private final UserPresenter presenter;
    private final UnfollowUserObserver observer;

    public interface UnfollowUserObserver {
        void unfollowUserComplete(Response response);
    }

    public UnfollowUserTask(UserPresenter presenter, UnfollowUserObserver observer) {
        this.presenter = presenter;
        this.observer = observer;
    }

    @Override
    protected Response doInBackground(Follow... follows) {
        return presenter.unfollowUser(follows[0]);
    }

    @Override
    protected void onPostExecute(Response response) {
        if (observer != null) {
            observer.unfollowUserComplete(response);
        }
    }
}
