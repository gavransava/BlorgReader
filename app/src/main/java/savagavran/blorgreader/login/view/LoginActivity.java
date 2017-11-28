package savagavran.blorgreader.login.view;

import android.support.v4.app.Fragment;
import savagavran.blorgreader.SingleFragmentActivity;

public class LoginActivity extends SingleFragmentActivity
{
    @Override
    protected Fragment createFragment() {
        return LoginFragment.newInstance();
    }
}
