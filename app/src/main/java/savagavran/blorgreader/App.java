package savagavran.blorgreader;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import savagavran.blorgreader.di.AppComponent;
import savagavran.blorgreader.di.AppModule;
import savagavran.blorgreader.di.DaggerAppComponent;
import savagavran.blorgreader.main.blogList.BlogsContract;
import savagavran.blorgreader.main.blogList.di.BlogsModule;

public class App extends Application {

    protected AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(App.class.getSimpleName(), "Application onCreate called!");
        setUpGraph();

        mAppComponent.inject(this);
    }

    /**
     * The object graph contains all the instances of the objects
     * that resolve a dependency
     */
    protected void setUpGraph() {
        mAppComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getComponent() {
        return mAppComponent;
    }

    public static App getAppContext(Context context) {
        return (App) context.getApplicationContext();
    }

    public BlogsModule getBlogsModule(BlogsContract.BlogsScreen screen) {
        return new BlogsModule(screen);
    }

//    public LoginModule getLoginModule(LoginContract.LoginScreen loginScreen) {
//        return new LoginModule(loginScreen);
//    }
}
