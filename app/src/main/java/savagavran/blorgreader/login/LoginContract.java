package savagavran.blorgreader.login;

public interface LoginContract {

    interface LoginUserActions {
        void onLoginClicked(String email, String password);
    }

    interface LoginScreen {
        void openBlogsScreen();
        void showLoginFailedError(String message);
    }
}
