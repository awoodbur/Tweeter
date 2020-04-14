package edu.byu.cs.tweeter.client.view.main.login;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import edu.byu.cs.tweeter.R;
import edu.byu.cs.tweeter.client.presenter.SignInPresenter;
import edu.byu.cs.tweeter.client.view.asyncTasks.SignInTask;
import edu.byu.cs.tweeter.client.view.main.MainActivity;
import edu.byu.cs.tweeter.model.service.request.SignInRequest;
import edu.byu.cs.tweeter.model.service.response.SignInResponse;

public class SignInFragment extends Fragment implements SignInTask.SignInObserver, SignInPresenter.View {

    private static final String TAG = SignInFragment.class.getName();

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
                SignInTask signInTask = new SignInTask(presenter, SignInFragment.this);
                String alias = mAlias.getText().toString();
                if (alias.charAt(0) == '@') {
                    alias = alias.substring(1);
                }
                SignInRequest request = new SignInRequest(alias, mPassword.getText().toString());
                signInTask.execute(request);
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

    @Override
    public void signInComplete(SignInResponse response) {
        if (response.isSuccess()) {
            startActivity(MainActivity.newIntent(getActivity()));
        } else {
            Toast.makeText(getActivity(), response.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void handleException(Exception e) {
        Log.e(TAG, e.getMessage(), e);
        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
    }
}
