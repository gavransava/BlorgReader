package savagavran.blorgreader.login.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.util.PatternsCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.regex.Pattern;

import javax.inject.Inject;

import savagavran.blorgreader.App;
import savagavran.blorgreader.R;
import savagavran.blorgreader.login.LoginContract;
import savagavran.blorgreader.login.di.DaggerLoginComponent;
import savagavran.blorgreader.main.MainActivity;


public class LoginFragment extends Fragment implements LoginContract.LoginScreen {

    @Inject
    LoginContract.LoginUserActions mLoginPresenter;

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

        App app = App.getAppContext(getActivity());

        DaggerLoginComponent
                .builder()
                .appComponent(app.getComponent())
                .loginModule(app.getLoginModule(this))
                .build()
                .inject(this);

        Toolbar mActionBarToolbar = view.findViewById(R.id.toolbar);
        mLoginButton = view.findViewById(R.id.login_button);
        mEmailText = view.findViewById(R.id.email_text);
        mPasswordText = view.findViewById(R.id.password_text);

        setupLoginButton();
        setupToolbar(mActionBarToolbar);
        return view;
    }

    private void setupLoginButton() {
        mLoginButton.setOnClickListener(view -> {
            String email = mEmailText.getText().toString();
            String password = mPasswordText.getText().toString();
            if (isValidEmail(email) && isValidPassword(password)) {
                mLoginPresenter.onLoginClicked(email, password);
            } else {
                Toast.makeText(getContext(),
                        getString(R.string.invalid_credentials),
                        Toast.LENGTH_SHORT).show();
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

    @Override
    public void openBlogsScreen() {
        Intent intent = new Intent(getContext(), MainActivity.class);
        startActivity(intent);
        // remove loginActivity from backStack
        getActivity().finish();
    }

    @Override
    public void showNoInternetConnection() {
        Toast.makeText(getContext(),
                getString(R.string.no_internet_connection),
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoginFailedError(String message) {
        Toast.makeText(getContext(),
                message,
                Toast.LENGTH_SHORT).show();
    }
}
