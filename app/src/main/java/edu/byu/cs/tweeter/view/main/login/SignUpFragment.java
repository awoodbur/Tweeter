package edu.byu.cs.tweeter.view.main.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import edu.byu.cs.tweeter.R;
import edu.byu.cs.tweeter.presenter.SignUpPresenter;

public class SignUpFragment extends Fragment implements SignUpPresenter.View {

    private SignUpPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signup, container, false);

        presenter = new SignUpPresenter(this);

        return view;
    }
}
