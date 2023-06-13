package com.extraleaderboard.logic.token;

import com.extraleaderboard.model.nadeo.Audience;
import com.extraleaderboard.model.nadeo.NadeoToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileTokenHandler implements ITokenHandler{

    private static final Logger LOGGER = LoggerFactory.getLogger(FileTokenHandler.class);
    private static final String TOKEN_FOLDER = "/tokens/";

    /**
     * Get the {@link NadeoToken} for the given audience, by reading from a file
     *
     * @param audience the audience for which the token is needed
     * @return the token, either a new one or an existing one
     */
    @Override
    public NadeoToken getNadeoToken(Audience audience) {
        // open the file and read the token
        try {
            List<String> lines = Files.readAllLines(Paths.get(TOKEN_FOLDER + audience.getAudienceName().toLowerCase() + ".token"), StandardCharsets.UTF_8);

            if(lines.size() != 2) {
                throw new IOException("The token file should only have two lines");
            }
            // should only be two line, create the token
            NadeoToken token = new NadeoToken();
            token.setAccessToken(lines.get(0));
            token.setRefreshToken(lines.get(1));

            return token;
        } catch (IOException e) {
            LOGGER.error("Error while reading the token file", e);
        }

        return null;
    }
}
