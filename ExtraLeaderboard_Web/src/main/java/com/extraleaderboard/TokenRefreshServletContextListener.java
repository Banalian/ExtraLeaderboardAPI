package com.extraleaderboard;

import com.extraleaderboard.logic.token.TokenFactory;
import com.extraleaderboard.model.nadeo.Audience;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.Map;
import java.util.Timer;

@WebListener
public class TokenRefreshServletContextListener implements ServletContextListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(TokenRefreshServletContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Cancel the timer when the application is undeployed
        Map<Audience, Timer> timers = TokenFactory.getTimers();
        timers.values().forEach(Timer::cancel);
        timers.values().forEach(Timer::purge);
        LOGGER.info("Token(s) refresh timer(s) cancelled");
    }
}