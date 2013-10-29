package com.gts.fb;

import org.json.JSONObject;
import org.junit.Test;

/**
 * <p><b> 2013 </b></p>
 *
 * @author Vadim
 */
public class TestStatusesRetriever {

    @Test
    public void testStatusRetrieving() {
        FacebookStatusesRetriever facebookStatusesRetriever = new FacebookStatusesRetriever("Syria", FacebookSearchType.POST);
        JSONObject facebookEntities = facebookStatusesRetriever.findFacebookEntities();
        System.out.println(facebookEntities);
        facebookStatusesRetriever = new FacebookStatusesRetriever("Minsk", FacebookSearchType.POST);
        facebookEntities = facebookStatusesRetriever.findFacebookEntities();
        System.out.println(facebookEntities);
        facebookStatusesRetriever = new FacebookStatusesRetriever("NY City", FacebookSearchType.POST);
        facebookEntities = facebookStatusesRetriever.findFacebookEntities();
        System.out.println(facebookEntities);
    }
}
