package savagavran.blorgreader.login.di;

import dagger.Component;
import savagavran.blorgreader.di.AppComponent;
import savagavran.blorgreader.login.view.LoginFragment;

@LoginScope
@Component(dependencies = {AppComponent.class}, modules = LoginModule.class)
public interface LoginComponent {

    void inject(LoginFragment fragment);
}
