package savagavran.blorgreader.main.blogList;

import android.content.Context;

public interface BlogsContract {

    interface BlogsScreen {

        void onAuthenticationConfirmed();

        void onAuthenticationRequired();

        void showBlogs();

        void openBlogDetails();
    }

    interface BlogsUserActions {

        void loadBlogs();

        void onBlogsDetailsClicked(int position);

        void onResume(Context context);

        void onPause(Context context);

        void onDestroy();
    }
}
