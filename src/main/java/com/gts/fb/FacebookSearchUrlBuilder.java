package com.gts.fb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

/**
 * <p><b> 2013 </b></p>
 *
 * @author Vadim
 */
public class FacebookSearchUrlBuilder {
    private static final String FACEBOOK_URL = "https://graph.facebook.com/search";

    private Map<String, String> parameters = new HashMap<String, String>(0);

    public FacebookSearchUrlBuilder() {
    }

    public FacebookSearchUrlBuilder query(String query) {
        parameters.put("q", query);
        return this;
    }

    public FacebookSearchUrlBuilder accessToken(String accessToken) {
        parameters.put("access_token", accessToken);
        return this;
    }

    public FacebookSearchUrlBuilder searchType(FacebookSearchType searchType) {
        parameters.put("type", searchType.toString());
        return this;
    }

    public FacebookSearchUrlBuilder searchParameter(SearchParameter searchParameter) {
        parameters.put(searchParameter.getName(), searchParameter.getValue());
        return this;
    }


    public String build() {
        String queryString = URLEncodedUtils.format(buildNameValuePairs(), "UTF-8");
        if (queryString.length() > 0) {
            return FACEBOOK_URL + "?" + queryString;
        }
        return FACEBOOK_URL;
    }

    private List<NameValuePair> buildNameValuePairs() {
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(parameters.size());
        for (String key : parameters.keySet()) {
            nameValuePairs.add(new BasicNameValuePair(key, parameters.get(key)));
        }
        return nameValuePairs;
    }
}
