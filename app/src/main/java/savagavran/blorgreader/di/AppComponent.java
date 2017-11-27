package savagavran.blorgreader.di;

import android.content.Context;

import dagger.Component;
import savagavran.blorgreader.App;
import savagavran.blorgreader.shared.DataModule;

@AppScope
@Component(modules = {AppModule.class, DataModule.class})
public interface AppComponent {

    Context getContext();

    void inject(App app);

}
