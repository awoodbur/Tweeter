package edu.byu.cs.tweeter.client.view.asyncTasks;

import android.os.AsyncTask;

import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.response.Response;
import edu.byu.cs.tweeter.client.presenter.UserPresenter;

public class LogoutTask extends AsyncTask<User, Void, Response> {

    private final UserPresenter presenter;
    private final LogoutObserver observer;

    public interface LogoutObserver {
        void logoutComplete(Response response);
    }

    public LogoutTask(UserPresenter presenter, LogoutObserver observer) {
        this.presenter = presenter;
        this.observer = observer;
    }

    @Override
    protected Response doInBackground(User... users) {
        presenter.setCurrentUser(null);
        presenter.setDisplayUser(null);
        return new Response(true, "Logged out successfully.");
    }

    @Override
    protected void onPostExecute(Response response) {
        if (observer != null) {
            observer.logoutComplete(response);
        }
    }
}
