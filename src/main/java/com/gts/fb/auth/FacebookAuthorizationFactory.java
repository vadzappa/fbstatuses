package com.gts.fb.auth;

/**
 * <p><b> Copyright 2013 (c) Waltz-Soft </b></p>
 *
 * @author zapolski
 */
public final class FacebookAuthorizationFactory {
    private static final FacebookAuthorizationFactory instance = new FacebookAuthorizationFactory();

    private static FacebookAuthorizationDetailsProvider provider;

    private FacebookAuthorizationFactory() {
    }

    public static FacebookAuthorizationDetailsProvider provide() {
        if (provider == null) {
            provider = new PropertiesAndEnvAuthorizationDetailsProvider();
        }
        return instance.provider;
    }

    public static void setProvider(FacebookAuthorizationDetailsProvider provider) {
        instance.provider = provider;
    }
}
