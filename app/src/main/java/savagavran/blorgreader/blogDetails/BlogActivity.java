package savagavran.blorgreader.blogDetails;

import android.support.v4.app.Fragment;

import savagavran.blorgreader.SingleFragmentActivity;
import savagavran.blorgreader.blogDetails.view.BlogFragment;

public class BlogActivity extends SingleFragmentActivity{
    @Override
    protected Fragment createFragment() {
        return BlogFragment.newInstance();
    }
}
