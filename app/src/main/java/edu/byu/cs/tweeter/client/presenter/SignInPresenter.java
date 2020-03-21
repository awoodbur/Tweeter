package edu.byu.cs.tweeter.client.presenter;

import java.io.IOException;

import edu.byu.cs.tweeter.client.model.service.SignInServiceProxy;
import edu.byu.cs.tweeter.model.service.SignInService;
import edu.byu.cs.tweeter.model.service.request.SignInRequest;
import edu.byu.cs.tweeter.model.service.response.SignInResponse;

/**
 * The presenter for the "sign in" functionality of the application.
 */
public class SignInPresenter extends Presenter {

    private final View view;

    /**
     * The interface by which this presenter sends notifications to it's view.
     */
    public interface View {
        // If needed, specify methods here that will be called on the view in response to model updates
    }

    /**
     * Creates an instance.
     *
     * @param view the view for which this class is the presenter.
     */
    public SignInPresenter(View view) {
        this.view = view;
    }

    public SignInResponse signIn(SignInRequest request) throws IOException {
        SignInService service = new SignInServiceProxy();
        SignInResponse response = service.signIn(request);
        setCurrentUser(response.getUser());
        return response;
    }
}
