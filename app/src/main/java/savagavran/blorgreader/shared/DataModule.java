package savagavran.blorgreader.shared;

import android.content.Context;
import android.content.SharedPreferences;

import dagger.Module;
import dagger.Provides;
import io.reactivex.subjects.ReplaySubject;
import savagavran.blorgreader.di.AppScope;
import savagavran.blorgreader.login.LoginContract;
import savagavran.blorgreader.shared.auth.AuthManager;
import savagavran.blorgreader.shared.auth.AuthManagerImpl;
import savagavran.blorgreader.shared.auth.ConnectivityInterceptor;

@AppScope
@Module
public class DataModule {

    private AuthManager mAuthManager;
    private ConnectivityInterceptor mConnInterceptor;

    @AppScope
    @Provides
    public AuthManager provideAuthManager(Context context,
                                          ConnectivityInterceptor mConnInterceptor) {
        if (mAuthManager == null) {
            mAuthManager = new AuthManagerImpl(context, mConnInterceptor);
        }
        return mAuthManager;
    }

    @AppScope
    @Provides
    public ConnectivityInterceptor provideConnectivityInterceptor(Context context) {
        if (mConnInterceptor == null) {
            mConnInterceptor = new ConnectivityInterceptor(context);
        }
        return mConnInterceptor;
    }
}
