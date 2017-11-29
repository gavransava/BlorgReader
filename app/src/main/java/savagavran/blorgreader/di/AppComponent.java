package savagavran.blorgreader.di;

import android.content.Context;

import dagger.Component;
import savagavran.blorgreader.App;
import savagavran.blorgreader.shared.DataModule;
import savagavran.blorgreader.shared.auth.AuthManager;
import savagavran.blorgreader.shared.auth.ConnectivityInterceptor;

@AppScope
@Component(modules = {AppModule.class, DataModule.class})
public interface AppComponent {

    Context getContext();

    AuthManager getAuthManager();

    ConnectivityInterceptor getConnectivityInterceptor();

    void inject(App app);

}
