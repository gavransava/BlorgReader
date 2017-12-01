package savagavran.blorgreader.shared;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import savagavran.blorgreader.di.AppScope;
import savagavran.blorgreader.shared.auth.AuthManager;
import savagavran.blorgreader.shared.auth.AuthManagerImpl;
import savagavran.blorgreader.shared.auth.ConnectivityInterceptor;
import savagavran.blorgreader.shared.repository.BlogsRepositoryImpl;

@AppScope
@Module
public class DataModule {

    private AuthManager mAuthManager;
    private ServiceApi mServiceApi;
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
    public ServiceApi provideServiceApi(Context context,
                                          ConnectivityInterceptor mConnInterceptor) {
        if (mServiceApi == null) {
            mServiceApi = new ServiceApiImpl(context, mConnInterceptor);
        }
        return mServiceApi;
    }

    @AppScope
    @Provides
    public ConnectivityInterceptor provideConnectivityInterceptor(Context context) {
        if (mConnInterceptor == null) {
            mConnInterceptor = new ConnectivityInterceptor(context);
        }
        return mConnInterceptor;
    }

    @AppScope
    @Provides
    public Repository provideBlogsRepository(ServiceApi serviceApi) {
        return new BlogsRepositoryImpl(serviceApi);
    }
}
