package savagavran.blorgreader.di;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;

import dagger.Module;
import dagger.Provides;
import savagavran.blorgreader.App;

@AppScope
@Module
public class AppModule {

    private App mApp;

    public AppModule(App app) {
        this.mApp = app;
    }

    @Provides
    public Context provideContext() {
        return mApp;
    }

    @Provides
    public LayoutInflater provideLayoutInflater(Context context) {
        return LayoutInflater.from(context);
    }

    @Provides
    public SharedPreferences provideSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

}
