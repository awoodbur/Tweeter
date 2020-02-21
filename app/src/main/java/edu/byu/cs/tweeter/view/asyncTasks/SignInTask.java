package edu.byu.cs.tweeter.view.asyncTasks;

import android.os.AsyncTask;

import edu.byu.cs.tweeter.net.request.AuthRequest;
import edu.byu.cs.tweeter.net.response.AuthResponse;
import edu.byu.cs.tweeter.presenter.SignInPresenter;

public class SignInTask extends AsyncTask<AuthRequest, Void, AuthResponse> {

    private final SignInPresenter presenter;
    private final SignInObserver observer;

    public interface SignInObserver {
        void signInComplete(AuthResponse response);
    }

    public SignInTask(SignInPresenter presenter, SignInObserver observer) {
        this.presenter = presenter;
        this.observer = observer;
    }

    @Override
    protected AuthResponse doInBackground(AuthRequest... authRequests) {
        return presenter.signIn(authRequests[0]);
    }

    @Override
    protected void onPostExecute(AuthResponse response) {
        if (observer != null) {
            observer.signInComplete(response);
        }
    }
}
