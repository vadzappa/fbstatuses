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

    public String clientId() {
        return properties.getProperty("facebook.clientId");
    }

    public String clientSecret() {
        return properties.getProperty("facebook.clientSecret");
    }

    public String userAuthCode() {
        return properties.getProperty("facebook.userAuthCode");
    }

    public String redirectUrl() {
        return properties.getProperty("facebook.redirectUrl");
    }

    public String existingToken() {
        return token;
    }

    public void setUpNewToken(String token) {
        this.token = token;
    }
}
