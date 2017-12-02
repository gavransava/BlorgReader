package savagavran.blorgreader.blogDetails.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import savagavran.blorgreader.R;

public class BlogFragment extends Fragment {

    public static final String HTML_CONTENT = "html_content";

    public BlogFragment() {
        // Required empty public constructor
    }

    public static BlogFragment newInstance() {
        BlogFragment fragment = new BlogFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blog, container, false);
        WebView webView = view.findViewById(R.id.blog_content);
        Toolbar actionBarToolbar = view.findViewById(R.id.toolbar);

        setupWebView(webView);
        setupToolbar(actionBarToolbar);

        Bundle bundle = getActivity().getIntent().getExtras();
        String htmlContent = "";
        if (bundle != null) {
            htmlContent = bundle.getString(HTML_CONTENT, "");
        }
        webView.loadData(htmlContent , "text/html", null);

        return view;
    }

    private void setupWebView(WebView webView) {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setSupportZoom(false);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR);
    }

    private void setupToolbar(Toolbar mActionBarToolbar) {
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(mActionBarToolbar);
        ActionBar actionBar = activity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(getString(R.string.blog_display));
        }
    }
}
