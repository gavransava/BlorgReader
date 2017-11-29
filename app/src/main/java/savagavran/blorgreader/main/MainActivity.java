package savagavran.blorgreader.main;

import android.support.v4.app.Fragment;
import savagavran.blorgreader.SingleFragmentActivity;
import savagavran.blorgreader.login.view.LoginFragment;

public class MainActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return LoginFragment.newInstance();
    }
}
