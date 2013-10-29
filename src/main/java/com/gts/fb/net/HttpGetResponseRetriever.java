package com.gts.fb.net;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * <p><b> Copyright 2013 (c) Waltz-Soft </b></p>
 *
 * @author zapolski
 */
public class HttpGetResponseRetriever {
    private static Logger LOGGER = Logger.getLogger(HttpGetResponseRetriever.class.getName());
    private String url;

    public HttpGetResponseRetriever(String url) {
        this.url = url;
    }

    public HttpGetResponse retrieve() {
        HttpGetResponse httpGetResponse = new HttpGetResponse();

        HttpGet httpGet = new HttpGet(url);
        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpResponse httpResponse = httpClient.execute(httpGet);
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            HttpEntity entity1 = httpResponse.getEntity();
            boolean responseStatusIsOk = statusCode > 199 && statusCode < 300;

            httpGetResponse.setStatusCode(statusCode);
            if (responseStatusIsOk) {
                httpGetResponse.setBody(EntityUtils.toString(entity1));
            } else {
                httpGetResponse.setError(new UnknownError(EntityUtils.toString(entity1)));
            }

            EntityUtils.consume(entity1);
        } catch (ClientProtocolException e) {
            httpGetResponse.setError(e);
            httpGetResponse.setStatusCode(500);
            LOGGER.log(Level.WARNING, e.getMessage(), e);
        } catch (IOException e) {
            httpGetResponse.setError(e);
            httpGetResponse.setStatusCode(500);
            LOGGER.log(Level.WARNING, e.getMessage(), e);
        } catch (Throwable e) {
            httpGetResponse.setError(e);
            httpGetResponse.setStatusCode(500);
            LOGGER.log(Level.WARNING, e.getMessage(), e);
        } finally {
            httpGet.releaseConnection();
        }
        return httpGetResponse;
    }
}
