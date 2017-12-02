package savagavran.blorgreader.main.blogList;

import android.content.Context;

public interface BlogsContract {

    interface BlogsScreen {

        void onAuthenticationConfirmed();

        void onAuthenticationRequired();

        void showBlogs();

        void openBlogDetails(String htmlContent);

        void showLoadingError(String message);
    }

    interface BlogsUserActions {

        void loadBlogs();

        void onScreenLaunched(Context context);

        void onBlogsDetailsClicked(int position);
    }
}
