package savagavran.blorgreader.login.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.util.PatternsCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


import java.util.regex.Pattern;

import savagavran.blorgreader.R;


public class LoginFragment extends Fragment {

    private Button mLoginButton;
    private AppCompatEditText mEmailText;
    private AppCompatEditText mPasswordText;

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        Toolbar mActionBarToolbar = view.findViewById(R.id.toolbar);
        mLoginButton = view.findViewById(R.id.login_button);
        mEmailText = view.findViewById(R.id.email_text);
        mPasswordText = view.findViewById(R.id.password_text);

        setupLoginButton();
        setupToolbar(mActionBarToolbar);
        return view ;
    }

    private void setupLoginButton() {
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isValidEmail(mEmailText.getText().toString())
                        && isValidPassword(mPasswordText.getText().toString())){
                    //api call
                } else {
                    Toast.makeText(getContext(),
                            getString(R.string.invalid_credentials),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setupToolbar(Toolbar mActionBarToolbar) {
        AppCompatActivity activity = (AppCompatActivity) getActivity();

        activity.setSupportActionBar(mActionBarToolbar);
        ActionBar actionBar = activity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(getString(R.string.login));
        }
    }

    private boolean isValidEmail(String email) {
        Pattern pattern = PatternsCompat.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }

    private boolean isValidPassword(String password) {
        return password.length() >= 6;
    }
}
