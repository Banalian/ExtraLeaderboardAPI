package com.extraleaderboard.logic.token;

import com.extraleaderboard.logic.exception.NadeoRuntimeException;
import com.extraleaderboard.model.nadeo.Audience;
import com.extraleaderboard.model.nadeo.NadeoToken;
import com.extraleaderboard.model.nadeo.NadeoTokenPayload;
import com.extraleaderboard.model.ubisoft.UbisoftTicket;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import java.util.Base64;
import java.util.EnumMap;

/**
 * Class in charge of creating tokens and handling them/renewing them
 */
public class TokenHandler {

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

    /**
     * All the tokens handled by the handler
     */
    private final EnumMap<Audience, NadeoToken> nadeoTokens = new EnumMap<>(Audience.class);

    /**
     * Get the {@link NadeoToken} for the given audience
     *
     * @param audience the {@link Audience} for which we want the token
     * @return the {@link NadeoToken} for the given audience
     */
    public NadeoToken getNadeoToken(Audience audience) {
        if (!nadeoTokens.containsKey(audience)) {
            LOGGER.info("No token found for audience {}, creating a new one", audience);
            nadeoTokens.put(audience, createNewToken(audience));
        }

        // get the current token
        NadeoToken currentToken = nadeoTokens.get(audience);

        //TODO : Refactor to use a java timer and timer task

        // In 55 minutes, the token will expire, so we renew it
        // Check if the token is still valid
        // decode the payload into a NadeoTokenPayload object
        NadeoTokenPayload nadeoTokenPayload = decodePayload(currentToken);

        // if the token is valid but will expire in less than 5 minutes, we renew it
        if (nadeoTokenPayload.getExp() - System.currentTimeMillis() / 1000 < 3300) {
            LOGGER.info("Token for audience {} will expire in less than 5 minutes, renewing it", audience);
            nadeoTokens.put(audience, refreshNadeoToken(audience));
        } else if (nadeoTokenPayload.getExp() < System.currentTimeMillis() / 1000) {
            // if the token is expired, get a new one
            LOGGER.info("Token for audience {} is expired, creating a new one", audience);
            nadeoTokens.put(audience, createNewToken(audience));
        }

        return nadeoTokens.get(audience);
    }

    /**
     * Refresh the {@link NadeoToken} for the given audience
     *
     * @param audience the {@link Audience} for which we want to refresh the token
     * @return the refreshed {@link NadeoToken}
     */
    private NadeoToken refreshNadeoToken(final Audience audience) {
        // call the refresh endpoint with the refresh token at Nadeo
        Client client = ClientBuilder.newClient();
        WebTarget target = client
                .target("https://prod.trackmania.core.nadeo.online/")
                .path("v2/authentication/token/refresh");

        return target
                .request()
                .header(AUTHORIZATION, NADEO_AUTH_NAME + " t=" + nadeoTokens.get(audience).getRefreshToken())
                .post(Entity.json(""), NadeoToken.class);

    }

    /**
     * Decode the payload of the given {@link NadeoToken}, to access its content
     *
     * @param payload the {@link NadeoToken} to decode
     * @return the decoded {@link NadeoTokenPayload}
     */
    private NadeoTokenPayload decodePayload(NadeoToken payload) {
        // get the payload of the token (the part after the first dot)
        String payloadString = payload.getAccessToken().split("\\.")[1];
        // decode the payload
        byte[] decodedBytes = Base64.getDecoder().decode(payloadString);
        String decodedPayload = new String(decodedBytes);
        // parse the payload into a NadeoTokenPayload object
        try {
            return new ObjectMapper().readValue(decodedPayload, NadeoTokenPayload.class);
        } catch (Exception e) {
            throw new NadeoRuntimeException("Error while parsing the payload of the token", e);
        }
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
        Client client = ClientBuilder.newClient();
        WebTarget target = client
                .target("https://public-ubiservices.ubi.com")
                .path("v3/profiles/sessions");

        return target.request(MediaType.APPLICATION_JSON)
                .header("Content-Type", "application/json")
                .header("Ubi-AppId", "86263886-327a-4328-ac69-527f0d20a237")
                .header("User-Agent", "Banalian API Test : Banalian#0584 on Discord")
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
        Client client = ClientBuilder.newClient();
        WebTarget target = client
                .target("https://prod.trackmania.core.nadeo.online/")
                .path("v2/authentication/token/ubiservices");

        // create json body containing the audience
        JsonNode audienceJson = new ObjectMapper().createObjectNode().put("audience", audience.getAudience());

        return target.request()
                .header("Content-Type", "application/json")
                .header("User-Agent", "Banalian API Test : Banalian#0584 on Discord")
                .header("AUTHORIZATION", UBI_AUTH_NAME + " t=" + ubisoftTicket)
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

}
