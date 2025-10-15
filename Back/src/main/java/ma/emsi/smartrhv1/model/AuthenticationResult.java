package ma.emsi.smartrhv1.model;
public class AuthenticationResult {
    private final boolean authenticated;
    private final String token;

    public AuthenticationResult(boolean authenticated, String token) {
        this.authenticated = authenticated;
        this.token = token;
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public String getToken() {
        return token;
    }
}



