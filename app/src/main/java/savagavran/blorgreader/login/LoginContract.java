package savagavran.blorgreader.login;

public interface LoginContract {

    interface LoginUserActions {
        void onLoginClicked(String email, String password);
    }

    interface LoginScreen {
        void openBlogsScreen();
        void showNoInternetConnection();
        void showLoginFailedError(String message);
    }
}
