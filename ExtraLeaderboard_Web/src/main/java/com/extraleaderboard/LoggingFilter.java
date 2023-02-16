package com.extraleaderboard;

import com.extraleaderboard.model.UserResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Provider
public class LoggingFilter implements ContainerRequestFilter, ContainerResponseFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingFilter.class);

    @Override
    public void filter(ContainerRequestContext reqContext) {
        long requestStartTime = System.nanoTime();
        reqContext.setProperty("requestStartTime", requestStartTime);

        // Create random request id
        UUID requestId = UUID.randomUUID();
        reqContext.setProperty("requestId", requestId);

        // Log that a request has been received to X endpoint
        LOGGER.info("Started request {} to {}. Parameters : {}", requestId, reqContext.getUriInfo().getPath(), reqContext.getUriInfo().getQueryParameters());

    }

    @Override
    public void filter(ContainerRequestContext reqContext,
                       ContainerResponseContext resContext) {
        long requestStartTime = (long) reqContext.getProperty("requestStartTime");
        long requestFinishTime = System.nanoTime();
        long duration = requestFinishTime - requestStartTime;

        UUID requestId = (UUID) reqContext.getProperty("requestId");

        // Get the response and add the uuid to it
        Object entity = resContext.getEntity();
        if (entity instanceof UserResponse userResponse) {
            userResponse.addMeta("requestId", requestId.toString());
        } else {
            // Write an error message in the log telling what happened
            LOGGER.error("The response entity is not an instance of UserResponse, it is an instance of {} (request : {})", entity.getClass().getName(), requestId);
        }


        // Print the elapsed time for the request
        LOGGER.info("Finished request {} to {} in {} ms", requestId, reqContext.getUriInfo().getPath(), TimeUnit.NANOSECONDS.toMillis(duration));
    }

}
