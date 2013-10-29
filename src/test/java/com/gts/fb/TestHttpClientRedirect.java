package com.gts.fb;

import java.util.Properties;

import com.gts.fb.net.HttpGetResponse;
import com.gts.fb.net.HttpGetResponseRetriever;
import com.gts.fb.util.PropertiesReader;
import org.junit.Test;

/**
 * <p><b> Copyright 2013 (c) Waltz-Soft </b></p>
 *
 * @author zapolski
 */
public class TestHttpClientRedirect {
    private static final Properties properties = PropertiesReader.readFromResource(FacebookStatusesRetriever.class, "at.properties");

    @Test
    public void testHttpClientRedirect() {
        String url = properties.getProperty("facebook.tokenGenerateUrl");
        String clientId = properties.getProperty("facebook.clientId");
        String clientSecret = properties.getProperty("facebook.clientSecret");
        String redirectUrl = properties.getProperty("facebook.redirectUrl");
        String userAuthCode = properties.getProperty("facebook.userAuthCode");

        url = url.replace("{clientId}", clientId);
        url = url.replace("{redirectUrl}", redirectUrl);
        url = url.replace("{clientSecret}", clientSecret);
        url = url.replace("{authCode}", userAuthCode);

        System.out.println(url);
        HttpGetResponseRetriever httpGetResponseRetriever = new HttpGetResponseRetriever(url);
        HttpGetResponse response = httpGetResponseRetriever.retrieve();
        System.out.println(response);

    }
}
