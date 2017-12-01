package savagavran.blorgreader.main.blogList.di;

import dagger.Module;
import dagger.Provides;
import savagavran.blorgreader.main.blogList.BlogsContract;
import savagavran.blorgreader.main.blogList.presenter.BlogsPresenter;
import savagavran.blorgreader.shared.ServiceApi;
import savagavran.blorgreader.shared.auth.AuthManager;

@BlogsScope
@Module
public class BlogsModule {

    private BlogsContract.BlogsScreen mBlogsScreen;

    public BlogsModule(BlogsContract.BlogsScreen screen) {
        mBlogsScreen = screen;
    }

    @Provides
    public BlogsContract.BlogsScreen provideBlogsScreen() {
        return mBlogsScreen;
    }
    @Provides
    public BlogsContract.BlogsUserActions provideBlogsUserActions(AuthManager mAuthManager,
                                                                  ServiceApi mServiceApi) {
        return new BlogsPresenter(mBlogsScreen, mAuthManager, mServiceApi);
    }

}
