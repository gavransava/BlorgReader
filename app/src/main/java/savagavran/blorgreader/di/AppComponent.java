package savagavran.blorgreader.di;

import android.content.Context;

import dagger.Component;
import savagavran.blorgreader.App;
import savagavran.blorgreader.shared.DataModule;
import savagavran.blorgreader.shared.RepositoryBlog;
import savagavran.blorgreader.shared.RepositoryBlogList;
import savagavran.blorgreader.shared.ServiceApi;
import savagavran.blorgreader.shared.auth.AuthManager;
import savagavran.blorgreader.shared.auth.ConnectivityInterceptor;
import savagavran.blorgreader.utils.Blog;
import savagavran.blorgreader.utils.BlogItem;

@AppScope
@Component(modules = {AppModule.class, DataModule.class})
public interface AppComponent {

    Context getContext();

    AuthManager getAuthManager();

    ServiceApi getServiceApi();

    ConnectivityInterceptor getConnectivityInterceptor();

    RepositoryBlogList<BlogItem> provideBlogsRepository();

    RepositoryBlog<Blog> provideBlogRepository();

    void inject(App app);

}
