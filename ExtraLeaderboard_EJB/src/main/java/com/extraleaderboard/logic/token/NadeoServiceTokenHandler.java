package com.extraleaderboard.logic.token;

import com.extraleaderboard.model.TokenStorage;
import com.extraleaderboard.model.nadeo.Audience;
import com.extraleaderboard.model.nadeo.NadeoToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;

public class NadeoServiceTokenHandler {
    /**
     * Authorization header name used to get the token
     */
    public static final String AUTHORIZATION = "Authorization";
    /**
     * Authorization header value used to get the token from the ubisoft API
     */
    public static final String AUTH_NAME = "Basic";
    private static final Logger LOGGER = LoggerFactory.getLogger(NadeoServiceTokenHandler.class);
    /**
     * Email of the Nadeo service account used to get the token
     */
    private static final String SERVICE_ACCOUNT_LOGIN = System.getProperty("serviceaccount.login");
    /**
     * Password of the Nadeo service account used to get the token
     */
    private static final String SERVICE_ACCOUNT_PASSWORD = System.getProperty("serviceaccount.password");
    /**
     * User agent used
     */
    private static final String USER_AGENT = "ExtraLeaderboard API : extraleaderboard@gmail.com";
    /**
     * Client used to call the different APIs
     */
    private final Client client = ClientBuilder.newClient();

    private final Map<Audience, Timer> timers = new HashMap<>();

    /**
     * Get the {@link NadeoToken} for the given audience
     *
     * @param audience the {@link Audience} for which we want the token
     * @return the {@link NadeoToken} for the given audience
     */
    public NadeoToken getNadeoToken(Audience audience) {
        if (!TokenStorage.hasToken(audience)) {
            LOGGER.info("No token found for audience {}, creating a new one", audience);
            TokenStorage.setToken(audience, getNadeoServiceAccountToken(audience));

            LOGGER.info("Token created for audience {}, scheduling the refresh task", audience);
            Timer timer = new Timer();
            // 55 min
            long delay = (long) 55 * (long) 60 * 1000;

            timer.schedule(new TokenRefreshTask(audience), delay, delay);
            Timer oldTimer = timers.put(audience, timer);
            // Cancel the old timer if it exists
            // Shouldn't happen, but we'd rather be safe than sorry, we don't want to have multiple timers running
            if (oldTimer != null) {
                oldTimer.cancel();
                oldTimer.purge();
                LOGGER.info("Old timer cancelled for audience {}", audience);
            }
            LOGGER.info("Refresh task scheduled for audience {}", audience);
        }

        return TokenStorage.getToken(audience);
    }

    /**
     * Get a {@link NadeoToken} from the nadeo API using a service account linked to the UBI Account
     *
     * @param audience      the {@link Audience} for which we want to get the token
     * @return the {@link NadeoToken}
     */
    private NadeoToken getNadeoServiceAccountToken(final Audience audience) {
        WebTarget target = client
                .target("https://prod.trackmania.core.nadeo.online/")
                .path("v2/authentication/token/basic");

        // create json body containing the audience
        JsonNode audienceJson = new ObjectMapper().createObjectNode().put("audience", audience.getAudienceName());

        return target.request()
                .header("Content-Type", MediaType.APPLICATION_JSON)
                .header("User-Agent", USER_AGENT)
                .header(AUTHORIZATION, AUTH_NAME + " " + getBase64EncodedCredentials())
                .post(Entity.json(audienceJson), NadeoToken.class);
    }

    /**
     * Get the base64 encoded credentials for the Ubisoft API
     *
     * @return the base64 encoded credentials
     */
    private String getBase64EncodedCredentials() {
        return Base64.getEncoder().encodeToString((SERVICE_ACCOUNT_LOGIN + ":" + SERVICE_ACCOUNT_PASSWORD).getBytes());
    }

    public Map<Audience, Timer> getTimers() {
        return timers;
    }
}
