package savagavran.blorgreader.shared;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import savagavran.blorgreader.di.AppScope;
import savagavran.blorgreader.shared.auth.AuthManager;
import savagavran.blorgreader.shared.auth.AuthManagerImpl;
import savagavran.blorgreader.shared.auth.ConnectivityInterceptor;
import savagavran.blorgreader.shared.repository.BlogRepositoryImpl;
import savagavran.blorgreader.shared.repository.BlogsRepositoryImpl;
import savagavran.blorgreader.utils.Blog;
import savagavran.blorgreader.utils.BlogItem;

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
                                        AuthManager authManager,
                                        ConnectivityInterceptor mConnInterceptor) {
        if (mServiceApi == null) {
            mServiceApi = new ServiceApiImpl(context, authManager, mConnInterceptor);
        }
        return mServiceApi;
    }

    @AppScope
    @Provides
    public RepositoryBlogList<BlogItem> provideBlogsRepository(ServiceApi serviceApi) {
        return new BlogsRepositoryImpl(serviceApi);
    }
    @AppScope
    @Provides
    public RepositoryBlog<Blog> provideBlogRepository(ServiceApi serviceApi) {
        return new BlogRepositoryImpl(serviceApi);
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
