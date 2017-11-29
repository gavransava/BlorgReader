package savagavran.blorgreader.login.di;

import android.support.annotation.NonNull;
import dagger.Module;
import dagger.Provides;
import savagavran.blorgreader.login.LoginContract;
import savagavran.blorgreader.login.presenter.LoginPresenter;
import savagavran.blorgreader.shared.auth.AuthManager;

@LoginScope
@Module
public class LoginModule {

    private LoginContract.LoginScreen mLoginScreen;

    public LoginModule(@NonNull LoginContract.LoginScreen loginScreen) {
        this.mLoginScreen = loginScreen;
    }

    @Provides
    LoginContract.LoginUserActions provideLoginPresenter(@NonNull AuthManager authManager) {
        return new LoginPresenter(mLoginScreen, authManager);
    }
}
