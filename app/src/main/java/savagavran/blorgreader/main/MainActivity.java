package savagavran.blorgreader.main;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import savagavran.blorgreader.R;
import savagavran.blorgreader.SingleFragmentActivity;
import savagavran.blorgreader.main.blogList.view.BlogsFragment;

public class MainActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return BlogsFragment.newInstance();
    }
}
