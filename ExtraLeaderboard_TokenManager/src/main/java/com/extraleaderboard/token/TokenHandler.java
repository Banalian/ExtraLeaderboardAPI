package com.extraleaderboard.token;

import com.extraleaderboard.model.TokenStorage;
import com.extraleaderboard.model.nadeo.Audience;
import com.extraleaderboard.model.nadeo.NadeoToken;
import com.extraleaderboard.model.ubisoft.UbisoftTicket;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;

/**
 * Class in charge of creating tokens and handling them/renewing them
 */
public class TokenHandler implements ITokenHandler {

    /**
     * Authorization header name used to get the token
     */
    public static final String AUTHORIZATION = "Authorization";
    /**
     * Authorization header value used to get the token from the ubisoft API
     */
    public static final String UBI_AUTH_NAME = "ubi_v1";
    /**
     * Authorization header value used to get the token from the nadeo API
     */
    public static final String NADEO_AUTH_NAME = "nadeo_v1";

    public static final String TOKEN_FOLDER = "/tokens/";

    private static final Logger LOGGER = LoggerFactory.getLogger(TokenHandler.class);
    /**
     * Email of the ubisoft account used to get the token
     */
    private static final String UBISOFT_MAIL = System.getProperty("ubisoft.email");
    /**
     * Password of the ubisoft account used to get the token
     */
    private static final String UBISOFT_PASSWORD = System.getProperty("ubisoft.password");
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
            NadeoToken token = createNewToken(audience);
            TokenStorage.setToken(audience, token);

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

            // write the token to a file
            try {
                Path path = Paths.get(TOKEN_FOLDER + audience.getAudienceName().toLowerCase()+ ".token");
                String content = token.getAccessToken() + "\n" + token.getRefreshToken();
                Files.writeString(path, content, Files.exists(path) ? StandardOpenOption.TRUNCATE_EXISTING : StandardOpenOption.CREATE);
            } catch (IOException e) {
                LOGGER.error("Error while writing token to file", e);
            }
        }

        return TokenStorage.getToken(audience);
    }

    /**
     * Create a new {@link NadeoToken} for the given audience
     *
     * @param audience the {@link Audience} for which we want to create a new token
     * @return the new {@link NadeoToken}
     */
    private NadeoToken createNewToken(final Audience audience) {
        // First step : get Ubisoft ticket
        String ubisoftTicket = getUbisoftTicket().getTicket();

        // Second step : get Nadeo token
        return getNadeoToken(ubisoftTicket, audience);
    }


    /**
     * Get a {@link UbisoftTicket} from the ubisoft API
     *
     * @return the {@link UbisoftTicket}
     */
    private UbisoftTicket getUbisoftTicket() {
        WebTarget target = client
                .target("https://public-ubiservices.ubi.com")
                .path("v3/profiles/sessions");

        return target.request(MediaType.APPLICATION_JSON)
                .header("Content-Type", "application/json")
                .header("Ubi-AppId", "86263886-327a-4328-ac69-527f0d20a237")
                .header("User-Agent", USER_AGENT)
                .header(AUTHORIZATION, "Basic " + getBase64EncodedCredentials())
                .post(Entity.json(""), UbisoftTicket.class);
    }

    /**
     * Get a {@link NadeoToken} from the nadeo API
     *
     * @param ubisoftTicket the ubisoft ticket used to get the nadeo token
     * @param audience      the {@link Audience} for which we want to get the token
     * @return the {@link NadeoToken}
     */
    private NadeoToken getNadeoToken(final String ubisoftTicket, final Audience audience) {
        WebTarget target = client
                .target("https://prod.trackmania.core.nadeo.online/")
                .path("v2/authentication/token/ubiservices");

        // create json body containing the audience
        JsonNode audienceJson = new ObjectMapper().createObjectNode().put("audience", audience.getAudienceName());

        return target.request()
                .header("Content-Type", "application/json")
                .header("User-Agent", USER_AGENT)
                .header(AUTHORIZATION, UBI_AUTH_NAME + " t=" + ubisoftTicket)
                .post(Entity.json(audienceJson), NadeoToken.class);
    }

    /**
     * Get the base64 encoded credentials for the ubisoft API
     *
     * @return the base64 encoded credentials
     */
    private String getBase64EncodedCredentials() {
        return Base64.getEncoder().encodeToString((UBISOFT_MAIL + ":" + UBISOFT_PASSWORD).getBytes());
    }

    public Map<Audience, Timer> getTimers() {
        return timers;
    }
}
