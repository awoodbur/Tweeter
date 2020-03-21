package edu.byu.cs.tweeter.client.presenter;

import edu.byu.cs.tweeter.client.model.service.UserService;
import edu.byu.cs.tweeter.model.service.request.AuthRequest;
import edu.byu.cs.tweeter.model.service.response.AuthResponse;

/**
 * The presenter for the "sign up" functionality of the application.
 */
public class SignUpPresenter extends Presenter {

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
    public SignUpPresenter(View view) {
        this.view = view;
    }

    public AuthResponse signUp(AuthRequest request) {
        return UserService.getInstance().signUp(request);
    }
}