package savagavran.blorgreader.shared.auth;

import io.reactivex.Observable;
import savagavran.blorgreader.shared.retrofit.Credentials;
import savagavran.blorgreader.utils.Token;

public interface AuthManager {

    Observable<Token> login(Credentials credentials);

    Observable<Boolean> authentication();
}
