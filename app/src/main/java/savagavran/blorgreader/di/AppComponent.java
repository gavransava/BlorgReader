package savagavran.blorgreader.di;

import android.content.Context;

import dagger.Component;
import savagavran.blorgreader.App;
import savagavran.blorgreader.shared.DataModule;
import savagavran.blorgreader.shared.Repository;
import savagavran.blorgreader.shared.ServiceApi;
import savagavran.blorgreader.shared.auth.AuthManager;
import savagavran.blorgreader.shared.auth.ConnectivityInterceptor;

@AppScope
@Component(modules = {AppModule.class, DataModule.class})
public interface AppComponent {

    Context getContext();

    AuthManager getAuthManager();

    ServiceApi getServiceApi();

    ConnectivityInterceptor getConnectivityInterceptor();

    Repository provideBlogsRepository();

    void inject(App app);

}
