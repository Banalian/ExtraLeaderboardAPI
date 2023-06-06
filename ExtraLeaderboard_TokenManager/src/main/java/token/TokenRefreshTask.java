package token;

import com.extraleaderboard.model.TokenStorage;
import com.extraleaderboard.model.nadeo.Audience;
import com.extraleaderboard.model.nadeo.NadeoToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import java.util.TimerTask;

import static com.extraleaderboard.logic.token.TokenHandler.NADEO_AUTH_NAME;

public class TokenRefreshTask extends TimerTask {

    private static final Logger LOGGER = LoggerFactory.getLogger(TokenRefreshTask.class);

    /**
     * Client used to call the nadeo API
     */
    private final Client client = ClientBuilder.newClient();

    /**
     * Audience for which we want to refresh the token
     */
    private final Audience audience;

    public TokenRefreshTask(Audience audience) {
        this.audience = audience;
    }

    /**
     * Method called when the timer is triggered.
     * Refreshes the token associated to the audience
     */
    @Override
    public void run() {
        LOGGER.info("Refreshing token for audience {}", audience);
        NadeoToken token = refreshNadeoToken();
        TokenStorage.setToken(audience, token);
        LOGGER.info("Token refreshed for audience {}", audience);
    }

    /**
     * Refresh the {@link NadeoToken} for the given audience
     *
     * @return the refreshed {@link NadeoToken}
     */
    private NadeoToken refreshNadeoToken() {
        // call the refresh endpoint with the refresh token at Nadeo
        WebTarget target = client
                .target("https://prod.trackmania.core.nadeo.online/")
                .path("v2/authentication/token/refresh");

        return target
                .request()
                .header("Authorization", NADEO_AUTH_NAME + " t=" + TokenStorage.getToken(audience).getRefreshToken())
                .post(Entity.json(""), NadeoToken.class);
    }
}
