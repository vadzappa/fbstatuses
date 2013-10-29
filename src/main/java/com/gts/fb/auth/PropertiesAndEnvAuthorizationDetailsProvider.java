package com.gts.fb.auth;

import java.util.Properties;

import com.gts.fb.util.PropertiesReader;

/**
 * <p><b> Copyright 2013 (c) Waltz-Soft </b></p>
 *
 * @author zapolski
 */
public class PropertiesAndEnvAuthorizationDetailsProvider implements FacebookAuthorizationDetailsProvider {
    private static final Properties properties = PropertiesReader.readFromResource(PropertiesAndEnvAuthorizationDetailsProvider.class, "fb_oauth.properties");
    private static String token = null;

    @Override
    public String clientId() {
        return properties.getProperty("facebook.clientId");
    }

    @Override
    public String clientSecret() {
        return properties.getProperty("facebook.clientSecret");
    }

    @Override
    public String userAuthCode() {
        return properties.getProperty("facebook.userAuthCode");
    }

    @Override
    public String redirectUrl() {
        return properties.getProperty("facebook.redirectUrl");
    }

    @Override
    public String existingToken() {
        return token;
    }

    @Override
    public void setUpNewToken(String token) {
        this.token = token;
    }
}
