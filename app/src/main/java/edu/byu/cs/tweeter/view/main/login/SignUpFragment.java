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
import edu.byu.cs.tweeter.presenter.SignUpPresenter;
import edu.byu.cs.tweeter.view.main.MainActivity;

public class SignUpFragment extends Fragment implements SignUpPresenter.View {

    private EditText mFirstNameField;
    private EditText mLastNameField;
    private EditText mAlias;
    private EditText mPassword;
    private EditText mImageURL;
    private Button mButton;

    private SignUpPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signup, container, false);

        presenter = new SignUpPresenter(this);

        mFirstNameField = view.findViewById(R.id.fragment_signup_first_name);
        mFirstNameField.addTextChangedListener(watcher);
        mLastNameField = view.findViewById(R.id.fragment_signup_last_name);
        mLastNameField.addTextChangedListener(watcher);
        mAlias = view.findViewById(R.id.fragment_signup_alias);
        mAlias.addTextChangedListener(watcher);
        mPassword = view.findViewById(R.id.fragment_signup_password);
        mPassword.addTextChangedListener(watcher);
        mImageURL = view.findViewById(R.id.fragment_signup_image_url);
        mImageURL.addTextChangedListener(watcher);
        mButton = view.findViewById(R.id.fragment_signup_button);
        mButton.setEnabled(false);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AuthResponse response = presenter.signUp(new AuthRequest(mFirstNameField.getText().toString(),
                        mLastNameField.getText().toString(),
                        mAlias.getText().toString(),
                        mPassword.getText().toString(), mImageURL.getText().toString()));
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
            // Do nothing
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // Do nothing
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (mFirstNameField.getText().length() != 0 && mLastNameField.getText().length() != 0
                    && mAlias.getText().length() != 0 && mPassword.getText().length() != 0
                    && mImageURL.getText().length() != 0) {
                mButton.setEnabled(true);
            } else {
                mButton.setEnabled(false);
            }
        }
    };
}
