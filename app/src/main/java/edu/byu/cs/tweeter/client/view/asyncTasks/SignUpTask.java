package edu.byu.cs.tweeter.client.view.asyncTasks;

import android.os.AsyncTask;

import edu.byu.cs.tweeter.model.service.request.AuthRequest;
import edu.byu.cs.tweeter.model.service.response.AuthResponse;
import edu.byu.cs.tweeter.client.presenter.SignUpPresenter;

public class SignUpTask extends AsyncTask<AuthRequest, Void, AuthResponse> {

    private final SignUpPresenter presenter;
    private final SignUpObserver observer;

    public interface SignUpObserver {
        void signUpComplete(AuthResponse response);
    }

    public SignUpTask(SignUpPresenter presenter, SignUpObserver observer) {
        this.presenter = presenter;
        this.observer = observer;
    }

    @Override
    protected AuthResponse doInBackground(AuthRequest... authRequests) {
        return presenter.signUp(authRequests[0]);
    }

    @Override
    protected void onPostExecute(AuthResponse response) {
        if (observer != null) {
            observer.signUpComplete(response);
        }
    }
}
