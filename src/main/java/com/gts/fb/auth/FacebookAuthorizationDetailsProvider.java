package com.gts.fb.auth;

/**
 * <p><b> Copyright 2013 (c) Waltz-Soft </b></p>
 *
 * @author zapolski
 */
public interface FacebookAuthorizationDetailsProvider {
    public String clientId();
    public String clientSecret();
    public String userAuthCode();
    public String redirectUrl();
    public String existingToken();
    public void setUpNewToken(String token);
}
