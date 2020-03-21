package edu.byu.cs.tweeter.client.presenter;

import java.io.IOException;

import edu.byu.cs.tweeter.client.model.service.SignUpServiceProxy;
import edu.byu.cs.tweeter.model.service.SignUpService;
import edu.byu.cs.tweeter.model.service.request.SignUpRequest;
import edu.byu.cs.tweeter.model.service.response.SignUpResponse;

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

    public SignUpResponse signUp(SignUpRequest request) throws IOException {
        SignUpService service = new SignUpServiceProxy();
        return service.signUp(request);
    }
}
