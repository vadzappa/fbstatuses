package com.gts.fb;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.gts.fb.dto.FacebookResponse;
import com.gts.fb.util.PropertiesReader;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * <p><b> 2013 </b></p>
 *
 * @author Vadim
 */
public class FacebookStatusesRetriever {
    private static final Properties properties = PropertiesReader.readFromResource(FacebookStatusesRetriever.class, "at.properties");
    private static Logger LOGGER = Logger.getLogger(FacebookStatusesRetriever.class.getName());


    private String query;
    private FacebookSearchType searchType = FacebookSearchType.POST;
    private Set<SearchParameter> searchParameters = new HashSet<SearchParameter>(0);

    public FacebookStatusesRetriever(String query, FacebookSearchType searchType, SearchParameter... searchParameters) {
        this.query = query;
        this.searchType = searchType;
        this.searchParameters.addAll(Arrays.asList(searchParameters));
    }

    public JSONObject findFacebookEntities() {
        JSONObject result = new JSONObject();
        String facebookSearchUrl = prepareFacebookUrl();
        FacebookResponse facebookResponse = retrieveFacebookResponse(facebookSearchUrl);
        try {
            if (facebookResponse.getError() != null) {
                result.putOnce("error", facebookResponse.getError());
            }
            if (facebookResponse.getBody() != null) {
                JSONObject facebookData = new JSONObject(facebookResponse.getBody());
                result.putOnce("results", facebookData.getJSONArray("data"));
            }
            result.putOnce("status", facebookResponse.getStatusCode());
        } catch (JSONException e) {
            LOGGER.log(Level.WARNING, e.getMessage(), e);
        }
        return result;
    }

    private String prepareFacebookUrl() {
        FacebookSearchUrlBuilder facebookSearchUrlBuilder = new FacebookSearchUrlBuilder();
        facebookSearchUrlBuilder.query(query).searchType(searchType).accessToken(properties.getProperty("facebook.access_token"));
        for (SearchParameter searchParameter : searchParameters) {
            facebookSearchUrlBuilder.searchParameter(searchParameter);
        }
        return facebookSearchUrlBuilder.build();
    }

    private FacebookResponse retrieveFacebookResponse(String facebookSearchUrl) {
        FacebookResponse facebookResponse = new FacebookResponse();

        HttpGet httpGet = new HttpGet(facebookSearchUrl);
        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpResponse httpResponse = httpClient.execute(httpGet);
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            HttpEntity entity1 = httpResponse.getEntity();
            boolean responseStatusIsOk = statusCode > 199 && statusCode < 300;

            facebookResponse.setStatusCode(statusCode);
            if (responseStatusIsOk) {
                facebookResponse.setBody(EntityUtils.toString(entity1));
            } else {
                facebookResponse.setError(new UnknownError(EntityUtils.toString(entity1)));
            }

            EntityUtils.consume(entity1);
        } catch (ClientProtocolException e) {
            facebookResponse.setError(e);
            facebookResponse.setStatusCode(500);
            LOGGER.log(Level.WARNING, e.getMessage(), e);
        } catch (IOException e) {
            facebookResponse.setError(e);
            facebookResponse.setStatusCode(500);
            LOGGER.log(Level.WARNING, e.getMessage(), e);
        } catch (Throwable e) {
            facebookResponse.setError(e);
            facebookResponse.setStatusCode(500);
            LOGGER.log(Level.WARNING, e.getMessage(), e);
        } finally {
            httpGet.releaseConnection();
        }
        return facebookResponse;

    }

}
