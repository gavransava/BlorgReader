package savagavran.blorgreader.login.presenter;

import android.support.annotation.NonNull;

import java.lang.ref.WeakReference;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import savagavran.blorgreader.login.LoginContract;
import savagavran.blorgreader.shared.auth.AuthManager;
import savagavran.blorgreader.shared.retrofit.Credentials;

public class LoginPresenter implements LoginContract.LoginUserActions {

    private WeakReference<LoginContract.LoginScreen> mLoginScreen;
    private AuthManager mAuthManager;

    public LoginPresenter(@NonNull LoginContract.LoginScreen loginScreen,
                          @NonNull AuthManager authManager) {
        this.mLoginScreen = new WeakReference<>(loginScreen);
        this.mAuthManager = authManager;
    }

    @Override
    public void onLoginClicked(String email, String password) {
        Credentials credentials = new Credentials(email, password);
        LoginContract.LoginScreen loginScreen = mLoginScreen.get();
        if (loginScreen != null) {
            mAuthManager.login(credentials)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(token -> {
                        onUserAuthenticated(loginScreen);
                    }, throwable -> {
                        loginScreen.showLoginFailedError(throwable.getMessage());
                    });
        }
    }

    private void onUserAuthenticated(LoginContract.LoginScreen loginScreen) {
        loginScreen.openBlogsScreen();
    }
}
