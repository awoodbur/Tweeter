package edu.byu.cs.tweeter.presenter;

import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.services.LoginService;

public abstract class Presenter {

    public User getCurrentUser() {
        return LoginService.getInstance().getCurrentUser();
    }
}
