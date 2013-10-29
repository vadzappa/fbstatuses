package com.gts.fb;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.gts.fb.net.HttpGetResponse;
import com.gts.fb.net.HttpGetResponseRetriever;
import com.gts.fb.util.PropertiesReader;
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
    private static FacebookAccessTokenRetriever facebookAccessTokenRetriever = new FacebookAccessTokenRetriever();

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
        HttpGetResponseRetriever httpGetResponseRetriever = new HttpGetResponseRetriever(facebookSearchUrl);
        HttpGetResponse httpGetResponse = httpGetResponseRetriever.retrieve();
        try {
            if (httpGetResponse.getError() != null) {
                result.putOnce("error", httpGetResponse.getError());
            }
            if (httpGetResponse.getBody() != null) {
                JSONObject facebookData = new JSONObject(httpGetResponse.getBody());
                result.putOnce("results", facebookData.getJSONArray("data"));
            }
            result.putOnce("status", httpGetResponse.getStatusCode());
        } catch (JSONException e) {
            LOGGER.log(Level.WARNING, e.getMessage(), e);
        }
        return result;
    }

    private String prepareFacebookUrl() {
        FacebookSearchUrlBuilder facebookSearchUrlBuilder = new FacebookSearchUrlBuilder();

        facebookSearchUrlBuilder.query(query).searchType(searchType).accessToken(facebookAccessTokenRetriever.retrieveLongLiveAccessToken());
        for (SearchParameter searchParameter : searchParameters) {
            facebookSearchUrlBuilder.searchParameter(searchParameter);
        }
        return facebookSearchUrlBuilder.build();
    }

}
