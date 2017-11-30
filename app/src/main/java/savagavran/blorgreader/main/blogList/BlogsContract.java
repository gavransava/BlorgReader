package savagavran.blorgreader.main.blogList;

import android.content.Context;

public interface BlogsContract {

    interface BlogsScreen {

        void onAuthenticationRequired();

        void showBlogs();

        void openBlogDetails();
    }

    interface BlogsUserActions {

        void loadBlogs();

        void onScreenLaunched(Context context);

        void onBlogsDetailsClicked(int position);
    }
}
