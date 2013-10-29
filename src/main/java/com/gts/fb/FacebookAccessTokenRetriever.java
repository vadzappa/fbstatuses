package com.gts.fb;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Properties;

import com.gts.fb.net.HttpGetResponse;
import com.gts.fb.net.HttpGetResponseRetriever;
import com.gts.fb.util.PropertiesReader;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;

/**
 * <p><b> Copyright 2013 (c) Waltz-Soft </b></p>
 *
 * @author zapolski
 */
public class FacebookAccessTokenRetriever {
    private static final Properties properties = PropertiesReader.readFromResource(FacebookStatusesRetriever.class, "at.properties");

    private String retrieveTemporaryAccessToken() {
        String url = properties.getProperty("facebook.tokenGenerateUrl");
        url = populateUrlWithBasicDetails(url);

        String redirectUrl = properties.getProperty("facebook.redirectUrl");
        String userAuthCode = properties.getProperty("facebook.userAuthCode");

        url = url.replace("{redirectUrl}", redirectUrl);
        url = url.replace("{authCode}", userAuthCode);

        HttpGetResponseRetriever httpGetResponseRetriever = new HttpGetResponseRetriever(url);
        HttpGetResponse response = httpGetResponseRetriever.retrieve();
        if (response.getError() != null) {
            throw new RuntimeException(response.getError().getMessage(), response.getError());
        }
        List<NameValuePair> queryStringDetails = URLEncodedUtils.parse(response.getBody(), Charset.defaultCharset());
        for (NameValuePair nameValuePair : queryStringDetails) {
            if (nameValuePair.getName().equalsIgnoreCase("access_token")) {
                return nameValuePair.getValue();
            }
        }
        throw new RuntimeException("Unable to get access token");
    }

    public String retrieveLongLiveAccessToken() {
        String url = properties.getProperty("facebook.longLivetokenGenerateUrl");
        url = populateUrlWithBasicDetails(url);
        url = url.replace("${tempAccessToken}", retrieveTemporaryAccessToken());

        HttpGetResponseRetriever httpGetResponseRetriever = new HttpGetResponseRetriever(url);
        HttpGetResponse response = httpGetResponseRetriever.retrieve();
        if (response.getError() != null) {
            throw new RuntimeException(response.getError().getMessage(), response.getError());
        }

        List<NameValuePair> queryStringDetails = URLEncodedUtils.parse(response.getBody(), Charset.defaultCharset());
        for (NameValuePair nameValuePair : queryStringDetails) {
            if (nameValuePair.getName().equalsIgnoreCase("access_token")) {
                return nameValuePair.getValue();
            }
        }
        throw new RuntimeException("Unable to get access token");
    }

    private String populateUrlWithBasicDetails(String url) {
        String clientId = properties.getProperty("facebook.clientId");
        String clientSecret = properties.getProperty("facebook.clientSecret");
        url = url.replace("{clientId}", clientId);
        url = url.replace("{clientSecret}", clientSecret);
        return url;
    }
}
