package edu.byu.cs.tweeter.view.main.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import edu.byu.cs.tweeter.R;
import edu.byu.cs.tweeter.net.request.AuthRequest;
import edu.byu.cs.tweeter.net.response.AuthResponse;
import edu.byu.cs.tweeter.presenter.SignInPresenter;
import edu.byu.cs.tweeter.view.main.MainActivity;

public class SignInFragment extends Fragment implements SignInPresenter.View {

    private EditText mAlias;
    private EditText mPassword;
    private Button mButton;

    private SignInPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signin, container, false);

        presenter = new SignInPresenter(this);

        mAlias = view.findViewById(R.id.fragment_signin_alias);
        mAlias.addTextChangedListener(watcher);
        mPassword = view.findViewById(R.id.fragment_signin_password);
        mPassword.addTextChangedListener(watcher);
        mButton = view.findViewById(R.id.fragment_signin_button);
        mButton.setEnabled(false);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AuthResponse response = presenter.signIn(new AuthRequest(mAlias.getText().toString(), mPassword.getText().toString()));
                if (response.isSuccess()) {
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_TASK_ON_HOME);
                    startActivity(intent);
                } else {
                    Toast.makeText(getActivity(), response.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    private final TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // Do nothing
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (mAlias.getText().length() != 0 && mPassword.getText().length() != 0) {
                mButton.setEnabled(true);
            } else {
                mButton.setEnabled(false);
            }
        }
    };
}
