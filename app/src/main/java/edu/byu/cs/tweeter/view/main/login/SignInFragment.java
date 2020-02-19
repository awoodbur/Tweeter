package edu.byu.cs.tweeter.view.main.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import edu.byu.cs.tweeter.R;
import edu.byu.cs.tweeter.presenter.SignInPresenter;

public class SignInFragment extends Fragment implements SignInPresenter.View {

    private SignInPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signin, container, false);

        presenter = new SignInPresenter(this);

        return view;
    }
}
